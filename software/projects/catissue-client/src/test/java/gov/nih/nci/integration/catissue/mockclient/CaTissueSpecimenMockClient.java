package gov.nih.nci.integration.catissue.mockclient;

import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.FluidSpecimen;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.domain.Specimen;
import edu.wustl.catissuecore.domain.TissueSpecimen;
import gov.nih.nci.integration.catissue.client.CaTissueAPIClientWithRegularAuthentication;
import gov.nih.nci.integration.catissue.domain.SpecimenDetail;
import gov.nih.nci.integration.catissue.domain.Specimens;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.net.MalformedURLException;

import org.springframework.beans.BeansException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * This is the 'Mock' client class for Specimen Flow. This class is used to Mock tests only and should not be used for
 * the 'Real Code'.
 * 
 * @author Rohit Gupta
 */
public class CaTissueSpecimenMockClient {

    private CaTissueAPIClientWithRegularAuthentication caTissueAPIClient; // NOPMD
    private final XStream xStream = new XStream(new StaxDriver());
    private static final String SPECIMEN_NOT_EXISTING = "SPECIMEN_NOT_EXISTING";

    /**
     * Constructor
     * 
     * @param loginName - loginName for the API authentication
     * @param password - password for the API authentication
     * @throws MalformedURLException - MalformedURLException
     * @throws BeansException - BeansException
     */
    public CaTissueSpecimenMockClient(String loginName, String password) throws BeansException, MalformedURLException {
        super();
        Thread.currentThread().setContextClassLoader(CaTissueSpecimenMockClient.class.getClassLoader());
        this.caTissueAPIClient = new CaTissueAPIClientWithRegularAuthentication(loginName, password);

        xStream.alias("specimens", Specimens.class);
        xStream.alias("participant", Participant.class);
        xStream.alias("specimenDetail", SpecimenDetail.class);
        xStream.alias("specimen", Specimen.class);
        xStream.alias("TissueSpecimen", TissueSpecimen.class);
        xStream.alias("FluidSpecimen", FluidSpecimen.class);
        xStream.alias("collectionProtocol", CollectionProtocol.class);
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
        if (specimenListXMLStr.contains("YES")) {
            throw new ApplicationException("Specimen with the same LABEL already exists.");
        } else {
            return SPECIMEN_NOT_EXISTING;
        }
    }

    /**
     * This method is used to fetch the Specimen(s) details for given specimen XMLString
     * 
     * @param specimenListXMLStr - XMLString containing list of specimens for which specimen details are to be fetched
     * @return XMLString for the retrieved specimens
     * @throws ApplicationException - if any exception occurred during data retrieval
     */
    public String getExistingSpecimens(String specimenListXMLStr) throws ApplicationException {
        return specimenListXMLStr;
    }

    /**
     * This method is used to create bio-specimens in caTissue
     * 
     * @param specimenListXMLStr - The XMLString for creating the bio-specimen which may contain multiple specimens.
     * @return String - some success message
     * */
    public String createSpecimens(String specimenListXMLStr) {
        return "SPECIMEN_CREATED_SUCCESSFULLY";
    }

    /**
     * This method is used to update bio-specimens in caTissue
     * 
     * @param specimenListXMLStr - The XML string for creating the specimens which may contain multiple specimens.
     * @return String - XMLString representation of updated specimens
     * @throws ApplicationException - if any exception occurred during data updation
     */
    public String updateSpecimens(String specimenListXMLStr) throws ApplicationException {
        return specimenListXMLStr;

    }

    /**
     * This method is used to Rollback the specimen changes for createSpecimen flow
     * 
     * @param specimenListXMLStr - XMLString containing the specimens to be rollback
     * @return String - currently returning null
     * @throws ApplicationException - if any exception occurred during rollback itself
     */
    public String rollbackCreatedSpecimens(String specimenListXMLStr) throws ApplicationException {
        return specimenListXMLStr;
    }

    /**
     * This method is used to Rollback the specimen changes for updateSpecimen flow
     * 
     * @param specimenListXMLStr - XMLString containing the specimens to be rollback
     * @return String - currently returning null
     * @throws ApplicationException - if any exception occurred during rollback itself
     */
    public String rollbackUpdatedSpecimens(String specimenListXMLStr) throws ApplicationException {
        return specimenListXMLStr;
    }

    /**
     * 
     * @param caTissueAPIClient CaTissueAPIClientWithRegularAuthentication
     */
    public void setCaTissueAPIClient(CaTissueAPIClientWithRegularAuthentication caTissueAPIClient) {
        this.caTissueAPIClient = caTissueAPIClient;
    }

}
