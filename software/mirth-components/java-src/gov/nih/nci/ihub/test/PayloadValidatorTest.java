/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.test;

import static org.junit.Assert.*;

import javax.xml.validation.Schema;

import gov.nih.nci.ihub.util.IntegrationHubUtil;
import gov.nih.nci.ihub.writer.ncies.infrastructure.validation.FileSystemSchemaFactory;
import gov.nih.nci.ihub.writer.ncies.infrastructure.validation.GMESchemaFactory;
import gov.nih.nci.ihub.writer.ncies.infrastructure.validation.PayloadValidationException;
import gov.nih.nci.ihub.writer.ncies.infrastructure.validation.PayloadValidator;
import gov.nih.nci.ihub.writer.ncies.infrastructure.validation.SchemaFactoryException;

import org.junit.Test;

public class PayloadValidatorTest {

	@Test
	public void testValidatePayload() {
		
		FileSystemSchemaFactory fileSystemSchemaFactory = new FileSystemSchemaFactory();
		fileSystemSchemaFactory
				.setSchemaDirectoryLocation("C:/projects_svn/ihub-mirth/trunk/software/common/resources/payloadSchemas");
		fileSystemSchemaFactory.init();
		
		GMESchemaFactory gmeSchemaFactory = new GMESchemaFactory();
		gmeSchemaFactory.setCacheDirectoryLocation("C:/projects_svn/ihub-mirth/trunk/software/common/resources/payloadSchemas");
		gmeSchemaFactory.setGMEGridServiceLocation("https://cagrid-gme-stage.nci.nih.gov:8443/wsrf/services/cagrid/GlobalModelExchange");
		gmeSchemaFactory.init();
		
		try {
//			Schema schema = fileSystemSchemaFactory.getSchema(
//					"REGISTER_SUBJECT",
//					"gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain");
//			Schema schema = gmeSchemaFactory.getSchema(
//					"REGISTER_SUBJECT",
//					"gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain");
//			System.out.println("Schema: " + schema.toString());
			PayloadValidator payloadValidator = new PayloadValidator();
			//payloadValidator.setCaxchangeSchemaFactory(fileSystemSchemaFactory);
			payloadValidator.setCaxchangeSchemaFactory(gmeSchemaFactory);
			System
					.setProperty("javax.xml.parsers.SAXParserFactory",
							"com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
			payloadValidator
					.validatePayload(IntegrationHubUtil.stringToDOMDocument(
							IntegrationHubUtil
									.getPayloadFromBusinessPayload(TestConstants.REGISTER_SUBJECT_PAYLOAD)),
							"gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain",
							"REGISTER_SUBJECT");
		} catch (PayloadValidationException e) {
			fail("Payload Validation Failed: "+e.getMessage());
		}

	}

}
