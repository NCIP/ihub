package gov.nih.nci.integration.caaers.invoker;

import gov.nih.nci.integration.invoker.ServiceInvocationStrategy;
import gov.nih.nci.integration.util.CustomClasspathXmlApplicationContext;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

/**
 * Factory class for Strategy for caAERS
 * @author Vinodh
 *
 */
public final class CaAERSServiceInvocationStrategyFactory {

    private static ServiceInvocationStrategy caaersRegistrationServiceInvocationStrategy = null;

    private static ServiceInvocationStrategy caaersUpdateRegistrationServiceInvocationStrategy = null;

    private static Boolean initStatus = null;
    
    private CaAERSServiceInvocationStrategyFactory() {        
    }

    private static synchronized void init(final String[] caaersLibLocation, final String... caaersConfig) {
        ExecutorCompletionService<Boolean> ecs = new ExecutorCompletionService<Boolean>(Executors
                .newSingleThreadExecutor());

        ecs.submit(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
                CustomClasspathXmlApplicationContext ctx = new CustomClasspathXmlApplicationContext(caaersLibLocation,
                        caaersConfig);
                caaersRegistrationServiceInvocationStrategy = (ServiceInvocationStrategy) ctx
                        .getBean("caAersRegistrationServiceInvocationStrategy");
                caaersUpdateRegistrationServiceInvocationStrategy = (ServiceInvocationStrategy) ctx
                        .getBean("caAersUpdateRegistrationServiceInvocationStrategy");

                return Boolean.TRUE;
            }

        });

        try {
            initStatus = ecs.take().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
            initStatus = Boolean.FALSE;
        } catch (ExecutionException e) {
            e.printStackTrace();
            initStatus = Boolean.FALSE;
        }
    }

    /**
     * Method to get caaersRegistrationServiceInvocationStrategy
     * @param caaersLibLocation - caaersLibLocation
     * @param caaersConfig - caaersConfig
     * @return caaersRegistrationServiceInvocationStrategy
     */
    // CHECKSTYLE:OFF
    public static ServiceInvocationStrategy createCaAERSRegistrationServiceInvocationStrategy(
            final String caaersLibLocation[] , final String... caaersConfig) {

        if (initStatus == null && caaersRegistrationServiceInvocationStrategy == null) {
            init(caaersLibLocation, caaersConfig);
        }
        if (initStatus) {
            return caaersRegistrationServiceInvocationStrategy;
        } else {
            return null;
        }
    }

    /**
     * Method to get caaersUpdateRegistrationServiceInvocationStrategy
     * @param caaersLibLocation - caaersLibLocation
     * @param caaersConfig - caaersConfig
     * @return caaersUpdateRegistrationServiceInvocationStrategy
     */
    public static ServiceInvocationStrategy createCaAERSUpdateRegistrationServiceInvocationStrategy(
            final String caaersLibLocation[], final String... caaersConfig) {

        if (initStatus == null && caaersUpdateRegistrationServiceInvocationStrategy == null) {
            init(caaersLibLocation, caaersConfig);
        }
        if (initStatus) {
            return caaersUpdateRegistrationServiceInvocationStrategy;
        } else {
            return null;
        }
    }
}
