package gov.nih.nci.integration.invoker;

import gov.nih.nci.integration.domain.StrategyIdentifier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
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
	private ExecutorCompletionService<ServiceInvocationResult> executorCompletionService;

	private Collection<ServiceInvocationStrategy> serviceInvocationStrategies;
	private Map<StrategyIdentifier, String> strategyToMessageMap;
	
	private static Logger LOG = LoggerFactory
		.getLogger(TransactionalServiceInvocatorAndResultAggregator.class);

	public TransactionalServiceInvocatorAndResultAggregator(
			ServiceBroadcaster serviceBroadcaster,
			Executor executor) {
		super();
		this.serviceBroadcaster = serviceBroadcaster;
		this.executorCompletionService = new ExecutorCompletionService<ServiceInvocationResult>(executor);
		this.serviceInvocationStrategies = new ArrayList<ServiceInvocationStrategy>();
		this.strategyToMessageMap = new HashMap<StrategyIdentifier, String>();
	}

	@Override
	public synchronized void invokeService(Long refMessageId, String message,
			ServiceInvocationStrategy serviceInvocationStrategy) {

		executorCompletionService.submit(new ServiceBroadcasterTask(
				serviceBroadcaster, refMessageId, message,
				serviceInvocationStrategy));
		serviceInvocationStrategies.add(serviceInvocationStrategy);
		strategyToMessageMap.put(serviceInvocationStrategy
				.getStrategyIdentifier(), message);
	}

	@Override
	public ServiceInvocationResult aggregateResults() {

		List<ServiceInvocationResult> serviceInvocationResultLst = new ArrayList<ServiceInvocationResult>();
		final int noOfTasks = serviceInvocationStrategies.size();
		ServiceInvocationResult serviceInvocationResult = null;
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
		}// end of for

		if (isRollback) {
			LOG.debug("Exception while service invocation", serviceInvocationResult.getInvocationException());
			executeRollback();
			return serviceInvocationResult;
		}
	
		serviceInvocationResult = checkRollback(serviceInvocationResultLst);
		
		if (serviceInvocationResult.isFault()) {
			LOG.debug("Exception from service", serviceInvocationResult.getInvocationException());
			executeRollback();
		}
		return serviceInvocationResult;
	}

	private void executeRollback() {
		LOG.debug("Executing rollback");
		for (ServiceInvocationStrategy serviceInvocationStrategy : serviceInvocationStrategies) {
			executorCompletionService.submit(new ServiceRollbackTask(
					strategyToMessageMap.get(serviceInvocationStrategy
							.getStrategyIdentifier()),
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
