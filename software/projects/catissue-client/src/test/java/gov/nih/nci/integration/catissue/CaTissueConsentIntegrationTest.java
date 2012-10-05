package gov.nih.nci.integration.catissue;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import gov.nih.nci.integration.catissue.client.CaTissueConsentClient;
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
    @Before
    public void initialize() {
        try {
            caTissueConsentClient = new CaTissueConsentClient("admin@admin.com", "caTissue20");
        } catch (BeansException e) {
            LOG.error("CaTissueConsentIntegrationTest-BeansException inside initialize() ", e);
        } catch (MalformedURLException e) {
            LOG.error("CaTissueConsentIntegrationTest-ApplicationException inside initialize() ", e);
        }
      
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
    // @Test
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

    private String getRegisterConsentXMLStr() {
        return getXMLString("RegisterConsent_catissue.xml");
    }

    private String getRegisterConsentSpecimenNotExistXMLStr() {
        return getXMLString("RegisterConsentSpecimenNotExist_catissue.xml");
    }

    private String getRegisterConsentStatementNotExistXMLStr() {
        return getXMLString("RegisterConsentStatementNotExist_catissue.xml");
    }

    private String getRollbackConsentXMLStr() {
        return getXMLString("RollbackSpecimen_catissue.xml");
    }

    private String getXMLString(String fileName) {
        final StringBuffer fileContents = new StringBuffer();
        final InputStream is = CaTissueConsentIntegrationTest.class.getClassLoader().getResourceAsStream(
                "payloads/consent/" + fileName);
        final BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String strLine;
        try {
            while ((strLine = br.readLine()) != null) {
                fileContents.append(strLine);
            }
            is.close();
        } catch (IOException e) {
            LOG.error("CaTissueConsentIntegrationTest-IOException inside getXMLString() ", e);
        }
        return fileContents.toString();
    }

}
