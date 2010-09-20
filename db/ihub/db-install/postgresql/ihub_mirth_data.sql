-- 
-- TABLE: TYPE_IHUB 
--
INSERT INTO TYPE_IHUB (TYPE_ID, NAME, DESCRIPTION) VALUES
	(1, 'Message Status', 'The status of the message at any given time during its processing');
INSERT INTO TYPE_IHUB (TYPE_ID, NAME, DESCRIPTION) VALUES
	(2, 'Service Type', 'Routing to various channels is based on the service type of the message');
INSERT INTO TYPE_IHUB (TYPE_ID, NAME, DESCRIPTION) VALUES
	(3, 'Transactional Control', 'Control that indicates where the transaction should be committed or rolledback');
INSERT INTO TYPE_IHUB (TYPE_ID, NAME, DESCRIPTION) VALUES
	(4, 'Overall Response Status', 'Indicates the overall status of the response message that is sent back to the consumer');
INSERT INTO TYPE_IHUB (TYPE_ID, NAME, DESCRIPTION) VALUES
	(5, 'Target Response Status', 'Indicates the target response status of each target that is invoked for the service type');
INSERT INTO TYPE_IHUB (TYPE_ID, NAME, DESCRIPTION) VALUES
	(6, 'Target Details', 'Details about the target service that is invoked');

	
-- 
-- TABLE: TYPE_CODE_IHUB 
--
-- message status
INSERT INTO TYPE_CODE_IHUB (CODE, DESCRIPTION, TYPE_ID) VALUES
	('OPEN', 'Message is open and hasn not been picked up for processing', 1);
INSERT INTO TYPE_CODE_IHUB (CODE, DESCRIPTION, TYPE_ID) VALUES
	('IN_PROCESS', 'The message is currently being processed', 1);
INSERT INTO TYPE_CODE_IHUB (CODE, DESCRIPTION, TYPE_ID) VALUES
	('PROCESSED', 'Message processing is complete', 1);
INSERT INTO TYPE_CODE_IHUB (CODE, DESCRIPTION, TYPE_ID) VALUES
	('RESPONSE_RETURNED', 'Response Message sent back to the consumer', 1);

	
-- service type
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('SCHEDULE_MODIFICATION', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('LAB_BASED_AE', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('LOAD_LAB_TO_CDMS', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('CT_LAB_DATA', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('STUDY_CREATION', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('PERSON', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('ORGANIZATION', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('CLINICAL_RESEARCH_STAFF', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('HEALTH_CARE_FACILITY', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('HEALTH_CARE_PROVIDER', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('IDENTIFIED_ORGANIZATION', 2);	
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('IDENTIFIED_PERSON', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('ORGANIZATIONAL_CONTACT', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('RESEARCH_ORGANIZATION', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('OVERSIGHT_COMMITTEE', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('STUDY_PROTOCOL', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('ARM', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('STUDY_SITE', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('PLANNED_ACTIVITY', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('STUDY_CONTACT', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('STUDY_DISEASE', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('STUDY_OVERALL_STATUS', 2);	
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('STUDY_SITE_CONTACT', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('STUDY_RELATIONSHIP', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('STUDY_RESOURCING', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('STUDY_IND_IDE', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('STUDY_ONHOLD', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('STUDY_OUTCOME_MEASURE', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('STUDY_RECRUITMENT_STATUS', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('STUDY_REGULATORY_AUTHORITY', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('STUDY_SITE_ACCRUAL_STATUS', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('DOCUMENT', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('DOCUMENT_WORKFLOW_STATUS', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('ORGANIZATION_BUSINESS_SERVICE', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('PERSON_BUSINESS_SERVICE', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('PO_BUSINESS', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('REGISTER_SUBJECT', 2);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('CATISSUE', 2);

--transactional control
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('PROCESS', 3);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('COMMIT', 3);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('ROLLBACK', 3);
	
--overall response status
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('SUCCESS', 4);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('FAILURE', 4);
	
--target response status
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('SUCCESSFUL', 5);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('FAULT', 5);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('TIMEOUT', 5);
	
--target details
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('caAERS', 6);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('PSC', 6);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('CTOM', 6);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('C3PR', 6);
INSERT INTO TYPE_CODE_IHUB (CODE, TYPE_ID) VALUES
	('DELEGATION', 6);



-- 
-- TABLE: MESSAGE 
--
INSERT INTO MESSAGE (MESSAGE_ID, METADATA, PAYLOAD, MESSAGE_STATUS)
	VALUES ('1', '<ns1:metadata>
  <ns1:transactionControl xsi:nil="true" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"/>
  <ns1:serviceType>REGISTER_SUBJECT</ns1:serviceType>
  <ns1:externalIdentifier>myExternalIdentifier</ns1:externalIdentifier>
  <ns1:caXchangeIdentifier>39bfd390-abd9-11df-b8de-bb2928d28ab7</ns1:caXchangeIdentifier>
  <ns1:credentials>
   <ns1:userName>cctsdev1</ns1:userName>
   <ns1:gridIdentifier>/O=caBIG/OU=caGrid/OU=Stage LOA1/OU=Dorian/CN=cctsdev1</ns1:gridIdentifier>
   <ns1:password>An010101!!</ns1:password>
   <ns1:delegatedCredentialReference>&lt;ns1:DelegatedCredentialReference xmlns:ns1=&quot;http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential/types&quot;&gt;
 &lt;ns2:EndpointReference xsi:type=&quot;ns2:EndpointReferenceType&quot; xmlns:ns2=&quot;http://schemas.xmlsoap.org/ws/2004/03/addressing&quot; xmlns:xsi=&quot;http://www.w3.org/2001/XMLSchema-instance&quot;&gt;
  &lt;ns2:Address xsi:type=&quot;ns2:AttributedURI&quot;&gt;https://cagrid-cds-stage.nci.nih.gov:8443/wsrf/services/cagrid/DelegatedCredential&lt;/ns2:Address&gt;
  &lt;ns2:ReferenceProperties xsi:type=&quot;ns2:ReferencePropertiesType&quot;&gt;
   &lt;ns2:DelegatedCredentialKey xmlns:ns2=&quot;http://cds.gaards.cagrid.org/CredentialDelegationService/DelegatedCredential&quot;&gt;
    &lt;ns3:delegationId xmlns:ns3=&quot;http://gaards.cagrid.org/cds&quot;&gt;7772&lt;/ns3:delegationId&gt;
   &lt;/ns2:DelegatedCredentialKey&gt;
  &lt;/ns2:ReferenceProperties&gt;
  &lt;ns2:ReferenceParameters xsi:type=&quot;ns2:ReferenceParametersType&quot;/&gt;
 &lt;/ns2:EndpointReference&gt;
&lt;/ns1:DelegatedCredentialReference&gt;
</ns1:delegatedCredentialReference>
  </ns1:credentials>
 </ns1:metadata>', '<registration xmlns=gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain gridId=6115c43c-851e-425c-8312-fd78367aaef3>
    <studySite gridId=9a147f4c-3a94-4883-a9b0-1926dda426be>
     <healthcareSite gridId=NCI GRID ID>
      <nciInstituteCode>NCI</nciInstituteCode>
     </healthcareSite>
     <irbApprovalDate>2008-08-25</irbApprovalDate>
     <roleCode>Affiliate Site</roleCode>
     <startDate>2008-08-25</startDate>
    </studySite>
    <studyRef gridId=51bd374c-d8b5-4759-84b3-ac6259d58648>
     <shortTitleText>Smoke Test</shortTitleText>
     <longTitleText>Smoke Test long title</longTitleText>
     <identifier xmlns:xsi=http://www.w3.org/2001/XMLSchema-instance  xsi:type=OrganizationAssignedIdentifierType>
      <type1>Coordinating Center Identifier</type1>
      <value>SMOKE_TEST</value>
      <primaryIndicator>true</primaryIndicator>
      <healthcareSite gridId=NCI GRID ID>
       <nciInstituteCode>NCI</nciInstituteCode>
      </healthcareSite>
     </identifier>
    </studyRef>
    <participant gridId=91dd4580-801b-4874-adeb-a174361bacea>
     <firstName>John</firstName>
     <lastName>Smith</lastName>
     <address/>
     <administrativeGenderCode>Male</administrativeGenderCode>
     <birthDate>1970-01-02</birthDate>
     <ethnicGroupCode>Non Hispanic or Latino</ethnicGroupCode>
     <raceCode>White</raceCode>
     <identifier xmlns:xsi=http://www.w3.org/2001/XMLSchema-instance xsi:type=OrganizationAssignedIdentifierType>
      <type>MRN</type>
      <value>00-00-00-0</value>
      <primaryIndicator>true</primaryIndicator>
      <healthcareSite gridId=NCI GRID ID>
       <nciInstituteCode>NCI</nciInstituteCode>
      </healthcareSite>
     </identifier>
    </participant>
    <startDate>2008-08-25</startDate>
    <informedConsentFormSignedDate>2008-01-01</informedConsentFormSignedDate>
    <informedConsentVersion>01/07/2008</informedConsentVersion>
    <regDataEntryStatus>COMPLETE</regDataEntryStatus>
    <regWorkflowStatus>REGISTERED</regWorkflowStatus>
    <scheduledEpoch xmlns:xsi=http://www.w3.org/2001/XMLSchema-instance gridId=bd77eed4-f1c6-4a58-98c8-3abe3e3c204e xsi:type=ScheduledTreatmentEpochType>
     <startDate>2008-08-25</startDate>
     <epoch gridId=73b061cf-e4cc-42ec-b7a3-9b8f39ac84ef xsi:type=TreatmentEpochType>
      <name>Treatment</name>
      <descriptionText/>
     </epoch>
     <scheduledArm>
      <arm gridId=6d3a4394-3e9e-449f-860b-039aa5e1a93e>
       <name>Arm A</name>
       <descriptionText/>
      </arm>
     </scheduledArm>
     <eligibilityIndicator>true</eligibilityIndicator>
    </scheduledEpoch>
    <stratumGroup>0:Male</stratumGroup>
   </registration>','OPEN'); 
   

   	
   
   UPDATE MESSAGE SET PAYLOAD='
      <registration gridId=@74703559-2c2f-4bcd-8f01-3f8898e8fbea@ xmlns=@gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain@ xmlns:xsi=@http://www.w3.org/2001/XMLSchema-instance@ xmlns:c3pr=@gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain@>   <studySite>      <healthcareSite>         <name>Duke University Medical Center</name>         <address>            <city>Durham</city>            <stateCode>NC</stateCode>            <countryCode>USA</countryCode>         </address>         <nciInstituteCode>NC010</nciInstituteCode>      </healthcareSite>   </studySite>   <studyRef>      <identifier xsi:type=@OrganizationAssignedIdentifierType@>         <type>Coordinating Center Identifier</type>         <value>SMOKE_TEST_5</value>         <primaryIndicator>true</primaryIndicator>         <healthcareSite>            <name>Duke University Medical Center</name>            <address>               <city>Durham</city>               <stateCode>NC</stateCode>               <countryCode>USA</countryCode>            </address>            <nciInstituteCode>NC010</nciInstituteCode>         </healthcareSite>      </identifier>      <identifier xsi:type=@OrganizationAssignedIdentifierType@>         <type>Protocol Authority Identifier</type>         <value>SMOKE_TEST_5</value>         <primaryIndicator>false</primaryIndicator>         <healthcareSite>            <name>Cancer Therapy Evaluation Program</name>            <address>               <streetAddress>9000 Rockville Pike</streetAddress>               <city>Bethesda</city>               <stateCode>MD</stateCode>               <postalCode>20892</postalCode>               <countryCode>USA</countryCode>            </address>            <nciInstituteCode>CTEP</nciInstituteCode>         </healthcareSite>      </identifier>   </studyRef>   <participant gridId=@9a18bcfa-1711-44d0-bede-7545e1d9a3d9@>      <firstName>Garth</firstName>      <lastName>Brooks</lastName>      <address/>      <administrativeGenderCode>Male</administrativeGenderCode>      <birthDate>1965-09-08</birthDate>      <ethnicGroupCode>Non Hispanic or Latino</ethnicGroupCode>      <raceCode>Black_or_African_American</raceCode>      <identifier xsi:type=@OrganizationAssignedIdentifierType@>         <type>MRN</type>         <value>GARTH-001</value>         <primaryIndicator>true</primaryIndicator>         <healthcareSite>            <name>Duke University Medical Center</name>            <address>               <city>Durham</city>               <stateCode>NC</stateCode>               <countryCode>USA</countryCode>            </address>            <nciInstituteCode>NC010</nciInstituteCode>         </healthcareSite>      </identifier>      <identifier xsi:type=@SystemAssignedIdentifierType@>         <type>SUBJECT_IDENTIFIER</type>         <value>G-53dc814e-08c0-4c36-a5dc-71d09172cb29</value>         <primaryIndicator>false</primaryIndicator>         <systemName>C3PR</systemName>      </identifier>   </participant>   <startDate>2010-08-11</startDate>   <informedConsentFormSignedDate>2010-08-11</informedConsentFormSignedDate>   <informedConsentVersion>general</informedConsentVersion>   <identifier xsi:type=@SystemAssignedIdentifierType@>      <type>Study Subject Identifier</type>      <value>G-1a13e380-27ca-48f3-aa76-7365bc5f2f3d</value>      <primaryIndicator>false</primaryIndicator>      <systemName>C3PR</systemName>   </identifier>   <identifier xsi:type=@OrganizationAssignedIdentifierType@>      <type>COORDINATING_CENTER_ASSIGNED_STUDY_SUBJECT_IDENTIFIER</type>      <value>6</value>      <primaryIndicator>false</primaryIndicator>      <healthcareSite>         <name>Duke University Medical Center</name>         <address>            <city>Durham</city>            <stateCode>NC</stateCode>            <countryCode>USA</countryCode>         </address>         <nciInstituteCode>NC010</nciInstituteCode>      </healthcareSite>   </identifier>   <scheduledEpoch xsi:type=@ScheduledTreatmentEpochType@ gridId=@c902dbc4-9883-473b-bedb-aa6b70bed793@>      <startDate>2010-08-11</startDate>      <epoch xsi:type=@TreatmentEpochType@ gridId=@c902dbc4-9883-473b-bedb-aa6b70bed793@>         <name>epoch1</name>         <descriptionText/>         <arm gridId=@200dcd1f-faf2-49de-afb5-840f3c030d33@>            <name>Arm A</name>            <descriptionText/>         </arm>      </epoch>      <scheduledArm>         <arm gridId=@200dcd1f-faf2-49de-afb5-840f3c030d33@>            <name>Arm A</name>            <descriptionText/>         </arm>      </scheduledArm>      <eligibilityIndicator>true</eligibilityIndicator>   </scheduledEpoch></registration>' 
   	WHERE MESSAGE_ID='1';
   	
   	
   	
   INSERT INTO COMPILED_TARGET_RESPONSE (MESSAGE_ID, TARGET_DETAILS, COMPILED_RESPONSE, TARGET_RESPONSE_STATUS, TIME_COMPLETED)
    VALUES('1', 'caAERS', '<?xml version="1.0" encoding="UTF-8"?><ns1:registration xmlns:ns1="gme://ccts.cabig/1.0/gov.nih.nci.cabig.ccts.domain" gridId="74703559-2c2f-4bcd-8f01-3f8898e8fbea">
    <ns1:studySite>
     <ns1:healthcareSite>
      <ns1:name>Duke University Medical Center</ns1:name>
      <ns1:address>
       <ns1:city>Durham</ns1:city>
       <ns1:stateCode>NC</ns1:stateCode>
       <ns1:countryCode>USA</ns1:countryCode>
      </ns1:address>
      <ns1:nciInstituteCode>NC010</ns1:nciInstituteCode>
     </ns1:healthcareSite>
    </ns1:studySite>
    <ns1:studyRef>
     <ns1:identifier xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ns1:OrganizationAssignedIdentifierType">
      <ns1:type>Coordinating Center Identifier</ns1:type>
      <ns1:value>SMOKE_TEST_5</ns1:value>
      <ns1:primaryIndicator>true</ns1:primaryIndicator>
      <ns1:healthcareSite>
       <ns1:name>Duke University Medical Center</ns1:name>
       <ns1:address>
        <ns1:city>Durham</ns1:city>
        <ns1:stateCode>NC</ns1:stateCode>
        <ns1:countryCode>USA</ns1:countryCode>
       </ns1:address>
       <ns1:nciInstituteCode>NC010</ns1:nciInstituteCode>
      </ns1:healthcareSite>
     </ns1:identifier>
     <ns1:identifier xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ns1:OrganizationAssignedIdentifierType">
      <ns1:type>Protocol Authority Identifier</ns1:type>
      <ns1:value>SMOKE_TEST_5</ns1:value>
      <ns1:primaryIndicator>false</ns1:primaryIndicator>
      <ns1:healthcareSite>
       <ns1:name>Cancer Therapy Evaluation Program</ns1:name>
       <ns1:address>
        <ns1:streetAddress>9000 Rockville Pike</ns1:streetAddress>
        <ns1:city>Bethesda</ns1:city>
        <ns1:stateCode>MD</ns1:stateCode>
        <ns1:postalCode>20892</ns1:postalCode>
        <ns1:countryCode>USA</ns1:countryCode>
       </ns1:address>
       <ns1:nciInstituteCode>CTEP</ns1:nciInstituteCode>
      </ns1:healthcareSite>
     </ns1:identifier>
    </ns1:studyRef>
    <ns1:participant gridId="9a18bcfa-1711-44d0-bede-7545e1d9a3d9">
     <ns1:firstName>Garth</ns1:firstName>
     <ns1:lastName>Brooks</ns1:lastName>
     <ns1:address/>
     <ns1:administrativeGenderCode>Male</ns1:administrativeGenderCode>
     <ns1:birthDate>1965-09-08</ns1:birthDate>
     <ns1:ethnicGroupCode>Non Hispanic or Latino</ns1:ethnicGroupCode>
     <ns1:raceCode>Black_or_African_American</ns1:raceCode>
     <ns1:identifier xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ns1:OrganizationAssignedIdentifierType">
      <ns1:type>MRN</ns1:type>
      <ns1:value>GARTH-001</ns1:value>
      <ns1:primaryIndicator>true</ns1:primaryIndicator>
      <ns1:healthcareSite>
       <ns1:name>Duke University Medical Center</ns1:name>
       <ns1:address>
        <ns1:city>Durham</ns1:city>
        <ns1:stateCode>NC</ns1:stateCode>
        <ns1:countryCode>USA</ns1:countryCode>
       </ns1:address>
       <ns1:nciInstituteCode>NC010</ns1:nciInstituteCode>
      </ns1:healthcareSite>
     </ns1:identifier>
     <ns1:identifier xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ns1:SystemAssignedIdentifierType">
      <ns1:type>SUBJECT_IDENTIFIER</ns1:type>
      <ns1:value>G-53dc814e-08c0-4c36-a5dc-71d09172cb29</ns1:value>
      <ns1:primaryIndicator>false</ns1:primaryIndicator>
      <ns1:systemName>C3PR</ns1:systemName>
     </ns1:identifier>
    </ns1:participant>
    <ns1:startDate>2010-08-11</ns1:startDate>
    <ns1:informedConsentFormSignedDate>2010-08-11</ns1:informedConsentFormSignedDate>
    <ns1:informedConsentVersion>general</ns1:informedConsentVersion>
    <ns1:identifier xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ns1:SystemAssignedIdentifierType">
     <ns1:type>Study Subject Identifier</ns1:type>
     <ns1:value>G-1a13e380-27ca-48f3-aa76-7365bc5f2f3d</ns1:value>
     <ns1:primaryIndicator>false</ns1:primaryIndicator>
     <ns1:systemName>C3PR</ns1:systemName>
    </ns1:identifier>
    <ns1:identifier xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:type="ns1:OrganizationAssignedIdentifierType">
     <ns1:type>COORDINATING_CENTER_ASSIGNED_STUDY_SUBJECT_IDENTIFIER</ns1:type>
     <ns1:value>6</ns1:value>
     <ns1:primaryIndicator>false</ns1:primaryIndicator>
     <ns1:healthcareSite>
      <ns1:name>Duke University Medical Center</ns1:name>
      <ns1:address>
       <ns1:city>Durham</ns1:city>
       <ns1:stateCode>NC</ns1:stateCode>
       <ns1:countryCode>USA</ns1:countryCode>
      </ns1:address>
      <ns1:nciInstituteCode>NC010</ns1:nciInstituteCode>
     </ns1:healthcareSite>
    </ns1:identifier>
    <ns1:scheduledEpoch xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" gridId="c902dbc4-9883-473b-bedb-aa6b70bed793" xsi:type="ns1:ScheduledTreatmentEpochType">
     <ns1:startDate>2010-08-11</ns1:startDate>
     <ns1:epoch gridId="c902dbc4-9883-473b-bedb-aa6b70bed793" xsi:type="ns1:TreatmentEpochType">
      <ns1:name>epoch1</ns1:name>
      <ns1:descriptionText/>
      <ns1:arm gridId="200dcd1f-faf2-49de-afb5-840f3c030d33">
       <ns1:name>Arm A</ns1:name>
       <ns1:descriptionText/>
      </ns1:arm>
     </ns1:epoch>
     <ns1:scheduledArm>
      <ns1:arm gridId="200dcd1f-faf2-49de-afb5-840f3c030d33">
       <ns1:name>Arm A</ns1:name>
       <ns1:descriptionText/>
      </ns1:arm>
     </ns1:scheduledArm>
     <ns1:eligibilityIndicator>true</ns1:eligibilityIndicator>
    </ns1:scheduledEpoch>
   </ns1:registration>
', 'SUCCESSFUL', now());
