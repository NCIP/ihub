package gov.nih.nci.integration.catissue.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
            caTissueSpecimenClient = new CaTissueSpecimenClient("admin@admin.com", "caTissue20");
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
     * Testcase for Create Specimen
     */
    @Test
    public void createSpecimens() {
        String existXML = null;
        String createdXML = "CREATED";
        try {
            existXML = caTissueSpecimenClient.isSpecimensExist(getInsertSpecimenXMLStr());
            caTissueSpecimenClient.createSpecimens(getInsertSpecimenXMLStr());
        } catch (ApplicationException e) {
            existXML = null;
            createdXML = null;
        }
        assertNotNull(existXML);
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
            retXML = null;
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
            caTissueSpecimenClient.getExistingSpecimens(getUpdateSpecimenNotExistXMLStr());
            retXML = caTissueSpecimenClient.updateSpecimens(getUpdateSpecimenNotExistXMLStr());
        } catch (ApplicationException e) {
            retXML = null;
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
            caTissueSpecimenClient.getExistingSpecimens(getUpdateSpecimenInvalidAvailableQtyXMLStr());
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
    // @Test
    public void rollbackSpecimens() {
        String retXML = "DELETE_SPECIMEN";
        try {
            caTissueSpecimenClient.rollbackCreatedSpecimens(getRollbackSpecimenXMLStr());
        } catch (ApplicationException e) {
            retXML = null;
        }
        assertNotNull(retXML);
    }

    private String getInsertSpecimenXMLStr() {
        return getXMLString("CreateSpecimen_catissue.xml");
    }

    private String getInsertExistingSpecimenXMLStr() {
        return getXMLString("CreateExistingSpecimen_catissue.xml");
    }

    private String getInsertInvalidCollectionProtocolXMLStr() {
        return getXMLString("CreateSpecimenInvalidCollectionProtocol_catissue.xml");
    }

    private String getInsertInvalidSpecimenClassXMLStr() {
        return getXMLString("CreateSpecimenInvalidSpecimenClass_catissue.xml");
    }

    private String getInsertInvalidAvailableQuantityXMLStr() {
        return getXMLString("CreateSpecimenInvalidAvailableQuantity_catissue.xml");
    }

    private String getInsertInvalidSpecimenTypeXMLStr() {
        return getXMLString("CreateSpecimenInvalidSpecimenType_catissue.xml");
    }

    private String getInsertInvalidTissueSideXMLStr() {
        return getXMLString("CreateSpecimenInvalidTissueSide_catissue.xml");
    }

    private String getInsertInvalidTissueSiteXMLStr() {
        return getXMLString("CreateSpecimenInvalidTissueSite_catissue.xml");
    }

    private String getUpdateSpecimenXMLStr() {
        return getXMLString("UpdateSpecimen_catissue.xml");
    }

    private String getUpdateSpecimenNotExistXMLStr() {
        return getXMLString("UpdateSpecimenNotExist_catissue.xml");
    }

    private String getUpdateSpecimenInvalidAvailableQtyXMLStr() {
        return getXMLString("UpdateSpecimenInvalidAvailableQty_catissue.xml");
    }

    private String getUpdateSpecimenCollectionProtocolChangeXMLStr() {
        return getXMLString("UpdateSpecimenCollectionProtocolChange_catissue.xml");
    }

    private String getUpdateSpecimenCollectionProtocolEventChangeXMLStr() {
        return getXMLString("UpdateSpecimenCollectionProtocolEventChange_catissue.xml");
    }

    private String getUpdateSpecimenClassChangeXMLStr() {
        return getXMLString("UpdateSpecimenClassChange_catissue.xml");
    }

    private String getUpdateSpecimenInvalidSpecimenTypeXMLStr() {
        return getXMLString("UpdateSpecimenInvalidSpecimenType_catissue.xml");
    }

    private String getUpdateSpecimenInvalidTissueSideXMLStr() {
        return getXMLString("UpdateSpecimenInvalidTissueSide_catissue.xml");
    }

    private String getUpdateSpecimenInvalidTissueSiteXMLStr() {
        return getXMLString("UpdateSpecimenInvalidTissueSite_catissue.xml");
    }

    private String getRollbackSpecimenXMLStr() {
        return getXMLString("RollbackSpecimen_catissue.xml");
    }

    private String getXMLString(String fileName) {
        final StringBuffer fileContents = new StringBuffer();
        final InputStream is = CaTissueSpecimenIntegrationTest.class.getClassLoader().getResourceAsStream(
                "payloads/specimen/" + fileName);
        final BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String strLine;
        try {
            while ((strLine = br.readLine()) != null) {
                fileContents.append(strLine);
            }
            is.close();
        } catch (IOException e) {
            LOG.error("CaTissueSpecimenIntegrationTest-IOException inside getXMLString() ", e);
        }
        return fileContents.toString();
    }

}
