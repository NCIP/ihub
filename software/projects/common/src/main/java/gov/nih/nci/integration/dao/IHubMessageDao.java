package gov.nih.nci.integration.dao;

import java.util.List;

import gov.nih.nci.integration.domain.IHubMessage;

/**
 * Interface which has method for persistence of IHubMessage objects.
 * 
 * @author Vinodh
 * 
 */
public interface IHubMessageDao extends Dao<IHubMessage> {

    /**
     * Persists the request message as IHubMessage
     * 
     * @param referenceMessageId reference message id as long value
     * @param request request string
     * @return entity id
     */
    Long saveMessage(Long referenceMessageId, String request);
    
    /**
     * getAllByReferenceMessageId
     * 
     * @param refMsgId - messageId
     * @return List<IHubMessage> - list of IHubMessae entities
     */
    List<IHubMessage> getAllByReferenceMessageId(Long refMsgId);
    
    /**
     * Updates the IHubMessage for the main incoming message with the iHub response. 
     * Note: IHubMessage corresponding to each ServiceInvocation will not be updated from here.
     * 
     * @param refMsgId - messageId
     * @param response response string
     * @return entity id
     */
    Long updateIHubResponse(Long refMsgId, String response);

}
