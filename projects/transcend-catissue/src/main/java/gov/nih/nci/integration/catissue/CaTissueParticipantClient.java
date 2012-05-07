package gov.nih.nci.integration.catissue;

import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;
import gov.nih.nci.integration.util.CustomUrlClassLoader;

import java.io.File;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

/**
 * 
 * @author chandrasekaravr
 * 
 */
public class CaTissueParticipantClient {
	
	private static String CLIENT_CLASSNM = "gov.nih.nci.integration.catissue.client.CaTissueParticipantClient";
	private Class caTissueClientClass = null;

	private String caTissueLibLocation = "";
	private String loginName = null;
	private String password = null;
	
	private static Class[] registerMethodParamTypes = {String.class};
	private static Class[] updateRegistrationMethodParamTypes = {String.class};
	private static Class[] deleteMethodParamTypes = {String.class};

	private Executor ex = Executors.newCachedThreadPool();

	public CaTissueParticipantClient(String caTissueLibLocation,
			String loginName, String password) throws IntegrationException {
		super();
		this.caTissueLibLocation = caTissueLibLocation;
		this.loginName = loginName;
		this.password = password;

		init();
	}

	private void init() throws IntegrationException {
		try {
			File libFile = new File(caTissueLibLocation);
			
			System.out.println(caTissueLibLocation);
			
			// creating the custom classloader that bypasses the
			// systemclassloader
			CustomUrlClassLoader ccl = new CustomUrlClassLoader(ClassLoader
					.getSystemClassLoader().getParent(), 
					libFile.getAbsolutePath());			
			caTissueClientClass = ccl.loadClass(CLIENT_CLASSNM);
			
			// CHECKSTYLE:OFF
		} catch (Exception e) { // NOPMD
			e.printStackTrace();
			throw new IntegrationException(IntegrationError._1052, e.getMessage());
		}
		// CHECKSTYLE:ON
	}

	public ServiceInvocationResult registerParticipant(
			final String participantXMLStr) {
		ServiceInvocationResult result = null;
		
		CaTissueTask task = null;
		try {
			task = new CaTissueTask(caTissueClientClass, loginName, password,
					"registerParticipantFromXML", registerMethodParamTypes, participantXMLStr);
		} catch (Exception e1) {			
			e1.printStackTrace();
			result = getServiceInvocationResult(IntegrationError._1051, e1);
			return result;
		}
		
		try {
			ExecutorCompletionService<ServiceInvocationResult> ecs = 
				new ExecutorCompletionService<ServiceInvocationResult>(ex);
			ecs.submit(task);

			result = ecs.take().get();
			
			if (!result.isFault()) {
				result.setResult("Successfully registered participant in CaTissue!");
			} else {
			
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			result = getServiceInvocationResult(IntegrationError._1051, e);
		} catch (ExecutionException e) {
			e.printStackTrace();
			result = getServiceInvocationResult(IntegrationError._1051, e);
		}

		return result;
	}
	
	public ServiceInvocationResult updateRegistrationParticipant(
			final String participantXMLStr) {
		ServiceInvocationResult result = null;
		
		CaTissueTask task = null;
		try {
			task = new CaTissueTask(caTissueClientClass, loginName, password,
					"updateParticipantRegistrationFromXML", updateRegistrationMethodParamTypes,
					participantXMLStr);
		} catch (Exception e1) {			
			e1.printStackTrace();
			result = getServiceInvocationResult(IntegrationError._1051, e1);
			return result;
		}
		
		try {
			ExecutorCompletionService<ServiceInvocationResult> ecs = 
				new ExecutorCompletionService<ServiceInvocationResult>(ex);
			ecs.submit(task);

			result = ecs.take().get();
			
			if (!result.isFault()) {
				result.setResult("Successfully update participant registration in CaTissue!");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			result = getServiceInvocationResult(IntegrationError._1051, e);
		} catch (ExecutionException e) {
			e.printStackTrace();
			result = getServiceInvocationResult(IntegrationError._1051, e);
		}

		return result;
	}

	public ServiceInvocationResult deleteParticipant(
			final String participantXMLStr) {
		ServiceInvocationResult result = null;
		
		CaTissueTask task = null;
		try {
			task = new CaTissueTask(caTissueClientClass, loginName, password,
					"deleteParticipantFromXML", deleteMethodParamTypes, participantXMLStr);
		} catch (Exception e1) {			
			e1.printStackTrace();
			result = getServiceInvocationResult(IntegrationError._1051, e1);
			return result;
		}
		
		try {
			ExecutorCompletionService<ServiceInvocationResult> ecs = 
				new ExecutorCompletionService<ServiceInvocationResult>(ex);
			ecs.submit(task);

			result = ecs.take().get();
			
			if (!result.isFault()) {
				result.setResult("Successfully rollabck participant from CaTissue!");
			}
		} catch (InterruptedException e) {
			result = getServiceInvocationResult(IntegrationError._1051, e);
		} catch (ExecutionException e) {
			result = getServiceInvocationResult(IntegrationError._1051, e);
		}

		return result;
	}

	private ServiceInvocationResult getServiceInvocationResult(
			IntegrationError error, Exception e) {
		ServiceInvocationResult result = new ServiceInvocationResult();
		if(e instanceof IntegrationException){
			result.setInvocationException(e);
		} else {
			result.setInvocationException(new IntegrationException(error, e, e.getMessage()));
		}
		return result;
	}

}
