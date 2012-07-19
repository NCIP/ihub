package gov.nih.nci.integration.transformer;

import gov.nih.nci.integration.exception.IntegrationException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Test Class for XML Transformation Tests
 * 
 * @author Vinodh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-common-test.xml")
public class XSLTTransformerTest {

    @Autowired
    private XSLTTransformer xsltTransformer;

    @Value("${integration.transformer.xsl.baseClassPath}")
    private String baseXSLPath;

    @Value("${catissue.api.participant.xsl}")
    private String catissueParticipantXsl;

    private static final Logger LOG = LoggerFactory.getLogger(XSLTTransformerTest.class);

    /**
     * Before Setup
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    @Before
    public void intialize() throws IntegrationException {
        xsltTransformer.initTransformer(catissueParticipantXsl, baseXSLPath);
        Assert.assertNotNull(xsltTransformer);
    }

    /**
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    public void transformMsgBCMsg() throws IntegrationException {
        xsltTransformer.initTransformer(catissueParticipantXsl, baseXSLPath);

        String trnsfrmdMsg = transformToParticipantXML(getMsgBCMsg());
        Assert.assertNotNull(trnsfrmdMsg);
    }

    private String transformToParticipantXML(String message) throws IntegrationException {
        String participantXMLStr = null;
        InputStream is = null;
        ByteArrayOutputStream os = null;

        try {
            os = new ByteArrayOutputStream();
            is = new ByteArrayInputStream(message.getBytes());

            xsltTransformer.transform(null, is, os);

            participantXMLStr = new String(os.toByteArray());
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
        return participantXMLStr;
    }

    private String getMsgBCMsg() {
        return getXMLString("ParticipantInterim.xml");
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = XSLTTransformerTest.class.getClassLoader().getResourceAsStream(
                "payloads_participant/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            LOG.error("Error while reading contents of file : " + fileName + ". " + e);
        }
        return contents;
    }
}
