/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.ihub.writer.ncies.entity.catissue.transcend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.tolven.trim.Act;
import org.tolven.trim.ActRelationship;
import org.tolven.trim.CE;
import org.tolven.trim.ObservationValueSlot;
import org.tolven.trim.Trim;
import org.w3c.dom.Node;

import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.CollectionProtocolRegistration;
import edu.wustl.catissuecore.domain.ExternalIdentifier;
import edu.wustl.catissuecore.domain.FluidSpecimen;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.domain.Race;
import edu.wustl.catissuecore.domain.Specimen;
import edu.wustl.catissuecore.domain.SpecimenCharacteristics;
import edu.wustl.catissuecore.domain.SpecimenCollectionGroup;
import edu.wustl.catissuecore.domain.TissueSpecimen;
import gov.nih.nci.ihub.writer.ncies.entity.catissue.CaTissueAPIClientBean;
import gov.nih.nci.ihub.writer.ncies.entity.catissue.InvokeCaTissueException;
import gov.nih.nci.ihub.writer.ncies.entity.catissue.PayloadParseException;
import gov.nih.nci.system.applicationservice.ApplicationException;

/**
 * CaTissueClient bean class to communicate with caTissue
 * 
 * @author syed added on 8/5/09 modified on 8/12/09
 */
public class TolvenCaTissueClientBean extends CaTissueAPIClientBean {

	protected Logger logger = LogManager
			.getLogger(TolvenCaTissueClientBean.class);
	protected TrimParser trimParser;
	private Map relationshipMap; // Map of relationships in Trim object with

	// relationship's name as key and the
	// relationship object as value

	/**
	 * Creates a participant in caTissue
	 * 
	 * @author syed added on 8/5/09 modified on 11/12/09
	 * @throws Exception
	 */
	public void createParticipant() throws Exception {
		Participant participant = getParticipant();
		if(!checkParticipant(participant)){
			/*   Protocol Registration   */
			CollectionProtocol cp = getCollectionProtocol(relationshipMap);
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

	/**
	 * Creates a Collection Protocol object based on the protocol details in
	 * specimenMap
	 * 
	 * @author syed added on 9/01/09
	 * @param specimenMap
	 *            - Map of Relationships
	 * @return cp - CollectionProtocol object
	 */
	public CollectionProtocol getCollectionProtocol(Map specimenMap) {
		CollectionProtocol cp = null;
		ActRelationship actRel = (ActRelationship) specimenMap
				.get("collectionProtocol");
		if (actRel != null) {
			List obsVal = actRel.getAct().getObservation().getValues();
			if ((obsVal != null) && (obsVal.size() > 0)) {
				cp = new CollectionProtocol();
				ObservationValueSlot val = (ObservationValueSlot) obsVal.get(0);
				if (val.getST() != null) {
					cp.setTitle(val.getST().getValue());
				}
				val = (ObservationValueSlot) obsVal.get(1);
				if (val.getST() != null) {
					cp.setShortTitle(val.getST().getValue());
				}
			}
		}
		return cp;
	}

	/**
	 * Creates a map of relationships in Trim object with relationship's name as
	 * key and the relationship object as value
	 * 
	 * @author syed added on 8/22/09
	 * @param trimObj
	 * @return relMap - Map of Relationships
	 */
	public Map<String, Object> createRelationshipMap(Act actObj) {
		Map<String, Object> relMap = new HashMap<String, Object>();
		List<ActRelationship> relationshipsList = actObj.getRelationships();
		for (ActRelationship actRel : relationshipsList) {
			relMap.put(actRel.getName(), actRel);
		}
		return relMap;
	}

	/**
	 * Creates a participant object with Tolven specimen details
	 * 
	 * @author syed added on 8/22/09
	 * @return partcipant - Participant object
	 */
	public Participant getParticipantForSpecimen() {
		Participant partcipant = new Participant();
		ActRelationship actRel = (ActRelationship) relationshipMap
				.get("cdmsSubjectId");
		ObservationValueSlot val;
		List obsVal;
		if (actRel != null) {
			obsVal = actRel.getAct().getObservation().getValues();
			if ((obsVal != null) && (obsVal.size() > 0)) {
				val = (ObservationValueSlot) obsVal.get(0);
				if (val.getST() != null) {
					partcipant.setLastName(val.getST().getValue());
				}
			}
		}
		actRel = (ActRelationship) relationshipMap.get("activityStatus");
		if (actRel != null) {
			obsVal = actRel.getAct().getObservation().getValues();
			if ((obsVal != null) && (obsVal.size() > 0)) {
				val = (ObservationValueSlot) obsVal.get(0);
				if (val.getST() != null) {
					partcipant.setActivityStatus(val.getST().getValue());
				}
			}
		}
		return partcipant;
	}

	/**
	 * Creates a list of maps containing specimen information
	 * 
	 * @author syed added on 9/01/2009
	 * @return specimenMapList - List of Specimen maps
	 */
	public List createSpecimenMaps() {
		ActRelationship actRel = (ActRelationship) relationshipMap
				.get("specimenList");
		List<ActRelationship> relationsList = actRel.getAct()
				.getRelationships();
		Specimen specimen = null;
		List specimenMapList = new ArrayList();
		for (ActRelationship actRelObj : relationsList) {
			Map specimenMap = createRelationshipMap(actRelObj.getAct());
			specimenMapList.add(specimenMap);
		}
		return specimenMapList;
	}

	/**
	 * Creates a Specimen object with required information
	 * 
	 * @author syed added on 9/01/2009
	 * @param scg
	 *            - SpecimenCollectionGroup object
	 * @param specimenMap
	 *            - Map of Relationships containing specimen information
	 * @return Specimen - the Specimen object
	 * @throws Exception
	 */
	public Specimen createSpecimenObject(SpecimenCollectionGroup scg,
			Map specimenMap) throws Exception {
		Specimen specimen = null;
		ActRelationship actRel = (ActRelationship) specimenMap
				.get("specimenClass");
		ObservationValueSlot val;
		List obsVal = null;
		if (actRel != null) {
			obsVal = actRel.getAct().getObservation().getValues();
			if ((obsVal != null) && (obsVal.size() > 0)) {
				val = (ObservationValueSlot) obsVal.get(0);
				if (val.getST() != null) {
					if (val.getST().getValue().equalsIgnoreCase("Tissue")) {
						specimen = new TissueSpecimen();
					} else if (val.getST().getValue().equalsIgnoreCase("Fluid")) {
						specimen = new FluidSpecimen();
					} else {
						throw new Exception("Invalid Specimen class");
					}
				}
			}
		}
		fillSpecimen(specimen, specimenMap);
		specimen.setSpecimenCollectionGroup(scg);
		return specimen;
	}

	/**
	 * Updates the Specimen object with the specimen details in relationshipMap
	 * 
	 * @author syed added on 8/22/09
	 * @param specimenObj
	 *            - Specimen object
	 */
	public void fillSpecimen(Specimen specimenObj, Map relMap) {
		ActRelationship actRel = (ActRelationship) relMap.get("cdmsSpecimenId");
		List obsVal;
		ObservationValueSlot val;
		if (actRel != null) {
			obsVal = actRel.getAct().getObservation().getValues();
			if ((obsVal != null) && (obsVal.size() > 0)) {
				val = (ObservationValueSlot) obsVal.get(0);
				if (val.getST() != null) {
					specimenObj.setLabel(val.getST().getValue());
					Collection externalIdentifierCollection = specimenObj
							.getExternalIdentifierCollection();
					ExternalIdentifier externalIdentifier = null;
					if (externalIdentifierCollection.size() > 0) {

						externalIdentifier = (ExternalIdentifier) externalIdentifierCollection
								.iterator().next();
						externalIdentifier.setName(val.getLabel().getValue());
						externalIdentifier.setValue(val.getST().getValue());
					} else {

						externalIdentifier = new ExternalIdentifier();
						externalIdentifier.setName(val.getLabel().getValue());
						externalIdentifier.setValue(val.getST().getValue());
						externalIdentifierCollection.add(externalIdentifier);
						specimenObj
								.setExternalIdentifierCollection(externalIdentifierCollection);
					}

				}
			}
		}
		actRel = (ActRelationship) relMap.get("specimenClass");
		if (actRel != null) {
			obsVal = actRel.getAct().getObservation().getValues();
			if ((obsVal != null) && (obsVal.size() > 0)) {
				val = (ObservationValueSlot) obsVal.get(0);
				if (val.getST() != null) {
					specimenObj.setSpecimenClass(val.getST().getValue());
				}
			}
		}
		actRel = (ActRelationship) relMap.get("specimenType");
		if (actRel != null) {
			obsVal = actRel.getAct().getObservation().getValues();
			if ((obsVal != null) && (obsVal.size() > 0)) {
				val = (ObservationValueSlot) obsVal.get(0);
				if (val.getST() != null) {
					specimenObj.setSpecimenType(val.getST().getValue());
				}
			}
		}
		actRel = (ActRelationship) relMap.get("barCode");
		if (actRel != null) {
			obsVal = actRel.getAct().getObservation().getValues();
			if ((obsVal != null) && (obsVal.size() > 0)) {
				val = (ObservationValueSlot) obsVal.get(0);
				if (val.getST() != null) {
					specimenObj.setBarcode(val.getST().getValue());
				}
			}
		}

		actRel = (ActRelationship) relMap.get("activityStatus");
		if (actRel != null) {
			obsVal = actRel.getAct().getObservation().getValues();
			if ((obsVal != null) && (obsVal.size() > 0)) {
				val = (ObservationValueSlot) obsVal.get(0);
				if (val.getST() != null) {
					specimenObj.setActivityStatus(val.getST().getValue());
				}
			}
		}

		actRel = (ActRelationship) relMap.get("pathologicalStatus");
		if (actRel != null) {
			obsVal = actRel.getAct().getObservation().getValues();
			if ((obsVal != null) && (obsVal.size() > 0)) {
				val = (ObservationValueSlot) obsVal.get(0);
				if (val.getST() != null) {
					specimenObj.setPathologicalStatus(val.getST().getValue());
				}
			}
		}

		actRel = (ActRelationship) relMap.get("initialQuantity");
		if (actRel != null) {
			obsVal = actRel.getAct().getObservation().getValues();
			if ((obsVal != null) && (obsVal.size() > 0)) {
				val = (ObservationValueSlot) obsVal.get(0);
				if (val.getST() != null) {
					specimenObj.setInitialQuantity(new Double(val.getST()
							.getValue()));
				}
			}
		}

		actRel = (ActRelationship) relMap.get("availableQuantity");
		if (actRel != null) {
			obsVal = actRel.getAct().getObservation().getValues();
			if ((obsVal != null) && (obsVal.size() > 0)) {
				val = (ObservationValueSlot) obsVal.get(0);
				if (val.getST() != null) {
					specimenObj.setAvailableQuantity(new Double(val.getST()
							.getValue()));
				}
			}
		}

		actRel = (ActRelationship) relMap.get("specimenCharacteristics");
		if (actRel != null) {
			obsVal = actRel.getAct().getObservation().getValues();
			if ((obsVal != null) && (obsVal.size() > 0)) {
				SpecimenCharacteristics specimenCharacteristics = specimenObj
						.getSpecimenCharacteristics();
				if (specimenCharacteristics == null) {
					specimenCharacteristics = new SpecimenCharacteristics();
					specimenObj
							.setSpecimenCharacteristics(specimenCharacteristics);
				}

				val = (ObservationValueSlot) obsVal.get(0);
				if (val.getST() != null) {
					specimenCharacteristics.setTissueSite(val.getST()
							.getValue());
				}
				val = (ObservationValueSlot) obsVal.get(1);
				if (val.getST() != null) {
					specimenCharacteristics.setTissueSide(val.getST()
							.getValue());
				}
			}
		}
		specimenObj.setIsAvailable(new Boolean(true));
//		specimenObj.setSpecimenPosition(null);
		specimenObj.setCollectionStatus("");
	}

	/**
	 * Creates specimens in caTissue
	 * 
	 * @author syed added on 8/31/09 modified on 11/12/09
	 * @throws Exception
	 */
	public void createSpecimens() throws Exception {
		CollectionProtocol cp = null;
		List participantList = null;
		Participant participantObj = null;
		Participant participant = getParticipantForSpecimen();
		List<SpecimenCollectionGroup> scgList = new ArrayList<SpecimenCollectionGroup>();
		String query = "edu.wustl.catissuecore.domain.CollectionProtocolRegistration,edu.wustl.catissuecore.domain.Participant";
		List<CollectionProtocolRegistration> cprList = appServiceCatissue
				.search(query, participant);
		query = "edu.wustl.catissuecore.domain.SpecimenCollectionGroup,edu.wustl.catissuecore.domain.CollectionProtocolRegistration";
		CollectionProtocolRegistration cprKey = new CollectionProtocolRegistration();
		// Retrieves SpecimenCollectionGroups
		for (CollectionProtocolRegistration cpr : cprList) {
			cprKey.setId(cpr.getId());
			scgList.addAll(appServiceCatissue.search(query, cprKey));
		}
		List<Map> specimenMapList = createSpecimenMaps();
		Specimen specimen = null;
		for (Map specimenMap : specimenMapList) {
			cp = getCollectionProtocol(specimenMap);
			int flag = 0;
			if ((scgList != null) && (scgList.size() > 0)) {
				flag = 1; // SpecimenCollectionGroup list is not empty
				for (SpecimenCollectionGroup scg : scgList) {
					CollectionProtocol cpObj = scg
							.getCollectionProtocolRegistration()
							.getCollectionProtocol();
					if ((cpObj.getTitle().equals(cp.getTitle()))
							&& (cpObj.getShortTitle()
									.equals(cp.getShortTitle()))) {
						flag = 2; // Participant has a SpecimenCollectionGroup
						// under the given protocol
						specimen = createSpecimenObject(scg, specimenMap);
						break;
					}
				}
			}
			if (flag < 2) {
				if (participantList == null) {
					participantList = appServiceCatissue.search(
							Participant.class, participant);
					try {
						participantObj = (Participant) participantList.get(0);
					} catch (Exception e) {
						throw new Exception("Participant not found in caTissue");
					}
				}
				List cpList = appServiceCatissue.search(
						CollectionProtocol.class, cp);
				try {
					cp = (CollectionProtocol) cpList.get(0);
				} catch (Exception e) {
					throw new Exception(
							"CollectionProtocol not found in caTissue");
				}
				CollectionProtocolRegistration cprObj = (CollectionProtocolRegistration) appServiceCatissue
						.createObject(initCollectionProtocolRegistration(
								participantObj, cp));
				logger
						.debug("Participant registered under CollectionProtocol: "
								+ cp.getTitle());
				logger
						.debug("----------------------------------------------------------");
				SpecimenCollectionGroup scg = getSpecimenCollectionGroup(cprObj
						.getSpecimenCollectionGroupCollection(), cp);
				specimen = createSpecimenObject(scg, specimenMap);
				scgList.add(scg);
			}
			try {
				Specimen searchSpecimen = new Specimen();
				searchSpecimen.setLabel(specimen.getLabel());
				List results = appServiceCatissue.search(Specimen.class,
						searchSpecimen);
				if (results.size() == 0) {
					specimen = (Specimen) appServiceCatissue
							.createObject(specimen);
					logger.debug("Specimen created successfully: Label = "
							+ specimen.getLabel() + " Barcode = "
							+ specimen.getBarcode());
				} else {
					logger.debug("Specimen already created");
				}

			} catch (Exception e) {
				logger.debug("Specimen creation failed");
				throw (e);
			}
		}
	}

	/**
	 * Gets the SpecimenCollectionGroup object from
	 * specimenCollectionGroupCollection with given CollectionProtocol
	 * 
	 * @author syed added on 9/01/09
	 * @param specimenCollectionGroupCollection
	 *            - Collection object
	 * @param cp
	 *            - CollectionProtocol object
	 * @return scg - SpecimenCollectionGroup object
	 */
	public SpecimenCollectionGroup getSpecimenCollectionGroup(
			Collection specimenCollectionGroupCollection, CollectionProtocol cp) {
		SpecimenCollectionGroup scg = null;
		CollectionProtocol cpObj = null;
		Iterator scgItr = specimenCollectionGroupCollection.iterator();
		while (scgItr.hasNext()) {
			scg = (SpecimenCollectionGroup) scgItr.next();
			cpObj = scg.getCollectionProtocolRegistration()
					.getCollectionProtocol();
			if (cpObj.getId().equals(cp.getId())) {
				cpObj.setShortTitle(cp.getShortTitle());
				cpObj.setTitle(cp.getTitle());
				break;
			}
		}
		return scg;
	}

	/**
	 * Retrieves participant information from relationshipMap
	 * 
	 * @author syed added on 8/5/09 modified on 8/24/09
	 * @return participant - Participant object
	 */
	public Participant getParticipant() {
		Participant participant = new Participant();
		ActRelationship actRel = (ActRelationship) relationshipMap
				.get("raceCollection");
		List obsVal;
		ObservationValueSlot val;
		if (actRel != null) {
			Collection<Race> raceCollection = new HashSet<Race>();

			Race race = null;
			obsVal = actRel.getAct().getObservation().getValues();
			if ((obsVal != null) && (obsVal.size() > 0)) {
				ObservationValueSlot raceValues = (ObservationValueSlot) obsVal
						.get(0);
				if (raceValues.getCE() != null) {
					race = new Race();
					race.setRaceName(raceValues.getCE().getDisplayName());
					race.setParticipant(participant);
					raceCollection.add(race);
				}
				if ((raceValues.getSETCES() != null)
						&& (raceValues.getSETCES().size() > 0)) {
					for (CE raceType : raceValues.getSETCES()) {
						race = new Race();
						race.setRaceName(raceType.getDisplayName());
						race.setParticipant(participant);
						raceCollection.add(race);
					}
				}
			}
			participant.setRaceCollection(raceCollection);
		}
		actRel = (ActRelationship) relationshipMap.get("ethnicity");
		if (actRel != null) {
			obsVal = actRel.getAct().getObservation().getValues();
			if ((obsVal != null) && (obsVal.size() > 0)) {
				ObservationValueSlot ethnicityVal = (ObservationValueSlot) obsVal
						.get(0);
				if (ethnicityVal.getCE() != null) {
					participant.setEthnicity(ethnicityVal.getCE()
							.getDisplayName());
				}
			}
		}
		actRel = (ActRelationship) relationshipMap.get("cdmsSubjectId");
		if (actRel != null) {
			obsVal = actRel.getAct().getObservation().getValues();
			if ((obsVal != null) && (obsVal.size() > 0)) {
				ObservationValueSlot cdmsVal = (ObservationValueSlot) obsVal
						.get(0);
				if (cdmsVal.getST() != null) {
					participant.setLastName(cdmsVal.getST().getValue());
				}
			}
		}
		actRel = (ActRelationship) relationshipMap.get("activityStatus");
		if (actRel != null) {
			obsVal = actRel.getAct().getObservation().getValues();
			if ((obsVal != null) && (obsVal.size() > 0)) {
				ObservationValueSlot activityVal = (ObservationValueSlot) obsVal
						.get(0);
				if (activityVal.getST() != null) {
					participant.setActivityStatus(activityVal.getST()
							.getValue());
				}
			}
		}
		return participant;
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

	public Map getRelationshipMap() {
		return relationshipMap;
	}

	public void setRelationshipMap(Map relationshipMap) {
		this.relationshipMap = relationshipMap;
	}

	/**
	 * Creates a relationship map of the trim object and invokes the target
	 * method based on key "targetOperation"
	 * 
	 * @author syed added on 8/24/09
	 * @param trim
	 *            - Trim object
	 * @param operationName
	 * @throws Exception
	 */
	public void createObjectImpl(Node trimNode, String operationName)
			throws InvokeCaTissueException, PayloadParseException, Exception {
		try {
			Trim trim = trimParser.parseTrim(trimNode);
			relationshipMap = createRelationshipMap(trim.getAct());
		} catch (Exception e) {
			logger.error("Error parsing the payload.", e);
			throw new PayloadParseException(e);
		}
		try {
			if (operationName.equalsIgnoreCase("createParticipant")) {
				createParticipant();
			} else if (operationName.equalsIgnoreCase("createSpecimen")) {
				createSpecimens();
			}
		} catch (Exception e) {
			logger.error("Error invoking caTissue API.", e);
			throw new InvokeCaTissueException(e);
		}
	}

	public TrimParser getTrimParser() {
		return trimParser;
	}

	public void setTrimParser(TrimParser trimParser) {
		this.trimParser = trimParser;
	}

}
