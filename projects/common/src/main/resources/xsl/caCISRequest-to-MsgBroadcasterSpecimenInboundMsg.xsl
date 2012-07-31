<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xs="http://www.w3.org/2001/XMLSchema" 
	xmlns:ns0="http://caXchange.nci.nih.gov/messaging" xmlns:ns1="urn:tolven-org:trim:4.0"
	xmlns:ns2="http://caXchange.nci.nih.gov/caxchangerequest" xmlns:ns3="http://cacis.nci.nih.gov" 
	exclude-result-prefixes="ns0 xs ns1 ns2 ns3">
	
	<xsl:output method="xml" encoding="UTF-8" indent="yes" />	
	
	<xsl:template match="/">	
		<ns2:caxchangerequest>
			<ns0:metadata>
				<xsl:copy-of select="//ns2:caxchangerequest/ns0:metadata/node()|@*"/>
			</ns0:metadata>		

			<ns0:request>
				<ns0:businessMessagePayload>
					<specimens>
						<participant>
							<xsl:for-each
								select="ns3:caCISRequest/ns3:sourceData/ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/ns1:trim/ns1:act/ns1:relationship">
								<xsl:if test="@name = 'cdmsSubjectId'">
									<cdmsSubjectId><xsl:value-of select="ns1:act/ns1:observation/ns1:value/ns1:ST" /></cdmsSubjectId>
								</xsl:if>
								<xsl:if test="@name = 'activityStatus'">
									<activityStatus><xsl:value-of select="ns1:act/ns1:observation/ns1:value/ns1:ST" /></activityStatus>
								</xsl:if>
							</xsl:for-each>
						</participant>
			
						<specimenDetailsList>
							<xsl:for-each
								select="ns3:caCISRequest/ns3:sourceData/ns2:caxchangerequest/ns0:request/ns0:businessMessagePayload/ns1:trim/ns1:act/ns1:relationship">
								<xsl:if test="@name='specimenList'">
									<xsl:for-each select="ns1:act/ns1:relationship">
										<specimenDetail>
											<xsl:if test="@name = 'specimen'">
												<xsl:for-each select="ns1:act/ns1:relationship">
													<xsl:if test="@name = 'collectionProtocolEvent'">
														<collectionProtocolEvent><xsl:value-of select="ns1:act/ns1:observation/ns1:value/ns1:ST" /></collectionProtocolEvent>
													</xsl:if>
												</xsl:for-each>													
												<specimen>
													<xsl:for-each select="ns1:act/ns1:relationship">														
														<xsl:if test="@name = 'cdmsSpecimenId'">
															<cdmsSpecimenId><xsl:value-of select="ns1:act/ns1:observation/ns1:value/ns1:ST" /></cdmsSpecimenId>
														</xsl:if>
														<xsl:if test="@name = 'barCode'">
															<barcode><xsl:value-of select="ns1:act/ns1:observation/ns1:value/ns1:ST" /></barcode>
														</xsl:if>
														<xsl:if test="@name = 'activityStatus'">
															<activityStatus><xsl:value-of select="ns1:act/ns1:observation/ns1:value/ns1:ST" /></activityStatus>
														</xsl:if>
														<xsl:if test="@name = 'specimenClass'">
															<specimenClass><xsl:value-of select="ns1:act/ns1:observation/ns1:value/ns1:ST" /></specimenClass>
														</xsl:if>
														<xsl:if test="@name = 'specimenType'">
															<specimenType><xsl:value-of select="ns1:act/ns1:observation/ns1:value/ns1:ST" /></specimenType>
														</xsl:if>
														<xsl:if test="@name = 'pathologicalStatus'">
															<pathologicalStatus><xsl:value-of select="ns1:act/ns1:observation/ns1:value/ns1:ST" /></pathologicalStatus>
														</xsl:if>
														<xsl:if test="@name = 'initialQuantity'">
															<initialQuantity><xsl:value-of select="ns1:act/ns1:observation/ns1:value/ns1:ST" /></initialQuantity>
														</xsl:if>
														<xsl:if test="@name = 'availableQuantity'">
															<availableQuantity><xsl:value-of select="ns1:act/ns1:observation/ns1:value/ns1:ST" /></availableQuantity>
														</xsl:if>
														<xsl:if test="@name = 'specimenCharacteristics'">
															<specimenCharacteristics>
																<xsl:for-each select="ns1:act/ns1:observation/ns1:value">
																	<xsl:choose>
																		<xsl:when test="ns1:label = 'tissueSite'">
																			<tissueSite><xsl:value-of select="ns1:ST" /></tissueSite>
																		</xsl:when>
																		<xsl:otherwise>
																			<tissueSide><xsl:value-of select="ns1:ST" /></tissueSide>
																		</xsl:otherwise>
																	</xsl:choose>
																</xsl:for-each>
															</specimenCharacteristics>
														</xsl:if>
													</xsl:for-each>
												</specimen>
												<collectionProtocol>
													<xsl:for-each select="ns1:act/ns1:relationship">
														<xsl:if test="@name = 'collectionProtocol'">
															<xsl:for-each select="ns1:act/ns1:observation/ns1:value">
																<xsl:choose>
																	<xsl:when test="ns1:label = 'title'">
																		<title><xsl:value-of select="ns1:ST" /></title>
																	</xsl:when>
																	<xsl:otherwise>
																		<shortTitle><xsl:value-of select="ns1:ST" /></shortTitle>
																	</xsl:otherwise>
																</xsl:choose>
															</xsl:for-each>															
														</xsl:if>
													</xsl:for-each>
												</collectionProtocol>
												<guidanceForBreastCoreBiopsy>
													<xsl:for-each select="ns1:act/ns1:relationship">
														<xsl:if test="@name = 'guidanceForBreastCoreBiopsy'">
															<xsl:for-each select="ns1:act/ns1:observation/ns1:value">
																<xsl:choose>
																	<xsl:when test="ns1:label = 'guidanceForBreastCoreBiopsyType'">
																		<guidanceForBreastCoreBiopsyType><xsl:value-of select="ns1:ST" /></guidanceForBreastCoreBiopsyType>
																	</xsl:when>
																	<xsl:otherwise>
																		<otherText><xsl:value-of select="ns1:ST" /></otherText>
																	</xsl:otherwise>
																</xsl:choose>
															</xsl:for-each>															
														</xsl:if>
													</xsl:for-each>													
												</guidanceForBreastCoreBiopsy>
											</xsl:if>
										</specimenDetail>
									</xsl:for-each>
								</xsl:if>
							   </xsl:for-each>
							</specimenDetailsList>
						</specimens>
					</ns0:businessMessagePayload>
				</ns0:request>
			</ns2:caxchangerequest>
	</xsl:template>
	
</xsl:stylesheet>