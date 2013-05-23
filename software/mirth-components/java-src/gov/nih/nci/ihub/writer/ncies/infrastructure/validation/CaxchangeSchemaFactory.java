/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.writer.ncies.infrastructure.validation;

import javax.xml.validation.Schema;

public interface CaxchangeSchemaFactory {

	public void init();
	
	public Schema getSchema(String serviceType, String namespaceName) throws SchemaFactoryException ;
}
