package com.atp.webservice.parking_reservation_10.repository.springCRUDRepository;

import com.atp.webservice.parking_reservation_10.entities.Owner;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface OwnerCRUDRepository extends JpaRepository<Owner, String> {

    List<Owner> findAllByOrderByCreatedTimeDesc(Pageable pageable);

}
