package gov.nih.nci.integration.catissue;

import static org.junit.Assert.assertNotNull;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

    // CHECKSTYLE:OFF
    private String getSpecimenXMLStr() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><specimens xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns:ns0=\"http://caXchange.nci.nih.gov/messaging\" xmlns:a=\"http://cacis.nci.nih.gov\"><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>100</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>10</availableQuantity><barcode>TestUser0016</barcode><label>TestUser0016</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }

}
