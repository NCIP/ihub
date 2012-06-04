package gov.nih.nci.integration.dao;

import gov.nih.nci.integration.domain.IHubMessage;
import gov.nih.nci.integration.domain.Status;

import java.sql.Date;

import javax.persistence.EntityManager;

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
     * 
     * @param request
     * @return Id
     */
    public Long saveMessage(Long referenceMessageId, String request) {
        IHubMessage iHubMessage = new IHubMessage();
        java.util.Date currDt = new java.util.Date();
        iHubMessage.setStartTime(new Date(currDt.getTime()));
        iHubMessage.setRequest(request);
        iHubMessage.setReferenceMessageId(referenceMessageId);
        iHubMessage.setStatus(Status.PROCESS);

        return save(iHubMessage);
    }

}
