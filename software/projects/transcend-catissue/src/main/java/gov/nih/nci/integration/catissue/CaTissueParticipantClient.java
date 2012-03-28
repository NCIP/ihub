package gov.nih.nci.integration.catissue;

import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.CollectionProtocolRegistration;
import edu.wustl.catissuecore.domain.Participant;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class CaTissueParticipantClient {
	
	private static Logger LOG = LoggerFactory
			.getLogger(CaTissueParticipantClient.class);

	private final CaTissueAPIClientWithRegularAuthentication caTissueAPIClient;

	private XStream xStream = new XStream(new StaxDriver());

	public CaTissueParticipantClient(
			CaTissueAPIClientWithRegularAuthentication caTissueAPIClient) {
		super();
		this.caTissueAPIClient = caTissueAPIClient;

		xStream.alias("participant", Participant.class);
		xStream.alias("collectionProtocol", CollectionProtocol.class);
		xStream.alias("collectionProtocolRegistration", CollectionProtocolRegistration.class);
	}
	
	/**
	 * Returns the instance of CaTissueAPIClientWithRegularAuthentication being used
	 * @return instance of CaTissueAPIClientWithRegularAuthentication
	 */	
	public CaTissueAPIClientWithRegularAuthentication getCaTissueAPIClient() {
		return caTissueAPIClient;
	}
	
	/**
	 * Returns the instance of XStream being used
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
	
	/**
	 * Parses Participant from xml and registers it in CaTissue
	 * 
	 * @param participantXMLStr
	 *            - Participant as XML String
	 * @return instance of Participant
	 */
	public Participant registerParticipant(String participantXMLStr) throws ApplicationException {
		Participant participant = parseParticipant(participantXMLStr);
		return registerParticipant(participant);
	}
	
	/**
	 * Registers Participant in CaTissue
	 * 
	 * @param participant
	 *            - Participant to be registered
	 * @return instance of Participant
	 */
	public Participant registerParticipant(Participant participant) throws ApplicationException {
		if (participant == null || StringUtils.isEmpty(participant.getSocialSecurityNumber())) {
			throw new ApplicationException("Participant does not contain the unique identifier, SSN!");
		}
		CollectionProtocolRegistration cpr = getCollectionProtocolRegistrationFromParticipant(participant);
		participant.getCollectionProtocolRegistrationCollection().add(
				initCollectionProtocolRegistration(participant, cpr ));
		
		return caTissueAPIClient.insert(participant);
	}
	
	/**
	 * Soft deletes Participant in CaTissue. That is to mark it as disabled with SSN set as NULL.
	 * 
	 * @param participantXMLStr
	 *            - String representation of the Participant to be deleted
	 * @return instance of Participant 
	 */
	public Participant deleteParticipant(String participantXMLStr) throws ApplicationException {
		Participant participant = parseParticipant(participantXMLStr);
		return deleteParticipant(participant);
	}
	
	/**
	 * Soft deletes Participant in CaTissue. That is to mark it as disabled with SSN set as NULL.
	 * 
	 * @param participant
	 *            - Participant to be deleted
	 * @return instance of Participant
	 */
	public Participant deleteParticipant(Participant participant) throws ApplicationException {
		if (participant == null || StringUtils.isEmpty(participant.getSocialSecurityNumber())) {
			throw new ApplicationException("Participant does not contain the unique identifier, SSN!");
		}
		
		Participant persistedParticipant = getParticipantForSSN(participant.getSocialSecurityNumber());
		persistedParticipant.setActivityStatus("Disabled");
		persistedParticipant.setSocialSecurityNumber(null);
			
		return caTissueAPIClient.update(persistedParticipant);
	}
	
	/**
	 * Returns the list of participants registered to a collectionprotocol
	 * @return List<Participant> - list of participants
	 * @throws ApplicationException exception thrown if any
	 */
	public List<Participant> getParticipantsForCollectionProtocol(String cpTitle) throws ApplicationException {
		return caTissueAPIClient.getApplicationService().query(CqlUtility
				.getParticipantsForCP(cpTitle));
	}
	
	/**
	 * Returns the participant for SSN
	 * @return Participant - instance of participant
	 * @throws ApplicationException exception thrown if any
	 */
	public Participant getParticipantForSSN(String ssn) throws ApplicationException {
		List<Participant> prtcpntLst = caTissueAPIClient.getApplicationService().query(CqlUtility
				.getParticipantForSSN(ssn));
		if(prtcpntLst==null || prtcpntLst.isEmpty()) {
			//TODO : decide on throwing exception vs returning null
			//throw new ApplicationException("No participant found for the SSN!");
			return null;
		}
		return prtcpntLst.get(0);
	}
	
	/**
	 * Creates a CollectionProtocolRegistration
	 * 
	 * @param participant
	 *            - Participant object
	 * @param collectionProtocol
	 *            - CollectionProtocol object
	 * @return instance of CollectionProtocolRegistration
	 */
	public CollectionProtocolRegistration initCollectionProtocolRegistration(
			Participant participant, CollectionProtocolRegistration collectionProtocolRegistration) {

		collectionProtocolRegistration.setParticipant(participant);
		collectionProtocolRegistration.setProtocolParticipantIdentifier("");
		collectionProtocolRegistration.setActivityStatus("Active");
		collectionProtocolRegistration.setRegistrationDate(new Date());
		collectionProtocolRegistration.setConsentSignatureDate(new Date());
		return collectionProtocolRegistration;
	}
	
	private CollectionProtocolRegistration getCollectionProtocolRegistrationFromParticipant(Participant participant) throws ApplicationException {
		Collection<CollectionProtocolRegistration> cprCollection = participant.getCollectionProtocolRegistrationCollection();
		
		if(cprCollection == null || cprCollection.isEmpty()) {
			throw new ApplicationException("Participant does not contain the collection protocol info to register to!");
		}
		
		List<CollectionProtocolRegistration> cprList = new ArrayList<CollectionProtocolRegistration>(cprCollection);		
		return cprList.get(0);
	}
	
	private CollectionProtocol getCollectionProtocolFromParticipant(Participant participant) throws ApplicationException {
		Collection<CollectionProtocolRegistration> cprCollection = participant.getCollectionProtocolRegistrationCollection();
		
		if(cprCollection == null || cprCollection.isEmpty()) {
			throw new ApplicationException("Participant does not contain the collection protocol info to register to!");
		}
		
		List<CollectionProtocolRegistration> cprList = new ArrayList<CollectionProtocolRegistration>(cprCollection);		
		CollectionProtocol cp = cprList.get(0).getCollectionProtocol();
		
		if(cp == null || StringUtils.isEmpty(cp.getTitle())) {
			throw new ApplicationException("Participant does not contain the collection protocol info to register to!");
		}
		
		return cp;
	}
}
