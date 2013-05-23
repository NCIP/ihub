/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.dc;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
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

public class XPathReader {
	private String xml;
	private XPath xpath;
	private Node node;

	public XPathReader(String xml) throws SAXException, IOException, ParserConfigurationException, TransformerException {
		this.xml = xml;
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(new InputSource(new StringReader(xml)));
		node = document.getDocumentElement();
		XPathFactory factory = XPathFactory.newInstance();
		xpath = factory.newXPath();
		xpath.setNamespaceContext(new MyNamespaceContext());
	}
	
	public String read(String path) throws XPathExpressionException {
		XPathExpression expression = xpath.compile(path);
		String result = expression.evaluate(node);
		return result;
	}
	
	public String read(Node node,String path) throws XPathExpressionException {
		XPathExpression expression = xpath.compile(path);
		String result = expression.evaluate(node);
		return result;
	}	

	public NodeList readNodeList(String path) throws XPathExpressionException {
		XPathExpression expression = xpath.compile(path);
		Object object = expression.evaluate(new InputSource(new StringReader(xml)), XPathConstants.NODESET);
		NodeList result = (NodeList)object;
		return result;
    }

	public void test_xpath() throws XPathExpressionException {
		System.out.println(read("/ccts:registration/ccts:startDate"));
		System.out.println(read("/ccts:registration/ccts:informedConsentFormSignedDate"));
	}

    public static void main(String[] args) throws Exception {
    	String xml = "<ns1:registration gridId=\"6115c43c-851e-425c-8312-fd78367aaef3\" xmlns:ns1=\"gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain\">" +
    		"<ns1:startDate>2008-08-25</ns1:startDate>" +
    		"<ns1:informedConsentFormSignedDate>2008-01-01</ns1:informedConsentFormSignedDate>" +
    		"<ns1:informedConsentVersion>01/07/2008</ns1:informedConsentVersion>" +
    		"<ns1:identifier xsi:type=\"ns1:SystemAssignedIdentifierType\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">" +
    		"<ns1:value>177</ns1:value>" +
    		"<ns1:systemName>C3D</ns1:systemName>" +
    		"</ns1:identifier>" +
    		"</ns1:registration>";
    	XPathReader rr = new XPathReader(xml);
    	rr.test_xpath();
    }
}
