package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.domain.StrategyIdentifier;

public interface ServiceInvocationStrategy {
	
	StrategyIdentifier getStrategyIdentifier();
	int getRetryCount();
	ServiceInvocationResult invoke(String message);
	ServiceInvocationResult rollback(String message);

}
