/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;

public interface ServiceInvocationStrategy {
	
	StrategyIdentifier getStrategyIdentifier();
	int getRetryCount();
	ServiceInvocationResult invoke(ServiceInvocationMessage serviceInvocationMessage);
	ServiceInvocationResult rollback(ServiceInvocationMessage serviceInvocationMessage);

}
