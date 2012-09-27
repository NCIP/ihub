<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:p="http://integration.nci.nih.gov/participant"
	xmlns:catissue="http://domain.catissuecore.wustl.edu/participant"
	xmlns:ns0="http://caXchange.nci.nih.gov/messaging" xmlns:ns2="http://caXchange.nci.nih.gov/caxchangerequest"
	xmlns:g="http://catissue/gender/data" xmlns:r="http://catissue/race/data">
	<xsl:output method="xml" indent="yes" />

	<xsl:key name="gender-lookup" match="g:gender" use="g:vockey" />
	<xsl:variable name="genders-top"
		select="document('catissue-genders-lookup.xml')/*" />
	<xsl:template match="g:genders">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('gender-lookup', $curr-key)/g:vocvalue" />
	</xsl:template>

	<xsl:key name="race-lookup" match="r:race" use="r:vockey" />
	<xsl:variable name="races-top"
		select="document('catissue-race-lookup.xml')/*" />
	<xsl:template match="r:races">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('race-lookup', $curr-key)/r:vocvalue" />
	</xsl:template>

	<!-- Main -->
	<xsl:template match="/">
		<xsl:call-template name="ConvertToCaTissueParticipantMsg" />
	</xsl:template>
	<xsl:template name="ConvertToCaTissueParticipantMsg">
		<catissue:participant
			xmlns:catissue="http://domain.catissuecore.wustl.edu/participant">
			<catissue:activityStatus>
				<xsl:value-of select="//p:participant/p:activityStatus" />
			</catissue:activityStatus>
			<catissue:birthDate>
				<xsl:call-template name="show-dateTime">
					<xsl:with-param name="dateValue" select="//p:participant/p:birthDate" />
				</xsl:call-template>
			</catissue:birthDate>
			<catissue:ethnicity>
				<xsl:value-of select="//p:participant/p:ethnicity" />
			</catissue:ethnicity>
			<catissue:firstName>TRANSCEND</catissue:firstName> <!-- Hardcode the FirstName -->
			<catissue:gender>
				<xsl:apply-templates select="$genders-top">
					<xsl:with-param name="curr-key" select="//p:participant/p:gender" />
				</xsl:apply-templates>
			</catissue:gender>
			<xsl:variable name="studySubjectIdentifier"
				select="//p:participant/p:assignments/p:assignment/p:studySubjectIdentifier" />
			<catissue:lastName>
				<xsl:value-of select="$studySubjectIdentifier" />
			</catissue:lastName>
			<catissue:vitalStatus>Alive</catissue:vitalStatus>
			<catissue:collectionProtocolRegistrationCollection
				class="set">
				<catissue:collectionProtocolRegistration>
					<catissue:activityStatus>
						<xsl:value-of select="//p:participant/p:activityStatus" />
					</catissue:activityStatus>
					<catissue:consentSignatureDate>
						<xsl:value-of select="substring-before(current-dateTime(),'T')" />
					</catissue:consentSignatureDate>
					<catissue:protocolParticipantIdentifier>
						<xsl:value-of select="$studySubjectIdentifier" />
					</catissue:protocolParticipantIdentifier>
					<catissue:registrationDate>
						<xsl:call-template name="show-dateTime">
							<xsl:with-param name="dateValue"
								select="//p:participant/p:registrationDate" />
						</xsl:call-template>
					</catissue:registrationDate>
					<catissue:specimenCollectionGroupCollection
						class="set" />
					<catissue:collectionProtocol>
						<catissue:shortTitle>
							<xsl:value-of
								select="//p:participant/p:assignments/p:assignment/p:studySite/p:study/p:identifiers/p:identifier[p:type/text()='Other']/p:value" />
						</catissue:shortTitle>
						<catissue:collectionProtocolEventCollection
							class="linked-hash-set" />
						<catissue:childCollectionProtocolCollection
							class="linked-hash-set" />
						<catissue:studyFormContextCollection
							class="set" />
						<catissue:collectionProtocolRegistrationCollection
							class="set" />
						<catissue:siteCollection class="set" />
						<catissue:clinicalDiagnosisCollection
							class="linked-hash-set" />
						<catissue:distributionProtocolCollection
							class="linked-hash-set" />
						<catissue:coordinatorCollection
							class="linked-hash-set" />
						<catissue:assignedProtocolUserCollection
							class="set" />
						<catissue:gridGrouperPrivileges />
					</catissue:collectionProtocol>
					<catissue:participant reference="../../.." />
					<catissue:isToInsertAnticipatorySCGs>true</catissue:isToInsertAnticipatorySCGs>
					<catissue:consentTierResponseCollection
						class="set" />
				</catissue:collectionProtocolRegistration>
			</catissue:collectionProtocolRegistrationCollection>
			<catissue:raceCollection class="set">
				<catissue:race>
					<catissue:raceName>
						<xsl:apply-templates select="$races-top">
							<xsl:with-param name="curr-key" select="//p:participant/p:race" />
						</xsl:apply-templates>
					</catissue:raceName>
					<catissue:participant reference="../../.." />
				</catissue:race>
				<xsl:for-each select="//p:participant/p:raceCollection/p:race">
					<catissue:race>
						<catissue:raceName>
							<xsl:apply-templates select="$races-top">
								<xsl:with-param name="curr-key" select="." />
							</xsl:apply-templates>
						</catissue:raceName>
						<catissue:participant reference="../../.." />
					</catissue:race>
				</xsl:for-each>
			</catissue:raceCollection>
			<catissue:participantMedicalIdentifierCollection
				class="set" />
			<catissue:participantRecordEntryCollection
				class="set" />
		</catissue:participant>
	</xsl:template>

	<!-- show dateTime.. format the date from 1967-01-31 to 19670131 -->
	<xsl:template name="show-dateTime">
		<xsl:param name="dateValue" />
		<xsl:value-of select="substring($dateValue,1,4)" />
		<xsl:value-of select="substring($dateValue,6,2)" />
		<xsl:value-of select="substring($dateValue,9,2)" />
	</xsl:template>

</xsl:stylesheet>