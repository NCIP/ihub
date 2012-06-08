package gov.nih.nci.integration.caaers;

import gov.nih.nci.integration.caaers.invoker.CaAERSRegistrationServiceInvocationStrategy;
import gov.nih.nci.integration.caaers.invoker.CaAERSUpdateRegistrationServiceInvocationStrategy;
import gov.nih.nci.integration.dao.ServiceInvocationMessageDao;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.invoker.DefaultServiceBroadcaster;
import gov.nih.nci.integration.invoker.ServiceBroadcaster;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author chandrasekaravr
 * 
 *         This client test code only tests the client communication and does code coverage. So, if there is proper
 *         service, it will fail with SOAPFaultException because of schema validation. If not will fail with
 *         IntegrationException because of Connection Refused.
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-caaers-client-test.xml")
public class CaAERSRegistrationServiceInvocationStrategyIntegrationTest {

    @Autowired
    private CaAERSRegistrationServiceInvocationStrategy caAersRegistrationServiceInvocationStrategy;

    @Autowired
    private CaAERSUpdateRegistrationServiceInvocationStrategy caAersUpdateRegistrationServiceInvocationStrategy;

    @Autowired
    private ServiceInvocationMessageDao serviceInvocationMessageDao;

    private static final Long REFMSGID = 12345L;

    /**
     * Tests createParticipant using the ServiceInvocationStrategy class.
     */
    @Test
    public void createParticipant() {

        final ServiceBroadcaster sb = new DefaultServiceBroadcaster(serviceInvocationMessageDao);
        sb.delegateServiceInvocation(REFMSGID, getInvalidPStr(), caAersRegistrationServiceInvocationStrategy);

        final Map<StrategyIdentifier, ServiceInvocationMessage> msgsMap = serviceInvocationMessageDao
                .getAllByReferenceMessageId(REFMSGID);

        Assert.assertNotNull(msgsMap);
        Assert.assertFalse(msgsMap.isEmpty());

        final ServiceInvocationMessage resultMsg = msgsMap.get(caAersRegistrationServiceInvocationStrategy
                .getStrategyIdentifier());
        Assert.assertNotNull(resultMsg);
        Assert.assertNotNull(resultMsg.getInvocationException());

        final ServiceInvocationResult result = caAersRegistrationServiceInvocationStrategy.rollback(resultMsg);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isFault());
    }

    /**
     * Tests createParticipant using the ServiceInvocationStrategy class.
     */
    @Test
    public void updateParticipant() {

        final ServiceBroadcaster sb = new DefaultServiceBroadcaster(serviceInvocationMessageDao);
        sb.delegateServiceInvocation(REFMSGID, getInvalidPStr(), caAersUpdateRegistrationServiceInvocationStrategy);

        final Map<StrategyIdentifier, ServiceInvocationMessage> msgsMap = serviceInvocationMessageDao
                .getAllByReferenceMessageId(REFMSGID);

        Assert.assertNotNull(msgsMap);
        Assert.assertFalse(msgsMap.isEmpty());

        final ServiceInvocationMessage resultMsg = msgsMap.get(caAersUpdateRegistrationServiceInvocationStrategy
                .getStrategyIdentifier());
        Assert.assertNotNull(resultMsg);
        Assert.assertNotNull(resultMsg.getInvocationException());

        // to test rollback set original data
        resultMsg.setOriginalData(getInvalidPStr());

        final ServiceInvocationResult result = caAersUpdateRegistrationServiceInvocationStrategy.rollback(resultMsg);
        Assert.assertNotNull(result);
        Assert.assertTrue(result.isFault());
    }

    // CHECKSTYLE:OFF
    private String getInvalidPStr() {
        return "<?xml version=\"1.0\"?><caaers:participant xmlns:caaers=\"http://webservice.caaers.cabig.nci.nih.gov/participant\" xmlns:p=\"http://integration.nci.nih.gov/participant\" id=\"1\" version=\"1\"><firstName>Cherry0415</firstName><lastName>Blossom0415</lastName><maidenName/><middleName/><birthDate>1965-11-24</birthDate><gender>Male</gender><race>White</race><ethnicity>Not Hispanic or Latino</ethnicity><identifiers><caaers:organizationAssignedIdentifier id=\"1\" version=\"1\"><type>MRN</type><value>997025</value><primaryIndicator>true</primaryIndicator><caaers:organization id=\"1\" version=\"1\"><name>QU</name><nciInstituteCode>DCP</nciInstituteCode></caaers:organization></caaers:organizationAssignedIdentifier><caaers:systemAssignedIdentifier id=\"1\" version=\"1\"><type>MRN</type><value>997025</value><primaryIndicator>true</primaryIndicator><systemName>MRN</systemName></caaers:systemAssignedIdentifier></identifiers><assignments><caaers:assignment id=\"1\" version=\"1\"><studySubjectIdentifier>48824</studySubjectIdentifier><caaers:studySite id=\"1\" version=\"1\"><caaers:study id=\"1\" version=\"1\"><identifiers><identifier id=\"1\" version=\"1\"><type>Protocol Authority Identifier</type><value>6482</value></identifier></identifiers></caaers:study><caaers:organization id=\"1\" version=\"1\"><name>QU</name><nciInstituteCode>DCP</nciInstituteCode></caaers:organization></caaers:studySite></caaers:assignment></assignments></caaers:participant>";
    }
    // CHECKSTYLE:ON
}
