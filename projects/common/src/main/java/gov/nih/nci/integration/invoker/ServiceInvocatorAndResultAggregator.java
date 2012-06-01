package gov.nih.nci.integration.invoker;

/**
 * Interface
 * 
 * @author Vinodh
 * 
 */
public interface ServiceInvocatorAndResultAggregator {

	/**
	 * This method is used for 'invokeService'
	 * 
	 * @param referenceMessageId
	 *            - MessageId
	 * @param message
	 *            - String message
	 * @param serviceInvocationStrategy
	 *            - ServiceInvocationStrategy
	 */
	void invokeService(Long referenceMessageId, String message,
			ServiceInvocationStrategy serviceInvocationStrategy);

	
	/**
	 * This method is used for 'aggregateResults'
	 * @param referenceMessageId - MessageId
	 * @return - ServiceInvocationResult
	 */
	ServiceInvocationResult aggregateResults(Long referenceMessageId);
}
