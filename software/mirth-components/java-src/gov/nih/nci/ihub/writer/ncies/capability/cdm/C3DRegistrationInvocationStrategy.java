package gov.nih.nci.ihub.writer.ncies.capability.cdm;

import gov.nih.nci.ihub.util.IntegrationHubUtil;
import gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.sr.SubjectRegistrationTransformer;
import gov.nih.nci.ihub.writer.ncies.common.GenericInvocationStrategy;
import gov.nih.nci.ihub.writer.ncies.common.GridInvocationResult;
import gov.nih.nci.ihub.writer.ncies.exception.GridInvocationException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.servicemix.jbi.jaxp.SourceTransformer;
import org.w3c.dom.Element;

import clinicalconnector.nci.nih.gov.RegisterSubjectResponse;

public class C3DRegistrationInvocationStrategy extends
		GenericInvocationStrategy {

	private static Logger logger = LogManager
			.getLogger(C3DRegistrationInvocationStrategy.class);

	public GridInvocationResult invokeGridService(boolean isRollback)
			throws GridInvocationException {
		try {						
			
			if (isRollback) {
				return new GridInvocationResult(false, null, false);
			} else {
				SourceTransformer transformer = new SourceTransformer();
				String payloadString = transformer.toString(getPayload());
				SubjectRegistrationTransformer subjectRegistrationTransformer = new SubjectRegistrationTransformer();
				String registerSubjectConvertedString = "";
				registerSubjectConvertedString = subjectRegistrationTransformer
						.convert2RequestString(payloadString);
				Element originalPayload = getPayload(); 
				payload = IntegrationHubUtil
						.stringToDOMDocument(
								IntegrationHubUtil
										.convertPayloadIntoBusinessPayload(registerSubjectConvertedString))
						.getDocumentElement();

				GridInvocationResult gridInvocationResult = super.invokeGridService(isRollback);
				if (gridInvocationResult.isFault()) {
					//If an error happened invaking the C3D register subject service return it so that the
					//error is communicated back to the invoking application.
					return gridInvocationResult;
				}
							
				final Element response = (Element) subjectRegistrationTransformer
						.insertPatientPosition(transformer
								.toDOMDocument(originalPayload),
								((RegisterSubjectResponse) returnObject)
										.getPatientIdentifier().getExtension(),
								getServiceProviderName());
				return new GridInvocationResult(false, response, false);
			}			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Failed to invoke grid service." + serviceUrl, e);
			return new GridInvocationResult(true, null, false, e);
		}
	}

}
