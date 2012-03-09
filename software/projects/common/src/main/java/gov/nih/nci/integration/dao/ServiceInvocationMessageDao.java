package gov.nih.nci.integration.dao;

import java.util.List;

import gov.nih.nci.integration.domain.ServiceInvocationMessage;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ServiceInvocationMessageDao extends
		AbstractDao<ServiceInvocationMessage> implements
		Dao<ServiceInvocationMessage> {

	/**
	 * Constructor.
	 * 
	 * @param em JPA EntityManager
	 */
	public ServiceInvocationMessageDao(EntityManager em) {
		super(ServiceInvocationMessage.class, em);
	}
	
	/**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<ServiceInvocationMessage> getAllByReferenceMessageId(Long refMsgId) {
    	final Query msgsQuery = this.getEntityManager().createQuery("from " + getDomainClass().getSimpleName()
                + " svcInvMsg where svcInvMsg.referenceMessageId = :referenceMessageId ");
    	msgsQuery.setParameter("referenceMessageId", refMsgId);
        return msgsQuery.getResultList();
    }
}
