package gov.nih.nci.integration.invoker;



public interface ServiceInvocatorAndResultAggregator {

	void invokeService(Long refMessageId, String message,
			ServiceInvocationStrategy serviceInvocationStrategy);

	ServiceInvocationResult aggregateResults();
}
