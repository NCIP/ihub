/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.dao.ServiceInvocationMessageDao;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This implementation of ServiceInvocatorAndResultAggregator will aggregate responses by periodically querying the
 * database until it finds either all service invocation responses or some (configurable) timeout period has elapsed. If
 * any service invocation response is an error or timeout, it will initiate a rollback
 * 
 * @author chandrasekaravr
 * 
 */
public class TransactionalServiceInvocatorAndResultAggregator implements ServiceInvocatorAndResultAggregator {

    private final ServiceBroadcaster serviceBroadcaster;
    private final ServiceInvocationMessageDao serviceInvocationMessageDao;
    private final ExecutorCompletionService<ServiceInvocationResult> executorCompletionService;

    private final Collection<ServiceInvocationStrategy> serviceInvocationStrategies;

    private static final Logger LOG = LoggerFactory.getLogger(TransactionalServiceInvocatorAndResultAggregator.class);

    /**
     * Constructor
     * 
     * @param serviceBroadcaster - ServiceBroadcaster impl object eg. DefaultServiceBroadcaster
     * @param serviceInvocationMessageDao - ServiceInvocationMessageDao impl object
     * @param executor - object that executes submitted Runnable tasks
     */
    public TransactionalServiceInvocatorAndResultAggregator(ServiceBroadcaster serviceBroadcaster,
            ServiceInvocationMessageDao serviceInvocationMessageDao, Executor executor) {
        super();
        this.serviceBroadcaster = serviceBroadcaster;
        this.serviceInvocationMessageDao = serviceInvocationMessageDao;
        this.executorCompletionService = new ExecutorCompletionService<ServiceInvocationResult>(executor);
        this.serviceInvocationStrategies = new ArrayList<ServiceInvocationStrategy>();
    }

    @Override
    public synchronized void invokeService(Long referenceMessageId, String message,
            ServiceInvocationStrategy serviceInvocationStrategy) {

        executorCompletionService.submit(new ServiceBroadcasterTask(serviceBroadcaster, referenceMessageId, message,
                serviceInvocationStrategy));
        serviceInvocationStrategies.add(serviceInvocationStrategy);
    }

    @Override
    public ServiceInvocationResult aggregateResults(Long refMsgId) {
        ServiceInvocationResult serviceInvocationResult = null;

        // TODO is check required to match refMsgId

        final List<ServiceInvocationResult> serviceInvocationResultLst = new ArrayList<ServiceInvocationResult>();
        final int noOfTasks = serviceInvocationStrategies.size();

        boolean isRollback = false;
        for (int i = 0; i < noOfTasks; i++) {
            try {
                serviceInvocationResult = executorCompletionService.take().get();
            } catch (InterruptedException e) {
                serviceInvocationResult = new ServiceInvocationResult();
                serviceInvocationResult.setInvocationException(e);
                isRollback = true;
            } catch (ExecutionException e) {
                serviceInvocationResult = new ServiceInvocationResult();
                serviceInvocationResult.setInvocationException(e);
                isRollback = true;
            }
            serviceInvocationResultLst.add(serviceInvocationResult);
        } // end of for

        if (isRollback) {
            LOG.debug("Exception while service invocation", serviceInvocationResult.getInvocationException());
            final ServiceInvocationResult rollbackResult = executeRollback(refMsgId);
            if (rollbackResult != null) { //NOPMD
                return rollbackResult;
            }
            return serviceInvocationResult;
        }

        serviceInvocationResult = checkRollback(serviceInvocationResultLst);

        if (serviceInvocationResult.isFault()) {
            LOG.debug("Exception from service that triggered rollback",
                    serviceInvocationResult.getInvocationException());
            final ServiceInvocationResult rollbackResult = executeRollback(refMsgId);
            if (rollbackResult != null) {  //NOPMD
                return rollbackResult;
            }
        }
        return serviceInvocationResult;
    }

    private ServiceInvocationResult executeRollback(Long referenceMessageId) {
        LOG.debug("Executing rollback");
        final Map<StrategyIdentifier, ServiceInvocationMessage> msgsMap = serviceInvocationMessageDao  //NOPMD
                .getAllByReferenceMessageId(referenceMessageId);

        int noOfRollbacks = 0;
        for (ServiceInvocationStrategy serviceInvocationStrategy : serviceInvocationStrategies) {
            final ServiceInvocationMessage msg = msgsMap.get(serviceInvocationStrategy.getStrategyIdentifier());
            if (!msg.isDataChanged()) {
                continue;
            }
            executorCompletionService.submit(new ServiceRollbackTask(msg, serviceInvocationStrategy));
            noOfRollbacks++;
        }
        ServiceInvocationResult serviceInvocationResult = null;
        for (int i = 0; i < noOfRollbacks; i++) {
            try {
                serviceInvocationResult = executorCompletionService.take().get();
            } catch (InterruptedException e) {
                serviceInvocationResult = new ServiceInvocationResult();
                serviceInvocationResult.setInvocationException(e);
            } catch (ExecutionException e) {
                serviceInvocationResult = new ServiceInvocationResult();
                serviceInvocationResult.setInvocationException(e);
            }

            if (serviceInvocationResult.isFault()) {
                return serviceInvocationResult;
            }
        } // end of for

        return null;
    }

    private ServiceInvocationResult checkRollback(List<ServiceInvocationResult> serviceInvocationResultLst) {
        for (ServiceInvocationResult serviceInvocationResult : serviceInvocationResultLst) {
            if (serviceInvocationResult.isFault()) {
                return serviceInvocationResult;
            }
        }
        final ServiceInvocationResult serviceInvocationResult = new ServiceInvocationResult();
        serviceInvocationResult.setResult("Success");
        return serviceInvocationResult;
    }
}
