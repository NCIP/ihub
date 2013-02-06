<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:ns0="http://caXchange.nci.nih.gov/messaging" xmlns:ns1="urn:hl7-org:v3"
	xmlns:ns2="http://caXchange.nci.nih.gov/caxchangerequest" xmlns:ns3="http://cacis.nci.nih.gov"
	xmlns:g="http://caaers/adverseevent/grade/data" xmlns:a="http://caaers/adverseevent/attribution/data"
	xmlns:r="http://caaers/adverseevent/seriousreasons/data"
	exclude-result-prefixes="ns0 xs ns1 ns2 ns3">

	<xsl:output method="xml" encoding="UTF-8" indent="yes" />

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
					<studySubjectIdentifier>
						<!-- study subject identifier -->
						<xsl:call-template name="show-id">
							<xsl:with-param name="id"
								select="$patientRole/ns1:id[@assigningAuthorityName='iSpy2 Study']" />
						</xsl:call-template>
					</studySubjectIdentifier>
					<studyIdentifier>
						<xsl:call-template name="show-id">
							<xsl:with-param name="id"
								select="$clinicalDocument/ns1:documentationOf/ns1:serviceEvent/ns1:id[../ns1:code/ns1:originalText='clinical trial']" />
						</xsl:call-template>
					</studyIdentifier>
					<course>
						<treatmentAssignmentCode>
							<!-- Assigned Treatment -->
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
					select="$observation/ns1:entryRelationship/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.9']/ns1:value/ns1:originalText" />
			</verbatim>
			<adverseEventCtepTerm>
				<ctepCode>
				<!-- adverse event coded term -->
				<xsl:value-of
					select="$observation/ns1:entryRelationship/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.9']/ns1:value/@code" />
				</ctepCode>
				<otherSpecify></otherSpecify>
			</adverseEventCtepTerm>			
			<grade>
				<xsl:value-of
					select="$observation/ns1:entryRelationship/ns1:observation/ns1:entryRelationship/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.8'][ns1:code/ns1:originalText='Grade']/ns1:value/ns1:originalText"></xsl:value-of>
			</grade>
			<startDate>
				<!-- Onset Date -->
				<xsl:call-template name="show-dateTime">
					<xsl:with-param name="dateTime"
						select="$observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.7']/ns1:effectiveTime/ns1:low/@value" />
				</xsl:call-template>
			</startDate>
			<endDate>
				<!-- Resolution Date -->
				<xsl:call-template name="show-dateTime">
					<xsl:with-param name="dateTime"
						select="$observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.7']/ns1:effectiveTime/ns1:high/@value" />
				</xsl:call-template>
			</endDate>
			<attributionSummary>
				<!-- Attribution -->
				<xsl:call-template name="get-attribution-code">
					<xsl:with-param name="attributionValue"
						select="$observation/ns1:entryRelationship/ns1:observation/ns1:entryRelationship/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code[@nullFlavor='OTH']/ns1:originalText='Attribution']/ns1:value" />
				</xsl:call-template>
			</attributionSummary>
			<!-- Adverse event unique identifier -->
			<externalId>				
				<xsl:call-template name="show-id">
							<xsl:with-param name="id"
								select="$observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.7']/ns1:id" />
						</xsl:call-template>
			</externalId>
			<!-- Serious Reason(s) -->
			<xsl:for-each
				select="$observation/ns1:entryRelationship/ns1:observation[ns1:templateId/@root='2.16.840.1.113883.10.20.22.4.2'][ns1:code/ns1:originalText='serious reason(s)']/ns1:value">
				<outcome>
					<outComeEnumType>
						<xsl:call-template name="get-serious-reason-code">
							<xsl:with-param name="reason" select="." />
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



	<!-- get-serious-reason-code -->
	<xsl:template name="get-serious-reason-code">
		<xsl:param name="reason" />
		<xsl:choose>
			<xsl:when test="$reason/@code !=''">
				<xsl:value-of select="$reason/@code"></xsl:value-of>
			</xsl:when>
			<xsl:when test="$reason/@nullFlavor !=''">
				<xsl:value-of select="$reason/@nullFlavor"></xsl:value-of>
			</xsl:when>
		</xsl:choose>
	</xsl:template>

	<!-- get-attribution-code -->
	<xsl:template name="get-attribution-code">
		<xsl:param name="attributionValue" />
		<xsl:choose>
			<xsl:when test="$attributionValue/@code !=''">
				<xsl:value-of select="$attributionValue/@code"></xsl:value-of>
			</xsl:when>
			<xsl:when test="$attributionValue/@nullFlavor !=''">
				<xsl:value-of select="$attributionValue/ns1:originalText"></xsl:value-of>
			</xsl:when>
		</xsl:choose>
	</xsl:template>


	<!-- format the date 20110701 to 2011-07-01-04:00 -->
	<xsl:template name="show-dateTime">
		<xsl:param name="dateTime" />
		<xsl:value-of select="substring($dateTime,1,4)" />
		<xsl:value-of select="'-'" />
		<xsl:value-of select="substring($dateTime,5,2)" />
		<xsl:value-of select="'-'" />
		<xsl:value-of select="substring($dateTime,7,2)" />		
	</xsl:template>

</xsl:stylesheet>