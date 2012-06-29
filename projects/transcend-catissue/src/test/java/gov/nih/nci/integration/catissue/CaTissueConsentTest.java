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
 * This is the test class to test the methods of the Wrapper Consent Client.
 * 
 * @author Rohit Gupta
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext-transcend-catissue.xml")
public class CaTissueConsentTest {

    private CaTissueConsentClient caTissueConsentClient;

    @Value("${catissue.custom.lib.location}")
    private String caTissueLibLocation;

    @Value("${catissue.api.login.username}")
    private String catissueApiLoginName;

    @Value("${catissue.api.login.password}")
    private String catissueApiPassword;

    @Value("${catissue.consent.mock.client}")
    private String consentMockClientClass;

    /**
     * To initialize the things
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    @Before
    public void initialize() throws IntegrationException {
        caTissueConsentClient = new CaTissueConsentClient(caTissueLibLocation, catissueApiLoginName,
                catissueApiPassword, consentMockClientClass);
    }

    /**
     * TestCase to test the registerConsents of Wrapper client
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    public void registerConsents() throws IntegrationException {
        final ServiceInvocationResult svc = caTissueConsentClient.registerConsents(getConsentXMLStr());
        assertNotNull(svc);
    }
    
    
    /**
     * TestCase to test the rollbackConsents of Wrapper client
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    public void rollbackConsents() throws IntegrationException {
        final ServiceInvocationResult svc = caTissueConsentClient.rollbackConsents(getConsentXMLStr());
        assertNotNull(svc);
    }

    // CHECKSTYLE:OFF
    private String getConsentXMLStr() {
        return "<?xml version=\"1.0\" ?><consents><participant><lastName>66604232</lastName></participant><consentDetails><collectionProtocolEvent>CPL</collectionProtocolEvent><consentData><specimenLabel>TolvenTestUser252TissueSpecimen173</specimenLabel><consentTierStatus><consentTier><statement>This is a statement</statement></consentTier><status>Yes</status></consentTierStatus><consentTierStatus><consentTier><statement>This is a second statement.</statement></consentTier><status>No</status></consentTierStatus></consentData><collectionProtocol><title>Tolven Tissue Protocol</title><shortTitle>ttp</shortTitle></collectionProtocol></consentDetails></consents>";
    }

}
