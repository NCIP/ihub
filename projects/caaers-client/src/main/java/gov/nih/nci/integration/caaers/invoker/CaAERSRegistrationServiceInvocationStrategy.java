package gov.nih.nci.integration.caaers.invoker;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.WsError;
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
import java.net.MalformedURLException;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.ws.soap.SOAPFaultException;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author chandrasekaravr
 * 
 */
public class CaAERSRegistrationServiceInvocationStrategy implements
		ServiceInvocationStrategy {
	
	private static Logger LOG = LoggerFactory
			.getLogger(CaAERSRegistrationServiceInvocationStrategy.class);

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
	public ServiceInvocationResult invoke(ServiceInvocationMessage msg) {
		ServiceInvocationResult result = new ServiceInvocationResult();
		try {
			String participantXMLStr = transformToParticipantXML(msg.getMessage().getRequest());
			
			CaaersServiceResponse caaersresponse = client.createParticipant(participantXMLStr);
			ServiceResponse response = caaersresponse.getServiceResponse();
			if ("0".equals(response.getResponsecode())) { 
				result.setResult(response.getResponsecode() + " : " + response.getMessage());
				result.setDataChanged(true);
				//there is no original data
			} else {
				List<WsError> wserrors = response.getWsError();
				WsError error = null;
				IntegrationException ie = null;
				if(wserrors != null && !wserrors.isEmpty()){
					error = wserrors.get(0);
					ie = new IntegrationException(
						IntegrationError._1053, new Throwable(error.getException()), error.getErrorDesc() );
				} else {
					ie = new IntegrationException(
						IntegrationError._1053, response.getMessage() );
				}
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
		handleException(result);
		return result;
	}

	@Override
	public ServiceInvocationResult rollback(ServiceInvocationMessage msg) {
		ServiceInvocationResult result = new ServiceInvocationResult();
		try {
			String participantXMLStr = transformToParticipantXML(msg.getMessage().getRequest());
			CaaersServiceResponse caaersresponse = client.deleteParticipant(participantXMLStr);
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
	
	private void handleException(ServiceInvocationResult result) {
		if(!result.isFault()) {
			return;
		}
		
		Exception exception = result.getInvocationException();
		Throwable cause = exception;
		while(cause instanceof IntegrationException) {
			cause = cause.getCause();
		}
		if(cause == null){
			return;
		}
		String stackTrace = ExceptionUtils.getFullStackTrace(cause);
		IntegrationException newie = (IntegrationException) exception;
		if(stackTrace.contains("Invalid Username/Password")){
			newie = new IntegrationException(IntegrationError._1011, cause, (Object)null );
		} else if (stackTrace.contains("Could not send Message")){
			newie = new IntegrationException(IntegrationError._1020, cause, (Object)null );
		} 
		result.setInvocationException(newie);
	}

}
