package gov.nih.nci.integration.catissue.transcend;

import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.CollectionProtocolRegistration;
import edu.wustl.catissuecore.domain.Participant;
import gov.nih.nci.integration.catissue.CaTissueAPIClientBean;
import gov.nih.nci.integration.catissue.InvokeCaTissueException;
import gov.nih.nci.integration.catissue.PayloadParseException;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CaTissueClient bean class to communicate with caTissue
 * 
 * @author vinodh.rc
 */
public class TolvenCaTissueClientBean extends CaTissueAPIClientBean {

	protected Logger logger = LoggerFactory
			.getLogger(TolvenCaTissueClientBean.class);
	protected CaTissueParticipantParser participantParser;
	/**
	 * Creates a participant in caTissue
	 * 
	 * @throws Exception - exception thrown if any
	 */
	public void createParticipant(Participant participant) throws Exception {
		if(!checkParticipant(participant)){
			/*   Protocol Registration   */
			CollectionProtocol cp = getCollectionProtocol(participant);
			List cpList = appServiceCatissue.search(CollectionProtocol.class, cp);
			try{
				cp = (CollectionProtocol)cpList.get(0);
				} catch(Exception e) {
					throw new Exception("CollectionProtocol not found in caTissue");
				}
		//	initParticipantWithCPR(participant,cp);
			participant.getCollectionProtocolRegistrationCollection().add(initCollectionProtocolRegistration(participant, cp));
			participant = (Participant)appServiceCatissue.createObject(participant);
			logger.debug("*********participant with last name \""+participant.getLastName()+"\" created successfully**************");
		} else {
			logger.debug("*********participant with last name \""+participant.getLastName()+"\" already created **************");
		}
		
	}
	
	private CollectionProtocol getCollectionProtocol(Participant participant) throws InvokeCaTissueException {
		if (participant.getCollectionProtocolRegistrationCollection().isEmpty()) {
			throw new InvokeCaTissueException("Collection protocol cannot be empty!");
		}
		return (CollectionProtocol) participant.getCollectionProtocolRegistrationCollection().toArray()[0];
	}
	
	private boolean checkParticipant(Participant participant) {
		Participant searchParticipant = new Participant();
		searchParticipant.setLastName(participant.getLastName());
		List resultList;
		try {
			resultList = appServiceCatissue.search(Participant.class,searchParticipant);
			if(resultList.size() > 0)
				return true;
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Initializes the participant with CollectionProtocolRegistration of given
	 * CollectionProtocol
	 * 
	 * @author syed added on 8/21/09
	 * @param participant
	 *            - Participant object
	 * @param cp
	 *            - CollectionProtocol object
	 */
	public void initParticipantWithCPR(Participant participant,
			CollectionProtocol cp) {
		Collection collectionProtocolRegistrationCollection = new HashSet();
		CollectionProtocolRegistration collectionProtocolRegistration = initCollectionProtocolRegistration(
				participant, cp);
		collectionProtocolRegistrationCollection
				.add(collectionProtocolRegistration);
		participant
				.setCollectionProtocolRegistrationCollection(collectionProtocolRegistrationCollection);
	}

	/**
	 * Creates a CollectionProtocolRegistration
	 * 
	 * @author syed added on 8/21/09
	 * @param participant
	 *            - Participant object
	 * @param collectionProtocol
	 *            - CollectionProtocol object
	 * @return
	 */
	public CollectionProtocolRegistration initCollectionProtocolRegistration(
			Participant participant, CollectionProtocol collectionProtocol) {
		CollectionProtocolRegistration collectionProtocolRegistration = new CollectionProtocolRegistration();
		collectionProtocolRegistration
				.setCollectionProtocol(collectionProtocol);
		collectionProtocolRegistration.setParticipant(participant);
		collectionProtocolRegistration.setProtocolParticipantIdentifier("");
		collectionProtocolRegistration.setActivityStatus("Active");
		collectionProtocolRegistration.setRegistrationDate(new Date());
		collectionProtocolRegistration.setConsentSignatureDate(new Date());
		return collectionProtocolRegistration;
	}

	/**
	 * Creates a caTissue object from the xml string
	 * method based on key "targetOperation", for now, just "createParticipant"
	 * 
	 * @param xmlStr
	 *            - caTissue object xml string
	 * @param operationName - caTissue client operation name
	 * @throws Exception - exception thrown if any
	 */
	public void createObjectImpl(String xmlStr, String operationName)
			throws InvokeCaTissueException, PayloadParseException, Exception {
		
		try {
			if (operationName.equalsIgnoreCase("createParticipant")) {
				Participant participant = getParticipant(xmlStr);
				createParticipant(participant);
			} else {
				throw new InvokeCaTissueException("UnSupported opertaion name!");
			}
		} catch (Exception e) {
			logger.error("Error invoking caTissue API.", e);
			throw new InvokeCaTissueException(e);
		}
	}
	
	private Participant getParticipant(String participantXMLStr) throws PayloadParseException {
		try {
			return getParticipantParser().parseParticipant(participantXMLStr);
		} catch (Exception e) {
			logger.error("Error parsing the xml string into Participant", e);
			throw new PayloadParseException(e);
		}
	}

	public CaTissueParticipantParser getParticipantParser() {
		return participantParser;
	}

	public void setParticipantParser(CaTissueParticipantParser participantParser) {
		this.participantParser = participantParser;
	}

}
