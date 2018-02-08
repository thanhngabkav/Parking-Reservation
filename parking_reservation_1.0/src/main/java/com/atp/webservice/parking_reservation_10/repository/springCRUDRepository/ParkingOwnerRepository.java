package com.atp.webservice.parking_reservation_10.repository.springCRUDRepository;

import com.atp.webservice.parking_reservation_10.entities.ParkingOwner;
import org.springframework.data.repository.CrudRepository;

public interface ParkingOwnerRepository extends CrudRepository<ParkingOwner, Integer> {
}
