package gov.nih.nci.integration.invoker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import gov.nih.nci.integration.dao.ServiceInvocationMessageDao;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.Status;
import gov.nih.nci.integration.domain.StrategyIdentifier;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author chandrasekaravr
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@ContextConfiguration(locations = "classpath:applicationContext-common-test.xml")
@Transactional
public class DefaultBroadcasterTest {

	@Autowired
	private ServiceBroadcaster serviceBroadcaster;

	@Autowired
	private ServiceInvocationMessageDao serviceInvocationMessageDao;

	ServiceInvocationStrategy serviceInvocationStrategy;

	ServiceInvocationResult serviceInvocationResult;

	@Before
	public void prepareMockServiceInvocationStrategy() {
		serviceInvocationStrategy = EasyMock
				.createMock(ServiceInvocationStrategy.class);

		serviceInvocationResult = new ServiceInvocationResult();

		EasyMock.expect(
				serviceInvocationStrategy.invoke("mock request message"))
				.andReturn(serviceInvocationResult);

		EasyMock.expect(serviceInvocationStrategy.getStrategyIdentifier())
				.andReturn(StrategyIdentifier.CAEERS_CREATE_REGISTRATION);

		EasyMock.replay(serviceInvocationStrategy);
	}
	
	/**
	 * Tests ServiceBroadcaster default implementation with a mock ServiceInvocationStrategy
	 * with successful invocation
	 */
	@Test
	@Rollback(true)
	public void broadcastWithMockServiceInvocationStrategyForSuccess() {

		serviceInvocationResult.setResult("mock request response");

		final ServiceInvocationResult serviceInvocationResultActual = serviceBroadcaster
				.delegateServiceInvocation(12345L, "mock request message",
						serviceInvocationStrategy);

		assertNotNull(serviceInvocationResultActual);
		assertEquals(serviceInvocationResult, serviceInvocationResultActual);

		final List<ServiceInvocationMessage> svcInvMsgs = serviceInvocationMessageDao
				.getAllByReferenceMessageId(12345L);
		assertNotNull(svcInvMsgs);
		assertEquals(1, svcInvMsgs.size());
		
		final ServiceInvocationMessage svcInvMsgRetrieved = svcInvMsgs.get(0);
		assertEquals(StrategyIdentifier.CAEERS_CREATE_REGISTRATION, svcInvMsgRetrieved.getStrategyIdentifier());
		assertNotNull(svcInvMsgRetrieved.getMessage());
		assertNotNull(svcInvMsgRetrieved.getMessage().getId());
		assertEquals(Status.SUCCESS, svcInvMsgRetrieved.getMessage().getStatus());
		assertEquals("mock request response", svcInvMsgs.get(0).getMessage().getResponse());
		assertNull(svcInvMsgRetrieved.getInvocationException());
	}
	
	/**
	 * Tests ServiceBroadcaster default implementation with a mock ServiceInvocationStrategy
	 * with failure invocation (failure can be from service or from service invocation itself)
	 */
	@Test
	@Rollback(true)
	public void broadcastWithMockServiceInvocationStrategyForFailure() {

		serviceInvocationResult.setInvocationException(
				new RuntimeException("Exception from ServiceInvocation"));

		final ServiceInvocationResult serviceInvocationResultActual = serviceBroadcaster
				.delegateServiceInvocation(12345L, "mock request message",
						serviceInvocationStrategy);

		assertNotNull(serviceInvocationResultActual);
		assertEquals(serviceInvocationResult, serviceInvocationResultActual);

		final List<ServiceInvocationMessage> svcInvMsgs = serviceInvocationMessageDao
				.getAllByReferenceMessageId(12345L);
		assertNotNull(svcInvMsgs);
		assertEquals(1, svcInvMsgs.size());
		
		final ServiceInvocationMessage svcInvMsgRetrieved = svcInvMsgs.get(0);
		assertEquals(StrategyIdentifier.CAEERS_CREATE_REGISTRATION, svcInvMsgRetrieved.getStrategyIdentifier());
		assertNotNull(svcInvMsgRetrieved.getMessage());
		assertNotNull(svcInvMsgRetrieved.getMessage().getId());
		assertEquals(Status.FAILED, svcInvMsgRetrieved.getMessage().getStatus());
		assertNull(svcInvMsgRetrieved.getMessage().getResponse());
		assertEquals("Exception from ServiceInvocation", svcInvMsgRetrieved.getInvocationException());
	}
	
}
