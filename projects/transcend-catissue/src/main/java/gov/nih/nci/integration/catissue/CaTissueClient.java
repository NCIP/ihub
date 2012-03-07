package gov.nih.nci.integration.catissue;

public interface CaTissueClient {
	
	public void createObject(String xmlStr, String operationName) throws InvokeCaTissueException, PayloadParseException,Exception;


}
