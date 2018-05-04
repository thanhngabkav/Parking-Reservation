package com.atp.webservice.parking_reservation_10.repository.springCRUDRepository;

import com.atp.webservice.parking_reservation_10.entities.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StationCRUDRepository extends JpaRepository<Station, Integer> {

    List<Station> findStationsByName(String name);

    long countByOwnerID(String ownerID);
}
