/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.catissue.mockclient;

import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.CollectionProtocolRegistration;
import edu.wustl.catissuecore.domain.Participant;
import edu.wustl.catissuecore.domain.ParticipantMedicalIdentifier;
import edu.wustl.catissuecore.domain.Race;
import gov.nih.nci.integration.catissue.client.CaTissueAPIClientWithRegularAuthentication;
import gov.nih.nci.system.applicationservice.ApplicationException;

import java.io.StringReader;
import java.net.MalformedURLException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * * This is the 'Mock' client class for Participant Flow. This class is used to Mock tests only and should not be used
 * for the 'Real Code'.
 * 
 * @author Rohit Gupta
 */
public class CaTissueParticipantMockClient {

    private CaTissueAPIClientWithRegularAuthentication caTissueAPIClient;
    private final XStream xStream = new XStream(new StaxDriver());

    /**
     * Constructor
     * 
     * @param loginName - loginName for the API authentication
     * @param password - password for the API authentication
     * @throws MalformedURLException - MalformedURLException
     * @throws BeansException - BeansException
     */
    public CaTissueParticipantMockClient(String loginName, String password) throws BeansException,
            MalformedURLException {
        super();
        Thread.currentThread().setContextClassLoader(CaTissueParticipantMockClient.class.getClassLoader());
        this.caTissueAPIClient = new CaTissueAPIClientWithRegularAuthentication(loginName, password);
        initXStream();
    }

    private void initXStream() {
        xStream.alias("participant", Participant.class);
        xStream.alias("race", Race.class);
        xStream.alias("collectionProtocol", CollectionProtocol.class);
        xStream.alias("collectionProtocolRegistration", CollectionProtocolRegistration.class);
        xStream.alias("participantMedicalIdentifier", ParticipantMedicalIdentifier.class);

        final String[] accFrmts = new String[] { "", "yyyyMMdd", "yyyy-MM-dd", "MM/dd/yyyy", "yyyy-MM-dd HH:mm:ss.S a",
                "yyyy-MM-dd HH:mm:ssz", "yyyy-MM-dd HH:mm:ss z", "yyyy-MM-dd HH:mm:ssa" };
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

    private Participant parseParticipant(String participantXMLStr) {
        return (Participant) xStream.fromXML(new StringReader(participantXMLStr));
    }

    /**
     * This method is used to register a Participant in caTissue
     * 
     * @param participantXMLStr - Participant information in the form of XMLString
     * @return XMLString - participantXMLStr.. some dummy data
     * @throws ApplicationException ApplicationException
     */
    public String registerParticipantFromXML(String participantXMLStr) throws ApplicationException {
        final Participant participant = parseParticipant(participantXMLStr);
        if (participant == null || StringUtils.isEmpty(participant.getLastName())) {
            throw new ApplicationException("Participant does not contain the unique medical identifier");
        }
        return participantXMLStr;
    }

    /**
     * This method is used to update a Participant in caTissue.
     * 
     * @param participantXMLStr - Participant update information in the form of XMLString
     * @return participantXMLStr.. some dummy data
     * @throws ApplicationException - ApplicationException
     */
    public String updateParticipantRegistrationFromXML(String participantXMLStr) throws ApplicationException {
        return participantXMLStr;
    }

    /**
     * This method is used to delete a Participant in caTissue.
     * 
     * @param participantXMLStr participant to be delete in the form of XMLString
     * @return true if participant is deleted
     * @throws ApplicationException - ApplicationException
     */
    public boolean deleteParticipantFromXML(String participantXMLStr) throws ApplicationException {
        return false;
    }

    /**
     * 
     * @param caTissueAPIClient CaTissueAPIClientWithRegularAuthentication
     */
    public void setCaTissueAPIClient(CaTissueAPIClientWithRegularAuthentication caTissueAPIClient) {
        this.caTissueAPIClient = caTissueAPIClient;
    }

}
