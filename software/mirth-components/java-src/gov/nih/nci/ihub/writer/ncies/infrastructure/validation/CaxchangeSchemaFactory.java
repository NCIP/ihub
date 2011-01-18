package gov.nih.nci.ihub.writer.ncies.infrastructure.validation;

import javax.xml.validation.Schema;

public interface CaxchangeSchemaFactory {

	public void init();
	
	public Schema getSchema(String serviceType, String namespaceName) throws SchemaFactoryException ;
}
