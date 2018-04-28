package com.atp.webservice.parking_reservation_10.repository.springCRUDRepository;

import com.atp.webservice.parking_reservation_10.entities.TicketType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketTypeCRUDRepository extends JpaRepository<TicketType, Integer> {

    List<TicketType>  findByStationVehicleTypeID(int stationVehicleTypeId);

    List<TicketType> findByServiceIDAndStationVehicleTypeID(int serviceID, int vehicleTypeID);
}
