package gov.nih.nci.integration.dao;

import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;

import java.util.Map;

public interface ServiceInvocationMessageDao extends
		Dao<ServiceInvocationMessage> {

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public abstract Map<StrategyIdentifier, ServiceInvocationMessage> getAllByReferenceMessageId(
			Long refMsgId);

}