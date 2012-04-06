package gov.nih.nci.integration.catissue;

import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * 
 * @author chandrasekaravr
 *
 */
public class CaTissueTask implements Callable<ServiceInvocationResult> {
	public Object caTissueClientInstance;
	public Method methodToInvoke;
	public String message;

	public CaTissueTask(Object caTissueClientInstance, Method methodToInvoke, String message) {
		super();
		this.caTissueClientInstance = caTissueClientInstance;
		this.methodToInvoke = methodToInvoke;
		this.message = message;
	}

	@Override
	public ServiceInvocationResult call() {
		ServiceInvocationResult result = new ServiceInvocationResult();		
		try {
			methodToInvoke.invoke(caTissueClientInstance, message);			
		} catch (Exception e) {
			//TODo : change to _1051
			IntegrationException ie = new IntegrationException(IntegrationError._1000, e.getCause());
			result.setInvocationException(ie);
		} 
		
		return result;
	}
}