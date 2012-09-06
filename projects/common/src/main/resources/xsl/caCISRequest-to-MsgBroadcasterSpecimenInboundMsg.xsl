<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:ns0="http://caXchange.nci.nih.gov/messaging" xmlns:ns1="urn:hl7-org:v3"
	xmlns:ns2="http://caXchange.nci.nih.gov/caxchangerequest" xmlns:ns3="http://cacis.nci.nih.gov"
	xmlns:b="http://catissue/guidanceForBreastCoreBiopsy/data" xmlns:d="http://catissue/characteristic/side/data"
	xmlns:st="http://catissue/characteristic/site/data" xmlns:c="http://catissue/specimen/class/data"
	xmlns:t="http://catissue/specimen/type/data" xmlns:s="http://catissue/activitystatus/data"
	exclude-result-prefixes="ns0 xs ns1 ns2 ns3">

	<xsl:output method="xml" encoding="UTF-8" indent="yes" />

	<xsl:key name="activitystatus-lookup" match="s:status" use="s:vockey" />
	<xsl:variable name="statuses-top"
		select="document('activitystatus-lookup.xml')/*" />
	<xsl:template match="s:activitystatus">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('activitystatus-lookup', $curr-key)/s:vocvalue" />
	</xsl:template>

	<xsl:key name="specimensite-lookup" match="st:site" use="st:vockey" />
	<xsl:variable name="specimensite-top"
		select="document('specimen-char-site-lookup.xml')/*" />
	<xsl:template match="st:specimensite">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('specimensite-lookup', $curr-key)/st:vocvalue" />
	</xsl:template>

	<xsl:key name="specimenside-lookup" match="d:side" use="d:vockey" />
	<xsl:variable name="specimenside-top"
		select="document('specimen-char-side-lookup.xml')/*" />
	<xsl:template match="d:specimenside">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('specimenside-lookup', $curr-key)/d:vocvalue" />
	</xsl:template>

	<xsl:key name="specimenclass-lookup" match="c:class" use="c:vockey" />
	<xsl:variable name="specimenclass-top"
		select="document('specimen-class-lookup.xml')/*" />
	<xsl:template match="c:specimenclass">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('specimenclass-lookup', $curr-key)/c:vocvalue" />
	</xsl:template>

	<xsl:key name="specimentype-lookup" match="t:type" use="t:vockey" />
	<xsl:variable name="specimentype-top"
		select="document('specimen-type-lookup.xml')/*" />
	<xsl:template match="t:specimentype">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('specimentype-lookup', $curr-key)/t:vocvalue" />
	</xsl:template>

	<xsl:key name="specimenbiopsy-lookup" match="b:biopsy" use="b:vockey" />
	<xsl:variable name="specimenbiopsy-top"
		select="document('specimen-biopsy-lookup.xml')/*" />
	<xsl:template match="b:breastCoreBiopsy">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('specimenbiopsy-lookup', $curr-key)/b:vocvalue" />
	</xsl:template>

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
		<xsl:variable name="observation" select="$component/ns1:section/ns1:entry/ns1:procedure/ns1:entryRelationship/ns1:observation" />
		<specimenDetail>
			<collectionProtocolEvent>
				<xsl:value-of
					select="$observation/ns1:entryRelationship/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code/ns1:originalText='Study Calendar Event Time Point']/ns1:value/@value" />
			</collectionProtocolEvent>
			<specimen>
				<cdmsSpecimenId>
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
					<xsl:call-template name="show-activityStatus">
						<xsl:with-param name="asValue"
							select="$observation/ns1:entryRelationship/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code[@code='263490005'][@codeSystem='2.16.840.1.113883.6.96']]/ns1:value/@code" />
					</xsl:call-template>
				</activityStatus>
				<specimenClass>
					<xsl:call-template name="show-specimen-class">
						<xsl:with-param name="classCode"
							select="$observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2']/ns1:specimen/ns1:specimenRole/ns1:specimenPlayingEntity/ns1:code/@code" />
					</xsl:call-template>
				</specimenClass>
				<specimenType>
					<xsl:call-template name="show-specimen-type">
						<xsl:with-param name="typeCode"
							select="$observation/ns1:entryRelationship/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code[@code='371439000'][@codeSystem='2.16.840.1.113883.6.96']]/ns1:value/@code" />
					</xsl:call-template>
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
						<xsl:call-template name="show-specimen-site">
							<xsl:with-param name="siteCode"
								select="$component/ns1:section/ns1:entry/ns1:procedure[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.14']/ns1:targetSiteCode/@code" />
						</xsl:call-template>
					</tissueSite>
					<tissueSide>
						<xsl:call-template name="show-specimen-side">
							<xsl:with-param name="sideCode"
								select="$component/ns1:section/ns1:entry/ns1:procedure[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.14']/ns1:targetSiteCode/ns1:qualifier[ns1:name[@code='272741003'][@codeSystem='2.16.840.1.113883.6.96']]/ns1:value/@code" />
						</xsl:call-template>
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
					<xsl:call-template name="show-specimen-biopsy">
						<xsl:with-param name="value"
							select="$observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.13'][ns1:code/ns1:originalText='Guidance for Breast Core Biopsy']/ns1:value" />
					</xsl:call-template>
				</guidanceForBreastCoreBiopsyType>
				<otherText>
					<xsl:call-template name="show-specimen-biopsy-otherText">
						<xsl:with-param name="value"
							select="$observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.13'][ns1:code/ns1:originalText='Guidance for Breast Core Biopsy']/ns1:value" />
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

	<!-- show-activityStatus -->
	<xsl:template name="show-activityStatus">
		<xsl:param name="asValue" />
		<xsl:apply-templates select="$statuses-top">
			<xsl:with-param name="curr-key" select="$asValue" />
		</xsl:apply-templates>
	</xsl:template>

	<!-- show-specimen-site -->
	<xsl:template name="show-specimen-site">
		<xsl:param name="siteCode" />
		<xsl:apply-templates select="$specimensite-top">
			<xsl:with-param name="curr-key" select="$siteCode" />
		</xsl:apply-templates>
	</xsl:template>

	<!-- show-specimen-side -->
	<xsl:template name="show-specimen-side">
		<xsl:param name="sideCode" />
		<xsl:apply-templates select="$specimenside-top">
			<xsl:with-param name="curr-key" select="$sideCode" />
		</xsl:apply-templates>
	</xsl:template>

	<!-- show-specimen-class -->
	<xsl:template name="show-specimen-class">
		<xsl:param name="classCode" />
		<xsl:apply-templates select="$specimenclass-top">
			<xsl:with-param name="curr-key" select="$classCode" />
		</xsl:apply-templates>
	</xsl:template>

	<!-- show-specimen-type -->
	<xsl:template name="show-specimen-type">
		<xsl:param name="typeCode" />
		<xsl:apply-templates select="$specimentype-top">
			<xsl:with-param name="curr-key" select="$typeCode" />
		</xsl:apply-templates>
	</xsl:template>

	<!-- show-specimen-biopsy -->
	<xsl:template name="show-specimen-biopsy">
		<xsl:param name="value" />
		<xsl:choose>
			<xsl:when test="$value/@code !=''">
				<xsl:apply-templates select="$specimenbiopsy-top">
					<xsl:with-param name="curr-key" select="$value/@code" />
				</xsl:apply-templates>
			</xsl:when>
			<xsl:when test="$value/@nullFlavor !=''">
				<xsl:apply-templates select="$specimenbiopsy-top">
					<xsl:with-param name="curr-key" select="$value/@nullFlavor" />
				</xsl:apply-templates>
			</xsl:when>
		</xsl:choose>
	</xsl:template>

	<!-- show-specimen-biopsy-otherText -->
	<xsl:template name="show-specimen-biopsy-otherText">
		<xsl:param name="value" />
		<xsl:choose>
			<xsl:when test="$value/@nullFlavor !=''">
				<xsl:value-of select="$value/ns1:originalText" />
			</xsl:when>
		</xsl:choose>
	</xsl:template>

</xsl:stylesheet>