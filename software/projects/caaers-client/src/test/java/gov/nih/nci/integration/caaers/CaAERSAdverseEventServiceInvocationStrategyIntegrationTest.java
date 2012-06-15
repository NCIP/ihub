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
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><adverseevents><studyInfo><studyIdentifier>7216</studyIdentifier></studyInfo><participantInfo><studySubjectIdentifier>PM-113</studySubjectIdentifier></participantInfo><timeframeInfo><reportingPeriod><startDateOfThisCourse>2012-07-12-04:00</startDateOfThisCourse><endDateOfThisCourse>2012-07-15-04:00</endDateOfThisCourse></reportingPeriod><periodType>Treatment</periodType><assignedTreatment>TAC</assignedTreatment></timeframeInfo><adverseEventsList><adverseEvent><verbatim>Event1 Verbatim</verbatim><codedTerm>Adrenal insufficiency</codedTerm><grade>3</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>POSSIBLE</attribution><seriousReasonsList><reason>LIFE_THREATENING</reason><reason>HOSPITALIZATION</reason></seriousReasonsList></adverseEvent><adverseEvent><verbatim>Event2 Verbatim</verbatim><codedTerm>Aspartateamino transferase increased</codedTerm><grade>4</grade><onsetDate>2012-07-10-04:00</onsetDate><resolutionDate>2012-07-11-04:00</resolutionDate><expected>true</expected><attribution>DEFINITE</attribution><seriousReasonsList><reason>CONGENITAL_ANOMALY</reason><reason>OTHER_SERIOUS</reason></seriousReasonsList></adverseEvent></adverseEventsList></adverseevents>";
    }
    // CHECKSTYLE:ON
}
