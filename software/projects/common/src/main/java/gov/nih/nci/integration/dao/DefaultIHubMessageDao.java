package gov.nih.nci.integration.dao;

import gov.nih.nci.integration.domain.IHubMessage;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.Status;
import gov.nih.nci.integration.domain.StrategyIdentifier;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

/**
 * DefaultIHubMessageDao
 * 
 * @author Vinodh
 * 
 */
@Transactional
public class DefaultIHubMessageDao extends AbstractDao<IHubMessage> implements Dao<IHubMessage>, IHubMessageDao {

    /**
     * Constructor.
     * 
     * @param em JPA EntityManager
     */
    public DefaultIHubMessageDao(EntityManager em) {
        super(IHubMessage.class, em);
    }

    /**
     * saveMessage
     * @param referenceMessageId - MessageId
     * @param request - Message in the form of XMLString
     * @return Id - returned Id
     */
    public Long saveMessage(Long referenceMessageId, String request) {
        final IHubMessage iHubMessage = new IHubMessage();
        final java.util.Date currDt = new java.util.Date();//NOPMD
        iHubMessage.setStartTime(new Date(currDt.getTime()));
        iHubMessage.setRequest(request);
        iHubMessage.setReferenceMessageId(referenceMessageId);
        iHubMessage.setStatus(Status.PROCESS);

        return save(iHubMessage);
    }
    
    /**
     * getAllByReferenceMessageId
     * 
     * @param refMsgId - messageId
     * @return List<IHubMessage> - list of IHubMessae entities
     */
    @SuppressWarnings("unchecked")
    public List<IHubMessage> getAllByReferenceMessageId(Long refMsgId) {
        final Query msgsQuery = this.getEm().createQuery(
                "from " + getDomainClass().getSimpleName()
                        + " iHubMsg where iHubMsg.referenceMessageId = :referenceMessageId ");
        msgsQuery.setParameter("referenceMessageId", refMsgId);
        return msgsQuery.getResultList();
    }

    /**
     * Updates the IHubMessage for the main incoming message with the iHub response. 
     * Note: IHubMessage corresponding to each ServiceInvocation will not be updated from here.
     * 
     * @param refMsgId - messageId
     * @param response response string
     * @return entity id
     */
    public Long updateIHubResponse(Long refMsgId, String response) {
        List<IHubMessage> msgs = getAllByReferenceMessageId(refMsgId);
        // 1st msg will be the incoming msg
        // the service invocation messages will be after that
        IHubMessage mainMsg = msgs.get(0); 
        
        mainMsg.setResponse(response);
        update(mainMsg);
        
        return mainMsg.getId();
    }
    
    
}
