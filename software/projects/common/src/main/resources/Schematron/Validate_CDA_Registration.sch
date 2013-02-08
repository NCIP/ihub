<?xml version="1.0" encoding="UTF-8"?>
<schema xmlns="http://purl.oclc.org/dsdl/schematron"
	schemaVersion="1.0">

  <title>iHUB_Data_Exchange_CDA_Mapping Registration Data</title>

  <p>Validates a 2TRANSCEND Registration event message</p>
  <p>2013-Jan-22 Tom Bechtold</p>
  <p>(c) Regents of the University of California. All Rights Reserved</p>

  <ns prefix="hl7" uri="urn:hl7-org:v3" />
  <ns prefix="xsi" uri="http://www.w3.org/2001/XMLSchema-instance" />

  <include href="Validate_CDA_Study_Subject_Identifier.sch" />
  <include href="Validate_CDA_Study_Identifier.sch" />
  <include href="Validate_CDA_Activity_Status.sch" />

  <pattern id="registration">
    <title>CDA registration</title>

    <rule context="/hl7:ClinicalDocument">
      <assert test="count(hl7:recordTarget/hl7:patientRole/hl7:patient)=1">Unavailable patient</assert>
      <assert test="count(hl7:documentationOf/hl7:serviceEvent[@classCode='CLNTRL']/hl7:id[@root='2.16.840.1.113883.3.26.1.7'][@extension]/../hl7:code[@nullFlavor='OTH'][hl7:originalText='site-specific component of clinical trial'])=1">Unavailable site identifier</assert>
    </rule>

    <rule context="/hl7:ClinicalDocument/hl7:recordTarget/hl7:patientRole">
      <assert test="count(hl7:id[@root][@extension][not(@assigningAuthorityName='iSpy2 Study')])=1">Unavailable Medical Record Number (MRN)</assert>
    </rule>

    <rule context="/hl7:ClinicalDocument/hl7:recordTarget/hl7:patientRole/hl7:patient">
      <assert test="count(hl7:birthTime)=1">Unavailable birth date</assert>
      <assert test="hl7:birthTime[matches(@value, '^[0-9]{1,8}|([0-9]{9,14}|[0-9]{14,14}\.[0-9]+)([+\-][0-9]{1,4})?$')]">Invalid birth date</assert>
      <assert test="count(hl7:ethnicGroupCode)=1">Unavailable ethnicity</assert>
      <assert test="hl7:ethnicGroupCode[not(@codeSystem and @nullFlavor)]">Conflicting ethnicity</assert>
      <assert test="hl7:ethnicGroupCode[(@codeSystem='2.16.840.1.113883.6.238' and matches(@code, '(^2186-5$)|(^2135-2$)')) or (@nullFlavor='UNK' and hl7:originalText='unknown') or (@nullFlavor='NI' and hl7:originalText='not reported')]">Invalid ethnicity</assert>
      <assert test="count(hl7:administrativeGenderCode)=1">Unavailable gender</assert>
      <assert test="hl7:administrativeGenderCode[not(@codeSystem and @nullFlavor)]">Conflicting gender</assert>
      <assert test="hl7:administrativeGenderCode[(@codeSystem='2.16.840.1.113883.5.1' and matches(@code, '(^F$)|(^M$)')) or (@nullFlavor='UNK' and hl7:originalText='unknown') or (@nullFlavor='NI' and hl7:originalText='not reported')]">Invalid gender</assert>
      <assert test="count(hl7:raceCode)=1">Unavailable race</assert>
      <assert test="hl7:raceCode[not(@codeSystem and @nullFlavor)]">Conflicting race</assert>
      <assert test="hl7:raceCode[(@codeSystem='2.16.840.1.113883.6.238' and matches(@code, '(^2106-3$)|(^2054-5$)|(^1002-5$)|(^2028-9$)|(^2076-8$)')) or (@nullFlavor='UNK' and hl7:originalText='unknown') or (@nullFlavor='NI' and hl7:originalText='not reported')]">Invalid race</assert>
    </rule>

    <rule context="/hl7:ClinicalDocument/hl7:component/hl7:structuredBody/hl7:component/hl7:section[hl7:templateId[@root='2.16.840.1.113883.10.20.22.2.17'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='29762-2'][@codeSystem='2.16.840.1.113883.6.1']]/hl7:entry[@typeCode='DRIV']/hl7:observation[@classCode='OBS'][@moodCode='EVN']/hl7:code[@code='103579009'][@codeSystem='2.16.840.1.113883.6.96']">
      <assert test="count(preceding-sibling::hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2'][@assigningAuthorityName='HL7 CCDA'])=1">Unavailable additional race(s) template</assert>
      <assert test="count(preceding-sibling::hl7:id[@root='0b1f04da-983c-4438-a093-31011a5f9758'])=1">Unavailable additional race(s) id</assert>
      <assert test="count(following-sibling::hl7:value[@xsi:type='CD'][@codeSystem='2.16.840.1.113883.6.238'])=1">Unavailable additional race(s) value</assert>
      <assert test="following-sibling::hl7:value[@xsi:type='CD'][@codeSystem='2.16.840.1.113883.6.238'][matches(@code, '(^2106-3$)|(^2054-5$)|(^1002-5$)|(^2028-9$)|(^2076-8$)')]">Invalid additional race(s) value</assert>
    </rule>
  </pattern>
</schema>