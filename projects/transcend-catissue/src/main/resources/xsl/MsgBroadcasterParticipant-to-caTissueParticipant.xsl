<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:p="http://integration.nci.nih.gov/participant"
	xmlns:catissue="http://domain.catissuecore.wustl.edu/participant">

	<xsl:output method="xml" indent="yes" />

	<!-- Main -->
	<xsl:template match="/">
		<xsl:call-template name="ConvertToCaTissueParticipantMsg"/>
	</xsl:template>
	
	<xsl:template name="ConvertToCaTissueParticipantMsg">
		<catissue:participant xmlns:catissue="http://domain.catissuecore.wustl.edu/participant">
			<catissue:activityStatus><xsl:value-of select="//p:participant/p:activityStatus"/></catissue:activityStatus>
			<catissue:birthDate><xsl:value-of select="//p:participant/p:birthDate"/></catissue:birthDate>
			<catissue:ethnicity><xsl:value-of select="//p:participant/p:ethnicity"/></catissue:ethnicity>
			<catissue:firstName><xsl:value-of select="//p:participant/p:firstName"/></catissue:firstName>
			<catissue:gender><xsl:value-of select="//p:participant/p:gender"/></catissue:gender>
			<catissue:lastName><xsl:value-of select="//p:participant/p:lastName"/></catissue:lastName>			
			<catissue:socialSecurityNumber><xsl:value-of select="//p:participant/p:identifiers/p:organizationAssignedIdentifier[p:type/text()='SSN']/p:value"/></catissue:socialSecurityNumber>			
			<catissue:vitalStatus>Alive</catissue:vitalStatus>
			<catissue:collectionProtocolRegistrationCollection
				class="set">
				<catissue:collectionProtocolRegistration>
					<catissue:activityStatus>Active</catissue:activityStatus>
					<catissue:consentSignatureDate></catissue:consentSignatureDate>
					<catissue:protocolParticipantIdentifier />
					<catissue:registrationDate></catissue:registrationDate>
					<catissue:specimenCollectionGroupCollection
						class="set" />
					<catissue:collectionProtocol>
						<catissue:title><xsl:value-of select="//p:participant/p:assignments/p:assignment/p:studySite/p:study/p:identifiers/p:identifier[p:type/text()='Study Identifier']/p:value"/></catissue:title>
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
					<catissue:isToInsertAnticipatorySCGs>true
					</catissue:isToInsertAnticipatorySCGs>
				</catissue:collectionProtocolRegistration>
			</catissue:collectionProtocolRegistrationCollection>
			<catissue:raceCollection class="set" />
			<catissue:participantMedicalIdentifierCollection
				class="linked-hash-set" />
			<catissue:participantRecordEntryCollection
				class="set" />
		</catissue:participant>
	</xsl:template>

</xsl:stylesheet>