package gov.nih.nci.integration.invoker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import gov.nih.nci.integration.dao.ServiceInvocationMessageDao;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.Status;
import gov.nih.nci.integration.domain.StrategyIdentifier;

import java.util.Map;

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

	private static final int RETRY_CNT = 2;

	private static final long REF_MSG_ID = 12345L;

	private static final String MOCK_REQUEST_RESPONSE = "mock request response";

	private static final String MOCK_REQUEST_MESSAGE = "mock request message";

	@Autowired
	private ServiceBroadcaster serviceBroadcaster;

	@Autowired
	private ServiceInvocationMessageDao serviceInvocationMessageDao;

	private ServiceInvocationStrategy serviceInvocationStrategy;

	private ServiceInvocationResult serviceInvocationResult;

	@Before
	public void prepareMockServiceInvocationStrategy() {
		serviceInvocationStrategy = EasyMock
				.createNiceMock(ServiceInvocationStrategy.class);

		serviceInvocationResult = new ServiceInvocationResult();

		EasyMock.expect(serviceInvocationStrategy.getStrategyIdentifier())
				.andReturn(StrategyIdentifier.CAEERS_CREATE_REGISTRATION);

		EasyMock
				.expect(
						serviceInvocationStrategy
								.invoke((ServiceInvocationMessage) EasyMock
										.anyObject())).andReturn(
						serviceInvocationResult);

		EasyMock.expect(serviceInvocationStrategy.getRetryCount()).andReturn(
				RETRY_CNT);
	}

	/**
	 * Tests ServiceBroadcaster default implementation with a mock
	 * ServiceInvocationStrategy with successful invocation
	 */
	@Test
	@Rollback(true)
	public void broadcastWithMockServiceInvocationStrategyForSuccess() {

		serviceInvocationResult.setResult(MOCK_REQUEST_RESPONSE);

		EasyMock.replay(serviceInvocationStrategy);

		final ServiceInvocationResult serviceInvocationResultActual = serviceBroadcaster
				.delegateServiceInvocation(REF_MSG_ID, MOCK_REQUEST_MESSAGE,
						serviceInvocationStrategy);

		assertNotNull(serviceInvocationResultActual);
		assertEquals(serviceInvocationResult, serviceInvocationResultActual);

		final Map<StrategyIdentifier, ServiceInvocationMessage> svcInvMsgs = serviceInvocationMessageDao
				.getAllByReferenceMessageId(REF_MSG_ID);
		assertNotNull(svcInvMsgs);
		assertEquals(1, svcInvMsgs.size());

		final ServiceInvocationMessage svcInvMsgRetrieved = svcInvMsgs
				.get(StrategyIdentifier.CAEERS_CREATE_REGISTRATION);
		assertNotNull(svcInvMsgRetrieved);
		assertEquals(StrategyIdentifier.CAEERS_CREATE_REGISTRATION,
				svcInvMsgRetrieved.getStrategyIdentifier());
		assertNotNull(svcInvMsgRetrieved.getMessage());
		assertNotNull(svcInvMsgRetrieved.getMessage().getId());
		assertEquals(Status.SUCCESS, svcInvMsgRetrieved.getMessage()
				.getStatus());
		assertEquals(MOCK_REQUEST_MESSAGE, svcInvMsgRetrieved.getMessage()
				.getRequest());
		assertEquals(MOCK_REQUEST_RESPONSE, svcInvMsgRetrieved.getMessage()
				.getResponse());
		assertNull(svcInvMsgRetrieved.getInvocationException());
	}

	/**
	 * Tests ServiceBroadcaster default implementation with a mock
	 * ServiceInvocationStrategy with failure invocation (failure can be from
	 * service or from service invocation itself)
	 */
	@Test
	@Rollback(true)
	public void broadcastWithMockServiceInvocationStrategyForFailureWithRetry() {

		serviceInvocationResult.setInvocationException(new RuntimeException(// NOPMD
				"Exception from ServiceInvocation"));

		serviceInvocationResult.setRetry(true);

		// verify 3 times called, 1 main + 2 retries
		EasyMock
				.expect(
						serviceInvocationStrategy
								.invoke((ServiceInvocationMessage) EasyMock
										.anyObject())).andReturn(
						serviceInvocationResult).times(RETRY_CNT);

		EasyMock.replay(serviceInvocationStrategy);

		final ServiceInvocationResult serviceInvocationResultActual = serviceBroadcaster
				.delegateServiceInvocation(REF_MSG_ID, MOCK_REQUEST_MESSAGE,
						serviceInvocationStrategy);

		assertNotNull(serviceInvocationResultActual);
		assertEquals(serviceInvocationResult, serviceInvocationResultActual);

		final Map<StrategyIdentifier, ServiceInvocationMessage> svcInvMsgs = serviceInvocationMessageDao
				.getAllByReferenceMessageId(REF_MSG_ID);
		assertNotNull(svcInvMsgs);
		assertEquals(1, svcInvMsgs.size());

		final ServiceInvocationMessage svcInvMsgRetrieved = svcInvMsgs
				.get(StrategyIdentifier.CAEERS_CREATE_REGISTRATION);
		assertNotNull(svcInvMsgRetrieved);
		assertEquals(StrategyIdentifier.CAEERS_CREATE_REGISTRATION,
				svcInvMsgRetrieved.getStrategyIdentifier());
		assertNotNull(svcInvMsgRetrieved.getMessage());
		assertNotNull(svcInvMsgRetrieved.getMessage().getId());
		assertEquals(Status.FAILED, svcInvMsgRetrieved.getMessage().getStatus());
		assertEquals(MOCK_REQUEST_MESSAGE, svcInvMsgRetrieved.getMessage()
				.getRequest());
		assertNull(svcInvMsgRetrieved.getMessage().getResponse());
		assertEquals("Exception from ServiceInvocation", svcInvMsgRetrieved
				.getInvocationException());

		EasyMock.verify(serviceInvocationStrategy);
	}

}
