<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:p="http://integration.nci.nih.gov/participant" xmlns:ns0="http://caXchange.nci.nih.gov/messaging"
	xmlns:ns1="urn:hl7-org:v3" xmlns:ns2="http://caXchange.nci.nih.gov/caxchangerequest"
	xmlns:ns3="http://cacis.nci.nih.gov" exclude-result-prefixes="ns0 xs ns1 ns2 ns3 ">
	<xsl:output method="xml" indent="yes" />

	<xsl:template match="/">
		<ns2:caxchangerequest>
			<ns0:metadata>
				<xsl:copy-of select="//ns2:caxchangerequest/ns0:metadata/node()|@*" />
			</ns0:metadata>
			<ns0:request>
				<ns0:businessMessagePayload>
					<p:participant id="1" version="1"
						xmlns:p="http://integration.nci.nih.gov/participant" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
						xsi:schemaLocation="http://integration.nci.nih.gov/participant Participant.xsd ">
						<xsl:variable name="clinicalDocument"
							select="ns3:caCISRequest/ns3:sourceData/ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/ns1:ClinicalDocument" />
						<xsl:variable name="patientRole"
							select="$clinicalDocument/ns1:recordTarget/ns1:patientRole" />
						<p:firstName>
							<xsl:value-of select="$patientRole/ns1:patient/ns1:name/ns1:given" />
						</p:firstName>
						<p:lastName>
							<xsl:value-of select="$patientRole/ns1:patient/ns1:name/ns1:family" />
						</p:lastName>
						<p:maidenName></p:maidenName>
						<p:middleName></p:middleName>
						<p:birthDate>
							<xsl:call-template name="show-dateTime">
								<xsl:with-param name="dateTime"
									select="$patientRole/ns1:patient/ns1:birthTime/@value" />
							</xsl:call-template>
						</p:birthDate>
						<p:gender>
							<xsl:call-template name="get-gender-code">
								<xsl:with-param name="genderTag"
									select="$patientRole/ns1:patient/ns1:administrativeGenderCode" />
							</xsl:call-template>
						</p:gender>
						<p:race>
							<xsl:call-template name="get-race-code">
								<xsl:with-param name="raceTag"
									select="$patientRole/ns1:patient/ns1:raceCode" />
							</xsl:call-template>
						</p:race>
						<p:ethnicity>
							<xsl:call-template name="get-ethnicity-code">
								<xsl:with-param name="ethnicityTag"
									select="$patientRole/ns1:patient/ns1:ethnicGroupCode" />
							</xsl:call-template>
						</p:ethnicity>
						<p:activityStatus>
							<xsl:value-of
								select="//ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code[@code='263490005'][@codeSystem='2.16.840.1.113883.6.96']]/ns1:value/@code" />
						</p:activityStatus>
						<p:registrationDate>
							<xsl:call-template name="show-dateTime">
								<xsl:with-param name="dateTime"
									select="$clinicalDocument/ns1:effectiveTime/@value" />
							</xsl:call-template>
						</p:registrationDate>
						<p:identifiers>
							<p:organizationAssignedIdentifier
								id="1" version="1">
								<p:type>MRN</p:type>
								<p:value>
									<!-- patient MRN -->
									<xsl:call-template name="show-id">
										<xsl:with-param name="id"
											select="$patientRole/ns1:id[@assigningAuthorityName !='iSpy2 Study']" />
									</xsl:call-template>
								</p:value>
								<p:primaryIndicator>true</p:primaryIndicator>
								<p:organization id="1" version="1">
									<p:name>
										<!-- site identifier -->
										<xsl:call-template name="show-id">
											<xsl:with-param name="id"
												select="$clinicalDocument/ns1:documentationOf/ns1:serviceEvent/ns1:id[../ns1:code/ns1:originalText='site-specific component of clinical trial']" />
										</xsl:call-template>
									</p:name>
									<p:nciInstituteCode>
										<xsl:call-template name="show-id">
											<xsl:with-param name="id"
												select="$clinicalDocument/ns1:documentationOf/ns1:serviceEvent/ns1:id[../ns1:code/ns1:originalText='site-specific component of clinical trial']" />
										</xsl:call-template>
									</p:nciInstituteCode>
								</p:organization>
							</p:organizationAssignedIdentifier>
							<p:systemAssignedIdentifier id="1"
								version="1">
								<p:type>MRN</p:type>
								<p:value>
									<xsl:call-template name="show-id">
										<xsl:with-param name="id"
											select="$patientRole/ns1:id[@assigningAuthorityName !='iSpy2 Study']" />
									</xsl:call-template>
								</p:value>
								<p:primaryIndicator>true</p:primaryIndicator>
								<p:systemName>MRN</p:systemName>
							</p:systemAssignedIdentifier>
						</p:identifiers>
						<p:assignments>
							<p:assignment id="1" version="1">
								<p:studySubjectIdentifier>
									<!-- study subject identifier -->
									<xsl:call-template name="show-id">
										<xsl:with-param name="id"
											select="$patientRole/ns1:id[@assigningAuthorityName='iSpy2 Study']" />
									</xsl:call-template>
								</p:studySubjectIdentifier>
								<p:studySite id="1" version="1">
									<p:study id="1" version="1">
										<p:identifiers>
											<p:identifier id="1" version="1">
												<p:type>Other</p:type>
												<p:value>
													<!-- study identifier -->
													<xsl:call-template name="show-id">
														<xsl:with-param name="id"
															select="$clinicalDocument/ns1:documentationOf/ns1:serviceEvent/ns1:id[../ns1:code/ns1:originalText='clinical trial']" />
													</xsl:call-template>
												</p:value>
											</p:identifier>
										</p:identifiers>
									</p:study>
									<p:organization id="1" version="1">
										<p:name>
											<!-- site identifier -->
											<xsl:call-template name="show-id">
												<xsl:with-param name="id"
													select="$clinicalDocument/ns1:documentationOf/ns1:serviceEvent/ns1:id[../ns1:code/ns1:originalText='site-specific component of clinical trial']" />
											</xsl:call-template>
										</p:name>
										<p:nciInstituteCode>
											<xsl:call-template name="show-id">
												<xsl:with-param name="id"
													select="$clinicalDocument/ns1:documentationOf/ns1:serviceEvent/ns1:id[../ns1:code/ns1:originalText='site-specific component of clinical trial']" />
											</xsl:call-template>
										</p:nciInstituteCode>
									</p:organization>
								</p:studySite>
							</p:assignment>
						</p:assignments>
						<p:raceCollection>
							<xsl:apply-templates
								select="//ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code[@code='103579009'][@codeSystem='2.16.840.1.113883.6.96']]/ns1:value" />
						</p:raceCollection>
					</p:participant>
				</ns0:businessMessagePayload>
			</ns0:request>
		</ns2:caxchangerequest>
	</xsl:template>

	<xsl:template match="node()|@*">
		<xsl:copy>
			<xsl:apply-templates select="node()|@*" />
		</xsl:copy>
	</xsl:template>

	<!-- show-RaceCollection -->
	<xsl:template
		match="//ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code[@code='103579009'][@codeSystem='2.16.840.1.113883.6.96']]/ns1:value">
		<p:race>
			<xsl:call-template name="get-race-code">
				<xsl:with-param name="raceTag" select="." />
			</xsl:call-template>
		</p:race>
	</xsl:template>

	<!-- get-race-code -->
	<xsl:template name="get-race-code">
		<xsl:param name="raceTag" />
		<xsl:choose>
			<xsl:when test="$raceTag/@code !=''">
				<xsl:value-of select="$raceTag/@code" />
			</xsl:when>
			<xsl:when test="$raceTag/@nullFlavor !=''">
				<xsl:value-of select="$raceTag/@nullFlavor" />
			</xsl:when>
		</xsl:choose>
	</xsl:template>

	<!-- get-ethnicity-code -->
	<xsl:template name="get-ethnicity-code">
		<xsl:param name="ethnicityTag" />
		<xsl:choose>
			<xsl:when test="$ethnicityTag/@code !=''">
				<xsl:value-of select="$ethnicityTag/@code" />
			</xsl:when>
			<xsl:when test="$ethnicityTag/@nullFlavor !=''">
				<xsl:value-of select="$ethnicityTag/@nullFlavor" />
			</xsl:when>
		</xsl:choose>
	</xsl:template>

	<!-- get-gender-code -->
	<xsl:template name="get-gender-code">
		<xsl:param name="genderTag" />
		<xsl:choose>
			<xsl:when test="$genderTag/@code !=''">
				<xsl:value-of select="$genderTag/@code" />
			</xsl:when>
			<xsl:when test="$genderTag/@nullFlavor !=''">
				<xsl:value-of select="$genderTag/@nullFlavor" />
			</xsl:when>
		</xsl:choose>
	</xsl:template>


	<!-- format the date 19670131 (or 20110722085054 ) to 1967-01-31 -->
	<xsl:template name="show-dateTime">
		<xsl:param name="dateTime" />
		<xsl:choose>
			<xsl:when test="string-length($dateTime)=0">
				<xsl:text></xsl:text>
			</xsl:when>
			<xsl:when test="string-length($dateTime)!=0">
				<xsl:value-of select="substring($dateTime,1,4)" />
				<xsl:value-of select="'-'" />
				<xsl:value-of select="substring($dateTime,5,2)" />
				<xsl:value-of select="'-'" />
				<xsl:value-of select="substring($dateTime,7,2)" />
			</xsl:when>
		</xsl:choose>

	</xsl:template>

	<!-- show-id -->
	<xsl:template name="show-id">
		<xsl:param name="id" />
		<xsl:value-of select="$id/@root" />
		<xsl:text>:</xsl:text>
		<xsl:value-of select="$id/@extension" />
	</xsl:template>

</xsl:stylesheet>
