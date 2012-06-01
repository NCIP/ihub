package gov.nih.nci.integration.invoker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author chandrasekaravr
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-common-test.xml")
public class TransactionalServiceInvocatorAndResultAggregatorTest {

	@PersistenceContext(unitName = "ihub-messages")
	private EntityManager em;

	@Autowired
	ServiceInvocatorAndResultAggregator serviceInvocatorAndResultAggregator;

	@Test
	public void aggregateResults() {
		long refMsgId = 12345L;
		String message = "Test message";
		SleeperServiceInvocationStrategy svcStrtgy1 = new SleeperServiceInvocationStrategy(
				3000, false);
		SleeperServiceInvocationStrategy svcStrtgy2 = new SleeperServiceInvocationStrategy(
				8000, false);
		SleeperServiceInvocationStrategy svcStrtgy3 = new SleeperServiceInvocationStrategy(
				5000, false);

		serviceInvocatorAndResultAggregator.invokeService(refMsgId, message,
				svcStrtgy1);
		serviceInvocatorAndResultAggregator.invokeService(refMsgId, message,
				svcStrtgy2);
		serviceInvocatorAndResultAggregator.invokeService(refMsgId, message,
				svcStrtgy3);

		ServiceInvocationResult serviceInvocationResult = serviceInvocatorAndResultAggregator
				.aggregateResults(refMsgId);
		assertNotNull(serviceInvocationResult);
		assertEquals("Success", serviceInvocationResult.getResult());

		assertNotNull(svcStrtgy1.getResult());
		assertEquals(false, svcStrtgy1.getResult().isFault());
		assertNotNull(svcStrtgy2.getResult());
		assertEquals(false, svcStrtgy2.getResult().isFault());
		assertNotNull(svcStrtgy3.getResult());
		assertEquals(false, svcStrtgy3.getResult().isFault());
	}

	@Test
	public void aggregateResultsWithError() {
		long refMsgId = 12346L;
		String message = "Test message";
		SleeperServiceInvocationStrategy svcStrtgy1 = new SleeperServiceInvocationStrategy(
				3000, false);
		SleeperServiceInvocationStrategy svcStrtgy2 = new SleeperServiceInvocationStrategy(
				8000, true);
		SleeperServiceInvocationStrategy svcStrtgy3 = new SleeperServiceInvocationStrategy(
				5000, false);

		serviceInvocatorAndResultAggregator.invokeService(refMsgId, message,
				svcStrtgy1);
		serviceInvocatorAndResultAggregator.invokeService(refMsgId, message,
				svcStrtgy2);
		serviceInvocatorAndResultAggregator.invokeService(refMsgId, message,
				svcStrtgy3);

		ServiceInvocationResult serviceInvocationResult = serviceInvocatorAndResultAggregator
				.aggregateResults(refMsgId);
		assertNotNull(serviceInvocationResult);
		assertEquals(true, serviceInvocationResult.isFault());

		assertNotNull(svcStrtgy1.getResult());
		assertEquals(false, svcStrtgy1.getResult().isFault());
		assertNotNull(svcStrtgy2.getResult());
		assertEquals(true, svcStrtgy2.getResult().isFault());
		assertNotNull(svcStrtgy3.getResult());
		assertEquals(false, svcStrtgy3.getResult().isFault());
	}

	@Test
	public void aggregateResultsWithoutAnyServiceInvocation() {
		long refMsgId = 12347L;
		ServiceInvocationResult serviceInvocationResult = serviceInvocatorAndResultAggregator
				.aggregateResults(refMsgId);
		assertNotNull(serviceInvocationResult);
		assertEquals("Success", serviceInvocationResult.getResult());

	}
}
