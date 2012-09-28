package gov.nih.nci.integration.catissue.client;

import java.io.StringReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.CollectionProtocolRegistration;
import edu.wustl.catissuecore.domain.ConsentTier;
import edu.wustl.catissuecore.domain.ConsentTierResponse;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.domain.ParticipantMedicalIdentifier;
import edu.wustl.catissuecore.domain.Race;
import edu.wustl.catissuecore.factory.CollectionProtocolFactory;
import edu.wustl.catissuecore.factory.CollectionProtocolRegistrationFactory;
import edu.wustl.catissuecore.factory.ConsentTierResponseFactory;
import edu.wustl.catissuecore.factory.ParticipantFactory;
import edu.wustl.catissuecore.factory.RaceFactory;
import gov.nih.nci.system.applicationservice.ApplicationException;

/**
 * This is the Client class for create Registration and update Registration
 * 
 * @author Vinodh
 * @author Rohit Gupta
 */
public class CaTissueParticipantClient {

    private static final String NOT_SPECIFIED = "Not Specified";
    private static final Logger LOG = LoggerFactory.getLogger(CaTissueParticipantClient.class);
    private CaTissueAPIClientWithRegularAuthentication caTissueAPIClient;
    private static final String DISABLED = "Disabled";
    private final XStream xStream = new XStream(new StaxDriver());

    /**
     * Constructor
     * 
     * @param loginName - loginName for the API authentication
     * @param password - password for the API authentication
     * @throws MalformedURLException - MalformedURLException
     * @throws BeansException - BeansException
     */
    public CaTissueParticipantClient(String loginName, String password) throws BeansException, MalformedURLException {
        super();
        Thread.currentThread().setContextClassLoader(CaTissueParticipantClient.class.getClassLoader());
        this.caTissueAPIClient = new CaTissueAPIClientWithRegularAuthentication(loginName, password);
        initXStream();
    }

    private void initXStream() {
        // CGLIB - related settings start
        /*
         * xStream.addDefaultImplementation( org.hibernate.collection.PersistentList.class, java.util.List.class);
         * xStream.addDefaultImplementation( org.hibernate.collection.PersistentMap.class, java.util.Map.class);
         * xStream.addDefaultImplementation( org.hibernate.collection.PersistentSet.class, java.util.Set.class);
         * 
         * Mapper mapper = xStream.getMapper(); xStream.registerConverter(new HibernateCollectionConverter(mapper));
         * xStream.registerConverter(new HibernateMapConverter(mapper));
         */
        // CGLIB - related settings end

        xStream.alias("participant", Participant.class);
        xStream.alias("race", Race.class);
        xStream.alias("collectionProtocol", CollectionProtocol.class);
        xStream.alias("collectionProtocolRegistration", CollectionProtocolRegistration.class);
        xStream.alias("consentTierResponse", ConsentTierResponse.class);
        xStream.alias("consentTier", ConsentTier.class);
        xStream.alias("participantMedicalIdentifier", ParticipantMedicalIdentifier.class);

        final String[] accFrmts = new String[] { "", "yyyyMMdd", "yyyy-MM-dd", "MM/dd/yyyy", // catissue formats
                "yyyy-MM-dd HH:mm:ss.S a", "yyyy-MM-dd HH:mm:ssz", "yyyy-MM-dd HH:mm:ss z", // JDK 1.3 needs both
                                                                                            // versions
                "yyyy-MM-dd HH:mm:ssa" }; // backwards compatibility
        xStream.registerConverter(new DateConverter("yyyy-MM-dd HH:mm:ss.S z", accFrmts, true));

    }

    /**
     * Returns the instance of CaTissueAPIClientWithRegularAuthentication being used
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
     * @param participantXMLStr - Participant as XML String
     * @return instance of Participant
     */
    public Participant parseParticipant(String participantXMLStr) {
        return (Participant) xStream.fromXML(new StringReader(participantXMLStr));
    }

    /**
     * This method is used to register a Participant in caTissue
     * 
     * @param participantXMLStr - Participant information in the form of XMLString
     * @return XMLString - currently null
     * @throws ApplicationException ApplicationException
     */
    public String registerParticipantFromXML(String participantXMLStr) throws ApplicationException {
        return registerParticipant(participantXMLStr);
    }

    /**
     * This method is used to register a Participant. It will parse the incoming XMLString and then call the private
     * method to register the participant
     * 
     * @param participantXMLStr - Participant information in the form of XMLString
     * @return null
     * @throws ApplicationException - ApplicationException
     */
    public String registerParticipant(String participantXMLStr) throws ApplicationException {
        final Participant participant = parseParticipant(participantXMLStr);
        registerParticipant(participant);

        return null;
    }

    /**
     * Registers Participant in CaTissue
     * 
     * @param participant - Participant to be registered
     * @return instance of Participant
     * @throws ApplicationException - ApplicationException
     */
    public Participant registerParticipant(Participant participant) throws ApplicationException {
        if (participant == null || StringUtils.isEmpty(participant.getLastName())) {
            throw new ApplicationException("Participant does not contain the unique medical identifier");
        }

        // populate the CP-Title inside Participant-CPR-CP-Title. Also call populateConsentTierResponse()
        populateCP(participant);

        Participant returnParticipant = null;
        try {
            returnParticipant = caTissueAPIClient.insert(participant);
        } catch (ApplicationException ae) {
            LOG.error("Create Registration Failed for Participant with Subject ID as " + participant.getLastName(), ae);
            throw new ApplicationException(ae);
        }
        return returnParticipant;
    }

    /**
     * This method is used to update a Participant in caTissue.
     * 
     * @param participantXMLStr - Participant update information in the form of XMLString
     * @return exisitingParticipant detail in form of XMLString which might be required for Rollback
     * @throws ApplicationException - ApplicationException
     */
    public String updateParticipantRegistrationFromXML(String participantXMLStr) throws ApplicationException {
        return updateParticipantRegistration(participantXMLStr);
    }

    /**
     * This method is used to update a Participant in caTissue. It will parse the incoming XMLString and then call the
     * private method to update the participant
     * 
     * @param participantXMLStr - Participant update information in the form of XMLString
     * @return exisitingParticipant detail in form of XMLString which might be required for Rollback
     * @throws ApplicationException - ApplicationException
     */
    public String updateParticipantRegistration(String participantXMLStr) throws ApplicationException {
        Participant participant = parseParticipant(participantXMLStr);
        participant = updateParticipantRegistration(participant);
        return xStream.toXML(participant);
    }

    /**
     * updates Participant registration in CaTissue
     * 
     * @param participant - Participant to be registered
     * @return instance of Participant
     * @throws ApplicationException - ApplicationException
     */
    public Participant updateParticipantRegistration(Participant participant) throws ApplicationException {
        if (participant == null || StringUtils.isEmpty(participant.getLastName())) {
            LOG.error("Participant does not contain the unique medical identifier " + participant.getLastName());
            throw new ApplicationException("Participant does not contain the unique medical identifier");
        }

        final Participant existingParticipant = getParticipantForPatientId(participant.getLastName(),
                getShortTitleForParticipant(participant));
        if (existingParticipant == null) {
            LOG.error("CaTissue does not contain a participant with the unique identifier, "
                    + participant.getLastName());
            throw new ApplicationException("CaTissue does not contain a participant with the unique identifier, "
                    + participant.getLastName());
        }

        // check if the collection protocol is different, if different then throw exception
        if (isCollectionProtocolChanged(participant, existingParticipant)) {
            LOG.error("Update Participant Registration failed for " + participant.getLastName()
                    + "and exception is Study can't be changed while updating the Participant.");
            throw new ApplicationException("Update Participant Registration failed for " + participant.getLastName()
                    + "and exception is Study can't be changed while updating the Participant.");
        }

        participant.setId(existingParticipant.getId());

        // Set the values in CPR only for Create Participant Flow and don't set it for UpdateParticipant
        participant.setCollectionProtocolRegistrationCollection(new HashSet<CollectionProtocolRegistration>());

        // code to handle the existing/new race collection
        updateParticipantRaceCollection(existingParticipant, participant);

        try {
            caTissueAPIClient.update(participant);
        } catch (ApplicationException ae) {
            LOG.error("Update Registration Failed for Participant with Subject ID " + participant.getLastName(), ae);
            throw new ApplicationException(ae);
        }

        return copyFrom(existingParticipant);
    }

    /**
     * This method is used to check if the CollectionProtocol of incoming participant is different from
     * existingParticipant
     * 
     * @return true if the CP is different
     */
    private boolean isCollectionProtocolChanged(Participant participant, Participant existingParticipant) {
        boolean isCPChanged = false;
        final List<CollectionProtocolRegistration> existCPRList = new ArrayList<CollectionProtocolRegistration>(
                existingParticipant.getCollectionProtocolRegistrationCollection());
        final CollectionProtocolRegistration existCPR = (CollectionProtocolRegistration) existCPRList.get(0);
        final String existShortTitle = existCPR.getCollectionProtocol().getShortTitle();

        final ArrayList<CollectionProtocolRegistration> cprColl = new ArrayList<CollectionProtocolRegistration>(
                participant.getCollectionProtocolRegistrationCollection());
        final String shortTitle = cprColl.get(0).getCollectionProtocol().getShortTitle();
        if (!shortTitle.equals(existShortTitle)) {
            isCPChanged = true;
        }
        return isCPChanged;
    }

    /**
     * This method is used to delete a Participant in caTissue.
     * 
     * @param participantXMLStr participant to be delete in the form of XMLString
     * @return true if participant is deleted
     * @throws ApplicationException - ApplicationException
     */
    public boolean deleteParticipantFromXML(String participantXMLStr) throws ApplicationException {
        final Participant participant = deleteParticipant(participantXMLStr);
        boolean flag = false;

        if (participant != null) {
            flag = true;
        }

        return flag;
    }

    /**
     * This method is used to delete a Participant in caTissue. It will parse the incoming XMLString and then call the
     * private method to delete the participant
     * 
     * @param participantXMLStr participant to be delete in the form of XMLString
     * @return Participant - deleted Participant
     * @throws ApplicationException - ApplicationException
     */
    public Participant deleteParticipant(String participantXMLStr) throws ApplicationException {
        final Participant participant = parseParticipant(participantXMLStr);
        return deleteParticipant(participant);
    }

    /**
     * Soft deletes Participant in CaTissue. That is to mark it as disabled with SSN set as NULL.
     * 
     * @param participant - Participant to be deleted
     * @return instance of Participant
     * @throws ApplicationException - ApplicationException
     */
    public Participant deleteParticipant(Participant participant) throws ApplicationException {
        final Participant persistedParticipant = getParticipantForPatientId(participant.getLastName(),
                getShortTitleForParticipant(participant));
        if (persistedParticipant == null) {
            return null;
        }

        persistedParticipant.setActivityStatus(DISABLED);
        persistedParticipant.setSocialSecurityNumber(null);
        persistedParticipant.setLastName(null);

        final Iterator<CollectionProtocolRegistration> iter = persistedParticipant
                .getCollectionProtocolRegistrationCollection().iterator();
        while (iter.hasNext()) {
            final CollectionProtocolRegistration collectionProtocolRegistration = (CollectionProtocolRegistration) iter
                    .next();
            collectionProtocolRegistration.setProtocolParticipantIdentifier("");
            collectionProtocolRegistration.setActivityStatus(DISABLED);
            caTissueAPIClient.update(collectionProtocolRegistration);
        }

        /*
         * Iterator<ParticipantMedicalIdentifier> iter = persistedParticipant.getParticipantMedicalIdentifierCollection
         * ().iterator(); while (iter.hasNext()) { ParticipantMedicalIdentifier participantMedicalIdentifier =
         * (ParticipantMedicalIdentifier) iter .next();//caTissueAPIClient.delete(
         * "Delete from ParticipantMedicalIdentifier pmi where pmi.id=" + participantMedicalIdentifier.getId());
         * caTissueAPIClient.delete(participantMedicalIdentifier); }
         */

        persistedParticipant.getParticipantMedicalIdentifierCollection().clear();

        return caTissueAPIClient.update(persistedParticipant);
    }

    /**
     * retrieve participants registered to a collection protocol
     * 
     * @param cpTitle - collection protocol title
     * @return list of participant for that collection protocol
     * @throws ApplicationException - ApplicationException
     */
    public List<Participant> getParticipantsForCollectionProtocol(String cpTitle) throws ApplicationException {
        return caTissueAPIClient.getApplicationService().query(CqlUtility.getParticipantsForCP(cpTitle));
    }

    /**
     * Retrieve the participant for given PatientId
     * 
     * @param participantId - PatientId for which participant has to be fetched
     * @param shortTitle - ShortTitle of the CollectionProtocol
     * @return Participant
     * @throws ApplicationException - ApplicationException
     */
    public Participant getParticipantForPatientId(String participantId, String shortTitle) throws ApplicationException {
        final List<Participant> prtcpntLst = caTissueAPIClient.getApplicationService().query(
                CqlUtility.getParticipantForPatientId(participantId, shortTitle));
        if (prtcpntLst == null || prtcpntLst.isEmpty()) {
            return null;
        }

        return prtcpntLst.get(0);
    }

    /**
     * This method is used to populate the CP-title inside Participant object for given CP-shortTitle. Also it will call
     * method to populate the default ConsentTierResponse
     * 
     * @param participant
     * @return Participant with Title populated
     * @throws ApplicationException - ApplicationException
     */
    private Participant populateCP(Participant participant) throws ApplicationException {
        final ArrayList<CollectionProtocolRegistration> cprColl = new ArrayList<CollectionProtocolRegistration>(
                participant.getCollectionProtocolRegistrationCollection());
        if (!cprColl.isEmpty()) {
            // We are expecting only ONE CPR here
            final CollectionProtocolRegistration incomingCPR = cprColl.get(0);
            final CollectionProtocol incomingCP = incomingCPR.getCollectionProtocol();

            // get the existing CollectionProtocol for given shortTitle
            final CollectionProtocol fetchedCP = getExistingCollectionProtocol(incomingCP.getShortTitle());

            if (fetchedCP != null) {
                // set the fetched CP_Title into the Participant-CPR-CP-title
                incomingCP.setTitle(fetchedCP.getTitle());

                populateConsentTierResponse(incomingCPR, fetchedCP);
            }
        }
        return participant;
    }

    private void populateConsentTierResponse(final CollectionProtocolRegistration incomingCPR,
            final CollectionProtocol fetchedCP) throws ApplicationException {
        final Collection<ConsentTier> consentTierCollection = fetchedCP.getConsentTierCollection();
        if (consentTierCollection == null || consentTierCollection.isEmpty()) {
            LOG.error("Collection Protocol's consent tier statements list is empty.");
            throw new ApplicationException("Collection Protocol's consent tier statements list is empty.");
        }
        final Iterator<ConsentTier> it = consentTierCollection.iterator();

        while (it.hasNext()) {
            final ConsentTier consentTier = (ConsentTier) it.next();
            final ConsentTierResponse ctResp = ConsentTierResponseFactory.getInstance().createObject();
            ctResp.setConsentTier(consentTier);
            ctResp.setResponse(NOT_SPECIFIED);
            incomingCPR.getConsentTierResponseCollection().add(ctResp);
        }
    }

    private String getShortTitleForParticipant(Participant participant) {
        String shortTitle = "";
        final ArrayList<CollectionProtocolRegistration> cprColl = new ArrayList<CollectionProtocolRegistration>(
                participant.getCollectionProtocolRegistrationCollection());
        if (!cprColl.isEmpty()) {
            // We are expecting only ONE CPR here
            final CollectionProtocolRegistration cpr = cprColl.get(0);
            final CollectionProtocol collectionProtocol = cpr.getCollectionProtocol();
            shortTitle = collectionProtocol.getShortTitle();
        }
        return shortTitle;
    }

    /**
     * This method is used to update the Race collection. It will replace the existing Race with the current Race and
     * then if the current race is MORE, then it will add remaining new race inside the collection. If the new Race is
     * LESSER than existing then it will set the remaining existing(after replacing with current race) to null. This
     * logic is implemented to avoid unnecessary null records in the database.
     * 
     * @return
     */
    private Participant updateParticipantRaceCollection(Participant existingParticipant, Participant participant) {
        final Race[] existRaceArray = (Race[]) existingParticipant.getRaceCollection().toArray(
                new Race[existingParticipant.getRaceCollection().size()]);
        final Race[] newRaceArray = (Race[]) participant.getRaceCollection().toArray(
                new Race[participant.getRaceCollection().size()]);

        final int existRaceCount = existRaceArray.length;
        final int newRaceCount = newRaceArray.length;

        // if the existing Race are more than the new/incoming Race
        if (existRaceCount >= newRaceCount) {
            int i = 0;
            for (; i < newRaceCount; i++) {
                // Iterate(till newRaceCount) & Replace the existing Race with the new/incoming Race
                existRaceArray[i].setRaceName(newRaceArray[i].getRaceName());
                existRaceArray[i].setParticipant(newRaceArray[i].getParticipant());
            }
            for (; i < existRaceCount; i++) {
                // set the remaining(more) existing Race to NULL
                existRaceArray[i].setRaceName(null);
                existRaceArray[i].setParticipant(null);
            }
            final Set<Race> mySet = new HashSet<Race>();
            Collections.addAll(mySet, existRaceArray);
            participant.setRaceCollection(mySet);
        } else {
            // if the existing Race are LESS than the new/incoming Race
            int i = 0;
            for (; i < existRaceCount; i++) {
                // Iterate(till existRaceCount) & Replace the existing Race with the new/incoming Race
                existRaceArray[i].setRaceName(newRaceArray[i].getRaceName());
                existRaceArray[i].setParticipant(newRaceArray[i].getParticipant());
            }
            final Set<Race> mySet = new HashSet<Race>();
            Collections.addAll(mySet, existRaceArray);
            participant.setRaceCollection(mySet);
            for (; i < newRaceCount; i++) {
                // add the remaining left new/incoming Race in the collection
                participant.getRaceCollection().add(newRaceArray[i]);
            }
        }
        return participant;
    }

    private CollectionProtocol getExistingCollectionProtocol(String shortTitle) throws ApplicationException {
        CollectionProtocol cp = new CollectionProtocol();
        cp.setShortTitle(shortTitle);
        cp = caTissueAPIClient.searchById(CollectionProtocol.class, cp);
        return cp;
    }

    private Participant copyFrom(Participant participant) {
        final Participant p = ParticipantFactory.getInstance().createObject();

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

        final Iterator<Race> iter = participant.getRaceCollection().iterator();
        while (iter.hasNext()) {
            final Race race = (Race) iter.next();
            final Race r = RaceFactory.getInstance().createObject();
            r.setParticipant(p);
            r.setRaceName(race.getRaceName());
            p.getRaceCollection().add(r);
        }

        final Iterator<CollectionProtocolRegistration> cprIter = participant
                .getCollectionProtocolRegistrationCollection().iterator();
        while (cprIter.hasNext()) {
            final CollectionProtocolRegistration collectionProtocolRegistration = (CollectionProtocolRegistration) cprIter
                    .next();

            final CollectionProtocol collectionProtocol = collectionProtocolRegistration.getCollectionProtocol();
            final CollectionProtocol cp = CollectionProtocolFactory.getInstance().createObject();
            cp.setActivityStatus(collectionProtocol.getActivityStatus());
            cp.setTitle(collectionProtocol.getTitle());
            cp.setShortTitle(collectionProtocol.getShortTitle());

            final CollectionProtocolRegistration cpr = CollectionProtocolRegistrationFactory.getInstance()
                    .createObject();
            cpr.setParticipant(p);
            cpr.setConsentSignatureDate(collectionProtocolRegistration.getConsentSignatureDate());
            cpr.setRegistrationDate(collectionProtocolRegistration.getRegistrationDate());
            cpr.setProtocolParticipantIdentifier(collectionProtocolRegistration.getProtocolParticipantIdentifier());

            cpr.setCollectionProtocol(cp);
            p.getCollectionProtocolRegistrationCollection().add(cpr);
        }
        return p;
    }

    /**
     * 
     * @param caTissueAPIClient CaTissueAPIClientWithRegularAuthentication
     */
    public void setCaTissueAPIClient(CaTissueAPIClientWithRegularAuthentication caTissueAPIClient) {
        this.caTissueAPIClient = caTissueAPIClient;
    }

}
