package gov.nih.nci.ihub.writer.ncies.common;

import gov.nih.nci.ihub.util.HubConstants;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * This class contains the methods related to getting meta data, payload and
 * schema definition related to caxchange message
 * 
 * @author steve
 * 
 */
public class GridMessageImpl implements GridMessage {

	public static Logger logger = LogManager.getLogger(GridMessageImpl.class);

	private Document message;

	/**
	 * Constructor for the class
	 * 
	 * @param message
	 */
	public GridMessageImpl(Document message) {

		this.message = message;
	}

	/**
	 * This methods gets the meta data form the caxchange message
	 * 
	 * @param
	 * @return null
	 * @throws
	 */
	public Element getMetaData() {
		NodeList elements = message.getDocumentElement()
				.getElementsByTagNameNS(HubConstants.NS,
						HubConstants.META_DATA_ELEMENT);
		//System.out.println(message.getDocumentElement().getElementsByTagName("ns1:metadata").getLength());
		if (elements.getLength() == 1) {
			return (Element) elements.item(0);
		} else {
			logger.warn("Grid message does not contain meta data element.");
			return null;
		}
	}

	/**
	 * This method gets the payload from the caxchange message
	 * 
	 * @param
	 * @return node or null
	 * @throws
	 */
	public Element getPayload() {
		NodeList nodes = getBusinessPayload().getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node instanceof Element) {				
				if (HubConstants.SCHEMA_DEFINITION_ELEMENT.equals(node
						.getLocalName())) {
					continue;
				}
				return (Element) node;
			}
		}
		logger.warn("Grid message does not contain payload element.");
		return null;
	}

	public List<Element> getPayloads() {
		NodeList nodes = getBusinessPayload().getChildNodes();
		List<Element> els = new ArrayList<Element>();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node instanceof Element) {
				if (HubConstants.SCHEMA_DEFINITION_ELEMENT.equals(node
						.getLocalName())) {
					continue;
				} else {
					els.add((Element) node);
				}
			}
		}
		return els;
	}
	
	public List<Element> getPayloads(Element payload) {
		NodeList nodes = payload.getChildNodes();
		List<Element> els = new ArrayList<Element>();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);			
			if (node instanceof Element) {
				System.out.println("Node Name: "+node.getNodeName());
				if (("ns1:"+HubConstants.SCHEMA_DEFINITION_ELEMENT).equals(node
						.getNodeName())) {
					continue;
				} else {
					els.add((Element) node);
				}
			}
		}
		return els;
	}

	/**
	 * This method gets the schema definition from the caxchange message
	 * 
	 * @param
	 * @return null
	 * @throws
	 */
	public Element getSchemaDefinition() {
		NodeList nodes = getBusinessPayload().getElementsByTagNameNS(
				HubConstants.NS, HubConstants.SCHEMA_DEFINITION_ELEMENT);

		if (nodes.getLength() == 1) {
			return (Element) nodes.item(0);
		} else {
			logger
					.warn("Grid message does not contain schema definition element.");
			return null;
		}
	}

	/**
	 * This method gets the business payload form the caxchange message
	 * 
	 * @param
	 * @return null
	 * @throws
	 */
	protected Element getBusinessPayload() {
		NodeList nodes = getRequest().getElementsByTagNameNS(HubConstants.NS,
				HubConstants.REQUEST_PAYLOAD_ELEMENT);

		if (nodes.getLength() == 1) {
			return (Element) nodes.item(0);
		} else {
			logger.warn("Grid message does not contain request element.");
			return null;
		}
	}

	/**
	 * This method gets the caxchange request message
	 * 
	 * @param
	 * @return null
	 * @throws
	 */
	protected Element getRequest() {
		NodeList nodes = message.getElementsByTagNameNS(HubConstants.NS,
				HubConstants.REQUEST_ELEMENT);
		if (nodes.getLength() == 1) {
			return (Element) nodes.item(0);
		} else {
			logger.warn("Grid message does not contain request element.");
			return null;
		}
	}

	public String getExternalIdentifier() {
		NodeList nodes = getMetaData().getElementsByTagNameNS(HubConstants.NS,
				HubConstants.EXTERNAL_IDENTIFIER);
		if (nodes.getLength() == 1) {
			return ((Element) nodes.item(0)).getTextContent();
		}
		return "";
	}

	public String getCaxchangeIdentifier() {
		NodeList nodes = getMetaData().getElementsByTagNameNS(HubConstants.NS,
				HubConstants.CAXCHANGE_IDENTIFIER);
		if (nodes.getLength() == 1) {
			return ((Element) nodes.item(0)).getTextContent();
		}
		return "";
	}

	public String getOperationName() {
		NodeList nodes = getMetaData().getElementsByTagNameNS(HubConstants.NS,
				HubConstants.OPERATION_NAME_ELEMENT);
		if (nodes.getLength() == 1) {
			return ((Element) nodes.item(0)).getTextContent();
		}
		return "";
	}

	public String getServiceType() {
		NodeList nodes = getMetaData().getElementsByTagNameNS(HubConstants.NS,
				HubConstants.SERVICE_TYPE_ELEMENT);
		if (nodes.getLength() == 1) {
			return ((Element) nodes.item(0)).getTextContent();
		}
		return "";
	}
}
