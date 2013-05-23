/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.caaers.invoker;

import gov.nih.nci.integration.invoker.ServiceInvocationStrategy;
import gov.nih.nci.integration.util.CustomClasspathXmlApplicationContext;

import java.net.MalformedURLException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

/**
 * Factory class for Strategy for caAERS
 * 
 * @author Vinodh
 * 
 */
public final class CaAERSServiceInvocationStrategyFactory {

    private static ServiceInvocationStrategy caaersRegistrationServiceInvocationStrategy = null;

    private static ServiceInvocationStrategy caaersUpdateRegistrationServiceInvocationStrategy = null;

    private static ServiceInvocationStrategy caaersAdverseEventServiceInvocationStrategy = null;

    private static Boolean initStatus = null;

    /**
     * 
     * @param initStatus - initStatus
     */
    public static void setInitStatus(Boolean initStatus) {
        CaAERSServiceInvocationStrategyFactory.initStatus = initStatus;
    }

    private static final Logger LOG = LoggerFactory.getLogger(CaAERSServiceInvocationStrategyFactory.class);

    private CaAERSServiceInvocationStrategyFactory() {
    }

    private static synchronized void init(final String[] caaersLibLocation, final String... caaersConfig) {
        final ExecutorCompletionService<Boolean> ecs = new ExecutorCompletionService<Boolean>(
                Executors.newSingleThreadExecutor());

        ecs.submit(new Callable<Boolean>() {

            @Override
            public Boolean call() throws MalformedURLException, BeansException {
                final CustomClasspathXmlApplicationContext ctx = new CustomClasspathXmlApplicationContext(
                        caaersLibLocation, caaersConfig);
                caaersRegistrationServiceInvocationStrategy = (ServiceInvocationStrategy) ctx
                        .getBean("caAersRegistrationServiceInvocationStrategy");
                caaersUpdateRegistrationServiceInvocationStrategy = (ServiceInvocationStrategy) ctx
                        .getBean("caAersUpdateRegistrationServiceInvocationStrategy");
                caaersAdverseEventServiceInvocationStrategy = (ServiceInvocationStrategy) ctx
                        .getBean("caAersAdverseEventServiceInvocationStrategy");
                return Boolean.TRUE;
            }
        });

        try {
            initStatus = ecs.take().get();
            // CHECKSTYLE:OFF
        } catch (Exception e) { // NOPMD
            LOG.error("CaAERSServiceInvocationStrategyFactory.Exception inside init(). ", e);
            initStatus = Boolean.FALSE;
        }
    }

    /**
     * Method to get caaersRegistrationServiceInvocationStrategy
     * 
     * @param caaersLibLocation - caaersLibLocation
     * @param caaersConfig - caaersConfig
     * @return caaersRegistrationServiceInvocationStrategy
     */
    public static ServiceInvocationStrategy createCaAERSRegistrationServiceInvocationStrategy(
            final String[] caaersLibLocation, final String... caaersConfig) {

        if (initStatus == null || caaersRegistrationServiceInvocationStrategy == null) {
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
     * 
     * @param caaersLibLocation - caaersLibLocation
     * @param caaersConfig - caaersConfig
     * @return caaersUpdateRegistrationServiceInvocationStrategy
     */
    public static ServiceInvocationStrategy createCaAERSUpdateRegistrationServiceInvocationStrategy(
            final String[] caaersLibLocation, final String... caaersConfig) {

        if (initStatus == null || caaersUpdateRegistrationServiceInvocationStrategy == null) {
            init(caaersLibLocation, caaersConfig);
        }
        if (initStatus) {
            return caaersUpdateRegistrationServiceInvocationStrategy;
        } else {
            return null;
        }
    }

    /**
     * Method to get CaAERSAdverseEventServiceInvocationStrategy
     * 
     * @param caaersLibLocation - caaersLibLocation
     * @param caaersConfig - caaersConfig
     * @return caaersAdverseEventServiceInvocationStrategy
     */
    public static ServiceInvocationStrategy createCaAERSAdverseEventServiceInvocationStrategy(
            final String[] caaersLibLocation, final String... caaersConfig) {

        if (initStatus == null || caaersAdverseEventServiceInvocationStrategy == null) {
            init(caaersLibLocation, caaersConfig);
        }
        if (initStatus) {
            return caaersAdverseEventServiceInvocationStrategy;
        } else {
            return null;
        }
    }

}
