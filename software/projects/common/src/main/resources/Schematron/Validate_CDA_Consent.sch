<?xml version="1.0" encoding="UTF-8"?>
<schema xmlns="http://purl.oclc.org/dsdl/schematron"
	schemaVersion="1.0">

  <title>iHUB_Data_Exchange_CDA_Mapping Consent Data</title>

  <p>Validates a 2TRANSCEND Consent event message</p>
  <p>2013-Jan-25 Tom Bechtold</p>
  <p>(c) Regents of the University of California. All Rights Reserved</p>

  <ns prefix="hl7" uri="urn:hl7-org:v3" />
  <ns prefix="xsi" uri="http://www.w3.org/2001/XMLSchema-instance" />

  <include href="Validate_CDA_Study_Subject_Identifier.sch" />
  <include href="Validate_CDA_Biospecimen_Id_Timepoint.sch" />

  <pattern id="consent">
    <title>CDA body (consent): Tier 1 response, Tier2 response</title>
    <rule context="/hl7:ClinicalDocument">
      <assert test="hl7:component/hl7:structuredBody/hl7:component/hl7:section">Unavailable consent section(s)</assert>
    </rule>

    <rule context="/hl7:ClinicalDocument/hl7:component/hl7:structuredBody/hl7:component/hl7:section">
      <assert test="count(hl7:entry[@typeCode='DRIV']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@nullFlavor='OTH'][hl7:originalText='Study Calendar Event Time Point']]/hl7:entryRelationship[@typeCode='SUBJ']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@code='123038009'][@codeSystem='2.16.840.1.113883.6.96']]/hl7:specimen/hl7:specimenRole/hl7:id)=1">Unavailable CDMS Specimen ID</assert>

      <assert test="count(hl7:entry[@typeCode='DRIV']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@code='309370004'][@codeSystem='2.16.840.1.113883.6.96'][hl7:originalText='Tier 1 consent response']])=1">Unavailable Tier 1 consent response</assert>
      <assert test="hl7:entry[@typeCode='DRIV']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@code='309370004'][@codeSystem='2.16.840.1.113883.6.96'][hl7:originalText='Tier 1 consent response']]/hl7:effectiveTime[@value]">Unavailable Tier 1 consent date</assert>
      <assert test="hl7:entry[@typeCode='DRIV']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@code='309370004'][@codeSystem='2.16.840.1.113883.6.96'][hl7:originalText='Tier 1 consent response']]/hl7:effectiveTime[matches(@value,'^[0-9]{1,8}|([0-9]{9,14}|[0-9]{14,14}\.[0-9]+)([+\-][0-9]{1,4})?$')]">Invalid Tier 1 consent date</assert>
      <assert test="hl7:entry[@typeCode='DRIV']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@code='309370004'][@codeSystem='2.16.840.1.113883.6.96'][hl7:originalText='Tier 1 consent response']]/hl7:value[@xsi:type='CD'][@codeSystem='2.16.840.1.113883.6.96'][matches(@code,'(^373066001$)|(^373067005$)')]">Invalid Tier 1 consent</assert>

      <assert test="count(hl7:entry[@typeCode='DRIV']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@code='309370004'][@codeSystem='2.16.840.1.113883.6.96'][hl7:originalText='Tier 2 consent response']])=1">Unavailable Tier 2 consent response</assert>
      <assert test="hl7:entry[@typeCode='DRIV']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@code='309370004'][@codeSystem='2.16.840.1.113883.6.96'][hl7:originalText='Tier 2 consent response']]/hl7:effectiveTime[@value]">Unavailable Tier 2 consent date</assert>
      <assert test="hl7:entry[@typeCode='DRIV']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@code='309370004'][@codeSystem='2.16.840.1.113883.6.96'][hl7:originalText='Tier 2 consent response']]/hl7:effectiveTime[matches(@value,'^[0-9]{1,8}|([0-9]{9,14}|[0-9]{14,14}\.[0-9]+)([+\-][0-9]{1,4})?$')]">Invalid Tier 2 consent date</assert>
      <assert test="hl7:entry[@typeCode='DRIV']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@code='309370004'][@codeSystem='2.16.840.1.113883.6.96'][hl7:originalText='Tier 2 consent response']]/hl7:value[@xsi:type='CD'][@codeSystem='2.16.840.1.113883.6.96'][matches(@code,'(^373066001$)|(^373067005$)')]">Invalid Tier 2 consent</assert>
    </rule>
  </pattern>
</schema>