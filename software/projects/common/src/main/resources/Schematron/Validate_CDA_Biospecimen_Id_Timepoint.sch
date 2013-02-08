<?xml version="1.0" encoding="UTF-8"?>
<!--
  (c) Regents of the University of California. All Rights Reserved
  2012-Dec-18 Tom Bechtold
-->
<pattern id="biospecimen_id_timepoint"
  xmlns="http://purl.oclc.org/dsdl/schematron">
  <title>CDA body (biospecimen id/timepoint): CDMS Specimen ID, Study Calendar Event Time Point</title>
  <rule context="/hl7:ClinicalDocument">
    <assert test="count(//hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@code='123038009'][@codeSystem='2.16.840.1.113883.6.96']])!=0">Unavailable biospecimen result</assert>
    <assert test="count(//hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@nullFlavor='OTH'][hl7:originalText='Study Calendar Event Time Point']][hl7:value[@xsi:type='PQ'][@value][@unit]])!=0">Unavailable biospecimen Study Calendar Event Time Point</assert>
  </rule>
</pattern>
