package gov.nih.nci.integration.caaers.invoker;

import gov.nih.nci.integration.invoker.ServiceInvocationStrategy;
import gov.nih.nci.integration.util.CustomClasspathXmlApplicationContext;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

public class CaAERSServiceInvocationStrategyFactory {

	public static ServiceInvocationStrategy createCaAERSServiceInvocationStrategy(
			final String caaersLibLocation, final String caaersConfig) {
		
		ExecutorCompletionService<ServiceInvocationStrategy> ecs = new ExecutorCompletionService<ServiceInvocationStrategy>(
				Executors.newSingleThreadExecutor());

		ecs.submit(new Callable<ServiceInvocationStrategy>() {

			@Override
			public ServiceInvocationStrategy call() throws Exception {
				CustomClasspathXmlApplicationContext ctx = new CustomClasspathXmlApplicationContext(
						caaersLibLocation, caaersConfig);
				System.out.println("context classloader = "
						+ Thread.currentThread().getContextClassLoader());
				System.out.println(ctx.getBeanDefinitionCount());
				System.out.println(Arrays.asList(ctx.getBeanDefinitionNames()));
				return (ServiceInvocationStrategy) ctx
						.getBean("caAersRegistrationServiceInvocationStrategy");
			}

		});
		
		ServiceInvocationStrategy serviceInvocationStrategy = null;
		try {
			serviceInvocationStrategy = ecs.take().get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		return serviceInvocationStrategy;
	}
}
