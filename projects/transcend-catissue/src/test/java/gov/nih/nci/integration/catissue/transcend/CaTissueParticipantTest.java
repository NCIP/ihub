package gov.nih.nci.integration.catissue.transcend;

import static org.junit.Assert.*;

import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.CollectionProtocolRegistration;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.factory.CollectionProtocolFactory;
import edu.wustl.catissuecore.factory.CollectionProtocolRegistrationFactory;
import edu.wustl.catissuecore.factory.ParticipantFactory;
import gov.nih.nci.integration.catissue.CaTissueAPIClientWithRegularAuthentication;
import gov.nih.nci.integration.catissue.CaTissueParticipantClient;
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
public class CaTissueParticipantTest {

	private final SimpleDateFormat dateFormat = new SimpleDateFormat(
			"mm/DD/yyyy");

	private final CaTissueParticipantClient caTissueParticipantClient;

	public CaTissueParticipantTest() {
		String loginName = PropertiesLoader.getAdminUsername();
		String password = PropertiesLoader.getAdminPassword();
		CaTissueAPIClientWithRegularAuthentication caTissueAPIClient = new CaTissueAPIClientWithRegularAuthentication(
				loginName, password);

		caTissueParticipantClient = new CaTissueParticipantClient(
				caTissueAPIClient);
	}
	
	/**
	 * Tests creating and registering participant with collection protocol
	 * 
	 * @throws Exception
	 *             exception thrown if any
	 */
	@Test
	public void createParticipantAsPC() throws Exception {
		try {
			final String cpTitle = PropertiesLoader.getPCScientistCPTitle();

			final CollectionProtocolFactory cpFact = CollectionProtocolFactory
					.getInstance();
			final CollectionProtocol cp = cpFact.createObject();
			cp.setTitle(cpTitle);

			final Participant participant = getParticipant();
			participant.getCollectionProtocolRegistrationCollection().add(
					initCollectionProtocolRegistration(participant, cp));

			final Participant result = caTissueParticipantClient.registerParticipant(participant);

			assertNotNull(result);
			assertNotNull(result.getObjectId());

			final List<Participant> partcpnts = caTissueParticipantClient.getParticipantsForCollectionProtocol(cpTitle);
			assertNotNull(partcpnts);
			assertEquals(false, partcpnts.isEmpty());
			
			caTissueParticipantClient .deleteParticipant(participant);
			 Participant result2 = 
				 caTissueParticipantClient.getParticipantForSSN(participant.getSocialSecurityNumber());
			assertNull(result2);

		} catch (ApplicationException e) {
			e.printStackTrace();
			assertFalse("Not able to retrieve Participants", true);
		}
	}

	@Test
	public void parseParticipant() throws JAXBException, ParseException {
		Participant participant = getParticipant();
		XStream xStream = caTissueParticipantClient.getxStream();
		String participantXML = xStream.toXML(participant);

		Participant parsedParticipant = (Participant) xStream
				.fromXML(new StringReader(participantXML));

		assertNotNull(parsedParticipant);
		assertNotSame(participant, parsedParticipant);
		assertEquals(participant.getSocialSecurityNumber(), parsedParticipant
				.getSocialSecurityNumber());

		String parsedParticipantXML = xStream.toXML(participant);
		assertEquals(participantXML, parsedParticipantXML);
	}

	@Test
	public void submitRegistrationFromXMLPayload() throws ParseException, ApplicationException {
		Participant participant = getParticipant();		
		
		final String cpTitle = PropertiesLoader.getPCScientistCPTitle();

		final CollectionProtocolFactory cpFact = CollectionProtocolFactory
				.getInstance();
		final CollectionProtocol cp = cpFact.createObject();
		cp.setTitle(cpTitle);
		
		participant.getCollectionProtocolRegistrationCollection().add(
				initCollectionProtocolRegistration(participant, cp));
		
		String participantXML = caTissueParticipantClient.getxStream().toXML(participant);
		System.out.println(participantXML);
		Participant registeredParticipant = caTissueParticipantClient.registerParticipant(participantXML);		
		
		assertNotNull(registeredParticipant);
		assertNotNull(registeredParticipant.getObjectId());
		
		assertNotSame(participant, registeredParticipant);
		assertEquals(participant.getSocialSecurityNumber(), registeredParticipant
				.getSocialSecurityNumber());

		caTissueParticipantClient.deleteParticipant(participant);
		final Participant result2 = caTissueParticipantClient.getParticipantForSSN(participant.getSocialSecurityNumber());
		assertNull(result2);
	}

	private Participant getParticipant() throws ParseException {
		final ParticipantFactory prtcpntFact = ParticipantFactory.getInstance();
		final Participant participant = prtcpntFact.createObject();

		participant.setActivityStatus("Active");
		participant.setBirthDate(dateFormat.parse("06/21/1983"));
		participant.setEthnicity("Unknown");
		participant.setGender("Unspecified");
		participant.setFirstName("JOHN5");
		participant.setLastName("DOE5");
		participant.setVitalStatus("Alive");
		participant.setSocialSecurityNumber("123-45-6803");

		return participant;
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

}
