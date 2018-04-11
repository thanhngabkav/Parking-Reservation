package com.atp.webservice.parking_reservation_10.repository.sparkRepository;

import java.util.List;

/**
 * SparkSQLCRUDRepository is an Interface for generic CRUD operations on a repository for a specific type.
 * @param <T> : Entity Class
 * @param <ID> : Primary key type
 */
public interface SparkSQLCRUDRepository <T,ID> {

    /**
     * Get all Records in database
     * @return List<T>
     */
    List<T> findAll();

    /**
     * Find one entity by ID
     * @param id ID
     * @return T or null if not found
     */
    T findOne(ID id);

    /**
     * Check an entity is existed or not by ID
     * @param id : ID
     * @return true if existed and false if not
     */
    boolean exist(ID id);

    /**
     * Delete a record by ID
     * @param id ID
     */
    void delete(ID id);

    /**
     * Count number unique Entities in database
     * @return @long
     */
    long count();

    /**
     * Save an entity into database, update if entity is existed or save new if not
     * @param entity T
     * @return Saved entity
     */
    T save(T entity);

    /**
     * Unpersist Dataset
     */
    public void unpersist();
}
