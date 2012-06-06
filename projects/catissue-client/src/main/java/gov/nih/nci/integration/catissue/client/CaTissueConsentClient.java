package gov.nih.nci.integration.catissue.client;

import edu.wustl.catissuecore.domain.AbstractSpecimen;
import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.ConsentTier;
import edu.wustl.catissuecore.domain.ConsentTierStatus;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.domain.Specimen;
import gov.nih.nci.integration.catissue.domain.ConsentData;
import gov.nih.nci.integration.catissue.domain.ConsentDetail;
import gov.nih.nci.integration.catissue.domain.Consents;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * This is the Client class to perform Consent Related Operations (RegisterConsent, RollbackConsent etc).
 * 
 * @author Rohit Gupta
 */

public class CaTissueConsentClient {

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueConsentClient.class);
    private final CaTissueAPIClientWithRegularAuthentication caTissueAPIClient;
    private XStream xStream = new XStream(new StaxDriver());

    /**
     * Constructor
     * @param loginName - loginName for the API authentication 
     * @param password - password for the API authentication 
     * @throws Exception - Exception
     */
    public CaTissueConsentClient(String loginName, String password) throws Exception {
        super();
        Thread.currentThread().setContextClassLoader(CaTissueConsentClient.class.getClassLoader());
        this.caTissueAPIClient = new CaTissueAPIClientWithRegularAuthentication(loginName, password);

        xStream.alias("consents", Consents.class);
        xStream.alias("participant", Participant.class);
        xStream.alias("consentDetails", ConsentDetail.class);
        xStream.alias("consentData", ConsentData.class);
        xStream.alias("collectionProtocol", CollectionProtocol.class);
        xStream.alias("consentTierStatus", ConsentTierStatus.class);
        xStream.alias("consentTier", ConsentTier.class);
        xStream.addImplicitCollection(ConsentData.class, "consentTierStatusSet");
        xStream.addImplicitCollection(Consents.class, "consentDetailsList");
    }

    /**
     * This method is used to fetch the existing Consents
     * 
     * @param consentsListXMLStr - XMLString containing the list of consents for which data is to be fetched
     * @return String - XMLString representation of the retrieved data
     * @throws ApplicationException - if any exception occurred during data retrieval
     */
    public String getExistingConsents(String consentsListXMLStr) throws ApplicationException {

        LOG.debug("Inside getExistingConsentsData...The Incoming XML for getExistingConsentsData() is --> "
                + consentsListXMLStr);

        // Parse the incoming XML String. The returned object will contain data
        // from the incoming consents XML
        Consents incomingConsents = parseConsentsListXML(consentsListXMLStr);

        // Fetch the existing Consents
        Consents exitingConsents = fetchExistingConsents(incomingConsents);

        return xStream.toXML(exitingConsents);
    }

    /**
     * This method is used Register the Consents
     * 
     * @param consentsListXMLStr - XMLString containing the list of consents for which registration has to be done
     * @return String - currently returning null
     * @throws ApplicationException - if any exception occurred during data insertion
     */
    public String registerConsents(String consentsListXMLStr) throws ApplicationException {
        LOG.debug("Inside CaTissueConsentClient...The Incoming XML for registerConsents() is --> " + consentsListXMLStr);

        // Parse the incoming XML String. The returned object will contain data
        // from the incoming specimens XML
        Consents incomingConsents = parseConsentsListXML(consentsListXMLStr);

        // perform the actual logic to register the Consents.. Also do the
        // rollback, if required
        performRegisterConsents(incomingConsents);

        // Returning NULL at the moment.
        return null;
    }

    /**
     * This method is used to Rollback the Consents
     * 
     * @param consentsListXMLStr - XMLString containing the specimens/consents to be rollback
     * @throws ApplicationException - if any exception occurred during rollback itself
     */
    public void rollbackConsentRegistration(String consentsListXMLStr) throws ApplicationException {
        LOG.debug("Inside rollbackConsentRegistration...The Incoming XML for rollbackConsentRegistration() is --> "
                + consentsListXMLStr);

        // Parse the incoming XML String. The returned object will contain data
        // from the incoming specimens XML
        Consents incomingConsents = parseConsentsListXML(consentsListXMLStr);

        // rollback the consents
        performRollbackConsentRegistration(incomingConsents);
    }

    /**
     * This method will fetch the existing consents which will be used incase of rollback
     * 
     * @param incomingConsents
     * @return
     * @throws ApplicationException
     */
    private Consents fetchExistingConsents(Consents incomingConsents) throws ApplicationException {
        Consents existingConsents = new Consents();
        List<ConsentDetail> exitingConsentDetailsList = new ArrayList<ConsentDetail>();
        Iterator<ConsentDetail> incomingConsentDetailItr = incomingConsents.getConsentsDetailsList().iterator();
        while (incomingConsentDetailItr.hasNext()) {
            ConsentDetail consentDetail = incomingConsentDetailItr.next();
            ConsentDetail existingConsentDetail = new ConsentDetail();
            String specimenLabel = consentDetail.getConsentData().getSpecimenLabel().trim();
            Specimen existingSpecimen = getExistingSpecimen(specimenLabel);
            if (existingSpecimen == null) {
                LOG.error("Specimen for given LABEL " + specimenLabel + " doesn't exist.");
                throw new ApplicationException("Specimen for given LABEL doesn't exist");
            }
            existingConsentDetail.setConsentData(getConsentData(existingSpecimen));
            exitingConsentDetailsList.add(existingConsentDetail);
        }
        existingConsents.setConsentsDetailsList(exitingConsentDetailsList);
        return existingConsents;
    }

    /**
     * This method will register the Consents in caTissue
     * 
     * @param consents
     * @throws ApplicationException
     */
    private void performRegisterConsents(Consents consents) throws ApplicationException {
        List<ConsentDetail> consentDetailList = consents.getConsentsDetailsList();
        Iterator<ConsentDetail> consentDetailItr = null;
        Specimen existingSpecimen = null;
        try {
            for (consentDetailItr = consentDetailList.iterator(); consentDetailItr.hasNext();) {
                ConsentDetail consentDetail = consentDetailItr.next();
                existingSpecimen = getExistingSpecimen(consentDetail.getConsentData().getSpecimenLabel().trim());
                // populate the tierId for given 'statement' inside consentDetail
                consentDetail = populateConsentTierId(consentDetail);
                // set the collection
                existingSpecimen.setConsentTierStatusCollection(consentDetail.getConsentData()
                        .getConsentTierStatusSet());
                // and then update the specimen
                updateSpecimen(existingSpecimen);

                // update the child specimen(s) now
                performRegisterConsentsForChildSpecimens(existingSpecimen, consentDetail);
            }
        } catch (ApplicationException ae) {
            LOG.error("Register Consent Failed for Specimen" + existingSpecimen.getLabel(), ae);
            throw new ApplicationException("Register Consent Failed for Specimen" + existingSpecimen.getLabel()
                    + " and exception is " + ae.getCause() + ae.getMessage());
        }
    }

    private void performRegisterConsentsForChildSpecimens(Specimen existingSpecimen, ConsentDetail consentDetail)
            throws ApplicationException {
        Collection<AbstractSpecimen> childSpecimenCollection = existingSpecimen.getChildSpecimenCollection();
        Iterator<AbstractSpecimen> itrChildSpecimen = childSpecimenCollection.iterator();
        while (itrChildSpecimen.hasNext()) {
            Specimen childSpecimen = (Specimen) itrChildSpecimen.next();
            childSpecimen.setConsentTierStatusCollection(consentDetail.getConsentData().getConsentTierStatusSet());
            updateSpecimen(childSpecimen);

            // do it recursively for child specimen
            /*
             * Currently this is working only when Main Specimen has 1 level of child specimen. If the child specimen
             * further has child specimens, then the below code doesn't work properly. This is a known issue at the
             * moment and the KC link is https://cabig-kc.nci. nih.gov/Biospecimen/forums/viewtopic.php?f=19
             * &t=1600&sid=005c36d237d7af0ef232dc5b1daecb31
             */
            performRegisterConsentsForChildSpecimens(childSpecimen, consentDetail);
        }
    }

    /**
     * This method is used to populate the 'consent_tier_id' based on the 'Statement'
     * 
     * @param consentDetail
     * @return
     * @throws ApplicationException
     */
    private ConsentDetail populateConsentTierId(ConsentDetail consentDetail) throws ApplicationException {
        Set<ConsentTierStatus> conTierStatusSet = consentDetail.getConsentData().getConsentTierStatusSet();
        Iterator<ConsentTierStatus> itrTierStatus = conTierStatusSet.iterator();
        // iterate thru all the consentTierStatus's statement
        while (itrTierStatus.hasNext()) {
            boolean isTierIdFound = false;
            ConsentTierStatus tierStatus = itrTierStatus.next();
            String stmt = tierStatus.getConsentTier().getStatement();
            // get the CollectionProtocol and then its consentTierCollection
            CollectionProtocol cp = getExistingCollectionProtocol(consentDetail.getCollectionProtocol().getTitle());
            if (cp != null) {
                Collection<ConsentTier> consentTierCollection = cp.getConsentTierCollection();
                Iterator<ConsentTier> itrConsentTier = consentTierCollection.iterator();
                // iterate thru each consentTier and compare for the statement..
                // if it matches- get its corresponding Id
                while (itrConsentTier.hasNext()) {
                    ConsentTier consentTier = itrConsentTier.next();
                    if (stmt.equalsIgnoreCase(consentTier.getStatement())) {
                        tierStatus.getConsentTier().setId(consentTier.getId());
                        isTierIdFound = true;
                        break;
                    }
                }
            } else {
                // i.e collection protocol not found is caTissue
                LOG.error("populateConsentTierId failed as Collection Protocol was not found in caTissue for the identifier "
                        + consentDetail.getCollectionProtocol().getTitle());
                throw new ApplicationException("Collection Protocol was not found in caTissue");
            }

            if (!isTierIdFound) {
                // i.e tierId not found for given CollectionProtocol & Statement
                // combination
                LOG.error("populateConsentTierId failed as ConsentTier Statement was not found for given CollectionProtocol "
                        + consentDetail.getCollectionProtocol().getTitle() + "in caTissue.");
                throw new ApplicationException(
                        "ConsentTier Statement was not found for given CollectionProtocol in caTissue");
            }
        }

        return consentDetail;
    }

    private CollectionProtocol getExistingCollectionProtocol(String title) throws ApplicationException {
        CollectionProtocol cp = new CollectionProtocol();
        cp.setTitle(title);
        cp = caTissueAPIClient.searchById(CollectionProtocol.class, cp);
        return cp;
    }

    /**
     * This method will rollback the Consents
     * 
     * @param consents
     * @throws ApplicationException
     */
    private void performRollbackConsentRegistration(Consents consents) throws ApplicationException {
        List<ConsentDetail> consentDetailList = consents.getConsentsDetailsList();
        Iterator<ConsentDetail> consentDetailItr = null;
        Specimen existingSpecimen = null;
        ConsentDetail consentDetail = null;
        try {
            for (consentDetailItr = consentDetailList.iterator(); consentDetailItr.hasNext();) {
                consentDetail = consentDetailItr.next();
                existingSpecimen = getExistingSpecimen(consentDetail.getConsentData().getSpecimenLabel());
                existingSpecimen.setConsentTierStatusCollection(consentDetail.getConsentData()
                        .getConsentTierStatusSet());
                updateSpecimen(existingSpecimen);
                performRollbackConsentForChildSpecimens(existingSpecimen, consentDetail);
            }
        } catch (ApplicationException ae) {
            // code for handling the exception
            LOG.error("Exception During Rollback of Consent with SpecimenLabel as "
                    + consentDetail.getConsentData().getSpecimenLabel(), ae);
            throw new ApplicationException("Error occurred : Unable to rollback. Please check the logs.");
        }
    }

    private void performRollbackConsentForChildSpecimens(Specimen existingSpecimen, ConsentDetail consentDetail)
            throws ApplicationException {
        try {
            Collection<AbstractSpecimen> childSpecimenCollection = existingSpecimen.getChildSpecimenCollection();

            Iterator<AbstractSpecimen> itrChildSpecimen = childSpecimenCollection.iterator();
            while (itrChildSpecimen.hasNext()) {
                Specimen childSpecimen = (Specimen) itrChildSpecimen.next();
                childSpecimen.setConsentTierStatusCollection(consentDetail.getConsentData().getConsentTierStatusSet());
                updateSpecimen(childSpecimen);
                // do it recursively
                performRollbackConsentForChildSpecimens(childSpecimen, consentDetail);
            }
            // CHECKSTYLE:OFF
        } catch (Exception e) {
            // CHECKSTYLE:ON
            LOG.error("Exception During Rollback of Consent for ChildSpecimen with SpecimenLabel as "
                    + consentDetail.getConsentData().getSpecimenLabel(), e);
            throw new ApplicationException("Rollback Consent Failed for ChildSpecimen "
                    + consentDetail.getConsentData().getSpecimenLabel() + " and exception is " + e.getCause());
        }

    }

    /**
     * This method is used to parse the incoming XML string and populate the 'Consents' object
     * 
     * @param specimenListXMLStr
     * @return
     */
    private Consents parseConsentsListXML(String consentsListXMLStr) {
        Consents consents = (Consents) xStream.fromXML(new StringReader(consentsListXMLStr));
        return consents;
    }

    /**
     * This method is used to fetch the ConsentData for given Specimen
     * 
     * @param existingSpecimen
     * @return
     */
    private ConsentData getConsentData(Specimen existingSpecimen) {
        ConsentData existingConsentData = new ConsentData();
        Set<ConsentTierStatus> existingTierStatusCollection = new LinkedHashSet<ConsentTierStatus>();
        Collection<ConsentTierStatus> consentTierStatusCollection = existingSpecimen.getConsentTierStatusCollection();

        if (consentTierStatusCollection != null) {
            Iterator<ConsentTierStatus> itr = consentTierStatusCollection.iterator();
            while (itr.hasNext()) {
                ConsentTierStatus tierStatus = itr.next();
                ConsentTierStatus exitingTierStatus = new ConsentTierStatus();
                exitingTierStatus.setStatus(tierStatus.getStatus());
                ConsentTier exitingConsentTier = new ConsentTier();
                exitingConsentTier.setId(tierStatus.getConsentTier().getId());
                exitingConsentTier.setStatement(tierStatus.getConsentTier().getStatement());
                exitingTierStatus.setConsentTier(exitingConsentTier);
                existingTierStatusCollection.add(exitingTierStatus);
            }
            existingConsentData.setConsentTierStatusSet(existingTierStatusCollection);
            existingConsentData.setSpecimenLabel(existingSpecimen.getLabel());
        }

        return existingConsentData;
    }

    private Specimen updateSpecimen(Specimen specimen) throws ApplicationException {
        return caTissueAPIClient.update(specimen);
    }

    /**
     * This method is used to get a specimen on the basis of the Label
     * 
     * @param label
     * @return
     * @throws ApplicationException
     */
    private Specimen getExistingSpecimen(String label) throws ApplicationException {
        Specimen specimen = new Specimen();
        specimen.setLabel(label);// set the cdmsSpecimenId
        // get the specimen, corresponding to the cdmsSpecimenId
        specimen = caTissueAPIClient.searchById(Specimen.class, specimen);
        return specimen;
    }

}
