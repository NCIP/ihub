/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.invoker;



public interface ServiceInvocatorAndResultAggregator {

	void invokeService(Long referenceMessageId, String message,
			ServiceInvocationStrategy serviceInvocationStrategy);

	ServiceInvocationResult aggregateResults(Long referenceMessageId);
}
