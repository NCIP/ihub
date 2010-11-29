package gov.nih.nci.ihub.test;

public class TestConstants {
	
	public static final String DELEGATED_CREDENTIAL_REF_NUMBER = "12083";
	public static final String CERT_FILE_LOCATION = "C:/projects_svn/caBIGIntegrationHub/common/resources/ihub/ncias-c278-v.nci.nih.gov-cert.pem";
	public static final String KEY_FILE_LOCATION = "C:/projects_svn/caBIGIntegrationHub/common/resources/ihub/ncias-c278-v.nci.nih.gov-key.pem";
	public static final String HOST_IDENTITY = "/O=caBIG/OU=caGrid/OU=Stage LOA1/OU=Dorian/CN=dev2_ld_user";
	public static final int CREDENTIAL_EXPIRATION_SECONDS = 600;
	
	public static final String DELEGATION_CREDENTIAL_REFERENCE_XML = "<ns1:DelegatedCredentialReference xmlns:ns1=\"http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential/types\">      <ns2:EndpointReference xsi:type=\"ns2:EndpointReferenceType\" xmlns:ns2=\"http://schemas.xmlsoap.org/ws/2004/03/addressing\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">       <ns2:Address xsi:type=\"ns2:AttributedURI\">https://cagrid-cds-stage.nci.nih.gov:8443/wsrf/services/cagrid/DelegatedCredential</ns2:Address>       <ns2:ReferenceProperties xsi:type=\"ns2:ReferencePropertiesType\">        <ns2:DelegatedCredentialKey xmlns:ns2=\"http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential\">         <ns3:delegationId xmlns:ns3=\"http://gaards.cagrid.org/cds\">"+DELEGATED_CREDENTIAL_REF_NUMBER+"</ns3:delegationId>        </ns2:DelegatedCredentialKey>       </ns2:ReferenceProperties>       <ns2:ReferenceParameters xsi:type=\"ns2:ReferenceParametersType\"/></ns2:EndpointReference></ns1:DelegatedCredentialReference>";
	
	public static final String REGISTER_SUBJECT_PAYLOAD = "<ns1:businessMessagePayload xmlns:ns1=\"http://caXchange.nci.nih.gov/messaging\"><ns1:xmlSchemaDefinition>gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain</ns1:xmlSchemaDefinition><registration xmlns=\"gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain\" xmlns:c3pr=\"gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" gridId=\"74703559-2c2f-4bcd-8f01-3f8898e8fbea\"><studySite><healthcareSite><name>Duke University Medical Center</name><address><city>Durham</city><stateCode>NC</stateCode><countryCode>USA</countryCode></address><nciInstituteCode>NC010</nciInstituteCode></healthcareSite></studySite><studyRef><identifier xsi:type=\"OrganizationAssignedIdentifierType\"><type>Coordinating Center Identifier</type><value>SMOKE_TEST_5</value><primaryIndicator>true</primaryIndicator><healthcareSite><name>Duke University Medical Center</name><address><city>Durham</city><stateCode>NC</stateCode><countryCode>USA</countryCode></address><nciInstituteCode>NC010</nciInstituteCode></healthcareSite></identifier><identifier xsi:type=\"OrganizationAssignedIdentifierType\"><type>Protocol Authority Identifier</type><value>SMOKE_TEST_5</value><primaryIndicator>false</primaryIndicator><healthcareSite><name>Cancer Therapy Evaluation Program</name><address><streetAddress>9000 Rockville Pike</streetAddress><city>Bethesda</city><stateCode>MD</stateCode><postalCode>20892</postalCode><countryCode>USA</countryCode></address><nciInstituteCode>CTEP</nciInstituteCode></healthcareSite></identifier></studyRef><participant gridId=\"9a18bcfa-1711-44d0-bede-7545e1d9a3d9\"><firstName>Garth</firstName><lastName>Brooks</lastName><address/><administrativeGenderCode>Male</administrativeGenderCode><birthDate>1965-09-08</birthDate><ethnicGroupCode>Non Hispanic or Latino</ethnicGroupCode><raceCode>Black_or_African_American</raceCode><identifier xsi:type=\"OrganizationAssignedIdentifierType\"><type>MRN</type><value>GARTH-001</value><primaryIndicator>true</primaryIndicator><healthcareSite><name>Duke University Medical Center</name><address><city>Durham</city><stateCode>NC</stateCode><countryCode>USA</countryCode></address><nciInstituteCode>NC010</nciInstituteCode></healthcareSite></identifier><identifier xsi:type=\"SystemAssignedIdentifierType\"><type>SUBJECT_IDENTIFIER</type><value>G-53dc814e-08c0-4c36-a5dc-71d09172cb29</value><primaryIndicator>false</primaryIndicator><systemName>C3PR</systemName></identifier></participant><startDate>2010-08-11</startDate><informedConsentFormSignedDate>2010-08-11</informedConsentFormSignedDate><informedConsentVersion>general</informedConsentVersion><identifier xsi:type=\"SystemAssignedIdentifierType\"><type>Study Subject Identifier</type><value>G-1a13e380-27ca-48f3-aa76-7365bc5f2f3d</value><primaryIndicator>false</primaryIndicator><systemName>C3PR</systemName></identifier><identifier xsi:type=\"OrganizationAssignedIdentifierType\"><type>COORDINATING_CENTER_ASSIGNED_STUDY_SUBJECT_IDENTIFIER</type><value>6</value><primaryIndicator>false</primaryIndicator><healthcareSite><name>Duke University Medical Center</name><address><city>Durham</city><stateCode>NC</stateCode><countryCode>USA</countryCode></address><nciInstituteCode>NC010</nciInstituteCode></healthcareSite></identifier><scheduledEpoch gridId=\"c902dbc4-9883-473b-bedb-aa6b70bed793\" xsi:type=\"ScheduledTreatmentEpochType\"><startDate>2010-08-11</startDate><epoch gridId=\"c902dbc4-9883-473b-bedb-aa6b70bed793\" xsi:type=\"TreatmentEpochType\"><name>epoch1</name><descriptionText/><arm gridId=\"200dcd1f-faf2-49de-afb5-840f3c030d33\"><name>Arm A</name><descriptionText/></arm></epoch><scheduledArm><arm gridId=\"200dcd1f-faf2-49de-afb5-840f3c030d33\"><name>Arm A</name><descriptionText/></arm></scheduledArm><eligibilityIndicator>true</eligibilityIndicator></scheduledEpoch></registration></ns1:businessMessagePayload>";
	public static final String REGISTER_SUBJECT_CAAERS_URL = "https://ncias-d282-v.nci.nih.gov:29543/wsrf-caaers/services/cagrid/RegistrationConsumer";

}
