/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.catissue;

import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A caTissue Task that returns a result and may throw an exception
 * @author chandrasekaravr
 * 
 */
public class CaTissueTask implements Callable<ServiceInvocationResult> {

    private Object caTissueClientInstance;
    private Method methodToInvoke;
    private final String message;

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueTask.class);

    /**
     * Constructor
     * 
     * @param caTissueClientClass - caTissueClientClass
     * @param loginName - loginName
     * @param password - password
     * @param methodName - methodName
     * @param message - message
     * @throws IntegrationException - IntegrationException
     */
    @SuppressWarnings("PMD.AvoidCatchingGenericException")
    public CaTissueTask(Class<?> caTissueClientClass, String loginName, String password, String methodName,
            String message) throws IntegrationException {
        super();

        Constructor<?> constructor;
        try {
            constructor = caTissueClientClass.getDeclaredConstructor(String.class, String.class);
            this.caTissueClientInstance = constructor.newInstance(loginName, password);
            methodToInvoke = caTissueClientClass.getDeclaredMethod(methodName, String.class);
         // CHECKSTYLE:OFF
        } catch (Exception e) {
            LOG.error("CaTissueTask-Exception inside the Constructor. ", e);
         // CHECKSTYLE:ON
            throw new IntegrationException(IntegrationError._1052, e);
        } 

        this.message = message;
    }

    @Override
    public ServiceInvocationResult call() {
        final ServiceInvocationResult result = new ServiceInvocationResult();
        try {
            final Object retValue = methodToInvoke.invoke(caTissueClientInstance, message);
            result.setDataChanged(true);
            result.setOriginalData(retValue);
        } catch (InvocationTargetException e) {
            final String exceptionMessage = e.getTargetException().getMessage();
            LOG.error("InvocationTargetException is :: ", e);
            final IntegrationException ie = new IntegrationException(IntegrationError._1051, e.getTargetException(),
                    exceptionMessage);
            result.setInvocationException(ie);
        } catch (IllegalArgumentException e) {
            final String exceptionMessage = e.getMessage();
            LOG.error("IllegalArgumentException is :: ", e);
            final IntegrationException ie = new IntegrationException(IntegrationError._1051, e, exceptionMessage);
            result.setInvocationException(ie);
        } catch (IllegalAccessException e) {
            final String exceptionMessage = e.getMessage();
            LOG.error("IllegalAccessException is :: ", e);
            final IntegrationException ie = new IntegrationException(IntegrationError._1051, e, exceptionMessage);
            result.setInvocationException(ie);
        }

        return result;
    }
}
