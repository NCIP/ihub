/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.dao;

import gov.nih.nci.integration.domain.IHubMessage;
import gov.nih.nci.integration.domain.Status;

import java.sql.Date;

import javax.persistence.EntityManager;

import org.springframework.transaction.annotation.Transactional;

@Transactional
public class DefaultIHubMessageDao extends AbstractDao<IHubMessage> implements
		Dao<IHubMessage>, IHubMessageDao {

	/**
	 * Constructor.
	 * 
	 * @param em JPA EntityManager
	 */
	public DefaultIHubMessageDao(EntityManager em) {
		super(IHubMessage.class, em);
	}
	
	/* (non-Javadoc)
	 * @see gov.nih.nci.integration.dao.IHubMessageDaoIntf#saveMessage(java.lang.String)
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
