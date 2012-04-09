package gov.nih.nci.integration.catissue;

import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;

import java.lang.reflect.Constructor;
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

	public CaTissueTask(Class caTissueParticipantClientClass, String loginName,
			String password, String methodName, Class[] paramClasses,
			String message) throws Exception {
		super();
		Constructor constructor = caTissueParticipantClientClass
				.getDeclaredConstructor(String.class, String.class);
		this.caTissueClientInstance = constructor.newInstance(loginName,
				password);
		methodToInvoke = caTissueParticipantClientClass.getDeclaredMethod(
				methodName, String.class);

		this.message = message;
	}

	@Override
	public ServiceInvocationResult call() {
		ServiceInvocationResult result = new ServiceInvocationResult();
		try {
			methodToInvoke.invoke(caTissueClientInstance, message);
		} catch (Exception e) {
			e.printStackTrace();
			IntegrationException ie = new IntegrationException(
					IntegrationError._1051, e.getCause());
			result.setInvocationException(ie);
		}

		return result;
	}
}