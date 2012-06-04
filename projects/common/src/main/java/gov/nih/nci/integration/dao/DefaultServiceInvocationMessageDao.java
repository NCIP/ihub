package gov.nih.nci.integration.dao;

import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

/**
 * DefaultServiceInvocationMessageDao
 * 
 * @author Vinodh
 * 
 */
@Transactional
public class DefaultServiceInvocationMessageDao extends AbstractDao<ServiceInvocationMessage> implements
        ServiceInvocationMessageDao {

    /**
     * Constructor.
     * 
     * @param em JPA EntityManager
     */
    public DefaultServiceInvocationMessageDao(EntityManager em) {
        super(ServiceInvocationMessage.class, em);
    }

    /**
     * getAllByReferenceMessageId
     * 
     * @param refMsgId - messageId
     * @return Map
     */
    @SuppressWarnings("unchecked")
    public Map<StrategyIdentifier, ServiceInvocationMessage> getAllByReferenceMessageId(Long refMsgId) {
        final Query msgsQuery = this.getEm().createQuery(
                "from " + getDomainClass().getSimpleName()
                        + " svcInvMsg where svcInvMsg.referenceMessageId = :referenceMessageId ");
        msgsQuery.setParameter("referenceMessageId", refMsgId);
        List<ServiceInvocationMessage> msgs = msgsQuery.getResultList();
        Map<StrategyIdentifier, ServiceInvocationMessage> map = new HashMap<StrategyIdentifier, ServiceInvocationMessage>();
        for (ServiceInvocationMessage serviceInvocationMessage : msgs) {
            map.put(serviceInvocationMessage.getStrategyIdentifier(), serviceInvocationMessage);
        }

        return map;
    }
}
