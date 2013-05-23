/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.transformer;

import gov.nih.nci.integration.exception.IntegrationException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test Class for XML Transformation Tests for Adverse Event Flow
 * 
 * @author Rohit Gupta
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-common-test.xml")
public class XSLTTransformerAdverseEventTest {

    @Autowired
    private XSLTTransformer xsltTransformer;

    @Value("${integration.transformer.xsl.baseClassPath}")
    private String baseXSLPath;

    @Value("${caaers.adverseevent.xsl}")
    private String caaersAdverseEventXsl;

    private static final Logger LOG = LoggerFactory.getLogger(XSLTTransformerAdverseEventTest.class);

    /**
     * Test case for transforming Interim XML to XMLString
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    public void transformInterimToXMLStringTest() throws IntegrationException {
        xsltTransformer.initTransformer(caaersAdverseEventXsl, baseXSLPath);
        final String trnsfrmdMsg = transformXML(getAEInterimMessage());        
        Assert.assertNotNull(trnsfrmdMsg);
    }

    private String getAEInterimMessage() {
        return getXMLString("AE_MBC.xml");
    }

    private String transformXML(String message) throws IntegrationException {
        String xmlStr = null;
        InputStream is = null;
        ByteArrayOutputStream os = null;

        try {
            os = new ByteArrayOutputStream();
            is = new ByteArrayInputStream(message.getBytes());

            xsltTransformer.transform(null, is, os);

            xmlStr = new String(os.toByteArray());
        } catch (IntegrationException e) {
            LOG.error("IntegrationException inside transformXML(). " + e);
            throw e;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                LOG.error("IOException while closing InputStream. " + e);
            }
            try {
                os.close();
            } catch (IOException e) {
                LOG.error("IOException while closing OutputStream. " + e);
            }
        }

        return xmlStr;
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = XSLTTransformerAdverseEventTest.class.getClassLoader().getResourceAsStream(
                "payloads/adverseevent/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            LOG.error("Error while reading contents of file : " + fileName + ". " + e);
        }
        return contents;
    }

}
