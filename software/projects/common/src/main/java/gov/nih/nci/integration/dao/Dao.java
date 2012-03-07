package gov.nih.nci.integration.dao;

import gov.nih.nci.integration.domain.AbstractIdentity;

import java.util.List;

/**
 * Base Dao interface. Defines common methods for operating on an entity. The intent is for there to be a single Dao
 * interface per entity (entity subclasses don't need a separate dao interface).
 *
 * @author chandrasekaravr
 * @param <T> the type of the entity for this dao
 */
public interface Dao<T extends AbstractIdentity> {

    /**
     * Retrieve all entities of the entity type served by this dao.
     *
     * @return list of the entities, empty list if none.
     */
    List<T> getAll();

    /**
     * Save the given entity in the persistent store.
     *
     * @param entity entity to save. It must not be yet persistent
     * @return id of the saved entity
     */
    Long save(T entity);

    /**
     * Entity Id is the identifier of the Entity within the persistence layer (database). 
     * @param id the id
     * @return the entity
     */
    T getById(Long id);

}
