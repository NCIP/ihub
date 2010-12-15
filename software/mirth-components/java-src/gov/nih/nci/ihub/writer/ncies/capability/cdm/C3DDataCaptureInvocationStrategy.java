package gov.nih.nci.ihub.writer.ncies.capability.cdm;

import gov.nih.nci.ihub.util.IntegrationHubUtil;
import gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.dc.DataCaptureTransformer;
import gov.nih.nci.ihub.writer.ncies.common.GenericInvocationStrategy;
import gov.nih.nci.ihub.writer.ncies.common.GridInvocationResult;
import gov.nih.nci.ihub.writer.ncies.exception.GridInvocationException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.servicemix.jbi.jaxp.SourceTransformer;

public class C3DDataCaptureInvocationStrategy extends
		GenericInvocationStrategy {

	private static Logger logger = LogManager
			.getLogger(C3DDataCaptureInvocationStrategy.class);

	public GridInvocationResult invokeGridService(boolean isRollback)
			throws GridInvocationException {
		try {
			SourceTransformer transformer = new SourceTransformer();
			String payloadString = transformer.toString(getPayload());
			DataCaptureTransformer dataCaptureTransformer = new DataCaptureTransformer();
			String registerSubjectConvertedString = "";
			if (isRollback) {
				registerSubjectConvertedString = dataCaptureTransformer
						.convert2RollbackRequestString(payloadString);
			} else {
				registerSubjectConvertedString = dataCaptureTransformer
						.convert2RequestString(payloadString);
			}			 
			payload = IntegrationHubUtil
					.stringToDOMDocument(
							IntegrationHubUtil
									.convertPayloadIntoBusinessPayload(registerSubjectConvertedString))
					.getDocumentElement();

			return super.invokeGridService(isRollback);			
		} catch (Exception e) {
			logger.error("Failed to invoke grid service." + serviceUrl, e);
			return new GridInvocationResult(true, null, false, e);
		}
	}

}
