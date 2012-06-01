package gov.nih.nci.integration.dao;

import gov.nih.nci.integration.domain.IHubMessage;

/**
 * IHubMessageDao
 * @author Vinodh
 *
 */
public interface IHubMessageDao extends Dao<IHubMessage> {

	/**
	 * Persists the request message as IHubMessage
	 * 
	 * @param request
	 *            request string
	 * @return entity id
	 */
	Long saveMessage(String request);

}