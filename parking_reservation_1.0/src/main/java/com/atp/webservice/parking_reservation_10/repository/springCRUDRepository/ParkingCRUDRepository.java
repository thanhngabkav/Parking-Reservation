package com.atp.webservice.parking_reservation_10.repository.springCRUDRepository;

import com.atp.webservice.parking_reservation_10.entities.Parking;
import org.springframework.data.repository.CrudRepository;

public interface ParkingCRUDRepository extends CrudRepository<Parking, Integer> {
}
