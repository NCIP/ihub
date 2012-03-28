package gov.nih.nci.integration.invoker;

import java.util.concurrent.Callable;

import org.apache.commons.lang.StringUtils;

public class ServiceRollbackTask implements Callable<ServiceInvocationResult> {
			
	private String message;
	
	private ServiceInvocationStrategy serviceInvocationStrategy;

	public ServiceRollbackTask(String message,
			ServiceInvocationStrategy serviceInvocationStrategy) {
		super();
		this.message = message;
		this.serviceInvocationStrategy = serviceInvocationStrategy;
	}

	@Override
	public ServiceInvocationResult call() throws Exception {
		
		
		if (StringUtils.isEmpty(message)) {
			throw new IllegalArgumentException("Requires a non-empty message!");
		}
		
		if (serviceInvocationStrategy == null) {
			throw new IllegalArgumentException("Requires a ServiceInvocationStrategy!");
		}
		return serviceInvocationStrategy.rollback(message);
	}
		
}
