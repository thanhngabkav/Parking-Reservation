package com.atp.webservice.parking_reservation_10.repository.springCRUDRepository;

import com.atp.webservice.parking_reservation_10.entities.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceCRUDRepository extends JpaRepository<Service, Integer> {
}
