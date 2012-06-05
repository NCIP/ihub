package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;

import java.util.concurrent.Callable;

import org.apache.commons.lang.StringUtils;

/**
 * This class provide method to call the invoke method of a strategy class.
 * @author Vinodh
 *
 */
public class ServiceBroadcasterTask implements Callable<ServiceInvocationResult> {

    private ServiceBroadcaster serviceBroadcaster;

    private Long referenceMessageId;

    private String message;

    private ServiceInvocationStrategy serviceInvocationStrategy;

    /**
     * Constructor
     * @param serviceBroadcaster - DefaultServiceBroadcaster object
     * @param referenceMessageId - messageId
     * @param message - XMLString message to be passed
     * @param serviceInvocationStrategy - Strategy class object whose invoke has to be called
     */
    public ServiceBroadcasterTask(ServiceBroadcaster serviceBroadcaster, Long referenceMessageId, String message,
            ServiceInvocationStrategy serviceInvocationStrategy) {
        super();
        this.serviceBroadcaster = serviceBroadcaster;
        this.referenceMessageId = referenceMessageId;
        this.message = message;
        this.serviceInvocationStrategy = serviceInvocationStrategy;
    }

    
    @Override
    public ServiceInvocationResult call() throws IntegrationException {
        if (serviceBroadcaster == null) {
            throw new IntegrationException(IntegrationError._1062);
        }

        if (referenceMessageId < 1) {
            throw new IntegrationException(IntegrationError._1063);
        }

        if (StringUtils.isEmpty(message)) {
            throw new IntegrationException(IntegrationError._1064);
        }

        if (serviceInvocationStrategy == null) {
            throw new IntegrationException(IntegrationError._1065);
        }
        return serviceBroadcaster.delegateServiceInvocation(referenceMessageId, message, serviceInvocationStrategy);
    }

}
