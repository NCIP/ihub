package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;

/**
 * Interface for Service Strategy classes
 * 
 * @author Vinodh
 * 
 */
public interface ServiceInvocationStrategy {

	/**
	 * getStrategyIdentifier
	 * 
	 * @return Strategy Identifier
	 */
	StrategyIdentifier getStrategyIdentifier();

	/**
	 * getRetryCount
	 * 
	 * @return Retry Count
	 */
	int getRetryCount();

	/**
	 * invoke
	 * 
	 * @param serviceInvocationMessage
	 *            - serviceInvocationMessage
	 * @return ServiceInvocationResult
	 */
	ServiceInvocationResult invoke(
			ServiceInvocationMessage serviceInvocationMessage);

	/**
	 * rollback
	 * 
	 * @param serviceInvocationMessage
	 *            - ServiceInvocationMessage
	 * @return ServiceInvocationResult containing rollback info
	 */
	ServiceInvocationResult rollback(
			ServiceInvocationMessage serviceInvocationMessage);

}
