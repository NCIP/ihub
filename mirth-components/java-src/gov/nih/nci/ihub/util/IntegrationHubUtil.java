package gov.nih.nci.ihub.util;

import gov.nih.nci.cagrid.common.Utils;
import gov.nih.nci.ihub.reader.cxf.ResponseMessage;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.apache.servicemix.jbi.jaxp.SourceTransformer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import gov.nih.nci.ihub.reader.cxf.Message;

public class IntegrationHubUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(IntegrationHubUtil.class);

	/**
	 * Returns the string representation of a given org.w3c.dom.Node object
	 * (usually an instance of org.w3c.dom.Element or org.w3c.dom.Document)
	 * 
	 * @param node
	 * @return
	 */
	public static String xmlToString(Node node) {
		try {
			Source source = new DOMSource(node);

			StringWriter stringWriter = new StringWriter();
			Result result = new StreamResult(stringWriter);

			// TODO move the below lines of code to at deployment
			// Properties props = System.getProperties();
			// props.put("javax.xml.transform.TransformerFactory",
			// "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");
			// System.setProperties(props);

			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			transformer.transform(source, result);
			return stringWriter.getBuffer().toString();
		} catch (TransformerConfigurationException e) {
			logger.error(e.getMessage());
		} catch (TransformerException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Converts a given XML string into a DOM Document
	 * 
	 * @param xmlString
	 * @return
	 */
	public static Document stringToDOMDocument(String xmlString) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			//factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(
					xmlString)));
			return document;
		} catch (ParserConfigurationException e) {
			logger.error(e.getMessage());
		} catch (SAXException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	
	
	/**
	 * Converts a given XML string into a DOM Document with Namespace aware
	 * set to true
	 * 
	 * @param xmlString
	 * @return
	 */
	public static Document stringToDOMDocumentNameSpaceAware(String xmlString) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setNamespaceAware(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(
					xmlString)));
			return document;
		} catch (ParserConfigurationException e) {
			logger.error(e.getMessage());
		} catch (SAXException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Returns the consumer sent payload from business payload
	 * 
	 * @param xmlToStrip
	 * @return
	 */
	public static String getPayloadFromBusinessPayload(
			String businessPayloadXMLString) {
		String payloadXMLString = null;
		try {
			XPath xpath = XPathFactory.newInstance().newXPath();
			System.out.println("PROCESS 1");
			Node xmlToStripNode = IntegrationHubUtil
					.stringToDOMDocument(businessPayloadXMLString);
			System.out.println("PROCESS 2: "+xmlToStripNode);
			XPathExpression expression = xpath.compile("/"
					+ HubConstants.REQUEST_PAYLOAD_ELEMENT);
			NodeList nodes = (NodeList) expression.evaluate(xmlToStripNode,
					XPathConstants.NODESET);
			payloadXMLString = IntegrationHubUtil.xmlToString(nodes.item(0)
					.getLastChild());
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return payloadXMLString;
	}

	/**
	 * Transforms a source XML Node using the XSL string
	 * 
	 * @param xslString
	 * @param xmlSourceNode
	 * @return Element
	 */
	public Element transformXML(String xslString, Node xmlSourceNode) {
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			StreamSource identifiedPersonXslSource = new StreamSource(
					new ByteArrayInputStream(xslString.getBytes()));
			Transformer transformer = tFactory
					.newTransformer(identifiedPersonXslSource);
			ByteArrayOutputStream bArrayOutputStream = new ByteArrayOutputStream();

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
					xmlToString(xmlSourceNode).getBytes());
			transformer.transform(new StreamSource(byteArrayInputStream),
					new StreamResult(bArrayOutputStream));
			return stringToDOMDocument(bArrayOutputStream.toString())
					.getDocumentElement();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}

	/**
	 * Converts payload (ex. clinical connector payload) into business message
	 * payload
	 * 
	 * @param payload
	 * @return
	 */
	public static String convertPayloadIntoBusinessPayload(String payload) {
		return "<" + HubConstants.REQUEST_PAYLOAD_ELEMENT + ">" + payload
				+ "</" + HubConstants.REQUEST_PAYLOAD_ELEMENT + ">";
	}
	

	/**
	 * 
	 * @param payload
	 * @return
	 */
	public static Element convertPayloadIntoBusinessPayload(Element payload) {
		try {
			Document output = new SourceTransformer().createDocument();
			Element root = output
					.createElement(HubConstants.REQUEST_PAYLOAD_ELEMENT);
			root.appendChild(output.importNode(payload, true));
			output.appendChild(root);
			return output.getDocumentElement();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return null;
		}
	}
	
	
	/**
	 * 
	 * @param cxfRequestMessage
	 * @return
	 * @throws Exception
	 */
	public static String getStringFromCXFMessage(Message cxfRequestMessage) throws Exception {
        StringWriter stringWriter = new StringWriter();
        QName requestQName =new QName(HubConstants.NS, HubConstants.ROOT_NODE);
        Utils.serializeObject(cxfRequestMessage,requestQName, stringWriter);
        return stringWriter.toString();
    }
	
	
	/**
     * Parse the response text from caXchange.
     * 
     * @return parsed gov.nih.nci.ihub.reader.cxf.ResponseMessage
     * @throws Exception
     */
    public static ResponseMessage getCXFResponseFromString(String responseText) throws Exception {
        Reader reader = new StringReader(responseText);
        ResponseMessage rm = (ResponseMessage) Utils.deserializeObject(reader,
				ResponseMessage.class);
		return rm;
	}

}
