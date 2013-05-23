/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.caaers;

import gov.nih.nci.integration.caaers.invoker.CaAERSRegistrationServiceInvocationStrategy;
import gov.nih.nci.integration.caaers.invoker.CaAERSUpdateRegistrationServiceInvocationStrategy;
import gov.nih.nci.integration.dao.ServiceInvocationMessageDao;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.StrategyIdentifier;
import gov.nih.nci.integration.invoker.DefaultServiceBroadcaster;
import gov.nih.nci.integration.invoker.ServiceBroadcaster;
import gov.nih.nci.integration.invoker.ServiceInvocationResult;

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

    private static final Logger LOG = LoggerFactory
            .getLogger(CaAERSRegistrationServiceInvocationStrategyIntegrationTest.class);

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

        final ServiceInvocationResult result = caAersRegistrationServiceInvocationStrategy.rollback(resultMsg);
        Assert.assertNotNull(result);
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

        // to test rollback set original data
        resultMsg.setOriginalData(getInvalidPStr());

        final ServiceInvocationResult result = caAersUpdateRegistrationServiceInvocationStrategy.rollback(resultMsg);
        Assert.assertNotNull(result);
    }

    private String getInvalidPStr() {
        return getXMLString("ParticipantMBC_caaers.xml");
    }

    private String getXMLString(String fileName) {
        String contents = null;
        final InputStream is = CaAERSAdverseEventServiceClientIntegrationTest.class.getClassLoader().getResourceAsStream(
                "payloads/participant/" + fileName);
        try {
            contents = org.apache.cxf.helpers.IOUtils.toString(is);
        } catch (IOException e) {
            LOG.error("Error while reading contents of file : " + fileName + ". " + e);
        }
        return contents;
    }
}
