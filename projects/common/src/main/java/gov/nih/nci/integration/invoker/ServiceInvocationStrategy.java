package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;

public interface ServiceInvocationStrategy {

	StrategyIdentifier getStrategyIdentifier();

	int getRetryCount();

	ServiceInvocationResult invoke(
			ServiceInvocationMessage serviceInvocationMessage);

	ServiceInvocationResult rollback(
			ServiceInvocationMessage serviceInvocationMessage);

}
