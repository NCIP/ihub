/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.caaers.invoker;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantType;
import gov.nih.nci.cabig.caaers.integration.schema.participant.Participants;
import gov.nih.nci.integration.caaers.CaAERSParticipantServiceWSClient;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;
import gov.nih.nci.integration.invoker.ServiceInvocationStrategy;
import gov.nih.nci.integration.transformer.XSLTTransformer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.MalformedURLException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.ws.soap.SOAPFaultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author chandrasekaravr
 * 
 */
public class CaAERSUpdateRegistrationServiceInvocationStrategy implements
		ServiceInvocationStrategy {
	
	private static Logger LOG = LoggerFactory
		.getLogger(CaAERSUpdateRegistrationServiceInvocationStrategy.class);
	
	private static QName QNAME = new QName("http://schema.integration.caaers.cabig.nci.nih.gov/participant", "participant");;

	private CaAERSParticipantServiceWSClient client;

	private int retryCount;

	private XSLTTransformer xsltTransformer;
	
	private Marshaller marshaller = null;

	public CaAERSUpdateRegistrationServiceInvocationStrategy(
			XSLTTransformer xsltTransformer,
			CaAERSParticipantServiceWSClient client, int retryCount) {
		super();
		this.xsltTransformer = xsltTransformer;
		this.client = client;
		this.retryCount = retryCount;
	}

	@Override
	public int getRetryCount() {
		return retryCount;
	}

	@Override
	public StrategyIdentifier getStrategyIdentifier() {
		return StrategyIdentifier.CAEERS_UPDATE_REGISTRATION;
	}

	@Override
	public ServiceInvocationResult invoke(ServiceInvocationMessage msg) {
		ServiceInvocationResult result = new ServiceInvocationResult();
		try {
			
			String participantXMLStr = transformToParticipantXML(msg.getMessage().getRequest());
			
			//TODO : get actual original participant data, for now, do some mock up
			/*String orginalData = participantXMLStr;
			String markup =	"firstName>";
			orginalData = orginalData.replaceAll(markup, markup + "original-");
			result.setDataChanged(true);
			result.setOriginalData(orginalData);*/
			
			//TODO : once caaers server is fixed uncomment it
			CaaersServiceResponse caaersGetResponse = client.getParticipant(participantXMLStr);	
			ServiceResponse response = caaersGetResponse.getServiceResponse();
			ParticipantType existingPrtcpnt = null;
			if ("0".equals(response.getResponsecode())) { 
				result.setResult(response.getResponsecode() + " : " + response.getMessage());
				Participants prtcpnts = (Participants)response.getResponseData().getAny();
				existingPrtcpnt = prtcpnts.getParticipant().get(0);
			} else {
				IntegrationException ie = new IntegrationException(
						IntegrationError._1053, response.getMessage());
				result.setInvocationException(ie);
				return result;
			}
			String originalData = marshalParticipantType(existingPrtcpnt);
			
			CaaersServiceResponse caaersresponse = client.updateParticipant(participantXMLStr);
			response = caaersresponse.getServiceResponse();
			if ("0".equals(response.getResponsecode())) { 
				result.setResult(response.getResponsecode() + " : " + response.getMessage());
				result.setDataChanged(true);
				result.setOriginalData(originalData);
			} else {
				IntegrationException ie = new IntegrationException(
						IntegrationError._1053, response.getMessage());
				result.setInvocationException(ie);
			}
		} catch (SOAPFaultException e) {
			e.printStackTrace();
			IntegrationException ie = new IntegrationException(
					IntegrationError._1053, e, e.getMessage());
			result.setInvocationException(ie);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			IntegrationException ie = new IntegrationException(
					IntegrationError._1053, e, e.getMessage());
			result.setInvocationException(ie);
		} catch (JAXBException e) {
			e.printStackTrace();
			IntegrationException ie = new IntegrationException(
					IntegrationError._1053, e, e.getMessage());
			result.setInvocationException(ie);
		} catch (IntegrationException e) {
			result.setInvocationException(e);
		}
		return result;
	}

	@Override
	public ServiceInvocationResult rollback(ServiceInvocationMessage msg) {
		ServiceInvocationResult result = new ServiceInvocationResult();
		try {
			String participantXMLStr = msg.getOriginalData();
			CaaersServiceResponse caaersresponse = client.updateParticipant(participantXMLStr);
			ServiceResponse response = caaersresponse.getServiceResponse();
			if ("0".equals(response.getResponsecode())) { 
				result.setResult(response.getResponsecode() + " : " + response.getMessage());
			} else {
				IntegrationException ie = new IntegrationException(
						IntegrationError._1053, response.getMessage());
				result.setInvocationException(ie);
			}
		} catch (SOAPFaultException e) {
			e.printStackTrace();
			IntegrationException ie = new IntegrationException(
					IntegrationError._1053, e, e.getMessage());
			result.setInvocationException(ie);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			IntegrationException ie = new IntegrationException(
					IntegrationError._1053, e, e.getMessage());
			result.setInvocationException(ie);
		} catch (JAXBException e) {
			e.printStackTrace();
			IntegrationException ie = new IntegrationException(
					IntegrationError._1053, e, e.getMessage());
			result.setInvocationException(ie);
		} 
		return result;
	}

	private String transformToParticipantXML(String message)
			throws IntegrationException {
		String participantXMLStr = null;
		InputStream is = null;
		ByteArrayOutputStream os = null;

		try {
			os = new ByteArrayOutputStream();
			is = new ByteArrayInputStream(message.getBytes());

			xsltTransformer.transform(null, is, os);

			participantXMLStr = new String(os.toByteArray());
		} catch (IntegrationException e) {
			e.printStackTrace();
			throw e;
		} finally {
			try {
				is.close();
			} catch (Exception e) {
			}
			try {
				os.close();
			} catch (Exception e) {
			}
		}
		return participantXMLStr;
	}	
	
	private String marshalParticipantType(ParticipantType participantType) throws IntegrationException {		
		StringWriter sw = new StringWriter();
		
		try {
			JAXBElement<ParticipantType> jaxbEle = new JAXBElement<ParticipantType>(QNAME, ParticipantType.class, null, participantType);		
			PrintWriter pw = new PrintWriter(sw);
			getMarshaller().marshal(jaxbEle, pw);
		} catch (JAXBException e) {
			IntegrationException ie = new IntegrationException(
					IntegrationError._1053, e, "Unable to serialize retrieved Participant");
			throw ie;
		}
		return sw.toString();
	}
	
	private Marshaller getMarshaller() throws JAXBException {
		if (marshaller == null) {
			JAXBContext jc = JAXBContext.newInstance(ParticipantType.class);
			marshaller = jc.createMarshaller();
		}
		return marshaller;
	}
}
