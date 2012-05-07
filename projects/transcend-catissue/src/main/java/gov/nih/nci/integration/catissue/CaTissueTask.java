package gov.nih.nci.integration.catissue;

import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
			String message) throws IntegrationException  {
		super();
		try {
			Constructor constructor = caTissueParticipantClientClass
					.getDeclaredConstructor(String.class, String.class);
			this.caTissueClientInstance = constructor.newInstance(loginName,
					password);
			methodToInvoke = caTissueParticipantClientClass.getDeclaredMethod(
					methodName, String.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new IntegrationException(IntegrationError._1052, e.getMessage());
		}

		this.message = message;
	}

	@Override
	public ServiceInvocationResult call() {
		ServiceInvocationResult result = new ServiceInvocationResult();
		try {
			Object retValue = methodToInvoke.invoke(caTissueClientInstance, message);
			result.setDataChanged(true);
			result.setOriginalData(retValue);
		} catch (InvocationTargetException e) {
			//must not reach here, exceptions must be handled inside client
			IntegrationException ie = new IntegrationException(
					IntegrationError._1051, e.getTargetException().getCause(), e.getTargetException().getMessage());
			result.setInvocationException(ie);
		} catch (Exception e) {
			//must not reach here, exceptions must be handled inside client
			IntegrationException ie = new IntegrationException(
					IntegrationError._1051, e.getCause(), e.getMessage());
			result.setInvocationException(ie);
		}

		return result;
	}
}