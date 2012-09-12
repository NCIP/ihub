<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:ns0="http://caXchange.nci.nih.gov/messaging" xmlns:ns1="urn:hl7-org:v3"
	xmlns:ns2="http://caXchange.nci.nih.gov/caxchangerequest" xmlns:ns3="http://cacis.nci.nih.gov"
	xmlns:g="http://caaers/adverseevent/grade/data" xmlns:a="http://caaers/adverseevent/attribution/data"
	xmlns:e="http://caaers/adverseevent/expected/data" xmlns:p="http://caaers/adverseevent/periodtype/data"
	xmlns:r="http://caaers/adverseevent/seriousreasons/data"
	exclude-result-prefixes="ns0 xs ns1 ns2 ns3">

	<xsl:output method="xml" encoding="UTF-8" indent="yes" />

	<xsl:key name="grade-lookup" match="g:grade" use="g:vockey" />
	<xsl:variable name="grades-top" select="document('ae-grade-lookup.xml')/*" />
	<xsl:template match="g:grades">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('grade-lookup', $curr-key)/g:vocvalue" />
	</xsl:template>

	<xsl:key name="period-type-lookup" match="p:type" use="p:vockey" />
	<xsl:variable name="period-type-top"
		select="document('ae-period-type-lookup.xml')/*" />
	<xsl:template match="p:periodtype">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('period-type-lookup', $curr-key)/p:vocvalue" />
	</xsl:template>

	<xsl:key name="serious-reason-lookup" match="r:reason" use="r:vockey" />
	<xsl:variable name="serious-reason-top"
		select="document('ae-serious-reasons-lookup.xml')/*" />
	<xsl:template match="r:reasons">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('serious-reason-lookup', $curr-key)/r:vocvalue" />
	</xsl:template>

	<xsl:key name="attribution-lookup" match="a:attribution" use="a:vockey" />
	<xsl:variable name="attribution-top"
		select="document('ae-attribution-lookup.xml')/*" />
	<xsl:template match="a:attributiontype">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('attribution-lookup', $curr-key)/a:vocvalue" />
	</xsl:template>


	<xsl:key name="expected-lookup" match="e:expect" use="e:vockey" />
	<xsl:variable name="expected-top" select="document('ae-expected-lookup.xml')/*" />
	<xsl:template match="e:expected">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('expected-lookup', $curr-key)/e:vocvalue" />
	</xsl:template>

	<xsl:template match="/">
		<ns2:caxchangerequest>
			<ns0:metadata>
				<xsl:copy-of select="//ns2:caxchangerequest/ns0:metadata/node()|@*" />
			</ns0:metadata>
			<adverseeventinput>
				<xsl:variable name="clinicalDocument"
					select="ns3:caCISRequest/ns3:sourceData/ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/ns1:ClinicalDocument" />
				<xsl:variable name="patientRole"
					select="$clinicalDocument/ns1:recordTarget/ns1:patientRole" />
				<criteria>
					<participantIdentifier>
						<xsl:call-template name="show-id">
							<xsl:with-param name="id"
								select="$patientRole/ns1:id[@assigningAuthorityName='iSpy2 Study']" />
						</xsl:call-template>
					</participantIdentifier>
					<studyIdentifier>
						<xsl:call-template name="show-id">
							<xsl:with-param name="id"
								select="$clinicalDocument/ns1:documentationOf/ns1:serviceEvent/ns1:id[../ns1:code/ns1:originalText='clinical trial']" />
						</xsl:call-template>
					</studyIdentifier>
					<course>
						<startDateOfThisCourse>
							<xsl:call-template name="show-dateTime">
								<xsl:with-param name="dateTime"
									select="//ns1:act[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.30']/ns1:effectiveTime/ns1:low/@value" />
							</xsl:call-template>
						</startDateOfThisCourse>
						<endDateOfThisCourse>
							<xsl:call-template name="show-dateTime">
								<xsl:with-param name="dateTime"
									select="//ns1:act[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.30']/ns1:effectiveTime/ns1:high/@value" />
							</xsl:call-template>
						</endDateOfThisCourse>
						<treatmentType>
							<xsl:call-template name="show-period-type">
								<xsl:with-param name="periodType"
									select="//ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code[@code='272117007'][@codeSystem='2.16.840.1.113883.6.96']]/ns1:value/ns1:originalText" />
							</xsl:call-template>
						</treatmentType>
						<treatmentAssignmentCode>
							<xsl:value-of
								select="//ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.13'][ns1:code[@code='225334002'][@codeSystem='2.16.840.1.113883.6.96']]/ns1:value/ns1:originalText" />
						</treatmentAssignmentCode>
					</course>
				</criteria>
				<adverseEventsList>
					<xsl:apply-templates
						select="//ns1:ClinicalDocument/ns1:component/ns1:structuredBody/ns1:component" />
				</adverseEventsList>
			</adverseeventinput>
		</ns2:caxchangerequest>
	</xsl:template>

	<xsl:template
		match="//ns1:ClinicalDocument/ns1:component/ns1:structuredBody/ns1:component">
		<xsl:variable name="component" select="." />
		<xsl:variable name="observation"
			select="$component/ns1:section/ns1:entry/ns1:act/ns1:entryRelationship/ns1:observation" />
		<adverseEvent>
			<verbatim>
				<xsl:value-of
					select="$observation/ns1:entryRelationship/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.9']/ns1:value" />
			</verbatim>
			<ctepCode>
				<xsl:value-of
					select="$observation/ns1:entryRelationship/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.9']/ns1:value/@code" />
			</ctepCode>
			<grade>
				<xsl:call-template name="show-grade">
					<xsl:with-param name="gradeValue"
						select="$observation/ns1:entryRelationship/ns1:observation/ns1:entryRelationship/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.8'][ns1:code/ns1:originalText='Grade']/ns1:value/ns1:originalText" />
				</xsl:call-template>
			</grade>
			<startDate>
				<xsl:call-template name="show-dateTime">
					<xsl:with-param name="dateTime"
						select="$observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.7']/ns1:effectiveTime/ns1:low/@value" />
				</xsl:call-template>
			</startDate>
			<endDate>
				<xsl:call-template name="show-dateTime">
					<xsl:with-param name="dateTime"
						select="$observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.7']/ns1:effectiveTime/ns1:high/@value" />
				</xsl:call-template>
			</endDate>
			<expected>
				<xsl:call-template name="show-expected">
					<xsl:with-param name="expectValue"
						select="$observation/ns1:entryRelationship/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code[@code='410596003'][@codeSystem='2.16.840.1.113883.6.96']]/ns1:value/@code" />
				</xsl:call-template>
			</expected>
			<attributionSummary>
				<xsl:call-template name="show-attribution">
					<xsl:with-param name="attributionValue"
						select="$observation/ns1:entryRelationship/ns1:observation/ns1:entryRelationship/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code[@nullFlavor='OTH']/ns1:originalText='Attribution']/ns1:value" />
				</xsl:call-template>
			</attributionSummary>
			<xsl:for-each
				select="$observation/ns1:entryRelationship/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code/ns1:originalText='serious reason(s)']/ns1:value">
				<outcome>
					<outComeEnumType>
						<xsl:call-template name="show-serious-reasons">
							<xsl:with-param name="reason" select="./ns1:originalText" />
						</xsl:call-template>
					</outComeEnumType>
				</outcome>
			</xsl:for-each>
		</adverseEvent>
	</xsl:template>

	<!-- show-id -->
	<xsl:template name="show-id">
		<xsl:param name="id" />
		<xsl:value-of select="$id/@root" />
		<xsl:text>:</xsl:text>
		<xsl:value-of select="$id/@extension" />
	</xsl:template>

	<!-- show-grade -->
	<xsl:template name="show-grade">
		<xsl:param name="gradeValue" />
		<xsl:apply-templates select="$grades-top">
			<xsl:with-param name="curr-key" select="$gradeValue" />
		</xsl:apply-templates>
	</xsl:template>

	<!-- show-period-type -->
	<xsl:template name="show-period-type">
		<xsl:param name="periodType" />
		<xsl:apply-templates select="$period-type-top">
			<xsl:with-param name="curr-key" select="$periodType" />
		</xsl:apply-templates>
	</xsl:template>

	<!-- show-serious-reasons -->
	<xsl:template name="show-serious-reasons">
		<xsl:param name="reason" />
		<xsl:apply-templates select="$serious-reason-top">
			<xsl:with-param name="curr-key" select="$reason" />
		</xsl:apply-templates>
	</xsl:template>

	<!-- show-attribution -->
	<xsl:template name="show-attribution">
		<xsl:param name="attributionValue" />
		<xsl:choose>
			<xsl:when test="$attributionValue/@code !=''">
				<xsl:apply-templates select="$attribution-top">
					<xsl:with-param name="curr-key" select="$attributionValue/@code" />
				</xsl:apply-templates>
			</xsl:when>
			<xsl:when test="$attributionValue/@nullFlavor !=''">
				<xsl:apply-templates select="$attribution-top">
					<xsl:with-param name="curr-key" select="$attributionValue/@nullFlavor" />
				</xsl:apply-templates>
			</xsl:when>
		</xsl:choose>
	</xsl:template>

	<!-- show-expected -->
	<xsl:template name="show-expected">
		<xsl:param name="expectValue" />
		<xsl:apply-templates select="$expected-top">
			<xsl:with-param name="curr-key" select="$expectValue" />
		</xsl:apply-templates>
	</xsl:template>

	<!-- format the date 20110701 to 2011-07-01-04:00 -->
	<xsl:template name="show-dateTime">
		<xsl:param name="dateTime" />
		<xsl:value-of select="substring($dateTime,1,4)" />
		<xsl:value-of select="'-'" />
		<xsl:value-of select="substring($dateTime,5,2)" />
		<xsl:value-of select="'-'" />
		<xsl:value-of select="substring($dateTime,7,2)" />
		<xsl:value-of select="'-04:00'" />
	</xsl:template>

</xsl:stylesheet>