package gov.nih.nci.integration.catissue.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.net.MalformedURLException;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

/**
 * This is an integration test class for Specimen client flows.
 * 
 * @author Rohit Gupta
 */
public class CaTissueSpecimenIntegrationTest {

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueSpecimenIntegrationTest.class);
    private CaTissueSpecimenClient caTissueSpecimenClient;

    /**
     * To initialize the things
     */
    @Test
    @Before
    public void initialize() {
        try {
            caTissueSpecimenClient = new CaTissueSpecimenClient("admin@admin.com", "Rohit123");
        } catch (BeansException e) {
            LOG.error("CaTissueConsentIntegrationTest-BeansException inside initialize() ", e);
        } catch (MalformedURLException e) {
            LOG.error("CaTissueConsentIntegrationTest-ApplicationException inside initialize() ", e);
        }

        assertNotNull(caTissueSpecimenClient);
    }

    /**
     * Testcase for Create Specimen when CollectionProtocol is invalid
     */
    @Test
    public void createInvalidCollectionProtocolSpecimens() {
        String existXML = null;
        String createdXML = "CREATED";
        try {
            existXML = caTissueSpecimenClient.isSpecimensExist(getInsertInvalidCollectionProtocolXMLStr());
            caTissueSpecimenClient.createSpecimens(getInsertInvalidCollectionProtocolXMLStr());

        } catch (ApplicationException e) {

            existXML = null;
            createdXML = null;
        }
        assertNull(existXML);
        assertNull(createdXML);
    }

    /**
     * Testcase for Create Specimen when SpecimenClass is invalid
     */
    @Test
    public void createInvalidSpecimenClass() {

        String existXML = null;
        String createdXML = "CREATED";
        try {

            existXML = caTissueSpecimenClient.isSpecimensExist(getInsertInvalidSpecimenClassXMLStr());
            caTissueSpecimenClient.createSpecimens(getInsertInvalidSpecimenClassXMLStr());

        } catch (ApplicationException e) {

            existXML = null;
            createdXML = null;
        }
        assertNull(existXML);
        assertNull(createdXML);
    }

    /**
     * Testcase for Create Specimen when Available Quantity is greater than Initial Quantity
     */
    @Test
    public void createInvalidAvailableQuantity() {

        String existXML = null;
        String createdXML = "CREATED";
        try {

            existXML = caTissueSpecimenClient.isSpecimensExist(getInsertInvalidAvailableQuantityXMLStr());
            caTissueSpecimenClient.createSpecimens(getInsertInvalidAvailableQuantityXMLStr());

        } catch (ApplicationException e) {

            existXML = null;
            createdXML = null;
        }
        assertNull(existXML);
        assertNull(createdXML);
    }

    /**
     * Testcase for Create Specimen when SpecimenType is invalid
     */
    @Test
    public void createInvalidSpecimenType() {

        String existXML = null;
        String createdXML = "CREATED";
        try {

            existXML = caTissueSpecimenClient.isSpecimensExist(getInsertInvalidSpecimenTypeXMLStr());
            caTissueSpecimenClient.createSpecimens(getInsertInvalidSpecimenTypeXMLStr());

        } catch (ApplicationException e) {

            existXML = null;
            createdXML = null;
        }
        assertNull(existXML);
        assertNull(createdXML);
    }

    /**
     * Testcase for Create Specimen when TissueSide is invalid
     */
    @Test
    public void createInvalidTissueSide() {

        String existXML = null;
        String createdXML = "CREATED";
        try {

            existXML = caTissueSpecimenClient.isSpecimensExist(getInsertInvalidTissueSideXMLStr());
            caTissueSpecimenClient.createSpecimens(getInsertInvalidTissueSideXMLStr());

        } catch (ApplicationException e) {

            existXML = null;
            createdXML = null;
        }
        assertNull(existXML);
        assertNull(createdXML);
    }

    /**
     * Testcase for Create Specimen when TissueSite is invalid
     */
    @Test
    public void createInvalidTissueSite() {

        String existXML = null;
        String createdXML = "CREATED";
        try {

            existXML = caTissueSpecimenClient.isSpecimensExist(getInsertInvalidTissueSiteXMLStr());
            caTissueSpecimenClient.createSpecimens(getInsertInvalidTissueSiteXMLStr());

        } catch (ApplicationException e) {

            existXML = null;
            createdXML = null;
        }
        assertNull(existXML);
        assertNull(createdXML);
    }

    /**
     * Testcase for Create Specimen when Pathological Status is invalid
     */
    @Test
    public void createInvalidPathologicalStatus() {

        String existXML = null;
        String createdXML = "CREATED";
        try {

            existXML = caTissueSpecimenClient.isSpecimensExist(getInsertInvalidPathologicalStatusXMLStr());
            caTissueSpecimenClient.createSpecimens(getInsertInvalidPathologicalStatusXMLStr());

        } catch (ApplicationException e) {

            existXML = null;
            createdXML = null;
        }
        assertNull(existXML);
        assertNull(createdXML);
    }

    /**
     * Testcase for Create Specimen
     */
    @Test
    public void createSpecimens() {

        String existXML = null;
        String createdXML = "CREATED";
        try {

            existXML = caTissueSpecimenClient.isSpecimensExist(getInsertExistingSpecimenXMLStr());
            caTissueSpecimenClient.createSpecimens(getInsertSpecimenXMLStr());

        } catch (ApplicationException e) {

            existXML = null;
            createdXML = null;
        }

        assertNull(existXML);
        assertNotNull(createdXML);
    }

    /**
     * Testcase for Create Specimen when specimen already exists in caTissue
     */
    @Test
    public void createExistingSpecimens() {

        String existXML = null;
        String createdXML = "CREATED";
        try {

            // existXML=
            // caTissueSpecimenClient.isSpecimensExist(getInsertExistingSpecimenXMLStr());
            caTissueSpecimenClient.createSpecimens(getInsertExistingSpecimenXMLStr());

        } catch (ApplicationException e) {

            existXML = null;
            createdXML = null;
        }
        assertNull(existXML);
        assertNull(createdXML);
    }

    /**
     * Testcase for Update Specimen
     */
    @Test
    public void updateSpecimens() {
        String retXML = null;

        try {
            retXML = caTissueSpecimenClient.updateSpecimens(getUpdateSpecimenXMLStr());

        } catch (ApplicationException e) {
        }
        assertNotNull(retXML);
    }

    /**
     * Testcase for Update Specimen when Specimen doesn't exist
     */
    @Test
    public void updateSpecimensSpecimenNotExist() {

        String retXML = null;
        try {

            retXML = caTissueSpecimenClient.updateSpecimens(getUpdateSpecimenNotExistXMLStr());

        } catch (ApplicationException e) {

        }
        assertNull(retXML);
    }

    /**
     * Testcase for Update Specimen when Available quantity is greater than Initial Quantity
     */
    @Test
    public void updateSpecimensInvalidAvailableQtyXMLStr() {

        String retXML = null;
        try {

            retXML = caTissueSpecimenClient.updateSpecimens(getUpdateSpecimenInvalidAvailableQtyXMLStr());

        } catch (ApplicationException e) {
            retXML = null;

        }
        assertNull(retXML);
    }

    /**
     * Testcase for Update Specimen when CollectionProtocol is changed during updateSpecimen
     */
    @Test
    public void updateSpecimensCollectionProtocolChangeXMLStr() {

        String retXML = null;
        String existXML = "UPDATING_SPECIMEN";
        try {

            existXML = caTissueSpecimenClient.getExistingSpecimens(getUpdateSpecimenCollectionProtocolChangeXMLStr());
            retXML = caTissueSpecimenClient.updateSpecimens(getUpdateSpecimenCollectionProtocolChangeXMLStr());

        } catch (ApplicationException e) {

            existXML = null;
            retXML = null;
        }
        assertNull(existXML);
        assertNull(retXML);
    }

    /**
     * Testcase for Update Specimen when CollectionProtocolEvent is changed during updateSpecimen
     */
    @Test
    public void updateSpecimensCollectionEventProtocolChangeXMLStr() {

        String retXML = null;
        String existXML = "UPDATING_SPECIMEN";
        try {

            existXML = caTissueSpecimenClient
                    .getExistingSpecimens(getUpdateSpecimenCollectionProtocolEventChangeXMLStr());
            retXML = caTissueSpecimenClient.updateSpecimens(getUpdateSpecimenCollectionProtocolEventChangeXMLStr());

        } catch (ApplicationException e) {

            existXML = null;
            retXML = null;
        }
        assertNull(existXML);
        assertNull(retXML);
    }

    /**
     * Testcase for Update Specimen when SpecimenClass is changed during updateSpecimen
     */
    @Test
    public void updateSpecimensSpecimenClassChange() {

        String retXML = null;
        String existXML = "UPDATING_SPECIMEN";
        try {

            existXML = caTissueSpecimenClient.getExistingSpecimens(getUpdateSpecimenClassChangeXMLStr());
            retXML = caTissueSpecimenClient.updateSpecimens(getUpdateSpecimenClassChangeXMLStr());

        } catch (ApplicationException e) {

            existXML = null;
            retXML = null;
        }
        assertNull(existXML);
        assertNull(retXML);
    }

    /**
     * Testcase for Update Specimen when specimenType is invalid
     */
    @Test
    public void updateSpecimensInvalidSpecimenType() {

        String retXML = null;
        String existXML = "UPDATING_SPECIMEN";
        try {

            existXML = caTissueSpecimenClient.getExistingSpecimens(getUpdateSpecimenInvalidSpecimenTypeXMLStr());
            retXML = caTissueSpecimenClient.updateSpecimens(getUpdateSpecimenInvalidSpecimenTypeXMLStr());

        } catch (ApplicationException e) {

            existXML = null;
            retXML = null;
        }
        assertNull(existXML);
        assertNull(retXML);
    }

    /**
     * Testcase for Update Specimen when TissueSide is invalid
     */
    @Test
    public void updateSpecimensInvalidTissueSide() {

        String retXML = null;
        String existXML = "UPDATING_SPECIMEN";
        try {

            existXML = caTissueSpecimenClient.getExistingSpecimens(getUpdateSpecimenInvalidTissueSideXMLStr());
            retXML = caTissueSpecimenClient.updateSpecimens(getUpdateSpecimenInvalidTissueSideXMLStr());
           
        } catch (ApplicationException e) {
           
            existXML = null;
            retXML = null;
        }
        assertNull(existXML);
        assertNull(retXML);
    }

    /**
     * Testcase for Update Specimen when TissueSite is invalid
     */
    @Test
    public void updateSpecimensInvalidTissueSite() {

        String retXML = null;
        String existXML = "UPDATING_SPECIMEN";
        try {

            existXML = caTissueSpecimenClient.getExistingSpecimens(getUpdateSpecimenInvalidTissueSiteXMLStr());
            retXML = caTissueSpecimenClient.updateSpecimens(getUpdateSpecimenInvalidTissueSiteXMLStr());
           
        } catch (ApplicationException e) {
           
            existXML = null;
            retXML = null;
        }
        assertNull(existXML);
        assertNull(retXML);
    }

    /**
     * Testcase for rollback created specimen
     */
    @Test
    public void rollbackSpecimens() {
        String retXML = "DELETE_SPECIMEN";

        try {

            caTissueSpecimenClient.rollbackCreatedSpecimens(getRollbackSpecimenXMLStr());
           
        } catch (ApplicationException e) {

            e.printStackTrace();
            retXML = null;
        }
        assertNotNull(retXML);
    }

    // CHECKSTYLE:OFF
    private String getInsertSpecimenXMLStr() {
        return "<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>4.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen171</barcode><label>TolvenTestUser252TissueSpecimen171</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>2.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen172</barcode><label>TolvenTestUser252TissueSpecimen172</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }

    private String getInsertExistingSpecimenXMLStr() {
        return "<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>4.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen173</barcode><label>TolvenTestUser252TissueSpecimen173</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>2.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen174</barcode><label>TolvenTestUser252TissueSpecimen174</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }

    private String getInsertInvalidCollectionProtocolXMLStr() {
        return "<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>4.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen175</barcode><label>TolvenTestUser252TissueSpecimen175</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6486</title><shortTitle>6486</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>2.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen176</barcode><label>TolvenTestUser252TissueSpecimen176</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6486</title><shortTitle>6486</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }

    private String getInsertInvalidSpecimenClassXMLStr() {
        return "<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"Tissue1234Specimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue1234</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>4.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen184</barcode><label>TolvenTestUser252TissueSpecimen184</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }

    private String getInsertInvalidAvailableQuantityXMLStr() {
        return "<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>40.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen184</barcode><label>TolvenTestUser252TissueSpecimen184</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }

    private String getInsertInvalidSpecimenTypeXMLStr() {
        return "<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue123</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>4.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen184</barcode><label>TolvenTestUser252TissueSpecimen184</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }

    private String getInsertInvalidTissueSideXMLStr() {
        return "<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right123</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>4.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen184</barcode><label>TolvenTestUser252TissueSpecimen184</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }

    private String getInsertInvalidTissueSiteXMLStr() {
        return "<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta123</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>4.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen184</barcode><label>TolvenTestUser252TissueSpecimen184</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }

    private String getInsertInvalidPathologicalStatusXMLStr() {
        return "<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant123</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>4.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen184</barcode><label>TolvenTestUser252TissueSpecimen184</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }

    private String getUpdateSpecimenXMLStr() {
        return "<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>4.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen171</barcode><label>TolvenTestUser252TissueSpecimen171</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>2.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen172</barcode><label>TolvenTestUser252TissueSpecimen172</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }

    private String getUpdateSpecimenNotExistXMLStr() {
        return "<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL1</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>5.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen185</barcode><label>TolvenTestUser252TissueSpecimen185</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>1.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen186</barcode><label>TolvenTestUser252TissueSpecimen186</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }

    private String getUpdateSpecimenInvalidAvailableQtyXMLStr() {
        return "<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>400.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen171</barcode><label>TolvenTestUser252TissueSpecimen171</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>2.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen172</barcode><label>TolvenTestUser252TissueSpecimen172</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }

    private String getUpdateSpecimenCollectionProtocolChangeXMLStr() {
        return "<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>5.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen173</barcode><label>TolvenTestUser252TissueSpecimen173</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6483</title><shortTitle>6483</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>2.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen172</barcode><label>TolvenTestUser252TissueSpecimen172</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }

    private String getUpdateSpecimenCollectionProtocolEventChangeXMLStr() {
        return "<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL123</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>5.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen171</barcode><label>TolvenTestUser252TissueSpecimen171</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>2.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen172</barcode><label>TolvenTestUser252TissueSpecimen172</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }

    private String getUpdateSpecimenClassChangeXMLStr() {
        return "<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>4.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen171</barcode><label>TolvenTestUser252TissueSpecimen171</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>2.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen172</barcode><label>TolvenTestUser252TissueSpecimen172</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }

    private String getUpdateSpecimenInvalidSpecimenTypeXMLStr() {
        return "<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue123</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>5.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen171</barcode><label>TolvenTestUser252TissueSpecimen171</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>2.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen172</barcode><label>TolvenTestUser252TissueSpecimen172</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }

    private String getUpdateSpecimenInvalidTissueSideXMLStr() {
        return "<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right123</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>5.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen171</barcode><label>TolvenTestUser252TissueSpecimen171</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>2.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen172</barcode><label>TolvenTestUser252TissueSpecimen172</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }

    private String getUpdateSpecimenInvalidTissueSiteXMLStr() {
        return "<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta123</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>5.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen171</barcode><label>TolvenTestUser252TissueSpecimen171</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>2.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen172</barcode><label>TolvenTestUser252TissueSpecimen172</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }

    private String getRollbackSpecimenXMLStr() {
        return "<?xml version=\"1.0\" ?><specimens><participant><lastName>66604232</lastName><activityStatus>Active</activityStatus></participant><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"TissueSpecimen\"><initialQuantity>9.0</initialQuantity><pathologicalStatus>Malignant</pathologicalStatus><specimenClass>Tissue</specimenClass><specimenType>Fixed Tissue</specimenType><specimenCharacteristics><tissueSide>Right</tissueSide><tissueSite>Placenta</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>5.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen171</barcode><label>TolvenTestUser252TissueSpecimen171</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail><specimenDetail><collectionProtocolEvent>CPL</collectionProtocolEvent><specimen class=\"FluidSpecimen\"><initialQuantity>8.0</initialQuantity><pathologicalStatus>Not Specified</pathologicalStatus><specimenClass>Fluid</specimenClass><specimenType>Not Specified</specimenType><specimenCharacteristics><tissueSide>Not Specified</tissueSide><tissueSite>Not Specified</tissueSite></specimenCharacteristics><activityStatus>Active</activityStatus><availableQuantity>2.0</availableQuantity><barcode>TolvenTestUser252TissueSpecimen172</barcode><label>TolvenTestUser252TissueSpecimen172</label><isAvailable>true</isAvailable><collectionStatus>Collected</collectionStatus></specimen><collectionProtocol><title>6482</title><shortTitle>6482</shortTitle></collectionProtocol></specimenDetail></specimens>";
    }
    // CHECKSTYLE:ON

}
