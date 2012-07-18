package gov.nih.nci.integration.transformer;

import gov.nih.nci.integration.exception.IntegrationException;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
        System.out.println("XMLString --> " + trnsfrmdMsg);// NOPMD
        Assert.assertNotNull(trnsfrmdMsg);
    }

    private String getAEInterimMessage() {
        return getXMLString("AEInterim.xml");
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
        final StringBuffer fileContents = new StringBuffer();
        final InputStream is = XSLTTransformerAdverseEventTest.class.getClassLoader().getResourceAsStream(
                "payloads_adverseevent/" + fileName);
        final BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String strLine;
        try {
            while ((strLine = br.readLine()) != null) { // NOPMD
                fileContents.append(strLine);
            }
            is.close();
        } catch (IOException e) {
            System.err.println("Error while reading contents of file : " + fileName + ". " + e);// NOPMD
        }
        return fileContents.toString();
    }

}
