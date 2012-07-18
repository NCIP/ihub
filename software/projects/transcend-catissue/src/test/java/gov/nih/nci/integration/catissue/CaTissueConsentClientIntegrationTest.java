package gov.nih.nci.integration.catissue;

import gov.nih.nci.integration.invoker.ServiceInvocationResult;

import java.io.IOException;
import java.io.InputStream;

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

    private String getRegisterConsentXMLStr() {
        return getXMLString("RegisterConsent.xml");
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = CaTissueConsentClientIntegrationTest.class.getClassLoader().getResourceAsStream(
                "payloads/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            System.err.println("Error while reading contents of file : " + fileName + ". " + e);// NOPMD
        }
        return contents;
    }

}
