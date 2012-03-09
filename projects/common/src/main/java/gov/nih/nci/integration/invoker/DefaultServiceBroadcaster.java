package gov.nih.nci.integration.invoker;

import java.sql.Date;

import gov.nih.nci.integration.dao.ServiceInvocationMessageDao;
import gov.nih.nci.integration.domain.IHubMessage;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.Status;
import gov.nih.nci.integration.domain.StrategyIdentifier;

public class DefaultServiceBroadcaster implements ServiceBroadcaster {

	ServiceInvocationMessageDao serviceInvocationMessageDao;

	public DefaultServiceBroadcaster(
			ServiceInvocationMessageDao serviceInvocationMessageDao) {
		super();
		this.serviceInvocationMessageDao = serviceInvocationMessageDao;
	}

	@Override
	public ServiceInvocationResult delegateServiceInvocation(
			Long referenceMessageId, String message,
			ServiceInvocationStrategy serviceInvocationStrategy) {
		final Date stTime = new Date(new java.util.Date().getTime());
		ServiceInvocationResult serviceInvocationResult;
		try {
			serviceInvocationResult = serviceInvocationStrategy.invoke(message);
		} catch (Exception e) {
			serviceInvocationResult = new ServiceInvocationResult();
			serviceInvocationResult.setInvocationException(e);
		}
		persistServiceInvocationMessage(referenceMessageId, message, stTime,
				serviceInvocationStrategy.getStrategyIdentifier(),
				serviceInvocationResult);
		return serviceInvocationResult;
	}

	private void persistServiceInvocationMessage(Long referenceMessageId,
			String message, Date startTime,
			StrategyIdentifier strategyIdentifier,
			ServiceInvocationResult serviceInvocationResult) {

		final ServiceInvocationMessage serviceInvocationMessage = new ServiceInvocationMessage();
		serviceInvocationMessage.setStrategyIdentifier(strategyIdentifier);

		final IHubMessage iHubMessage = new IHubMessage();
		iHubMessage.setStartTime(startTime);
		iHubMessage.setEndTime(new Date(new java.util.Date().getTime()));
		iHubMessage.setRequest(message);

		final Exception invocationException = serviceInvocationResult
				.getInvocationException();
		if (invocationException != null) {
			iHubMessage.setStatus(Status.FAILED);
			// FIXIT : construct proper error message with error code and format
			serviceInvocationMessage.setInvocationException(invocationException
					.getMessage());
		} else {
			iHubMessage.setStatus(Status.SUCCESS);
			iHubMessage.setResponse(serviceInvocationResult.getResult());
		}

		serviceInvocationMessage.setReferenceMessageId(referenceMessageId);
		serviceInvocationMessage.setMessage(iHubMessage);

		final Long id = serviceInvocationMessageDao
				.save(serviceInvocationMessage);
	}

}
