<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:output method="xml" />
	<xsl:template match="/">
		<ns2:adverseevent
			xmlns:ns2="http://schema.integration.caaers.cabig.nci.nih.gov/adverseevent"
			xmlns:ns3="http://schema.integration.caaers.cabig.nci.nih.gov/common">
			<ns2:adverseEvents>
				<xsl:for-each select="adverseevents/adverseEventsList/adverseEvent">
					<ns2:adverseEvent>
						<verbatim>
							<xsl:value-of select="verbatim" />
						</verbatim>
						<grade>
							<xsl:value-of select="grade" />
						</grade>
						<expected>
							<xsl:value-of select="expected" />
						</expected>
						<attributionSummary>
							<xsl:value-of select="attribution" />
						</attributionSummary>
						<startDate>
							<xsl:value-of select="onsetDate" />
						</startDate>
						<endDate>
							<xsl:value-of select="resolutionDate" />
						</endDate>
						<ctepCode>
							<xsl:value-of select="codedTerm" />
						</ctepCode>
						<xsl:for-each select="seriousReasonsList/reason">
							<outcome>
								<outComeEnumType>
									<xsl:value-of select="." />
								</outComeEnumType>
							</outcome>
						</xsl:for-each>
					</ns2:adverseEvent>
				</xsl:for-each>
			</ns2:adverseEvents>
			<ns2:criteria>
				<participantIdentifier>
					<xsl:value-of
						select="adverseevents/participantInfo/studySubjectIdentifier" />
				</participantIdentifier>
				<studyIdentifier>
					<xsl:value-of select="adverseevents/studyInfo/studyIdentifier" />
				</studyIdentifier>
				<course>
					<startDateOfThisCourse>
						<xsl:value-of
							select="adverseevents/timeframeInfo/reportingPeriod/startDateOfThisCourse" />
					</startDateOfThisCourse>
					<endDateOfThisCourse>
						<xsl:value-of
							select="adverseevents/timeframeInfo/reportingPeriod/endDateOfThisCourse" />
					</endDateOfThisCourse>
					<treatmentType>
						<xsl:value-of select="adverseevents/timeframeInfo/periodType" />
					</treatmentType>
					<treatmentAssignmentCode>
						<xsl:value-of select="adverseevents/timeframeInfo/assignedTreatment" />
					</treatmentAssignmentCode>
				</course>
			</ns2:criteria>
		</ns2:adverseevent>
	</xsl:template>
</xsl:stylesheet>