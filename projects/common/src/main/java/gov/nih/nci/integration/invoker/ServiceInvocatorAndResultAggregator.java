package gov.nih.nci.integration.invoker;



public interface ServiceInvocatorAndResultAggregator {

	void invokeService(Long referenceMessageId, String message,
			ServiceInvocationStrategy serviceInvocationStrategy);

	ServiceInvocationResult aggregateResults(Long referenceMessageId);
}
