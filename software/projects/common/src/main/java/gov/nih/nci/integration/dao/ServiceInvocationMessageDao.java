/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.dao;

import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;

import java.util.Map;

/**
 *  
 * @author Vinodh
 */
public interface ServiceInvocationMessageDao extends Dao<ServiceInvocationMessage> {

    /**
     * getAllByReferenceMessageId
     * 
     * @param refMsgId - MessageId
     * @return Map
     */
    @SuppressWarnings("unchecked")
    Map<StrategyIdentifier, ServiceInvocationMessage> getAllByReferenceMessageId(Long refMsgId);

}
