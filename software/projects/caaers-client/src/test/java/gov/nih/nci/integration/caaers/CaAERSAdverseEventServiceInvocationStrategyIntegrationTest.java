package gov.nih.nci.integration.caaers;

import gov.nih.nci.integration.caaers.invoker.CaAERSAdverseEventServiceInvocationStrategy;
import gov.nih.nci.integration.dao.ServiceInvocationMessageDao;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.invoker.DefaultServiceBroadcaster;
import gov.nih.nci.integration.invoker.ServiceBroadcaster;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private ServiceInvocationMessageDao serviceInvocationMessageDao;

    private static final Long REFMSGID = 12345L;

    private static final Logger LOG = LoggerFactory
            .getLogger(CaAERSAdverseEventServiceInvocationStrategyIntegrationTest.class);

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

    private String getAEInterimMessage() {
        return getXMLString("AEInterimXML.xml");
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = CaAERSAdverseEventServiceClientIntegrationTest.class.getClassLoader()
                .getResourceAsStream("payloads/adverseevent/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            LOG.error("Error while reading contents of file : " + fileName + ". " + e);
        }
        return contents;
    }
}
