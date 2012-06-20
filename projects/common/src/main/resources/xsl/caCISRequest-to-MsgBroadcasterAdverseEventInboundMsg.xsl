<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:ns0="http://caXchange.nci.nih.gov/messaging" xmlns:ns1="urn:tolven-org:trim:4.0"
	xmlns:ns2="http://caXchange.nci.nih.gov/caxchangerequest" xmlns:ns3="http://cacis.nci.nih.gov"
	exclude-result-prefixes="ns0 xs ns1 ns2 ns3">

	<xsl:output method="xml" encoding="UTF-8" indent="yes" />

	<xsl:template match="/">
		<ns2:caxchangerequest>
			<ns0:metadata>
				<xsl:copy-of select="//ns2:caxchangerequest/ns0:metadata/node()|@*" />
			</ns0:metadata>
			<adverseevents xmlns="http://cacis.nci.nih.gov">
				<studyInfo>
					<studyIdentifier>
						<xsl:value-of
							select="ns3:caCISRequest/ns3:sourceData/ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/ns1:trim/ns1:adverseevents/ns1:studyInfo/ns1:studyIdentifier" />
					</studyIdentifier>
				</studyInfo>
				<participantInfo>
					<studySubjectIdentifier>
						<xsl:value-of
							select="ns3:caCISRequest/ns3:sourceData/ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/ns1:trim/ns1:adverseevents/ns1:participantInfo/ns1:studySubjectIdentifier" />
					</studySubjectIdentifier>
				</participantInfo>
				<timeframeInfo>
					<reportingPeriod>
						<startDateOfThisCourse>
							<xsl:value-of
								select="ns3:caCISRequest/ns3:sourceData/ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/ns1:trim/ns1:adverseevents/ns1:timeframeInfo/ns1:reportingPeriod/ns1:startDateOfThisCourse" />
						</startDateOfThisCourse>
						<endDateOfThisCourse>
							<xsl:value-of
								select="ns3:caCISRequest/ns3:sourceData/ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/ns1:trim/ns1:adverseevents/ns1:timeframeInfo/ns1:reportingPeriod/ns1:endDateOfThisCourse" />
						</endDateOfThisCourse>
					</reportingPeriod>
					<periodType>
						<xsl:value-of
							select="ns3:caCISRequest/ns3:sourceData/ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/ns1:trim/ns1:adverseevents/ns1:timeframeInfo/ns1:periodType" />
					</periodType>
					<assignedTreatment>
						<xsl:value-of
							select="ns3:caCISRequest/ns3:sourceData/ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/ns1:trim/ns1:adverseevents/ns1:timeframeInfo/ns1:assignedTreatment" />
					</assignedTreatment>
				</timeframeInfo>
				<adverseEventsList>
					<xsl:for-each
						select="ns3:caCISRequest/ns3:sourceData/ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/ns1:trim/ns1:adverseevents/ns1:adverseEventsList/ns1:adverseEvent">
						<adverseEvent>
							<verbatim>
								<xsl:value-of select="ns1:verbatim" />
							</verbatim>
							<codedTerm>
								<xsl:value-of select="ns1:codedTerm" />
							</codedTerm>
							<grade>
								<xsl:value-of select="ns1:grade" />
							</grade>
							<onsetDate>
								<xsl:value-of select="ns1:onsetDate" />
							</onsetDate>
							<resolutionDate>
								<xsl:value-of select="ns1:resolutionDate" />
							</resolutionDate>
							<expected>
								<xsl:value-of select="ns1:expected" />
							</expected>
							<attribution>
								<xsl:value-of select="ns1:attribution" />
							</attribution>
							<seriousReasonsList>
								<xsl:for-each select="ns1:seriousReasonsList/ns1:reason">
									<reason>
										<xsl:value-of select="." />
									</reason>
								</xsl:for-each>
							</seriousReasonsList>
						</adverseEvent>
					</xsl:for-each>
				</adverseEventsList>
			</adverseevents>
		</ns2:caxchangerequest>
	</xsl:template>
</xsl:stylesheet>