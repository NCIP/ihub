package gov.nih.nci.integration.caaers;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.participant.CreateParticipant;
import gov.nih.nci.cabig.caaers.integration.schema.participant.CreateParticipantResponse;
import gov.nih.nci.cabig.caaers.integration.schema.participant.DeleteParticipant;
import gov.nih.nci.cabig.caaers.integration.schema.participant.DeleteParticipantResponse;
import gov.nih.nci.cabig.caaers.integration.schema.participant.GetParticipant;
import gov.nih.nci.cabig.caaers.integration.schema.participant.GetParticipantResponse;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantRef;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantServiceInterface;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.Participants;
import gov.nih.nci.cabig.caaers.integration.schema.participant.UpdateParticipant;
import gov.nih.nci.cabig.caaers.integration.schema.participant.UpdateParticipantResponse;
import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;

import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.springframework.remoting.soap.SoapFaultException;

public class CaAERSParticipantServiceWSClient {

	private Unmarshaller unmarshaller = null;

	private ParticipantServiceInterface client;

	private String userName;

	private ClientPasswordCallback clientPasswordCallback;

	public CaAERSParticipantServiceWSClient(String serviceUrl, String userName,
			ClientPasswordCallback clientPasswordCallback) throws IntegrationException {
		super();
		this.userName = userName;
		this.clientPasswordCallback = clientPasswordCallback;

		try {
			getUnmarshaller();

			initClient(serviceUrl);			
		} catch (Exception e) {
			e.printStackTrace();
			throw new IntegrationException(IntegrationError._1054, e.getMessage());
		}
	}

	private void initClient(String serviceUrl) {
		// Manual WSS4JOutInterceptor interceptor process - start
		Map outProps = new HashMap();
		outProps.put(WSHandlerConstants.ACTION,
				WSHandlerConstants.USERNAME_TOKEN);
		outProps.put(WSHandlerConstants.USER, userName);
		outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
		outProps
				.put(WSHandlerConstants.PW_CALLBACK_REF, clientPasswordCallback);

		WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		factory.getOutInterceptors().add(wssOut);
		factory.setServiceClass(ParticipantServiceInterface.class);
		factory.setAddress(serviceUrl);

		client = (ParticipantServiceInterface) factory.create();

		Client clientProxy = ClientProxy.getClient(client);
		
		HTTPConduit http = (HTTPConduit) clientProxy.getConduit();
		TLSClientParameters tlsClientParams = new TLSClientParameters();
		tlsClientParams.setDisableCNCheck(true);
		http.setTlsClientParameters(tlsClientParams);
	}

	private Unmarshaller getUnmarshaller() throws JAXBException {
		if (unmarshaller == null) {
			JAXBContext jc = JAXBContext.newInstance(ParticipantType.class);
			unmarshaller = jc.createUnmarshaller();
		}
		return unmarshaller;
	}

	/**
	 * Unmarshalls a ParticipantType object from an xml string
	 * 
	 * @param participantXMLStr
	 *            - the Participant xml string
	 * @return ParticipantType object
	 * @throws JAXBException
	 */
	public ParticipantType parseParticipant(String participantXMLStr)
			throws JAXBException {
		JAXBElement<ParticipantType> jaxbEle = (JAXBElement<ParticipantType>) getUnmarshaller()
				.unmarshal(
						new StreamSource(new StringReader(participantXMLStr)),
						ParticipantType.class);
		return jaxbEle.getValue();
	}

	public CaaersServiceResponse createParticipant(String participantXMLStr)
			throws JAXBException, MalformedURLException, SOAPFaultException, IntegrationException {
		ParticipantType participant = parseParticipant(participantXMLStr);
		
		CreateParticipant createParticipant = new CreateParticipant();
		Participants participants = new Participants();
		participants.getParticipant().add(participant);
		createParticipant.setParticipants(participants);

		CreateParticipantResponse retValue = null;
		try {
			retValue = client.createParticipant(createParticipant);
		} catch (SOAPFaultException e) {
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new IntegrationException(IntegrationError._1053, e, (Object) null);
		}
		return retValue.getCaaersServiceResponse();
	}

	public CaaersServiceResponse deleteParticipant(String participantXMLStr)
			throws JAXBException, MalformedURLException, SOAPFaultException, IntegrationException {
		ParticipantType participant = parseParticipant(participantXMLStr);
		
		DeleteParticipant deleteParticipant = new DeleteParticipant();
		Participants participants = new Participants();
		participants.getParticipant().add(participant);
		deleteParticipant.setParticipants(participants);

		DeleteParticipantResponse retValue = null;
		try {
			retValue = client.deleteParticipant(deleteParticipant);
		} catch (SOAPFaultException e) {
			//e.printStackTrace();
			throw e;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new IntegrationException(IntegrationError._1053, e, (Object) null);
		}
		return retValue.getCaaersServiceResponse();
	}

	public CaaersServiceResponse updateParticipant(String participantXMLStr)
			throws JAXBException, MalformedURLException, SOAPFaultException, IntegrationException {
		ParticipantType participant = parseParticipant(participantXMLStr);
		
		UpdateParticipant updateParticipant = new UpdateParticipant();
		Participants participants = new Participants();
		participants.getParticipant().add(participant);
		updateParticipant.setParticipants(participants);

		UpdateParticipantResponse retValue = null;
		try {
			retValue = client.updateParticipant(updateParticipant);
		} catch (SOAPFaultException e) {
			//e.printStackTrace();
			throw e;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new IntegrationException(IntegrationError._1053, e, (Object) null);
		}
		return retValue.getCaaersServiceResponse();
	}

	public CaaersServiceResponse getParticipant(String participantXMLStr)
			throws JAXBException, MalformedURLException, SOAPFaultException, IntegrationException {
		ParticipantType participant = parseParticipant(participantXMLStr);	
		
		GetParticipant getParticipant = new GetParticipant();
		ParticipantRef participantRef = new ParticipantRef();
		ParticipantRef.Identifiers refIds = new ParticipantRef.Identifiers();
		participantRef.setIdentifiers(refIds);
		ParticipantType.Identifiers prtcpntIds = participant.getIdentifiers();
		
		refIds.getOrganizationAssignedIdentifier().addAll(prtcpntIds.getOrganizationAssignedIdentifier());
		refIds.getSystemAssignedIdentifier().addAll(prtcpntIds.getSystemAssignedIdentifier());
		
		getParticipant.setParticipantRef(participantRef);
		
		GetParticipantResponse retValue = null;
		try {
			retValue = client.getParticipant(getParticipant);
		} catch (SOAPFaultException e) {
			//e.printStackTrace();
			throw e;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new IntegrationException(IntegrationError._1053, e, (Object) null);
		}
		return retValue.getCaaersServiceResponse();
	}
}
