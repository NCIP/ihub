<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:p="http://integration.nci.nih.gov/participant" xmlns:ns0="http://caXchange.nci.nih.gov/messaging"
	xmlns:ns1="urn:hl7-org:v3" xmlns:ns2="http://caXchange.nci.nih.gov/caxchangerequest"
	xmlns:ns3="http://cacis.nci.nih.gov" xmlns:r="http://catissue/race/data"
	xmlns:e="http://catissue/ethnicity/data" xmlns:g="http://catissue/gender/data"
	xmlns:s="http://catissue/activitystatus/data" exclude-result-prefixes="
	ns0 xs ns1 ns2 ns3 ">
	<xsl:output method="xml" indent="yes" />

	<xsl:key name="race-lookup" match="r:race" use="r:vockey" />
	<xsl:variable name="races-top" select="document('race-lookup.xml')/*" />
	<xsl:template match="r:races">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('race-lookup', $curr-key)/r:vocvalue" />
	</xsl:template>

	<xsl:key name="ethnicity-lookup" match="e:ethnicity" use="e:vockey" />
	<xsl:variable name="ethnicities-top" select="document('ethnicity-lookup.xml')/*" />
	<xsl:template match="e:ethnicities">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('ethnicity-lookup', $curr-key)/e:vocvalue" />
	</xsl:template>

	<xsl:key name="gender-lookup" match="g:gender" use="g:vockey" />
	<xsl:variable name="genders-top" select="document('genders-lookup.xml')/*" />
	<xsl:template match="g:genders">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('gender-lookup', $curr-key)/g:vocvalue" />
	</xsl:template>


	<xsl:key name="status-lookup" match="s:status" use="s:vockey" />
	<xsl:variable name="statuses-top"
		select="document('activitystatus-lookup.xml')/*" />
	<xsl:template match="s:activitystatus">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('status-lookup', $curr-key)/s:vocvalue" />
	</xsl:template>

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
							<xsl:call-template name="show-gender">
								<xsl:with-param name="adminGenderCode"
									select="$patientRole/ns1:patient/ns1:administrativeGenderCode" />
							</xsl:call-template>
						</p:gender>
						<p:race>
							<xsl:call-template name="show-race">
								<xsl:with-param name="raceValue"
									select="$patientRole/ns1:patient/ns1:raceCode" />
							</xsl:call-template>
						</p:race>
						<p:ethnicity>
							<xsl:call-template name="show-ethnicity">
								<xsl:with-param name="ethnicGroupCode"
									select="$patientRole/ns1:patient/ns1:ethnicGroupCode" />
							</xsl:call-template>
						</p:ethnicity>
						<p:activityStatus>
							<xsl:call-template name="show-activityStatus">
								<xsl:with-param name="asValue"
									select="//ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code[@code='263490005'][@codeSystem='2.16.840.1.113883.6.96']]/ns1:value/@code" />
							</xsl:call-template>
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
			<xsl:call-template name="show-race">
				<xsl:with-param name="raceValue" select="." />
			</xsl:call-template>
		</p:race>
	</xsl:template>

	<!-- show-race -->
	<xsl:template name="show-race">
		<xsl:param name="raceValue" />
		<xsl:choose>
			<xsl:when test="$raceValue/@code !=''">
				<xsl:apply-templates select="$races-top">
					<xsl:with-param name="curr-key" select="$raceValue/@code" />
				</xsl:apply-templates>
			</xsl:when>
			<xsl:when test="$raceValue/@nullFlavor !=''">
				<xsl:apply-templates select="$races-top">
					<xsl:with-param name="curr-key" select="$raceValue/@nullFlavor" />
				</xsl:apply-templates>
			</xsl:when>
		</xsl:choose>
	</xsl:template>

	<!-- show-gender -->
	<xsl:template name="show-gender">
		<xsl:param name="adminGenderCode" />
		<xsl:choose>
			<xsl:when test="$adminGenderCode/@code !=''">
				<xsl:apply-templates select="$genders-top">
					<xsl:with-param name="curr-key" select="$adminGenderCode/@code" />
				</xsl:apply-templates>
			</xsl:when>
			<xsl:when test="$adminGenderCode/@nullFlavor !=''">
				<xsl:apply-templates select="$genders-top">
					<xsl:with-param name="curr-key" select="$adminGenderCode/@nullFlavor" />
				</xsl:apply-templates>
			</xsl:when>
		</xsl:choose>
	</xsl:template>


	<!-- show-ethnicity -->
	<xsl:template name="show-ethnicity">
		<xsl:param name="ethnicGroupCode" />
		<xsl:choose>
			<xsl:when test="$ethnicGroupCode/@code !=''">
				<xsl:apply-templates select="$ethnicities-top">
					<xsl:with-param name="curr-key" select="$ethnicGroupCode/@code" />
				</xsl:apply-templates>
			</xsl:when>
			<xsl:when test="$ethnicGroupCode/@nullFlavor !=''">
				<xsl:apply-templates select="$ethnicities-top">
					<xsl:with-param name="curr-key" select="$ethnicGroupCode/@nullFlavor" />
				</xsl:apply-templates>
			</xsl:when>
		</xsl:choose>
	</xsl:template>

	<!-- show-activityStatus -->
	<xsl:template name="show-activityStatus">
		<xsl:param name="asValue" />
		<xsl:apply-templates select="$statuses-top">
			<xsl:with-param name="curr-key" select="$asValue" />
		</xsl:apply-templates>
	</xsl:template>


	<!-- format the date 19670131 (or 20110722085054 ) to 1967-01-31 -->
	<xsl:template name="show-dateTime">
		<xsl:param name="dateTime" />
		<xsl:value-of select="substring($dateTime,1,4)" />
		<xsl:value-of select="'-'" />
		<xsl:value-of select="substring($dateTime,5,2)" />
		<xsl:value-of select="'-'" />
		<xsl:value-of select="substring($dateTime,7,2)" />
	</xsl:template>

	<!-- show-id -->
	<xsl:template name="show-id">
		<xsl:param name="id" />
		<xsl:value-of select="$id/@root" />
		<xsl:text>:</xsl:text>
		<xsl:value-of select="$id/@extension" />
	</xsl:template>

</xsl:stylesheet>
