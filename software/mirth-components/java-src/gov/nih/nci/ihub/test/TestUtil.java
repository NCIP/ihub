/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.test;

import gov.nih.nci.ihub.util.IntegrationHubUtil;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TestUtil {

	private static String businessMessagePayload = "<ns1:businessMessagePayload xmlns:ns1=@http://caXchange.nci.nih.gov/messaging@><ns1:xmlSchemaDefinition>gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain</ns1:xmlSchemaDefinition><registration xmlns=@gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain@ xmlns:ns2=@gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain@ xmlns:xsi=@http://www.w3.org/2001/XMLSchema-instance@ gridId=@6115c43c-851e-425c-8312-fd78367aaef3@ xsi:schemaLocation=@gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain C:/apps/caxchange/apache-servicemix-3.3.1/conf/gme_cache/ccts.cabig-1.0_gov.nih.nci.cabig.ccts.domain.xsd@><studySite gridId=@9a147f4c-3a94-4883-a9b0-1926dda426be@><healthcareSite gridId=@NCI GRID ID@><nciInstituteCode>NCI</nciInstituteCode></healthcareSite><irbApprovalDate>2008-08-25</irbApprovalDate><roleCode>Affiliate Site</roleCode><startDate>2008-08-25</startDate></studySite><studyRef gridId=@51bd374c-d8b5-4759-84b3-ac6259d58648@><shortTitleText>Smoke Test</shortTitleText><longTitleText>Smoke Test long title</longTitleText><identifier xsi:type=@ns2:OrganizationAssignedIdentifierType@><type>Coordinating Center Identifier</type><value>SMOKE_TEST</value><primaryIndicator>true</primaryIndicator><healthcareSite gridId=@NCI GRID ID@><nciInstituteCode>NCI</nciInstituteCode></healthcareSite></identifier></studyRef><participant gridId=@91dd4580-801b-4874-adeb-a174361bacea@><firstName>John</firstName><lastName>Smith</lastName><administrativeGenderCode>Male</administrativeGenderCode><birthDate>1970-01-02</birthDate><ethnicGroupCode>Non Hispanic or Latino</ethnicGroupCode><raceCode>White</raceCode><identifier xsi:type=@ns2:OrganizationAssignedIdentifierType@><type>MRN</type><value>00-00-00-0</value><primaryIndicator>true</primaryIndicator><healthcareSite gridId=@NCI GRID ID@><nciInstituteCode>NCI</nciInstituteCode></healthcareSite></identifier></participant><startDate>2008-08-25</startDate><informedConsentFormSignedDate>2008-01-01</informedConsentFormSignedDate><informedConsentVersion>01/07/2008</informedConsentVersion><identifier xsi:type=@ns2:OrganizationAssignedIdentifierType@><type>COORDINATING_CENTER_ASSIGNED_STUDY_SUBJECT_IDENTIFIER</type><value>c1</value><primaryIndicator>false</primaryIndicator><healthcareSite><name>National CI</name><address><streetAddress>200 First Street SW</streetAddress><city>Rochester</city><stateCode>MN</stateCode><postalCode>55905</postalCode><countryCode>USA</countryCode></address><nciInstituteCode>MD017</nciInstituteCode></healthcareSite></identifier><regDataEntryStatus>COMPLETE</regDataEntryStatus><regWorkflowStatus>REGISTERED</regWorkflowStatus><scheduledEpoch gridId=@bd77eed4-f1c6-4a58-98c8-3abe3e3c204e@ xsi:type=@ns2:ScheduledTreatmentEpochType@><startDate>2008-08-25</startDate><epoch gridId=@73b061cf-e4cc-42ec-b7a3-9b8f39ac84ef@ xsi:type=@ns2:TreatmentEpochType@><name>Treatment</name></epoch><scheduledArm><arm gridId=@6d3a4394-3e9e-449f-860b-039aa5e1a93e@><name>Arm A</name><descriptionText/></arm></scheduledArm><eligibilityIndicator>true</eligibilityIndicator></scheduledEpoch><stratumGroup>0:Male</stratumGroup></registration></ns1:businessMessagePayload>";

	public static String stripXMLTags(String xmlToStrip) {
		String strippedXML = null;
		try {
			XPath xpath = XPathFactory.newInstance().newXPath();

			Node xmlToStripNode = IntegrationHubUtil
					.stringToDOMDocument(xmlToStrip);
			XPathExpression expression = xpath
					.compile("/businessMessagePayload");
			NodeList nodes = (NodeList) expression.evaluate(xmlToStripNode,
					XPathConstants.NODESET);
			strippedXML = IntegrationHubUtil.xmlToString(nodes.item(0).getLastChild());
			System.out.println("Stripped XML: " + strippedXML);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strippedXML;
	}

	public static void main(String args[]) {
		stripXMLTags(businessMessagePayload.replace('@', '"'));
	}

}
