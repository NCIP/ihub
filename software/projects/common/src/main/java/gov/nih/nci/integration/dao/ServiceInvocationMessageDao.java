package gov.nih.nci.integration.dao;

import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;

import java.util.Map;

/**
 *  
 * @author Vinodh
 */
public interface ServiceInvocationMessageDao extends Dao<ServiceInvocationMessage> {

    /**
     * getAllByReferenceMessageId
     * 
     * @param refMsgId - MessageId
     * @return Map
     */
    @SuppressWarnings("unchecked")
    Map<StrategyIdentifier, ServiceInvocationMessage> getAllByReferenceMessageId(Long refMsgId);

}
