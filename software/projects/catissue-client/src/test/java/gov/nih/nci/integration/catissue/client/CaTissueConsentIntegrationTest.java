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
 * This is the TestClass for registerConsent flow.
 * 
 * @author Rohit Gupta
 */

public class CaTissueConsentIntegrationTest {

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueConsentIntegrationTest.class);
    private CaTissueConsentClient caTissueConsentClient;

    /**
     * To initialize the things
     */
    @Test
    @Before
    public void initialize() {
        try {
            caTissueConsentClient = new CaTissueConsentClient("admin@admin.com", "Rohit123");
        } catch (BeansException e) {
            LOG.error("CaTissueConsentIntegrationTest-BeansException inside initialize() ", e);
        } catch (MalformedURLException e) {
            LOG.error("CaTissueConsentIntegrationTest-ApplicationException inside initialize() ", e);
        }

        assertNotNull(caTissueConsentClient);
    }

    /**
     * Testcase for registerConsents flow
     */
    @Test
    public void registerConsents() {
        String retConsentXML = null;
        try {
            caTissueConsentClient.registerConsents(getRegisterConsentXMLStr());
            retConsentXML = "REGISTER_CONSENT";
        } catch (ApplicationException e) {
            LOG.error("CaTissueConsentIntegrationTest-ApplicationException inside registerConsents() ", e);
        }
        assertNotNull(retConsentXML);
    }

    /**
     * Testcase for registerConsents when Specimen doesn't exist
     */
    @Test
    public void registerConsentsSpecimenNotExist() {
        String existXML = null;
        String retXML = null;
        try {
            caTissueConsentClient.getExistingConsents(getRegisterConsentSpecimenNotExistXMLStr());
            caTissueConsentClient.registerConsents(getRegisterConsentSpecimenNotExistXMLStr());
            existXML = "SPECIMEN_EXIST_FOR_REGISTER_CONSENT";
            retXML = "REGISTER_SPECIMEN_RETURN";
        } catch (ApplicationException e) {
            LOG.error("ApplicationException inside registerConsentsSpecimenNotExist() ", e);
        }
        assertNull(existXML);
        assertNull(retXML);
    }

    /**
     * Testcase for registerConsents when collectionProtocol doesn't exist
     */
    @Test
    public void registerConsentsCollectionProtocolNotExist() {
        String existXML = null;
        String retXML = null;
        try {
            caTissueConsentClient.getExistingConsents(getRegisterConsentCollectionProtocolNotExistXMLStr());
            caTissueConsentClient.registerConsents(getRegisterConsentCollectionProtocolNotExistXMLStr());
            existXML = "SPECIMEN_EXIST";
            retXML = "REGISTER_SPECIMEN";
        } catch (ApplicationException e) {
            LOG.error("ApplicationException inside registerConsentsCollectionProtocolNotExist() ", e);
        }
        assertNull(existXML);
        assertNull(retXML);
    }

    /**
     * Testcase for registerConsents when Tier statement doesn't exist
     */
    @Test
    public void registerConsentsStatementNotExist() {
        String existXML = null;
        String retXML = null;
        try {
            caTissueConsentClient.getExistingConsents(getRegisterConsentStatementNotExistXMLStr());
            caTissueConsentClient.registerConsents(getRegisterConsentStatementNotExistXMLStr());
            existXML = "SPECIMEN_EXIST";
            retXML = "REGISTER_SPECIMEN";
        } catch (ApplicationException e) {
            LOG.error("ApplicationException inside registerConsentsCollectionProtocolNotExist() ", e);
        }
        assertNull(existXML);
        assertNull(retXML);
    }

    /**
     * Testcase for rollback of registerConsents
     */
//    @Test
    public void rollbackConsents() {
        String existXML = null;
        String retXML = null;

        try {
            caTissueConsentClient.getExistingConsents(getRollbackConsentXMLStr());
            caTissueConsentClient.rollbackConsentRegistration(getRollbackConsentXMLStr());
            existXML = "SPECIMEN_EXIST";
            retXML = "REGISTER_SPECIMEN";
        } catch (ApplicationException e) {
            LOG.error("ApplicationException inside rollbackConsents() ", e);
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
