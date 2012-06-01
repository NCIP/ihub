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
 * 
 * @author chandrasekaravr
 * 
 */
public class TransactionalServiceInvocatorAndResultAggregator implements
		ServiceInvocatorAndResultAggregator {

	private ServiceBroadcaster serviceBroadcaster;
	private ServiceInvocationMessageDao serviceInvocationMessageDao;
	private ExecutorCompletionService<ServiceInvocationResult> executorCompletionService;

	private Collection<ServiceInvocationStrategy> serviceInvocationStrategies;

	private static Logger LOG = LoggerFactory
			.getLogger(TransactionalServiceInvocatorAndResultAggregator.class);

	public TransactionalServiceInvocatorAndResultAggregator(
			ServiceBroadcaster serviceBroadcaster,
			ServiceInvocationMessageDao serviceInvocationMessageDao,
			Executor executor) {
		super();
		this.serviceBroadcaster = serviceBroadcaster;
		this.serviceInvocationMessageDao = serviceInvocationMessageDao;
		this.executorCompletionService = new ExecutorCompletionService<ServiceInvocationResult>(
				executor);
		this.serviceInvocationStrategies = new ArrayList<ServiceInvocationStrategy>();

		System.out.println("serviceInvocationMessageDao 2 is "
				+ serviceInvocationMessageDao);
	}

	@Override
	public synchronized void invokeService(Long referenceMessageId,
			String message, ServiceInvocationStrategy serviceInvocationStrategy) {

		executorCompletionService.submit(new ServiceBroadcasterTask(
				serviceBroadcaster, referenceMessageId, message,
				serviceInvocationStrategy));
		serviceInvocationStrategies.add(serviceInvocationStrategy);
	}

	@Override
	public ServiceInvocationResult aggregateResults(Long refMsgId) {
		ServiceInvocationResult serviceInvocationResult = null;

		// TODO is check required to match refMsgId

		List<ServiceInvocationResult> serviceInvocationResultLst = new ArrayList<ServiceInvocationResult>();
		final int noOfTasks = serviceInvocationStrategies.size();

		boolean isRollback = false;
		for (int i = 0; i < noOfTasks; i++) {
			try {
				serviceInvocationResult = executorCompletionService.take()
						.get();
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
		}// end of for

		if (isRollback) {
			LOG.debug("Exception while service invocation",
					serviceInvocationResult.getInvocationException());
			executeRollback(refMsgId);
			return serviceInvocationResult;
		}

		serviceInvocationResult = checkRollback(serviceInvocationResultLst);

		if (serviceInvocationResult.isFault()) {
			LOG.debug("Exception from service", serviceInvocationResult
					.getInvocationException());
			executeRollback(refMsgId);
		}
		return serviceInvocationResult;
	}

	private void executeRollback(Long referenceMessageId) {
		LOG.debug("Executing rollback");
		Map<StrategyIdentifier, ServiceInvocationMessage> msgsMap = serviceInvocationMessageDao
				.getAllByReferenceMessageId(referenceMessageId);

		for (ServiceInvocationStrategy serviceInvocationStrategy : serviceInvocationStrategies) {
			ServiceInvocationMessage msg = msgsMap
					.get(serviceInvocationStrategy.getStrategyIdentifier());
			if (!msg.isDataChanged()) {
				continue;
			}
			executorCompletionService.submit(new ServiceRollbackTask(msg,
					serviceInvocationStrategy));
		}
	}

	private ServiceInvocationResult checkRollback(
			List<ServiceInvocationResult> serviceInvocationResultLst) {
		for (ServiceInvocationResult serviceInvocationResult : serviceInvocationResultLst) {
			if (serviceInvocationResult.isFault()) {
				return serviceInvocationResult;
			}
		}
		ServiceInvocationResult serviceInvocationResult = new ServiceInvocationResult();
		serviceInvocationResult.setResult("Success");
		return serviceInvocationResult;
	}
}
