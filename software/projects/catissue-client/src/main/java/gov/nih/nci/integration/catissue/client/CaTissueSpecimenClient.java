package gov.nih.nci.integration.catissue.client;

import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.DisposalEventParameters;
import edu.wustl.catissuecore.domain.FluidSpecimen;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.domain.Specimen;
import edu.wustl.catissuecore.domain.SpecimenCharacteristics;
import edu.wustl.catissuecore.domain.SpecimenCollectionGroup;
import edu.wustl.catissuecore.domain.TissueSpecimen;
import edu.wustl.catissuecore.domain.User;
import edu.wustl.catissuecore.domain.deintegration.SpecimenRecordEntry;
import gov.nih.nci.dynext.guidance_for_breast_core_biopsy.GuidanceForBreastCoreBiopsy;
import gov.nih.nci.integration.catissue.domain.SpecimenDetail;
import gov.nih.nci.integration.catissue.domain.Specimens;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.io.StringReader;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * This is the client class for Specimen Flow. It provide operation like CreateSpecimen, UpdateSpecimen,RollbackSpecimen
 * 
 * @author Rohit Gupta
 */
public class CaTissueSpecimenClient {

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueSpecimenClient.class);
    private CaTissueAPIClientWithRegularAuthentication caTissueAPIClient;
    private final XStream xStream = new XStream(new StaxDriver());

    private static final String ACTIVITY_STATUS_DISABLED = "Disabled";
    private static final String TISSUE = "Tissue";
    private static final String FLUID = "Fluid";
    private static final String SPECIMEN_NOT_EXISTING = "SPECIMEN_NOT_EXISTING";

    /**
     * Constructor
     * 
     * @param loginName - loginName for the API authentication
     * @param password - password for the API authentication
     * @throws MalformedURLException - MalformedURLException
     * @throws BeansException - BeansException
     */
    public CaTissueSpecimenClient(String loginName, String password) throws BeansException, MalformedURLException {
        super();
        Thread.currentThread().setContextClassLoader(CaTissueSpecimenClient.class.getClassLoader());
        this.caTissueAPIClient = new CaTissueAPIClientWithRegularAuthentication(loginName, password);

        xStream.alias("specimens", Specimens.class);
        xStream.alias("participant", Participant.class);
        xStream.alias("specimenDetail", SpecimenDetail.class);
        xStream.alias("specimen", Specimen.class);
        xStream.alias("TissueSpecimen", TissueSpecimen.class);
        xStream.alias("FluidSpecimen", FluidSpecimen.class);
        xStream.alias("collectionProtocol", CollectionProtocol.class);
        xStream.alias("guidanceForBreastCoreBiopsy", GuidanceForBreastCoreBiopsy.class);
        xStream.addImplicitCollection(Specimens.class, "specimenDetailList");
    }

    /**
     * This method is used to check if Specimen(s) already exist in caTissue
     * 
     * @param specimenListXMLStr - XMLString containing list of specimens which has to be check if they are existing.
     * @return String as 'SPECIMEN_NOT_EXISTING' if specimen doesn't exist
     * @throws ApplicationException - Throws exception if Specimen already exist
     */
    public String isSpecimensExist(String specimenListXMLStr) throws ApplicationException {

        // Parse the incoming XML String. The returned object will contain data
        // from the incoming specimens XML
        final Specimens specimens = parseSpecimenListXML(specimenListXMLStr);

        return isSpecimensAlreadyExist(specimens);
    }

    /**
     * This method is used to fetch the Specimen(s) details for given specimen XMLString
     * 
     * @param specimenListXMLStr - XMLString containing list of specimens for which specimen details are to be fetched
     * @return XMLString for the retrieved specimens
     * @throws ApplicationException - if any exception occurred during data retrieval
     */
    public String getExistingSpecimens(String specimenListXMLStr) throws ApplicationException {

        // Parse the incoming XML String. The returned object will contain data
        // from the incoming consents XML
        final Specimens incomingSpecimens = parseSpecimenListXML(specimenListXMLStr);

        // Fetch the existing Consents
        final Specimens exitingSpecimens = fetchExistingSpecimens(incomingSpecimens);

        return xStream.toXML(exitingSpecimens);
    }

    /**
     * This method is used to create bio-specimens in caTissue
     * 
     * @param specimenListXMLStr - The XMLString for creating the bio-specimen which may contain multiple specimens.
     * @return String - currently returning null
     * @throws ApplicationException - if any exception occurred during data insertion
     */
    public String createSpecimens(String specimenListXMLStr) throws ApplicationException {

        // Parse the incoming XML String. The returned object will contain data
        // from the incoming specimens XML
        final Specimens specimens = parseSpecimenListXML(specimenListXMLStr);

        // perform the actual logic to create the Specimens..
        performCreateSpecimens(specimens);

        // Returning NULL at the moment.
        return null;
    }

    /**
     * This method is used to update bio-specimens in caTissue
     * 
     * @param specimenListXMLStr - The XML string for creating the specimens which may contain multiple specimens.
     * @return String - XMLString representation of updated specimens
     * @throws ApplicationException - if any exception occurred during data updation
     */
    public String updateSpecimens(String specimenListXMLStr) throws ApplicationException {

        // This object contain data from the incoming specimens xml
        final Specimens specimens = parseSpecimenListXML(specimenListXMLStr);

        // perform the actual logic to Updating the Specimens..
        final List<Specimen> updatedSpecimenList = performUpdateSpecimens(specimens);

        // Copy the exiting Specimen and return in the form of XML
        return xStream.toXML(copyFromExistingSpecimen(updatedSpecimenList));

    }

    /**
     * This method is used to Rollback the specimen changes for createSpecimen flow
     * 
     * @param specimenListXMLStr - XMLString containing the specimens to be rollback
     * @return String - currently returning null
     * @throws ApplicationException - if any exception occurred during rollback itself
     */
    public String rollbackCreatedSpecimens(String specimenListXMLStr) throws ApplicationException {

        // This object contain data from the incoming specimens xml
        final Specimens specimens = parseSpecimenListXML(specimenListXMLStr);

        // write a method which will rollback the things...
        performRollbackCreatedSpecimens(specimens);

        return null;
    }

    /**
     * This method is used to Rollback the specimen changes for updateSpecimen flow
     * 
     * @param specimenListXMLStr - XMLString containing the specimens to be rollback
     * @return String - currently returning null
     * @throws ApplicationException - if any exception occurred during rollback itself
     */
    public String rollbackUpdatedSpecimens(String specimenListXMLStr) throws ApplicationException {

        // This object contain data from the incoming specimens xml
        final Specimens specimens = parseSpecimenListXML(specimenListXMLStr);

        // write a method which will rollback the things...
        performRollbackUpdatedSpecimens(specimens);

        return null;
    }

    /**
     * This method is used to parse the incoming XML string and populate the 'Specimens' object
     * 
     * @param specimenListXMLStr
     * @return
     */
    private Specimens parseSpecimenListXML(String specimenListXMLStr) {
        return (Specimens) xStream.fromXML(new StringReader(specimenListXMLStr));
    }

    /**
     * This method is used to check if the specimens already exist
     * 
     * @param specimens
     * @throws ApplicationException if specimen already exist
     */
    private String isSpecimensAlreadyExist(Specimens specimens) throws ApplicationException {
        final List<SpecimenDetail> specimenDetailList = specimens.getSpecimenDetailList();
        final Iterator<SpecimenDetail> specimenDetailItr = specimenDetailList.iterator();
        while (specimenDetailItr.hasNext()) {
            final SpecimenDetail specimenDetail = specimenDetailItr.next();
            final String specimenLabel = specimenDetail.getSpecimen().getLabel();
            final Specimen existingSpecimen = getExistingSpecimen(specimenLabel);
            if (existingSpecimen != null) {
                LOG.error("Specimen with the same LABEL already exists for Specimen " + specimenLabel);
                throw new ApplicationException("Specimen with the same LABEL already exists.");
            }

        }
        return SPECIMEN_NOT_EXISTING;
    }

    /**
     * This method is used to fetch the existing specimen(s) for corresponding incoming specimen label. This will be
     * used incase rollback is required.
     * 
     * @param incomingSpecimens
     */
    private Specimens fetchExistingSpecimens(Specimens incomingSpecimens) throws ApplicationException {
        final List<Specimen> existingSpecimenList = new ArrayList<Specimen>();
        final List<SpecimenDetail> specimenDetailList = incomingSpecimens.getSpecimenDetailList();
        final Iterator<SpecimenDetail> specimenDetailItr = specimenDetailList.iterator();
        while (specimenDetailItr.hasNext()) {
            final SpecimenDetail specimenDetail = specimenDetailItr.next();
            final Specimen existingSpecimen = getExistingSpecimen(specimenDetail.getSpecimen().getLabel());
            if (existingSpecimen == null) {
                LOG.error("Specimen for given LABEL " + specimenDetail.getSpecimen().getLabel() + " doesn't exist.");
                throw new ApplicationException("Specimen for given LABEL doesn't exist.");
            }

            // check if the request data is correct by doing validation checks
            if (isUpdateSpecimenRequestDataValid(specimenDetail, existingSpecimen)) {
                existingSpecimenList.add(existingSpecimen);
            }
        }

        // copy the data from the list into 'Specimens' object -- to get rid of
        // Hibernate CGLib issue
        return copyFromExistingSpecimen(existingSpecimenList);
    }

    /**
     * This method has the code/logic to call the createSpecimen.
     * 
     * @param specimens to be created
     */
    private void performCreateSpecimens(Specimens specimens) throws ApplicationException {

        final List<SpecimenDetail> specimenDetailList = specimens.getSpecimenDetailList();
        Iterator<SpecimenDetail> specimenDetailItr = null;
        Specimen specimen = null;

        for (specimenDetailItr = specimenDetailList.iterator(); specimenDetailItr.hasNext();) {
            SpecimenDetail specimenDetail = null;
            specimenDetail = (SpecimenDetail) specimenDetailItr.next();
            final CollectionProtocol cp = specimenDetail.getCollectionProtocol();
            specimen = specimenDetail.getSpecimen();
            boolean scgFound = false;
            final List<SpecimenCollectionGroup> scgList = getSpecimenCollectionGroupList(specimenDetail);

            if (scgList != null && !scgList.isEmpty()) {
                for (SpecimenCollectionGroup scg : scgList) {
                    final CollectionProtocol cpObj = scg.getCollectionProtocolRegistration().getCollectionProtocol();
                    if (cpObj.getTitle().equals(cp.getTitle())) {
                        scgFound = true;
                        // Participant has a SpecimenCollectionGroup under the given protocol
                        specimen.setSpecimenCollectionGroup(scg);
                        break;
                    }
                }
            }

            if (!scgFound) {
                // throw exception
                LOG.error("Specimen Collection Group was not found in caTissue for Label " + specimen.getLabel());
                throw new ApplicationException("Specimen Collection Group not found in caTissue");
            }

            try {
                // populate GuidanceForBreastCoreBiopsy inside specimen object
                populateGuidanceForBreastCoreBiopsy(specimen, specimenDetail);
                // method call to createSpecimen in caTissue
                createSpecimen(specimen);
            } catch (ApplicationException e) {
                LOG.error("CreateSpecimen Failed for Label" + specimen.getLabel(), e);
                throw new ApplicationException("CreateSpecimen Failed for Label"
                        + specimenDetail.getSpecimen().getLabel() + " and exception is " + e.getCause(), e);
            }
        }
    }

    private Specimen createSpecimen(Specimen specimen) throws ApplicationException {
        return caTissueAPIClient.insert(specimen);
    }

    /**
     * This method is used to populate 'GuidanceForBreastCoreBiopsy object' inside 'Specimen object'
     * 
     * @return - Specimen with GuidanceForBreastCoreBiopsy populate, if present in the request
     */
    private Specimen populateGuidanceForBreastCoreBiopsy(Specimen specimen, SpecimenDetail specimenDetail) {
        final String biopsyType = specimenDetail.getGuidanceForBreastCoreBiopsy().getGuidanceForBreastCoreBiopsyType();
        // Check if biopsyType is not available in the request
        if (StringUtils.isBlank(biopsyType)) {
            return specimen;
        }

        final SpecimenRecordEntry sre = new SpecimenRecordEntry();
        final GuidanceForBreastCoreBiopsy gfbcb = new GuidanceForBreastCoreBiopsy();
        gfbcb.setGuidanceForBreastCoreBiopsyType(specimenDetail.getGuidanceForBreastCoreBiopsy()
                .getGuidanceForBreastCoreBiopsyType());
        gfbcb.setOtherText(specimenDetail.getGuidanceForBreastCoreBiopsy().getOtherText());
        gfbcb.setSpecimenRecordEntry_GuidanceForBreastCoreBiopsy(sre);

        final Collection<GuidanceForBreastCoreBiopsy> gfbcbCollection = new HashSet<GuidanceForBreastCoreBiopsy>();
        gfbcbCollection.add(gfbcb);

        sre.setGuidanceForBreastCoreBiopsyCollection(gfbcbCollection);
        sre.setSpecimen(specimen);

        final Collection<SpecimenRecordEntry> specimenRecordEntryCollection = new HashSet<SpecimenRecordEntry>();
        specimenRecordEntryCollection.add(sre);
        specimen.setSpecimenRecordEntryCollection(specimenRecordEntryCollection);

        return specimen;
    }

    /**
     * This method has the code/logic to Update the Specimens.
     * 
     * @throws Exception
     */
    private List<Specimen> performUpdateSpecimens(Specimens specimens) throws ApplicationException {

        final List<Specimen> updatedSpecimenList = new ArrayList<Specimen>();

        final List<SpecimenDetail> specimenDetailList = specimens.getSpecimenDetailList();
        Iterator<SpecimenDetail> specimenDetailItr = null;
        SpecimenDetail specimenDetail = null;

        try {
            for (specimenDetailItr = specimenDetailList.iterator(); specimenDetailItr.hasNext();) {
                specimenDetail = specimenDetailItr.next();
                final Specimen incomingSpecimen = specimenDetail.getSpecimen();

                // Get the corresponding existing Specimen using the Label
                final Specimen existingSpecimen = getExistingSpecimen(incomingSpecimen.getLabel());
                incomingSpecimen.setId(existingSpecimen.getId());
                // Specimen Collection Group is required.
                incomingSpecimen.setSpecimenCollectionGroup(existingSpecimen.getSpecimenCollectionGroup());
                // Lineage should not be changed while updating the specimen
                incomingSpecimen.setLineage(existingSpecimen.getLineage());
                incomingSpecimen.getSpecimenCharacteristics().setId(
                        existingSpecimen.getSpecimenCharacteristics().getId());
                // update GuidanceForBreastCoreBiopsy in caTissue
                updateGuidanceForBreastCoreBiopsy(existingSpecimen, specimenDetail);
                // update Specimen in caTissue
                updateSpecimen(incomingSpecimen);

                updatedSpecimenList.add(incomingSpecimen);
            }
        } catch (ApplicationException e) {
            LOG.error("UpdateSpecimen Failed for Label" + specimenDetail.getSpecimen().getLabel(), e);
            throw new ApplicationException("UpdateSpecimen Failed for Label" + specimenDetail.getSpecimen().getLabel()
                    + " and exception is " + e.getCause(), e);
        }

        return updatedSpecimenList;
    }

    /**
     * This method is used to check if the updateSpecimen request had valid data by comparing the values of incoming
     * specimen and existing specimen (doing check only for CP, CPE & SC).
     * 
     * @return
     */
    private boolean isUpdateSpecimenRequestDataValid(SpecimenDetail inSpecimenDetail, Specimen existingSpecimen)
            throws ApplicationException {
        boolean hasValidData = true;
        final String inCPE = inSpecimenDetail.getCollectionProtocolEvent();
        String existCPE = "";
        final String inCP = inSpecimenDetail.getCollectionProtocol().getTitle();
        String existCP = "";
        final String inSC = inSpecimenDetail.getSpecimen().getSpecimenClass();
        final String existSC = existingSpecimen.getSpecimenClass();

        if (existingSpecimen.getSpecimenCollectionGroup() != null) {
            existCPE = existingSpecimen.getSpecimenCollectionGroup().getCollectionProtocolEvent()
                    .getCollectionPointLabel();
            existCP = existingSpecimen.getSpecimenCollectionGroup().getCollectionProtocolEvent()
                    .getCollectionProtocol().getTitle();
        }

        if (!inCPE.equals(existCPE)) {
            hasValidData = false;
            LOG.error("UpdateSpecimen Request Failed for Label" + existingSpecimen.getLabel()
                    + " and exception is Collection Protocol Event can't be changed while updating the Specimen");
            throw new ApplicationException("Collection Protocol Event can't be changed while updating the Specimen");
        }

        if (!inCP.equals(existCP)) {
            hasValidData = false;
            LOG.error("UpdateSpecimen Request Failed for Label" + existingSpecimen.getLabel()
                    + " and exception is Collection Protocol can't be changed while updating the Specimen");
            throw new ApplicationException("Collection Protocol can't be changed while updating the Specimen");
        }

        if (!inSC.equals(existSC)) {
            hasValidData = false;
            LOG.error("UpdateSpecimen Request Failed for Label" + existingSpecimen.getLabel()
                    + " and exception is Specimen Class can't be changed while updating the Specimen");
            throw new ApplicationException("Specimen Class can't be changed while updating the Specimen");
        }

        return hasValidData;
    }

    /**
     * This method is used to update GuidanceForBreastCoreBiopsy for a Specimen in caTissue
     * 
     * @param existingSpecimen
     * @param specimenDetail
     * @throws ApplicationException
     */
    private void updateGuidanceForBreastCoreBiopsy(Specimen existingSpecimen, SpecimenDetail specimenDetail)
            throws ApplicationException {

        final String biopsyType = specimenDetail.getGuidanceForBreastCoreBiopsy().getGuidanceForBreastCoreBiopsyType();
        // If biopsyType is not available in the request, then no need to call update -- just return
        if (StringUtils.isBlank(biopsyType)) {
            return;
        }

        if (existingSpecimen.getSpecimenRecordEntryCollection() != null) {
            final Iterator<SpecimenRecordEntry> sreItr = existingSpecimen.getSpecimenRecordEntryCollection().iterator();
            while (sreItr.hasNext()) {
                final SpecimenRecordEntry sre = sreItr.next();
                if (sre.getGuidanceForBreastCoreBiopsyCollection() != null) {
                    final Iterator<GuidanceForBreastCoreBiopsy> gfbcbItr = sre
                            .getGuidanceForBreastCoreBiopsyCollection().iterator();
                    while (gfbcbItr.hasNext()) {
                        final GuidanceForBreastCoreBiopsy gfbcb = gfbcbItr.next();
                        // update the values with the incoming values
                        gfbcb.setGuidanceForBreastCoreBiopsyType(specimenDetail.getGuidanceForBreastCoreBiopsy()
                                .getGuidanceForBreastCoreBiopsyType());
                        gfbcb.setOtherText(specimenDetail.getGuidanceForBreastCoreBiopsy().getOtherText());
                        gfbcb.setSpecimenRecordEntry_GuidanceForBreastCoreBiopsy(sre);
                        // call update to update GuidanceForBreastCoreBiopsy in caTissue
                        caTissueAPIClient.update(sre);
                    }
                }
            }

        }

    }

    /**
     * This method will rollback the specimens for Update_Specimen flow
     * 
     * @param specimens
     */
    private void performRollbackUpdatedSpecimens(Specimens specimens) throws ApplicationException {
        final List<SpecimenDetail> specimenDetailList = specimens.getSpecimenDetailList();
        final Iterator<SpecimenDetail> specimenDetailItr = specimenDetailList.iterator();

        while (specimenDetailItr.hasNext()) {
            final SpecimenDetail specimenDetail = specimenDetailItr.next();
            final Specimen existingSpecimen = specimenDetail.getSpecimen();
            try {
                // rollback the GuidanceForBreastCoreBiopsy
                updateGuidanceForBreastCoreBiopsy(existingSpecimen, specimenDetail);
                // rollback the Specimen
                updateSpecimen(existingSpecimen);
            } catch (ApplicationException e) {
                LOG.error("Exception occured during Rollback of UpdateSpecimen " + existingSpecimen.getLabel(), e);
                throw new ApplicationException("Error occurred : Unable to rollback. Please check the logs.", e);
            }
        }

    }

    /**
     * This method is used to get a specimen on the basis of the Label
     * 
     * @param label
     */
    private Specimen getExistingSpecimen(String label) throws ApplicationException {
        Specimen specimen = new Specimen();
        specimen.setLabel(label);// set the cdmsSpecimenId
        // get the specimen, corresponding to the cdmsSpecimenId
        specimen = caTissueAPIClient.searchById(Specimen.class, specimen);
        return specimen;
    }

    private Specimen updateSpecimen(Specimen specimen) throws ApplicationException {
        return caTissueAPIClient.update(specimen);
    }

    private Specimens copyFromExistingSpecimen(List<Specimen> existingSpecimenList) {

        final Specimens existingSpecimens = new Specimens();
        Iterator<Specimen> specimenItr = null;
        for (specimenItr = existingSpecimenList.iterator(); specimenItr.hasNext();) {
            final Specimen existingSpecimen = specimenItr.next();
            Specimen specimen = null;
            if (TISSUE.equalsIgnoreCase(existingSpecimen.getSpecimenClass())) {
                specimen = new TissueSpecimen();
            } else if (FLUID.equalsIgnoreCase(existingSpecimen.getSpecimenClass())) {
                specimen = new FluidSpecimen();
            }

            specimen.setId(existingSpecimen.getId());
            specimen.setInitialQuantity(existingSpecimen.getInitialQuantity());
            specimen.setPathologicalStatus(existingSpecimen.getPathologicalStatus());
            specimen.setSpecimenClass(existingSpecimen.getSpecimenClass().trim());
            specimen.setSpecimenType(existingSpecimen.getSpecimenType());
            specimen.setActivityStatus(existingSpecimen.getActivityStatus());
            specimen.setAvailableQuantity(existingSpecimen.getAvailableQuantity());
            specimen.setBarcode(existingSpecimen.getBarcode());
            specimen.setLabel(existingSpecimen.getLabel());
            final SpecimenCollectionGroup specimenCollectionGroup = new SpecimenCollectionGroup();
            specimenCollectionGroup.setId(existingSpecimen.getSpecimenCollectionGroup().getId());
            specimen.setSpecimenCollectionGroup(specimenCollectionGroup);
            final SpecimenCharacteristics chars = new SpecimenCharacteristics();
            chars.setTissueSide(existingSpecimen.getSpecimenCharacteristics().getTissueSide());
            chars.setTissueSite(existingSpecimen.getSpecimenCharacteristics().getTissueSite());
            specimen.setSpecimenCharacteristics(chars);
            specimen.getSpecimenCharacteristics().setId(existingSpecimen.getSpecimenCharacteristics().getId());
            specimen.setLineage(existingSpecimen.getLineage());
            specimen.setIsAvailable(existingSpecimen.getIsAvailable());
            specimen.setCollectionStatus(existingSpecimen.getCollectionStatus());
            final SpecimenDetail specimenDetail = new SpecimenDetail();
            specimenDetail.setSpecimen(specimen);
            existingSpecimens.add(specimenDetail);
        }

        return existingSpecimens;
    }

    /**
     * This method will rollback the specimens for Create_Specimen flow
     * 
     * @param specimens
     */
    private void performRollbackCreatedSpecimens(Specimens specimens) throws ApplicationException {
        final List<SpecimenDetail> specimenDetailList = specimens.getSpecimenDetailList();
        final Iterator<SpecimenDetail> specimenDetailItr = specimenDetailList.iterator();
        while (specimenDetailItr.hasNext()) {
            final SpecimenDetail specimenDetail = specimenDetailItr.next();
            final String specimenLabel = specimenDetail.getSpecimen().getLabel();
            Specimen existingSpecimen = null;
            try {
                existingSpecimen = getExistingSpecimen(specimenLabel);
            } catch (ApplicationException e) {
                LOG.info("Specimen with label as " + specimenLabel + " not present in caTissue for Rollback ");
            }

            try {
                if (existingSpecimen != null) {
                    softDeleteSpecimen(existingSpecimen);
                }
            } catch (ApplicationException e) {
                LOG.error("Exception occured during Rollback of CreateSpecimen " + specimenLabel, e);
                throw new ApplicationException("Error occurred : Unable to rollback. Please check the logs.", e);
            }
        }
    }

    /**
     * This method is used to Soft delete the Created Specimen
     * 
     * @param existingSpecimen
     * @throws ApplicationException
     */
    private void softDeleteSpecimen(Specimen existingSpecimen) throws ApplicationException {
        // First change the Label of the Specimen to some dummy value..
        // like "Soft_Del_+ label/barcode +Timestamp"
        final Specimen updatedSpecimen = updateSpecimenLabel(existingSpecimen);

        // Then set the Specimen to Disabled
        final DisposalEventParameters disposalEventParameters = new DisposalEventParameters();
        updatedSpecimen.setActivityStatus(ACTIVITY_STATUS_DISABLED);
        disposalEventParameters.setSpecimen(updatedSpecimen);
        disposalEventParameters.setActivityStatus(ACTIVITY_STATUS_DISABLED);
        disposalEventParameters.setComment("Rollback the Specimen");
        disposalEventParameters.setReason("Rollback the Specimen");
        disposalEventParameters.setTimestamp(new Date());
        disposalEventParameters.setUser(getCaTissueAdminUser());

        caTissueAPIClient.insert(disposalEventParameters);
    }

    /**
     * This method is used to update the specimen label & barcode
     * 
     */
    private Specimen updateSpecimenLabel(Specimen specimen) throws ApplicationException {
        specimen.setLabel("Soft-Del__" + specimen.getLabel() + "__" + getCurrentDateTime());
        specimen.setBarcode("Soft-Del__" + specimen.getBarcode() + "__" + getCurrentDateTime());
        return updateSpecimen(specimen);
    }

    private String getCurrentDateTime() {
        final Calendar cal = Calendar.getInstance();
        final Locale locale = Locale.US;
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS", locale);
        return sdf.format(cal.getTime());

    }

    /**
     * This method is used to get a User on the basis of the LoginName
     * 
     * @return
     * @throws ApplicationException
     */
    private User getCaTissueAdminUser() throws ApplicationException {
        User user = new User();
        user.setLoginName("admin@admin.com");
        user = caTissueAPIClient.searchById(User.class, user);
        return user;
    }

    private List<SpecimenCollectionGroup> getSpecimenCollectionGroupList(SpecimenDetail specimenDetail)
            throws ApplicationException {
        final String shortTitle = specimenDetail.getCollectionProtocol().getShortTitle();
        final String label = specimenDetail.getCollectionProtocolEvent();
        return caTissueAPIClient.getApplicationService().query(
                CqlUtility.getSpecimenCollectionGroupListQuery(shortTitle, label));
    }

    /**
     * 
     * @param caTissueAPIClient CaTissueAPIClientWithRegularAuthentication
     */
    public void setCaTissueAPIClient(CaTissueAPIClientWithRegularAuthentication caTissueAPIClient) {
        this.caTissueAPIClient = caTissueAPIClient;
    }

}
