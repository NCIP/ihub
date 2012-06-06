package gov.nih.nci.integration.catissue;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Integration test class for RegisterParticipant
 * 
 * @author Vinodh
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext-transcend-catissue.xml")
public class CaTissueParticipantClientIntegrationTest {

    @Autowired
    private CaTissueParticipantClient caTissueParticipantClient;

    /**
     * Test for registerParticipant
     */
    @Test
    public void registerParticipant() {
        ServiceInvocationResult svc = caTissueParticipantClient.registerParticipant(getParticipantXMLStr());
        assertNotNull(svc);
        assertFalse(svc.isFault());
        svc = caTissueParticipantClient.deleteParticipant(getParticipantXMLStr());
        assertNotNull(svc);
        assertFalse(svc.isFault());
    }

    // CHECKSTYLE:OFF
    private String getParticipantXMLStr() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?><catissue:participant xmlns:p=\"http://integration.nci.nih.gov/participant\" xmlns:catissue=\"http://domain.catissuecore.wustl.edu/participant\"><catissue:activityStatus>Active</catissue:activityStatus><catissue:birthDate>19410502</catissue:birthDate><catissue:ethnicity>Not Hispanic or Latino</catissue:ethnicity><catissue:firstName>Cherry</catissue:firstName><catissue:gender>Male Gender</catissue:gender><catissue:lastName>Blossom</catissue:lastName><catissue:socialSecurityNumber>123-45-9991</catissue:socialSecurityNumber><catissue:vitalStatus>Alive</catissue:vitalStatus><catissue:collectionProtocolRegistrationCollection class=\"set\"><catissue:collectionProtocolRegistration><catissue:activityStatus>Active</catissue:activityStatus><catissue:consentSignatureDate>2012-04-08</catissue:consentSignatureDate><catissue:protocolParticipantIdentifier/><catissue:registrationDate>2012-04-08</catissue:registrationDate><catissue:specimenCollectionGroupCollection class=\"set\"/><catissue:collectionProtocol><catissue:title>CP-01</catissue:title><catissue:collectionProtocolEventCollection class=\"linked-hash-set\"/><catissue:childCollectionProtocolCollection class=\"linked-hash-set\"/><catissue:studyFormContextCollection class=\"set\"/><catissue:collectionProtocolRegistrationCollection class=\"set\"/><catissue:siteCollection class=\"set\"/><catissue:clinicalDiagnosisCollection class=\"linked-hash-set\"/><catissue:distributionProtocolCollection class=\"linked-hash-set\"/><catissue:coordinatorCollection class=\"linked-hash-set\"/><catissue:assignedProtocolUserCollection class=\"set\"/><catissue:gridGrouperPrivileges/></catissue:collectionProtocol><catissue:participant reference=\"../../..\"/><catissue:isToInsertAnticipatorySCGs>true</catissue:isToInsertAnticipatorySCGs></catissue:collectionProtocolRegistration></catissue:collectionProtocolRegistrationCollection><catissue:raceCollection class=\"set\"/><catissue:participantMedicalIdentifierCollection class=\"linked-hash-set\"/><catissue:participantRecordEntryCollection class=\"set\"/></catissue:participant>";
    }
}
