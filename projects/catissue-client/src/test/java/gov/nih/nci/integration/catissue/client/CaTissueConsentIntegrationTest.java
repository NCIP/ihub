package gov.nih.nci.integration.catissue.client;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * This is the TestClass for registerConsent flow.
 * 
 * @author Rohit Gupta
 */

public class CaTissueConsentIntegrationTest {

    /**
     * Testcase for registerConsents flow
     */
    @Test
    public void registerConsents() {
        String retXML = "REGISTER_SPECIMEN";
        CaTissueConsentClient caTissueConsentClient;
        try {
            caTissueConsentClient = new CaTissueConsentClient("admin@admin.com", "Rohit123");
            caTissueConsentClient.registerConsents(getRegisterConsentXMLStr());
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // CHECKSTYLE:ON
            e.printStackTrace();
            retXML = null;
        }
        assertNotNull(retXML);
    }

    /**
     * Testcase for registerConsents when Specimen doesn't exist
     */
    @Test
    public void registerConsentsSpecimenNotExist() {
        String existXML = "SPECIMEN_EXIST";
        String retXML = "REGISTER_SPECIMEN";
        CaTissueConsentClient caTissueConsentClient;
        try {
            caTissueConsentClient = new CaTissueConsentClient("admin@admin.com", "Rohit123");
            caTissueConsentClient.getExistingConsents(getRegisterConsentSpecimenNotExistXMLStr());
            caTissueConsentClient.registerConsents(getRegisterConsentSpecimenNotExistXMLStr());
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // CHECKSTYLE:ON
            existXML = null;
            retXML = null;
        }
        assertNull(existXML);
        assertNull(retXML);
    }

    /**
     * Testcase for registerConsents when collectionProtocol doesn't exist
     */
    @Test
    public void registerConsentsCollectionProtocolNotExist() {
        String existXML = "SPECIMEN_EXIST";
        String retXML = "REGISTER_SPECIMEN";
        CaTissueConsentClient caTissueConsentClient;
        try {
            caTissueConsentClient = new CaTissueConsentClient("admin@admin.com", "Rohit123");
            caTissueConsentClient.getExistingConsents(getRegisterConsentCollectionProtocolNotExistXMLStr());
            caTissueConsentClient.registerConsents(getRegisterConsentCollectionProtocolNotExistXMLStr());
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // CHECKSTYLE:ON
            existXML = null;
            retXML = null;
        }
        assertNull(existXML);
        assertNull(retXML);
    }

    /**
     * Testcase for registerConsents when Tier statement doesn't exist
     */
    @Test
    public void registerConsentsStatementNotExist() {
        String existXML = "SPECIMEN_EXIST";
        String retXML = "REGISTER_SPECIMEN";
        CaTissueConsentClient caTissueConsentClient;
        try {
            caTissueConsentClient = new CaTissueConsentClient("admin@admin.com", "Rohit123");
            caTissueConsentClient.getExistingConsents(getRegisterConsentStatementNotExistXMLStr());
            caTissueConsentClient.registerConsents(getRegisterConsentStatementNotExistXMLStr());
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // CHECKSTYLE:ON
            existXML = null;
            retXML = null;
        }
        assertNull(existXML);
        assertNull(retXML);
    }

    /**
     * Testcase for rollback of registerConsents 
     */
    @Test
    public void rollbackConsents() {
        String existXML = "SPECIMEN_EXIST";
        String retXML = "REGISTER_SPECIMEN";

        CaTissueConsentClient caTissueConsentClient;
        try {
            caTissueConsentClient = new CaTissueConsentClient("admin@admin.com", "Rohit123");
            caTissueConsentClient.getExistingConsents(getRollbackConsentXMLStr());
            caTissueConsentClient.rollbackConsentRegistration(getRollbackConsentXMLStr());
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // CHECKSTYLE:ON
            e.printStackTrace();
            existXML = null;
            retXML = null;
        }
        assertNotNull(existXML);
        assertNotNull(retXML);
    }

    // CHECKSTYLE:OFF
    private String getRegisterConsentXMLStr() {
        return "<?xml version=\"1.0\" ?><consents><participant><lastName>66604232</lastName></participant><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><consentData><specimenLabel>TolvenTestUser252TissueSpecimen173</specimenLabel><consentTierStatus><consentTier><statement>This is a statement</statement></consentTier><status>Yes</status></consentTierStatus><consentTierStatus><consentTier><statement>This is a second statement.</statement></consentTier><status>No</status></consentTierStatus></consentData><collectionProtocol><title>Tolven Tissue Protocol</title><shortTitle>ttp</shortTitle></collectionProtocol></consentDetails></consents>";
    }

    private String getRegisterConsentSpecimenNotExistXMLStr() {
        return "<?xml version=\"1.0\" ?><consents><participant><lastName>66604232</lastName></participant><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><consentData><specimenLabel>TolvenTestUser252TissueSpecimen200</specimenLabel><consentTierStatus><consentTier><statement>This is a statement</statement></consentTier><status>Yes</status></consentTierStatus><consentTierStatus><consentTier><statement>This is a second statement.</statement></consentTier><status>No</status></consentTierStatus></consentData><collectionProtocol><title>Tolven Tissue Protocol</title><shortTitle>ttp</shortTitle></collectionProtocol></consentDetails></consents>";
    }

    private String getRegisterConsentStatementNotExistXMLStr() {
        return "<?xml version=\"1.0\" ?><consents><participant><lastName>66604232</lastName></participant><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><consentData><specimenLabel>TolvenTestUser252TissueSpecimen173</specimenLabel><consentTierStatus><consentTier><statement>This statement does not exist</statement></consentTier><status>Yes</status></consentTierStatus><consentTierStatus><consentTier><statement>This is a second statement.</statement></consentTier><status>No</status></consentTierStatus></consentData><collectionProtocol><title>Tolven Tissue Protocol</title><shortTitle>ttp</shortTitle></collectionProtocol></consentDetails></consents>";
    }

    private String getRegisterConsentCollectionProtocolNotExistXMLStr() {
        return "<?xml version=\"1.0\" ?><consents><participant><lastName>66604232</lastName></participant><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><consentData><specimenLabel>TolvenTestUser252TissueSpecimen173</specimenLabel><consentTierStatus><consentTier><statement>This is a statement</statement></consentTier><status>Yes</status></consentTierStatus><consentTierStatus><consentTier><statement>This is a second statement.</statement></consentTier><status>No</status></consentTierStatus></consentData><collectionProtocol><title>Tolven Tissue Protocol123</title><shortTitle>ttp123</shortTitle></collectionProtocol></consentDetails></consents>";
    }

    private String getRollbackConsentXMLStr() {
        return "<?xml version=\"1.0\" ?><consents><participant><lastName>66604232</lastName></participant><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><consentData><specimenLabel>TolvenTestUser252TissueSpecimen173</specimenLabel><consentTierStatus><consentTier><statement>This is a statement</statement></consentTier><status>Yes</status></consentTierStatus><consentTierStatus><consentTier><statement>This is a second statement.</statement></consentTier><status>No</status></consentTierStatus></consentData><collectionProtocol><title>Tolven Tissue Protocol</title><shortTitle>ttp</shortTitle></collectionProtocol></consentDetails></consents>";
    }
    // CHECKSTYLE:ON

}
