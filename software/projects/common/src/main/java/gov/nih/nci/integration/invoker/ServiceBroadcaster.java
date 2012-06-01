package gov.nih.nci.integration.invoker;

/**
 * ServiceBroadcaster interface
 * 
 * @author Vinodh
 * 
 */
public interface ServiceBroadcaster {

    /**
     * delegateServiceInvocation
     * 
     * @param referenceMessageId - MessageId
     * @param message - Message
     * @param serviceInvocationStrategy - ServiceInvocationStrategy
     * @return ServiceInvocationResult
     */
    ServiceInvocationResult delegateServiceInvocation(Long referenceMessageId, String message,
            ServiceInvocationStrategy serviceInvocationStrategy);
}
