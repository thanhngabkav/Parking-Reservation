package com.atp.webservice.parking_reservation_10.repository.springCRUDRepository;

import com.atp.webservice.parking_reservation_10.entities.Driver;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DriverCRUDRepository extends CrudRepository<Driver, UUID>{
    Driver findDriverByEmailOrPhoneNumber(String userName);
}
