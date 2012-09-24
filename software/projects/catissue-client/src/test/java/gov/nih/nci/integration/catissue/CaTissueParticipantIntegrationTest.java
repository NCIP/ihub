package gov.nih.nci.integration.catissue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.CollectionProtocolRegistration;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.domain.Race;
import edu.wustl.catissuecore.factory.CollectionProtocolFactory;
import edu.wustl.catissuecore.factory.CollectionProtocolRegistrationFactory;
import edu.wustl.catissuecore.factory.ParticipantFactory;
import edu.wustl.catissuecore.factory.RaceFactory;
import gov.nih.nci.integration.catissue.client.CaTissueParticipantClient;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;

/**
 * 
 * @author chandrasekaravr
 * @author Rohit Gupta
 * 
 */
public class CaTissueParticipantIntegrationTest {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);
    private final CaTissueParticipantClient caTissueParticipantClient;
    private static final Logger LOG = LoggerFactory.getLogger(CaTissueParticipantIntegrationTest.class);

    /**
     * Constructor
     * 
     * @throws Exception - Exception
     */
    public CaTissueParticipantIntegrationTest() throws Exception {
        caTissueParticipantClient = new CaTissueParticipantClient("admin@admin.com", "caTissue20");
    }

    /**
     * Testcase for submitRegistrationFromXMLPayload
     * 
     * @throws ApplicationException - ApplicationException
     */
    @Test
    public void submitRegistrationFromXMLPayload() throws ApplicationException {

        caTissueParticipantClient.registerParticipant(getParticipantXMLStr());

        final Participant registeredParticipant = caTissueParticipantClient
                .getParticipantForPatientId("1.2.1.173000:echr:patient-1823462");

        assertNotNull(registeredParticipant);
        assertNotNull(registeredParticipant.getObjectId());

        caTissueParticipantClient.deleteParticipant(getParticipantXMLStr());
        final Participant result2 = caTissueParticipantClient
                .getParticipantForPatientId("1.2.1.173000:echr:patient-1823462");
        assertNull(result2);
    }

    /**
     * This testcase is for a scenario when trying to update the Study during updateRegistration.
     */
    @Test
    public void registerParticipantToDifferentStudy() {
        String xmlString = getParticipantXMLStr();
        xmlString = xmlString.replace("6482:6482", "7216:7216");
        try {
            caTissueParticipantClient.updateParticipantRegistrationFromXML(xmlString);
        } catch (ApplicationException e) {
            assertTrue(e.getMessage().contains("Study can't be changed while updating the Participant"));
        }
    }

    /**
     * This testcase is for a scenario when trying to register a participant against a Study which doesn't exist.
     */
    @Test
    public void registerParticipantStudyNotFound() {
        String xmlString = getParticipantXMLStr();
        xmlString = xmlString.replace("6482:6482", "6482_123:6482_123");
        xmlString = xmlString.replace("1823469", "1823469_123");
        try {
            caTissueParticipantClient.registerParticipantFromXML(xmlString);
        } catch (ApplicationException e) {
            final String expMessage = e.getCause().getCause().getMessage();
            assertTrue(expMessage
                    .contains("Either Title is not selected or Date format is not correct in Collection Protocol Registration"));
        }
    }

    /**
     * Tests creating and registering participant with collection protocol
     * 
     * @throws Exception exception thrown if any
     */
    @Test
    public void handleParticipantRegistration() throws Exception {
        // create sample participant registration
        final String cpTitle = "6482:6482";

        final CollectionProtocolFactory cpFact = CollectionProtocolFactory.getInstance();
        final CollectionProtocol cp = cpFact.createObject();
        cp.setTitle(cpTitle);
        cp.setShortTitle(cpTitle);

        final Participant participant = getParticipant();
        participant.getCollectionProtocolRegistrationCollection().add(
                initCollectionProtocolRegistration(participant, cp));

        final String participantXML = caTissueParticipantClient.getxStream().toXML(participant);

        // register participant
        caTissueParticipantClient.registerParticipant(participant);

        // retrieve participant by cp
        final List<Participant> partcpnts = caTissueParticipantClient.getParticipantsForCollectionProtocol(cpTitle);
        assertNotNull(partcpnts);
        // assertEquals(false, partcpnts.isEmpty());

        // retrieve participant by MRN
        Participant existParticipant = caTissueParticipantClient.getParticipantForPatientId(participant.getLastName());
        assertNotNull(existParticipant);

        // update participant
        existParticipant.setFirstName(existParticipant.getFirstName() + "-updated");
        existParticipant = caTissueParticipantClient.updateParticipantRegistration(existParticipant);
        assertNotNull(existParticipant);
        assertTrue(!existParticipant.getFirstName().endsWith("updated"));

        existParticipant.setFirstName(existParticipant.getFirstName() + "-updated2");
        existParticipant = caTissueParticipantClient.updateParticipantRegistration(existParticipant);
        assertNotNull(existParticipant);
        assertTrue(!existParticipant.getFirstName().endsWith("updated2"));

        // check serializing orig participant registration before update
        final String existingPrtcpntStr = caTissueParticipantClient.getxStream().toXML(existParticipant);
        assertNotNull(existingPrtcpntStr);

        // update with original incoming msg - equivalent to update msg
        final ArrayList<CollectionProtocolRegistration> cprList = new ArrayList<CollectionProtocolRegistration>(
                participant.getCollectionProtocolRegistrationCollection());
        cprList.get(0).getCollectionProtocol().setTitle("6482:6482");
        existParticipant = caTissueParticipantClient.updateParticipantRegistration(participant);
        assertNotNull(existParticipant);
        assertTrue(!existParticipant.getFirstName().endsWith("updated"));

        // simulate rollback update, by deleting the update participant registration
        caTissueParticipantClient.deleteParticipant(participant);
        // assert deletion
        final Participant afterDel = caTissueParticipantClient.getParticipantForPatientId(participant.getLastName());
        assertNull(afterDel);

        // re-register with the original participant retrieved before update
        caTissueParticipantClient.registerParticipant(existParticipant);

        // assert presence by retreival
        final Participant afterReinsert = caTissueParticipantClient.getParticipantForPatientId(participant
                .getLastName());

        assertNotNull(afterReinsert);

        // cleanup by deletion
        caTissueParticipantClient.deleteParticipant(participant);
    }

    /**
     * Testcase for parseParticipant
     * 
     * @throws JAXBException - JAXBException
     * @throws ParseException - ParseException
     */
    @Test
    public void parseParticipant() throws JAXBException, ParseException {
        final Participant participant = getParticipant();
        final CollectionProtocol cp = getCollectionProtocol();
        final CollectionProtocolRegistration cpr = initCollectionProtocolRegistration(participant, cp);

        participant.getCollectionProtocolRegistrationCollection().clear();
        participant.getCollectionProtocolRegistrationCollection().add(cpr);

        final XStream xStream = caTissueParticipantClient.getxStream();
        final String participantXML = xStream.toXML(participant);

        Participant parsedParticipant = (Participant) xStream.fromXML(new StringReader(participantXML));

        assertNotNull(parsedParticipant);
        assertNotSame(participant, parsedParticipant);
        // assertEquals(participant.getSocialSecurityNumber(), parsedParticipant.getSocialSecurityNumber());

        final String parsedParticipantXML = xStream.toXML(participant);
        assertEquals(participantXML, parsedParticipantXML);

        parsedParticipant = (Participant) xStream.fromXML(new StringReader(getParticipantXMLStr()));

        assertNotNull(parsedParticipant);
    }

    private Participant getParticipant() throws ParseException {
        final ParticipantFactory prtcpntFact = ParticipantFactory.getInstance();
        final Participant participant = prtcpntFact.createObject();

        participant.setActivityStatus("Active");
        participant.setBirthDate(dateFormat.parse("19410502"));
        participant.setEthnicity("Unknown");
        participant.setGender("Unspecified");
        participant.setFirstName("JOHN11");

        // MRN or Medical Identifier is being set as lastName for identification
        participant.setLastName("123092101");
        participant.setVitalStatus("Alive");
        // participant.setSocialSecurityNumber("123-05-0608");

        final Race race = RaceFactory.getInstance().createObject();
        race.setParticipant(participant);
        race.setRaceName("White");
        participant.getRaceCollection().add(race);

        return participant;
    }

    private CollectionProtocol getCollectionProtocol() throws ParseException {
        final CollectionProtocolFactory cpFact = CollectionProtocolFactory.getInstance();
        final CollectionProtocol cp = cpFact.createObject();

        cp.setTitle("6482:6482");
        return cp;
    }

    private CollectionProtocolRegistration initCollectionProtocolRegistration(Participant participant,
            CollectionProtocol collectionProtocol) {
        final CollectionProtocolRegistrationFactory cprFact = CollectionProtocolRegistrationFactory.getInstance();
        final CollectionProtocolRegistration collectionProtocolRegistration = cprFact.createObject();
        collectionProtocolRegistration.setCollectionProtocol(collectionProtocol);

        collectionProtocolRegistration.setParticipant(participant);
        collectionProtocolRegistration.setActivityStatus("Active");
        collectionProtocolRegistration.setRegistrationDate(new Date());
        collectionProtocolRegistration.setConsentSignatureDate(new Date());
        collectionProtocolRegistration.setProtocolParticipantIdentifier("123092101");
        return collectionProtocolRegistration;
    }

    private String getParticipantXMLStr() {
        return getXMLString("Participant_catissue.xml");
    }

    private String getXMLString(String fileName) {
        final StringBuffer fileContents = new StringBuffer();
        final InputStream is = CaTissueParticipantIntegrationTest.class.getClassLoader().getResourceAsStream(
                "payloads/participant/" + fileName);
        final BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String strLine;
        try {
            while ((strLine = br.readLine()) != null) {
                fileContents.append(strLine);
            }
            is.close();
        } catch (IOException e) {
            LOG.error("CaTissueParticipantIntegrationTest-IOException inside getXMLString() ", e);
        }
        return fileContents.toString();
    }

}
