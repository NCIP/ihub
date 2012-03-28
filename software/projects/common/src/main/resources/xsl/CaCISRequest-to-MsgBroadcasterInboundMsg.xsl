<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns1trim="urn:tolven-org:trim:4.0"
	xmlns:xs="http://www.w3.org/TR/2008/REC-xml-20081126#" xmlns:cacis="http://cacis.nci.nih.gov"
	xmlns:ns1="http://integration.nci.nih.gov/messaging" xmlns:p="http://integration.nci.nih.gov/participant">

	<xsl:output method="xml" indent="yes" />

	<!-- Main -->
	<xsl:template match="/">
		<xsl:call-template name="ConvertToMessageBroadcasterInboundMsg">
		</xsl:call-template>
	</xsl:template>


	<xsl:template name="ConvertToMessageBroadcasterInboundMsg">
		
		<!-- get the trim msg id -->
		<xsl:variable name="trimMsgId"
			select="//ns1trim:trim/ns1trim:tolvenEventId/@id" />


		<ns1:MessageBroadcasterRequestMessage
			xmlns:ns1="http://integration.nci.nih.gov/messaging">
			<ns1:metadata>
				<ns1:serviceType>Registration</ns1:serviceType>
				<ns1:externalIdentifier><xsl:value-of select="$trimMsgId" /></ns1:externalIdentifier>
			</ns1:metadata>
			<ns1:request>
				<ns1:businessMessagePayload>
					<ns1:xmlSchemaDefinition>http://integration.nci.nih.gov/participant</ns1:xmlSchemaDefinition>
					<p:participant id="1" version="1"
						xmlns:p="http://integration.nci.nih.gov/participant" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
						xsi:schemaLocation="http://integration.nci.nih.gov/participant Participant.xsd ">
						
						<xsl:variable name="patientNameAct" select="//ns1trim:trim/ns1trim:act/ns1trim:relationship[@name='patientName']/ns1trim:act"/>
						<p:firstName><xsl:value-of select="$patientNameAct/ns1trim:relationship[@name='firstName']/ns1trim:act/ns1trim:observation/ns1trim:value/ns1trim:ST"/></p:firstName>
						<p:lastName><xsl:value-of select="$patientNameAct/ns1trim:relationship[@name='lastName']/ns1trim:act/ns1trim:observation/ns1trim:value/ns1trim:ST"/></p:lastName>
						<p:maidenName></p:maidenName>
						<p:middleName><xsl:value-of select="$patientNameAct/ns1trim:relationship[@name='middleName']/ns1trim:act/ns1trim:observation/ns1trim:value/ns1trim:ST"/></p:middleName>						
						<p:birthDate><xsl:value-of select="//ns1trim:trim/ns1trim:act/ns1trim:relationship[@name='patientDOB']/ns1trim:act/ns1trim:observation/ns1trim:value/ns1trim:TS/ns1trim:value"/></p:birthDate>
						<xsl:variable name="otherInfoAct" select="//ns1trim:trim/ns1trim:act/ns1trim:relationship[@name='otherInfo']/ns1trim:act"/>
						<p:gender><xsl:value-of select="$otherInfoAct/ns1trim:relationship[@name='gender']/ns1trim:act/ns1trim:title/ns1trim:ST"/></p:gender>
						<xsl:variable name="otherInfoObs" select="$otherInfoAct/ns1trim:observation"/>
						<p:race><xsl:value-of select="$otherInfoObs/ns1trim:value[ns1trim:label/text()='Race']/ns1trim:SETCE/ns1trim:displayName"/></p:race>
						<p:ethnicity><xsl:value-of select="$otherInfoObs/ns1trim:value[ns1trim:label/text()='Ethnicity']/ns1trim:CE/ns1trim:displayName"/></p:ethnicity>
						<p:activityStatus>ACTIVE</p:activityStatus>
						<xsl:variable name="randomPatIdAct" select="//ns1trim:trim/ns1trim:act/ns1trim:relationship[@name='RandomPatientID']/ns1trim:act"/>
						<p:identifiers>
							<p:organizationAssignedIdentifier
								id="1" version="1">
								<p:type>MRN</p:type>
								<p:value><xsl:value-of select="$otherInfoAct/ns1trim:relationship[@name='mrn']/ns1trim:act/ns1trim:observation/ns1trim:value/ns1trim:ST"/></p:value>
								<p:primaryIndicator>true</p:primaryIndicator>
								<p:organization id="1" version="1">
									<p:name><xsl:value-of select="$otherInfoObs/ns1trim:value[ns1trim:label/text()='Institution']/ns1trim:originalText"/></p:name>
									<p:nciInstituteCode><xsl:value-of select="$otherInfoObs/ns1trim:value[ns1trim:label/text()='Institution']/ns1trim:ST"/></p:nciInstituteCode>
								</p:organization>
							</p:organizationAssignedIdentifier>
							<p:organizationAssignedIdentifier
								id="1" version="1">
								<p:type>SSN</p:type>
								<p:value><xsl:value-of select="$otherInfoObs/ns1trim:value[ns1trim:label/text()='SSN']/ns1trim:ST"/></p:value>
								<p:primaryIndicator>false</p:primaryIndicator>
								<p:organization id="1" version="1">
									<p:name>SSN</p:name>
									<p:nciInstituteCode>SSN</p:nciInstituteCode>
								</p:organization>
							</p:organizationAssignedIdentifier>
							<p:systemAssignedIdentifier id="1"
								version="1">
								<p:type>MRN</p:type>
								<p:value><xsl:value-of select="$otherInfoAct/ns1trim:relationship[@name='mrn']/ns1trim:act/ns1trim:observation/ns1trim:value/ns1trim:ST"/></p:value>
								<p:primaryIndicator>true</p:primaryIndicator>
								<p:systemName>MRN</p:systemName>
							</p:systemAssignedIdentifier>
						</p:identifiers>
						<p:assignments>
							<p:assignment id="1" version="1">
								<p:studySubjectIdentifier><xsl:value-of select="$randomPatIdAct/ns1trim:observation/ns1trim:value[ns1trim:label/text()='Patient ID']/ns1trim:ST"/></p:studySubjectIdentifier>
								<p:studySite id="1" version="1">
									<p:study id="1" version="1">
										<p:identifiers>
											<p:identifier id="1" version="1">
												<p:type>Study Identifier</p:type>
												<p:value><xsl:value-of select="$randomPatIdAct/ns1trim:observation/ns1trim:value[ns1trim:label/text()='Study ID']/ns1trim:ST"/></p:value>
											</p:identifier>
										</p:identifiers>
									</p:study>
									<p:organization id="1" version="1">
										<p:name><xsl:value-of select="$otherInfoObs/ns1trim:value[ns1trim:label/text()='Institution']/ns1trim:originalText"/></p:name>
									<p:nciInstituteCode><xsl:value-of select="$otherInfoObs/ns1trim:value[ns1trim:label/text()='Institution']/ns1trim:ST"/></p:nciInstituteCode>
									</p:organization>
								</p:studySite>
							</p:assignment>
						</p:assignments>
					</p:participant>
				</ns1:businessMessagePayload>
			</ns1:request>
		</ns1:MessageBroadcasterRequestMessage>
	</xsl:template>
	
</xsl:stylesheet>
