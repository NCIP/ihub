<?xml version='1.0' ?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns0="http://caXchange.nci.nih.gov/messaging"
	xmlns:ns1="urn:hl7-org:v3" xmlns:ns2="http://caXchange.nci.nih.gov/caxchangerequest"
	xmlns:ns3="http://cacis.nci.nih.gov" xmlns:r="http://catissue/consent/response">

	<xsl:key name="response-lookup" match="r:response" use="r:vockey" />
	<xsl:variable name="response-top"
		select="document('catissue-consent-response-lookup.xml')/*" />
	<xsl:template match="r:consentresponse">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('response-lookup', $curr-key)/r:vocvalue" />
	</xsl:template>

	<xsl:template match="/">
		<ns2:caxchangerequest>
			<ns0:metadata>
				<xsl:copy-of select="//ns2:caxchangerequest/ns0:metadata/node()|@*" />
			</ns0:metadata>
			<ns0:request>
				<ns0:businessMessagePayload>
					<consents>
						<participant>
							<cdmsSubjectId>
								<xsl:call-template name="show-id">
									<xsl:with-param name="id"
										select="//ns1:ClinicalDocument/ns1:recordTarget/ns1:patientRole/ns1:id[@assigningAuthorityName='iSpy2 Study']" />
								</xsl:call-template>
							</cdmsSubjectId>
						</participant>
						<consentsDetailsList>
							<xsl:apply-templates
								select="//ns1:ClinicalDocument/ns1:component/ns1:structuredBody/ns1:component" />
						</consentsDetailsList>
					</consents>
				</ns0:businessMessagePayload>
			</ns0:request>
		</ns2:caxchangerequest>
	</xsl:template>

	<xsl:template
		match="//ns1:ClinicalDocument/ns1:component/ns1:structuredBody/ns1:component">
		<xsl:variable name="entry" select="./ns1:section/ns1:entry" />
		<consentDetails>
			<specimen>
				<cdmsSpecimenId>
					<xsl:call-template name="show-id">
						<xsl:with-param name="id"
							select="$entry/ns1:observation/ns1:entryRelationship/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code[@code='123038009'][@codeSystem='2.16.840.1.113883.6.96']]/ns1:specimen/ns1:specimenRole/ns1:id" />
					</xsl:call-template>
				</cdmsSpecimenId>
			</specimen>
			<consentTierResponses>
				<tier>
					<statement><xsl:value-of select="$entry/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code[@code='309370004'][@codeSystem='2.16.840.1.113883.6.96'][ns1:originalText='Tier 1 consent response']]/ns1:code/ns1:originalText" /></statement>
					<response>
						<xsl:call-template name="show-consent-response">
							<xsl:with-param name="responseCode"
								select="$entry/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code[@code='309370004'][@codeSystem='2.16.840.1.113883.6.96'][ns1:originalText='Tier 1 consent response']]/ns1:value/@code" />
						</xsl:call-template>
					</response>
				</tier>
				<tier>
					<statement><xsl:value-of select="$entry/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code[@code='309370004'][@codeSystem='2.16.840.1.113883.6.96'][ns1:originalText='Tier 2 consent response']]/ns1:code/ns1:originalText" /></statement>
					<response>
						<xsl:call-template name="show-consent-response">
							<xsl:with-param name="responseCode"
								select="$entry/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code[@code='309370004'][@codeSystem='2.16.840.1.113883.6.96'][ns1:originalText='Tier 2 consent response']]/ns1:value/@code" />
						</xsl:call-template>
					</response>
				</tier>
			</consentTierResponses>
		</consentDetails>
	</xsl:template>

	<!-- show-id -->
	<xsl:template name="show-id">
		<xsl:param name="id" />
		<xsl:value-of select="$id/@root" />
		<xsl:text>:</xsl:text>
		<xsl:value-of select="$id/@extension" />
	</xsl:template>

	<!-- show-consent-response -->
	<xsl:template name="show-consent-response">
		<xsl:param name="responseCode" />
		<xsl:apply-templates select="$response-top">
			<xsl:with-param name="curr-key" select="$responseCode" />
		</xsl:apply-templates>
	</xsl:template>

</xsl:stylesheet>