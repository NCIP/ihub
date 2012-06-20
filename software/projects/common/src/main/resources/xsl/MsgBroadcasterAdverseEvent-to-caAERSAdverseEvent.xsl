<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:ns0="http://caXchange.nci.nih.gov/messaging"
	xmlns:a="http://cacis.nci.nih.gov" xmlns:ns2="http://caXchange.nci.nih.gov/caxchangerequest">

	<xsl:output method="xml" />
	<xsl:template match="/">
		<ns22:adverseevent
			xmlns:ns22="http://schema.integration.caaers.cabig.nci.nih.gov/adverseevent"
			xmlns:ns33="http://schema.integration.caaers.cabig.nci.nih.gov/common">
			<ns22:adverseEvents>
				<xsl:for-each
					select="ns2:caxchangerequest/a:adverseevents/a:adverseEventsList/a:adverseEvent">
					<ns22:adverseEvent>
						<verbatim>
							<xsl:value-of select="a:verbatim" />
						</verbatim>
						<grade>
							<xsl:value-of select="a:grade" />
						</grade>
						<expected>
							<xsl:value-of select="a:expected" />
						</expected>
						<attributionSummary>
							<xsl:value-of select="a:attribution" />
						</attributionSummary>
						<startDate>
							<xsl:value-of select="a:onsetDate" />
						</startDate>
						<endDate>
							<xsl:value-of select="a:resolutionDate" />
						</endDate>
						<ctepCode>
							<xsl:value-of select="a:codedTerm" />
						</ctepCode>
						<xsl:for-each select="a:seriousReasonsList/a:reason">
							<outcome>
								<outComeEnumType>
									<xsl:value-of select="." />
								</outComeEnumType>
							</outcome>
						</xsl:for-each>
					</ns22:adverseEvent>
				</xsl:for-each>
			</ns22:adverseEvents>
			<ns22:criteria>
				<participantIdentifier>
					<xsl:value-of
						select="ns2:caxchangerequest/a:adverseevents/a:participantInfo/a:studySubjectIdentifier" />
				</participantIdentifier>
				<studyIdentifier>
					<xsl:value-of
						select="ns2:caxchangerequest/a:adverseevents/a:studyInfo/a:studyIdentifier" />
				</studyIdentifier>
				<course>
					<startDateOfThisCourse>
						<xsl:value-of
							select="ns2:caxchangerequest/a:adverseevents/a:timeframeInfo/a:reportingPeriod/a:startDateOfThisCourse" />
					</startDateOfThisCourse>
					<endDateOfThisCourse>
						<xsl:value-of
							select="ns2:caxchangerequest/a:adverseevents/a:timeframeInfo/a:reportingPeriod/a:endDateOfThisCourse" />
					</endDateOfThisCourse>
					<treatmentType>
						<xsl:value-of
							select="ns2:caxchangerequest/a:adverseevents/a:timeframeInfo/a:periodType" />
					</treatmentType>
					<treatmentAssignmentCode>
						<xsl:value-of
							select="ns2:caxchangerequest/a:adverseevents/a:timeframeInfo/a:assignedTreatment" />
					</treatmentAssignmentCode>
				</course>
			</ns22:criteria>
		</ns22:adverseevent>
	</xsl:template>
</xsl:stylesheet>