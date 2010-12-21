<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:po="http://po.coppa.nci.nih.gov" xmlns:cax="http://caXchange.nci.nih.gov/messaging" xmlns:ISO="uri:iso.org:21090">
<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"/>
<xsl:template match="po:playerIdentifier" >
<Id xmlns="http://po.coppa.nci.nih.gov" xmlns:ISO="uri:iso.org:21090" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"> 
<xsl:attribute name="root" >
<xsl:apply-templates select="@root" />
</xsl:attribute>
<xsl:attribute name="extension" >
<xsl:apply-templates select="@extension" />
</xsl:attribute> 
</Id> 
</xsl:template> 
</xsl:stylesheet>
