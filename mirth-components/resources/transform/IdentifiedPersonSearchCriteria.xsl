<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:po="http://po.coppa.nci.nih.gov" xmlns:ISO="uri:iso.org:21090">
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
<xsl:template match="po:Id" >
<IdentifiedPerson xmlns="http://po.coppa.nci.nih.gov" xmlns:ISO="uri:iso.org:21090"> 
<identifier nullFlavor="NI"/> 
<playerIdentifier nullFlavor="NI"/> 
<scoperIdentifier nullFlavor="NI"/> 
<assignedId>
<xsl:attribute name="root" >
<xsl:apply-templates select="@root" />
</xsl:attribute>
<xsl:attribute name="extension" >
<xsl:apply-templates select="@extension" />
</xsl:attribute> 
</assignedId>
<status code="active"/> 
</IdentifiedPerson> 
</xsl:template> 
<xsl:template match="*">
<xsl:copy>
<xsl:apply-templates select="*|@*|text()" />
</xsl:copy>
</xsl:template>
</xsl:stylesheet>