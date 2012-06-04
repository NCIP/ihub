package gov.nih.nci.integration.invoker;

/**
 * Implementations of this interface are responsible for invoking all target services within a single thread of
 * execution of the MessageBroadcasterChannel channel, and then aggregating the results of those invocations
 * 
 * @author Vinodh
 * 
 */
public interface ServiceInvocatorAndResultAggregator {

    /**
     * This method is used for 'invokeService'
     * 
     * @param referenceMessageId - MessageId
     * @param message - String message
     * @param serviceInvocationStrategy - ServiceInvocationStrategy
     */
    void invokeService(Long referenceMessageId, String message, ServiceInvocationStrategy serviceInvocationStrategy);

    /**
     * This method is used for 'aggregateResults'
     * 
     * @param referenceMessageId - MessageId
     * @return - ServiceInvocationResult
     */
    ServiceInvocationResult aggregateResults(Long referenceMessageId);
}
