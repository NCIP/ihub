package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.catissue.CaTissueParticipantClient;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.transformer.XSLTTransformer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CaTissueUpdateRegistrationServiceInvocationStrategy implements
		ServiceInvocationStrategy {

	private static Logger LOG = LoggerFactory
			.getLogger(CaTissueRegistrationServiceInvocationStrategy.class);

	private int retryCount = 0;

	private CaTissueParticipantClient caTissueParticipantClient;

	private XSLTTransformer xsltTransformer;
	
	
	public CaTissueUpdateRegistrationServiceInvocationStrategy(int retryCount,
			CaTissueParticipantClient caTissueParticipantClient,
			XSLTTransformer xsltTransformer) {
		super();
		this.retryCount = retryCount;
		this.caTissueParticipantClient = caTissueParticipantClient;
		this.xsltTransformer = xsltTransformer;
	}

	@Override
	public int getRetryCount() {
		return retryCount;
	}

	@Override
	public StrategyIdentifier getStrategyIdentifier() {
		return StrategyIdentifier.CATISSUE_UPDATE_REGISTRATION;
	}

	@Override
	public ServiceInvocationResult invoke(ServiceInvocationMessage msg) {
		ServiceInvocationResult serviceInvocationResult = new ServiceInvocationResult();
		try {
			String participantXMLStr = transformToParticipantXML(msg.getMessage().getRequest());
			serviceInvocationResult = caTissueParticipantClient.updateRegistrationParticipant(participantXMLStr);
		} catch (IntegrationException e) {
			serviceInvocationResult.setInvocationException(e);
		}
		return serviceInvocationResult;
	}

	@Override
	public ServiceInvocationResult rollback(ServiceInvocationMessage msg) {
		ServiceInvocationResult serviceInvocationResult = new ServiceInvocationResult();
		try {
			String participantXMLStr = transformToParticipantXML(msg.getOriginalData());
			serviceInvocationResult = caTissueParticipantClient.updateRegistrationParticipant(participantXMLStr);
		} catch (IntegrationException e) {
			serviceInvocationResult.setInvocationException(e);
		} 
		return serviceInvocationResult;
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
			LOG.debug("Error transforming to catissue participant XML!", e);
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
