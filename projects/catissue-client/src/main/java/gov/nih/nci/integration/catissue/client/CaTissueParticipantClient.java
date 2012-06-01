package gov.nih.nci.integration.catissue.client;

import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.CollectionProtocolRegistration;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.domain.ParticipantMedicalIdentifier;
import edu.wustl.catissuecore.domain.Race;
import edu.wustl.catissuecore.factory.CollectionProtocolFactory;
import edu.wustl.catissuecore.factory.CollectionProtocolRegistrationFactory;
import edu.wustl.catissuecore.factory.ParticipantFactory;
import edu.wustl.catissuecore.factory.RaceFactory;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class CaTissueParticipantClient {

	private static final Logger LOG = LoggerFactory
			.getLogger(CaTissueParticipantClient.class);

	private final CaTissueAPIClientWithRegularAuthentication caTissueAPIClient;

	private XStream xStream = new XStream(new StaxDriver());

	public CaTissueParticipantClient(String loginName, String password)
			throws Exception {
		super();

		Thread.currentThread().setContextClassLoader(
				CaTissueParticipantClient.class.getClassLoader());

		this.caTissueAPIClient = new CaTissueAPIClientWithRegularAuthentication(
				loginName, password);

		initXStream();
	}

	private void initXStream() {
		// CGLIB - related settings start
		/*
		 * xStream.addDefaultImplementation(
		 * org.hibernate.collection.PersistentList.class, java.util.List.class);
		 * xStream.addDefaultImplementation(
		 * org.hibernate.collection.PersistentMap.class, java.util.Map.class);
		 * xStream.addDefaultImplementation(
		 * org.hibernate.collection.PersistentSet.class, java.util.Set.class);
		 * 
		 * Mapper mapper = xStream.getMapper(); xStream.registerConverter(new
		 * HibernateCollectionConverter(mapper)); xStream.registerConverter(new
		 * HibernateMapConverter(mapper));
		 */
		// CGLIB - related settings end

		xStream.alias("participant", Participant.class);
		xStream.alias("race", Race.class);
		xStream.alias("collectionProtocol", CollectionProtocol.class);
		xStream.alias("collectionProtocolRegistration",
				CollectionProtocolRegistration.class);
		xStream.alias("participantMedicalIdentifier",
				ParticipantMedicalIdentifier.class);

		String[] accFrmts = new String[] { "", "yyyyMMdd",
				"yyyy-MM-dd",
				"MM/dd/yyyy", // catissue formats
				"yyyy-MM-dd HH:mm:ss.S a", "yyyy-MM-dd HH:mm:ssz",
				"yyyy-MM-dd HH:mm:ss z", // JDK 1.3 needs both versions
				"yyyy-MM-dd HH:mm:ssa" }; // backwards compatibility
		xStream.registerConverter(new DateConverter("yyyy-MM-dd HH:mm:ss.S z",
				accFrmts, true));

	}

	/**
	 * Returns the instance of CaTissueAPIClientWithRegularAuthentication being
	 * used
	 * 
	 * @return instance of CaTissueAPIClientWithRegularAuthentication
	 */
	public CaTissueAPIClientWithRegularAuthentication getCaTissueAPIClient() {
		return caTissueAPIClient;
	}

	/**
	 * Returns the instance of XStream being used
	 * 
	 * @return instance of XStream
	 */
	public XStream getxStream() {
		return xStream;
	}

	/**
	 * Parses Participant from xml
	 * 
	 * @param participantXMLStr
	 *            - Participant as XML String
	 * @return instance of Participant
	 */
	public Participant parseParticipant(String participantXMLStr) {
		return (Participant) xStream
				.fromXML(new StringReader(participantXMLStr));
	}

	public String registerParticipantFromXML(String participantXMLStr)
			throws Exception {
		return registerParticipant(participantXMLStr);
	}

	public String registerParticipant(String participantXMLStr)
			throws ApplicationException {
		Participant participant = parseParticipant(participantXMLStr);
		registerParticipant(participant);

		return null;
	}

	/**
	 * Registers Participant in CaTissue
	 * 
	 * @param participant
	 *            - Participant to be registered
	 * @return instance of Participant
	 */
	public Participant registerParticipant(Participant participant)
			throws ApplicationException {
		if (participant == null
				|| StringUtils.isEmpty(participant.getSocialSecurityNumber())) {
			throw new ApplicationException(
					"Participant does not contain the unique identifier, SSN!");
		}
		if (participant == null
				|| StringUtils.isEmpty(participant.getLastName())) {
			throw new ApplicationException(
					"Participant does not contain the unique medical identifier!");
		}
		caTissueAPIClient.insert(participant);

		return null;
	}

	public String updateParticipantRegistrationFromXML(String participantXMLStr)
			throws Exception {
		return updateParticipantRegistration(participantXMLStr);
	}

	public String updateParticipantRegistration(String participantXMLStr)
			throws ApplicationException {
		Participant participant = parseParticipant(participantXMLStr);
		participant = updateParticipantRegistration(participant);
		return xStream.toXML(participant);
	}

	/**
	 * updates Participant registration in CaTissue
	 * 
	 * @param participant
	 *            - Participant to be registered
	 * @return instance of Participant
	 */
	public Participant updateParticipantRegistration(Participant participant)
			throws ApplicationException {
		if (participant == null
				|| StringUtils.isEmpty(participant.getSocialSecurityNumber())) {
			throw new ApplicationException(
					"Participant does not contain the unique identifier, SSN!");
		}
		if (participant == null
				|| StringUtils.isEmpty(participant.getLastName())) {
			throw new ApplicationException(
					"Participant does not contain the unique medical identifier!");
		}

		Participant existingParticipant = getParticipantForPatientId(participant
				.getLastName());
		if (existingParticipant == null) {
			throw new ApplicationException(
					"CaTissue does not contain a participant with the unique identifier, "
							+ participant.getLastName());
		}

		participant.setId(existingParticipant.getId());
		ArrayList<CollectionProtocolRegistration> existingCprLst = new ArrayList<CollectionProtocolRegistration>(
				existingParticipant
						.getCollectionProtocolRegistrationCollection());
		Collection<CollectionProtocolRegistration> cprColl = participant
				.getCollectionProtocolRegistrationCollection();
		int cnt = 0;
		for (Iterator iterator = cprColl.iterator(); iterator.hasNext();) {
			CollectionProtocolRegistration collectionProtocolRegistration = (CollectionProtocolRegistration) iterator
					.next();
			collectionProtocolRegistration.setId(existingCprLst.get(cnt)
					.getId());
			cnt++;
		}

		caTissueAPIClient.update(participant);

		return copyFrom(existingParticipant);
	}

	public boolean deleteParticipantFromXML(String participantXMLStr)
			throws Exception {
		if (deleteParticipant(participantXMLStr) != null) {
			return true;
		} else {
			return false;
		}
	}

	public Participant deleteParticipant(String participantXMLStr)
			throws ApplicationException {
		Participant participant = parseParticipant(participantXMLStr);
		return deleteParticipant(participant);
	}

	/**
	 * Soft deletes Participant in CaTissue. That is to mark it as disabled with
	 * SSN set as NULL.
	 * 
	 * @param participant
	 *            - Participant to be deleted
	 * @return instance of Participant
	 */
	public Participant deleteParticipant(Participant participant)
			throws ApplicationException {
		if (participant == null
				|| StringUtils.isEmpty(participant.getSocialSecurityNumber())) {
			throw new ApplicationException(
					"Participant does not contain the unique identifier, SSN!");
		}

		Participant persistedParticipant = getParticipantForPatientId(participant
				.getLastName());
		if (persistedParticipant == null) {
			return null;
		}
		persistedParticipant.setActivityStatus("Disabled");
		persistedParticipant.setSocialSecurityNumber(null);
		persistedParticipant.setLastName(null);

		Iterator<CollectionProtocolRegistration> iter = persistedParticipant
				.getCollectionProtocolRegistrationCollection().iterator();
		while (iter.hasNext()) {
			CollectionProtocolRegistration collectionProtocolRegistration = (CollectionProtocolRegistration) iter
					.next();
			collectionProtocolRegistration.setProtocolParticipantIdentifier("");
			collectionProtocolRegistration.setActivityStatus("Disabled");
			caTissueAPIClient.update(collectionProtocolRegistration);
		}

		/*
		 * Iterator<ParticipantMedicalIdentifier> iter =
		 * persistedParticipant.getParticipantMedicalIdentifierCollection
		 * ().iterator(); while (iter.hasNext()) { ParticipantMedicalIdentifier
		 * participantMedicalIdentifier = (ParticipantMedicalIdentifier) iter
		 * .next();//caTissueAPIClient.delete(
		 * "Delete from ParticipantMedicalIdentifier pmi where pmi.id=" +
		 * participantMedicalIdentifier.getId());
		 * caTissueAPIClient.delete(participantMedicalIdentifier); }
		 */

		persistedParticipant.getParticipantMedicalIdentifierCollection()
				.clear();

		return caTissueAPIClient.update(persistedParticipant);
	}

	/**
	 * retrieve participants registered to a collection protocol
	 */
	public List<Participant> getParticipantsForCollectionProtocol(String cpTitle)
			throws ApplicationException {
		return caTissueAPIClient.getApplicationService().query(
				CqlUtility.getParticipantsForCP(cpTitle));
	}

	public Participant getParticipantForSSN(String ssn)
			throws ApplicationException {
		List<Participant> prtcpntLst = caTissueAPIClient
				.getApplicationService().query(
						CqlUtility.getParticipantForSSN(ssn));
		if (prtcpntLst == null || prtcpntLst.isEmpty()) {
			// TODO : decide on throwing exception vs returning null
			// throw new
			// ApplicationException("No participant found for the SSN!");
			return null;
		}
		return prtcpntLst.get(0);
	}

	public Participant getParticipantForPatientId(String mrn)
			throws ApplicationException {
		List<Participant> prtcpntLst = caTissueAPIClient
				.getApplicationService().query(
						CqlUtility.getParticipantForPatientId(mrn));
		if (prtcpntLst == null || prtcpntLst.isEmpty()) {
			// TODO : decide on throwing exception vs returning null
			return null;
		}
		return prtcpntLst.get(0);
	}

	private CollectionProtocolRegistration getCollectionProtocolRegistrationFromParticipant(
			Participant participant) throws ApplicationException {
		Collection<CollectionProtocolRegistration> cprCollection = participant
				.getCollectionProtocolRegistrationCollection();

		if (cprCollection == null || cprCollection.isEmpty()) {
			throw new ApplicationException(
					"Participant does not contain the collection protocol info to register to!");
		}

		List<CollectionProtocolRegistration> cprList = new ArrayList<CollectionProtocolRegistration>(
				cprCollection);
		return cprList.get(0);
	}

	private CollectionProtocol getCollectionProtocolFromParticipant(
			Participant participant) throws ApplicationException {
		Collection<CollectionProtocolRegistration> cprCollection = participant
				.getCollectionProtocolRegistrationCollection();

		if (cprCollection == null || cprCollection.isEmpty()) {
			throw new ApplicationException(
					"Participant does not contain the collection protocol info to register to!");
		}

		List<CollectionProtocolRegistration> cprList = new ArrayList<CollectionProtocolRegistration>(
				cprCollection);
		CollectionProtocol cp = cprList.get(0).getCollectionProtocol();

		if (cp == null || StringUtils.isEmpty(cp.getTitle())) {
			throw new ApplicationException(
					"Participant does not contain the collection protocol info to register to!");
		}

		return cp;
	}

	private Participant copyFrom(Participant participant) {
		Participant p = ParticipantFactory.getInstance().createObject();

		p.setId(participant.getId());
		p.setActivityStatus(participant.getActivityStatus());
		p.setBirthDate(participant.getBirthDate());
		p.setEthnicity(participant.getEthnicity());
		p.setFirstName(participant.getFirstName());
		p.setLastName(participant.getLastName());
		p.setGender(participant.getGender());
		p.setMetaPhoneCode(participant.getMetaPhoneCode());
		p.setSocialSecurityNumber(participant.getSocialSecurityNumber());
		p.setVitalStatus(participant.getVitalStatus());

		Iterator<Race> iter = participant.getRaceCollection().iterator();
		while (iter.hasNext()) {
			Race race = (Race) iter.next();
			Race r = RaceFactory.getInstance().createObject();
			r.setParticipant(p);
			r.setRaceName(race.getRaceName());
			p.getRaceCollection().add(r);
		}

		Iterator<CollectionProtocolRegistration> cprIter = participant
				.getCollectionProtocolRegistrationCollection().iterator();
		while (cprIter.hasNext()) {
			CollectionProtocolRegistration collectionProtocolRegistration = (CollectionProtocolRegistration) cprIter
					.next();

			CollectionProtocol collectionProtocol = collectionProtocolRegistration
					.getCollectionProtocol();
			CollectionProtocol cp = CollectionProtocolFactory.getInstance()
					.createObject();
			cp.setActivityStatus(collectionProtocol.getActivityStatus());
			cp.setTitle(collectionProtocol.getTitle());

			CollectionProtocolRegistration cpr = CollectionProtocolRegistrationFactory
					.getInstance().createObject();
			cpr.setParticipant(p);
			cpr.setConsentSignatureDate(collectionProtocolRegistration
					.getConsentSignatureDate());
			cpr.setRegistrationDate(collectionProtocolRegistration
					.getRegistrationDate());
			cpr.setProtocolParticipantIdentifier(collectionProtocolRegistration
					.getProtocolParticipantIdentifier());

			cpr.setCollectionProtocol(cp);
			p.getCollectionProtocolRegistrationCollection().add(cpr);
		}

		return p;
	}

}
