package gov.nih.nci.ihub.writer.ncies.core;

import gov.nih.nci.ihub.util.HubConstants;
import gov.nih.nci.ihub.util.IntegrationHubUtil;
import gov.nih.nci.ihub.writer.ncies.common.GridInvocationResult;
import gov.nih.nci.ihub.writer.ncies.exception.GridInvocationException;

import java.net.ConnectException;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;

public class CoppaPBInvocationStrategy extends CoppaInvocationStrategy {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(CoppaPBInvocationStrategy.class);

	protected CoppaInvocationStrategy personInvocationStrategy;
	protected CoppaInvocationStrategy identifiedPersonInvocationStrategy;

	public void copyCommonContents(
			CoppaPBInvocationStrategy coppaPBInvocationStrategy) {
		personInvocationStrategy = new CoppaInvocationStrategy();
		personInvocationStrategy.copyCommonContents(coppaPBInvocationStrategy
				.getPersonInvocationStrategy());
		personInvocationStrategy.init();
		this.identifiedPersonInvocationStrategy = new CoppaInvocationStrategy();
		this.identifiedPersonInvocationStrategy
				.copyCommonContents(coppaPBInvocationStrategy
						.getIdentifiedPersonInvocationStrategy());
		identifiedPersonInvocationStrategy.init();
		super.copyCommonContents(coppaPBInvocationStrategy);
	}

	public GridInvocationResult invokeGridService(boolean isRollback)
			throws GridInvocationException {
		try {
			IntegrationHubUtil iHubUtil = new IntegrationHubUtil();
			Element identifiedPersonPayload = iHubUtil
					.transformXML(
							HubConstants.ORCHESTRATION_PBS_TRANSFORM_INPUT_TO_IDENTIFIED_PERSON_XSL,
							payload);

			logger.warn("Identified Person Transformed XML: "
					+ IntegrationHubUtil.xmlToString(identifiedPersonPayload));

			identifiedPersonInvocationStrategy.setStrategySpecificVariables(
					HubConstants.ORCHESTRATION_PBS_OPERATION_IDENTIFIED_PERSON,
					getServiceType(), identifiedPersonPayload, getSubject(),
					getServiceType());

			GridInvocationResult identifiedInvocationResult = identifiedPersonInvocationStrategy
					.invokeGridService(false);
			logger.warn("Identified Person Response: "
					+ IntegrationHubUtil.xmlToString(identifiedInvocationResult
							.getResult()));

			if (!identifiedInvocationResult.isFault()) {
				int iPResultLookupCount = identifiedInvocationResult
						.getResult()
						.getOwnerDocument()
						.getElementsByTagNameNS(
								HubConstants.ORCHESTRATION_PBS_RESULT_PO_NAMESPACE,
								HubConstants.ORCHESTRATION_PBS_RESULT_IDENTIFIED_PERSON_LOOKUP_ELEMENT)
						.getLength();
				logger.warn("Identified Result Lookup Count: "
						+ iPResultLookupCount);
				if (iPResultLookupCount == 0) {
					throw new GridInvocationException(
							HubConstants.ORCHESTRATION_PBS_EXCEPTION_MESSAGE_IDENTIFIED_PERSON);
				} else {
					Element personPayload = iHubUtil
							.transformXML(
									HubConstants.ORCHESTRATION_PBS_TRANSFORM_IDENFIFIED_PERSON_TO_PERSON_XSL,
									identifiedInvocationResult.getResult());

					logger.warn("Person Transformed XML: "
							+ IntegrationHubUtil.xmlToString(personPayload));
					personInvocationStrategy
							.setStrategySpecificVariables(
									HubConstants.ORCHESTRATION_PBS_OPERATION_PERSON,
									getServiceType(),
									IntegrationHubUtil
											.convertPayloadIntoBusinessPayload(personPayload),
									getSubject(), getServiceType());

					GridInvocationResult personInvocationResult = personInvocationStrategy
							.invokeGridService(false);
					logger.warn("Person Response: "
							+ IntegrationHubUtil
									.xmlToString(personInvocationResult
											.getResult()));
					return personInvocationResult;
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

	public CoppaInvocationStrategy getPersonInvocationStrategy() {
		return personInvocationStrategy;
	}

	public void setPersonInvocationStrategy(
			CoppaInvocationStrategy personInvocationStrategy) {
		this.personInvocationStrategy = personInvocationStrategy;
	}

	public CoppaInvocationStrategy getIdentifiedPersonInvocationStrategy() {
		return identifiedPersonInvocationStrategy;
	}

	public void setIdentifiedPersonInvocationStrategy(
			CoppaInvocationStrategy identifiedPersonInvocationStrategy) {
		this.identifiedPersonInvocationStrategy = identifiedPersonInvocationStrategy;
	}
}
