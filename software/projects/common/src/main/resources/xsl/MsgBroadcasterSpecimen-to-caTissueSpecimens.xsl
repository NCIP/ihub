<?xml version='1.0' ?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns0="http://caXchange.nci.nih.gov/messaging"
	xmlns:a="http://cacis.nci.nih.gov" xmlns:ns2="http://caXchange.nci.nih.gov/caxchangerequest"
	xmlns:b="http://catissue/guidanceForBreastCoreBiopsy/data" xmlns:d="http://catissue/characteristic/side/data"
	xmlns:st="http://catissue/characteristic/site/data" xmlns:c="http://catissue/specimen/class/data"
	xmlns:ca="http://catissue/specimen/class/attribute/data" xmlns:t="http://catissue/specimen/type/data"
	xmlns:s="http://catissue/activitystatus/data">

	<xsl:key name="activitystatus-lookup" match="s:status" use="s:vockey" />
	<xsl:variable name="statuses-top"
		select="document('catissue-participant-activitystatus-lookup.xml')/*" />
	<xsl:template match="s:activitystatus">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('activitystatus-lookup', $curr-key)/s:vocvalue" />
	</xsl:template>

	<xsl:key name="specimensite-lookup" match="st:site" use="st:vockey" />
	<xsl:variable name="specimensite-top"
		select="document('catissue-biospecimen-char-site-lookup.xml')/*" />
	<xsl:template match="st:specimensite">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('specimensite-lookup', $curr-key)/st:vocvalue" />
	</xsl:template>

	<xsl:key name="specimenside-lookup" match="d:side" use="d:vockey" />
	<xsl:variable name="specimenside-top"
		select="document('catissue-biospecimen-char-side-lookup.xml')/*" />
	<xsl:template match="d:specimenside">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('specimenside-lookup', $curr-key)/d:vocvalue" />
	</xsl:template>

	<xsl:key name="specimentype-lookup" match="t:type" use="t:vockey" />
	<xsl:variable name="specimentype-top"
		select="document('catissue-biospecimen-type-lookup.xml')/*" />
	<xsl:template match="t:specimentype">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('specimentype-lookup', $curr-key)/t:vocvalue" />
	</xsl:template>

	<xsl:key name="specimenbiopsy-lookup" match="b:biopsy" use="b:vockey" />
	<xsl:variable name="specimenbiopsy-top"
		select="document('catissue-biospecimen-biopsy-lookup.xml')/*" />
	<xsl:template match="b:breastCoreBiopsy">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('specimenbiopsy-lookup', $curr-key)/b:vocvalue" />
	</xsl:template>

	<xsl:key name="specimenclass-lookup" match="c:class" use="c:vockey" />
	<xsl:variable name="specimenclass-top"
		select="document('catissue-biospecimen-class-lookup.xml')/*" />
	<xsl:template match="c:specimenclass">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('specimenclass-lookup', $curr-key)/c:vocvalue" />
	</xsl:template>

	<xsl:key name="specimenclass-attribute-lookup" match="ca:class"
		use="ca:vockey" />
	<xsl:variable name="specimenclass-attribute-top"
		select="document('catissue-biospecimen-class-attribute-lookup.xml')/*" />
	<xsl:template match="ca:specimenclassattribute">
		<xsl:param name="curr-key" />
		<xsl:value-of
			select="key('specimenclass-attribute-lookup', $curr-key)/ca:vocvalue" />
	</xsl:template>


	<xsl:template match="/">
		<specimens>
			<participant>
				<lastName>
					<xsl:value-of
						select="ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/a:specimens/a:participant/a:cdmsSubjectId" />
				</lastName>
			</participant>
			<xsl:for-each
				select="ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/a:specimens/a:specimenDetailsList/a:specimenDetail">
				<specimenDetail>
					<collectionProtocolEvent>
						<xsl:value-of select="a:collectionProtocolEvent" />
					</collectionProtocolEvent>
					<specimen>
						<xsl:attribute name="class"><xsl:apply-templates
							select="$specimenclass-attribute-top">
								<xsl:with-param name="curr-key"
							select="a:specimen/a:specimenClass" />
							</xsl:apply-templates></xsl:attribute>
						<initialQuantity>
							<xsl:value-of select="a:specimen/a:initialQuantity" />
						</initialQuantity>
						<pathologicalStatus>Not Specified</pathologicalStatus>
						<specimenClass>
							<xsl:apply-templates select="$specimenclass-top">
								<xsl:with-param name="curr-key" select="a:specimen/a:specimenClass" />
							</xsl:apply-templates>
						</specimenClass>
						<specimenType>
							<xsl:apply-templates select="$specimentype-top">
								<xsl:with-param name="curr-key" select="a:specimen/a:specimenType" />
							</xsl:apply-templates>
						</specimenType>
						<specimenCharacteristics>
							<tissueSide>
								<xsl:apply-templates select="$specimenside-top">
									<xsl:with-param name="curr-key"
										select="a:specimen/a:specimenCharacteristics/a:tissueSide" />
								</xsl:apply-templates>
							</tissueSide>
							<tissueSite>
								<xsl:apply-templates select="$specimensite-top">
									<xsl:with-param name="curr-key"
										select="a:specimen/a:specimenCharacteristics/a:tissueSite" />
								</xsl:apply-templates>
							</tissueSite>
						</specimenCharacteristics>
						<activityStatus>
							<xsl:apply-templates select="$statuses-top">
								<xsl:with-param name="curr-key"
									select="a:specimen/a:activityStatus" />
							</xsl:apply-templates>
						</activityStatus>
						<availableQuantity>
							<xsl:value-of select="a:specimen/a:availableQuantity" />
						</availableQuantity>
						<barcode>
							<xsl:value-of select="a:specimen/a:barcode" />
						</barcode>
						<label>
							<xsl:value-of select="a:specimen/a:cdmsSpecimenId" />
						</label>
						<isAvailable>true</isAvailable>
						<collectionStatus>Collected</collectionStatus>
					</specimen>
					<collectionProtocol>
						<title>
							<xsl:value-of select="a:collectionProtocol/a:title" />
						</title>
						<shortTitle>
							<xsl:value-of select="a:collectionProtocol/a:shortTitle" />
						</shortTitle>
					</collectionProtocol>
					<guidanceForBreastCoreBiopsy>
						<guidanceForBreastCoreBiopsyType>
							<xsl:apply-templates select="$specimenbiopsy-top">
								<xsl:with-param name="curr-key"
									select="a:guidanceForBreastCoreBiopsy/a:guidanceForBreastCoreBiopsyType" />
							</xsl:apply-templates>
						</guidanceForBreastCoreBiopsyType>
						<otherText>
							<xsl:value-of select="a:guidanceForBreastCoreBiopsy/a:otherText" />
						</otherText>
					</guidanceForBreastCoreBiopsy>
				</specimenDetail>
			</xsl:for-each>
		</specimens>
	</xsl:template>

</xsl:stylesheet>