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
 * Test Class for XML Transformation Tests for Consent Flow
 * 
 * @author Rohit Gupta
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-common-test.xml")
public class XSLTTransformerSpecimenTest {

    @Autowired
    private XSLTTransformer xsltTransformer;

    @Value("${integration.transformer.xsl.baseClassPath}")
    private String baseXSLPath;

    @Value("${catissue.api.specimen.xsl}")
    private String catissueSpecimenXsl;

    private static final Logger LOG = LoggerFactory.getLogger(XSLTTransformerSpecimenTest.class);

    /**
     * Testcase for transforming Wrapper XML to Interim XML
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    public void transformWrapperToInterimXMLTest() throws IntegrationException {
        xsltTransformer.initTransformer("caCISRequest-to-MsgBroadcasterSpecimenInboundMsg.xsl", baseXSLPath);
        final String trnsfrmdMsg = transformXML(getSpecimenWrapperXML());        
        Assert.assertNotNull(trnsfrmdMsg);
    }

    /**
     * Testcase for transforming Interim XML to XMLString
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    public void transformSpecimenInterimToXMLStringTest() throws IntegrationException {
        xsltTransformer.initTransformer(catissueSpecimenXsl, baseXSLPath);
        final String trnsfrmdMsg = transformXML(getInterimSpecimenXML());
        Assert.assertNotNull(trnsfrmdMsg);
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

    private String getSpecimenWrapperXML() {
        return getXMLString("Specimen_TSA.xml");
    }

    private String getInterimSpecimenXML() {
        return getXMLString("Specimen_MBC.xml");
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = XSLTTransformerSpecimenTest.class.getClassLoader().getResourceAsStream(
                "payloads/specimen/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            LOG.error("Error while reading contents of file : " + fileName + ". " + e);
        }
        return contents;
    }
}
