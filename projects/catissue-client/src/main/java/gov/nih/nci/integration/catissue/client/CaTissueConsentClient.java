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
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * This is the Client class to perform Consent Status Update Related Operations (RegisterConsent, RollbackConsent etc).
 * 
 * @author Rohit Gupta
 */

public class CaTissueConsentClient {

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueConsentClient.class);
    private CaTissueAPIClientWithRegularAuthentication caTissueAPIClient;
    private final XStream xStream = new XStream(new StaxDriver());
    private static final String XLS_TRANSFORMATION_EXP = "Exception occurred while XSL transformation.";

    /**
     * Constructor
     * 
     * @param loginName - loginName for the API authentication
     * @param password - password for the API authentication
     * @throws MalformedURLException - MalformedURLException
     * @throws BeansException - BeansException
     */
    public CaTissueConsentClient(String loginName, String password) throws BeansException, MalformedURLException {
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

        // Parse the incoming XML String. The returned object will contain data
        // from the incoming consents XML
        final Consents incomingConsents = parseConsentsListXML(consentsListXMLStr);

        // Fetch the existing Consents
        final Consents exitingConsents = fetchExistingConsents(incomingConsents);

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

        // Parse the incoming XML String. The returned object will contain data
        // from the incoming specimens XML
        final Consents incomingConsents = parseConsentsListXML(consentsListXMLStr);

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

        // Parse the incoming XML String. The returned object will contain data
        // from the incoming specimens XML
        final Consents incomingConsents = parseConsentsListXML(consentsListXMLStr);

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
        final Consents existingConsents = new Consents();
        final List<ConsentDetail> exitingConsentDetailsList = new ArrayList<ConsentDetail>();
        final Iterator<ConsentDetail> incomingConsentDetailItr = incomingConsents.getConsentsDetailsList().iterator();
        while (incomingConsentDetailItr.hasNext()) {
            final ConsentDetail consentDetail = incomingConsentDetailItr.next();
            final ConsentDetail existingConsentDetail = new ConsentDetail();
            final String specimenLabel = consentDetail.getConsentData().getSpecimenLabel().trim();
            final Specimen existingSpecimen = getExistingSpecimen(specimenLabel);
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
        final List<ConsentDetail> consentDetailList = consents.getConsentsDetailsList();
        Iterator<ConsentDetail> consentDetailItr = null;
        Specimen existingSpecimen = null;
        try {
            for (consentDetailItr = consentDetailList.iterator(); consentDetailItr.hasNext();) {
                ConsentDetail consentDetail = consentDetailItr.next();
                existingSpecimen = getExistingSpecimen(consentDetail.getConsentData().getSpecimenLabel().trim());

                // populate the tierId for given 'statement' inside consentDetail
                consentDetail = populateConsentInfo(consentDetail, existingSpecimen);
                // set the ConsentTierStatusCollection to main/parent specimen
                existingSpecimen.setConsentTierStatusCollection(consentDetail.getConsentData()
                        .getConsentTierStatusSet());

                // set the consentTierCollection for child specimens
                populateConsentTierStatusCollectionforChildSpecimens(existingSpecimen, consentDetail);

                // and then update the specimen
                updateSpecimen(existingSpecimen);

                // Currently the consent status of only parent specimen is updated and not child specimen(s). This is a
                // known issue. The issue like is https://tracker.nci.nih.gov/browse/IHUB-221
            }
        } catch (ApplicationException ae) {
            LOG.error("Register Consent Failed for Specimen", ae);
            throw new ApplicationException("Register Consent Failed for Specimen"
                    + " and exception is " + ae.getCause() + ae.getMessage(), ae);
        }
    }

    /**
     * This method is used to set the consentTierStatusCollection to the child specimens of the given parent specimen
     */
    private void populateConsentTierStatusCollectionforChildSpecimens(Specimen parentSpecimen,
            ConsentDetail consentDetail) throws ApplicationException {
        final Collection<AbstractSpecimen> childSpecimenCollection = parentSpecimen.getChildSpecimenCollection();
        if (childSpecimenCollection != null) {
            final Iterator<AbstractSpecimen> itrChildSpecimen = childSpecimenCollection.iterator();
            while (itrChildSpecimen.hasNext()) {
                final Specimen childSpecimen = (Specimen) itrChildSpecimen.next();
                childSpecimen.setConsentTierStatusCollection(consentDetail.getConsentData().getConsentTierStatusSet());
                populateConsentTierStatusCollectionforChildSpecimens(childSpecimen, consentDetail);
            }
        }
    }

    /**
     * This method is used to populate the 'consent_tier_id' based on the 'Statement'
     * 
     * @param consentDetail
     * @existingSpecimen existingSpecimen
     * @return
     * @throws ApplicationException
     */
    private ConsentDetail populateConsentInfo(ConsentDetail consentDetail, Specimen existingSpecimen)
            throws ApplicationException {
        final Set<ConsentTierStatus> conTierStatusSet = consentDetail.getConsentData().getConsentTierStatusSet();
        final Iterator<ConsentTierStatus> itrTierStatus = conTierStatusSet.iterator();
        
        final Collection<ConsentTier> consentTierCollection = existingSpecimen.getSpecimenCollectionGroup()
                .getCollectionProtocolEvent().getCollectionProtocol().getConsentTierCollection();
        final Collection<ConsentTierStatus> existingSpcmnCTSCol = existingSpecimen.getConsentTierStatusCollection();
        
        if (consentTierCollection == null || consentTierCollection.isEmpty()) {            
            LOG.error("populateConsentTierId failed as ConsentTier Statement was not found for given CollectionProtocol "
                    + consentDetail.getCollectionProtocol().getShortTitle() + "in caTissue.");
            throw new ApplicationException(
                    "ConsentTier Statement was not found for given CollectionProtocol in caTissue");
        }
        
        // iterate thru all the consentTierStatus's statement
        while (itrTierStatus.hasNext()) {      
            
            final ConsentTierStatus tierStatus = itrTierStatus.next();          
            
            if (!handleConsentTierStatusForStatement(consentTierCollection, tierStatus, existingSpcmnCTSCol)) {
                // i.e tierId not found for given CollectionProtocol & Statement
                // combination
                LOG.error("populateConsentTierId failed as ConsentTier Statement was not found for given CollectionProtocol "
                        + consentDetail.getCollectionProtocol().getShortTitle() + "in caTissue.");
                throw new ApplicationException(
                        "ConsentTier Statement was not found for given CollectionProtocol in caTissue");
            }
            
        } //end of outer while        

        return consentDetail;
    }

    private boolean handleConsentTierStatusForStatement(final Collection<ConsentTier> consentTierCollection,
            final ConsentTierStatus tierStatus, Collection<ConsentTierStatus> existingSpcmnCTSCol) {
        final Iterator<ConsentTier> itrConsentTier = consentTierCollection.iterator();
        // iterate thru each consentTier and compare for the statement..
        // if it matches- get its corresponding Id
        boolean isTierIdFound = false;
        while (itrConsentTier.hasNext()) {
            final ConsentTier consentTier = itrConsentTier.next();
            if (tierStatus.getConsentTier().getStatement().equalsIgnoreCase(consentTier.getStatement())) {
                tierStatus.getConsentTier().setId(consentTier.getId());
                
                populateStatus(tierStatus, existingSpcmnCTSCol);
                
                isTierIdFound = true;
                break;
            }
        }
        return isTierIdFound;
    }

    private void populateStatus(final ConsentTierStatus tierStatus, Collection<ConsentTierStatus> existingSpcmnCTSCol) {
        //Setting status as 'Withdrawn' only if incoming status is 'No'
        //and the existing status is 'Yes'
        for (ConsentTierStatus existingCTS : existingSpcmnCTSCol) {
          if (tierStatus.getConsentTier().getId().equals(existingCTS.getConsentTier().getId())
                  && "No".equals(tierStatus.getStatus())
                  &&  "Yes".equals(existingCTS.getStatus()) ) {                              
              tierStatus.setStatus("Withdrawn");
          }
        }
    }
    
    

    /**
     * This method will rollback the Consents
     * 
     * @param consents
     * @throws ApplicationException
     */
    private void performRollbackConsentRegistration(Consents consents) throws ApplicationException {
        final List<ConsentDetail> consentDetailList = consents.getConsentsDetailsList();
        Iterator<ConsentDetail> consentDetailItr = null;
        Specimen existingSpecimen = null;
        ConsentDetail consentDetail = null;
        try {
            for (consentDetailItr = consentDetailList.iterator(); consentDetailItr.hasNext();) {
                consentDetail = consentDetailItr.next();
                existingSpecimen = getExistingSpecimen(consentDetail.getConsentData().getSpecimenLabel());
                // populate the tierId for given 'statement' inside consentDetail
                consentDetail = populateConsentInfo(consentDetail, existingSpecimen);
                // set the ConsentTierStatusCollection to main/parent specimen
                existingSpecimen.setConsentTierStatusCollection(consentDetail.getConsentData()
                        .getConsentTierStatusSet());

                // set the consentTierCollection for child specimens
                populateConsentTierStatusCollectionforChildSpecimens(existingSpecimen, consentDetail);

                // and then update the specimen
                updateSpecimen(existingSpecimen);

            }
        } catch (ApplicationException ae) {
            // code for handling the exception
            LOG.error("Exception During Rollback of Consent with SpecimenLabel as "
                    + consentDetail.getConsentData().getSpecimenLabel(), ae);
            throw new ApplicationException("Error occurred : Unable to rollback. Please check the logs.", ae);
        }
    }

    /**
     * This method is used to parse the incoming XML string and populate the 'Consents' object
     * 
     * @param specimenListXMLStr
     * @return
     */
    private Consents parseConsentsListXML(String consentsListXMLStr) throws ApplicationException {
        Consents consents = null;
        try {
            consents = (Consents) xStream.fromXML(new StringReader(consentsListXMLStr));
            // CHECKSTYLE:OFF
        } catch (Exception e) { // NOPMD
            // CHECKSTYLE:ON
            LOG.error(XLS_TRANSFORMATION_EXP + e.getCause(), e);
            throw new ApplicationException(XLS_TRANSFORMATION_EXP + e.getCause(), e);
        }
        return consents;
    }

    /**
     * This method is used to fetch the ConsentData for given Specimen
     * 
     * @param existingSpecimen
     * @return
     */
    private ConsentData getConsentData(Specimen existingSpecimen) {
        final ConsentData existingConsentData = new ConsentData();
        final Set<ConsentTierStatus> existingTierStatusCollection = new LinkedHashSet<ConsentTierStatus>();
        final Collection<ConsentTierStatus> consentTierStatusCollection = existingSpecimen
                .getConsentTierStatusCollection();

        if (consentTierStatusCollection != null) {
            final Iterator<ConsentTierStatus> itr = consentTierStatusCollection.iterator();
            while (itr.hasNext()) {
                final ConsentTierStatus tierStatus = itr.next();
                final ConsentTierStatus exitingTierStatus = new ConsentTierStatus();
                exitingTierStatus.setStatus(tierStatus.getStatus());
                final ConsentTier exitingConsentTier = new ConsentTier();
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

    /**
     * 
     * @param caTissueAPIClient CaTissueAPIClientWithRegularAuthentication
     */
    public void setCaTissueAPIClient(CaTissueAPIClientWithRegularAuthentication caTissueAPIClient) {
        this.caTissueAPIClient = caTissueAPIClient;
    }

}
