package gov.nih.nci.integration.caaers.invoker;

import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;
import gov.nih.nci.integration.invoker.ServiceInvocationStrategy;

/**
 * 
 * @author chandrasekaravr
 *
 */
public class CaAERSRegistrationInvocationStrategy implements
		ServiceInvocationStrategy {

	
	@Override
	public int getRetryCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public StrategyIdentifier getStrategyIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceInvocationResult invoke(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServiceInvocationResult rollback(String arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
