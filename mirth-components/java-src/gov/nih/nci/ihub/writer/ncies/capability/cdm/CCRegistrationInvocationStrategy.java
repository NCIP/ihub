package gov.nih.nci.ihub.writer.ncies.capability.cdm;

import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.BL;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.Message;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.RegisterSubjectRequest;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.SubjectRegistrationServiceStub.RegisterSubjectResponse;
import gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.sr.SubjectRegistrationTransformer;
import gov.nih.nci.ihub.writer.ncies.common.GenericInvocationStrategy;
import gov.nih.nci.ihub.writer.ncies.common.GridInvocationResult;
import gov.nih.nci.ihub.writer.ncies.exception.GridInvocationException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.servicemix.jbi.jaxp.SourceTransformer;
import org.w3c.dom.Element;

public class CCRegistrationInvocationStrategy extends GenericInvocationStrategy {

	private static Logger logger = LogManager
			.getLogger(CCRegistrationInvocationStrategy.class);

	public GridInvocationResult invokeGridService(boolean isRollback)
			throws GridInvocationException {
		try {
			SourceTransformer transformer = new SourceTransformer();
			String payloadString = transformer.toString(getPayload());
			SubjectRegistrationTransformer subjectRegistrationTransformer = new SubjectRegistrationTransformer();
			SubjectRegistrationClient registrationClient = new SubjectRegistrationClient(
					serviceUrl, username, password, connectionTimeout);
			if (isRollback) {
				RegisterSubjectRequest request = (RegisterSubjectRequest) subjectRegistrationTransformer
						.convert2RollbackRequest(payloadString);
				registrationClient.rollbackRegisterSubject(request);
				System.out.println("Registeration ROLLBACK called");
				return new GridInvocationResult(false, null, false);

			} else {
				RegisterSubjectRequest request = (RegisterSubjectRequest) subjectRegistrationTransformer
						.convert2Request(payloadString);
				RegisterSubjectResponse clinicalConnectorresponse = registrationClient
						.registerSubject(request);

				BL responseIndicator = clinicalConnectorresponse.getIndicator();
				if (responseIndicator == null) {
					return new GridInvocationResult(true, null, false,
							new InvokeClinicalConnectorException(
									"Indicator NOT set in response."));
				}
				if (responseIndicator.getValue()) {
					final Element transformedResponse = (Element) subjectRegistrationTransformer
							.insertPatientPosition(transformer
									.toDOMDocument(getPayload()),
									clinicalConnectorresponse
											.getPatientIdentifier()
											.getExtension(),
									getServiceProviderName());
					return new GridInvocationResult(false, transformedResponse,
							false);
				} else {
					Message msg = clinicalConnectorresponse.getMessage();
					if (msg != null) {
						String errorMessage = "Error in registerSubject: "
								+ msg.getCode() + " - " + msg.getCode() + ".";

						return new GridInvocationResult(true, null, false,
								new InvokeClinicalConnectorException(
										errorMessage));
					} else {
						return new GridInvocationResult(true, null, false,
								new InvokeClinicalConnectorException(
										"Error message NOT set in response."));
					}
				}
			}
		} catch (InsertPatientPositionException e) {
			logger.error("Error inserting patient position", e);
			return new GridInvocationResult(true, null, false,
					new InvokeClinicalConnectorException(
							"Error inserting patient position", e));
		} catch (Exception e) {
			logger.error("Failed to invoke grid service: " + serviceUrl, e);
			return new GridInvocationResult(true, null, false, e);
		}

	}
}
