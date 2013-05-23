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
    @Before
    public void intialize() throws IntegrationException {
        xsltTransformer.initTransformer(catissueParticipantXsl, baseXSLPath);
        Assert.assertNotNull(xsltTransformer);
    }

    /**
     * Testcase for transforming Wrapper XML to Interim XML
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    public void transformWrapperToInterimXMLTest() throws IntegrationException {
        xsltTransformer.initTransformer("caCISRequest-to-MsgBroadcasterParticipantInboundMsg.xsl", baseXSLPath);
        final String trnsfrmdMsg = transformToParticipantXML(getParticipantWrapperXML());
        Assert.assertNotNull(trnsfrmdMsg);
    }

    /**
     * Test case for transforming Interim XML to XMLString
     * 
     * @throws IntegrationException - IntegrationException
     */
    // @Test
    public void transformInterimToXMLStringTest() throws IntegrationException {
        xsltTransformer.initTransformer(catissueParticipantXsl, baseXSLPath);

        final String trnsfrmdMsg = transformToParticipantXML(getParticipantInterimXML());
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
            LOG.error("IntegrationException while transformation : " + e);
            throw e;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                LOG.error("IOException while closing InputStream during transformation : " + e);
            }
            try {
                os.close();
            } catch (IOException e) {
                LOG.error("IOException while closing InputStream during transformation : " + e);
            }
        }
        return participantXMLStr;
    }

    private String getParticipantWrapperXML() {
        return getXMLString("Participant_TSA.xml");
    }

    private String getParticipantInterimXML() {
        return getXMLString("Participant_MBC.xml");
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = XSLTTransformerTest.class.getClassLoader().getResourceAsStream(
                "payloads/participant/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            LOG.error("Error while reading contents of file : " + fileName + ". " + e);
        }
        return contents;
    }
}
