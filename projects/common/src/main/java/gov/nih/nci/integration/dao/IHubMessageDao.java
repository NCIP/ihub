package gov.nih.nci.integration.dao;

import java.sql.Date;

import javax.persistence.EntityManager;

import gov.nih.nci.integration.domain.IHubMessage;
import gov.nih.nci.integration.domain.Status;

public class IHubMessageDao extends AbstractDao<IHubMessage> implements
		Dao<IHubMessage> {

	/**
	 * Constructor.
	 * 
	 * @param em JPA EntityManager
	 */
	public IHubMessageDao(EntityManager em) {
		super(IHubMessage.class, em);
	}
	
	/**
	 * Persists the request message as IHubMessage
	 * @param request request string
	 * @return entity id
	 */
	public Long saveMessage(String request) {
		IHubMessage iHubMessage = new IHubMessage();
		java.util.Date currDt = new java.util.Date();
		iHubMessage.setStartTime(new Date(currDt.getTime()));
		iHubMessage.setRequest(request);
		iHubMessage.setStatus(Status.PROCESS);
		
		return save(iHubMessage);
	}

}
