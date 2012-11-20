<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:p="http://integration.nci.nih.gov/participant"
	xmlns:caaers="http://schema.integration.caaers.cabig.nci.nih.gov/participant"
	xmlns:g="http://caaers/gender/data" xmlns:r="http://caaers/race/data"
	xmlns:e="http://caaers/ethnicity/data">
	<xsl:output method="xml" indent="yes" />

	<xsl:template match="/">
		<xsl:call-template name="ConvertToCaAERSParticipantMsg" />
	</xsl:template>

	<xsl:template name="ConvertToCaAERSParticipantMsg">
		<caaers:participant id="1" version="1"
			xmlns:caaers="http://schema.integration.caaers.cabig.nci.nih.gov/participant">
			<xsl:apply-templates select="//p:participant/node()|@*" />
		</caaers:participant>
	</xsl:template>

	<xsl:key name="gender-lookup" match="g:gender" use="g:vockey" />
	<xsl:variable name="genders-top"
		select="document('caaers-participant-genders-lookup.xml')/*" />
	<xsl:template match="g:genders">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('gender-lookup', $curr-key)/g:vocvalue" />
	</xsl:template>

	<xsl:key name="race-lookup" match="r:race" use="r:vockey" />
	<xsl:variable name="races-top"
		select="document('caaers-participant-race-lookup.xml')/*" />
	<xsl:template match="r:races">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('race-lookup', $curr-key)/r:vocvalue" />
	</xsl:template>

	<xsl:key name="ethnicity-lookup" match="e:ethnicity" use="e:vockey" />
	<xsl:variable name="ethnicities-top"
		select="document('caaers-participant-ethnicity-lookup.xml')/*" />
	<xsl:template match="e:ethnicities">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('ethnicity-lookup', $curr-key)/e:vocvalue" />
	</xsl:template>

	<xsl:template match="node()|@*">
		<xsl:copy>
			<xsl:apply-templates select="node()|@*" />
		</xsl:copy>
	</xsl:template>

	<xsl:template match="p:*">
		<xsl:element name="{local-name()}">
			<xsl:apply-templates select="node()|@*" />
		</xsl:element>
	</xsl:template>

	<xsl:template match="p:firstName">
		<firstName>TRANSCEND</firstName>
	</xsl:template>
	<xsl:template match="p:lastName">
		<lastName>TRANSCEND</lastName>
	</xsl:template>

	<xsl:template match="p:race">
		<race>
			<xsl:apply-templates select="$races-top">
				<xsl:with-param name="curr-key" select="." />
			</xsl:apply-templates>
		</race>
	</xsl:template>

	<xsl:template match="p:gender">
		<gender>
			<xsl:apply-templates select="$genders-top">
				<xsl:with-param name="curr-key" select="." />
			</xsl:apply-templates>
		</gender>
	</xsl:template>

	<xsl:template match="p:ethnicity">
		<ethnicity>
			<xsl:apply-templates select="$ethnicities-top">
				<xsl:with-param name="curr-key" select="." />
			</xsl:apply-templates>
		</ethnicity>
	</xsl:template>

	<!-- TO ignore registration date -->
	<xsl:template match="p:registrationDate" />


	<xsl:template match="p:organizationAssignedIdentifier[p:type/text() = 'SSN']" />

	<xsl:template match="p:organizationAssignedIdentifier">
		<caaers:organizationAssignedIdentifier
			id="1" version="1"
			xmlns:caaers="http://schema.integration.caaers.cabig.nci.nih.gov/participant">
			<xsl:apply-templates select="node()|@*" />
		</caaers:organizationAssignedIdentifier>
	</xsl:template>

	<xsl:template match="p:systemAssignedIdentifier">
		<caaers:systemAssignedIdentifier id="1"
			version="1"
			xmlns:caaers="http://schema.integration.caaers.cabig.nci.nih.gov/participant">
			<xsl:apply-templates select="node()|@*" />
		</caaers:systemAssignedIdentifier>
	</xsl:template>

	<xsl:template match="p:organization">
		<caaers:organization id="1" version="1"
			xmlns:caaers="http://schema.integration.caaers.cabig.nci.nih.gov/participant">
			<xsl:apply-templates select="node()|@*" />
		</caaers:organization>
	</xsl:template>

	<xsl:template match="p:assignment">
		<caaers:assignment id="1" version="1"
			xmlns:caaers="http://schema.integration.caaers.cabig.nci.nih.gov/participant">
			<xsl:apply-templates select="node()|@*" />
		</caaers:assignment>
	</xsl:template>

	<xsl:template match="p:studySite">
		<caaers:studySite id="1" version="1"
			xmlns:caaers="http://schema.integration.caaers.cabig.nci.nih.gov/participant">
			<xsl:apply-templates select="node()|@*" />
		</caaers:studySite>
	</xsl:template>

	<xsl:template match="p:study">
		<caaers:study id="1" version="1"
			xmlns:caaers="http://schema.integration.caaers.cabig.nci.nih.gov/participant">
			<xsl:apply-templates select="node()|@*" />
		</caaers:study>
	</xsl:template>



</xsl:stylesheet>
