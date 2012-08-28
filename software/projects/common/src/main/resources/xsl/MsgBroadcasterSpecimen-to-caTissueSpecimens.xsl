<?xml version='1.0' ?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns0="http://caXchange.nci.nih.gov/messaging"
	xmlns:a="http://cacis.nci.nih.gov" xmlns:ns2="http://caXchange.nci.nih.gov/caxchangerequest">
	<xsl:template match="/">
		<specimens>
			<participant>
				<lastName><xsl:value-of select="ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/a:specimens/a:participant/a:cdmsSubjectId" /></lastName>
				<activityStatus><xsl:value-of select="ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/a:specimens/a:participant/a:activityStatus" /></activityStatus>
			</participant>
			<xsl:for-each select="ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/a:specimens/a:specimenDetailsList/a:specimenDetail">
				<specimenDetail>
					<collectionProtocolEvent><xsl:value-of select="a:collectionProtocolEvent" /></collectionProtocolEvent>
					<specimen>
						<xsl:attribute name="class"><xsl:value-of select='concat(string(a:specimen/a:specimenClass), "Specimen")'/></xsl:attribute>
						<initialQuantity><xsl:value-of select="a:specimen/a:initialQuantity" /></initialQuantity>
						<pathologicalStatus>Not Specified</pathologicalStatus>
						<specimenClass><xsl:value-of select="a:specimen/a:specimenClass" /></specimenClass>
						<specimenType><xsl:value-of select="a:specimen/a:specimenType" /></specimenType>
						<specimenCharacteristics>
							<tissueSide><xsl:value-of select="a:specimen/a:specimenCharacteristics/a:tissueSide" /></tissueSide>
							<tissueSite><xsl:value-of select="a:specimen/a:specimenCharacteristics/a:tissueSite" /></tissueSite>
						</specimenCharacteristics>
						<activityStatus><xsl:value-of select="a:specimen/a:activityStatus" /></activityStatus>
						<availableQuantity><xsl:value-of select="a:specimen/a:availableQuantity" /></availableQuantity>
						<barcode><xsl:value-of select="a:specimen/a:barcode" /></barcode>
						<label><xsl:value-of select="a:specimen/a:cdmsSpecimenId" /></label>
						<isAvailable>true</isAvailable>
						<collectionStatus>Collected</collectionStatus>
					</specimen>
					<collectionProtocol>
						<title><xsl:value-of select="a:collectionProtocol/a:title" /></title>
						<shortTitle><xsl:value-of select="a:collectionProtocol/a:shortTitle" /></shortTitle>
					</collectionProtocol>
					<guidanceForBreastCoreBiopsy>
						<guidanceForBreastCoreBiopsyType><xsl:value-of select="a:guidanceForBreastCoreBiopsy/a:guidanceForBreastCoreBiopsyType" /></guidanceForBreastCoreBiopsyType>
						<otherText><xsl:value-of select="a:guidanceForBreastCoreBiopsy/a:otherText" /></otherText>
					</guidanceForBreastCoreBiopsy>
				</specimenDetail>
			</xsl:for-each>
		</specimens>
	</xsl:template>
</xsl:stylesheet>