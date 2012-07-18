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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test Class for XML Transformation Tests for Consent Flow
 * 
 * @author Rohit Gupta
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-common-test.xml")
public class XSLTTransformerConsentTest {

    @Autowired
    private XSLTTransformer xsltTransformer;

    @Value("${integration.transformer.xsl.baseClassPath}")
    private String baseXSLPath;

    @Value("${catissue.api.consent.xsl}")
    private String catissueConsentXsl;

    /**
     * Testcase for transforming incoming XML to Wrapper XML
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    public void transformIncomingToWrapperXMLTest() throws IntegrationException {
        xsltTransformer.initTransformer("TranscendInboundMsg-to-caCISRequest.xsl", baseXSLPath);
        final String trnsfrmdMsg = transformXML(getConsentIncomingRequestMessage());
        Assert.assertNotNull(trnsfrmdMsg);
    }

    /**
     * Testcase for transforming Wrapper XML to Interim XML
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    public void transformWrapperToInterimXMLTest() throws IntegrationException {
        xsltTransformer.initTransformer("caCISRequest-to-MsgBroadcasterConsentInboundMsg.xsl", baseXSLPath);
        final String trnsfrmdMsg = transformXML(getConsentWrapperMessage());
        Assert.assertNotNull(trnsfrmdMsg);
    }

    /**
     * Testcase for transforming Interim XML to XMLString
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    public void transformInterimToXMLStringTest() throws IntegrationException {
        xsltTransformer.initTransformer(catissueConsentXsl, baseXSLPath);
        final String trnsfrmdMsg = transformXML(getConsentInterimMessage());
        Assert.assertNotNull(trnsfrmdMsg);
    }

    private String getConsentIncomingRequestMessage() {
        return getXMLString("ConsentIncoming.xml");
    }

    private String getConsentWrapperMessage() {
        return getXMLString("ConsentWrapper.xml");
    }

    private String getConsentInterimMessage() {
        return getXMLString("ConsentInterim.xml");
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
            e.printStackTrace();
            throw e;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return xmlStr;
    }

    private String getXMLString(String fileName) {
        final StringBuffer fileContents = new StringBuffer();
        final InputStream is = XSLTTransformerConsentTest.class.getClassLoader().getResourceAsStream(
                "payloads_consent/" + fileName);
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
