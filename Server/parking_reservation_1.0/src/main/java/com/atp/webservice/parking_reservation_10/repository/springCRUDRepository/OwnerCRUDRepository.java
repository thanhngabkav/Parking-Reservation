package com.atp.webservice.parking_reservation_10.repository.springCRUDRepository;

import com.atp.webservice.parking_reservation_10.entities.Owner;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OwnerCRUDRepository extends JpaRepository<Owner, String> {

}
