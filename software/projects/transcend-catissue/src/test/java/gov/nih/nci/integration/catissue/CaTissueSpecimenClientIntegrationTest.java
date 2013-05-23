/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.catissue;

import static org.junit.Assert.*;
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
    private CaTissueParticipantClient caTissueParticipantClient;
    
    @Autowired
    private CaTissueSpecimenClient caTissueSpecimenClient;

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueSpecimenClientIntegrationTest.class);

    /**
     * Testcase for createSpecimen
     */
    @Test
    public void createSpecimen() {
        ServiceInvocationResult svca = caTissueParticipantClient.registerParticipant(getParticipantXMLStr());
        ServiceInvocationResult svc = caTissueSpecimenClient.createSpecimens(getCreateSpecimenXMLStr());
        assertNotNull(svc);
        assertFalse(svc.isFault());
    }
    
    /**
     * Test case when trying to create existing Specimen
     */
    @Test
    public void createExistingSpecimen() {
        final ServiceInvocationResult svc = caTissueSpecimenClient.createSpecimens(getCreateExistingSpecimenXMLStr());
        assertNotNull(svc);
        assertTrue(svc.isFault());
    }
    

    /**
     * Testcase for updateSpecimen
     */
    @Test
    public void updateSpecimen() {
        final ServiceInvocationResult svc = caTissueSpecimenClient.updateSpecimens(getUpdateSpecimenXMLStr());
        assertNotNull(svc);
        assertFalse(svc.isFault());
    }

    /**
     * Testcase for updateSpecimen when specimen doesn't exist
     */
    @Test
    public void updateSpecimenNotExist() {
        final ServiceInvocationResult svc = caTissueSpecimenClient.updateSpecimens(getUpdateSpecimenNotExistXMLStr());
        assertNotNull(svc);
        assertTrue(svc.isFault());
    }
    
    private String getParticipantXMLStr() {
        return getXMLString("CreateParticipantForSpecimen_catissue.xml");
    }

    private String getCreateExistingSpecimenXMLStr() {
        return getXMLString("CreateExistingSpecimen_catissue.xml");
    }

    private String getCreateSpecimenXMLStr() {
        return getXMLString("CreateSpecimen_catissue.xml");
    }

    private String getUpdateSpecimenNotExistXMLStr() {
        return getXMLString("UpdateSpecimenNotExist_catissue.xml");
    }

    private String getUpdateSpecimenXMLStr() {
        return getXMLString("UpdateSpecimen_catissue.xml");
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
