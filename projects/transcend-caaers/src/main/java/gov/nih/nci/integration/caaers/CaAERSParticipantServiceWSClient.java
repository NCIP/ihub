package gov.nih.nci.integration.caaers;

import gov.nih.nci.cabig.caaers.webservice.CreateParticipant;
import gov.nih.nci.cabig.caaers.webservice.DeleteParticipant;
import gov.nih.nci.cabig.caaers.webservice.ParticipantServiceIntf;
import gov.nih.nci.cabig.caaers.webservice.ParticipantType;
import gov.nih.nci.cabig.caaers.webservice.Response;
import gov.nih.nci.cabig.caaers.webservice.CreateParticipant.Participants;
import gov.nih.nci.cabig.caaers.webservice.CreateParticipantResponse.Return;

import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;

public class CaAERSParticipantServiceWSClient {

	private Unmarshaller unmarshaller = null;

	private ParticipantServiceIntf client;

	private String userName;

	private ClientPasswordCallback clientPasswordCallback;

	public CaAERSParticipantServiceWSClient(String wsdl, String userName,
			ClientPasswordCallback clientPasswordCallback) throws JAXBException {
		super();
		this.userName = userName;
		this.clientPasswordCallback = clientPasswordCallback;

		getUnmarshaller();

		initClient(wsdl);
	}

	private void initClient(String wsdl) {
		// Manual WSS4JOutInterceptor interceptor process - start
		Map outProps = new HashMap();
		outProps.put(WSHandlerConstants.ACTION,
				WSHandlerConstants.USERNAME_TOKEN);
		outProps.put(WSHandlerConstants.USER, userName);
		outProps.put(WSHandlerConstants.PASSWORD_TYPE, WSConstants.PW_TEXT);
		outProps.put(WSHandlerConstants.PW_CALLBACK_REF, clientPasswordCallback);

		WSS4JOutInterceptor wssOut = new WSS4JOutInterceptor(outProps);

		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

		factory.getOutInterceptors().add(wssOut);
		factory.setServiceClass(ParticipantServiceIntf.class);
		factory.setAddress(wsdl);
		client = (ParticipantServiceIntf) factory.create();
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

	public Response createParticipant(String participantXMLStr)
			throws JAXBException, MalformedURLException, SOAPFaultException {
		ParticipantType participant = parseParticipant(participantXMLStr);
		System.out.println(participant.getFirstName());
		System.out.println(participant.getLastName());
		System.out.println(participant.getGender());
		System.out.println(participant.getIdentifiers());
		CreateParticipant createParticipant = new CreateParticipant();
		Participants participants = new Participants();
		participants.getParticipant().add(participant);
		createParticipant.setParticipants(participants);

		Return retValue = null;
		try {
			retValue = client.createParticipant(createParticipant
					.getParticipants());
		} catch (SOAPFaultException e) {
			e.printStackTrace();
			throw e;
		}
		return retValue.getResponse();
	}

	public Response deleteParticipant(String participantXMLStr)
			throws JAXBException, MalformedURLException, SOAPFaultException {
		ParticipantType participant = parseParticipant(participantXMLStr);
		DeleteParticipant deleteParticipant = new DeleteParticipant();
		gov.nih.nci.cabig.caaers.webservice.DeleteParticipant.Participants participants = 
			new gov.nih.nci.cabig.caaers.webservice.DeleteParticipant.Participants();
		participants.getParticipant().add(participant);
		deleteParticipant.setParticipants(participants);

		gov.nih.nci.cabig.caaers.webservice.DeleteParticipantResponse.Return retValue = null;
		try {
			retValue = client.deleteParticipant(deleteParticipant
					.getParticipants());
		} catch (SOAPFaultException e) {
			e.printStackTrace();
			throw e;
		}
		return retValue.getResponse();
	}
}
