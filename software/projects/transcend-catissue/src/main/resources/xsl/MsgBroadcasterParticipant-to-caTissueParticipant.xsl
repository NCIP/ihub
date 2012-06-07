<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" 	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:p="http://integration.nci.nih.gov/participant" 	xmlns:catissue="http://domain.catissuecore.wustl.edu/participant" 	xmlns:g="http://catissue/gender/data">
	<xsl:output method="xml" indent="yes" />
	<!-- Gender lookup -->	
<g:genders>
	<g:gender>
		<g:vockey>Male</g:vockey>
		<g:vocvalue>Male Gender</g:vocvalue>
	</g:gender>
	<g:gender>
		<g:vockey>Female</g:vockey>
		<g:vocvalue>Female Gender</g:vocvalue>
	</g:gender>
</g:genders>
	<xsl:key name="gender-lookup" match="g:gender" use="g:vockey"/>
<!--<xsl:variable name="genders-top" select="document('')/*/g:genders"/> -->
<xsl:variable name="genders-top" select="document('genders-lookup.xml')/*"/> 
	
	<xsl:template match="g:genders">
		<xsl:param name="curr-key"/>
		<xsl:value-of select="key('gender-lookup', $curr-key)/g:vocvalue"/>
	</xsl:template>

	<!-- Main -->
	<xsl:template match="/">
		<xsl:call-template name="ConvertToCaTissueParticipantMsg"/>
	</xsl:template>
	<xsl:template name="ConvertToCaTissueParticipantMsg">
		<catissue:participant xmlns:catissue="http://domain.catissuecore.wustl.edu/participant">
			<catissue:activityStatus>
				<xsl:value-of select="//p:participant/p:activityStatus"/>
			</catissue:activityStatus>
			<catissue:birthDate>
				<xsl:value-of select="//p:participant/p:birthDate"/>
			</catissue:birthDate>
			<catissue:ethnicity>
				<xsl:value-of select="//p:participant/p:ethnicity"/>
			</catissue:ethnicity>
			<catissue:firstName>
				<xsl:value-of select="//p:participant/p:firstName"/>
			</catissue:firstName>
			<catissue:gender>
				<xsl:apply-templates select="$genders-top">
					<xsl:with-param name="curr-key" select="//p:participant/p:gender"/>
				</xsl:apply-templates> 
			</catissue:gender>
			<!--
			<catissue:lastName>
				<xsl:value-of select="//p:participant/p:lastName"/>
			</catissue:lastName>
			
			<catissue:lastName>
				<xsl:value-of select="//p:participant/p:identifiers/p:organizationAssignedIdentifier[p:type/text()='MRN']/p:value"/>
			</catissue:lastName>
			-->
			<xsl:variable name="studySubjectIdentifier" select="//p:participant/p:assignments/p:assignment/p:studySubjectIdentifier"/>
			<catissue:lastName>
				<xsl:value-of select="$studySubjectIdentifier"/>
			</catissue:lastName>			
			<catissue:socialSecurityNumber>
				<xsl:value-of select="//p:participant/p:identifiers/p:organizationAssignedIdentifier[p:type/text()='SSN']/p:value"/>
			</catissue:socialSecurityNumber>
			<catissue:vitalStatus>Alive</catissue:vitalStatus>
			<catissue:collectionProtocolRegistrationCollection	class="set">
				<catissue:collectionProtocolRegistration>
					<catissue:activityStatus>Active</catissue:activityStatus>
					<catissue:consentSignatureDate>
						<xsl:value-of  select="substring-before(current-dateTime(),'T')"/>
					</catissue:consentSignatureDate>
					<catissue:protocolParticipantIdentifier>
						<xsl:value-of select="$studySubjectIdentifier"/>
					</catissue:protocolParticipantIdentifier>
					<catissue:registrationDate>
						<xsl:value-of select="//p:participant/p:registrationDate"/>
					</catissue:registrationDate>
					<catissue:specimenCollectionGroupCollection	class="set" />
					<catissue:collectionProtocol>
						<catissue:shortTitle>
							<xsl:value-of select="//p:participant/p:assignments/p:assignment/p:studySite/p:study/p:identifiers/p:identifier[p:type/text()='Protocol Authority Identifier']/p:value"/>
						</catissue:shortTitle>
						<catissue:collectionProtocolEventCollection	class="linked-hash-set" />
						<catissue:childCollectionProtocolCollection	class="linked-hash-set" />
						<catissue:studyFormContextCollection	class="set" />
						<catissue:collectionProtocolRegistrationCollection	class="set" />
						<catissue:siteCollection class="set" />
						<catissue:clinicalDiagnosisCollection	class="linked-hash-set" />
						<catissue:distributionProtocolCollection	class="linked-hash-set" />
						<catissue:coordinatorCollection	class="linked-hash-set" />
						<catissue:assignedProtocolUserCollection	class="set" />
						<catissue:gridGrouperPrivileges />
					</catissue:collectionProtocol>
					<catissue:participant reference="../../.." />
					<catissue:isToInsertAnticipatorySCGs>true</catissue:isToInsertAnticipatorySCGs>
				</catissue:collectionProtocolRegistration>
			</catissue:collectionProtocolRegistrationCollection>
			<catissue:raceCollection class="set">
				<catissue:race>
					<catissue:raceName>
						<xsl:value-of select="//p:participant/p:race"/>
					</catissue:raceName>
					<catissue:participant reference="../../.."/>
				</catissue:race>
			</catissue:raceCollection>
			<catissue:participantMedicalIdentifierCollection class="set" />
			<!--
			<catissue:participantMedicalIdentifierCollection class="linked-hash-set">			
				<catissue:participantMedicalIdentifier>
					<catissue:medicalRecordNumber><xsl:value-of select="//p:participant/p:identifiers/p:organizationAssignedIdentifier[p:type/text()='MRN']/p:value"/></catissue:medicalRecordNumber>
					<catissue:site>
						<catissue:name><xsl:value-of select="//p:participant/p:assignments/p:assignment/p:studySite/p:organization/p:nciInstituteCode"/></catissue:name>
						<catissue:assignedSiteUserCollection class="set"/>
						<catissue:collectionProtocolCollection class="set"/>
					</catissue:site>
					<catissue:participant reference="../../.."/>
				</catissue:participantMedicalIdentifier>
			</catissue:participantMedicalIdentifierCollection>
			-->
			<catissue:participantRecordEntryCollection	class="set" />
		</catissue:participant>
	</xsl:template>
</xsl:stylesheet>