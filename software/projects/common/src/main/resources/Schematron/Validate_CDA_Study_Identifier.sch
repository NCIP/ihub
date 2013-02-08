<?xml version="1.0" encoding="UTF-8"?>
<!--
  (c) Regents of the University of California. All Rights Reserved
  2012-Dec-18 Tom Bechtold
 -->
<pattern id="study_identifier"
  xmlns="http://purl.oclc.org/dsdl/schematron">

  <title>CDA study identifier</title>

  <rule context="/hl7:ClinicalDocument">
    <assert test="count(hl7:documentationOf/hl7:serviceEvent[@classCode='CLNTRL']/hl7:id[@root='2.16.840.1.113883.3.26.1.7'][@extension]/../hl7:code[@nullFlavor='OTH'][hl7:originalText='clinical trial'])=1">Unavailable study identifier</assert>
  </rule>
</pattern>
