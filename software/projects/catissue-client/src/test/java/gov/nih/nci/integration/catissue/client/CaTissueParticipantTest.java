package gov.nih.nci.integration.catissue.client;

import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;

import org.easymock.classextension.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;

import edu.wustl.catissuecore.cacore.CaTissueWritableAppService;
import edu.wustl.catissuecore.domain.CollectionProtocol;
import edu.wustl.catissuecore.domain.Participant;
import gov.nih.nci.system.applicationservice.ApplicationException;

/**
 * This is the TestClass for Participant Registration flow.
 * 
 * @author Rohit Gupta
 */

public class CaTissueParticipantTest {

    private static final Logger LOG = LoggerFactory.getLogger(CaTissueParticipantTest.class);

    private CaTissueParticipantClient caTissueParticipantClient = null;
    private CaTissueAPIClientWithRegularAuthentication caTissueAPIClient = null;
    private CaTissueWritableAppService appService = null;

    /**
     * To initialize the things
     * 
     * @throws MalformedURLException - MalformedURLException
     * @throws BeansException - BeansException
     */
    @Test
    @Before
    public void initialize() throws BeansException, MalformedURLException {
        appService = org.easymock.EasyMock.createMock(CaTissueWritableAppService.class);
        caTissueAPIClient = EasyMock.createMock(CaTissueAPIClientWithRegularAuthentication.class);
        caTissueParticipantClient = new CaTissueParticipantClient("", "");
        caTissueParticipantClient.setCaTissueAPIClient(caTissueAPIClient);
    }

    /**
     * Mock Testcase for Register Participant
     */
    @SuppressWarnings("unchecked")
    @Test
    public void registerParticipant() {
        String retXMLString = "";
        final CollectionProtocol collectionProtocol = new CollectionProtocol();
        collectionProtocol.setTitle("6482");
        collectionProtocol.setShortTitle("6482");
        final Participant participant = new Participant();

        try {
            EasyMock.expect(caTissueAPIClient.getApplicationService()).andReturn(appService);
            EasyMock.expect(
                    caTissueAPIClient.searchById((Class<CollectionProtocol>) EasyMock.anyObject(),
                            (CollectionProtocol) org.easymock.EasyMock.anyObject())).andReturn(collectionProtocol);
            EasyMock.expect(caTissueAPIClient.insert((Participant) org.easymock.EasyMock.anyObject())).andReturn(
                    participant);
            EasyMock.replay(caTissueAPIClient);

            caTissueParticipantClient.registerParticipantFromXML(getParticipantXMLStr());

            retXMLString = "REGISTER_PARTICIPANT";
        } catch (ApplicationException e) {
            LOG.error("CaTissueParticipantTest-ApplicationException inside registerParticipant() ", e);
            retXMLString = "REGISTER_PARTICIPANT_FAILED";
        }
        assertNotNull(retXMLString);
    }

    // CHECKSTYLE:OFF

    private String getParticipantXMLStr() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <catissue:participant xmlns:p=\"http://integration.nci.nih.gov/participant\" xmlns:catissue=\"http://domain.catissuecore.wustl.edu/participant\" xmlns:g=\"http://catissue/gender/data\"> <catissue:activityStatus>Active</catissue:activityStatus> <catissue:birthDate>1965-11-24</catissue:birthDate> <catissue:ethnicity>Not Hispanic or Latino</catissue:ethnicity> <catissue:firstName>Cherry061501</catissue:firstName> <catissue:gender>Male Gender</catissue:gender> <catissue:lastName>488061501</catissue:lastName> <catissue:socialSecurityNumber>123-06-1501</catissue:socialSecurityNumber> <catissue:vitalStatus>Alive</catissue:vitalStatus> <catissue:collectionProtocolRegistrationCollection class=\"set\"> <catissue:collectionProtocolRegistration> <catissue:activityStatus>Active</catissue:activityStatus> <catissue:consentSignatureDate>2012-06-27</catissue:consentSignatureDate> <catissue:protocolParticipantIdentifier>488061501</catissue:protocolParticipantIdentifier> <catissue:registrationDate>2012-03-07</catissue:registrationDate> <catissue:specimenCollectionGroupCollection class=\"set\"/> <catissue:collectionProtocol> <catissue:shortTitle>6482</catissue:shortTitle> <catissue:collectionProtocolEventCollection class=\"linked-hash-set\"/> <catissue:childCollectionProtocolCollection class=\"linked-hash-set\"/> <catissue:studyFormContextCollection class=\"set\"/> <catissue:collectionProtocolRegistrationCollection class=\"set\"/> <catissue:siteCollection class=\"set\"/> <catissue:clinicalDiagnosisCollection class=\"linked-hash-set\"/> <catissue:distributionProtocolCollection class=\"linked-hash-set\"/> <catissue:coordinatorCollection class=\"linked-hash-set\"/> <catissue:assignedProtocolUserCollection class=\"set\"/> <catissue:gridGrouperPrivileges/> </catissue:collectionProtocol> <catissue:participant reference=\"../../..\"/> <catissue:isToInsertAnticipatorySCGs>true</catissue:isToInsertAnticipatorySCGs> </catissue:collectionProtocolRegistration> </catissue:collectionProtocolRegistrationCollection> <catissue:raceCollection class=\"set\"> <catissue:race> <catissue:raceName>White</catissue:raceName> <catissue:participant reference=\"../../..\"/> </catissue:race> </catissue:raceCollection> <catissue:participantMedicalIdentifierCollection class=\"set\"/> <catissue:participantRecordEntryCollection class=\"set\"/> </catissue:participant>";
    }

    // CHECKSTYLE:ON

}
