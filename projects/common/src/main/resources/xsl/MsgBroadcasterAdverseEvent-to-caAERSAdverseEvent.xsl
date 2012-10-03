<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns0="http://caXchange.nci.nih.gov/messaging"
	xmlns:na="http://cacis.nci.nih.gov" xmlns:ns2="http://caXchange.nci.nih.gov/caxchangerequest"
	xmlns:g="http://caaers/adverseevent/grade/data" xmlns:a="http://caaers/adverseevent/attribution/data"
	xmlns:r="http://caaers/adverseevent/seriousreasons/data">

	<xsl:output method="xml" />

	<xsl:key name="grade-lookup" match="g:grade" use="g:vockey" />
	<xsl:variable name="grades-top"
		select="document('caaers-ae-grade-lookup.xml')/*" />
	<xsl:template match="g:grades">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('grade-lookup', $curr-key)/g:vocvalue" />
	</xsl:template>

	<xsl:key name="serious-reason-lookup" match="r:reason" use="r:vockey" />
	<xsl:variable name="serious-reason-top"
		select="document('caaers-ae-serious-reasons-lookup.xml')/*" />
	<xsl:template match="r:reasons">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('serious-reason-lookup', $curr-key)/r:vocvalue" />
	</xsl:template>

	<xsl:key name="attribution-lookup" match="a:attribution" use="a:vockey" />
	<xsl:variable name="attribution-top"
		select="document('caaers-ae-attribution-lookup.xml')/*" />
	<xsl:template match="a:attributiontype">
		<xsl:param name="curr-key" />
		<xsl:value-of select="key('attribution-lookup', $curr-key)/a:vocvalue" />
	</xsl:template>

	<xsl:template match="/">
		<ns22:adverseevent
			xmlns:ns22="http://schema.integration.caaers.cabig.nci.nih.gov/adverseevent"
			xmlns:ns33="http://schema.integration.caaers.cabig.nci.nih.gov/common">
			<ns22:adverseEvents>
				<xsl:for-each
					select="ns2:caxchangerequest/na:adverseeventinput/na:adverseEventsList/na:adverseEvent">
					<ns22:adverseEvent>
						<verbatim>
							<xsl:value-of select="na:verbatim" />
						</verbatim>
						<grade>
							<xsl:apply-templates select="$grades-top">
								<xsl:with-param name="curr-key" select="na:grade" />
							</xsl:apply-templates>
						</grade>
						<attributionSummary>
							<xsl:apply-templates select="$attribution-top">
								<xsl:with-param name="curr-key" select="na:attributionSummary" />
							</xsl:apply-templates>
						</attributionSummary>
						<startDate>
							<xsl:value-of select="na:startDate" />
						</startDate>
						<endDate>
							<xsl:value-of select="na:endDate" />
						</endDate>
						<ctepCode>
							<xsl:value-of select="na:ctepCode" />
						</ctepCode>
						<externalId>
							<xsl:value-of select="na:externalId"></xsl:value-of>
						</externalId>
						<xsl:for-each select="na:outcome">
							<outcome>
								<outComeEnumType>
									<xsl:apply-templates select="$serious-reason-top">
										<xsl:with-param name="curr-key" select="na:outComeEnumType" />
									</xsl:apply-templates>
								</outComeEnumType>
							</outcome>
						</xsl:for-each>
					</ns22:adverseEvent>
				</xsl:for-each>
			</ns22:adverseEvents>
			<ns22:criteria>
				<studySubjectIdentifier>
					<xsl:value-of
						select="ns2:caxchangerequest/na:adverseeventinput/na:criteria/na:studySubjectIdentifier" />
				</studySubjectIdentifier>
				<studyIdentifier>
					<xsl:value-of
						select="ns2:caxchangerequest/na:adverseeventinput/na:criteria/na:studyIdentifier" />
				</studyIdentifier>
				<course>
					<startDateOfThisCourse>
						<xsl:value-of
							select="ns2:caxchangerequest/na:adverseeventinput/na:criteria/na:course/na:startDateOfThisCourse" />
					</startDateOfThisCourse>
					<endDateOfThisCourse>
						<xsl:value-of
							select="ns2:caxchangerequest/na:adverseeventinput/na:criteria/na:course/na:endDateOfThisCourse" />
					</endDateOfThisCourse>
					<treatmentAssignmentCode>
						<xsl:value-of
							select="ns2:caxchangerequest/na:adverseeventinput/na:criteria/na:course/na:treatmentAssignmentCode" />
					</treatmentAssignmentCode>
				</course>
			</ns22:criteria>
		</ns22:adverseevent>
	</xsl:template>
</xsl:stylesheet>