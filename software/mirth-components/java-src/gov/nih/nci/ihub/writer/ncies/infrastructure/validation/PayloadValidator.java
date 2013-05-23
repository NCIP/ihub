/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.writer.ncies.infrastructure.validation;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.servicemix.jbi.jaxp.SourceTransformer;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 * Validates the payload against a given schema.
 * 
 * @author marwahah
 * 
 */
public class PayloadValidator {
	private Logger logger = LogManager.getLogger(PayloadValidator.class);
	private CaxchangeSchemaFactory caxchangeSchemaFactory = null;

	public void init() {
		logger.info("Payload validator initialized.");
	}

	/**
	 * Validates the payload against the schema.
	 * 
	 * @param payload
	 * @param schema
	 */
	public void validatePayload(Node payload, String namespaceName,
			String serviceType) throws PayloadValidationException {
		ValidatingErrorHandler validatingErrorHandler = new ValidatingErrorHandler();
		DOMSource source = new DOMSource(payload);
		try {
			if (logger.isDebugEnabled()) {
				try {
					SourceTransformer stransformer = new SourceTransformer();
					logger.debug("Payload:" + stransformer.toString(source));
				} catch (TransformerException te) {
				}
			}
			Schema schema = null;
			if (caxchangeSchemaFactory == null) {
				throw new PayloadValidationException(
						"Schema factory not initialized for payload validation.");
			}
			if (namespaceName != null) {
				schema = caxchangeSchemaFactory.getSchema(serviceType,
						namespaceName);
			} else {
				throw new PayloadValidationException(
						"Namespace not configured for this service type:"
								+ serviceType
								+ ". Please configure the namespace to get the validating schema.");
			}
			if (schema != null) {
				long timeBefore = new java.util.Date().getTime();
				SAXParserFactory parserFactory = null;
				String propertyValue = System
						.getProperty("javax.xml.parsers.SAXParserFactory");
				logger.debug("Parser factory:" + propertyValue);
				System
						.setProperty("javax.xml.parsers.SAXParserFactory",
								"com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
				parserFactory = SAXParserFactory.newInstance();
				logger.debug("Parser Factory."
						+ parserFactory.getClass().getName());
				parserFactory.setValidating(false);
				parserFactory.setNamespaceAware(true);
				parserFactory.setSchema(schema);
				SAXParser parser = parserFactory.newSAXParser();
				logger.debug("validating payload."
						+ parser.getClass().getName());
				SourceTransformer st = new SourceTransformer();

				parser.parse(st.toStreamSource(source).getInputStream(),
						validatingErrorHandler);
				
				if(propertyValue == null){
					System.clearProperty("javax.xml.parsers.SAXParserFactory");
				}
//				System.setProperty("javax.xml.parsers.SAXParserFactory",
//						propertyValue);
				long timeAfter = new java.util.Date().getTime();
				logger.debug("Time for validation:" + (timeAfter - timeBefore));
			} else {
				throw new PayloadValidationException(
						"Schema not found for namespace:" + namespaceName
								+ "  and service type:" + serviceType);
			}
		} catch (SAXException e1) {
			throw new PayloadValidationException(
					"SAX Exception validating payload." + e1.getMessage(), e1);
		} catch (IOException e2) {
			throw new PayloadValidationException(
					"IO Exception validating payload." + e2.getMessage(), e2);
		} catch (ParserConfigurationException pce) {
			throw new PayloadValidationException(
					"ParserConfigurationException validating payload."
							+ pce.getMessage(), pce);
		} catch (TransformerException te) {
			throw new PayloadValidationException(
					"TransformerException validating payload."
							+ te.getMessage(), te);
		} catch (SchemaFactoryException sfe) {
			throw new PayloadValidationException(sfe.getMessage(), sfe);
		}
		PayloadValidationException pve = validatingErrorHandler
				.getPayloadValidationException();
		if (pve != null) {
			throw pve;
		}
	}

	public CaxchangeSchemaFactory getCaxchangeSchemaFactory() {
		return caxchangeSchemaFactory;
	}

	public void setCaxchangeSchemaFactory(
			CaxchangeSchemaFactory caxchangeSchemaFactory) {
		this.caxchangeSchemaFactory = caxchangeSchemaFactory;
	}

}
