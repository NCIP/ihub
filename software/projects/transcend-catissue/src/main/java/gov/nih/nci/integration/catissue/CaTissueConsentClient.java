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
 * This is the Wrapper client class for RegisterConsent client class.
 * 
 * @author Rohit Gupta
 */
public class CaTissueConsentClient {

	private static String CLIENT_CLASSNAME = "gov.nih.nci.integration.catissue.client.CaTissueConsentClient";
	private Class caTissueConsentClientClass = null;

	private String caTissueLibLocation = "";
	private String loginName = null;
	private String password = null;

	private static Class[] registerConsentsParamTypes = { String.class };
	private static Class[] rollbackConsentParamTypes = { String.class };

	private Executor ex = Executors.newCachedThreadPool();

	public CaTissueConsentClient(String caTissueLibLocation, String loginName,
			String password) throws IntegrationException {
		super();
		this.caTissueLibLocation = caTissueLibLocation;
		this.loginName = loginName;
		this.password = password;

		init();
	}

	private void init() throws IntegrationException {
		try {
			File libFile = new File(caTissueLibLocation);

			// creating the custom classloader that bypasses the
			// systemclassloader
			CustomUrlClassLoader ccl = new CustomUrlClassLoader(ClassLoader
					.getSystemClassLoader().getParent(), libFile
					.getAbsolutePath());

			caTissueConsentClientClass = ccl.loadClass(CLIENT_CLASSNAME);

		} catch (Exception e) {
			e.printStackTrace();
			throw new IntegrationException(IntegrationError._1000, e);
		}

	}

	public ServiceInvocationResult registerConsents(
			final String consentListXMLStr) {
		ServiceInvocationResult result1, result2, finalResult = null;
		CaTissueTask task1, task2 = null;

		// First get the existing Specimen/Consent data - which will be needed
		// incase of Rollback
		try {
			task1 = new CaTissueTask(caTissueConsentClientClass, loginName,
					password, "getExistingConsents",
					registerConsentsParamTypes, consentListXMLStr);
		} catch (Exception e1) {
			result1 = getServiceInvocationResult(IntegrationError._1051, e1);
			return result1;
		}

		try {
			ExecutorCompletionService<ServiceInvocationResult> ecs = new ExecutorCompletionService<ServiceInvocationResult>(
					ex);
			ecs.submit(task1);

			result1 = ecs.take().get();

			if (!result1.isFault()) {
				result1.setResult("Successfully Fetched Consent in CaTissue!");
			} else {

			}
		} catch (InterruptedException e) {
			result1 = getServiceInvocationResult(IntegrationError._1051, e);
		} catch (ExecutionException e) {
			result1 = getServiceInvocationResult(IntegrationError._1051, e);
		}

		if (result1.getInvocationException() != null) {
			// return if some issue while fetching the data itself.
			return result1;
		}

		// now perform registerConsent operation
		try {
			task2 = new CaTissueTask(caTissueConsentClientClass, loginName,
					password, "registerConsents", registerConsentsParamTypes,
					consentListXMLStr);
		} catch (Exception e1) {
			result2 = getServiceInvocationResult(IntegrationError._1051, e1);
			return result2;
		}

		try {
			ExecutorCompletionService<ServiceInvocationResult> ecs = new ExecutorCompletionService<ServiceInvocationResult>(
					ex);
			ecs.submit(task2);
			result2 = ecs.take().get();
			if (!result2.isFault()) {
				result2
						.setResult("Successfully Registered Consent in CaTissue!");
			} else {

			}
		} catch (InterruptedException e) {
			result2 = getServiceInvocationResult(IntegrationError._1051, e);
		} catch (ExecutionException e) {
			result2 = getServiceInvocationResult(IntegrationError._1051, e);
		}

		// Merge result1 & result2 to get the final result
		if (result2.getInvocationException() != null) {
			finalResult = new ServiceInvocationResult();
			finalResult.setDataChanged(true);
			finalResult.setOriginalData(result1.getOriginalData());
			finalResult
					.setInvocationException(result2.getInvocationException());
		} else {
			finalResult = result2;
		}

		return finalResult;
	}

	public ServiceInvocationResult rollbackConsents(
			final String consentListXMLStr) {
		ServiceInvocationResult result = null;
		CaTissueTask task = null;
		try {
			task = new CaTissueTask(caTissueConsentClientClass, loginName,
					password, "rollbackConsentRegistration",
					rollbackConsentParamTypes, consentListXMLStr);
		} catch (Exception e1) {
			e1.printStackTrace();
			result = getServiceInvocationResult(IntegrationError._1051, e1);
			return result;
		}

		try {
			ExecutorCompletionService<ServiceInvocationResult> ecs = new ExecutorCompletionService<ServiceInvocationResult>(
					ex);
			ecs.submit(task);
			result = ecs.take().get();
			if (!result.isFault()) {
				result
						.setResult("Successfully rollback Consents from CaTissue!");
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
		result.setInvocationException(new IntegrationException(error, e));
		return result;
	}

}
