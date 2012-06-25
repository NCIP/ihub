package gov.nih.nci.integration.catissue;

import gov.nih.nci.integration.invoker.ServiceInvocationResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This is the TestClass for registerConsent flow (from Wrappper Client).
 * 
 * @author Rohit Gupta
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext-transcend-catissue.xml")
public class CaTissueConsentClientIntegrationTest {

    @Autowired
    private CaTissueConsentClient caTissueConsentClient;

    /**
     * Test for RegisterConsent
     */
    @Test
    public void registerConsents() {
        final ServiceInvocationResult svc = caTissueConsentClient.registerConsents(getRegisterConsentXMLStr());

        if (svc.isDataChanged() && svc.getInvocationException() != null) {
            caTissueConsentClient.rollbackConsents(svc.getOriginalData().toString());
        }

    }

    // CHECKSTYLE:OFF
    private String getRegisterConsentXMLStr() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><consents xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns:ns0=\"http://caXchange.nci.nih.gov/messaging\" xmlns:a=\"http://cacis.nci.nih.gov\"><participant><lastName>66604232</lastName></participant><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><consentData><specimenLabel>TolvenTestUser252TissueSpecimen124</specimenLabel><consentTierStatus><consentTier><statement>This is a statement</statement></consentTier><status>Yes</status></consentTierStatus><consentTierStatus><consentTier><statement>This is a second statement.</statement></consentTier><status>No</status></consentTierStatus></consentData><collectionProtocol><title>Tolven Tissue Protocol</title><shortTitle>ttp</shortTitle></collectionProtocol></consentDetails><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><consentData><specimenLabel>TolvenTestUser252TissueSpecimen155</specimenLabel><consentTierStatus><consentTier><statement>This is a statement</statement></consentTier><status>Not Specified</status></consentTierStatus><consentTierStatus><consentTier><statement>This is a second statement.</statement></consentTier><status>Withdrawn</status></consentTierStatus></consentData><collectionProtocol><title>Tolven Tissue Protocol</title><shortTitle>ttp</shortTitle></collectionProtocol></consentDetails></consents>";
    }

}
