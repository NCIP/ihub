package gov.nih.nci.integration.dao;

import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;

import java.util.Map;

/**
 * ServiceInvocationMessageDao
 * 
 * @author Vinodh
 * 
 */
public interface ServiceInvocationMessageDao extends Dao<ServiceInvocationMessage> {

    /**
     * getAllByReferenceMessageId
     * 
     * @param refMsgId - MessageId
     * @return Map
     */
    @SuppressWarnings("unchecked")
    public abstract Map<StrategyIdentifier, ServiceInvocationMessage> getAllByReferenceMessageId(Long refMsgId);

}
