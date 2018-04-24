package com.atp.webservice.parking_reservation_10.repository.springCRUDRepository;

import com.atp.webservice.parking_reservation_10.entities.StationVehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationVehicleTypeCRUDRepository extends JpaRepository<StationVehicleType, Integer> {

    List<StationVehicleType> findByStationID(int stationID);

    List<StationVehicleType> findByStationIDAndAndVehicleTypeId(int stationID, int vehicleTypeID);
}
