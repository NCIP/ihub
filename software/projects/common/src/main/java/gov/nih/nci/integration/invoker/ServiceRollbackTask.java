/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;

import java.util.concurrent.Callable;

public class ServiceRollbackTask implements Callable<ServiceInvocationResult> {
			
	private ServiceInvocationMessage message;
	
	private ServiceInvocationStrategy serviceInvocationStrategy;

	public ServiceRollbackTask(ServiceInvocationMessage message,
			ServiceInvocationStrategy serviceInvocationStrategy) {
		super();
		this.message = message;
		this.serviceInvocationStrategy = serviceInvocationStrategy;
	}

	@Override
	public ServiceInvocationResult call() throws IntegrationException {
		
		if (message == null) {
			throw new IntegrationException(IntegrationError._1064);
		}
		
		if (serviceInvocationStrategy == null) {
			throw new IntegrationException(IntegrationError._1065);
		}
		return serviceInvocationStrategy.rollback(message);
	}
		
}
