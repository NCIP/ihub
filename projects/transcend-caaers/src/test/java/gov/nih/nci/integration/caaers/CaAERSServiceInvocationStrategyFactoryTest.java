package gov.nih.nci.integration.caaers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import gov.nih.nci.integration.caaers.invoker.CaAERSServiceInvocationStrategyFactory;
import gov.nih.nci.integration.domain.IHubMessage;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;
import gov.nih.nci.integration.invoker.ServiceInvocationStrategy;

import java.io.File;
import java.sql.Date;

import org.junit.Test;

/**
 * Test class for CaAERSServiceInvocationStrategyFactory
 * 
 * @author Vinodh
 * 
 */
public class CaAERSServiceInvocationStrategyFactoryTest {

    /**
     * Testcase for createCaAERSUpdateAdverseEventServiceInvocationStrategy
     */
    @Test
    public void createCaAERSUpdateAdverseEventServiceInvocationStrategyFailure() {
        final File customLibLoc = new File("..\\caaers-client\\build\\caaers-client-lib-123\\");
        final File distLoc = new File("..\\caaers-client\\build\\dist\\");
        final ServiceInvocationStrategy suaeis = CaAERSServiceInvocationStrategyFactory
                .createCaAERSUpdateAdverseEventServiceInvocationStrategy(new String[] { customLibLoc.getAbsolutePath(),
                        distLoc.getAbsolutePath() }, "classpath*:applicationContext-caaers-client-test.xml");
        assertNull(suaeis);
    }

    /**
     * Testcase for createCaAERSServiceInvocationStrategy
     */
    @Test
    public void createCaAERSServiceInvocationStrategy() {
        final File customLibLoc = new File("..\\caaers-client\\build\\caaers-client-lib\\");
        final File distLoc = new File("..\\caaers-client\\build\\dist\\");
        final ServiceInvocationStrategy sris = CaAERSServiceInvocationStrategyFactory
                .createCaAERSRegistrationServiceInvocationStrategy(new String[] { customLibLoc.getAbsolutePath(),
                        distLoc.getAbsolutePath() }, "classpath*:applicationContext-caaers-client-test.xml");
        final ServiceInvocationStrategy suris = CaAERSServiceInvocationStrategyFactory
                .createCaAERSUpdateRegistrationServiceInvocationStrategy(new String[] { customLibLoc.getAbsolutePath(),
                        distLoc.getAbsolutePath() }, "classpath*:applicationContext-caaers-client-test.xml");

        assertNotNull(sris);
        assertNotNull(suris);

        final ServiceInvocationMessage msg = new ServiceInvocationMessage();
        msg.setStrategyIdentifier(StrategyIdentifier.CAEERS_CREATE_REGISTRATION);
        final IHubMessage iHubMessage = new IHubMessage();
        iHubMessage.setRequest(getPStr());
        final Date stTime = new Date(new java.util.Date().getTime());
        iHubMessage.setStartTime(stTime);
        msg.setMessage(iHubMessage);

        final ServiceInvocationResult res = sris.invoke(msg);
        assertNotNull(res);
        assertTrue(res.isFault());
        assertNotNull(res.getInvocationException());
    }

    /**
     * Testcase for createCaAERSAdverseEventServiceInvocationStrategy
     */
    @Test
    public void createCaAERSAdverseEventServiceInvocationStrategy() {
        final File customLibLoc = new File("..\\caaers-client\\build\\caaers-client-lib\\");
        final File distLoc = new File("..\\caaers-client\\build\\dist\\");
        final ServiceInvocationStrategy saeis = CaAERSServiceInvocationStrategyFactory
                .createCaAERSAdverseEventServiceInvocationStrategy(new String[] { customLibLoc.getAbsolutePath(),
                        distLoc.getAbsolutePath() }, "classpath*:applicationContext-caaers-client-test.xml");
        assertNotNull(saeis);
    }

    /**
     * Testcase for createCaAERSUpdateAdverseEventServiceInvocationStrategy
     */
    @Test
    public void createCaAERSUpdateAdverseEventServiceInvocationStrategy() {
        final File customLibLoc = new File("..\\caaers-client\\build\\caaers-client-lib\\");
        final File distLoc = new File("..\\caaers-client\\build\\dist\\");
        final ServiceInvocationStrategy suaeis = CaAERSServiceInvocationStrategyFactory
                .createCaAERSUpdateAdverseEventServiceInvocationStrategy(new String[] { customLibLoc.getAbsolutePath(),
                        distLoc.getAbsolutePath() }, "classpath*:applicationContext-caaers-client-test.xml");
        assertNotNull(suaeis);
    }
    
    /**
     * Testcase for createCaAERSAdverseEventServiceInvocationStrategy
     */
    @Test
    public void createCaAERSAdverseEventServiceInvocationStrategyFailure() {
        final File customLibLoc = new File("..\\caaers-client\\build\\caaers-client-lib\\");
        final File distLoc = new File("..\\caaers-client\\build\\dist\\");
        CaAERSServiceInvocationStrategyFactory.setInitStatus(null);
        final ServiceInvocationStrategy saeis = CaAERSServiceInvocationStrategyFactory
                .createCaAERSAdverseEventServiceInvocationStrategy(new String[] { customLibLoc.getAbsolutePath(),
                        distLoc.getAbsolutePath() }, "classpath*:applicationContext-caaers-client-test.xml");
        assertNull(saeis);
    }

    // CHECKSTYLE:OFF
    private String getPStr() {
        return "<xr:caxchangerequest xmlns:xr=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\" xmlns:p=\"http://integration.nci.nih.gov/participant\" xmlns:ns1trim=\"urn:tolven-org:trim:4.0\" xmlns:xs=\"http://www.w3.org/TR/2008/REC-xml-20081126#\"><mes:metadata><mes:serviceType xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">iHub</mes:serviceType><mes:operationName xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">Create Registration</mes:operationName><mes:externalIdentifier xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">322061501</mes:externalIdentifier><mes:caXchangeIdentifier xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">1340655059894</mes:caXchangeIdentifier><mes:credentials xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><mes:userName>tolvenuser</mes:userName><mes:password>changeme</mes:password></mes:credentials></mes:metadata><mes:request><mes:businessMessagePayload><xmlSchemaDefinition xmlns=\"http://cacis.nci.nih.gov\">http://integration.nci.nih.gov/participant</xmlSchemaDefinition><p:participant xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" id=\"1\" version=\"1\" xsi:schemaLocation=\"http://integration.nci.nih.gov/participant Participant.xsd \"><p:firstName>Cherry061501</p:firstName><p:lastName>Blossom061501</p:lastName><p:maidenName/><p:middleName/><p:birthDate>1965-11-24</p:birthDate><p:gender>Male</p:gender><p:race>White</p:race><p:ethnicity>Not Hispanic or Latino</p:ethnicity><p:activityStatus>Active</p:activityStatus><p:registrationDate>2012-03-07</p:registrationDate><p:identifiers><p:organizationAssignedIdentifier id=\"1\" version=\"1\"><p:type>MRN</p:type><p:value>666061501</p:value><p:primaryIndicator>true</p:primaryIndicator><p:organization id=\"1\" version=\"1\"><p:name>QU</p:name><p:nciInstituteCode>DCP</p:nciInstituteCode></p:organization></p:organizationAssignedIdentifier><p:organizationAssignedIdentifier id=\"2\" version=\"1\"><p:type>SSN</p:type><p:value>123-06-1501</p:value><p:primaryIndicator>false</p:primaryIndicator><p:organization id=\"1\" version=\"1\"><p:name>SSN</p:name><p:nciInstituteCode>SSN</p:nciInstituteCode></p:organization></p:organizationAssignedIdentifier><p:systemAssignedIdentifier id=\"1\" version=\"1\"><p:type>MRN</p:type><p:value>666061501</p:value><p:primaryIndicator>true</p:primaryIndicator><p:systemName>MRN</p:systemName></p:systemAssignedIdentifier></p:identifiers><p:assignments><p:assignment id=\"1\" version=\"1\"><p:studySubjectIdentifier>488061501</p:studySubjectIdentifier><p:studySite id=\"1\" version=\"1\"><p:study id=\"1\" version=\"1\"><p:identifiers><p:identifier id=\"1\" version=\"1\"><p:type>Protocol Authority Identifier</p:type><p:value>6482</p:value></p:identifier></p:identifiers></p:study><p:organization id=\"1\" version=\"1\"><p:name>QU</p:name><p:nciInstituteCode>DCP</p:nciInstituteCode></p:organization></p:studySite></p:assignment></p:assignments></p:participant></mes:businessMessagePayload></mes:request></xr:caxchangerequest>";
    }

}
