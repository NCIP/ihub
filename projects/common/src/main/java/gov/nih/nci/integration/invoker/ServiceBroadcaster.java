package gov.nih.nci.integration.invoker;

public interface ServiceBroadcaster {

	ServiceInvocationResult delegateServiceInvocation(Long referenceMessageId,
			String message, ServiceInvocationStrategy serviceInvocationStrategy);
}
