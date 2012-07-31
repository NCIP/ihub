package gov.nih.nci.integration.catissue;

import static org.junit.Assert.assertNotNull;
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
 * IntegrationTest class for Specimen
 * 
 * @author Rohit Gupta
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext-transcend-catissue.xml")
public class CaTissueSpecimenClientIntegrationTest {

    @Autowired
    private CaTissueSpecimenClient caTissueSpecimenClient;

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueSpecimenClientIntegrationTest.class);

    /**
     * Test case when trying to create existing Specimen
     */
    @Test
    public void createExistingSpecimen() {
        final ServiceInvocationResult svc = caTissueSpecimenClient.createSpecimens(getCreateExistingSpecimenXMLStr());
        assertNotNull(svc);

    }

    /**
     * Testcase for createSpecimen
     */
    @Test
    public void createSpecimen() {
        final ServiceInvocationResult svc = caTissueSpecimenClient.createSpecimens(getCreateSpecimenXMLStr());
        assertNotNull(svc);

    }

    /**
     * Testcase for updateSpecimen
     */
    @Test
    public void updateSpecimen() {
        final ServiceInvocationResult svc = caTissueSpecimenClient.updateSpecimens(getUpdateSpecimenXMLStr());
        assertNotNull(svc);

    }

    /**
     * Testcase for updateSpecimen when specimen doesn't exist
     */
    @Test
    public void updateSpecimenNotExist() {
        final ServiceInvocationResult svc = caTissueSpecimenClient.updateSpecimens(getUpdateSpecimenNotExistXMLStr());
        assertNotNull(svc);

    }

    private String getCreateExistingSpecimenXMLStr() {
        return getXMLString("CreateExistingSpecimen.xml");
    }

    private String getCreateSpecimenXMLStr() {
        return getXMLString("CreateSpecimen.xml");
    }

    private String getUpdateSpecimenNotExistXMLStr() {
        return getXMLString("UpdateSpecimenNotExist.xml");
    }

    private String getUpdateSpecimenXMLStr() {
        return getXMLString("UpdateSpecimen.xml");
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = CaTissueSpecimenClientIntegrationTest.class.getClassLoader().getResourceAsStream(
                "payloads/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            LOG.error("Error while reading contents of file : " + fileName + ". " + e);
        }
        return contents;
    }
}
