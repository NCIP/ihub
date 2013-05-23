/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.writer.ncies.core;

import gov.nih.nci.ihub.util.HubConstants;
import gov.nih.nci.ihub.util.IntegrationHubUtil;
import gov.nih.nci.ihub.writer.ncies.common.GridInvocationResult;
import gov.nih.nci.ihub.writer.ncies.exception.GridInvocationException;

import java.net.ConnectException;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;

public class CoppaOBInvocationStrategy extends CoppaInvocationStrategy {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(CoppaOBInvocationStrategy.class);

	protected CoppaInvocationStrategy organizationInvocationStrategy;
	protected CoppaInvocationStrategy identifiedOrganizationInvocationStrategy;

	public void copyCommonContents(
			CoppaOBInvocationStrategy coppaOBInvocationStrategy) {
		organizationInvocationStrategy = new CoppaInvocationStrategy();
		organizationInvocationStrategy.copyCommonContents(coppaOBInvocationStrategy
				.getOrganizationInvocationStrategy());
		organizationInvocationStrategy.init();
		this.identifiedOrganizationInvocationStrategy = new CoppaInvocationStrategy();
		this.identifiedOrganizationInvocationStrategy
				.copyCommonContents(coppaOBInvocationStrategy
						.getIdentifiedOrganizationInvocationStrategy());
		identifiedOrganizationInvocationStrategy.init();
		super.copyCommonContents(coppaOBInvocationStrategy);
	}

	public GridInvocationResult invokeGridService(boolean isRollback)
			throws GridInvocationException {
		try {
			IntegrationHubUtil iHubUtil = new IntegrationHubUtil();
			Element identifiedOrganizationPayload = iHubUtil
					.transformXML(
							HubConstants.ORCHESTRATION_OBS_TRANSFORM_INPUT_TO_IDENTIFIED_ORG_XSL,
							payload);

			logger.warn("Identified Organization Transformed XML: "
					+ IntegrationHubUtil.xmlToString(identifiedOrganizationPayload));

			identifiedOrganizationInvocationStrategy.setStrategySpecificVariables(
					HubConstants.ORCHESTRATION_OBS_OPERATION_IDENTIFIED_ORG,
					getServiceType(), identifiedOrganizationPayload, getSubject(),
					getServiceType());

			GridInvocationResult identifiedInvocationResult = identifiedOrganizationInvocationStrategy
					.invokeGridService(false);
			logger.warn("Identified Organization Response: "
					+ IntegrationHubUtil.xmlToString(identifiedInvocationResult
							.getResult()));

			if (!identifiedInvocationResult.isFault()) {
				int iPResultLookupCount = identifiedInvocationResult
						.getResult()
						.getOwnerDocument()
						.getElementsByTagNameNS(
								HubConstants.ORCHESTRATION_OBS_RESULT_PO_NAMESPACE,
								HubConstants.ORCHESTRATION_OBS_RESULT_IDENTIFIED_ORG_LOOKUP_ELEMENT)
						.getLength();
				logger.warn("Identified Result Lookup Count: "
						+ iPResultLookupCount);
				if (iPResultLookupCount == 0) {
					throw new GridInvocationException(
							HubConstants.ORCHESTRATION_OBS_EXCEPTION_MESSAGE_IDENTIFIED_ORG);
				} else {
					Element organizationPayload = iHubUtil
							.transformXML(
									HubConstants.ORCHESTRATION_OBS_TRANSFORM_IDENFIFIED_ORG_TO_ORG_XSL,
									identifiedInvocationResult.getResult());

					logger.warn("Organization Transformed XML: "
							+ IntegrationHubUtil.xmlToString(organizationPayload));
					organizationInvocationStrategy
							.setStrategySpecificVariables(
									HubConstants.ORCHESTRATION_OBS_OPERATION_ORG,
									getServiceType(),
									IntegrationHubUtil
											.convertPayloadIntoBusinessPayload(organizationPayload),
									getSubject(), getServiceType());

					GridInvocationResult organizationInvocationResult = organizationInvocationStrategy
							.invokeGridService(false);
					logger.warn("Organization Response: "
							+ IntegrationHubUtil
									.xmlToString(organizationInvocationResult
											.getResult()));
					return organizationInvocationResult;
				}
			} else {
				if (identifiedInvocationResult.getInvocationException()
						.getCause() instanceof ConnectException) {
					return new GridInvocationResult(true, null, true,
							identifiedInvocationResult.getInvocationException());
				} else {
					return new GridInvocationResult(true, null, false,
							identifiedInvocationResult.getInvocationException());
				}
			}
		} catch (Exception e) {
			logger.error("Failed to invoke grid service." + serviceUrl, e);
			return new GridInvocationResult(true, null, false, e);
		}
	}

	public CoppaInvocationStrategy getOrganizationInvocationStrategy() {
		return organizationInvocationStrategy;
	}

	public void setOrganizationInvocationStrategy(
			CoppaInvocationStrategy organizationInvocationStrategy) {
		this.organizationInvocationStrategy = organizationInvocationStrategy;
	}

	public CoppaInvocationStrategy getIdentifiedOrganizationInvocationStrategy() {
		return identifiedOrganizationInvocationStrategy;
	}

	public void setIdentifiedOrganizationInvocationStrategy(
			CoppaInvocationStrategy identifiedOrganizationInvocationStrategy) {
		this.identifiedOrganizationInvocationStrategy = identifiedOrganizationInvocationStrategy;
	}
}
