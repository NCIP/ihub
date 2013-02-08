<?xml version="1.0" encoding="UTF-8"?>
<!--
  (c) Regents of the University of California. All Rights Reserved
  2012-Dec-18 Tom Bechtold
 -->
<pattern id="study_subject_identifier"
  xmlns="http://purl.oclc.org/dsdl/schematron">

  <title>CDA study subject identifier: ensures one patient role and one iSpy2 Study subject identifier</title>

  <rule context="/hl7:ClinicalDocument">
    <assert test="count(hl7:recordTarget/hl7:patientRole)=1">Unavailable patient role</assert>
  </rule>
  <rule context="/hl7:ClinicalDocument/hl7:recordTarget/hl7:patientRole">
    <assert test="count(hl7:id[@root][@extension][@assigningAuthorityName='iSpy2 Study'])=1">Unavailable Study subject identifier</assert>
  </rule>
</pattern>
