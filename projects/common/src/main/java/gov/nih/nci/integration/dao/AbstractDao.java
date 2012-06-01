package gov.nih.nci.integration.dao;

import gov.nih.nci.integration.domain.AbstractIdentity;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Base class for Dao implementations.
 * 
 * @author chandrasekaravr
 * @param <T>
 *            entity
 * 
 */

@Transactional
public abstract class AbstractDao<T extends AbstractIdentity> implements Dao<T> {

	private static final Logger LOG = LoggerFactory
			.getLogger(AbstractDao.class);
	private final Class<T> domainClass;

	@PersistenceContext(unitName = "ihub-messages")
	private EntityManager em;

	/**
	 * @param domainClass
	 *            - entity class
	 */
	public AbstractDao(final Class<T> domainClass) {
		super();
		this.domainClass = domainClass;
	}

	/**
	 * @param domainClass
	 *            - entity class
	 * @param em
	 *            JPA EntityManager
	 */
	public AbstractDao(final Class<T> domainClass, EntityManager em) {
		super();
		this.domainClass = domainClass;
		this.em = em;
	}

	/**
	 * getDomainClass
	 * @return domain class
	 */
	public Class<T> getDomainClass() {
		return domainClass;
	}

	/**
	 * getById
	 * @param id -Id
	 * @return T - Entity
	 */
	public T getById(final Long id) {
		return em.find(domainClass, id);
	}

	/**
	 * getAll
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return em.createQuery("from " + domainClass.getSimpleName())
				.getResultList();
	}

	/**
	 * save
	 * @param entity - Entity
	 * @return Id
	 */
	public Long save(final T entity) {
		em.persist(entity);
		/*
		 * Commenting out the flush after save. It will be executed at the time
		 * of commit. This is done because this flush causes embeddable
		 * Collections to be inserted first at this flush and then later deleted
		 * and inserted at the commit flush.
		 */
		return entity.getId();
	}

	/**
	 * getEm
	 * @return EntityManager
	 */
	public EntityManager getEm() {
		return em;
	}

	/**
	 * set EntityManager
	 * @param em EntityManager
	 */
	public void setEm(EntityManager em) {
		this.em = em;
	}

}
