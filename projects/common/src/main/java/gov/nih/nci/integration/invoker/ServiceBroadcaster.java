package gov.nih.nci.integration.invoker;

/**
 * ServiceBroadcaster interface - Implementations of this interface are responsible for doing the work that must be done
 * prior to and post invoking a target service  
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
