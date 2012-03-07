package gov.nih.nci.integration.dao;

import javax.persistence.EntityManager;

import gov.nih.nci.integration.domain.IHubMessage;

public class IHubMessageDao extends AbstractDao<IHubMessage> implements
		Dao<IHubMessage> {

	/**
	 * Constructor.
	 * 
	 * @param em
	 *            JPA EntityManager
	 */
	public IHubMessageDao(EntityManager em) {
		super(IHubMessage.class, em);
	}

}
