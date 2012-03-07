<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns1trim="urn:tolven-org:trim:4.0"
	xmlns:xs="http://www.w3.org/TR/2008/REC-xml-20081126#" xmlns:cacis="http://cacis.nci.nih.gov"
	xmlns:ns1="http://integration.nci.nih.gov/messaging" xmlns:p="http://integration.nci.nih.gov/participant"
	xmlns:par="http://webservice.caaers.cabig.nci.nih.gov/participant">

	<xsl:output method="xml" indent="yes" />

	<!-- Main -->
	<xsl:template match="/">
		<xsl:call-template name="ConvertToCaAERSParticipantMsg">
		</xsl:call-template>
	</xsl:template>


	<xsl:template name="ConvertToCaAERSParticipantMsg">
		<!--
			***************************** TODO: 1st draft
			implementation****************
		-->
		<par:participant id="" version=""
			xmlns:par="http://webservice.caaers.cabig.nci.nih.gov/participant">
			<firstName><xsl:value-of select="//p:firstName"/></firstName>
			<lastName><xsl:value-of select="//p:lastName"/></lastName>
			<maidenName><xsl:value-of select="//p:maidenName"/></maidenName>
			<middleName><xsl:value-of select="//p:middleName"/></middleName>
			<birthDate><xsl:value-of select="//p:birthDate"/></birthDate>
			<gender><xsl:value-of select="//p:gender"/></gender>
			<race><xsl:value-of select="//p:race"/></race>
			<ethnicity><xsl:value-of select="//p:ethnicity"/></ethnicity>
			<identifiers>
				<xsl:apply-templates select="//p:identifiers/p:organizationAssignedIdentifier"/>
				<xsl:apply-templates select="//p:identifiers/p:systemAssignedIdentifier"/>
			</identifiers>
			<assignments>
				<xsl:apply-templates select="//p:assignments/p:assignment"/>
			</assignments>
		</par:participant>
	</xsl:template>
	
	<xsl:template match="//p:identifiers/p:organizationAssignedIdentifier">
		<par:organizationAssignedIdentifier
			id="?" version="?">
			<type><xsl:value-of select="p:type"/></type>
			<value><xsl:value-of select="p:value"/></value>
			<primaryIndicator><xsl:value-of select="p:primaryIndicator"/></primaryIndicator>
			<par:organization id="?" version="?">
				<name><xsl:value-of select="p:organization/p:name"/></name>
				<nciInstituteCode><xsl:value-of select="p:organization/p:nciInstituteCode"/></nciInstituteCode>
			</par:organization>
		</par:organizationAssignedIdentifier>
	</xsl:template>
	
	<xsl:template match="//p:identifiers/p:systemAssignedIdentifier">
		<par:systemAssignedIdentifier id="?" version="?">
			<type><xsl:value-of select="p:type"/></type>
			<value><xsl:value-of select="p:value"/></value>
			<primaryIndicator><xsl:value-of select="p:primaryIndicator"/></primaryIndicator>
			<systemName><xsl:value-of select="p:systemName"/></systemName>
		</par:systemAssignedIdentifier>
	</xsl:template>
	
	<xsl:template match="//p:assignments/p:assignment">
		<par:assignment id="" version="">
			<studySubjectIdentifier><xsl:value-of select="p:studySubjectIdentifier"/></studySubjectIdentifier>
			<par:studySite id="" version="">
				<par:study id="" version="">
					<par:identifiers>					
					 <xsl:apply-templates select="//p:assignments/p:assignment/p:studySite/p:study/p:identifiers/p:identifier"/>
					</par:identifiers>
				</par:study>
				<par:organization id="" version="">
					<name><xsl:value-of select="p:studySite/p:organization/p:name"/></name>
					<nciInstituteCode><xsl:value-of select="p:studySite/p:organization/p:nciInstituteCode"/></nciInstituteCode>
				</par:organization>
			</par:studySite>
		</par:assignment>
	</xsl:template>
	
	<xsl:template match="//p:assignments/p:assignment/p:studySite/p:study/p:identifiers/p:identifier">
		<par:identifier id="" version="">
			<type><xsl:value-of select="p:type"/></type>
			<value><xsl:value-of select="p:value"/></value>
		</par:identifier>
	</xsl:template>
	
</xsl:stylesheet>
