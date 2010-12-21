package gov.nih.nci.ihub.test;

import static org.junit.Assert.fail;
import gov.nih.nci.ihub.util.IntegrationHubUtil;
import gov.nih.nci.ihub.writer.ncies.common.GenerateResponseBean;
import gov.nih.nci.ihub.writer.ncies.common.GridInvocationResult;
import gov.nih.nci.ihub.writer.ncies.core.CoppaInvocationStrategy;
import gov.nih.nci.ihub.writer.ncies.core.CoppaPBInvocationStrategy;
import gov.nih.nci.ihub.writer.ncies.exception.GridInvocationException;
import gov.nih.nci.ihub.writer.ncies.infrastructure.InvokeDelegationServiceBean;

import javax.security.auth.Subject;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CoppaPBInvocationStrategyTest {

	@Test
	public void testInvokeGridService() {

		InvokeDelegationServiceBean delegationServiceBean = new InvokeDelegationServiceBean(
				TestConstants.HOST_IDENTITY,
				TestConstants.DELEGATION_CREDENTIAL_REFERENCE_XML,
				TestConstants.CERT_FILE_LOCATION,
				TestConstants.KEY_FILE_LOCATION,
				TestConstants.CREDENTIAL_EXPIRATION_SECONDS);
		try {
			Subject subject = delegationServiceBean.invokeDelegationService();
			System.out.println("SUBJECT: " + subject.toString());

			CoppaPBInvocationStrategy coppaPBInvocationStrategy = new CoppaPBInvocationStrategy();
			coppaPBInvocationStrategy.setServiceUrl(TestConstants.IDENTIFIED_PERSON_URL);
			coppaPBInvocationStrategy
			.setGridClientClassName("gov.nih.nci.coppa.services.structuralroles.identifiedperson.client.IdentifiedPersonClient");
			
			CoppaInvocationStrategy identifiedPersonInvocationStrategy = new CoppaInvocationStrategy();
			identifiedPersonInvocationStrategy.setServiceUrl(TestConstants.IDENTIFIED_PERSON_URL);
			identifiedPersonInvocationStrategy
			.setGridClientClassName("gov.nih.nci.coppa.services.structuralroles.identifiedperson.client.IdentifiedPersonClient");
			
			CoppaInvocationStrategy personInvocationStrategy = new CoppaInvocationStrategy();
			personInvocationStrategy.setServiceUrl(TestConstants.PERSON_URL);
			personInvocationStrategy.setGridClientClassName("gov.nih.nci.coppa.services.entities.person.client.PersonClient");
			
			coppaPBInvocationStrategy.setIdentifiedPersonInvocationStrategy(identifiedPersonInvocationStrategy);
			coppaPBInvocationStrategy.setPersonInvocationStrategy(personInvocationStrategy);
			
			CoppaPBInvocationStrategy channelCoppaPBInvocationStrategy = new CoppaPBInvocationStrategy();
			channelCoppaPBInvocationStrategy.copyCommonContents(coppaPBInvocationStrategy);
			channelCoppaPBInvocationStrategy.init();
			
			
			
			channelCoppaPBInvocationStrategy.setStrategySpecificVariables("something", "PERSON_BUSINESS_SERVICE", IntegrationHubUtil.stringToDOMDocument(TestConstants.PERSON_BUSINESS_SERVICE_PAYLOAD).getDocumentElement(), subject, "PERSON_BUSINESS_SERVICE");
			
			GridInvocationResult personBusinessServiceInvocationResult = channelCoppaPBInvocationStrategy.invokeGridService(false);
			
			System.out.println("Person Business Service Response: "
					+ IntegrationHubUtil
							.xmlToString(personBusinessServiceInvocationResult
									.getResult()));
			
			GenerateResponseBean generateResponseBean = new GenerateResponseBean();
			Document gridInvocationResultDocument = generateResponseBean.createOutputDocument("PERSON_BUSINESS_SERVICE", "something",
					"gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain", personBusinessServiceInvocationResult);
			
			String successResponseXML = IntegrationHubUtil.xmlToString(gridInvocationResultDocument);			
			System.out.println(" Response: "+successResponseXML);
			
			
			/*
			String identifiedPersonXslString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" xmlns:po=\"http://po.coppa.nci.nih.gov\" xmlns:ISO=\"uri:iso.org:21090\"><xsl:output method=\"xml\" version=\"1.0\" encoding=\"UTF-8\" indent=\"yes\"/><xsl:template match=\"po:Id\" ><IdentifiedPerson xmlns=\"http://po.coppa.nci.nih.gov\" xmlns:ISO=\"uri:iso.org:21090\"> <identifier nullFlavor=\"NI\"/> <playerIdentifier nullFlavor=\"NI\"/> <scoperIdentifier nullFlavor=\"NI\"/> <assignedId><xsl:attribute name=\"root\" ><xsl:apply-templates select=\"@root\" /></xsl:attribute><xsl:attribute name=\"extension\" ><xsl:apply-templates select=\"@extension\" /></xsl:attribute> </assignedId><status code=\"active\"/> </IdentifiedPerson> </xsl:template> <xsl:template match=\"*\"><xsl:copy><xsl:apply-templates select=\"*|@*|text()\" /></xsl:copy></xsl:template></xsl:stylesheet>";
			IntegrationHubUtil iHubUtil = new IntegrationHubUtil();
			Element identifiedPersonPayload = iHubUtil
					.transformXML(
							identifiedPersonXslString,
							IntegrationHubUtil
									.stringToDOMDocument(TestConstants.PERSON_BUSINESS_SERVICE_PAYLOAD));

			System.out.println("Identified Person Transformed XML: "
					+ IntegrationHubUtil.xmlToString(identifiedPersonPayload));
			CoppaInvocationStrategy coppaPBInvocationStrategy = new CoppaInvocationStrategy();
			coppaPBInvocationStrategy
					.setServiceUrl(TestConstants.IDENTIFIED_PERSON_URL);
			coppaPBInvocationStrategy
					.setGridClientClassName("gov.nih.nci.coppa.services.structuralroles.identifiedperson.client.IdentifiedPersonClient");
			coppaPBInvocationStrategy.init();
			coppaPBInvocationStrategy.setStrategySpecificVariables("search",
					"IDENTIFIED_PERSON", identifiedPersonPayload, subject,
					"IDENTIFIED_PERSON");

			// IdentifiedPersonClient identifiedPersonClient = new
			// IdentifiedPersonClient("");
			// identifiedPersonClient.
			GridInvocationResult identifiedInvocationResult = coppaPBInvocationStrategy
					.invokeGridService(false);
			System.out.println("Identified Person Response: "
					+ IntegrationHubUtil.xmlToString(identifiedInvocationResult
							.getResult()));

			String personIdCriteriaXslString = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xsl:stylesheet version=\"1.0\" xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" xmlns:po=\"http://po.coppa.nci.nih.gov\" xmlns:cax=\"http://caXchange.nci.nih.gov/messaging\" xmlns:ISO=\"uri:iso.org:21090\"><xsl:output method=\"xml\" version=\"1.0\" encoding=\"UTF-8\" indent=\"yes\"/><xsl:template match=\"po:playerIdentifier\" ><Id xmlns=\"http://po.coppa.nci.nih.gov\" xmlns:ISO=\"uri:iso.org:21090\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"> <xsl:attribute name=\"root\" ><xsl:apply-templates select=\"@root\" /></xsl:attribute><xsl:attribute name=\"extension\" ><xsl:apply-templates select=\"@extension\" /></xsl:attribute> </Id> </xsl:template></xsl:stylesheet>";
			if (!identifiedInvocationResult.isFault()) {
				System.out.println("Identified Result Doc Type: "
						+ identifiedInvocationResult.getResult()
								.getOwnerDocument().getElementsByTagNameNS(
										"http://po.coppa.nci.nih.gov",
										"playerIdentifier").getLength());
				if (identifiedInvocationResult.getResult().getOwnerDocument()
						.getElementsByTagNameNS("http://po.coppa.nci.nih.gov",
								"playerIdentifier").getLength() == 0) {
					throw new GridInvocationException(
							"CTEP Identifier not found");
				} else {
					Element personPayload = iHubUtil
							.transformXML(
									personIdCriteriaXslString,identifiedInvocationResult.getResult());

					System.out.println("Person Transformed XML: "
							+ IntegrationHubUtil.xmlToString(personPayload));
					CoppaInvocationStrategy coppaPersonInvocationStrategy = new CoppaInvocationStrategy();
					coppaPersonInvocationStrategy
							.setServiceUrl(TestConstants.PERSON_URL);
					coppaPersonInvocationStrategy
							.setGridClientClassName("gov.nih.nci.coppa.services.entities.person.client.PersonClient");
					coppaPersonInvocationStrategy.init();					
					
					System.out.println("PERSON ELEMENT "+IntegrationHubUtil.convertPayloadIntoBusinessPayload(personPayload));
					
					coppaPersonInvocationStrategy.setStrategySpecificVariables(
							"getById", "PERSON", IntegrationHubUtil.convertPayloadIntoBusinessPayload(personPayload),
							subject, "PERSON");
				
					GridInvocationResult personInvocationResult = coppaPersonInvocationStrategy
							.invokeGridService(false);
					System.out.println("Person Response: "
							+ IntegrationHubUtil
									.xmlToString(personInvocationResult
											.getResult()));
				}
			}
*/
		} catch (GridInvocationException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.getMessage());
		}

	}

}
