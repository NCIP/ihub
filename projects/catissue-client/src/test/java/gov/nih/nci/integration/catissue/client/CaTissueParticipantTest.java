package gov.nih.nci.integration.catissue.client;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.easymock.classextension.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import edu.wustl.catissuecore.cacore.CaTissueWritableAppService;
import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.CollectionProtocolRegistration;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.domain.Race;
import edu.wustl.catissuecore.factory.CollectionProtocolFactory;
import edu.wustl.catissuecore.factory.CollectionProtocolRegistrationFactory;
import edu.wustl.catissuecore.factory.ParticipantFactory;
import edu.wustl.catissuecore.factory.RaceFactory;
import gov.nih.nci.system.applicationservice.ApplicationException;
import gov.nih.nci.system.query.cql.CQLQuery;

/**
 * This is the TestClass for Participant Registration flow.
 * 
 * @author Rohit Gupta
 */

public class CaTissueParticipantTest {

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueParticipantTest.class);

    private CaTissueParticipantClient caTissueParticipantClient = null;
    private CaTissueAPIClientWithRegularAuthentication caTissueAPIClient = null;
    private CaTissueWritableAppService writableAppService = null;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);

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
        caTissueParticipantClient = new CaTissueParticipantClient("", "");
        caTissueParticipantClient.setCaTissueAPIClient(caTissueAPIClient);
    }

    /**
     * Mock Testcase for Register Participant
     */
    @SuppressWarnings("unchecked")
    @Test
    public void registerParticipant() {
        String retXMLString = "";
        final CollectionProtocol collectionProtocol = new CollectionProtocol();
        collectionProtocol.setTitle("6482:6482");
        collectionProtocol.setShortTitle("6482:6482");
        final Participant participant = new Participant();

        try {
            EasyMock.expect(caTissueAPIClient.getApplicationService()).andReturn(writableAppService);
            EasyMock.expect(
                    caTissueAPIClient.searchById((Class<CollectionProtocol>) EasyMock.anyObject(),
                            (CollectionProtocol) org.easymock.EasyMock.anyObject())).andReturn(collectionProtocol);
            EasyMock.expect(caTissueAPIClient.insert((Participant) org.easymock.EasyMock.anyObject())).andReturn(
                    participant);
            EasyMock.replay(caTissueAPIClient);

            caTissueParticipantClient.registerParticipantFromXML(getParticipantXMLStr());

            retXMLString = "REGISTER_PARTICIPANT";
        } catch (ApplicationException e) {
            LOG.error("CaTissueParticipantTest-ApplicationException inside registerParticipant() ", e);
            retXMLString = "REGISTER_PARTICIPANT_FAILED";
        }
        assertNotNull(retXMLString);
    }

    /**
     * Mock Testcase for Register Participant when SSN is blank is the incoming request
     */
    @SuppressWarnings("unchecked")
    @Test
    public void registerParticipantForLastNameNULL() {
        String retXMLString = "";
        final CollectionProtocol collectionProtocol = new CollectionProtocol();
        collectionProtocol.setTitle("6482:6482");
        collectionProtocol.setShortTitle("6482:6482");
        final Participant participant = new Participant();

        try {
            EasyMock.expect(caTissueAPIClient.getApplicationService()).andReturn(writableAppService);
            EasyMock.expect(
                    caTissueAPIClient.searchById((Class<CollectionProtocol>) EasyMock.anyObject(),
                            (CollectionProtocol) org.easymock.EasyMock.anyObject())).andReturn(collectionProtocol);
            EasyMock.expect(caTissueAPIClient.insert((Participant) org.easymock.EasyMock.anyObject())).andReturn(
                    participant);
            EasyMock.replay(caTissueAPIClient);

            caTissueParticipantClient.registerParticipantFromXML(getParticipantXMLStrForLastNameNULL());

            retXMLString = "REGISTER_PARTICIPANT";
        } catch (ApplicationException e) {
            LOG.error("CaTissueParticipantTest-ApplicationException inside registerParticipantForBlankSSN() ", e);
            retXMLString = "REGISTER_PARTICIPANT_FAILED";
        }
        assertNotNull(retXMLString);
    }

    /**
     * Mock Testcase for Update Participant Registration
     * 
     * @throws ParseException - ParseException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void updateParticipantRegistration() throws ParseException {
        String retXMLString = "";
        final CollectionProtocol collectionProtocol = new CollectionProtocol();
        collectionProtocol.setTitle("6482:6482");
        collectionProtocol.setShortTitle("6482:6482");

        final Participant participant = getParticipant();

        final String participantXML = caTissueParticipantClient.getxStream().toXML(participant);

        final List<Object> participantList = new ArrayList<Object>();
        participantList.add(participant);

        try {
            EasyMock.expect(caTissueAPIClient.getApplicationService()).andReturn(writableAppService);
            EasyMock.expect(
                    caTissueAPIClient.searchById((Class<CollectionProtocol>) EasyMock.anyObject(),
                            (CollectionProtocol) org.easymock.EasyMock.anyObject())).andReturn(collectionProtocol);
            EasyMock.expect(caTissueAPIClient.update((Participant) org.easymock.EasyMock.anyObject())).andReturn(
                    participant);
            EasyMock.expect(writableAppService.query((CQLQuery) org.easymock.EasyMock.anyObject())).andReturn(
                    participantList);

            EasyMock.replay(caTissueAPIClient);
            EasyMock.replay(writableAppService);

            caTissueParticipantClient.updateParticipantRegistrationFromXML(participantXML);

            retXMLString = "UPDATE_PARTICIPANT_REGISTRATION";
        } catch (ApplicationException e) {
            LOG.error("CaTissueParticipantTest-ApplicationException inside registerParticipant() ", e);
            retXMLString = "UPDATE_PARTICIPANT_REGISTRATION_FAILED";
        }
        assertNotNull(retXMLString);
    }

    /**
     * Mock Testcase for Update Participant Registration when PArticipant is not found in the caTissue
     * 
     * @throws ParseException - ParseException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void updateParticipantRegistrationParticipantNotFound() throws ParseException {
        String retXMLString = "";
        final CollectionProtocol collectionProtocol = new CollectionProtocol();
        collectionProtocol.setTitle("6482:6482");
        collectionProtocol.setShortTitle("6482:6482");

        final Participant participant = getParticipant();

        final String participantXML = caTissueParticipantClient.getxStream().toXML(participant);

        final List<Object> participantList = null; // to replicate the scenario of participant not found

        try {
            EasyMock.expect(caTissueAPIClient.getApplicationService()).andReturn(writableAppService);
            EasyMock.expect(
                    caTissueAPIClient.searchById((Class<CollectionProtocol>) EasyMock.anyObject(),
                            (CollectionProtocol) org.easymock.EasyMock.anyObject())).andReturn(collectionProtocol);
            EasyMock.expect(caTissueAPIClient.update((Participant) org.easymock.EasyMock.anyObject())).andReturn(
                    participant);
            EasyMock.expect(writableAppService.query((CQLQuery) org.easymock.EasyMock.anyObject())).andReturn(
                    participantList);

            EasyMock.replay(caTissueAPIClient);
            EasyMock.replay(writableAppService);

            caTissueParticipantClient.updateParticipantRegistrationFromXML(participantXML);

            retXMLString = "UPDATE_PARTICIPANT_REGISTRATION";
        } catch (ApplicationException e) {
            LOG.error("CaTissueParticipantTest-ApplicationException inside registerParticipant() ", e);
            retXMLString = "UPDATE_PARTICIPANT_REGISTRATION_FAILED";
        }
        assertNotNull(retXMLString);
    }

    /**
     * Mock Testcase for Update Participant Registration when SSN is blank is incoming request
     * 
     * @throws ParseException - ParseException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void updateParticipantRegistrationForBlankSSN() throws ParseException {
        String retXMLString = "";
        final CollectionProtocol collectionProtocol = new CollectionProtocol();
        collectionProtocol.setTitle("6482:6482");
        collectionProtocol.setShortTitle("6482:6482");

        final Participant participant = getParticipant();
        participant.setSocialSecurityNumber("");

        final String participantXML = caTissueParticipantClient.getxStream().toXML(participant);

        final List<Object> participantList = new ArrayList<Object>();
        participantList.add(participant);

        try {
            EasyMock.expect(caTissueAPIClient.getApplicationService()).andReturn(writableAppService);
            EasyMock.expect(
                    caTissueAPIClient.searchById((Class<CollectionProtocol>) EasyMock.anyObject(),
                            (CollectionProtocol) org.easymock.EasyMock.anyObject())).andReturn(collectionProtocol);
            EasyMock.expect(caTissueAPIClient.update((Participant) org.easymock.EasyMock.anyObject())).andReturn(
                    participant);
            EasyMock.expect(writableAppService.query((CQLQuery) org.easymock.EasyMock.anyObject())).andReturn(
                    participantList);

            EasyMock.replay(caTissueAPIClient);
            EasyMock.replay(writableAppService);

            caTissueParticipantClient.updateParticipantRegistrationFromXML(participantXML);

            retXMLString = "UPDATE_PARTICIPANT_REGISTRATION";
        } catch (ApplicationException e) {
            LOG.error("CaTissueParticipantTest-ApplicationException inside registerParticipant() ", e);
            retXMLString = "UPDATE_PARTICIPANT_REGISTRATION_FAILED";
        }
        assertNotNull(retXMLString);
    }

    /**
     * Mock Testcase for deleteParticipant
     * 
     * @throws ParseException - ParseException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void deleteParticipant() throws ParseException {
        String retXMLString = "";
        final Participant participant = getParticipant();
        final String participantXML = caTissueParticipantClient.getxStream().toXML(participant);
        final List<Object> participantList = new ArrayList<Object>();
        participantList.add(participant);

        final CollectionProtocolRegistration cpr = new CollectionProtocolRegistration();

        try {
            EasyMock.expect(caTissueAPIClient.getApplicationService()).andReturn(writableAppService);
            EasyMock.expect(
                    caTissueAPIClient.update((CollectionProtocolRegistration) org.easymock.EasyMock.anyObject()))
                    .andReturn(cpr);
            EasyMock.expect(caTissueAPIClient.update((Participant) org.easymock.EasyMock.anyObject())).andReturn(
                    participant);
            EasyMock.expect(writableAppService.query((CQLQuery) org.easymock.EasyMock.anyObject())).andReturn(
                    participantList);

            EasyMock.replay(caTissueAPIClient);
            EasyMock.replay(writableAppService);

            caTissueParticipantClient.deleteParticipantFromXML(participantXML);

            retXMLString = "DELETE_PARTICIPANT_REGISTRATION";
        } catch (ApplicationException e) {
            LOG.error("CaTissueParticipantTest-ApplicationException inside registerParticipant() ", e);
            retXMLString = "DELETE_PARTICIPANT_REGISTRATION_FAILED";
        }
        assertNotNull(retXMLString);
    }

    private Participant getParticipant() throws ParseException {
        final ParticipantFactory prtcpntFact = ParticipantFactory.getInstance();
        final Participant participant = prtcpntFact.createObject();

        participant.setActivityStatus("Active");
        participant.setBirthDate(dateFormat.parse("19410502"));
        participant.setEthnicity("Unknown");
        participant.setGender("Unspecified");
        participant.setFirstName("JOHN5");
        // participant.setLastName("DOE5");
        // MRN or Medical Identifier is being set as lastName for identification
        participant.setLastName("488060801");
        participant.setVitalStatus("Alive");
        participant.setSocialSecurityNumber("123-05-0608");

        final Race race = RaceFactory.getInstance().createObject();
        race.setParticipant(participant);
        race.setRaceName("White");
        participant.getRaceCollection().add(race);
        participant.getCollectionProtocolRegistrationCollection().add(initCollectionProtocolRegistration(participant));
        return participant;
    }

    private CollectionProtocolRegistration initCollectionProtocolRegistration(Participant participant) {

        final CollectionProtocolFactory cpFact = CollectionProtocolFactory.getInstance();
        final CollectionProtocol cp = cpFact.createObject();
        final String cpTitle = "CP-01";
        cp.setTitle(cpTitle);

        final CollectionProtocolRegistrationFactory cprFact = CollectionProtocolRegistrationFactory.getInstance();
        final CollectionProtocolRegistration collectionProtocolRegistration = cprFact.createObject();
        collectionProtocolRegistration.setCollectionProtocol(cp);

        collectionProtocolRegistration.setParticipant(participant);
        collectionProtocolRegistration.setActivityStatus("Active");
        collectionProtocolRegistration.setRegistrationDate(new Date());
        collectionProtocolRegistration.setConsentSignatureDate(new Date());
        collectionProtocolRegistration.setProtocolParticipantIdentifier("123050608");
        return collectionProtocolRegistration;
    }

    private String getParticipantXMLStr() {
        return getXMLString("Participant_Mock_catissue.xml");
    }

    private String getParticipantXMLStrForLastNameNULL() {
        return getXMLString("Participant_LastNameNull_Mock_catissue.xml");
    }

    private String getXMLString(String fileName) {
        final StringBuffer fileContents = new StringBuffer();
        final InputStream is = CaTissueConsentIntegrationTest.class.getClassLoader().getResourceAsStream(
                "payloads/participant/" + fileName);
        final BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String strLine;
        try {
            while ((strLine = br.readLine()) != null) {
                fileContents.append(strLine);
            }
            is.close();
        } catch (IOException e) {
            LOG.error("CaTissueParticipantTest-IOException inside getXMLString() ", e);
        }
        return fileContents.toString();
    }

}
