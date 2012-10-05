package gov.nih.nci.integration.caaers;

import gov.nih.nci.cabig.caaers.integration.schema.common.CaaersServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.ServiceResponse;
import gov.nih.nci.cabig.caaers.integration.schema.common.Status;
import gov.nih.nci.cabig.caaers.integration.schema.participant.CreateParticipant;
import gov.nih.nci.cabig.caaers.integration.schema.participant.CreateParticipantResponse;
import gov.nih.nci.cabig.caaers.integration.schema.participant.DeleteParticipant;
import gov.nih.nci.cabig.caaers.integration.schema.participant.DeleteParticipantResponse;
import gov.nih.nci.cabig.caaers.integration.schema.participant.GetParticipant;
import gov.nih.nci.cabig.caaers.integration.schema.participant.GetParticipantResponse;
import gov.nih.nci.cabig.caaers.integration.schema.participant.ParticipantServiceInterface;
import gov.nih.nci.cabig.caaers.integration.schema.participant.UpdateParticipant;
import gov.nih.nci.cabig.caaers.integration.schema.participant.UpdateParticipantResponse;
import gov.nih.nci.integration.exception.IntegrationException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

import javax.xml.bind.JAXBException;

import org.easymock.classextension.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This client test code only tests the client communication and does code coverage.
 * 
 * @author Rohit Gupta
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-caaers-client-test.xml")
public class CaAERSParticipantTest {

    private CaAERSParticipantServiceWSClient caAERSParticipantServiceClient;
    private ParticipantServiceInterface client;
    private static final Logger LOG = LoggerFactory.getLogger(CaAERSParticipantTest.class);

    /**
     * To initialize the things
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Before
    public void initialize() throws IntegrationException {
        client = EasyMock.createMock(ParticipantServiceInterface.class);
        caAERSParticipantServiceClient = new CaAERSParticipantServiceWSClient(client);
    }

    /**
     * Testcase for createParticipant
     */
    @Test
    public void createParticipant() {
        final String participantXMLStr = getPStr();
        final CreateParticipantResponse response = new CreateParticipantResponse();
        final CaaersServiceResponse caaersServiceResponse = new CaaersServiceResponse();
        final ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(Status.PROCESSED);
        serviceResponse.setResponsecode("0");
        serviceResponse.setMessage("Participant Cherry062801 Blossom062801  Created in caAERS");
        caaersServiceResponse.setServiceResponse(serviceResponse);
        response.setCaaersServiceResponse(caaersServiceResponse);
        try {
            EasyMock.expect(client.createParticipant((CreateParticipant) org.easymock.EasyMock.anyObject())).andReturn(
                    response);
            EasyMock.replay(client);

            caAERSParticipantServiceClient.createParticipant(participantXMLStr);
        } catch (Exception e) {
            LOG.error("Exception occured : " + e);
        }
    }

    /**
     * Testcase for getParticipant
     * 
     * @throws JAXBException - JAXBException
     * @throws MalformedURLException - MalformedURLException
     */
    @Test
    public void getParticipant() throws MalformedURLException, JAXBException {
        final String participantXMLStr = getPStr();
        final GetParticipantResponse response = new GetParticipantResponse();
        final CaaersServiceResponse caaersServiceResponse = new CaaersServiceResponse();
        final ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(Status.PROCESSED);
        serviceResponse.setResponsecode("0");
        caaersServiceResponse.setServiceResponse(serviceResponse);
        response.setCaaersServiceResponse(caaersServiceResponse);
        try {
            EasyMock.expect(client.getParticipant((GetParticipant) org.easymock.EasyMock.anyObject())).andReturn(
                    response);
            EasyMock.replay(client);

            caAERSParticipantServiceClient.getParticipant(participantXMLStr);
        } catch (Exception e) {
            LOG.error("Exception occured : " + e);
        }
    }

    /**
     * Testcase for updateParticipant
     * 
     * @throws JAXBException - JAXBException
     * @throws MalformedURLException - MalformedURLException
     */
    @Test
    public void updateParticipant() throws MalformedURLException, JAXBException {
        final String participantXMLStr = getPStr();
        final UpdateParticipantResponse response = new UpdateParticipantResponse();
        final CaaersServiceResponse caaersServiceResponse = new CaaersServiceResponse();
        final ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(Status.PROCESSED);
        serviceResponse.setResponsecode("0");
        caaersServiceResponse.setServiceResponse(serviceResponse);
        response.setCaaersServiceResponse(caaersServiceResponse);

        final GetParticipantResponse gpr = new GetParticipantResponse();
        final CaaersServiceResponse csr = new CaaersServiceResponse();
        final ServiceResponse sr = new ServiceResponse();
        sr.setStatus(Status.PROCESSED);
        sr.setResponsecode("0");
        caaersServiceResponse.setServiceResponse(sr);
        gpr.setCaaersServiceResponse(csr);

        try {
            EasyMock.expect(client.updateParticipant((UpdateParticipant) org.easymock.EasyMock.anyObject())).andReturn(
                    response);
            EasyMock.expect(client.getParticipant((GetParticipant) org.easymock.EasyMock.anyObject())).andReturn(gpr);
            EasyMock.replay(client);

            caAERSParticipantServiceClient.updateParticipant(participantXMLStr);
        } catch (Exception e) {
            LOG.error("Exception occured : " + e);
        }
    }

    /**
     * Testcase for updateParticipant for Off Study
     * 
     * @throws JAXBException - JAXBException
     * @throws MalformedURLException - MalformedURLException
     */
    @Test
    public void updateParticipantOffStudy() throws MalformedURLException, JAXBException {
        final String participantXMLStr = getParticipantOffStudyString();
        try {
            caAERSParticipantServiceClient.updateParticipant(participantXMLStr);
        } catch (Exception e) {
            LOG.error("Exception occured : " + e);
        }
    }

    /**
     * Testcase for deleteParticipant
     * 
     * @throws JAXBException - JAXBException
     * @throws MalformedURLException - MalformedURLException
     */
    // @Test
    public void deleteParticipant() throws MalformedURLException, JAXBException {
        final String participantXMLStr = getPStr();
        final DeleteParticipantResponse response = new DeleteParticipantResponse();
        final CaaersServiceResponse caaersServiceResponse = new CaaersServiceResponse();
        final ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus(Status.PROCESSED);
        serviceResponse.setResponsecode("0");
        caaersServiceResponse.setServiceResponse(serviceResponse);
        response.setCaaersServiceResponse(caaersServiceResponse);
        try {
            EasyMock.expect(client.deleteParticipant((DeleteParticipant) org.easymock.EasyMock.anyObject())).andReturn(
                    response);
            EasyMock.replay(client);
            caAERSParticipantServiceClient.deleteParticipant(participantXMLStr);
        } catch (Exception e) {
            LOG.error("Exception occured : " + e);
        }
    }

    private String getPStr() {
        return getXMLString("Participant_caaers.xml");
    }

    private String getParticipantOffStudyString() {
        return getXMLString("Participant_OffStudy_caaers.xml");
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = CaAERSAdverseEventServiceClientIntegrationTest.class.getClassLoader()
                .getResourceAsStream("payloads/participant/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            LOG.error("Error while reading contents of file : " + fileName + ". " + e);
        }
        return contents;
    }
}
