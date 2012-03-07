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
		<!--
			***************************** FIXIT: Since the actual trim msg is not
			present, this transformation just creates a constnat msg with just
			the msg identifier changed ******************************
		-->
		<!-- get the trim msg id -->
		<xsl:variable name="trimMsgId"
			select="//ns1trim:trim/ns1trim:tolvenEventId/@id" />


		<ns1:MessageBroadcasterRequestMessage
			xmlns:ns1="http://integration.nci.nih.gov/messaging">
			<ns1:metadata>
				<ns1:serviceType>Registration</ns1:serviceType>
				<ns1:externalIdentifier>
					<xsl:value-of select="$trimMsgId" />
				</ns1:externalIdentifier>
			</ns1:metadata>
			<ns1:request>
				<ns1:businessMessagePayload>
					<ns1:xmlSchemaDefinition>http://integration.nci.nih.gov/participant
					</ns1:xmlSchemaDefinition>
					<p:participant id="1" version="1"
						xmlns:p="http://integration.nci.nih.gov/participant" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
						xsi:schemaLocation="http://integration.nci.nih.gov/participant Participant.xsd ">
						<p:firstName>firstName</p:firstName>
						<p:lastName>lastName</p:lastName>
						<p:maidenName>maidenName</p:maidenName>
						<p:middleName>middleName</p:middleName>
						<p:birthDate>2001-01-01</p:birthDate>
						<p:gender>Male</p:gender>
						<p:race>Asian</p:race>
						<p:ethnicity>Hispanic or Latino</p:ethnicity>
						<p:activityStatus>ACTIVE</p:activityStatus>
						<p:identifiers>
							<p:organizationAssignedIdentifier
								id="1" version="1">
								<p:type>MRN</p:type>
								<p:value>value</p:value>
								<p:primaryIndicator>true</p:primaryIndicator>
								<p:organization id="1" version="1">
									<p:name>name</p:name>
									<p:nciInstituteCode>nciInstituteCode</p:nciInstituteCode>
								</p:organization>
							</p:organizationAssignedIdentifier>
							<p:organizationAssignedIdentifier
								id="1" version="1">
								<p:type>SSN</p:type>
								<p:value>value</p:value>
								<p:primaryIndicator>false</p:primaryIndicator>
								<p:organization id="1" version="1">
									<p:name>SSN</p:name>
									<p:nciInstituteCode>SSN</p:nciInstituteCode>
								</p:organization>
							</p:organizationAssignedIdentifier>
							<p:systemAssignedIdentifier id="1"
								version="1">
								<p:type>MRN</p:type>
								<p:value>value</p:value>
								<p:primaryIndicator>true</p:primaryIndicator>
								<p:systemName>systemName</p:systemName>
							</p:systemAssignedIdentifier>
						</p:identifiers>
						<p:assignments>
							<p:assignment id="1" version="1">
								<p:studySubjectIdentifier>studySubjectIdentifier</p:studySubjectIdentifier>
								<p:studySite id="1" version="1">
									<p:study id="1" version="1">
										<p:identifiers>
											<p:identifier id="1" version="1">
												<p:type>Site Identifier</p:type>
												<p:value>value</p:value>
											</p:identifier>
										</p:identifiers>
									</p:study>
									<p:organization id="1" version="1">
										<p:name>name</p:name>
										<p:nciInstituteCode>nciInstituteCode</p:nciInstituteCode>
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
