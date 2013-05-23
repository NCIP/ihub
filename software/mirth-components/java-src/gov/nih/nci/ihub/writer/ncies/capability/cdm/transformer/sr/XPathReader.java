/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.writer.ncies.capability.cdm.transformer.sr;

import java.io.StringReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

public class XPathReader {
	private String xml;
	private XPath xpath;
	
	public XPathReader(String xml) {
		this.xml = xml;
		XPathFactory factory = XPathFactory.newInstance();
		xpath = factory.newXPath();
		xpath.setNamespaceContext(new MyNamespaceContext());
	}
	
	public String read(String path) throws XPathExpressionException {
		XPathExpression expression = xpath.compile(path);
		String result = expression.evaluate(new InputSource(new StringReader(xml)));
		return result;
	}
}
