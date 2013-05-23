/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.writer.ncies.capability.cdm;

import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.BL;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.LoadLabsRequest;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.LoadLabsResponse;
import gov.nih.nci.caxchange.servicemix.bean.clinicalconnector.DataCaptureServiceStub.Message;
import gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.dc.DataCaptureTransformer;
import gov.nih.nci.ihub.writer.ncies.common.GenericInvocationStrategy;
import gov.nih.nci.ihub.writer.ncies.common.GridInvocationResult;
import gov.nih.nci.ihub.writer.ncies.exception.GridInvocationException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.servicemix.jbi.jaxp.SourceTransformer;

public class CCDataCaptureInvocationStrategy extends GenericInvocationStrategy {

	private static Logger logger = LogManager
			.getLogger(CCDataCaptureInvocationStrategy.class);

	public GridInvocationResult invokeGridService(boolean isRollback)
			throws GridInvocationException {
		try {
			SourceTransformer transformer = new SourceTransformer();
			String payloadString = transformer.toString(getPayload());
			DataCaptureTransformer dataCaptureTransformer = new DataCaptureTransformer();
			DataCaptureClient dataCaptureClient = new DataCaptureClient(
					serviceUrl, username, password, connectionTimeout);
			if (isRollback) {
				LoadLabsRequest request = (LoadLabsRequest) dataCaptureTransformer
						.convert2RollbackRequest(payloadString);
				dataCaptureClient.rollbackLoadLabs(request);
				logger.debug("Data capture ROLLBACK called");
				return new GridInvocationResult(false, null, false);

			} else {
				LoadLabsRequest request = (LoadLabsRequest) dataCaptureTransformer
						.convert2Request(payloadString);
				LoadLabsResponse clinicalConnectorresponse = dataCaptureClient
						.loadLabs(request);

				BL responseIndicator = clinicalConnectorresponse.getIndicator();
				if (responseIndicator == null) {
					return new GridInvocationResult(true, null, false,
							new InvokeClinicalConnectorException(
									"Indicator NOT set in response."));
				}
				if (!responseIndicator.getValue()) {
					// failure case
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
				} else {
					return new GridInvocationResult(false, getPayload(), false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Failed to invoke grid service: " + serviceUrl, e);
			return new GridInvocationResult(true, null, false, e);
		}
	}

}
