/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.catissue;

import static org.junit.Assert.*;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Integration test class for RegisterParticipant
 * 
 * @author Vinodh
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext-transcend-catissue.xml")
public class CaTissueParticipantClientIntegrationTest {

    @Autowired
    private CaTissueParticipantClient caTissueParticipantClient;

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueParticipantClientIntegrationTest.class);

    /**
     * Test for registerParticipant
     */
    @Test
    public void registerParticipant() {
        String xmlStr = getParticipantXMLStr();
        ServiceInvocationResult svc = caTissueParticipantClient.registerParticipant(xmlStr);
        assertNotNull(svc);
        assertFalse(svc.isFault());
        svc = caTissueParticipantClient.deleteParticipant(xmlStr);
        assertNotNull(svc);
        assertFalse(svc.isFault());
    }

    private String getParticipantXMLStr() {
        return getXMLString("ParticipantIntg_catissue.xml");
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = CaTissueParticipantClientIntegrationTest.class.getClassLoader().getResourceAsStream(
                "payloads/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            LOG.error("Error while reading contents of file : " + fileName + ". " + e);
        }
        return contents;
    }
}
