package gov.nih.nci.integration.catissue.client;

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
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;

/**
 * 
 * @author chandrasekaravr
 * 
 */
public class CaTissueParticipantIntegrationTest {

	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	private final CaTissueParticipantClient caTissueParticipantClient;

	public CaTissueParticipantIntegrationTest() throws Exception {
		caTissueParticipantClient = new CaTissueParticipantClient(
				"admin@admin.com", "Aa_111111");
	}

	/**
	 * Tests creating and registering participant with collection protocol
	 * 
	 * @throws Exception
	 *             exception thrown if any
	 */
	@Test
	public void handleParticipantRegistration() throws Exception {
		// create sample participant registration
		final String cpTitle = "CP-01";

		final CollectionProtocolFactory cpFact = CollectionProtocolFactory
				.getInstance();
		final CollectionProtocol cp = cpFact.createObject();
		cp.setTitle(cpTitle);

		final Participant participant = getParticipant();
		participant.getCollectionProtocolRegistrationCollection().add(
				initCollectionProtocolRegistration(participant, cp));

		String participantXML = caTissueParticipantClient.getxStream().toXML(
				participant);
//		System.out.println(participantXML);

		// register participant
		caTissueParticipantClient.registerParticipant(participant);

		// retrieve participant by cp
		final List<Participant> partcpnts = caTissueParticipantClient
				.getParticipantsForCollectionProtocol(cpTitle);
		assertNotNull(partcpnts);
		assertEquals(false, partcpnts.isEmpty());

		// retrieve participant by MRN
		Participant existParticipant = caTissueParticipantClient
				.getParticipantForPatientId(participant.getLastName());
		assertNotNull(existParticipant);

		// update participant
		existParticipant.setFirstName(existParticipant.getFirstName()
				+ "-updated");
		existParticipant = caTissueParticipantClient
				.updateParticipantRegistration(existParticipant);
		assertNotNull(existParticipant);
		assertTrue(!existParticipant.getFirstName().endsWith("updated"));

		existParticipant.setFirstName(existParticipant.getFirstName()
				+ "-updated2");
		existParticipant = caTissueParticipantClient
				.updateParticipantRegistration(existParticipant);
		assertNotNull(existParticipant);
		assertTrue(!existParticipant.getFirstName().endsWith("updated2"));

		// check serializing orig participant registration before update
		String existingPrtcpntStr = caTissueParticipantClient.getxStream()
				.toXML(existParticipant);
		assertNotNull(existingPrtcpntStr);
//		System.out.println("existingPrtcpntStr >>> " + existingPrtcpntStr);

		// update with original incoming msg - equivalent to update msg
		ArrayList<CollectionProtocolRegistration> cprList = new ArrayList<CollectionProtocolRegistration>(
				participant.getCollectionProtocolRegistrationCollection());
		cprList.get(0).getCollectionProtocol().setTitle("6482");
		existParticipant = caTissueParticipantClient
				.updateParticipantRegistration(participant);
		assertNotNull(existParticipant);
		assertTrue(!existParticipant.getFirstName().endsWith("updated"));

		// simulate rollback update, by deleting the update participant
		// registration
		caTissueParticipantClient.deleteParticipant(participant);

		// assert deletion
		Participant afterDel = caTissueParticipantClient
				.getParticipantForPatientId(participant.getLastName());

		assertNull(afterDel);

		// re-register with the original participant retrieved before update
		caTissueParticipantClient.registerParticipant(existParticipant);

		// assert presence by retreival
		Participant afterReinsert = caTissueParticipantClient
				.getParticipantForPatientId(participant.getLastName());

		assertNotNull(afterReinsert);

		// cleanup by deletion
		caTissueParticipantClient.deleteParticipant(participant);
	}

	// @Test
	public void parseParticipant() throws JAXBException, ParseException {
		Participant participant = getParticipant();
		CollectionProtocol cp = getCollectionProtocol();
		CollectionProtocolRegistration cpr = initCollectionProtocolRegistration(
				participant, cp);

		participant.getCollectionProtocolRegistrationCollection().clear();
		participant.getCollectionProtocolRegistrationCollection().add(cpr);

		XStream xStream = caTissueParticipantClient.getxStream();
		String participantXML = xStream.toXML(participant);
//		System.out.println(participantXML);
		Participant parsedParticipant = (Participant) xStream
				.fromXML(new StringReader(participantXML));

		assertNotNull(parsedParticipant);
		assertNotSame(participant, parsedParticipant);
		assertEquals(participant.getSocialSecurityNumber(), parsedParticipant
				.getSocialSecurityNumber());

		String parsedParticipantXML = xStream.toXML(participant);
		assertEquals(participantXML, parsedParticipantXML);

		parsedParticipant = (Participant) xStream.fromXML(new StringReader(
				getParticipantXMLStr()));

		assertNotNull(parsedParticipant);
	}

	// @Test
	public void submitRegistrationFromXMLPayload() throws ApplicationException {

		String registeredParticipantStr = caTissueParticipantClient
				.registerParticipant(getParticipantXMLStr());
//		System.out.println(registeredParticipantStr);
		Participant registeredParticipant = caTissueParticipantClient
				.getParticipantForPatientId("995683");

		assertNotNull(registeredParticipant);
		assertNotNull(registeredParticipant.getObjectId());

		assertEquals("123-45-6814", registeredParticipant
				.getSocialSecurityNumber());

		caTissueParticipantClient.deleteParticipant(getParticipantXMLStr());
		final Participant result2 = caTissueParticipantClient
				.getParticipantForPatientId("995683");
		assertNull(result2);
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
		participant.setLastName("9050608");
		participant.setVitalStatus("Alive");
		participant.setSocialSecurityNumber("123-05-0608");

		Race race = RaceFactory.getInstance().createObject();
		race.setParticipant(participant);
		race.setRaceName("White");
		participant.getRaceCollection().add(race);

		/*
		 * Site site = SiteFactory.getInstance().createObject();
		 * site.setName("DCP");
		 * 
		 * ParticipantMedicalIdentifier pmi =
		 * ParticipantMedicalIdentifierFactory .getInstance().createObject();
		 * pmi.setParticipant(participant);
		 * pmi.setMedicalRecordNumber("9050608"); pmi.setSite(site);
		 * 
		 * participant.getParticipantMedicalIdentifierCollection().add(pmi);
		 */

		return participant;
	}

	private CollectionProtocol getCollectionProtocol() throws ParseException {
		final CollectionProtocolFactory cpFact = CollectionProtocolFactory
				.getInstance();
		final CollectionProtocol cp = cpFact.createObject();

		cp.setTitle("CP-01");
		return cp;
	}

	private CollectionProtocolRegistration initCollectionProtocolRegistration(
			Participant participant, CollectionProtocol collectionProtocol) {
		final CollectionProtocolRegistrationFactory cprFact = CollectionProtocolRegistrationFactory
				.getInstance();
		final CollectionProtocolRegistration collectionProtocolRegistration = cprFact
				.createObject();
		collectionProtocolRegistration
				.setCollectionProtocol(collectionProtocol);

		collectionProtocolRegistration.setParticipant(participant);
		collectionProtocolRegistration.setActivityStatus("Active");
		collectionProtocolRegistration.setRegistrationDate(new Date());
		collectionProtocolRegistration.setConsentSignatureDate(new Date());
		collectionProtocolRegistration
				.setProtocolParticipantIdentifier("123050608");
		return collectionProtocolRegistration;
	}

	private String getParticipantXMLStr() {
		return "<?xml version=\"1.0\" ?><participant><activityStatus>Active</activityStatus>"
				+ "<birthDate>1941-05-02 00:00:00.0 EDT</birthDate><ethnicity>Unknown</ethnicity>"
				+ "<firstName>JOHN5</firstName><gender>Unspecified</gender><lastName>995683</lastName>"
				+ "<socialSecurityNumber>123-45-6814</socialSecurityNumber><vitalStatus>Alive</vitalStatus>"
				+ "<collectionProtocolRegistrationCollection class=\"set\"><collectionProtocolRegistration>"
				+ "<activityStatus>Active</activityStatus>"
				+ "<consentSignatureDate>2012-03-30 14:36:24.822 EDT</consentSignatureDate>"
				+ "<protocolParticipantIdentifier></protocolParticipantIdentifier>"
				+ "<registrationDate>2012-03-30 14:36:24.822 EDT</registrationDate>"
				+ "<specimenCollectionGroupCollection class=\"set\"></specimenCollectionGroupCollection>"
				+ "<collectionProtocol><title>CP-01</title><collectionProtocolEventCollection class=\"linked-hash-set\"/>"
				+ "<childCollectionProtocolCollection class=\"linked-hash-set\"/>"
				+ "<studyFormContextCollection class=\"set\"></studyFormContextCollection>"
				+ "<collectionProtocolRegistrationCollection class=\"set\"/>"
				+ "<siteCollection class=\"set\"></siteCollection>"
				+ "<clinicalDiagnosisCollection class=\"linked-hash-set\"/>"
				+ "<distributionProtocolCollection class=\"linked-hash-set\"/>"
				+ "<coordinatorCollection class=\"linked-hash-set\"/>"
				+ "<assignedProtocolUserCollection class=\"set\"></assignedProtocolUserCollection>"
				+ "<gridGrouperPrivileges></gridGrouperPrivileges></collectionProtocol>"
				+ "<participant reference=\"../../..\"></participant>"
				+ "<isToInsertAnticipatorySCGs>true</isToInsertAnticipatorySCGs></collectionProtocolRegistration>"
				+ "</collectionProtocolRegistrationCollection><raceCollection class=\"set\"></raceCollection>"
				+ "<participantMedicalIdentifierCollection class=\"linked-hash-set\"/>"
				+ "<participantRecordEntryCollection class=\"set\"/></participant>";
	}

}
