package gov.nih.nci.integration.catissue;

import static org.junit.Assert.assertNotNull;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * IntegrationTest class for Specimen
 * 
 * @author Rohit Gupta
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext-transcend-catissue.xml")
public class CaTissueSpecimenClientIntegrationTest {

    @Autowired
    private CaTissueSpecimenClient caTissueSpecimenClient;

    /**
     * Test case when trying to create existing Specimen
     */
    @Test
    public void createExistingSpecimen() {
        final ServiceInvocationResult svc = caTissueSpecimenClient.createSpecimens(getCreateExistingSpecimenXMLStr());
        assertNotNull(svc);

    }

    /**
     * Testcase for createSpecimen
     */
    @Test
    public void createSpecimen() {
        final ServiceInvocationResult svc = caTissueSpecimenClient.createSpecimens(getCreateSpecimenXMLStr());
        assertNotNull(svc);

    }

    /**
     * Testcase for updateSpecimen
     */
    @Test
    public void updateSpecimen() {
        final ServiceInvocationResult svc = caTissueSpecimenClient.updateSpecimens(getUpdateSpecimenXMLStr());
        assertNotNull(svc);

    }

    /**
     * Testcase for updateSpecimen when specimen doesn't exist
     */
    @Test
    public void updateSpecimenNotExist() {
        final ServiceInvocationResult svc = caTissueSpecimenClient.updateSpecimens(getUpdateSpecimenNotExistXMLStr());
        assertNotNull(svc);

    }

    // CHECKSTYLE:OFF

    private String getCreateExistingSpecimenXMLStr() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><specimens xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns:ns0=\"http://caXchange.nci.nih.gov/messaging\" xmlns:a=\"http://cacis.nci.nih.gov\"><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>100</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>50</availableQuantity><barcode>TestUser0016</barcode><label>TestUser0016</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>400</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>200</availableQuantity><barcode>TestUser0017</barcode><label>TestUser0017</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }

    private String getCreateSpecimenXMLStr() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><specimens xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns:ns0=\"http://caXchange.nci.nih.gov/messaging\" xmlns:a=\"http://cacis.nci.nih.gov\"><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>100</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>50</availableQuantity><barcode>TestUser0020</barcode><label>TestUser0020</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>400</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>200</availableQuantity><barcode>TestUser0021</barcode><label>TestUser0021</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }

    private String getUpdateSpecimenNotExistXMLStr() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><specimens xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns:ns0=\"http://caXchange.nci.nih.gov/messaging\" xmlns:a=\"http://cacis.nci.nih.gov\"><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>100</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>10</availableQuantity><barcode>TolvenTestUser252TissueSpecimen254</barcode><label>TolvenTestUser252TissueSpecimen254</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }

    private String getUpdateSpecimenXMLStr() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><specimens xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns:ns0=\"http://caXchange.nci.nih.gov/messaging\" xmlns:a=\"http://cacis.nci.nih.gov\"><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>100</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>10</availableQuantity><barcode>TestUser0016</barcode><label>TestUser0016</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }
}
