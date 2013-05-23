/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.dao;

import gov.nih.nci.integration.domain.IHubMessage;

public interface IHubMessageDao extends Dao<IHubMessage> {

	/**
	 * Persists the request message as IHubMessage
	 * @param request request string
	 * @return entity id
	 */
	public abstract Long saveMessage(String request);

}
