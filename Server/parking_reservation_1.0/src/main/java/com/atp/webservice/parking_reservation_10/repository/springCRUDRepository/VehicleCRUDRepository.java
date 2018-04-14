package com.atp.webservice.parking_reservation_10.repository.springCRUDRepository;

import com.atp.webservice.parking_reservation_10.entities.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

@Repository
public interface VehicleCRUDRepository extends JpaRepository<Vehicle, String> {

    List<Vehicle> findByDriveID(String driverID);

    List<Vehicle> findByDriveID(String driverID, PageRequest pageRequest);

    List<Vehicle> findByVehicleTypeID(int typeID, PageRequest pageRequest);
}
