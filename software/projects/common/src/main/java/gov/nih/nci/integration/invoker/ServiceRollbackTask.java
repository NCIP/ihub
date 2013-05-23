/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;

import java.util.concurrent.Callable;

/**
 * This class provide method to call the Rollback method of a strategy class. 
 * @author Vinodh
 * 
 */
public class ServiceRollbackTask implements Callable<ServiceInvocationResult> {

    private final ServiceInvocationMessage message;

    private final ServiceInvocationStrategy serviceInvocationStrategy;

    /**
     * Constructor
     * 
     * @param message - ServiceInvocationMessage containing the XMLString inside originalData. This contains the data to
     *            be rollback
     * @param serviceInvocationStrategy -- Strategy class whose rollback has to be called.
     */
    public ServiceRollbackTask(ServiceInvocationMessage message, ServiceInvocationStrategy serviceInvocationStrategy) {
        super();
        this.message = message;
        this.serviceInvocationStrategy = serviceInvocationStrategy;
    }

   
    @Override
    public ServiceInvocationResult call() throws IntegrationException {

        if (message == null) { //NOPMD
            throw new IntegrationException(IntegrationError._1064);
        }

        if (serviceInvocationStrategy == null) { //NOPMD
            throw new IntegrationException(IntegrationError._1065);
        }
        return serviceInvocationStrategy.rollback(message);
    }

}
