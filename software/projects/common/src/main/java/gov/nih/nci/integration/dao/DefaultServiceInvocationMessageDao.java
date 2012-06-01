package gov.nih.nci.integration.dao;

import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class DefaultServiceInvocationMessageDao extends
		AbstractDao<ServiceInvocationMessage> implements
		ServiceInvocationMessageDao {

	/**
	 * Constructor.
	 * 
	 * @param em
	 *            JPA EntityManager
	 */
	public DefaultServiceInvocationMessageDao(EntityManager em) {
		super(ServiceInvocationMessage.class, em);
		System.out.println("em in constr is " + em);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seegov.nih.nci.integration.dao.ServiceInvocationMessageDaoIntf#
	 * getAllByReferenceMessageId(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	public Map<StrategyIdentifier, ServiceInvocationMessage> getAllByReferenceMessageId(
			Long refMsgId) {
		System.out.println("got em from abstract dao " + this.getEm());
		final Query msgsQuery = this
				.getEm()
				.createQuery(
						"from "
								+ getDomainClass().getSimpleName()
								+ " svcInvMsg where svcInvMsg.referenceMessageId = :referenceMessageId ");
		msgsQuery.setParameter("referenceMessageId", refMsgId);
		List<ServiceInvocationMessage> msgs = msgsQuery.getResultList();
		Map<StrategyIdentifier, ServiceInvocationMessage> map = new HashMap<StrategyIdentifier, ServiceInvocationMessage>();
		for (ServiceInvocationMessage serviceInvocationMessage : msgs) {
			map.put(serviceInvocationMessage.getStrategyIdentifier(),
					serviceInvocationMessage);
		}

		return map;
	}
}
