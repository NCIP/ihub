package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;

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
	public ServiceInvocationResult call() throws IntegrationException {
		
		
		if (StringUtils.isEmpty(message)) {
			throw new IntegrationException(IntegrationError._1064);
		}
		
		if (serviceInvocationStrategy == null) {
			throw new IntegrationException(IntegrationError._1065);
		}
		return serviceInvocationStrategy.rollback(message);
	}
		
}
