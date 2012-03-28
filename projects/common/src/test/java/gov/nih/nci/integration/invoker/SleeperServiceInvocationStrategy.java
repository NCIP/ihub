package gov.nih.nci.integration.invoker;

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
	public ServiceInvocationResult invoke(String message) {
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
		}
		
		return result;
	}

	@Override
	public ServiceInvocationResult rollback(String message) {
		ServiceInvocationResult rlbkRes = new ServiceInvocationResult();
		rlbkRes.setResult("Successfully rollback");		
		return rlbkRes; 
	}

	public ServiceInvocationResult getResult() {
		return result;
	}

}
