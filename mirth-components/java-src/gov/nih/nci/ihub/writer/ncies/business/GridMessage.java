package gov.nih.nci.ihub.writer.ncies.business;

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
