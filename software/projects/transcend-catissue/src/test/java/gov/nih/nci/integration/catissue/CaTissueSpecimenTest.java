package gov.nih.nci.integration.catissue;

import static org.junit.Assert.assertNotNull;
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
 * This is the test class to test the methods of the Wrapper Specimen Client.
 * 
 * @author Rohit Gupta
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext-transcend-catissue.xml")
public class CaTissueSpecimenTest {

    private CaTissueSpecimenClient caTissueSpecimenClient;

    @Value("${catissue.custom.lib.location}")
    private String caTissueLibLocation;

    @Value("${catissue.api.login.username}")
    private String catissueApiLoginName;

    @Value("${catissue.api.login.password}")
    private String catissueApiPassword;

    @Value("${catissue.specimen.mock.client}")
    private String specimenMockClientClass;

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueSpecimenTest.class);

    /**
     * To initialize the things
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    @Before
    public void initialize() throws IntegrationException {
        caTissueSpecimenClient = new CaTissueSpecimenClient(caTissueLibLocation, catissueApiLoginName,
                catissueApiPassword, specimenMockClientClass);
    }

    /**
     * TestCase to test the createSpecimen of Wrapper client when specimen already exist
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    public void createSpecimenAlreadyExist() throws IntegrationException {
        final ServiceInvocationResult svc = caTissueSpecimenClient.createSpecimens("YES_SPECIMEN_ALREADY_EXIST");
        assertNotNull(svc);
    }

    /**
     * TestCase to test the createSpecimen of Wrapper client
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    public void createSpecimen() throws IntegrationException {
        final ServiceInvocationResult svc = caTissueSpecimenClient.createSpecimens(getSpecimenXMLStr());
        assertNotNull(svc);
    }

    /**
     * TestCase to test the rollbackCreatedSpecimens of Wrapper client
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    public void rollbackCreatedSpecimens() throws IntegrationException {
        final ServiceInvocationResult svc = caTissueSpecimenClient.rollbackCreatedSpecimens(getSpecimenXMLStr());
        assertNotNull(svc);
    }

    /**
     * TestCase to test the updateSpecimens of Wrapper client when specimen doesn't exist in caTissue
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    public void updateSpecimen() throws IntegrationException {
        final ServiceInvocationResult svc = caTissueSpecimenClient.updateSpecimens(getSpecimenXMLStr());
        assertNotNull(svc);
    }

    /**
     * TestCase to test the rollbackUpdatedSpecimens of Wrapper client
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    public void rollbackUpdatedSpecimens() throws IntegrationException {
        final ServiceInvocationResult svc = caTissueSpecimenClient.rollbackUpdatedSpecimens(getSpecimenXMLStr());
        assertNotNull(svc);
    }

    private String getSpecimenXMLStr() {
        return getXMLString("CreateSpecimen.xml");
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
