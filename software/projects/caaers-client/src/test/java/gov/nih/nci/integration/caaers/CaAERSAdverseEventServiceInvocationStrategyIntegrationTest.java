package gov.nih.nci.integration.caaers;

import gov.nih.nci.integration.caaers.invoker.CaAERSAdverseEventServiceInvocationStrategy;
import gov.nih.nci.integration.caaers.invoker.CaAERSUpdateAdverseEventServiceInvocationStrategy;
import gov.nih.nci.integration.dao.ServiceInvocationMessageDao;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.invoker.DefaultServiceBroadcaster;
import gov.nih.nci.integration.invoker.ServiceBroadcaster;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This client test code only tests the client communication and does code coverage. So, if there is proper service, it
 * will fail with SOAPFaultException because of schema validation.
 * 
 * @author Rohit Gupta
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-caaers-client-test.xml")
public class CaAERSAdverseEventServiceInvocationStrategyIntegrationTest {

    @Autowired
    private CaAERSAdverseEventServiceInvocationStrategy caAERSAdverseEventServiceInvocationStrategy;

    @Autowired
    private CaAERSUpdateAdverseEventServiceInvocationStrategy caAERSUpdateAdverseEventServiceInvocationStrategy;

    @Autowired
    private ServiceInvocationMessageDao serviceInvocationMessageDao;

    private static final Long REFMSGID = 12345L;

    /**
     * Tests createAdverseEvent using the ServiceInvocationStrategy class.
     */
    @Test
    public void createAdverseEvent() {

        final ServiceBroadcaster sb = new DefaultServiceBroadcaster(serviceInvocationMessageDao);
        sb.delegateServiceInvocation(REFMSGID, getAEInterimMessage(), caAERSAdverseEventServiceInvocationStrategy);

        final Map<StrategyIdentifier, ServiceInvocationMessage> msgsMap = serviceInvocationMessageDao
                .getAllByReferenceMessageId(REFMSGID);

        Assert.assertNotNull(msgsMap);
        Assert.assertFalse(msgsMap.isEmpty());

        final ServiceInvocationMessage resultMsg = msgsMap.get(caAERSAdverseEventServiceInvocationStrategy
                .getStrategyIdentifier());
        Assert.assertNotNull(resultMsg);
        Assert.assertNotNull(resultMsg.getInvocationException());

    }

    /**
     * Tests updateAdverseEvent using the ServiceInvocationStrategy class.
     */
    @Test
    public void updateAdverseEvent() {

        final ServiceBroadcaster sb = new DefaultServiceBroadcaster(serviceInvocationMessageDao);
        sb.delegateServiceInvocation(REFMSGID, getAEInterimMessage(), caAERSUpdateAdverseEventServiceInvocationStrategy);

        final Map<StrategyIdentifier, ServiceInvocationMessage> msgsMap = serviceInvocationMessageDao
                .getAllByReferenceMessageId(REFMSGID);

        Assert.assertNotNull(msgsMap);
        Assert.assertFalse(msgsMap.isEmpty());

        final ServiceInvocationMessage resultMsg = msgsMap.get(caAERSUpdateAdverseEventServiceInvocationStrategy
                .getStrategyIdentifier());
        Assert.assertNotNull(resultMsg);
        Assert.assertNotNull(resultMsg.getInvocationException());

    }

    // CHECKSTYLE:OFF
    private String getAEInterimMessage() {
        return "<ns2:caxchangerequest xmlns:ns2=\"http://caXchange.nci.nih.gov/caxchangerequest\"><ns0:metadata xmlns:ns0=\"http://caXchange.nci.nih.gov/messaging\"><mes:serviceType xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1trim=\"urn:tolven-org:trim:4.0\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">iHub</mes:serviceType><mes:operationName xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1trim=\"urn:tolven-org:trim:4.0\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">Create Adverse Event</mes:operationName><mes:externalIdentifier xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1trim=\"urn:tolven-org:trim:4.0\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">32225879</mes:externalIdentifier><mes:caXchangeIdentifier xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1trim=\"urn:tolven-org:trim:4.0\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">1340651815262</mes:caXchangeIdentifier><mes:credentials xmlns:mes=\"http://caXchange.nci.nih.gov/messaging\" xmlns=\"http://caXchange.nci.nih.gov/messaging\" xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1trim=\"urn:tolven-org:trim:4.0\" xmlns:cda=\"urn:hl7-org:v3\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"><mes:userName>tolvenuser</mes:userName><mes:groupName>nogrid</mes:groupName><mes:gridIdentifier>nogrid</mes:gridIdentifier><mes:password>changeme</mes:password><mes:delegatedCredentialReference>nocredentials</mes:delegatedCredentialReference></mes:credentials></ns0:metadata><adverseeventinput xmlns=\"http://cacis.nci.nih.gov\"><criteria><participantIdentifier>PM-113</participantIdentifier><studyIdentifier>7216</studyIdentifier><course><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse><treatmentType>Treatment</treatmentType><treatmentAssignmentCode>TAC</treatmentAssignmentCode></course></criteria><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><ctepCode>Adrenal insufficiency</ctepCode><grade>3</grade><startDate>2012-07-10-04:00</startDate><endDate>2012-07-11-04:00</endDate><expected>true</expected><attributionSummary>POSSIBLE</attributionSummary><outcome><outComeEnumType>LIFE_THREATENING</outComeEnumType></outcome><outcome><outComeEnumType>HOSPITALIZATION</outComeEnumType></outcome></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><ctepCode>Aspartateamino transferase increased</ctepCode><grade>4</grade><startDate>2012-07-10-04:00</startDate><endDate>2012-07-11-04:00</endDate><expected>true</expected><attributionSummary>DEFINITE</attributionSummary><outcome><outComeEnumType>CONGENITAL_ANOMALY</outComeEnumType></outcome><outcome><outComeEnumType>OTHER_SERIOUS</outComeEnumType></outcome></adverseEvent></adverseEventsList></adverseeventinput></ns2:caxchangerequest>";
    }

    // CHECKSTYLE:ON
}
