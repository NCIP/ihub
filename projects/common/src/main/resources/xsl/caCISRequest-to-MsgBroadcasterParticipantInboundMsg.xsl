<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:p="http://integration.nci.nih.gov/participant" xmlns:ns0="http://caXchange.nci.nih.gov/messaging"
	xmlns:ns1="urn:hl7-org:v3" xmlns:ns2="http://caXchange.nci.nih.gov/caxchangerequest"
	xmlns:ns3="http://cacis.nci.nih.gov" xmlns:r="http://catissue/race/data"
	xmlns:e="http://catissue/ethnicity/data" xmlns:g="http://catissue/gender/data"
	xmlns:s="http://catissue/acticitystatus/data" exclude-result-prefixes="
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
						<xsl:variable name="patient"
							select="$clinicalDocument/ns1:recordTarget/ns1:patientRole/ns1:patient" />
						<p:firstName>
							<xsl:value-of select="$patient/ns1:name/ns1:given" />
						</p:firstName>
						<p:lastName>
							<xsl:value-of select="$patient/ns1:name/ns1:family" />
						</p:lastName>
						<p:maidenName></p:maidenName>
						<p:middleName></p:middleName>
						<p:birthDate>
							<xsl:for-each select="$patient/ns1:birthTime">
								<xsl:call-template name="show-dateTime" />
							</xsl:for-each>
						</p:birthDate>
						<p:gender>
							<xsl:for-each select="$patient/ns1:administrativeGenderCode">
								<xsl:call-template name="show-gender" />
							</xsl:for-each>
						</p:gender>
						<p:race>
							<xsl:for-each select="$patient/ns1:raceCode">
								<xsl:call-template name="show-race" />
							</xsl:for-each>
						</p:race>
						<p:ethnicity>
							<xsl:for-each select="$patient/ns1:ethnicGroupCode">
								<xsl:call-template name="show-ethnicity" />
							</xsl:for-each>
						</p:ethnicity>
						<p:activityStatus>
							<xsl:for-each
								select="$clinicalDocument/ns1:component/ns1:structuredBody/ns1:component/ns1:section/ns1:entry/ns1:observation/ns1:value[@codeSystem='2.16.840.1.113883.6.96']">
								<xsl:call-template name="show-activityStatus" />
							</xsl:for-each>
						</p:activityStatus>
						<p:registrationDate>
							<xsl:for-each select="$clinicalDocument/ns1:effectiveTime">
								<xsl:call-template name="show-dateTime" />
							</xsl:for-each>
						</p:registrationDate>
						<p:identifiers>
							<p:organizationAssignedIdentifier
								id="1" version="1">
								<p:type>MRN</p:type>
								<p:value>
									<xsl:call-template name="show-MRN">
										<xsl:with-param name="clinicalDocument" select="$clinicalDocument" />
									</xsl:call-template>
								</p:value>
								<p:primaryIndicator>true</p:primaryIndicator>
								<p:organization id="1" version="1">
									<p:name>
										<xsl:call-template name="show-SiteId">
											<xsl:with-param name="clinicalDocument"
												select="$clinicalDocument" />
										</xsl:call-template>
									</p:name>
									<p:nciInstituteCode>
										<xsl:call-template name="show-SiteId">
											<xsl:with-param name="clinicalDocument"
												select="$clinicalDocument" />
										</xsl:call-template>
									</p:nciInstituteCode>
								</p:organization>
							</p:organizationAssignedIdentifier>
							<p:systemAssignedIdentifier id="1"
								version="1">
								<p:type>MRN</p:type>
								<p:value>
									<xsl:call-template name="show-MRN">
										<xsl:with-param name="clinicalDocument" select="$clinicalDocument" />
									</xsl:call-template>
								</p:value>
								<p:primaryIndicator>true</p:primaryIndicator>
								<p:systemName>MRN</p:systemName>
							</p:systemAssignedIdentifier>
						</p:identifiers>
						<p:assignments>
							<p:assignment id="1" version="1">
								<p:studySubjectIdentifier>
									<xsl:call-template name="show-StudySubjectId">
										<xsl:with-param name="clinicalDocument" select="$clinicalDocument" />
									</xsl:call-template>
								</p:studySubjectIdentifier>
								<p:studySite id="1" version="1">
									<p:study id="1" version="1">
										<p:identifiers>
											<p:identifier id="1" version="1">
												<p:type>Other</p:type>
												<p:value>
													<xsl:call-template name="show-StudyId">
														<xsl:with-param name="clinicalDocument"
															select="$clinicalDocument" />
													</xsl:call-template>
												</p:value>
											</p:identifier>
										</p:identifiers>
									</p:study>
									<p:organization id="1" version="1">
										<p:name>
											<xsl:call-template name="show-SiteId">
												<xsl:with-param name="clinicalDocument"
													select="$clinicalDocument" />
											</xsl:call-template>
										</p:name>
										<p:nciInstituteCode>
											<xsl:call-template name="show-SiteId">
												<xsl:with-param name="clinicalDocument"
													select="$clinicalDocument" />
											</xsl:call-template>
										</p:nciInstituteCode>
									</p:organization>
								</p:studySite>
							</p:assignment>
						</p:assignments>
						<p:raceCollection>
							<xsl:call-template name="show-RaceCollection">
								<xsl:with-param name="clinicalDocument" select="$clinicalDocument" />
							</xsl:call-template>
						</p:raceCollection>
					</p:participant>
				</ns0:businessMessagePayload>
			</ns0:request>
		</ns2:caxchangerequest>
	</xsl:template>

	<!-- show-StudySubjectId -->
	<xsl:template name="show-StudySubjectId">
		<xsl:param name="clinicalDocument" />
		<xsl:if
			test="	$clinicalDocument/ns1:recordTarget/ns1:patientRole/ns1:id[1]/@assigningAuthorityName = 'iSpy2 Study'">
			<xsl:call-template name="show-id">
				<xsl:with-param name="id"
					select="$clinicalDocument/ns1:recordTarget/ns1:patientRole/ns1:id[1]" />
			</xsl:call-template>
		</xsl:if>
		<xsl:if
			test="	$clinicalDocument/ns1:recordTarget/ns1:patientRole/ns1:id[2]/@assigningAuthorityName = 'iSpy2 Study'">
			<xsl:call-template name="show-id">
				<xsl:with-param name="id"
					select="$clinicalDocument/ns1:recordTarget/ns1:patientRole/ns1:id[2]" />
			</xsl:call-template>
		</xsl:if>
	</xsl:template>

	<!-- show-MRN -->
	<xsl:template name="show-MRN">
		<xsl:param name="clinicalDocument" />
		<xsl:if
			test="	$clinicalDocument/ns1:recordTarget/ns1:patientRole/ns1:id[1]/@assigningAuthorityName != 'iSpy2 Study'">
			<xsl:call-template name="show-id">
				<xsl:with-param name="id"
					select="$clinicalDocument/ns1:recordTarget/ns1:patientRole/ns1:id[1]" />
			</xsl:call-template>
		</xsl:if>
		<xsl:if
			test="	$clinicalDocument/ns1:recordTarget/ns1:patientRole/ns1:id[2]/@assigningAuthorityName != 'iSpy2 Study'">
			<xsl:call-template name="show-id">
				<xsl:with-param name="id"
					select="$clinicalDocument/ns1:recordTarget/ns1:patientRole/ns1:id[2]" />
			</xsl:call-template>
		</xsl:if>
	</xsl:template>

	<!-- show-RaceCollection -->
	<xsl:template name="show-RaceCollection">
		<xsl:param name="clinicalDocument" />
		<xsl:for-each
			select="$clinicalDocument/ns1:component/ns1:structuredBody/ns1:component/ns1:section/ns1:entry/ns1:observation/ns1:value[@codeSystem='2.16.840.1.113883.6.238']">
			<p:race>
				<xsl:call-template name="show-race" />
			</p:race>
		</xsl:for-each>
	</xsl:template>

	<!-- show-StudyId -->
	<xsl:template name="show-StudyId">
		<xsl:param name="clinicalDocument" />
		<xsl:if
			test="$clinicalDocument/ns1:documentationOf[1]/ns1:serviceEvent/ns1:code/ns1:originalText = 'clinical trial'">
			<xsl:call-template name="show-id">
				<xsl:with-param name="id"
					select="$clinicalDocument/ns1:documentationOf[1]/ns1:serviceEvent/ns1:id" />
			</xsl:call-template>
		</xsl:if>
		<xsl:if
			test="$clinicalDocument/ns1:documentationOf[2]/ns1:serviceEvent/ns1:code/ns1:originalText = 'clinical trial'">
			<xsl:call-template name="show-id">
				<xsl:with-param name="id"
					select="$clinicalDocument/ns1:documentationOf[2]/ns1:serviceEvent/ns1:id" />
			</xsl:call-template>
		</xsl:if>
	</xsl:template>

	<!-- show-SiteId -->
	<xsl:template name="show-SiteId">
		<xsl:param name="clinicalDocument" />
		<xsl:if
			test="$clinicalDocument/ns1:documentationOf[1]/ns1:serviceEvent/ns1:code/ns1:originalText = 'site-specific component of clinical trial'">
			<xsl:call-template name="show-id">
				<xsl:with-param name="id"
					select="$clinicalDocument/ns1:documentationOf[1]/ns1:serviceEvent/ns1:id" />
			</xsl:call-template>
		</xsl:if>
		<xsl:if
			test="$clinicalDocument/ns1:documentationOf[2]/ns1:serviceEvent/ns1:code/ns1:originalText = 'site-specific component of clinical trial'">
			<xsl:call-template name="show-id">
				<xsl:with-param name="id"
					select="$clinicalDocument/ns1:documentationOf[2]/ns1:serviceEvent/ns1:id" />
			</xsl:call-template>
		</xsl:if>
	</xsl:template>

	<xsl:template match="node()|@*">
		<xsl:copy>
			<xsl:apply-templates select="node()|@*" />
		</xsl:copy>
	</xsl:template>

	<!-- show-gender -->
	<xsl:template name="show-gender">
		<xsl:choose>
			<xsl:when test="@code !=''">
				<xsl:apply-templates select="$genders-top">
					<xsl:with-param name="curr-key" select="@code" />
				</xsl:apply-templates>
			</xsl:when>
			<xsl:when test="@nullFlavor !=''">
				<xsl:apply-templates select="$genders-top">
					<xsl:with-param name="curr-key" select="@nullFlavor" />
				</xsl:apply-templates>
			</xsl:when>
		</xsl:choose>
	</xsl:template>


	<!-- show-race -->
	<xsl:template name="show-race">
		<xsl:choose>
			<xsl:when test="@code !=''">
				<xsl:apply-templates select="$races-top">
					<xsl:with-param name="curr-key" select="@code" />
				</xsl:apply-templates>
			</xsl:when>
			<xsl:when test="@nullFlavor !=''">
				<xsl:apply-templates select="$races-top">
					<xsl:with-param name="curr-key" select="@nullFlavor" />
				</xsl:apply-templates>
			</xsl:when>
		</xsl:choose>
	</xsl:template>

	<!-- show-ethnicity -->
	<xsl:template name="show-ethnicity">
		<xsl:choose>
			<xsl:when test="@code !=''">
				<xsl:apply-templates select="$ethnicities-top">
					<xsl:with-param name="curr-key" select="@code" />
				</xsl:apply-templates>
			</xsl:when>
			<xsl:when test="@nullFlavor !=''">
				<xsl:apply-templates select="$ethnicities-top">
					<xsl:with-param name="curr-key" select="@nullFlavor" />
				</xsl:apply-templates>
			</xsl:when>
		</xsl:choose>
	</xsl:template>

	<!-- show-activityStatus -->
	<xsl:template name="show-activityStatus">
		<xsl:apply-templates select="$statuses-top">
			<xsl:with-param name="curr-key" select="@code" />
		</xsl:apply-templates>
	</xsl:template>

	<!-- show dateTime -->
	<xsl:template name="show-dateTime">
		<xsl:choose>
			<xsl:when test="@value">
				<xsl:call-template name="FormatDate">
					<xsl:with-param name="DateTime" select="@value" />
				</xsl:call-template>
			</xsl:when>
		</xsl:choose>
	</xsl:template>

	<!-- format the date 19670131 to 1967-01-31 -->
	<xsl:template name="FormatDate">
		<xsl:param name="DateTime" />
		<xsl:value-of select="substring($DateTime,1,4)" />
		<xsl:value-of select="'-'" />
		<xsl:value-of select="substring($DateTime,5,2)" />
		<xsl:value-of select="'-'" />
		<xsl:value-of select="substring($DateTime,7,2)" />
	</xsl:template>


	<!-- show-id -->
	<xsl:template name="show-id">
		<xsl:param name="id" />
		<xsl:choose>
			<xsl:when test="not($id)">
				<xsl:if test="not(@nullFlavor)">
					<xsl:value-of select="@root" />
					<xsl:text>:</xsl:text>
					<xsl:if test="@extension">
						<xsl:value-of select="@extension" />
					</xsl:if>
				</xsl:if>
			</xsl:when>
			<xsl:otherwise>
				<xsl:if test="not($id/@nullFlavor)">
					<xsl:value-of select="$id/@root" />
					<xsl:text>:</xsl:text>
					<xsl:if test="$id/@extension">
						<xsl:value-of select="$id/@extension" />
					</xsl:if>
				</xsl:if>
				<xsl:if test="$id/@nullFlavor">
					<xsl:call-template name="show-nullFlavor">
						<xsl:with-param name="nf" select="$id/@nullFlavor" />
					</xsl:call-template>
				</xsl:if>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<!-- show-nullFlavor -->
	<xsl:template name="show-nullFlavor">
		<xsl:param name="nf" />
		<xsl:choose>
			<xsl:when test=" $nf = 'NI' ">
				<xsl:text>Not Reported</xsl:text>
			</xsl:when>
			<xsl:when test=" $nf = 'UNK' ">
				<xsl:text>Unknown</xsl:text>
			</xsl:when>
			<xsl:when test=" $nf = 'INV' ">
				<xsl:text>Invalid</xsl:text>
			</xsl:when>
			<xsl:when test=" $nf = 'MSK' ">
				<xsl:text>masked</xsl:text>
			</xsl:when>
			<xsl:when test=" $nf = 'NA' ">
				<xsl:text>not applicable</xsl:text>
			</xsl:when>
			<xsl:when test=" $nf = 'OTH' ">
				<xsl:text>other</xsl:text>
			</xsl:when>
		</xsl:choose>
	</xsl:template>

</xsl:stylesheet>
