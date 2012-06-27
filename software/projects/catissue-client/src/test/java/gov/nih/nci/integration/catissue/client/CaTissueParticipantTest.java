package gov.nih.nci.integration.catissue.client;

import static org.junit.Assert.assertNotNull;

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
        collectionProtocol.setTitle("6482");
        collectionProtocol.setShortTitle("6482");
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
     * Mock Testcase for Update Participant Registration
     * 
     * @throws ParseException - ParseException
     */
    @SuppressWarnings("unchecked")
    @Test
    public void updateParticipantRegistration() throws ParseException {
        String retXMLString = "";
        final CollectionProtocol collectionProtocol = new CollectionProtocol();
        collectionProtocol.setTitle("6482");
        collectionProtocol.setShortTitle("6482");

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

    // CHECKSTYLE:OFF

    private String getParticipantXMLStr() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <catissue:participant xmlns:p=\"http://integration.nci.nih.gov/participant\" xmlns:catissue=\"http://domain.catissuecore.wustl.edu/participant\" xmlns:g=\"http://catissue/gender/data\"> <catissue:activityStatus>Active</catissue:activityStatus> <catissue:birthDate>1965-11-24</catissue:birthDate> <catissue:ethnicity>Not Hispanic or Latino</catissue:ethnicity> <catissue:firstName>Cherry061501</catissue:firstName> <catissue:gender>Male Gender</catissue:gender> <catissue:lastName>488061501</catissue:lastName> <catissue:socialSecurityNumber>123-06-1501</catissue:socialSecurityNumber> <catissue:vitalStatus>Alive</catissue:vitalStatus> <catissue:collectionProtocolRegistrationCollection class=\"set\"> <catissue:collectionProtocolRegistration> <catissue:activityStatus>Active</catissue:activityStatus> <catissue:consentSignatureDate>2012-06-27</catissue:consentSignatureDate> <catissue:protocolParticipantIdentifier>488061501</catissue:protocolParticipantIdentifier> <catissue:registrationDate>2012-03-07</catissue:registrationDate> <catissue:specimenCollectionGroupCollection class=\"set\"/> <catissue:collectionProtocol> <catissue:shortTitle>6482</catissue:shortTitle> <catissue:collectionProtocolEventCollection class=\"linked-hash-set\"/> <catissue:childCollectionProtocolCollection class=\"linked-hash-set\"/> <catissue:studyFormContextCollection class=\"set\"/> <catissue:collectionProtocolRegistrationCollection class=\"set\"/> <catissue:siteCollection class=\"set\"/> <catissue:clinicalDiagnosisCollection class=\"linked-hash-set\"/> <catissue:distributionProtocolCollection class=\"linked-hash-set\"/> <catissue:coordinatorCollection class=\"linked-hash-set\"/> <catissue:assignedProtocolUserCollection class=\"set\"/> <catissue:gridGrouperPrivileges/> </catissue:collectionProtocol> <catissue:participant reference=\"../../..\"/> <catissue:isToInsertAnticipatorySCGs>true</catissue:isToInsertAnticipatorySCGs> </catissue:collectionProtocolRegistration> </catissue:collectionProtocolRegistrationCollection> <catissue:raceCollection class=\"set\"> <catissue:race> <catissue:raceName>White</catissue:raceName> <catissue:participant reference=\"../../..\"/> </catissue:race> </catissue:raceCollection> <catissue:participantMedicalIdentifierCollection class=\"set\"/> <catissue:participantRecordEntryCollection class=\"set\"/> </catissue:participant>";
    }

    // CHECKSTYLE:ON

}
