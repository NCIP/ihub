package gov.nih.nci.integration.catissue.client;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.easymock.classextension.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import edu.wustl.catissuecore.cacore.CaTissueWritableAppService;
import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.CollectionProtocolEvent;
import edu.wustl.catissuecore.domain.DisposalEventParameters;
import edu.wustl.catissuecore.domain.Specimen;
import edu.wustl.catissuecore.domain.SpecimenCharacteristics;
import edu.wustl.catissuecore.domain.SpecimenCollectionGroup;
import edu.wustl.catissuecore.domain.User;
import gov.nih.nci.system.applicationservice.ApplicationException;

/**
 * This is the TestClass for Specimen flow.
 * 
 * @author Rohit Gupta
 */

public class CaTissueSpecimenTest {

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueSpecimenTest.class);

    private CaTissueSpecimenClient caTissueSpecimenClient = null;
    private CaTissueAPIClientWithRegularAuthentication caTissueAPIClient = null;
    private CaTissueWritableAppService writableAppService = null;

    /**
     * To initialize the things
     * 
     * @throws MalformedURLException - MalformedURLException
     * @throws BeansException - BeansException
     */
    @Test
    @Before
    public void initialize() throws BeansException, MalformedURLException {
        writableAppService = org.easymock.EasyMock.createMock(CaTissueWritableAppService.class);
        caTissueAPIClient = EasyMock.createMock(CaTissueAPIClientWithRegularAuthentication.class);
        caTissueSpecimenClient = new CaTissueSpecimenClient("", "");
        caTissueSpecimenClient.setCaTissueAPIClient(caTissueAPIClient);
    }

    /**
     * Mock Testcase for createSpecimens
     */
    @SuppressWarnings("unchecked")
    @Test
    public void createSpecimens() {
        String retSpecimenXML = "";
        final Specimen specimen = null;

        try {
            EasyMock.expect(caTissueAPIClient.getApplicationService()).andReturn(writableAppService);
            EasyMock.expect(
                    caTissueAPIClient.searchById((Class<Specimen>) EasyMock.anyObject(),
                            (Specimen) org.easymock.EasyMock.anyObject())).andReturn(specimen);

            EasyMock.expect(caTissueAPIClient.insert((Specimen) org.easymock.EasyMock.anyObject())).andReturn(specimen);
            EasyMock.replay(caTissueAPIClient);

            caTissueSpecimenClient.isSpecimensExist(getInsertSpecimenXMLStr());
            caTissueSpecimenClient.createSpecimens(getInsertSpecimenXMLStr());
            retSpecimenXML = "CREATE_SPECIMEN";
        } catch (ApplicationException e) {
            LOG.error("CaTissueSpecimenTest-ApplicationException inside createSpecimens() ", e);
            retSpecimenXML = "CREATE_SPECIMEN_FAILED";
        }
        assertNotNull(retSpecimenXML);
    }

    /**
     * Mock Testcase for createSpecimens for existing specimen scenario
     */
    @SuppressWarnings("unchecked")
    @Test
    public void createExistingSpecimens() {
        String retSpecimenXML = "";
        final Specimen specimen = new Specimen();
        specimen.setLabel("TolvenTestUser252TissueSpecimen173");

        try {
            EasyMock.expect(caTissueAPIClient.getApplicationService()).andReturn(writableAppService);
            EasyMock.expect(
                    caTissueAPIClient.searchById((Class<Specimen>) EasyMock.anyObject(),
                            (Specimen) org.easymock.EasyMock.anyObject())).andReturn(specimen);

            EasyMock.expect(caTissueAPIClient.insert((Specimen) org.easymock.EasyMock.anyObject())).andReturn(specimen);
            EasyMock.replay(caTissueAPIClient);

            caTissueSpecimenClient.isSpecimensExist(getInsertExistingSpecimenXMLStr());
            caTissueSpecimenClient.createSpecimens(getInsertExistingSpecimenXMLStr());
            retSpecimenXML = "CREATE_EXISTING_SPECIMEN_FAILED";
        } catch (ApplicationException e) {
            LOG.error("CaTissueSpecimenTest-ApplicationException inside createSpecimens() ", e);
            retSpecimenXML = "CREATE_EXISTING_SPECIMEN";
        }

        assertNotNull(retSpecimenXML);

    }

    /**
     * Mock Testcase for getting existing Specimens when the specimen is not existing in the caTissue
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getExistingSpecimensSpecimenNotFound() {
        String retXML = null;
        final Specimen specimen = null;

        try {
            EasyMock.expect(caTissueAPIClient.getApplicationService()).andReturn(writableAppService);
            EasyMock.expect(
                    caTissueAPIClient.searchById((Class<Specimen>) EasyMock.anyObject(),
                            (Specimen) org.easymock.EasyMock.anyObject())).andReturn(specimen);

            EasyMock.replay(caTissueAPIClient);

            caTissueSpecimenClient.getExistingSpecimens(getUpdateSpecimenXMLStr());

            retXML = "GET_EXISTING_SPECIMEN";
        } catch (ApplicationException e) {
            LOG.error("CaTissueSpecimenTest-ApplicationException inside getExistingSpecimens() ", e);
            retXML = "GET_EXISTING_SPECIMEN_FAILED";
        }
        assertNotNull(retXML);
    }

    /**
     * Mock Testcase for getting existing Specimens when CPE is not same.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getExistingSpecimensCPENotSame() {
        String retXML = null;
        final Specimen specimen = new Specimen();
        specimen.setLabel("TolvenTestUser252TissueSpecimen173");

        try {
            EasyMock.expect(caTissueAPIClient.getApplicationService()).andReturn(writableAppService);
            EasyMock.expect(
                    caTissueAPIClient.searchById((Class<Specimen>) EasyMock.anyObject(),
                            (Specimen) org.easymock.EasyMock.anyObject())).andReturn(specimen);

            EasyMock.replay(caTissueAPIClient);

            caTissueSpecimenClient.getExistingSpecimens(getUpdateSpecimenXMLStr());

            retXML = "GET_EXISTING_SPECIMEN";
        } catch (ApplicationException e) {
            LOG.error("CaTissueSpecimenTest-ApplicationException inside getExistingSpecimens() ", e);
            retXML = "GET_EXISTING_SPECIMEN_FAILED";
        }
        assertNotNull(retXML);
    }

    /**
     * Mock Testcase for getting existing Specimens when CollectionProtocol is not same
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getExistingSpecimensCollectionProtocolNotSame() {
        String retXML = null;

        final Specimen specimen = new Specimen();
        specimen.setLabel("TolvenTestUser252TissueSpecimen173");
        final SpecimenCollectionGroup spg = new SpecimenCollectionGroup();
        final CollectionProtocolEvent cpe = new CollectionProtocolEvent();
        final CollectionProtocol cp = new CollectionProtocol();
        cp.setShortTitle("ttp");
        cp.setTitle("ttp");
        cpe.setCollectionPointLabel("CPE");
        cpe.setCollectionProtocol(cp);
        spg.setCollectionProtocolEvent(cpe);
        specimen.setSpecimenCollectionGroup(spg);

        try {
            EasyMock.expect(caTissueAPIClient.getApplicationService()).andReturn(writableAppService);
            EasyMock.expect(
                    caTissueAPIClient.searchById((Class<Specimen>) EasyMock.anyObject(),
                            (Specimen) org.easymock.EasyMock.anyObject())).andReturn(specimen);

            EasyMock.replay(caTissueAPIClient);

            caTissueSpecimenClient.getExistingSpecimens(getUpdateSpecimenXMLStr());

            retXML = "GET_EXISTING_SPECIMEN";
        } catch (ApplicationException e) {
            LOG.error("CaTissueSpecimenTest-ApplicationException inside getExistingSpecimens() ", e);
            retXML = "GET_EXISTING_SPECIMEN_FAILED";
        }
        assertNotNull(retXML);
    }

    /**
     * Mock Testcase for getting existing Specimens when SpecimenClass is not same
     */
    @SuppressWarnings("unchecked")
    @Test
    public void getExistingSpecimensSpecimenClassNotSame() {
        String retXML = null;

        final Specimen specimen = new Specimen();
        specimen.setLabel("TolvenTestUser252TissueSpecimen173");
        final SpecimenCollectionGroup spg = new SpecimenCollectionGroup();
        final CollectionProtocolEvent cpe = new CollectionProtocolEvent();
        final CollectionProtocol cp = new CollectionProtocol();
        cp.setShortTitle("6482");
        cp.setTitle("6482");
        cpe.setCollectionPointLabel("CPE");
        cpe.setCollectionProtocol(cp);
        spg.setCollectionProtocolEvent(cpe);
        specimen.setSpecimenCollectionGroup(spg);

        try {
            EasyMock.expect(caTissueAPIClient.getApplicationService()).andReturn(writableAppService);
            EasyMock.expect(
                    caTissueAPIClient.searchById((Class<Specimen>) EasyMock.anyObject(),
                            (Specimen) org.easymock.EasyMock.anyObject())).andReturn(specimen);

            EasyMock.replay(caTissueAPIClient);

            caTissueSpecimenClient.getExistingSpecimens(getUpdateSpecimenXMLStr());

            retXML = "GET_EXISTING_SPECIMEN";
        } catch (ApplicationException e) {
            LOG.error("CaTissueSpecimenTest-ApplicationException inside getExistingSpecimens() ", e);
            retXML = "GET_EXISTING_SPECIMEN_FAILED";
        }
        assertNotNull(retXML);
    }

    /**
     * Mock Testcase for updating existing Specimens
     */
    @SuppressWarnings("unchecked")
    @Test
    public void updateSpecimens() {
        String retXML = null;
        final Specimen specimen = new Specimen();
        specimen.setId(138L);
        specimen.setLabel("TolvenTestUser252TissueSpecimen173");
        specimen.setSpecimenType("Fixed Tissue");
        specimen.setSpecimenClass("Tissue");
        final SpecimenCollectionGroup spg = new SpecimenCollectionGroup();
        spg.setId(4L);
        specimen.setSpecimenCollectionGroup(spg);
        specimen.setLineage("New");
        final SpecimenCharacteristics sc = new SpecimenCharacteristics();
        sc.setId(138L);
        sc.setTissueSide("Right");
        sc.setTissueSite("Placenta");
        specimen.setSpecimenCharacteristics(sc);
        try {
            EasyMock.expect(caTissueAPIClient.getApplicationService()).andReturn(writableAppService);
            EasyMock.expect(
                    caTissueAPIClient.searchById((Class<Specimen>) EasyMock.anyObject(),
                            (Specimen) org.easymock.EasyMock.anyObject())).andReturn(specimen);

            EasyMock.expect(caTissueAPIClient.update((Specimen) org.easymock.EasyMock.anyObject())).andReturn(specimen);
            EasyMock.replay(caTissueAPIClient);

            caTissueSpecimenClient.updateSpecimens(getUpdateSpecimenXMLStr());

            retXML = "UPDATE_SPECIMEN";
        } catch (ApplicationException e) {
            LOG.error("CaTissueSpecimenTest-ApplicationException inside updateSpecimens() ", e);
            retXML = "UPDATE_SPECIMEN_FAILED";
        }
        assertNotNull(retXML);
    }

    /**
     * Mock Testcase for Rollback Updated Specimens
     */
    @SuppressWarnings("unchecked")
    @Test
    public void rollbackUpdatedSpecimens() {
        String retXML = null;
        final Specimen specimen = new Specimen();
        specimen.setId(138L);
        specimen.setLabel("TolvenTestUser252TissueSpecimen173");
        specimen.setSpecimenType("Fixed Tissue");
        specimen.setSpecimenClass("Tissue");
        final SpecimenCollectionGroup spg = new SpecimenCollectionGroup();
        spg.setId(4L);
        specimen.setSpecimenCollectionGroup(spg);
        specimen.setLineage("New");
        final SpecimenCharacteristics sc = new SpecimenCharacteristics();
        sc.setId(138L);
        sc.setTissueSide("Right");
        sc.setTissueSite("Placenta");
        specimen.setSpecimenCharacteristics(sc);
        try {
            EasyMock.expect(caTissueAPIClient.getApplicationService()).andReturn(writableAppService);
            EasyMock.expect(
                    caTissueAPIClient.searchById((Class<Specimen>) EasyMock.anyObject(),
                            (Specimen) org.easymock.EasyMock.anyObject())).andReturn(specimen);

            EasyMock.expect(caTissueAPIClient.update((Specimen) org.easymock.EasyMock.anyObject())).andReturn(specimen);
            EasyMock.replay(caTissueAPIClient);

            caTissueSpecimenClient.rollbackUpdatedSpecimens(getRollbackSpecimenXMLStr());

            retXML = "ROLLBACK_UPDATE_SPECIMEN";
        } catch (ApplicationException e) {
            LOG.error("CaTissueSpecimenTest-ApplicationException inside updateSpecimens() ", e);
            retXML = "ROLLBACK_UPDATE_SPECIMEN_FAILED";
        }
        assertNotNull(retXML);
    }

    /**
     * Mock Testcase for Rollback Created Specimens
     */
    @SuppressWarnings("unchecked")
    @Test
    public void rollbackCreatedSpecimens() {
        String retXML = null;
        final Specimen specimen = new Specimen();
        specimen.setId(138L);
        specimen.setLabel("TolvenTestUser252TissueSpecimen173");
        final User user = new User();
        user.setLoginName("admin@admin.com");

        try {
            EasyMock.expect(caTissueAPIClient.getApplicationService()).andReturn(writableAppService);
            EasyMock.expect(
                    caTissueAPIClient.searchById((Class<Specimen>) EasyMock.anyObject(),
                            (Specimen) org.easymock.EasyMock.anyObject())).andReturn(specimen);
            EasyMock.expect(
                    caTissueAPIClient.searchById((Class<User>) EasyMock.anyObject(),
                            (User) org.easymock.EasyMock.anyObject())).andReturn(user);

            EasyMock.expect(caTissueAPIClient.update((Specimen) org.easymock.EasyMock.anyObject())).andReturn(specimen)
                    .anyTimes();

            final DisposalEventParameters disposalEventParameters = new DisposalEventParameters();

            EasyMock.expect(caTissueAPIClient.insert((DisposalEventParameters) org.easymock.EasyMock.anyObject()))
                    .andReturn(disposalEventParameters).anyTimes();

            EasyMock.replay(caTissueAPIClient);

            caTissueSpecimenClient.rollbackCreatedSpecimens(getRollbackSpecimenXMLStr());

            retXML = "ROLLBACK_CREATE_SPECIMEN";
        } catch (ApplicationException e) {
            LOG.error("CaTissueSpecimenTest-ApplicationException inside updateSpecimens() ", e);
            retXML = "ROLLBACK_CREATE_SPECIMEN_FAILED";
        }
        assertNotNull(retXML);
    }

    private String getInsertSpecimenXMLStr() {
        return getXMLString("CreateSpecimen_Mock.xml");
    }

    private String getInsertExistingSpecimenXMLStr() {
        return getXMLString("CreateExistingSpecimen_Mock.xml");
    }

    private String getUpdateSpecimenXMLStr() {
        return getXMLString("UpdateSpecimen_Mock.xml");
    }

    private String getRollbackSpecimenXMLStr() {
        return getXMLString("RollbackSpecimen_Mock.xml");
    }

    private String getXMLString(String fileName) {
        final StringBuffer fileContents = new StringBuffer();
        final InputStream is = CaTissueSpecimenIntegrationTest.class.getClassLoader().getResourceAsStream(
                "payloads_specimen/" + fileName);
        final BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String strLine;
        try {
            while ((strLine = br.readLine()) != null) { // NOPMD
                fileContents.append(strLine);
            }
            is.close();
        } catch (IOException e) {
            System.err.println("Error while reading contents of file : " + fileName + ". " + e);// NOPMD
        }
        return fileContents.toString();
    }

}
