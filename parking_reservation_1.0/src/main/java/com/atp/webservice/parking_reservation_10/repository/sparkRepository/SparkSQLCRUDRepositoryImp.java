package com.atp.webservice.parking_reservation_10.repository.sparkRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SparkSQLCRUDRepositoryImp<T,ID> implements SparkSQLCRUDRepository<T,ID>{

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public T findOne(ID id) {
        return null;
    }

    @Override
    public boolean exist(ID id) {
        return false;
    }

    @Override
    public void delete(ID id) {

    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public T save(T entity) {
        return null;
    }
}
