<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:schold="http://www.ascc.net/xml/schematron"
                xmlns:iso="http://purl.oclc.org/dsdl/schematron"
                xmlns:xhtml="http://www.w3.org/1999/xhtml"
                xmlns:xs="http://www.w3.org/2001/XMLSchema"
                xmlns:hl7="urn:hl7-org:v3"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                version="1.0"><!--Implementers: please note that overriding process-prolog or process-root is 
    the preferred method for meta-stylesheets to use where possible. -->
<xsl:param name="archiveDirParameter"/>
   <xsl:param name="archiveNameParameter"/>
   <xsl:param name="fileNameParameter"/>
   <xsl:param name="fileDirParameter"/>
   <xsl:variable name="document-uri">
      <xsl:value-of select="document-uri(/)"/>
   </xsl:variable>

   <!--PHASES-->


<!--PROLOG-->
<xsl:output xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
               method="xml"
               omit-xml-declaration="no"
               standalone="yes"
               indent="yes"/>

   <!--XSD TYPES FOR XSLT2-->


<!--KEYS AND FUNCTIONS-->


<!--DEFAULT RULES-->


<!--MODE: SCHEMATRON-SELECT-FULL-PATH-->
<!--This mode can be used to generate an ugly though full XPath for locators-->
<xsl:template match="*" mode="schematron-select-full-path">
      <xsl:apply-templates select="." mode="schematron-get-full-path"/>
   </xsl:template>

   <!--MODE: SCHEMATRON-FULL-PATH-->
<!--This mode can be used to generate an ugly though full XPath for locators-->
<xsl:template match="*" mode="schematron-get-full-path">
      <xsl:apply-templates select="parent::*" mode="schematron-get-full-path"/>
      <xsl:text>/</xsl:text>
      <xsl:choose>
         <xsl:when test="namespace-uri()=''">
            <xsl:value-of select="name()"/>
            <xsl:variable name="p_1"
                          select="1+    count(preceding-sibling::*[name()=name(current())])"/>
            <xsl:if test="$p_1&gt;1 or following-sibling::*[name()=name(current())]">[<xsl:value-of select="$p_1"/>]</xsl:if>
         </xsl:when>
         <xsl:otherwise>
            <xsl:text>*[local-name()='</xsl:text>
            <xsl:value-of select="local-name()"/>
            <xsl:text>']</xsl:text>
            <xsl:variable name="p_2"
                          select="1+   count(preceding-sibling::*[local-name()=local-name(current())])"/>
            <xsl:if test="$p_2&gt;1 or following-sibling::*[local-name()=local-name(current())]">[<xsl:value-of select="$p_2"/>]</xsl:if>
         </xsl:otherwise>
      </xsl:choose>
   </xsl:template>
   <xsl:template match="@*" mode="schematron-get-full-path">
      <xsl:text>/</xsl:text>
      <xsl:choose>
         <xsl:when test="namespace-uri()=''">@<xsl:value-of select="name()"/>
         </xsl:when>
         <xsl:otherwise>
            <xsl:text>@*[local-name()='</xsl:text>
            <xsl:value-of select="local-name()"/>
            <xsl:text>' and namespace-uri()='</xsl:text>
            <xsl:value-of select="namespace-uri()"/>
            <xsl:text>']</xsl:text>
         </xsl:otherwise>
      </xsl:choose>
   </xsl:template>

   <!--MODE: SCHEMATRON-FULL-PATH-2-->
<!--This mode can be used to generate prefixed XPath for humans-->
<xsl:template match="node() | @*" mode="schematron-get-full-path-2">
      <xsl:for-each select="ancestor-or-self::*">
         <xsl:text>/</xsl:text>
         <xsl:value-of select="name(.)"/>
         <xsl:if test="preceding-sibling::*[name(.)=name(current())]">
            <xsl:text>[</xsl:text>
            <xsl:value-of select="count(preceding-sibling::*[name(.)=name(current())])+1"/>
            <xsl:text>]</xsl:text>
         </xsl:if>
      </xsl:for-each>
      <xsl:if test="not(self::*)">
         <xsl:text/>/@<xsl:value-of select="name(.)"/>
      </xsl:if>
   </xsl:template>
   <!--MODE: SCHEMATRON-FULL-PATH-3-->
<!--This mode can be used to generate prefixed XPath for humans 
	(Top-level element has index)-->
<xsl:template match="node() | @*" mode="schematron-get-full-path-3">
      <xsl:for-each select="ancestor-or-self::*">
         <xsl:text>/</xsl:text>
         <xsl:value-of select="name(.)"/>
         <xsl:if test="parent::*">
            <xsl:text>[</xsl:text>
            <xsl:value-of select="count(preceding-sibling::*[name(.)=name(current())])+1"/>
            <xsl:text>]</xsl:text>
         </xsl:if>
      </xsl:for-each>
      <xsl:if test="not(self::*)">
         <xsl:text/>/@<xsl:value-of select="name(.)"/>
      </xsl:if>
   </xsl:template>

   <!--MODE: GENERATE-ID-FROM-PATH -->
<xsl:template match="/" mode="generate-id-from-path"/>
   <xsl:template match="text()" mode="generate-id-from-path">
      <xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
      <xsl:value-of select="concat('.text-', 1+count(preceding-sibling::text()), '-')"/>
   </xsl:template>
   <xsl:template match="comment()" mode="generate-id-from-path">
      <xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
      <xsl:value-of select="concat('.comment-', 1+count(preceding-sibling::comment()), '-')"/>
   </xsl:template>
   <xsl:template match="processing-instruction()" mode="generate-id-from-path">
      <xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
      <xsl:value-of select="concat('.processing-instruction-', 1+count(preceding-sibling::processing-instruction()), '-')"/>
   </xsl:template>
   <xsl:template match="@*" mode="generate-id-from-path">
      <xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
      <xsl:value-of select="concat('.@', name())"/>
   </xsl:template>
   <xsl:template match="*" mode="generate-id-from-path" priority="-0.5">
      <xsl:apply-templates select="parent::*" mode="generate-id-from-path"/>
      <xsl:text>.</xsl:text>
      <xsl:value-of select="concat('.',name(),'-',1+count(preceding-sibling::*[name()=name(current())]),'-')"/>
   </xsl:template>

   <!--MODE: GENERATE-ID-2 -->
<xsl:template match="/" mode="generate-id-2">U</xsl:template>
   <xsl:template match="*" mode="generate-id-2" priority="2">
      <xsl:text>U</xsl:text>
      <xsl:number level="multiple" count="*"/>
   </xsl:template>
   <xsl:template match="node()" mode="generate-id-2">
      <xsl:text>U.</xsl:text>
      <xsl:number level="multiple" count="*"/>
      <xsl:text>n</xsl:text>
      <xsl:number count="node()"/>
   </xsl:template>
   <xsl:template match="@*" mode="generate-id-2">
      <xsl:text>U.</xsl:text>
      <xsl:number level="multiple" count="*"/>
      <xsl:text>_</xsl:text>
      <xsl:value-of select="string-length(local-name(.))"/>
      <xsl:text>_</xsl:text>
      <xsl:value-of select="translate(name(),':','.')"/>
   </xsl:template>
   <!--Strip characters--><xsl:template match="text()" priority="-1"/>

   <!--SCHEMA SETUP-->
<xsl:template match="/">
      <svrl:schematron-output xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                              title="iHUB_Data_Exchange_CDA_Mapping Biospecimen Data"
                              schemaVersion="1.0">
         <xsl:comment>
            <xsl:value-of select="$archiveDirParameter"/>   
		 <xsl:value-of select="$archiveNameParameter"/>  
		 <xsl:value-of select="$fileNameParameter"/>  
		 <xsl:value-of select="$fileDirParameter"/>
         </xsl:comment>
         <svrl:text>Validates a 2TRANSCEND Biospecimen event message</svrl:text>
         <svrl:text>2012-Dec-04 Tom Bechtold</svrl:text>
         <svrl:text>(c) Regents of the University of California. All Rights Reserved</svrl:text>
         <svrl:ns-prefix-in-attribute-values uri="urn:hl7-org:v3" prefix="hl7"/>
         <svrl:ns-prefix-in-attribute-values uri="http://www.w3.org/2001/XMLSchema-instance" prefix="xsi"/>
         <svrl:active-pattern>
            <xsl:attribute name="document">
               <xsl:value-of select="document-uri(/)"/>
            </xsl:attribute>
            <xsl:attribute name="id">header</xsl:attribute>
            <xsl:attribute name="name">CDA header: birthdate, ethnicity, gender, race and identifiers (MRN, subject, site, study)</xsl:attribute>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M6"/>
         <svrl:active-pattern>
            <xsl:attribute name="document">
               <xsl:value-of select="document-uri(/)"/>
            </xsl:attribute>
            <xsl:attribute name="id">activity_status</xsl:attribute>
            <xsl:attribute name="name">CDA Body: actitity status</xsl:attribute>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M7"/>
         <svrl:active-pattern>
            <xsl:attribute name="document">
               <xsl:value-of select="document-uri(/)"/>
            </xsl:attribute>
            <xsl:attribute name="id">biospecimen_id_timepoint</xsl:attribute>
            <xsl:attribute name="name">CDA body (biospecimen id/timepoint): CDMS Specimen ID, Study Calendar Event Time Point</xsl:attribute>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M8"/>
         <svrl:active-pattern>
            <xsl:attribute name="document">
               <xsl:value-of select="document-uri(/)"/>
            </xsl:attribute>
            <xsl:attribute name="id">biospecimen</xsl:attribute>
            <xsl:attribute name="name">CDA body (biospecimen): Available Quantity, Initial Quantity, Barcode, Collection Protocol Short Title, Collection Protocol Title, Specimen Characteristics Side, Specimen Characteristics Site, Specimen Class, Specimen Type, Guidance for Breast Core Biopsy</xsl:attribute>
            <xsl:apply-templates/>
         </svrl:active-pattern>
         <xsl:apply-templates select="/" mode="M9"/>
      </svrl:schematron-output>
   </xsl:template>

   <!--SCHEMATRON PATTERNS-->
<svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">iHUB_Data_Exchange_CDA_Mapping Biospecimen Data</svrl:text>

   <!--PATTERN headerCDA header: birthdate, ethnicity, gender, race and identifiers (MRN, subject, site, study)-->
<svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">CDA header: birthdate, ethnicity, gender, race and identifiers (MRN, subject, site, study)</svrl:text>

	  <!--RULE -->
<xsl:template match="/hl7:ClinicalDocument" priority="1002" mode="M6">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="/hl7:ClinicalDocument"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="count(hl7:recordTarget/hl7:patientRole)=1"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="count(hl7:recordTarget/hl7:patientRole)=1">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Unavailable patient role</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

			   
		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="count(hl7:documentationOf/hl7:serviceEvent[@classCode='CLNTRL']/hl7:id[@root='2.16.840.1.113883.3.26.1.7'][@extension]/../hl7:code[@nullFlavor='OTH'][hl7:originalText='clinical trial'])=1"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="count(hl7:documentationOf/hl7:serviceEvent[@classCode='CLNTRL']/hl7:id[@root='2.16.840.1.113883.3.26.1.7'][@extension]/../hl7:code[@nullFlavor='OTH'][hl7:originalText='clinical trial'])=1">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Unavailable study identifier</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M6"/>
   </xsl:template>


	  <!--RULE -->
<xsl:template match="/hl7:ClinicalDocument/hl7:recordTarget/hl7:patientRole"
                 priority="1000"
                 mode="M6">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="/hl7:ClinicalDocument/hl7:recordTarget/hl7:patientRole"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="count(hl7:id[@root][@extension][@assigningAuthorityName='iSpy2 Study'])=1"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="count(hl7:id[@root][@extension][@assigningAuthorityName='iSpy2 Study'])=1">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Unavailable Study subject identifier</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M6"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M6"/>
   <xsl:template match="@*|node()" priority="-2" mode="M6">
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M6"/>
   </xsl:template>

   <!--PATTERN activity_statusCDA Body: actitity status-->
<svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">CDA Body: actitity status</svrl:text>

	  <!--RULE -->
<xsl:template match="/" priority="1001" mode="M7">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="/"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="count(//hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:id[@nullFlavor='NI']][hl7:code[@code='263490005'][@codeSystem='2.16.840.1.113883.6.96']])=1"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="count(//hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:id[@nullFlavor='NI']][hl7:code[@code='263490005'][@codeSystem='2.16.840.1.113883.6.96']])=1">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Unavailable activity status observation</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M7"/>
   </xsl:template>

	  <!--RULE -->
<xsl:template match="//hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:id[@nullFlavor='NI']][hl7:code[@code='263490005'][@codeSystem='2.16.840.1.113883.6.96']]"
                 priority="1000"
                 mode="M7">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="//hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:id[@nullFlavor='NI']][hl7:code[@code='263490005'][@codeSystem='2.16.840.1.113883.6.96']]"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="count(hl7:value[@xsi:type='CD'][@codeSystem='2.16.840.1.113883.6.96'])=1"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="count(hl7:value[@xsi:type='CD'][@codeSystem='2.16.840.1.113883.6.96'])=1">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Unavailable activity status value</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="hl7:value[@xsi:type='CD'][@codeSystem='2.16.840.1.113883.6.96'][matches(@code, '^(55561003)|(73425007)$')]"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="hl7:value[@xsi:type='CD'][@codeSystem='2.16.840.1.113883.6.96'][matches(@code, '^(55561003)|(73425007)$')]">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Invalid activity status value</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M7"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M7"/>
   <xsl:template match="@*|node()" priority="-2" mode="M7">
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M7"/>
   </xsl:template>

   <!--PATTERN biospecimen_id_timepointCDA body (biospecimen id/timepoint): CDMS Specimen ID, Study Calendar Event Time Point-->
<svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">CDA body (biospecimen id/timepoint): CDMS Specimen ID, Study Calendar Event Time Point</svrl:text>

	  <!--RULE -->
<xsl:template match="/hl7:ClinicalDocument" priority="1000" mode="M8">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="/hl7:ClinicalDocument"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="count(//hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@code='123038009'][@codeSystem='2.16.840.1.113883.6.96']])=1"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="count(//hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@code='123038009'][@codeSystem='2.16.840.1.113883.6.96']])=1">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Unavailable biospecimen result</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="count(//hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@nullFlavor='OTH'][hl7:originalText='Study Calendar Event Time Point']][hl7:value[@xsi:type='PQ'][@value][@unit]])=1"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="count(//hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@nullFlavor='OTH'][hl7:originalText='Study Calendar Event Time Point']][hl7:value[@xsi:type='PQ'][@value][@unit]])=1">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Unavailable biospecimen Study Calendar Event Time Point</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M8"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M8"/>
   <xsl:template match="@*|node()" priority="-2" mode="M8">
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M8"/>
   </xsl:template>

   <!--PATTERN biospecimenCDA body (biospecimen): Available Quantity, Initial Quantity, Barcode, Collection Protocol Short Title, Collection Protocol Title, Specimen Characteristics Side, Specimen Characteristics Site, Specimen Class, Specimen Type, Guidance for Breast Core Biopsy-->
<svrl:text xmlns:svrl="http://purl.oclc.org/dsdl/svrl">CDA body (biospecimen): Available Quantity, Initial Quantity, Barcode, Collection Protocol Short Title, Collection Protocol Title, Specimen Characteristics Side, Specimen Characteristics Site, Specimen Class, Specimen Type, Guidance for Breast Core Biopsy</svrl:text>

	  <!--RULE -->
<xsl:template match="/hl7:ClinicalDocument" priority="1002" mode="M9">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl" context="/hl7:ClinicalDocument"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="count(hl7:component/hl7:structuredBody/hl7:component/hl7:section[hl7:templateId[@root='2.16.840.1.113883.10.20.22.2.31'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='59773-2'][@codeSystem='2.16.840.1.113883.6.1']]/hl7:entry[@typeCode='DRIV']/hl7:procedure[@classCode='PROC'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.14'][@assigningAuthorityName='HL7 CCDA']])=1"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="count(hl7:component/hl7:structuredBody/hl7:component/hl7:section[hl7:templateId[@root='2.16.840.1.113883.10.20.22.2.31'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='59773-2'][@codeSystem='2.16.840.1.113883.6.1']]/hl7:entry[@typeCode='DRIV']/hl7:procedure[@classCode='PROC'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.14'][@assigningAuthorityName='HL7 CCDA']])=1">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Unavailable biospecimen procedure</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="count(hl7:component/hl7:structuredBody/hl7:component/hl7:section[hl7:templateId[@root='2.16.840.1.113883.10.20.22.2.31'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='59773-2'][@codeSystem='2.16.840.1.113883.6.1']]/hl7:entry[@typeCode='DRIV']/hl7:procedure[@classCode='PROC'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.14'][@assigningAuthorityName='HL7 CCDA']]/hl7:entryRelationship[@typeCode='COMP']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@code='123038009'][@codeSystem='2.16.840.1.113883.6.96']])=1"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="count(hl7:component/hl7:structuredBody/hl7:component/hl7:section[hl7:templateId[@root='2.16.840.1.113883.10.20.22.2.31'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='59773-2'][@codeSystem='2.16.840.1.113883.6.1']]/hl7:entry[@typeCode='DRIV']/hl7:procedure[@classCode='PROC'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.14'][@assigningAuthorityName='HL7 CCDA']]/hl7:entryRelationship[@typeCode='COMP']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@code='123038009'][@codeSystem='2.16.840.1.113883.6.96']])=1">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Unavailable biospecimen result</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M9"/>
   </xsl:template>

	  <!--RULE -->
<xsl:template match="/hl7:ClinicalDocument/hl7:component/hl7:structuredBody/hl7:component/hl7:section[hl7:templateId[@root='2.16.840.1.113883.10.20.22.2.31'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='59773-2'][@codeSystem='2.16.840.1.113883.6.1']]/hl7:entry[@typeCode='DRIV']/hl7:procedure[@classCode='PROC'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.14'][@assigningAuthorityName='HL7 CCDA']]"
                 priority="1001"
                 mode="M9">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="/hl7:ClinicalDocument/hl7:component/hl7:structuredBody/hl7:component/hl7:section[hl7:templateId[@root='2.16.840.1.113883.10.20.22.2.31'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='59773-2'][@codeSystem='2.16.840.1.113883.6.1']]/hl7:entry[@typeCode='DRIV']/hl7:procedure[@classCode='PROC'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.14'][@assigningAuthorityName='HL7 CCDA']]"/>

		 
		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="count(hl7:targetSiteCode/hl7:qualifier[hl7:name[@code='272741003'][@codeSystem='2.16.840.1.113883.6.96']][hl7:value[@code][@codeSystem='2.16.840.1.113883.6.96']])=1"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="count(hl7:targetSiteCode/hl7:qualifier[hl7:name[@code='272741003'][@codeSystem='2.16.840.1.113883.6.96']][hl7:value[@code][@codeSystem='2.16.840.1.113883.6.96']])=1">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Unavailable biospecimen target side</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="count(hl7:entryRelationship[@typeCode='COMP']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.13'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='30651-4'][@codeSystem='2.16.840.1.113883.6.1']][hl7:statusCode[@code='completed']][hl7:value[@xsi:type='CD']])=1"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="count(hl7:entryRelationship[@typeCode='COMP']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.13'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='30651-4'][@codeSystem='2.16.840.1.113883.6.1']][hl7:statusCode[@code='completed']][hl7:value[@xsi:type='CD']])=1">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Unavailable biospecimen guidance for core needle biopsy</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="hl7:entryRelationship[@typeCode='COMP']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.13'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='30651-4'][@codeSystem='2.16.840.1.113883.6.1']][hl7:statusCode[@code='completed']][hl7:value[@xsi:type='CD'][(@codeSystem='2.16.840.1.113883.6.96' and matches(@code,'^(16310003)|(241615005)|(258172002)|(71651007)|(113011001)$')) or (@nullFlavor='OTH')]]"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="hl7:entryRelationship[@typeCode='COMP']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.13'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='30651-4'][@codeSystem='2.16.840.1.113883.6.1']][hl7:statusCode[@code='completed']][hl7:value[@xsi:type='CD'][(@codeSystem='2.16.840.1.113883.6.96' and matches(@code,'^(16310003)|(241615005)|(258172002)|(71651007)|(113011001)$')) or (@nullFlavor='OTH')]]">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Invalid biospecimen guidance for core needle biopsy</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="count(hl7:code)=1"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl" test="count(hl7:code)=1">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Unavailable biospecimen collection protocol short title</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="hl7:code[@nullFlavor='OTH'][hl7:originalText]"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="hl7:code[@nullFlavor='OTH'][hl7:originalText]">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Unavailable biospecimen collection protocol titles</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M9"/>
   </xsl:template>

	  <!--RULE -->
<xsl:template match="hl7:component/hl7:structuredBody/hl7:component/hl7:section[hl7:templateId[@root='2.16.840.1.113883.10.20.22.2.31'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='59773-2'][@codeSystem='2.16.840.1.113883.6.1']]/hl7:entry[@typeCode='DRIV']/hl7:procedure[@classCode='PROC'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.14'][@assigningAuthorityName='HL7 CCDA']]/hl7:entryRelationship[@typeCode='COMP']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@code='123038009'][@codeSystem='2.16.840.1.113883.6.96']]"
                 priority="1000"
                 mode="M9">
      <svrl:fired-rule xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                       context="hl7:component/hl7:structuredBody/hl7:component/hl7:section[hl7:templateId[@root='2.16.840.1.113883.10.20.22.2.31'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='59773-2'][@codeSystem='2.16.840.1.113883.6.1']]/hl7:entry[@typeCode='DRIV']/hl7:procedure[@classCode='PROC'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.14'][@assigningAuthorityName='HL7 CCDA']]/hl7:entryRelationship[@typeCode='COMP']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2']][hl7:code[@code='123038009'][@codeSystem='2.16.840.1.113883.6.96']]"/>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="count(hl7:entryRelationship[@typeCode='SUBJ']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@nullFlavor='OTH'][hl7:originalText='Available Quantity']][hl7:value[@xsi:type='PQ'][@value][@unit]])=1"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="count(hl7:entryRelationship[@typeCode='SUBJ']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@nullFlavor='OTH'][hl7:originalText='Available Quantity']][hl7:value[@xsi:type='PQ'][@value][@unit]])=1">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Unavailable biospecimen Available Quantity</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="count(hl7:entryRelationship[@typeCode='SUBJ']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@nullFlavor='OTH'][hl7:originalText='Initial Quantity']][hl7:value[@xsi:type='PQ'][@value][@unit]])=1"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="count(hl7:entryRelationship[@typeCode='SUBJ']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@nullFlavor='OTH'][hl7:originalText='Initial Quantity']][hl7:value[@xsi:type='PQ'][@value][@unit]])=1">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Unavailable biospecimen Initial Quantity</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="count(hl7:participant[@typeCode='DEV']/hl7:participantRole[@classCode='MANU'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.37']][hl7:id[@extension]])=1"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="count(hl7:participant[@typeCode='DEV']/hl7:participantRole[@classCode='MANU'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.37']][hl7:id[@extension]])=1">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Unavailable biospecimen barcode</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="count(hl7:specimen/hl7:specimenRole/hl7:specimenPlayingEntity/hl7:code)=1"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="count(hl7:specimen/hl7:specimenRole/hl7:specimenPlayingEntity/hl7:code)=1">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Unavailable biospecimen class</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="count(hl7:specimen/hl7:specimenRole/hl7:specimenPlayingEntity/hl7:code[@codeSystem='2.16.840.1.113883.6.96'][matches(@code,'^(48469005)|(258442002)|(258562007)|(119376003)$')])=1"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="count(hl7:specimen/hl7:specimenRole/hl7:specimenPlayingEntity/hl7:code[@codeSystem='2.16.840.1.113883.6.96'][matches(@code,'^(48469005)|(258442002)|(258562007)|(119376003)$')])=1">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Invalid biospecimen class</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="count(hl7:entryRelationship[@typeCode='SUBJ']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='371439000'][@codeSystem='2.16.840.1.113883.6.96']][hl7:value[@xsi:type='CD'][@codeSystem='2.16.840.1.113883.6.96']])=1"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="count(hl7:entryRelationship[@typeCode='SUBJ']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='371439000'][@codeSystem='2.16.840.1.113883.6.96']][hl7:value[@xsi:type='CD'][@codeSystem='2.16.840.1.113883.6.96']])=1">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Unavailable biospecimen type</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>

		    <!--ASSERT -->
<xsl:choose>
         <xsl:when test="hl7:entryRelationship[@typeCode='SUBJ']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='371439000'][@codeSystem='2.16.840.1.113883.6.96']][hl7:value[@xsi:type='CD'][@codeSystem='2.16.840.1.113883.6.96'][matches(@code,'^(119376003)|(441479001)|(119373006)|(119341000)|(309051001)|(119359002)|(258450006)|(119339001)|(258459007)|(440674008)|(119321005)|(122571007)|(119361006)|(119342007)|(119364003)|(119334006)|(122569007)|(119332005)|(122575003)|(258438000)|(258580003)|(48469005)|(258661006)|(258566005)|(441673008)|(448789008)|(258562007)|(88878007)$')]]"/>
         <xsl:otherwise>
            <svrl:failed-assert xmlns:svrl="http://purl.oclc.org/dsdl/svrl"
                                test="hl7:entryRelationship[@typeCode='SUBJ']/hl7:observation[@classCode='OBS'][@moodCode='EVN'][hl7:templateId[@root='2.16.840.1.113883.10.20.22.4.2'][@assigningAuthorityName='HL7 CCDA']][hl7:code[@code='371439000'][@codeSystem='2.16.840.1.113883.6.96']][hl7:value[@xsi:type='CD'][@codeSystem='2.16.840.1.113883.6.96'][matches(@code,'^(119376003)|(441479001)|(119373006)|(119341000)|(309051001)|(119359002)|(258450006)|(119339001)|(258459007)|(440674008)|(119321005)|(122571007)|(119361006)|(119342007)|(119364003)|(119334006)|(122569007)|(119332005)|(122575003)|(258438000)|(258580003)|(48469005)|(258661006)|(258566005)|(441673008)|(448789008)|(258562007)|(88878007)$')]]">
               <xsl:attribute name="location">
                  <xsl:apply-templates select="." mode="schematron-select-full-path"/>
               </xsl:attribute>
               <svrl:text>Invalid biospecimen type</svrl:text>
            </svrl:failed-assert>
         </xsl:otherwise>
      </xsl:choose>
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M9"/>
   </xsl:template>
   <xsl:template match="text()" priority="-1" mode="M9"/>
   <xsl:template match="@*|node()" priority="-2" mode="M9">
      <xsl:apply-templates select="*|comment()|processing-instruction()" mode="M9"/>
   </xsl:template>
</xsl:stylesheet>
