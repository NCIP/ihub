/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.test;

import static org.junit.Assert.*;
import gov.nih.nci.ihub.util.IntegrationHubUtil;
import gov.nih.nci.ihub.writer.ncies.common.GenerateResponseBean;
import gov.nih.nci.ihub.writer.ncies.common.GridInvocationResult;

import org.junit.Test;
import org.w3c.dom.Document;

public class GenerateResponseBeanTest {

	@Test
	public void testGenerateResponseFromTargetResponse() {
		GenerateResponseBean generateResponseBean = new GenerateResponseBean();
		String compiledTargetResponse = "<targetResponse><targetServiceIdentifier>PO_PERSON_SERVICE</targetServiceIdentifier><targetServiceOperation>search</targetServiceOperation><targetMessageStatus>RESPONSE</targetMessageStatus><targetBusinessMessage><xmlSchemaDefinition>http://po.coppa.nci.nih.gov</xmlSchemaDefinition><soapenc:Array xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\"> <ns1:Person xmlns:ns1=\"http://po.coppa.nci.nih.gov\">  <ns1:identifier displayable=\"true\" extension=\"272810\" identifierName=\"NCI person entity identifier\" reliability=\"ISS\" root=\"2.16.840.1.113883.3.26.4.1\" scope=\"OBJ\"/>  <ns1:name>   <ns2:part xmlns:ns2=\"uri:iso.org:21090\" type=\"FAM\" value=\"Gaasch Smith\"/>   <ns3:part xmlns:ns3=\"uri:iso.org:21090\" type=\"GIV\" value=\"Adriana\"/>   <ns4:part xmlns:ns4=\"uri:iso.org:21090\" type=\"PFX\" value=\"Ms.\"/>  </ns1:name>  <ns1:postalAddress>   <ns5:part xmlns:ns5=\"uri:iso.org:21090\" type=\"AL\" value=\"9500 Gilman Drive\"/>   <ns6:part xmlns:ns6=\"uri:iso.org:21090\" type=\"ADL\" value=\"Clinical Trials Office MC 0698\"/>   <ns7:part xmlns:ns7=\"uri:iso.org:21090\" type=\"CTY\" value=\"La Jolla\"/>   <ns8:part xmlns:ns8=\"uri:iso.org:21090\" type=\"STA\" value=\"CA\"/>   <ns9:part xmlns:ns9=\"uri:iso.org:21090\" type=\"ZIP\" value=\"92037-0698\"/>   <ns10:part xmlns:ns10=\"uri:iso.org:21090\" code=\"USA\" codeSystem=\"ISO 3166-1 alpha-3 code\" type=\"CNT\" value=\"United States\"/>  </ns1:postalAddress>  <ns1:statusCode code=\"active\"/>  <ns1:telecomAddress>   <ns11:item xmlns:ns11=\"uri:iso.org:21090\" value=\"mailto:a8smith@ucsd.edu\"/>   <ns12:item xmlns:ns12=\"uri:iso.org:21090\" value=\"x-text-fax:(858)-657-7025\"/>   <ns13:item xmlns:ns13=\"uri:iso.org:21090\" value=\"tel:(858)-657-7019\"/>  </ns1:telecomAddress>  <ns1:raceCode nullFlavor=\"NI\"/>  <ns1:sexCode nullFlavor=\"NI\"/>  <ns1:ethnicGroupCode nullFlavor=\"NI\"/>  <ns1:birthDate nullFlavor=\"NI\"/> </ns1:Person></soapenc:Array></targetBusinessMessage></targetResponse>";
		Document compiledTargetDocument = IntegrationHubUtil.stringToDOMDocument(compiledTargetResponse);
		System.out.println("XML String: "+IntegrationHubUtil.xmlToString(compiledTargetDocument));
		try {
			System.out.println("Converted String: "+generateResponseBean.generateResponseFromTargetResponse("myExternalIdentifier", "d499943-3394330-3934830-399dddd", compiledTargetDocument));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}		
	}
	
	@Test
	public void testcreateOutputDocument() {
		GenerateResponseBean generateResponseBean = new GenerateResponseBean();
		String targetResponse = "<ns1:registration xmlns:ns1=\"gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain\" gridId=\"74703559-2c2f-4bcd-8f01-3f8898e8fbea\">    <ns1:studySite>     <ns1:healthcareSite>      <ns1:name>Duke University Medical Center</ns1:name>      <ns1:address>       <ns1:city>Durham</ns1:city>       <ns1:stateCode>NC</ns1:stateCode>       <ns1:countryCode>USA</ns1:countryCode>      </ns1:address>      <ns1:nciInstituteCode>NC010</ns1:nciInstituteCode>     </ns1:healthcareSite>    </ns1:studySite>    <ns1:studyRef>     <ns1:identifier xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:OrganizationAssignedIdentifierType\">      <ns1:type>Coordinating Center Identifier</ns1:type>      <ns1:value>SMOKE_TEST_5</ns1:value>      <ns1:primaryIndicator>true</ns1:primaryIndicator>      <ns1:healthcareSite>       <ns1:name>Duke University Medical Center</ns1:name>       <ns1:address>        <ns1:city>Durham</ns1:city>        <ns1:stateCode>NC</ns1:stateCode>        <ns1:countryCode>USA</ns1:countryCode>       </ns1:address>       <ns1:nciInstituteCode>NC010</ns1:nciInstituteCode>      </ns1:healthcareSite>     </ns1:identifier>     <ns1:identifier xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:OrganizationAssignedIdentifierType\">      <ns1:type>Protocol Authority Identifier</ns1:type>      <ns1:value>SMOKE_TEST_5</ns1:value>      <ns1:primaryIndicator>false</ns1:primaryIndicator>      <ns1:healthcareSite>       <ns1:name>Cancer Therapy Evaluation Program</ns1:name>       <ns1:address>        <ns1:streetAddress>9000 Rockville Pike</ns1:streetAddress>        <ns1:city>Bethesda</ns1:city>        <ns1:stateCode>MD</ns1:stateCode>        <ns1:postalCode>20892</ns1:postalCode>        <ns1:countryCode>USA</ns1:countryCode>       </ns1:address>       <ns1:nciInstituteCode>CTEP</ns1:nciInstituteCode>      </ns1:healthcareSite>     </ns1:identifier>    </ns1:studyRef>    <ns1:participant gridId=\"9a18bcfa-1711-44d0-bede-7545e1d9a3d9\">     <ns1:firstName>Garth</ns1:firstName>     <ns1:lastName>Brooks</ns1:lastName>     <ns1:address/>     <ns1:administrativeGenderCode>Male</ns1:administrativeGenderCode>     <ns1:birthDate>1965-09-08</ns1:birthDate>     <ns1:ethnicGroupCode>Non Hispanic or Latino</ns1:ethnicGroupCode>     <ns1:raceCode>Black_or_African_American</ns1:raceCode>     <ns1:identifier xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:OrganizationAssignedIdentifierType\">      <ns1:type>MRN</ns1:type>      <ns1:value>GARTH-001</ns1:value>      <ns1:primaryIndicator>true</ns1:primaryIndicator>      <ns1:healthcareSite>       <ns1:name>Duke University Medical Center</ns1:name>       <ns1:address>        <ns1:city>Durham</ns1:city>        <ns1:stateCode>NC</ns1:stateCode>        <ns1:countryCode>USA</ns1:countryCode>       </ns1:address>       <ns1:nciInstituteCode>NC010</ns1:nciInstituteCode>      </ns1:healthcareSite>     </ns1:identifier>     <ns1:identifier xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:SystemAssignedIdentifierType\">      <ns1:type>SUBJECT_IDENTIFIER</ns1:type>      <ns1:value>G-53dc814e-08c0-4c36-a5dc-71d09172cb29</ns1:value>      <ns1:primaryIndicator>false</ns1:primaryIndicator>      <ns1:systemName>C3PR</ns1:systemName>     </ns1:identifier>    </ns1:participant>    <ns1:startDate>2010-08-11</ns1:startDate>    <ns1:informedConsentFormSignedDate>2010-08-11</ns1:informedConsentFormSignedDate>    <ns1:informedConsentVersion>general</ns1:informedConsentVersion>    <ns1:identifier xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:SystemAssignedIdentifierType\">     <ns1:type>Study Subject Identifier</ns1:type>     <ns1:value>G-1a13e380-27ca-48f3-aa76-7365bc5f2f3d</ns1:value>     <ns1:primaryIndicator>false</ns1:primaryIndicator>     <ns1:systemName>C3PR</ns1:systemName>    </ns1:identifier>    <ns1:identifier xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns1:OrganizationAssignedIdentifierType\">     <ns1:type>COORDINATING_CENTER_ASSIGNED_STUDY_SUBJECT_IDENTIFIER</ns1:type>     <ns1:value>6</ns1:value>     <ns1:primaryIndicator>false</ns1:primaryIndicator>     <ns1:healthcareSite>      <ns1:name>Duke University Medical Center</ns1:name>      <ns1:address>       <ns1:city>Durham</ns1:city>       <ns1:stateCode>NC</ns1:stateCode>       <ns1:countryCode>USA</ns1:countryCode>      </ns1:address>      <ns1:nciInstituteCode>NC010</ns1:nciInstituteCode>     </ns1:healthcareSite>    </ns1:identifier>    <ns1:scheduledEpoch xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" gridId=\"c902dbc4-9883-473b-bedb-aa6b70bed793\" xsi:type=\"ns1:ScheduledTreatmentEpochType\">     <ns1:startDate>2010-08-11</ns1:startDate>     <ns1:epoch gridId=\"c902dbc4-9883-473b-bedb-aa6b70bed793\" xsi:type=\"ns1:TreatmentEpochType\">      <ns1:name>epoch1</ns1:name>      <ns1:descriptionText/>      <ns1:arm gridId=\"200dcd1f-faf2-49de-afb5-840f3c030d33\">       <ns1:name>Arm A</ns1:name>       <ns1:descriptionText/>      </ns1:arm>     </ns1:epoch>     <ns1:scheduledArm>      <ns1:arm gridId=\"200dcd1f-faf2-49de-afb5-840f3c030d33\">       <ns1:name>Arm A</ns1:name>       <ns1:descriptionText/>      </ns1:arm>     </ns1:scheduledArm>     <ns1:eligibilityIndicator>true</ns1:eligibilityIndicator>    </ns1:scheduledEpoch>   </ns1:registration>";
		Document targetResponseDocument = IntegrationHubUtil.stringToDOMDocument(targetResponse);
		GridInvocationResult gridInvocationResult = new GridInvocationResult(false, targetResponseDocument.getChildNodes().item(0), false);
		//System.out.println("XML String: "+IntegrationHubUtil.xmlToString(compiledTargetDocument));
		try {			
			System.out.println("Converted String: "+generateResponseBean.createOutputDocument("caAERS", "", "", gridInvocationResult));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}		
	}

}
