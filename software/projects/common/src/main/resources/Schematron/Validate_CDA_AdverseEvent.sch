<?xml version="1.0" encoding="UTF-8"?>
<schema xmlns="http://purl.oclc.org/dsdl/schematron"
	schemaVersion="1.0">

  <title>iHUB_Data_Exchange_CDA_Mapping Adverse Event Data</title>

  <p>Validates a 2TRANSCEND Adverse Event message</p>
  <p>2013-Jan-29 Tom Bechtold</p>
  <p>(c) Regents of the University of California. All Rights Reserved</p>

  <ns prefix="hl7" uri="urn:hl7-org:v3" />
  <ns prefix="xsi" uri="http://www.w3.org/2001/XMLSchema-instance" />

  <include href="Validate_CDA_Study_Subject_Identifier.sch" />
  <include href="Validate_CDA_Study_Identifier.sch" />

  <pattern id="adverseevent">
    <title>CDA body (adverse event): Adverse Event unique identifier, Onset Date, Attribution, Coded Term, Verbatim, Grade, Serious Reason(s)</title>
    <rule context="/hl7:ClinicalDocument">
      <assert test="count(hl7:component/hl7:structuredBody/hl7:component/hl7:section[hl7:templateId[@root='2.16.840.1.113883.10.20.22.2.6.1']][hl7:code[@code='48765-2'][@codeSystem='2.16.840.1.113883.6.1']]/hl7:entry[@typeCode='DRIV']/hl7:act[@classCode='ACT'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.30']][hl7:code[@code='48765-2'][@codeSystem='2.16.840.1.113883.6.1']][hl7:statusCode[@code='active']]/hl7:entryRelationship[@typeCode='SUBJ']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.7']][hl7:code[@code='ASSERTION'][@codeSystem='2.16.840.1.113883.5.4']][hl7:statusCode[@code='completed']])!=0">Unavailable adverse event(s)</assert>
      <assert test="count(distinct-values(hl7:component/hl7:structuredBody/hl7:component/hl7:section[hl7:templateId[@root='2.16.840.1.113883.10.20.22.2.6.1']][hl7:code[@code='48765-2'][@codeSystem='2.16.840.1.113883.6.1']]/hl7:entry[@typeCode='DRIV']/hl7:act[@classCode='ACT'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.30']][hl7:code[@code='48765-2'][@codeSystem='2.16.840.1.113883.6.1']][hl7:statusCode[@code='active']]/hl7:entryRelationship[@typeCode='SUBJ']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.7']][hl7:code[@code='ASSERTION'][@codeSystem='2.16.840.1.113883.5.4']][hl7:statusCode[@code='completed']]/hl7:entryRelationship[@typeCode='REFR'][@inversionInd='true']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.13']][hl7:code[@code='225334002'][@codeSystem='2.16.840.1.113883.6.96']]/hl7:value[@xsi:type='CD'][@nullFlavor='OTH']/hl7:originalText)) lt 2">Inconsistent assigned treatments</assert>
    </rule>

    <rule context="/hl7:ClinicalDocument/hl7:component/hl7:structuredBody/hl7:component/hl7:section[hl7:templateId[@root='2.16.840.1.113883.10.20.22.2.6.1']][hl7:code[@code='48765-2'][@codeSystem='2.16.840.1.113883.6.1']]/hl7:entry[@typeCode='DRIV']/hl7:act[@classCode='ACT'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.30']][hl7:code[@code='48765-2'][@codeSystem='2.16.840.1.113883.6.1']][hl7:statusCode[@code='active']]/hl7:entryRelationship[@typeCode='SUBJ']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.7']][hl7:code[@code='ASSERTION'][@codeSystem='2.16.840.1.113883.5.4']][hl7:statusCode[@code='completed']]">
      <assert test="count(hl7:id[@root])=1">Unavailable unique identifier</assert>

      <assert test="count(hl7:effectiveTime/hl7:low[@value])=1">Unavailable onset date</assert>
      <assert test="hl7:effectiveTime/hl7:low[matches(@value,'^(19|20)\d\d(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$')]">Invalid onset date/time</assert>

      <assert test="count(hl7:effectiveTime/hl7:high[@value])=1">Unavailable resolution date</assert>
      <assert test="hl7:effectiveTime/hl7:high[matches(@value,'^(19|20)\d\d(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$')]">Invalid resolution date/time</assert>

      <assert test="count(hl7:entryRelationship[@typeCode='MFST']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.9']][hl7:code[@code='ASSERTION'][@codeSystem='2.16.840.1.113883.5.4']][hl7:statusCode[@code='completed']])=1">Unavailable reaction observation</assert>
      <assert test="hl7:entryRelationship[@typeCode='MFST']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.9']][hl7:code[@code='ASSERTION'][@codeSystem='2.16.840.1.113883.5.4']][hl7:statusCode[@code='completed']]/hl7:value[@xsi:type='CD']">Unavailable adverse event coded term</assert>
      <assert test="hl7:entryRelationship[@typeCode='MFST']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.9']][hl7:code[@code='ASSERTION'][@codeSystem='2.16.840.1.113883.5.4']][hl7:statusCode[@code='completed']]/hl7:value[@xsi:type='CD']/hl7:originalText">Unavailable adverse event verbatim</assert>

      <assert test="count(hl7:entryRelationship[@typeCode='MFST']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.9']][hl7:code[@code='ASSERTION'][@codeSystem='2.16.840.1.113883.5.4']][hl7:statusCode[@code='completed']]/hl7:entryRelationship[@typeCode='SUBJ'][@inversionInd='true']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.8']][hl7:code[@code='SEV'][@codeSystem='2.16.840.1.113883.5.4']][hl7:statusCode[@code='completed']])=1">Unavailable severity observation</assert>
      <assert test="hl7:entryRelationship[@typeCode='MFST']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.9']][hl7:code[@code='ASSERTION'][@codeSystem='2.16.840.1.113883.5.4']][hl7:statusCode[@code='completed']]/hl7:entryRelationship[@typeCode='SUBJ'][@inversionInd='true']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.8']][hl7:code[@code='SEV'][@codeSystem='2.16.840.1.113883.5.4']][hl7:statusCode[@code='completed']]/hl7:value[@xsi:type='CD'][@nullFlavor='OTH']/hl7:originalText">Unavailable adverse event grade</assert>
      <assert test="hl7:entryRelationship[@typeCode='MFST']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.9']][hl7:code[@code='ASSERTION'][@codeSystem='2.16.840.1.113883.5.4']][hl7:statusCode[@code='completed']]/hl7:entryRelationship[@typeCode='SUBJ'][@inversionInd='true']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.8']][hl7:code[@code='SEV'][@codeSystem='2.16.840.1.113883.5.4']][hl7:statusCode[@code='completed']]/hl7:value[@xsi:type='CD'][@nullFlavor='OTH'][matches(hl7:originalText,'^CTCAE Grade [1-5]$')]">Invalid adverse event grade</assert>

      <assert test="count(hl7:entryRelationship[@typeCode='REFR'][@inversionInd='true']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.13']][hl7:code[@code='225334002'][@codeSystem='2.16.840.1.113883.6.96']]/hl7:value[@xsi:type='CD'][@nullFlavor='OTH']/hl7:originalText)=1">Unavailable assigned treatment</assert>

      <assert test="count(hl7:entryRelationship[@typeCode='REFR'][@inversionInd='true']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.13']]/hl7:entryRelationship[@typeCode='SUBJ']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@nullFlavor='OTH'][hl7:originalText='Attribution']])=1">Unavailable attribution</assert>
      <assert test="hl7:entryRelationship[@typeCode='REFR'][@inversionInd='true']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.13']]/hl7:entryRelationship[@typeCode='SUBJ']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@nullFlavor='OTH'][hl7:originalText='Attribution']]/hl7:value[@xsi:type='CD'][(@codeSystem='2.16.840.1.113883.6.96' and matches(@code,'(^371930009$)|(^255545003$)')) or (@nullFlavor='OTH' and matches(hl7:originalText,'(^Unrelated$)|(^Unlikely$)|(^Probable$)'))]">Invalid attribution</assert>

      <assert test="count(hl7:entryRelationship[@typeCode='SUBJ']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@nullFlavor='OTH'][hl7:originalText='serious reason(s)']])&gt;=1">Unavailable serious reason(s)</assert>
      <assert test="hl7:entryRelationship[@typeCode='SUBJ']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@nullFlavor='OTH'][hl7:originalText='serious reason(s)']]/hl7:value[@xsi:type='CD'][(@codeSystem='2.16.840.1.113883.6.96' and matches(@code,'(^405535005$)|(^440181000$)|(^308540004$)|(^405532008$)|(^66091009$)')) or (@nullFlavor='OTH' and hl7:originalText)]">Invalid serious reason(s)</assert>
    </rule>

  </pattern>
</schema>