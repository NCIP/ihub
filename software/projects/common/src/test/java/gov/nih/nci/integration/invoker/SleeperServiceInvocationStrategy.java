/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;

public class SleeperServiceInvocationStrategy implements
		ServiceInvocationStrategy {
	
	private long sleeptime;
	
	private boolean makefault;
	
	private ServiceInvocationResult result;
		
	public SleeperServiceInvocationStrategy(long sleeptime, boolean makefault) {
		super();
		this.sleeptime = sleeptime;
		this.makefault = makefault;
	}

	@Override
	public int getRetryCount() {
		return 3;
	}

	@Override
	public StrategyIdentifier getStrategyIdentifier() {
		return StrategyIdentifier.CAEERS_CREATE_REGISTRATION;
	}

	@Override
	public ServiceInvocationResult invoke(ServiceInvocationMessage message) {
		try {
			Thread.sleep(sleeptime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		result = new ServiceInvocationResult();
		if (makefault) {
			result.setInvocationException(new RuntimeException("Sleeper service throws exception"));
		} else {
			result.setResult("Successfully waited for " + sleeptime + " ms.");
			result.setDataChanged(true);
		}
		
		return result;
	}

	@Override
	public ServiceInvocationResult rollback(ServiceInvocationMessage message) {
		ServiceInvocationResult rlbkRes = new ServiceInvocationResult();
		rlbkRes.setResult("Successfully rollback");		
		return rlbkRes; 
	}

	public ServiceInvocationResult getResult() {
		return result;
	}

}
