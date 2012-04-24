package gov.nih.nci.integration.catissue.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.CollectionProtocolRegistration;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.domain.ParticipantMedicalIdentifier;
import edu.wustl.catissuecore.domain.Site;
import edu.wustl.catissuecore.factory.CollectionProtocolFactory;
import edu.wustl.catissuecore.factory.CollectionProtocolRegistrationFactory;
import edu.wustl.catissuecore.factory.ParticipantFactory;
import edu.wustl.catissuecore.factory.ParticipantMedicalIdentifierFactory;
import edu.wustl.catissuecore.factory.SiteFactory;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public void createParticipantAsPC() throws Exception {
		final String cpTitle = "CP-01";

		final CollectionProtocolFactory cpFact = CollectionProtocolFactory
				.getInstance();
		final CollectionProtocol cp = cpFact.createObject();
		cp.setTitle(cpTitle);

		final Participant participant = getParticipant();
		participant.getCollectionProtocolRegistrationCollection().add(
				initCollectionProtocolRegistration(participant, cp));

		final Participant result = caTissueParticipantClient
				.registerParticipant(participant);

		assertNotNull(result);
		assertNotNull(result.getObjectId());

		final List<Participant> partcpnts = caTissueParticipantClient
				.getParticipantsForCollectionProtocol(cpTitle);
		assertNotNull(partcpnts);
		assertEquals(false, partcpnts.isEmpty());

		caTissueParticipantClient.deleteParticipant(participant);
		Participant result2 = caTissueParticipantClient
				.getParticipantForSSN(participant.getSocialSecurityNumber());
		assertNull(result2);
	}

	@Test
	public void parseParticipant() throws JAXBException, ParseException {
		Participant participant = getParticipant();
		CollectionProtocol cp = getCollectionProtocol();
		CollectionProtocolRegistration cpr = initCollectionProtocolRegistration(
				participant, cp);

		participant.getCollectionProtocolRegistrationCollection().clear();
		participant.getCollectionProtocolRegistrationCollection().add(cpr);

		XStream xStream = caTissueParticipantClient.getxStream();
		String participantXML = xStream.toXML(participant);
		System.out.println(participantXML);
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

	@Test
	public void submitRegistrationFromXMLPayload() throws ApplicationException {

		String registeredParticipantStr = caTissueParticipantClient
				.registerParticipant(getParticipantXMLStr());
		System.out.println(registeredParticipantStr);
		Participant registeredParticipant = caTissueParticipantClient.getParticipantForMRN("995683");

		assertNotNull(registeredParticipant);
		assertNotNull(registeredParticipant.getObjectId());

		assertEquals("123-45-6814", registeredParticipant
				.getSocialSecurityNumber());

		caTissueParticipantClient.deleteParticipant(getParticipantXMLStr());
		final Participant result2 = caTissueParticipantClient
				.getParticipantForSSN("123-45-6814");
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
		//participant.setLastName("DOE5");
		//MRN or Medical Identifier is being set as lastName for identification
		participant.setLastName("995679");
		participant.setVitalStatus("Alive");
		participant.setSocialSecurityNumber("123-45-6824");

		Site site = SiteFactory.getInstance().createObject();
		site.setName("In Transit");

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
		collectionProtocolRegistration.setProtocolParticipantIdentifier("");
		collectionProtocolRegistration.setActivityStatus("Active");
		collectionProtocolRegistration.setRegistrationDate(new Date());
		collectionProtocolRegistration.setConsentSignatureDate(new Date());
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
