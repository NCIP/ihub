package gov.nih.nci.integration.catissue;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This is the test class to test the methods of the Wrapper Consent Client.
 * 
 * @author Rohit Gupta
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext-transcend-catissue.xml")
public class CaTissueParticipantTest {

    private CaTissueParticipantClient caTissueParticipantClient;

    @Value("${catissue.custom.lib.location}")
    private String caTissueLibLocation;

    @Value("${catissue.api.login.username}")
    private String catissueApiLoginName;

    @Value("${catissue.api.login.password}")
    private String catissueApiPassword;

    @Value("${catissue.participant.mock.client}")
    private String participantMockClientClass;

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueParticipantTest.class);

    /**
     * To initialize the things
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    @Before
    public void initialize() throws IntegrationException {
        caTissueParticipantClient = new CaTissueParticipantClient(caTissueLibLocation, catissueApiLoginName,
                catissueApiPassword, participantMockClientClass);
    }

    /**
     * TestCase to test the registerParticipant of Wrapper client
     */
    @Test
    public void clientInitException() {
        CaTissueParticipantClient client = null;
        ServiceInvocationResult svc = null;
        try {
            client = new CaTissueParticipantClient("catissue-client-lib-123/", catissueApiLoginName,
                    catissueApiPassword, participantMockClientClass);
            svc = client.registerParticipant(getParticipantXMLStr());

        } catch (IntegrationException e) { 
            svc = null;
        }

        assertNull(svc);
    }

    /**
     * TestCase to test the registerParticipant of Wrapper client
     * 
     */
    @Test
    public void registerParticipantFailure() {
        final ServiceInvocationResult svc = caTissueParticipantClient
                .registerParticipant(getParticipantXMLStrFailure());
        assertNotNull(svc);
    }

    /**
     * TestCase to test the registerParticipant of Wrapper client
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    public void registerParticipant() throws IntegrationException {
        final ServiceInvocationResult svc = caTissueParticipantClient.registerParticipant(getParticipantXMLStr());
        assertNotNull(svc);
    }

    /**
     * TestCase to test the updateRegistrationParticipant of Wrapper client
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    public void updateRegistrationParticipant() throws IntegrationException {
        final ServiceInvocationResult svc = caTissueParticipantClient
                .updateRegistrationParticipant(getParticipantXMLStr());
        assertNotNull(svc);
    }

    /**
     * TestCase to test the deleteParticipant of Wrapper client
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    public void deleteParticipant() throws IntegrationException {
        final ServiceInvocationResult svc = caTissueParticipantClient.deleteParticipant(getParticipantXMLStr());
        assertNotNull(svc);
    }

    private String getParticipantXMLStr() {
        return getXMLString("Participant.xml");
    }

    private String getParticipantXMLStrFailure() {
        return getXMLString("ParticipantFailure.xml");
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
