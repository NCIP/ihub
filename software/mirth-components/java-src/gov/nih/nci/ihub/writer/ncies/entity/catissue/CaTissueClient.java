/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.writer.ncies.entity.catissue;

import org.w3c.dom.Node;

public interface CaTissueClient {
	
	public void createObject(Node node, String operationName) throws InvokeCaTissueException, PayloadParseException,Exception;


}
