<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
       xmlns:ns1trim="urn:tolven-org:trim:4.0"
       xmlns:xs="http://www.w3.org/TR/2008/REC-xml-20081126#"
       xmlns:cacis="http://cacis.nci.nih.gov">
		
		<xsl:output method="xml" indent="yes"/>
		
		<!-- Main -->
		<xsl:template match="/">
				<xsl:call-template name="ConvertToCaCISRequest">
				</xsl:call-template>
		</xsl:template>
		
		
		<xsl:template name="ConvertToCaCISRequest">
				<cacis:caCISRequest xmlns:cda="urn:hl7-org:v3" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" >
				<!-- get the trim description -->
				<xsl:variable name="trimdesc" select="//ns1trim:trim/ns1trim:description"/>
				<cacis:routingInstructions>
					<!-- Match the trim description to apply the correct template, add more when as required -->
					<xsl:choose>
						<!-- Baseline AE trim -->
						<xsl:when test="$trimdesc='Baseline Symptoms'">
							<cacis:exchangeDocument exchangeFormat="AE">							
							</cacis:exchangeDocument>
						</xsl:when>
						
						<!-- Participant Registration -->
						<xsl:when test="$trimdesc='Registration'">
							<cacis:exchangeDocument exchangeFormat="REGISTRATION">
							</cacis:exchangeDocument>
						</xsl:when>
										
					</xsl:choose>				
					</cacis:routingInstructions>
					<cacis:sourceData>						
						<xsl:copy-of select="/HttpRequest/Content/ns1trim:trim"></xsl:copy-of>
					</cacis:sourceData>
					<cacis:clinicalMetaData siteIdRoot="2.16.840.1.113883.2" patientIdRoot="2.16.840.1.113883.3" siteIdExtension="site_id" patientIdExtension="patient_id_1" studyIdRoot="2.16.840.1.113883.1" studyIdExtension="study_id">
						 <xsl:attribute name="documentType">
						 	<xsl:value-of select="$trimdesc"></xsl:value-of>
						 </xsl:attribute>
					</cacis:clinicalMetaData>
				</cacis:caCISRequest>
		</xsl:template>
</xsl:stylesheet>
