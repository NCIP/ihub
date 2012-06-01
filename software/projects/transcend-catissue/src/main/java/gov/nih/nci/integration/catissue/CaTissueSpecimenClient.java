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

	private static Class[] createSpecimensParamTypes = { String.class };
	private static Class[] updateSpecimensParamTypes = { String.class };
	private static Class[] rollbackSpecimensParamTypes = { String.class };

	private Executor ex = Executors.newCachedThreadPool();

	public CaTissueSpecimenClient(String caTissueLibLocation, String loginName,
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

			// System.out.println(caTissueLibLocation);

			// creating the custom classloader that bypasses the
			// systemclassloader
			CustomUrlClassLoader ccl = new CustomUrlClassLoader(ClassLoader
					.getSystemClassLoader().getParent(), libFile
					.getAbsolutePath());

			caTissueSpecimenClientClass = ccl.loadClass(CLIENT_CLASSNAME);

		} catch (Exception e) {
			e.printStackTrace();
			throw new IntegrationException(IntegrationError._1000, e);
		}

	}

	public ServiceInvocationResult createSpecimens(
			final String specimenListXMLStr) {
		ServiceInvocationResult result1, result2 = null;

		CaTissueTask task1, task2 = null;
		try {
			task1 = new CaTissueTask(caTissueSpecimenClientClass, loginName,
					password, "isSpecimensExist", createSpecimensParamTypes,
					specimenListXMLStr);
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
				result1.setResult("Successfully called isSpecimensExist !");
			} else {

			}
		} catch (InterruptedException e) {
			result1 = getServiceInvocationResult(IntegrationError._1051, e);
		} catch (ExecutionException e) {
			result1 = getServiceInvocationResult(IntegrationError._1051, e);
		}

		if (result1.getInvocationException() != null) {
			// We don't want to call the Rollback if a Specimen is already
			// existing. 'isDataChanged' should also be false
			// Also no need to call createSpecimen flow. We are throwing
			// ApplicationException from client code if Specimen is already
			// existing
			return result1;
		}

		// It means that Specimen is not existing so try to execute below code..
		try {
			task2 = new CaTissueTask(caTissueSpecimenClientClass, loginName,
					password, "createSpecimens", createSpecimensParamTypes,
					specimenListXMLStr);
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
						.setResult("Successfully created Specimens in CaTissue!");
			} else {

			}
		} catch (InterruptedException e) {
			result2 = getServiceInvocationResult(IntegrationError._1051, e);
		} catch (ExecutionException e) {
			result2 = getServiceInvocationResult(IntegrationError._1051, e);
		}

		if (result2.getInvocationException() != null) {
			// It means that the Specimens were NOT present at the starting when
			// we checked before calling createSpecimen,
			// but then Specimen might be created as we have the list of
			// specimen and exception is for one of them.
			// So we have to call Rollback to make sure that the create specimen
			// are rollback
			result2.setDataChanged(true);
		}
		return result2;
	}

	public ServiceInvocationResult rollbackCreatedSpecimens(
			final String specimenListXMLStr) {
		ServiceInvocationResult result = null;
		CaTissueTask task = null;
		try {
			task = new CaTissueTask(caTissueSpecimenClientClass, loginName,
					password, "rollbackCreatedSpecimens",
					rollbackSpecimensParamTypes, specimenListXMLStr);
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
						.setResult("Successfully rollback Specimens from CaTissue!");
			}
		} catch (InterruptedException e) {
			result = getServiceInvocationResult(IntegrationError._1051, e);
		} catch (ExecutionException e) {
			result = getServiceInvocationResult(IntegrationError._1051, e);
		}

		return result;
	}

	public ServiceInvocationResult updateSpecimens(
			final String specimenListXMLStr) {
		ServiceInvocationResult result1, result2, finalResult = null;

		CaTissueTask task1, task2 = null;
		try {
			task1 = new CaTissueTask(caTissueSpecimenClientClass, loginName,
					password, "getExistingSpecimens",
					updateSpecimensParamTypes, specimenListXMLStr);
		} catch (Exception e1) {
			e1.printStackTrace();
			result1 = getServiceInvocationResult(IntegrationError._1051, e1);
			return result1;
		}

		try {
			ExecutorCompletionService<ServiceInvocationResult> ecs = new ExecutorCompletionService<ServiceInvocationResult>(
					ex);
			ecs.submit(task1);

			result1 = ecs.take().get();

			if (!result1.isFault()) {
				result1
						.setResult("Successfully fetched Specimens from CaTissue!");
			} else {

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			result1 = getServiceInvocationResult(IntegrationError._1051, e);
		} catch (ExecutionException e) {
			e.printStackTrace();
			result1 = getServiceInvocationResult(IntegrationError._1051, e);
		}

		if (result1.getInvocationException() != null) {
			// If some exception while fetching the data, don't try for
			// updation.
			return result1;
		}

		try {
			task2 = new CaTissueTask(caTissueSpecimenClientClass, loginName,
					password, "updateSpecimens", updateSpecimensParamTypes,
					specimenListXMLStr);
		} catch (Exception e1) {
			e1.printStackTrace();
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
						.setResult("Successfully Updated Specimens from CaTissue!");
			} else {

			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			result2 = getServiceInvocationResult(IntegrationError._1051, e);
		} catch (ExecutionException e) {
			e.printStackTrace();
			result2 = getServiceInvocationResult(IntegrationError._1051, e);
		}

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

	public ServiceInvocationResult rollbackUpdatedSpecimens(
			final String specimenListXMLStr) {
		ServiceInvocationResult result = null;
		CaTissueTask task = null;
		try {
			task = new CaTissueTask(caTissueSpecimenClientClass, loginName,
					password, "rollbackUpdatedSpecimens",
					rollbackSpecimensParamTypes, specimenListXMLStr);
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
						.setResult("Successfully rollback Specimens from CaTissue!");
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
