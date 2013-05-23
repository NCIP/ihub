/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.dao.ServiceInvocationMessageDao;
import gov.nih.nci.integration.domain.IHubMessage;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.Status;
import gov.nih.nci.integration.domain.StrategyIdentifier;

import java.sql.Date;

/**
 * An implementation of ServiceBroadcaster that uses the DAOs to persist ServiceInvocationMessage objects and invoke
 * ServiceInvocationStrategy implementations.
 * 
 * @author Vinodh
 * 
 */
public class DefaultServiceBroadcaster implements ServiceBroadcaster {

    private final ServiceInvocationMessageDao serviceInvocationMessageDao;

    /**
     * Constructor
     * 
     * @param serviceInvocationMessageDao ServiceInvocationMessageDao
     */
    public DefaultServiceBroadcaster(ServiceInvocationMessageDao serviceInvocationMessageDao) {
        super();
        this.serviceInvocationMessageDao = serviceInvocationMessageDao;
    }

    @Override
    public ServiceInvocationResult delegateServiceInvocation(Long referenceMessageId, String message,
            ServiceInvocationStrategy serviceInvocationStrategy) {

        final Date stTime = new Date(new java.util.Date().getTime()); // NOPMD

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(referenceMessageId,
                message, stTime, serviceInvocationStrategy.getStrategyIdentifier());

        ServiceInvocationResult serviceInvocationResult = delegate(serviceInvocationMessage, serviceInvocationStrategy);

        if (serviceInvocationResult.isFault()) {
            // upon receiving the fault can control retry attempts, if it makes
            // sense or not
            if (serviceInvocationResult.isRetry()) { // NOPMD
                final int retryCnt = serviceInvocationStrategy.getRetryCount();
                for (int i = 0; i < retryCnt; i++) {
                    serviceInvocationResult = delegate(serviceInvocationMessage, serviceInvocationStrategy);
                    if (!serviceInvocationResult.isFault()) { // NOPMD
                        break;
                    }
                }
            }
        }

        persistServiceInvocationMessage(serviceInvocationMessage, serviceInvocationResult);

        return serviceInvocationResult;
    }

    private ServiceInvocationResult delegate(ServiceInvocationMessage serviceInvocationMessage,
            ServiceInvocationStrategy serviceInvocationStrategy) {
        ServiceInvocationResult serviceInvocationResult;
        serviceInvocationResult = serviceInvocationStrategy.invoke(serviceInvocationMessage);

        return serviceInvocationResult;
    }

    private ServiceInvocationMessage prepareServiceInvocationMessage(Long referenceMessageId, String message,
            Date startTime, StrategyIdentifier strategyIdentifier) {
        final ServiceInvocationMessage serviceInvocationMessage = new ServiceInvocationMessage();
        serviceInvocationMessage.setStrategyIdentifier(strategyIdentifier);

        final IHubMessage iHubMessage = new IHubMessage();
        iHubMessage.setStartTime(startTime);
        iHubMessage.setEndTime(new Date(new java.util.Date().getTime())); // NOPMD
        iHubMessage.setRequest(message);
        iHubMessage.setReferenceMessageId(referenceMessageId);

        serviceInvocationMessage.setReferenceMessageId(referenceMessageId);
        serviceInvocationMessage.setMessage(iHubMessage);

        return serviceInvocationMessage;
    }

    private void persistServiceInvocationMessage(ServiceInvocationMessage serviceInvocationMessage,
            ServiceInvocationResult serviceInvocationResult) {

        final Exception invocationException = serviceInvocationResult.getInvocationException();
        final IHubMessage iHubMessage = serviceInvocationMessage.getMessage();
        if (invocationException != null) { // NOPMD
            iHubMessage.setStatus(Status.FAILED);
            serviceInvocationMessage.setInvocationException(invocationException.getMessage());
        } else {
            iHubMessage.setStatus(Status.SUCCESS);
            iHubMessage.setResponse(serviceInvocationResult.getResult());
        }
        serviceInvocationMessage.setDataChanged(serviceInvocationResult.isDataChanged());
        serviceInvocationMessage.setOriginalData((String) serviceInvocationResult.getOriginalData());

        final Long id = serviceInvocationMessageDao.save(serviceInvocationMessage);

        serviceInvocationResult.setMessageId(id);
    }

}
