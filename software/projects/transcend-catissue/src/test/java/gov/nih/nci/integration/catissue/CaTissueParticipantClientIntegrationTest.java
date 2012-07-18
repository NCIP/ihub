package gov.nih.nci.integration.catissue;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
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

    /**
     * Test for registerParticipant
     */
    @Test
    public void registerParticipant() {
        ServiceInvocationResult svc = caTissueParticipantClient.registerParticipant(getParticipantXMLStr());
        assertNotNull(svc);
        assertFalse(svc.isFault());
        svc = caTissueParticipantClient.deleteParticipant(getParticipantXMLStr());
        assertNotNull(svc);
        assertFalse(svc.isFault());
    }

    private String getParticipantXMLStr() {
        return getXMLString("Participant.xml");
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = CaTissueParticipantClientIntegrationTest.class.getClassLoader().getResourceAsStream(
                "payloads/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            System.err.println("Error while reading contents of file : " + fileName + ". " + e);// NOPMD
        }
        return contents;
    }
}
