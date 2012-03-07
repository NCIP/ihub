package gov.nih.nci.integration.dao;

import gov.nih.nci.integration.domain.ServiceInvocationMessage;

import javax.persistence.EntityManager;

public class ServiceInvocationMessageDao extends
		AbstractDao<ServiceInvocationMessage> implements
		Dao<ServiceInvocationMessage> {

	/**
	 * Constructor.
	 * 
	 * @param em
	 *            JPA EntityManager
	 */
	public ServiceInvocationMessageDao(EntityManager em) {
		super(ServiceInvocationMessage.class, em);
	}

}
