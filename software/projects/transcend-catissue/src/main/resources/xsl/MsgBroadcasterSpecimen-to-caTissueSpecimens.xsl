<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns0="http://caXchange.nci.nih.gov/caxchangerequest" xmlns:ns1="http://caXchange.nci.nih.gov/messaging" xmlns:ns2="http://cacis.nci.nih.gov" xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="ns0 ns1 ns2 xs">
	<xsl:output method="xml" encoding="UTF-8" indent="yes"/>
	<xsl:template match="/">
		<specimens>
			<xsl:for-each select="ns0:caxchangerequest">
				<participant>
					<lastName><xsl:value-of select="string(ns1:request/ns1:businessMessagePayload/ns2:specimens/ns2:participant/ns2:cdmsSubjectId)"/></lastName>
					<activityStatus><xsl:value-of select="string(ns1:request/ns1:businessMessagePayload/ns2:specimens/ns2:participant/ns2:activityStatus)"/></activityStatus>
				</participant>
				<specimenDetail>
					<collectionProtocolEvent><xsl:value-of select="string(ns1:request/ns1:businessMessagePayload/ns2:specimens/ns2:specimenDetailsList/ns2:specimenDetail/ns2:collectionProtocolEvent)" /></collectionProtocolEvent>
					<specimen>
						<xsl:attribute name="class"><xsl:value-of select='concat(string(ns1:request/ns1:businessMessagePayload/ns2:specimens/ns2:specimenDetailsList/ns2:specimenDetail/ns2:specimen/ns2:specimenClass), "Specimen")'/></xsl:attribute>
						<initialQuantity><xsl:value-of select="string(floor(number(string(ns1:request/ns1:businessMessagePayload/ns2:specimens/ns2:specimenDetailsList/ns2:specimenDetail/ns2:specimen/ns2:initialQuantity))))"/></initialQuantity>
						<pathologicalStatus><xsl:value-of select="string(ns1:request/ns1:businessMessagePayload/ns2:specimens/ns2:specimenDetailsList/ns2:specimenDetail/ns2:specimen/ns2:pathologicalStatus)"/></pathologicalStatus>
						<specimenClass><xsl:value-of select="string(ns1:request/ns1:businessMessagePayload/ns2:specimens/ns2:specimenDetailsList/ns2:specimenDetail/ns2:specimen/ns2:specimenClass)"/></specimenClass>
						<specimenType><xsl:value-of select="string(ns1:request/ns1:businessMessagePayload/ns2:specimens/ns2:specimenDetailsList/ns2:specimenDetail/ns2:specimen/ns2:specimenType)"/></specimenType>
						<specimenCharacteristics>
							<tissueSide><xsl:value-of select="string(ns1:request/ns1:businessMessagePayload/ns2:specimens/ns2:specimenDetailsList/ns2:specimenDetail/ns2:specimen/ns2:specimenCharacteristics/ns2:tissueSide)"/></tissueSide>
							<tissueSite><xsl:value-of select="string(ns1:request/ns1:businessMessagePayload/ns2:specimens/ns2:specimenDetailsList/ns2:specimenDetail/ns2:specimen/ns2:specimenCharacteristics/ns2:tissueSite)"/></tissueSite>
						</specimenCharacteristics>
						<activityStatus><xsl:value-of select="string(ns1:request/ns1:businessMessagePayload/ns2:specimens/ns2:specimenDetailsList/ns2:specimenDetail/ns2:specimen/ns2:activityStatus)"/></activityStatus>
						<availableQuantity><xsl:value-of select="string(floor(number(string(ns1:request/ns1:businessMessagePayload/ns2:specimens/ns2:specimenDetailsList/ns2:specimenDetail/ns2:specimen/ns2:availableQuantity))))"/></availableQuantity>
						<barcode><xsl:value-of select="string(ns1:request/ns1:businessMessagePayload/ns2:specimens/ns2:specimenDetailsList/ns2:specimenDetail/ns2:specimen/ns2:barcode)"/></barcode>
						<label><xsl:value-of select="string(ns1:request/ns1:businessMessagePayload/ns2:specimens/ns2:specimenDetailsList/ns2:specimenDetail/ns2:specimen/ns2:cdmsSpecimenId)"/></label>
						<isAvailable>true</isAvailable>
						<collectionStatus>Collected</collectionStatus>		
					</specimen>
					<collectionProtocol>
						<title><xsl:value-of select="string(ns1:request/ns1:businessMessagePayload/ns2:specimens/ns2:specimenDetailsList/ns2:specimenDetail/ns2:collectionProtocol/ns2:title)"/></title>
						<shortTitle><xsl:value-of select="string(ns1:request/ns1:businessMessagePayload/ns2:specimens/ns2:specimenDetailsList/ns2:specimenDetail/ns2:collectionProtocol/ns2:shortTitle)"/></shortTitle>
					</collectionProtocol>
				</specimenDetail>
			</xsl:for-each>
		</specimens>
	</xsl:template>
</xsl:stylesheet>