package gov.nih.nci.integration.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import gov.nih.nci.integration.domain.IHubMessage;
import gov.nih.nci.integration.domain.ServiceInvocationMessage;
import gov.nih.nci.integration.domain.Status;
import gov.nih.nci.integration.domain.StrategyIdentifier;

import java.sql.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
public class ServiceInvocationMessagesDaoTest {

	@Autowired
	private ServiceInvocationMessageDao serviceInvocationMessageDao;

	@Autowired
	private IHubMessageDao iHubMessageDao;

	@PersistenceContext
	private EntityManager em;

	private Long refMsgId;

	@Before
	public void createReferenceMessage() {
		// create and persist reference message
		final IHubMessage refIHubMessage = createIHubMessage();
		refMsgId = iHubMessageDao.save(refIHubMessage);
	}

	/**
	 * Tests saving a ServiceInvocationMessage
	 */
	@Test
	@Rollback(true)
	public void save() {
		final int sizeBefore = serviceInvocationMessageDao.getAll().size();
		final ServiceInvocationMessage serviceInvocationMessage = createServiceInvocationMessage();
		serviceInvocationMessage.setReferenceMessageId(refMsgId);

		final Long id = serviceInvocationMessageDao
				.save(serviceInvocationMessage);
		assertNotNull(id);
		assertEquals(id, serviceInvocationMessage.getId());
		// associated IHubMessage must also be saved
		assertNotNull(serviceInvocationMessage.getMessage().getId());
		assertEquals(sizeBefore + 1, serviceInvocationMessageDao.getAll()
				.size());
		
		final ServiceInvocationMessage savedServiceInvocationMessage = serviceInvocationMessageDao
				.getById(serviceInvocationMessage.getId());
		assertEquals(serviceInvocationMessage.getStrategyIdentifier(),
				savedServiceInvocationMessage.getStrategyIdentifier());
		assertEquals(serviceInvocationMessage.getReferenceMessageId(),
				savedServiceInvocationMessage.getReferenceMessageId());
	}
	
	/**
	 * Tests retrieving list of ServiceInvocationMessage by reference message id
	 */
	@Test
	@Rollback(true)
	public void getAllByReferenceMessageId() {
		final ServiceInvocationMessage serviceInvocationMessage = createServiceInvocationMessage();
		serviceInvocationMessage.setReferenceMessageId(refMsgId);

		final Long id = serviceInvocationMessageDao
				.save(serviceInvocationMessage);
		assertNotNull(id);
		
		List<ServiceInvocationMessage> svcInvMsgs = serviceInvocationMessageDao.getAllByReferenceMessageId(refMsgId);
		assertNotNull(svcInvMsgs);
		assertEquals(1, svcInvMsgs.size());
		
		final ServiceInvocationMessage savedServiceInvocationMessage = svcInvMsgs.get(0);
		assertEquals(serviceInvocationMessage.getStrategyIdentifier(),
				savedServiceInvocationMessage.getStrategyIdentifier());
		assertEquals(serviceInvocationMessage.getReferenceMessageId(),
				savedServiceInvocationMessage.getReferenceMessageId());
	}

	private IHubMessage createIHubMessage() {
		final IHubMessage iHubMessage = new IHubMessage();
		iHubMessage.setRequest("request string");
		iHubMessage.setStartTime(new Date(new java.util.Date().getTime()));
		iHubMessage.setStatus(Status.PROCESS);

		return iHubMessage;
	}

	private ServiceInvocationMessage createServiceInvocationMessage() {
		ServiceInvocationMessage serviceInvocationMessage = new ServiceInvocationMessage();
		serviceInvocationMessage
				.setStrategyIdentifier(StrategyIdentifier.CATISSUE_CREATE_REGISTRATION);

		final IHubMessage iHubMessage = createIHubMessage();
		iHubMessage.setResponse("response string");
		iHubMessage.setStatus(Status.SUCCESS);
		serviceInvocationMessage.setMessage(iHubMessage);

		return serviceInvocationMessage;
	}
}
