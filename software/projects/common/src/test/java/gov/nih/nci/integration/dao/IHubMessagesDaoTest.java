/*
 * Copyright Ekagra and SemanticBits, LLC
 * 
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/ihub/LICENSE.txt for details.
 */
package gov.nih.nci.integration.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import gov.nih.nci.integration.domain.IHubMessage;
import gov.nih.nci.integration.domain.Status;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@ContextConfiguration(locations = "classpath:applicationContext-common-test.xml")
@Transactional
public class IHubMessagesDaoTest {

    @Autowired
    private IHubMessageDao iHubMessageDao;

    @PersistenceContext
    private EntityManager em;

    /**
     * Tests saving a iHubMessage
     */
    @Test
    public void save() {
        final int sizeBefore = iHubMessageDao.getAll().size();
        final IHubMessage iHubMessage = createIHubMessage();

        final Long id = iHubMessageDao.save(iHubMessage);
        assertNotNull(id);
        assertEquals(id, iHubMessage.getId());
        assertEquals(sizeBefore + 1, iHubMessageDao.getAll().size());

        final IHubMessage savedIHubMessage = iHubMessageDao.getById(iHubMessage.getId());
        assertEquals(iHubMessage.getRequest(), savedIHubMessage.getRequest());
    }
    
    /**
     * Tests saving request to iHubMessage
     */
    @Test
    public void saveRequest() {
        final int sizeBefore = iHubMessageDao.getAll().size();

        final Long id = iHubMessageDao.saveMessage(12345L, "request string");
        assertNotNull(id);
        assertEquals(sizeBefore + 1, iHubMessageDao.getAll().size());

        final IHubMessage savedIHubMessage = iHubMessageDao.getById(id);
        assertNotNull(savedIHubMessage);
    }
    
    /**
     * Tests updating response to iHubMessage
     */
    @Test
    public void updateResponse() {
        final int sizeBefore = iHubMessageDao.getAll().size();

        final Long id = iHubMessageDao.saveMessage(12345L, "request string");
        assertNotNull(id);
        assertEquals(sizeBefore + 1, iHubMessageDao.getAll().size());

        final IHubMessage savedIHubMessage = iHubMessageDao.getById(id);        
        assertNotNull(savedIHubMessage);
        assertNull(savedIHubMessage.getResponse());
        
        iHubMessageDao.updateIHubResponse(12345L, "response string");
        
        final IHubMessage savedIHubMessage2 = iHubMessageDao.getById(id);
        assertNotNull(savedIHubMessage2);
        assertNotNull(savedIHubMessage2.getResponse());
        assertEquals("response string", savedIHubMessage2.getResponse());
    }

    private IHubMessage createIHubMessage() {
        final IHubMessage iHubMessage = new IHubMessage();
        iHubMessage.setRequest("request string");
        iHubMessage.setStartTime(new Date(new java.util.Date().getTime()));
        iHubMessage.setStatus(Status.PROCESS);
        iHubMessage.setReferenceMessageId(12345L);

        return iHubMessage;
    }
}
