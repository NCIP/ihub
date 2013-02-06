package gov.nih.nci.integration.catissue;

import gov.nih.nci.integration.invoker.ServiceInvocationResult;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private CaTissueParticipantClient caTissueParticipantClient;
    
    @Autowired
    private CaTissueSpecimenClient caTissueSpecimenClient;

    @Autowired
    private CaTissueConsentClient caTissueConsentClient;

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueConsentClientIntegrationTest.class);

    /**
     * Test for RegisterConsent
     */
    @Test
    public void registerConsents() {
       caTissueParticipantClient.registerParticipant(getParticipantXMLStr());
        try {
          //wait for scg to be created
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } 
        caTissueSpecimenClient.createSpecimens(getSpecimenXMLStr());
        final ServiceInvocationResult svc = caTissueConsentClient.registerConsents(getRegisterConsentXMLStr());

        if (svc.isDataChanged() && svc.getInvocationException() != null) {
            caTissueConsentClient.rollbackConsents(svc.getOriginalData().toString());
        }
    }
    
    private String getParticipantXMLStr() {
        return getXMLString("CreateParticipantForConsentIntg_catissue.xml");
    }
    
    private String getSpecimenXMLStr() {
        return getXMLString("CreateSpecimenForConsentIntg_catissue.xml");
    }
    
    private String getRegisterConsentXMLStr() {
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
