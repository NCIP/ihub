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
			<adverseeventinput xmlns="http://cacis.nci.nih.gov">
				<criteria>
					<participantIdentifier>
						<xsl:value-of
							select="ns3:caCISRequest/ns3:sourceData/ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/ns1:trim/ns1:adverseeventinput/ns1:criteria/ns1:participantIdentifier" />
					</participantIdentifier>
					<studyIdentifier>
						<xsl:value-of
							select="ns3:caCISRequest/ns3:sourceData/ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/ns1:trim/ns1:adverseeventinput/ns1:criteria/ns1:studyIdentifier" />
					</studyIdentifier>
					<course>
						<startDateOfThisCourse>
							<xsl:value-of
								select="ns3:caCISRequest/ns3:sourceData/ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/ns1:trim/ns1:adverseeventinput/ns1:criteria/ns1:course/ns1:startDateOfThisCourse" />
						</startDateOfThisCourse>
						<endDateOfThisCourse>
							<xsl:value-of
								select="ns3:caCISRequest/ns3:sourceData/ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/ns1:trim/ns1:adverseeventinput/ns1:criteria/ns1:course/ns1:endDateOfThisCourse" />
						</endDateOfThisCourse>
						<treatmentType>Treatment</treatmentType>
						<treatmentAssignmentCode>TAC</treatmentAssignmentCode>
					</course>
				</criteria>
				<adverseEventsList>
					<xsl:for-each
						select="ns3:caCISRequest/ns3:sourceData/ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/ns1:trim/ns1:adverseeventinput/ns1:adverseEventsList/ns1:adverseEvent">
						<adverseEvent>
							<verbatim>
								<xsl:value-of select="ns1:verbatim" />
							</verbatim>
							<ctepCode>
								<xsl:value-of select="ns1:ctepCode" />
							</ctepCode>
							<grade>
								<xsl:value-of select="ns1:grade" />
							</grade>
							<startDate>
								<xsl:value-of select="ns1:startDate" />
							</startDate>
							<endDate>
								<xsl:value-of select="ns1:endDate" />
							</endDate>
							<expected>
								<xsl:value-of select="ns1:expected" />
							</expected>
							<attributionSummary>
								<xsl:value-of select="ns1:attributionSummary" />
							</attributionSummary>
							<xsl:for-each select="ns1:outcome">
								<outcome>
									<outComeEnumType>
										<xsl:value-of select="ns1:outComeEnumType" />
									</outComeEnumType>
								</outcome>
							</xsl:for-each>
						</adverseEvent>
					</xsl:for-each>
				</adverseEventsList>
			</adverseeventinput>
		</ns2:caxchangerequest>
	</xsl:template>
</xsl:stylesheet>