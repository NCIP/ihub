package gov.nih.nci.integration.catissue;

import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;
import gov.nih.nci.integration.util.CustomUrlClassLoader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
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

	private Method registerParticipantMethod = null;
	private Method deleteParticipantMethod = null;
	// private Method updateParticipantMethod = null;

	private Object caTissueClientInstance = null;

	private String caTissueLibLocation = "";
	private String loginName = null;
	private String password = null;

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

			// creating the custom classloader that bypasses the
			// systemclassloader
			CustomUrlClassLoader ccl = new CustomUrlClassLoader(ClassLoader
					.getSystemClassLoader().getParent(), libFile
					.getAbsolutePath());

			String intfNm = "gov.nih.nci.integration.catissue.CaTissueParticipantClient";
			Class clientClass = ccl.loadClass(intfNm);

			Constructor constructor = clientClass.getDeclaredConstructor(
					String.class, String.class);

			caTissueClientInstance = constructor.newInstance(loginName,
					password);

			registerParticipantMethod = clientClass.getDeclaredMethod(
					"registerParticipantFromXML", String.class);
			deleteParticipantMethod = clientClass.getDeclaredMethod(
					"deleteParticipantFromXML", String.class);
			// TODO : uncomment once the method is implemented
			// updateParticipantMethod =
			// clientClass.getDeclaredMethod("updateParticipantFromXML",
			// String.class);

			// CHECKSTYLE:OFF
		} catch (Exception e) { // NOPMD
			throw new IntegrationException(IntegrationError._1000, e);
		}
		// CHECKSTYLE:ON
	}

	public ServiceInvocationResult registerParticipant(
			final String participantXMLStr) {
		ServiceInvocationResult result = null;

		try {
			ExecutorCompletionService<ServiceInvocationResult> ecs = 
				new ExecutorCompletionService<ServiceInvocationResult>(ex);
			ecs.submit(new CaTissueTask(caTissueClientInstance,
					registerParticipantMethod, participantXMLStr));

			result = ecs.take().get();
			
			if (!result.isFault()) {
				result.setResult("Successfully registered participant in CaTissue!");
			}
		} catch (InterruptedException e) {
			result = getServiceInvocationResult(IntegrationError._1051, e);
		} catch (ExecutionException e) {
			result = getServiceInvocationResult(IntegrationError._1051, e);
		}

		return result;
	}

	public ServiceInvocationResult deleteParticipant(
			final String participantXMLStr) {
		ServiceInvocationResult result = null;

		try {
			ExecutorCompletionService<ServiceInvocationResult> ecs = 
				new ExecutorCompletionService<ServiceInvocationResult>(ex);
			ecs.submit(new CaTissueTask(caTissueClientInstance,
					deleteParticipantMethod, participantXMLStr));

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
		result.setInvocationException(new IntegrationException(error, e));
		return result;
	}

}
