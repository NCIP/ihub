package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.dao.ServiceInvocationMessageDao;
import gov.nih.nci.integration.domain.IHubMessage;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.Status;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.exception.IntegrationError;
import gov.nih.nci.integration.exception.IntegrationException;

import java.sql.Date;

/**
 * An implementation of ServiceBroadcaster that uses the DAOs to persist ServiceInvocationMessage objects and invoke
 * ServiceInvocationStrategy implementations. 
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

        final Date stTime = new Date(new java.util.Date().getTime());

        final ServiceInvocationMessage serviceInvocationMessage = prepareServiceInvocationMessage(referenceMessageId,
                message, stTime, serviceInvocationStrategy.getStrategyIdentifier());

        ServiceInvocationResult serviceInvocationResult = delegate(serviceInvocationMessage, serviceInvocationStrategy);

        if (serviceInvocationResult.isFault()) {
            // upon receiving the fault can control retry attempts, if it makes
            // sense or not
            if (serviceInvocationResult.isRetry()) { //NOPMD
                final int retryCnt = serviceInvocationStrategy.getRetryCount();
                for (int i = 0; i < retryCnt; i++) {
                    serviceInvocationResult = delegate(serviceInvocationMessage, serviceInvocationStrategy);
                    if (!serviceInvocationResult.isFault()) { //NOPMD
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
     // CHECKSTYLE:OFF
        try {
            serviceInvocationResult = serviceInvocationStrategy.invoke(serviceInvocationMessage);
        } catch (Exception e) { //NOPMD
            // this code must not be reached...ServiceInvocationStrategy must
            // not throw exception
            // TODO : To handle any exceptions not handled by
            // ServiceInvocationStrategy
            if (!(e instanceof IntegrationException)) {
                e = new IntegrationException(IntegrationError._1000, e.getCause(), (Object) null);
            }
            serviceInvocationResult = new ServiceInvocationResult();
            serviceInvocationResult.setInvocationException(e);
        }
        
        // CHECKSTYLE:ON
        return serviceInvocationResult;
    }

    private ServiceInvocationMessage prepareServiceInvocationMessage(Long referenceMessageId, String message,
            Date startTime, StrategyIdentifier strategyIdentifier) {
        final ServiceInvocationMessage serviceInvocationMessage = new ServiceInvocationMessage();
        serviceInvocationMessage.setStrategyIdentifier(strategyIdentifier);

        final IHubMessage iHubMessage = new IHubMessage();
        iHubMessage.setStartTime(startTime);
        iHubMessage.setEndTime(new Date(new java.util.Date().getTime()));
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
        if (invocationException != null) { //NOPMD
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
