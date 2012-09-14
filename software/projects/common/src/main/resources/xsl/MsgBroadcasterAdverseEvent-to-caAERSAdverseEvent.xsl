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
					select="ns2:caxchangerequest/a:adverseeventinput/a:adverseEventsList/a:adverseEvent">
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
							<xsl:value-of select="a:attributionSummary" />
						</attributionSummary>
						<startDate>
							<xsl:value-of select="a:startDate" />
						</startDate>
						<endDate>
							<xsl:value-of select="a:endDate" />
						</endDate>
						<ctepCode>
							<xsl:value-of select="a:ctepCode" />
						</ctepCode>
						<xsl:for-each select="a:outcome">
							<outcome>
								<outComeEnumType>
									<xsl:value-of select="a:outComeEnumType" />
								</outComeEnumType>
							</outcome>
						</xsl:for-each>
					</ns22:adverseEvent>
				</xsl:for-each>
			</ns22:adverseEvents>
			<ns22:criteria>
				<studySubjectIdentifier>
					<xsl:value-of
						select="ns2:caxchangerequest/a:adverseeventinput/a:criteria/a:studySubjectIdentifier" />
				</studySubjectIdentifier>
				<studyIdentifier>
					<xsl:value-of
						select="ns2:caxchangerequest/a:adverseeventinput/a:criteria/a:studyIdentifier" />
				</studyIdentifier>
				<course>
					<startDateOfThisCourse>
						<xsl:value-of
							select="ns2:caxchangerequest/a:adverseeventinput/a:criteria/a:course/a:startDateOfThisCourse" />
					</startDateOfThisCourse>
					<endDateOfThisCourse>
						<xsl:value-of
							select="ns2:caxchangerequest/a:adverseeventinput/a:criteria/a:course/a:endDateOfThisCourse" />
					</endDateOfThisCourse>
					<treatmentType>
						<xsl:value-of
							select="ns2:caxchangerequest/a:adverseeventinput/a:criteria/a:course/a:treatmentType" />
					</treatmentType>
					<treatmentAssignmentCode>
						<xsl:value-of
							select="ns2:caxchangerequest/a:adverseeventinput/a:criteria/a:course/a:treatmentAssignmentCode" />
					</treatmentAssignmentCode>
				</course>
			</ns22:criteria>
		</ns22:adverseevent>
	</xsl:template>
</xsl:stylesheet>