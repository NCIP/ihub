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

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueConsentTest.class);

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

    private String getConsentXMLStr() {
        return getXMLString("RegisterConsent_catissue.xml");
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = CaTissueConsentClientIntegrationTest.class.getClassLoader().getResourceAsStream(
                "payloads/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            LOG.error("Error while reading contents of file : " + fileName + ". " + e);
        }
        return contents;
    }

}
