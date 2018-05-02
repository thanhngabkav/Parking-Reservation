package com.atp.webservice.parking_reservation_10.repository.springCRUDRepository;

import com.atp.webservice.parking_reservation_10.entities.Vehicle;
import com.atp.webservice.parking_reservation_10.entities.VehicleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VehicleCRUDRepository extends JpaRepository<Vehicle, String> {

    List<Vehicle> findByDriverID(String driverID);

    List<Vehicle> findByDriverID(String driverID, PageRequest pageRequest);

    //@Query(value = "select v from VehicleModel v where v.vehicleTypeID = ?1", nativeQuery = true)
    List<Vehicle> findByVehicleTypeID(@Param("vehicleTypeID") int typeID, Pageable pageRequest);

    List<Vehicle> findAllByVehicleTypeID(int typeID);

}
