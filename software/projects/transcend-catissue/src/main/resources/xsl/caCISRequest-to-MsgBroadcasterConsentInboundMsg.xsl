<?xml version='1.0' ?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:a="http://cacis.nci.nih.gov"
	xmlns:mes="http://caXchange.nci.nih.gov/messaging" xmlns:ns2="http://caXchange.nci.nih.gov/caxchangerequest"
	xmlns:consents="http://cacis.nci.nih.gov" xmlns:trim="urn:tolven-org:trim:4.0">
	<xsl:template match="/">
		<ns2:caxchangerequest xmlns:c="http://caXchange.nci.nih.gov/messaging"
			xmlns:a="http://cacis.nci.nih.gov" xmlns:b="urn:tolven-org:trim:4.0"
			xmlns:ns2="http://caXchange.nci.nih.gov/caxchangerequest">
			<ns0:metadata xmlns:ns0="http://caXchange.nci.nih.gov/messaging">
				<serviceType xmlns:ns1trim="urn:tolven-org:trim:4.0"
					xmlns:cda="urn:hl7-org:v3" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
					xmlns="http://caXchange.nci.nih.gov/messaging" xmlns:S="http://schemas.xmlsoap.org/soap/envelope/"
					xmlns:mes="http://caXchange.nci.nih.gov/messaging">
					<xsl:value-of
						select="consents:caCISRequest/consents:sourceData/ns2:caxchangerequest/mes:metadata/mes:serviceType" />
				</serviceType>
				<mes:operationName xmlns:ns1trim="urn:tolven-org:trim:4.0"
					xmlns:cda="urn:hl7-org:v3" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
					xmlns="http://caXchange.nci.nih.gov/messaging" xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
					<xsl:value-of
						select="consents:caCISRequest/consents:sourceData/ns2:caxchangerequest/mes:metadata/mes:operationName" />
				</mes:operationName>
				<mes:externalIdentifier xmlns:ns1trim="urn:tolven-org:trim:4.0"
					xmlns:cda="urn:hl7-org:v3" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
					xmlns="http://caXchange.nci.nih.gov/messaging" xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
					<xsl:value-of
						select="consents:caCISRequest/consents:sourceData/ns2:caxchangerequest/mes:metadata/mes:externalIdentifier" />
				</mes:externalIdentifier>
				<mes:caXchangeIdentifier xmlns:ns1trim="urn:tolven-org:trim:4.0"
					xmlns:cda="urn:hl7-org:v3" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
					xmlns="http://caXchange.nci.nih.gov/messaging" xmlns:S="http://schemas.xmlsoap.org/soap/envelope/">
					<xsl:value-of
						select="consents:caCISRequest/consents:sourceData/ns2:caxchangerequest/mes:metadata/mes:caXchangeIdentifier" />
				</mes:caXchangeIdentifier>
			</ns0:metadata>
			<ns0:request xmlns:ns0="http://caXchange.nci.nih.gov/messaging">
				<ns0:businessMessagePayload>
					<consents xmlns="http://cacis.nci.nih.gov">
						<participant>
							<cdmsSubjectId>
								<xsl:value-of
									select="consents:caCISRequest/consents:sourceData/ns2:caxchangerequest/mes:request/mes:businessMessagePayload/trim:trim/consents:consents/consents:participant/consents:cdmsSubjectId" />
							</cdmsSubjectId>
						</participant>
						<consentsDetailsList>
							<xsl:for-each
								select="consents:caCISRequest/consents:sourceData/ns2:caxchangerequest/mes:request/mes:businessMessagePayload/trim:trim/consents:consents/consents:consentsDetailsList/consents:consentDetails">
								<consentDetails>
									<collectionProtocolEvent>
										<xsl:value-of select="consents:collectionProtocolEvent" />
									</collectionProtocolEvent>
									<specimen>
										<cdmsSpecimenId>
											<xsl:value-of select="consents:specimen/consents:cdmsSpecimenId" />
										</cdmsSpecimenId>
									</specimen>
									<collectionProtocol>
										<title>
											<xsl:value-of select="consents:collectionProtocol/consents:title" />
										</title>
										<shortTitle>
											<xsl:value-of
												select="consents:collectionProtocol/consents:shortTitle" />
										</shortTitle>
									</collectionProtocol>
									<consentTierResponses>
										<xsl:for-each select="consents:consentTierResponses/consents:tier">
											<tier>
												<statement>
													<xsl:value-of select="consents:statement" />
												</statement>
												<response>
													<xsl:value-of select="consents:response" />
												</response>
											</tier>
										</xsl:for-each>
									</consentTierResponses>
								</consentDetails>
							</xsl:for-each>
						</consentsDetailsList>
					</consents>
				</ns0:businessMessagePayload>
			</ns0:request>
		</ns2:caxchangerequest>
	</xsl:template>
</xsl:stylesheet>