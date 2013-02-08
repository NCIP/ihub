<?xml version="1.0" encoding="UTF-8"?>
<!--
  (c) Regents of the University of California. All Rights Reserved
  2013-Jan-22 Tom Bechtold
-->
<pattern id="activity_status"
	xmlns="http://purl.oclc.org/dsdl/schematron">
	<title>CDA Body: actitity status</title>

  <rule context="/">
    <assert test="count(//hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:id[@nullFlavor='NI']][hl7:code[@code='263490005'][@codeSystem='2.16.840.1.113883.6.96']])!=0">Unavailable activity status observation</assert>
  </rule>

  <rule context="//hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:id[@nullFlavor='NI']][hl7:code[@code='263490005'][@codeSystem='2.16.840.1.113883.6.96']]">
    <!-- Activity status value within value set -->
    <assert test="count(hl7:value[@xsi:type='CD'][@codeSystem='2.16.840.1.113883.6.96'])=1">Unavailable activity status value</assert>
    <assert test="hl7:value[@xsi:type='CD'][@codeSystem='2.16.840.1.113883.6.96'][matches(@code, '(^55561003$)|(^73425007$)')]">Invalid activity status value</assert>
  </rule>
</pattern>
