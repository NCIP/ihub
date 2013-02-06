<?xml version='1.0' ?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns0="http://caXchange.nci.nih.gov/messaging"
	xmlns:a="http://cacis.nci.nih.gov" xmlns:ns2="http://caXchange.nci.nih.gov/caxchangerequest"
	xmlns:r="http://catissue/consent/response">
	<xsl:output method="xml" />

	<xsl:key name="response-lookup" match="r:response" use="r:vockey" />
	<xsl:variable name="response-top"
		select="document('catissue-consent-response-lookup.xml')/*" />
	<xsl:template match="r:consentresponse">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('response-lookup', $curr-key)/r:vocvalue" />
	</xsl:template>

	<xsl:template match="/">
		<consents>
			<participant>
				<lastName>
					<xsl:value-of
						select="ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/a:consents/a:participant/a:cdmsSubjectId" />
				</lastName>
			</participant>

			<xsl:for-each
				select="ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/a:consents/a:consentsDetailsList/a:consentDetails">
				<consentDetails>
					<collectionProtocolEvent>
						<xsl:value-of select="a:collectionProtocolEvent" />
					</collectionProtocolEvent>
					<consentData>
						<specimenLabel>
							<xsl:value-of select="a:specimen/a:cdmsSpecimenId" />
						</specimenLabel>
						<xsl:for-each select="a:consentTierResponses/a:tier">
							<consentTierStatus>
								<consentTier>
									<statement>
										<xsl:value-of select="a:statement" />
									</statement>
								</consentTier>
								<status>
									<xsl:apply-templates select="$response-top">
										<xsl:with-param name="curr-key" select="a:response" />
									</xsl:apply-templates>
								</status>
							</consentTierStatus>
						</xsl:for-each>
					</consentData>
					<collectionProtocol>
						<title>
							<xsl:value-of select="a:collectionProtocol/a:title" />
						</title>
						<shortTitle>
							<xsl:value-of select="a:collectionProtocol/a:shortTitle" />
						</shortTitle>
					</collectionProtocol>
				</consentDetails>
			</xsl:for-each>
		</consents>
	</xsl:template>

</xsl:stylesheet>