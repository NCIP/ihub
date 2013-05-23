/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.writer.ncies.common;

import java.util.List;

import org.w3c.dom.Element;

public interface GridMessage {

	public Element getMetaData();

	public Element getPayload();

	public List<Element> getPayloads();

	public Element getSchemaDefinition();

	public String getExternalIdentifier();

	public String getCaxchangeIdentifier();

	public String getOperationName();

	public String getServiceType();

}
