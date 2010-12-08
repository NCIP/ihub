package gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer;


/**
 * A common interface for transforming Suite messages to Clinical Connector messages.
 * 
 * @author marwahah
 *
 */
public interface CCTransformer {
	
	public  Object convert2Request(String xml) throws CCTransformException;
	
	public  String convert2RequestString(String xml) throws CCTransformException;

	public  Object convert2RollbackRequest(String xml) throws CCTransformException;
	
	public  String convert2RollbackRequestString(String xml) throws CCTransformException;

}
