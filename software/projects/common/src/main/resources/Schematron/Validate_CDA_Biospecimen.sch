<?xml version="1.0" encoding="UTF-8"?>
<schema xmlns="http://purl.oclc.org/dsdl/schematron"
	schemaVersion="1.0">

  <title>iHUB_Data_Exchange_CDA_Mapping Biospecimen Data</title>

  <p>Validates a 2TRANSCEND Biospecimen event message</p>
  <p>2013-Jan-22 Tom Bechtold</p>
  <p>(c) Regents of the University of California. All Rights Reserved</p>

  <ns prefix="hl7" uri="urn:hl7-org:v3" />
  <ns prefix="xsi" uri="http://www.w3.org/2001/XMLSchema-instance" />

  <include href="Validate_CDA_Study_Subject_Identifier.sch" />
  <include href="Validate_CDA_Study_Identifier.sch" />
  <include href="Validate_CDA_Activity_Status.sch" />
  <include href="Validate_CDA_Biospecimen_Id_Timepoint.sch" />

  <pattern id="biospecimen">
    <title>CDA body (biospecimen): Available Quantity, Initial Quantity, Barcode, Collection Protocol Short Title, Collection Protocol Title, Specimen Characteristics Side, Specimen Class, Specimen Type, Guidance for Breast Core Biopsy</title>
    <rule context="/hl7:ClinicalDocument">
      <assert test="count(hl7:component/hl7:structuredBody/hl7:component/hl7:section[hl7:templateId[@root='2.16.840.1.113883.10.20.22.2.31'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='59773-2'][@codeSystem='2.16.840.1.113883.6.1']]/hl7:entry[@typeCode='DRIV']/hl7:procedure[@classCode='PROC'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.14'][@assigningAuthorityName='HL7 CCDA']])!=0">Unavailable biospecimen procedure</assert>
      <assert test="count(hl7:component/hl7:structuredBody/hl7:component/hl7:section[hl7:templateId[@root='2.16.840.1.113883.10.20.22.2.31'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='59773-2'][@codeSystem='2.16.840.1.113883.6.1']]/hl7:entry[@typeCode='DRIV']/hl7:procedure[@classCode='PROC'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.14'][@assigningAuthorityName='HL7 CCDA']]/hl7:entryRelationship[@typeCode='COMP']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@code='123038009'][@codeSystem='2.16.840.1.113883.6.96']])!=0">Unavailable biospecimen result</assert>
    </rule>

    <rule context="/hl7:ClinicalDocument/hl7:component/hl7:structuredBody/hl7:component/hl7:section[hl7:templateId[@root='2.16.840.1.113883.10.20.22.2.31'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='59773-2'][@codeSystem='2.16.840.1.113883.6.1']]/hl7:entry[@typeCode='DRIV']/hl7:procedure[@classCode='PROC'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.14'][@assigningAuthorityName='HL7 CCDA']]">
      <assert test="count(hl7:targetSiteCode/hl7:qualifier[hl7:name[@code='272741003'][@codeSystem='2.16.840.1.113883.6.96']][hl7:value[@code][@codeSystem='2.16.840.1.113883.6.96']])=1">Unavailable biospecimen target side</assert>
      <assert test="count(hl7:entryRelationship[@typeCode='COMP']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.13'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='30651-4'][@codeSystem='2.16.840.1.113883.6.1']][hl7:statusCode[@code='completed']][hl7:value[@xsi:type='CD']])=1">Unavailable biospecimen guidance for core needle biopsy</assert>
      <assert test="hl7:entryRelationship[@typeCode='COMP']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.13'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='30651-4'][@codeSystem='2.16.840.1.113883.6.1']][hl7:statusCode[@code='completed']][hl7:value[@xsi:type='CD'][(@codeSystem='2.16.840.1.113883.6.96' and matches(@code,'(^16310003$)|(^241615005$)|(^258172002$)|(^71651007$)|(^113011001$)')) or (@nullFlavor='OTH')]]">Invalid biospecimen guidance for core needle biopsy</assert>
      <assert test="count(hl7:code)=1">Unavailable biospecimen collection protocol short title</assert>
      <assert test="hl7:code[@nullFlavor='OTH'][hl7:originalText]">Unavailable biospecimen collection protocol titles</assert>
    </rule>

    <rule context="hl7:component/hl7:structuredBody/hl7:component/hl7:section[hl7:templateId[@root='2.16.840.1.113883.10.20.22.2.31'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='59773-2'][@codeSystem='2.16.840.1.113883.6.1']]/hl7:entry[@typeCode='DRIV']/hl7:procedure[@classCode='PROC'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.14'][@assigningAuthorityName='HL7 CCDA']]/hl7:entryRelationship[@typeCode='COMP']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@code='123038009'][@codeSystem='2.16.840.1.113883.6.96']]">
      <assert test="count(hl7:entryRelationship[@typeCode='SUBJ']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@nullFlavor='OTH'][hl7:originalText='Available Quantity']][hl7:value[@xsi:type='PQ'][@value][@unit]])=1">Unavailable biospecimen Available Quantity</assert>
      <assert test="count(hl7:entryRelationship[@typeCode='SUBJ']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@nullFlavor='OTH'][hl7:originalText='Initial Quantity']][hl7:value[@xsi:type='PQ'][@value][@unit]])=1">Unavailable biospecimen Initial Quantity</assert>
      <assert test="count(hl7:participant[@typeCode='DEV']/hl7:participantRole[@classCode='MANU'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.37']][hl7:id[@extension]])=1">Unavailable biospecimen barcode</assert>
      <assert test="count(hl7:specimen/hl7:specimenRole/hl7:specimenPlayingEntity/hl7:code)=1">Unavailable biospecimen class</assert>
      <assert test="count(hl7:specimen/hl7:specimenRole/hl7:specimenPlayingEntity/hl7:code[@codeSystem='2.16.840.1.113883.6.96'][matches(@code,'(^48469005$)|(^258442002$)|(^258562007$)|(^119376003$)')])=1">Invalid biospecimen class</assert>
      <assert test="count(hl7:entryRelationship[@typeCode='SUBJ']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='371439000'][@codeSystem='2.16.840.1.113883.6.96']][hl7:value[@xsi:type='CD'][@codeSystem='2.16.840.1.113883.6.96']])=1">Unavailable biospecimen type</assert>
      <assert test="hl7:entryRelationship[@typeCode='SUBJ']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='371439000'][@codeSystem='2.16.840.1.113883.6.96']][hl7:value[@xsi:type='CD'][@codeSystem='2.16.840.1.113883.6.96'][matches(@code,'(^119376003$)|(^441479001$)|(^119373006$)|(^119341000$)|(^309051001$)|(^119359002$)|(^258450006$)|(^119339001$)|(^258459007$)|(^440674008$)|(^119321005$)|(^122571007$)|(^119361006$)|(^119342007$)|(^119364003$)|(^119334006$)|(^122569007$)|(^119332005$)|(^122575003$)|(^258438000$)|(^258580003$)|(^48469005$)|(^258661006$)|(^258566005$)|(^441673008$)|(^448789008$)|(^258562007$)|(^88878007)$')]]">Invalid biospecimen type</assert>
    </rule>
  </pattern>
</schema>