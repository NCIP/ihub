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
 * @author Rohit Gupta
 * 
 */
public class CaTissueSpecimenClient {
	
	private static String CLIENT_CLASSNAME = "gov.nih.nci.integration.catissue.client.CaTissueSpecimenClient";
	private Class caTissueSpecimenClientClass = null;

	private String caTissueLibLocation = "";
	private String loginName = null;
	private String password = null;
	
	private static Class[] createSpecimensParamTypes = {String.class};
	private static Class[] updateSpecimensParamTypes = {String.class};
	private static Class[] rollbackSpecimensParamTypes = {String.class};

	private Executor ex = Executors.newCachedThreadPool();

	public CaTissueSpecimenClient(String caTissueLibLocation,
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
			
			// creating the custom classloader that bypasses the systemclassloader
			CustomUrlClassLoader ccl = new CustomUrlClassLoader(ClassLoader.getSystemClassLoader().getParent(), libFile.getAbsolutePath());		
			
			caTissueSpecimenClientClass = ccl.loadClass(CLIENT_CLASSNAME);			
			
		} catch (Exception e) { 
			e.printStackTrace();
			throw new IntegrationException(IntegrationError._1000, e);
		}
		
	}

	public ServiceInvocationResult createSpecimens(final String specimenListXMLStr)  {
		ServiceInvocationResult result = null;
		
		CaTissueTask task = null;
		try {
			task = new CaTissueTask(caTissueSpecimenClientClass, loginName, password,
					"createSpecimens", createSpecimensParamTypes, specimenListXMLStr);
		} catch (Exception e1) {			
			System.out.println("Catch Inside CaTissueSpecimenClient.createSpecimens()..Inside Catch(exception) Block... ");
			result = getServiceInvocationResult(IntegrationError._1051, e1);
			return result;
		}
		
		try {
			ExecutorCompletionService<ServiceInvocationResult> ecs = new ExecutorCompletionService<ServiceInvocationResult>(ex);
			ecs.submit(task);
	
			result = ecs.take().get();
		
			if (!result.isFault()) {
				result.setResult("Successfully created Specimens in CaTissue!");
			} else {
				
			}
		} catch (InterruptedException e) {
			result = getServiceInvocationResult(IntegrationError._1051, e);
		} catch (ExecutionException e) {
			result = getServiceInvocationResult(IntegrationError._1051, e);
		}

		return result;
	}
	
	
	

	public ServiceInvocationResult updateSpecimens(final String specimenListXMLStr) {
		ServiceInvocationResult result = null;
		
		CaTissueTask task = null;
		try {
			task = new CaTissueTask(caTissueSpecimenClientClass, loginName, password,
					"updateSpecimens", updateSpecimensParamTypes, specimenListXMLStr);
		} catch (Exception e1) {			
			e1.printStackTrace();
			result = getServiceInvocationResult(IntegrationError._1051, e1);
			return result;
		}
		
		try {
			ExecutorCompletionService<ServiceInvocationResult> ecs = new ExecutorCompletionService<ServiceInvocationResult>(ex);
			ecs.submit(task);

			result = ecs.take().get();
			
			if (!result.isFault()) {
				result.setResult("Successfully updated Specimens in CaTissue!");
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
	
	
	public ServiceInvocationResult rollbackSpecimens(final String specimenListXMLStr) {
		ServiceInvocationResult result = null;
		
		CaTissueTask task = null;
		try {
			task = new CaTissueTask(caTissueSpecimenClientClass, loginName, password,
					"rollbackSpecimens", rollbackSpecimensParamTypes, specimenListXMLStr);
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
				result.setResult("Successfully rollback Specimens from CaTissue!");
			}
		} catch (InterruptedException e) {			
			result = getServiceInvocationResult(IntegrationError._1051, e);
		} catch (ExecutionException e) {
			result = getServiceInvocationResult(IntegrationError._1051, e);
		}

		return result;
	}

	
	private ServiceInvocationResult getServiceInvocationResult(IntegrationError error, Exception e) {
		ServiceInvocationResult result = new ServiceInvocationResult();
		result.setInvocationException(new IntegrationException(error, e));
		return result;
	}

}
