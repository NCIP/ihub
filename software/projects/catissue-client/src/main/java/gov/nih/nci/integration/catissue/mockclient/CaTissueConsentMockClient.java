package gov.nih.nci.integration.catissue.mockclient;

import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.ConsentTier;
import edu.wustl.catissuecore.domain.ConsentTierStatus;
import edu.wustl.catissuecore.domain.Participant;
import gov.nih.nci.integration.catissue.client.CaTissueAPIClientWithRegularAuthentication;
import gov.nih.nci.integration.catissue.domain.ConsentData;
import gov.nih.nci.integration.catissue.domain.ConsentDetail;
import gov.nih.nci.integration.catissue.domain.Consents;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.net.MalformedURLException;

import org.springframework.beans.BeansException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * This is the 'Mock' client class for Register Consent Flow. This class is used to Mock tests only and should NOT be
 * used for the 'Real Code'.
 * 
 * @author Rohit Gupta
 */

public class CaTissueConsentMockClient {

    private CaTissueAPIClientWithRegularAuthentication caTissueAPIClient; // NOPMD
    private final XStream xStream = new XStream(new StaxDriver());

    /**
     * Constructor
     * 
     * @param loginName - loginName for the API authentication
     * @param password - password for the API authentication
     * @throws MalformedURLException - MalformedURLException
     * @throws BeansException - BeansException
     */
    public CaTissueConsentMockClient(String loginName, String password) throws BeansException, MalformedURLException {
        super();
        Thread.currentThread().setContextClassLoader(CaTissueConsentMockClient.class.getClassLoader());
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
        return consentsListXMLStr;
    }

    /**
     * This method is used Register the Consents
     * 
     * @param consentsListXMLStr - XMLString containing the list of consents for which registration has to be done
     * @return String - some dummy data - incoming string for now
     * @throws ApplicationException - if any exception occurred during data insertion
     */
    public String registerConsents(String consentsListXMLStr) throws ApplicationException {
        return consentsListXMLStr;
    }

    /**
     * This method is used to Rollback the Consents
     * 
     * @param consentsListXMLStr - XMLString containing the specimens/consents to be rollback
     * @throws ApplicationException - if any exception occurred during rollback itself
     */
    public void rollbackConsentRegistration(String consentsListXMLStr) throws ApplicationException {
        // no dummy code
    }

    /**
     * 
     * @param caTissueAPIClient CaTissueAPIClientWithRegularAuthentication
     */
    public void setCaTissueAPIClient(CaTissueAPIClientWithRegularAuthentication caTissueAPIClient) {
        this.caTissueAPIClient = caTissueAPIClient;
    }

}
