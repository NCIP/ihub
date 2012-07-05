package gov.nih.nci.integration.catissue;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import gov.nih.nci.integration.exception.IntegrationException;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This is the test class to test the methods of the Wrapper Consent Client.
 * 
 * @author Rohit Gupta
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext-transcend-catissue.xml")
public class CaTissueParticipantTest {

    private CaTissueParticipantClient caTissueParticipantClient;

    @Value("${catissue.custom.lib.location}")
    private String caTissueLibLocation;

    @Value("${catissue.api.login.username}")
    private String catissueApiLoginName;

    @Value("${catissue.api.login.password}")
    private String catissueApiPassword;

    @Value("${catissue.participant.mock.client}")
    private String participantMockClientClass;

    /**
     * To initialize the things
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    @Before
    public void initialize() throws IntegrationException {
        caTissueParticipantClient = new CaTissueParticipantClient(caTissueLibLocation, catissueApiLoginName,
                catissueApiPassword, participantMockClientClass);
    }

    /**
     * TestCase to test the registerParticipant of Wrapper client
     */
    @Test
    public void clientInitException() {
        CaTissueParticipantClient client = null;
        ServiceInvocationResult svc = null;
        try {
            client = new CaTissueParticipantClient("catissue-client-lib-123/", catissueApiLoginName,
                    catissueApiPassword, participantMockClientClass);
            svc = client.registerParticipant(getParticipantXMLStr());
            // CHECKSTYLE:OFF
        } catch (IntegrationException e) { // NOPMD
            svc = null;
        }

        assertNull(svc);
    }

    /**
     * TestCase to test the registerParticipant of Wrapper client
     * 
     */
    @Test
    public void registerParticipantFailure() {
        final ServiceInvocationResult svc = caTissueParticipantClient
                .registerParticipant(getParticipantXMLStrFailure());
        assertNotNull(svc);
    }

    /**
     * TestCase to test the registerParticipant of Wrapper client
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    public void registerParticipant() throws IntegrationException {
        final ServiceInvocationResult svc = caTissueParticipantClient.registerParticipant(getParticipantXMLStr());
        assertNotNull(svc);
    }

    /**
     * TestCase to test the updateRegistrationParticipant of Wrapper client
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    public void updateRegistrationParticipant() throws IntegrationException {
        final ServiceInvocationResult svc = caTissueParticipantClient
                .updateRegistrationParticipant(getParticipantXMLStr());
        assertNotNull(svc);
    }

    /**
     * TestCase to test the deleteParticipant of Wrapper client
     * 
     * @throws IntegrationException - IntegrationException
     */
    @Test
    public void deleteParticipant() throws IntegrationException {
        final ServiceInvocationResult svc = caTissueParticipantClient.deleteParticipant(getParticipantXMLStr());
        assertNotNull(svc);
    }

    // CHECKSTYLE:OFF
    private String getParticipantXMLStr() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><catissue:participant xmlns:p=\"http://integration.nci.nih.gov/participant\" xmlns:catissue=\"http://domain.catissuecore.wustl.edu/participant\"><catissue:activityStatus>Active</catissue:activityStatus><catissue:birthDate>19410502</catissue:birthDate><catissue:ethnicity>Not Hispanic or Latino</catissue:ethnicity><catissue:firstName>Cherry</catissue:firstName><catissue:gender>Male Gender</catissue:gender><catissue:lastName>Blossom</catissue:lastName><catissue:socialSecurityNumber>123-45-9991</catissue:socialSecurityNumber><catissue:vitalStatus>Alive</catissue:vitalStatus><catissue:collectionProtocolRegistrationCollection class=\"set\"><catissue:collectionProtocolRegistration><catissue:activityStatus>Active</catissue:activityStatus><catissue:consentSignatureDate>2012-04-08</catissue:consentSignatureDate><catissue:protocolParticipantIdentifier/><catissue:registrationDate>2012-04-08</catissue:registrationDate><catissue:specimenCollectionGroupCollection class=\"set\"/><catissue:collectionProtocol><catissue:title>CP-01</catissue:title><catissue:collectionProtocolEventCollection class=\"linked-hash-set\"/><catissue:childCollectionProtocolCollection class=\"linked-hash-set\"/><catissue:studyFormContextCollection class=\"set\"/><catissue:collectionProtocolRegistrationCollection class=\"set\"/><catissue:siteCollection class=\"set\"/><catissue:clinicalDiagnosisCollection class=\"linked-hash-set\"/><catissue:distributionProtocolCollection class=\"linked-hash-set\"/><catissue:coordinatorCollection class=\"linked-hash-set\"/><catissue:assignedProtocolUserCollection class=\"set\"/><catissue:gridGrouperPrivileges/></catissue:collectionProtocol><catissue:participant reference=\"../../..\"/><catissue:isToInsertAnticipatorySCGs>true</catissue:isToInsertAnticipatorySCGs></catissue:collectionProtocolRegistration></catissue:collectionProtocolRegistrationCollection><catissue:raceCollection class=\"set\"/><catissue:participantMedicalIdentifierCollection class=\"linked-hash-set\"/><catissue:participantRecordEntryCollection class=\"set\"/></catissue:participant>";
    }

    private String getParticipantXMLStrFailure() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><catissue:participant xmlns:p=\"http://integration.nci.nih.gov/participant\" xmlns:catissue=\"http://domain.catissuecore.wustl.edu/participant\"><catissue:activityStatus>Active</catissue:activityStatus><catissue:birthDate>19410502</catissue:birthDate><catissue:ethnicity>Not Hispanic or Latino</catissue:ethnicity><catissue:firstName>Cherry</catissue:firstName><catissue:gender>Male Gender</catissue:gender><catissue:lastName></catissue:lastName><catissue:socialSecurityNumber>123-45-9991</catissue:socialSecurityNumber><catissue:vitalStatus>Alive</catissue:vitalStatus><catissue:collectionProtocolRegistrationCollection class=\"set\"><catissue:collectionProtocolRegistration><catissue:activityStatus>Active</catissue:activityStatus><catissue:consentSignatureDate>2012-04-08</catissue:consentSignatureDate><catissue:protocolParticipantIdentifier/><catissue:registrationDate>2012-04-08</catissue:registrationDate><catissue:specimenCollectionGroupCollection class=\"set\"/><catissue:collectionProtocol><catissue:title>CP-01</catissue:title><catissue:collectionProtocolEventCollection class=\"linked-hash-set\"/><catissue:childCollectionProtocolCollection class=\"linked-hash-set\"/><catissue:studyFormContextCollection class=\"set\"/><catissue:collectionProtocolRegistrationCollection class=\"set\"/><catissue:siteCollection class=\"set\"/><catissue:clinicalDiagnosisCollection class=\"linked-hash-set\"/><catissue:distributionProtocolCollection class=\"linked-hash-set\"/><catissue:coordinatorCollection class=\"linked-hash-set\"/><catissue:assignedProtocolUserCollection class=\"set\"/><catissue:gridGrouperPrivileges/></catissue:collectionProtocol><catissue:participant reference=\"../../..\"/><catissue:isToInsertAnticipatorySCGs>true</catissue:isToInsertAnticipatorySCGs></catissue:collectionProtocolRegistration></catissue:collectionProtocolRegistrationCollection><catissue:raceCollection class=\"set\"/><catissue:participantMedicalIdentifierCollection class=\"linked-hash-set\"/><catissue:participantRecordEntryCollection class=\"set\"/></catissue:participant>";
    }

}
