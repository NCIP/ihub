package gov.nih.nci.integration.dao;

import gov.nih.nci.integration.domain.AbstractIdentity;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Base class for Dao implementations.
 *
 * @author chandrasekaravr
 * @param <T>  entity
 */
public abstract class AbstractDao<T  extends AbstractIdentity> implements Dao<T> {
	
    private static final Logger LOG = LoggerFactory.getLogger(AbstractDao.class);
    private final Class<T>   domainClass;
    
    private final EntityManager em;

    /**
     * @param domainClass - entity class
     * @param em JPA EntityManager
     */
    protected AbstractDao(final Class<T> domainClass, EntityManager em) {
        this.domainClass = domainClass;
        this.em = em;
    }
    
    /**
     * @return domain class
     */
    public Class<T> getDomainClass() {
        return domainClass;
    }
    
    /**
     * {@inheritDoc}
     */
    public T getById(final Long id) {
        return em.find(domainClass, id);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return em.createQuery("from " + domainClass.getSimpleName()).getResultList();
    }

    /**
     * {@inheritDoc}
     */
    public Long save(final T entity) {        
        em.persist(entity);
        /* Commenting out the flush after save. It will be executed at the time of commit. 
           This is done because this flush causes embeddable Collections to be inserted 
           first at this flush and then later deleted and inserted at the commit flush.*/
        //em.flush();
        return entity.getId();
    }

    /**
     * @return em
     */
    protected EntityManager getEntityManager() {
        return em;
    }
}
