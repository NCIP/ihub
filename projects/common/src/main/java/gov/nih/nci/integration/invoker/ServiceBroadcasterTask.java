package gov.nih.nci.integration.invoker;

import java.util.concurrent.Callable;

import org.apache.commons.lang.StringUtils;

public class ServiceBroadcasterTask implements Callable<ServiceInvocationResult> {
	
	private ServiceBroadcaster serviceBroadcaster;
	
	private Long referenceMessageId;
	
	private String message;
	
	private ServiceInvocationStrategy serviceInvocationStrategy;

	public ServiceBroadcasterTask(ServiceBroadcaster serviceBroadcaster,
			Long referenceMessageId, String message,
			ServiceInvocationStrategy serviceInvocationStrategy) {
		super();
		this.serviceBroadcaster = serviceBroadcaster;
		this.referenceMessageId = referenceMessageId;
		this.message = message;
		this.serviceInvocationStrategy = serviceInvocationStrategy;
	}

	@Override
	public ServiceInvocationResult call() throws Exception {
		if (serviceBroadcaster == null) {
			throw new IllegalArgumentException("Requires a ServiceBroadcaster!");
		}
		
		if (referenceMessageId < 1) {
			throw new IllegalArgumentException("Requires a valid referenceMessageId!");
		}		
		
		if (StringUtils.isEmpty(message)) {
			throw new IllegalArgumentException("Requires a non-empty message!");
		}
		
		if (serviceInvocationStrategy == null) {
			throw new IllegalArgumentException("Requires a ServiceInvocationStrategy!");
		}
		return serviceBroadcaster.delegateServiceInvocation(referenceMessageId, message, serviceInvocationStrategy);
	}
		
}
