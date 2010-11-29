package gov.nih.nci.ihub.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class IntegrationHubUtil {

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
			
			//TODO move the below lines of code to at deployment
			//Properties props = System.getProperties();
		    //props.put("javax.xml.transform.TransformerFactory",
		      //  "com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl");
		    //System.setProperties(props);
		    
			TransformerFactory factory = TransformerFactory.newInstance();			
			Transformer transformer = factory.newTransformer();
			transformer.transform(source, result);
			return stringWriter.getBuffer().toString();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
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
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(
					xmlString)));
			return document;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
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

			Node xmlToStripNode = IntegrationHubUtil
					.stringToDOMDocument(businessPayloadXMLString);
			XPathExpression expression = xpath
					.compile("/businessMessagePayload");
			NodeList nodes = (NodeList) expression.evaluate(xmlToStripNode,
					XPathConstants.NODESET);
			payloadXMLString = IntegrationHubUtil.xmlToString(nodes.item(0)
					.getLastChild());
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return payloadXMLString;
	}

}
