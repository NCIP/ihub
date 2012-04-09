<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 	
xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
xmlns:p="http://integration.nci.nih.gov/participant">
	<xsl:output method="xml" indent="yes" />
	
	<xsl:template match="/">
		<xsl:call-template name="ConvertToCaAERSParticipantMsg"/>
	</xsl:template>
	<xsl:template name="ConvertToCaAERSParticipantMsg">
		<caaers:participant id="1" version="1" xmlns:caaers="http://webservice.caaers.cabig.nci.nih.gov/participant">
			<xsl:apply-templates select="//p:participant/node()|@*"/>
		</caaers:participant>
	</xsl:template>
	
	<xsl:template match="node()|@*">
		<xsl:copy>
		  <xsl:apply-templates select="node()|@*"/>
		</xsl:copy>
  </xsl:template>
	
	<xsl:template match="p:*">
		<xsl:element name="{local-name()}" >
		  <xsl:apply-templates select="node()|@*"/>
		</xsl:element>		
	</xsl:template>
	
	<!-- TO ignore activityStatus -->
	<xsl:template match="p:activityStatus"/>

</xsl:stylesheet>
