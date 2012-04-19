package gov.nih.nci.integration.caaers.invoker;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.MalformedURLException;

import javax.xml.bind.JAXBException;
import javax.xml.ws.soap.SOAPFaultException;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.integration.caaers.CaAERSParticipantServiceWSClient;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;
import gov.nih.nci.integration.invoker.ServiceInvocationStrategy;
import gov.nih.nci.integration.transformer.XSLTTransformer;

/**
 * 
 * @author chandrasekaravr
 * 
 */
public class CaAERSRegistrationServiceInvocationStrategy implements
		ServiceInvocationStrategy {

	private CaAERSParticipantServiceWSClient client;

	private int retryCount;

	private XSLTTransformer xsltTransformer;

	public CaAERSRegistrationServiceInvocationStrategy(
			XSLTTransformer xsltTransformer,
			CaAERSParticipantServiceWSClient client, int retryCount) {
		super();
		this.xsltTransformer = xsltTransformer;
		this.client = client;
		this.retryCount = retryCount;
		
		System.out.println("sis cl " + getClass().getClassLoader());
		System.out.println("client cl " + client.getClass().getClassLoader());
	}

	@Override
	public int getRetryCount() {
		return retryCount;
	}

	@Override
	public StrategyIdentifier getStrategyIdentifier() {
		return StrategyIdentifier.CAEERS_CREATE_REGISTRATION;
	}

	@Override
	public ServiceInvocationResult invoke(String arg0) {
		ServiceInvocationResult result = new ServiceInvocationResult();
		try {
			String participantXMLStr = transformToParticipantXML(arg0);
			CaaersServiceResponse caaersresponse = client.createParticipant(participantXMLStr);
			ServiceResponse response = caaersresponse.getServiceResponse();
			if ("0".equals(response.getResponsecode())) { 
				result.setResult(response.getResponsecode() + " : " + response.getMessage());
			} else {
				IntegrationException ie = new IntegrationException(
						IntegrationError._1020, new Throwable(response.getMessage()), null);
				result.setInvocationException(ie);				
			}
		} catch (SOAPFaultException e) {
			e.printStackTrace();
			IntegrationException ie = new IntegrationException(
					IntegrationError._1020, e, null);
			result.setInvocationException(ie);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			IntegrationException ie = new IntegrationException(
					IntegrationError._1019, e, null);
			result.setInvocationException(ie);
		} catch (JAXBException e) {
			e.printStackTrace();
			IntegrationException ie = new IntegrationException(
					IntegrationError._1018, e, null);
			result.setInvocationException(ie);
		} catch (IntegrationException e) {
			result.setInvocationException(e);
		}
		return result;
	}

	@Override
	public ServiceInvocationResult rollback(String arg0) {
		ServiceInvocationResult result = new ServiceInvocationResult();
		try {
			String participantXMLStr = transformToParticipantXML(arg0);
			CaaersServiceResponse caaersresponse = client.deleteParticipant(participantXMLStr);
			ServiceResponse response = caaersresponse.getServiceResponse();
			if ("0".equals(response.getResponsecode())) { 
				result.setResult(response.getResponsecode() + " : " + response.getMessage());
			} else {
				IntegrationException ie = new IntegrationException(
						IntegrationError._1020, new Throwable(response.getMessage()), null);
				result.setInvocationException(ie);
			}
		} catch (SOAPFaultException e) {
			e.printStackTrace();
			IntegrationException ie = new IntegrationException(
					IntegrationError._1020, e, null);
			result.setInvocationException(ie);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			IntegrationException ie = new IntegrationException(
					IntegrationError._1019, e, null);
			result.setInvocationException(ie);
		} catch (JAXBException e) {
			e.printStackTrace();
			IntegrationException ie = new IntegrationException(
					IntegrationError._1018, e, null);
			result.setInvocationException(ie);
		} catch (IntegrationException e) {
			result.setInvocationException(e);
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

}
