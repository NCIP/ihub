<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:ns0="http://caXchange.nci.nih.gov/messaging" xmlns:ns1="urn:hl7-org:v3"
	xmlns:ns2="http://caXchange.nci.nih.gov/caxchangerequest" xmlns:ns3="http://cacis.nci.nih.gov"
	exclude-result-prefixes="ns0 xs ns1 ns2 ns3">

	<xsl:output method="xml" encoding="UTF-8" indent="yes" />

	<xsl:template match="/">
		<ns2:caxchangerequest>
			<ns0:metadata>
				<xsl:copy-of select="//ns2:caxchangerequest/ns0:metadata/node()|@*" />
			</ns0:metadata>
			<ns0:request>
				<ns0:businessMessagePayload>
					<specimens>
						<participant>
							<cdmsSubjectId>
								<!-- study subject identifier -->
								<xsl:call-template name="show-id">
									<xsl:with-param name="id"
										select="//ns1:ClinicalDocument/ns1:recordTarget/ns1:patientRole/ns1:id[@assigningAuthorityName='iSpy2 Study']" />
								</xsl:call-template>
							</cdmsSubjectId>
						</participant>
						<specimenDetailsList>
							<xsl:apply-templates
								select="//ns1:ClinicalDocument/ns1:component/ns1:structuredBody/ns1:component" />
						</specimenDetailsList>
					</specimens>
				</ns0:businessMessagePayload>
			</ns0:request>
		</ns2:caxchangerequest>
	</xsl:template>

	<xsl:template
		match="//ns1:ClinicalDocument/ns1:component/ns1:structuredBody/ns1:component">
		<xsl:variable name="component" select="." />
		<xsl:variable name="observation"
			select="$component/ns1:section/ns1:entry/ns1:procedure/ns1:entryRelationship/ns1:observation" />
		<specimenDetail>
			<collectionProtocolEvent>
				<!-- Study Calendar Event Point -->
				<xsl:value-of
					select="$observation/ns1:entryRelationship/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code/ns1:originalText='Study Calendar Event Time Point']/ns1:value/@value" />
			</collectionProtocolEvent>
			<specimen>
				<cdmsSpecimenId>
					<!-- CDMS Specimen ID -->
					<xsl:call-template name="show-id">
						<xsl:with-param name="id"
							select="$observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code[@code='123038009'][@codeSystem='2.16.840.1.113883.6.96']]/ns1:specimen/ns1:specimenRole/ns1:id" />
					</xsl:call-template>
				</cdmsSpecimenId>
				<barcode>
					<xsl:call-template name="show-id">
						<xsl:with-param name="id"
							select="$observation/ns1:participant/ns1:participantRole[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.37']/ns1:id" />
					</xsl:call-template>
				</barcode>
				<activityStatus>
					<xsl:value-of
						select="$observation/ns1:entryRelationship/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code[@code='263490005'][@codeSystem='2.16.840.1.113883.6.96']]/ns1:value/@code"></xsl:value-of>
				</activityStatus>
				<specimenClass>
					<xsl:value-of
						select="$observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2']/ns1:specimen/ns1:specimenRole/ns1:specimenPlayingEntity/ns1:code/@code"></xsl:value-of>
				</specimenClass>
				<specimenType>
					<xsl:value-of
						select="$observation/ns1:entryRelationship/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code[@code='371439000'][@codeSystem='2.16.840.1.113883.6.96']]/ns1:value/@code"></xsl:value-of>
				</specimenType>
				<initialQuantity>
					<xsl:value-of
						select="$observation/ns1:entryRelationship/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code/ns1:originalText='Initial Quantity']/ns1:value/@value" />
				</initialQuantity>
				<availableQuantity>
					<xsl:value-of
						select="$observation/ns1:entryRelationship/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code/ns1:originalText='Available Quantity']/ns1:value/@value" />
				</availableQuantity>
				<specimenCharacteristics>
					<tissueSite>
						<xsl:value-of
							select="$component/ns1:section/ns1:entry/ns1:procedure[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.14']/ns1:targetSiteCode/@code"></xsl:value-of>
					</tissueSite>
					<tissueSide>
						<xsl:value-of
							select="$component/ns1:section/ns1:entry/ns1:procedure[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.14']/ns1:targetSiteCode/ns1:qualifier[ns1:name[@code='272741003'][@codeSystem='2.16.840.1.113883.6.96']]/ns1:value/@code"></xsl:value-of>
					</tissueSide>
				</specimenCharacteristics>
			</specimen>
			<collectionProtocol>
				<title>
				</title>
				<shortTitle>
					<xsl:call-template name="show-id">
						<xsl:with-param name="id"
							select="//ns1:ClinicalDocument/ns1:documentationOf/ns1:serviceEvent/ns1:id[../ns1:code/ns1:originalText='clinical trial']" />
					</xsl:call-template>
				</shortTitle>
			</collectionProtocol>
			<guidanceForBreastCoreBiopsy>
				<guidanceForBreastCoreBiopsyType>
					<xsl:call-template name="get-biopsy-code">
						<xsl:with-param name="value"
							select="$observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.13'][ns1:code[@code='30651-4']]/ns1:value" />
					</xsl:call-template>
				</guidanceForBreastCoreBiopsyType>
				<otherText>
					<xsl:call-template name="get-biopsy-otherText">
						<xsl:with-param name="value"
							select="$observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.13'][ns1:code[@code='30651-4']]/ns1:value" />
					</xsl:call-template>
				</otherText>
			</guidanceForBreastCoreBiopsy>
		</specimenDetail>
	</xsl:template>


	<!-- show-id -->
	<xsl:template name="show-id">
		<xsl:param name="id" />
		<xsl:value-of select="$id/@root" />
		<xsl:text>:</xsl:text>
		<xsl:value-of select="$id/@extension" />
	</xsl:template>


	<!-- get-biopsy-code -->
	<xsl:template name="get-biopsy-code">
		<xsl:param name="value" />
		<xsl:choose>
			<xsl:when test="$value/@code !=''">
				<xsl:value-of select="$value/@code"></xsl:value-of>
			</xsl:when>
			<xsl:when test="$value/@nullFlavor !=''">
				<xsl:value-of select="$value/@nullFlavor"></xsl:value-of>
			</xsl:when>
		</xsl:choose>
	</xsl:template>

	<!-- get-biopsy-otherText -->
	<xsl:template name="get-biopsy-otherText">
		<xsl:param name="value" />
		<xsl:choose>
			<xsl:when test="$value/@nullFlavor !=''">
				<xsl:value-of select="$value/ns1:originalText" />
			</xsl:when>
		</xsl:choose>
	</xsl:template>

</xsl:stylesheet>