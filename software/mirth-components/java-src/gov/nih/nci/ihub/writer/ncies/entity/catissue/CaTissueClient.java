package gov.nih.nci.ihub.writer.ncies.entity.catissue;

import org.w3c.dom.Node;

public interface CaTissueClient {
	
	public void createObject(Node node, String operationName) throws InvokeCaTissueException, PayloadParseException,Exception;


}
