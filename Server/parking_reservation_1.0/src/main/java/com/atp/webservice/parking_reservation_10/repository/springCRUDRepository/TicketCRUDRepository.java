package com.atp.webservice.parking_reservation_10.repository.springCRUDRepository;

import com.atp.webservice.parking_reservation_10.entities.Ticket;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TicketCRUDRepository extends JpaRepository<Ticket, String> {

    List<Ticket> findTicketsByDriverIDAndStatusOrderByCreatedTimeDesc(String driverID, String status);

    List<Ticket> findTicketsByDriverIDAndStatusOrderByCreatedTimeDesc(@Param("driverID") String driverID,@Param("status") String status, Pageable pageRequest);

    List<Ticket> findByStatus(@Param("status") String status);

    List<Ticket> findByStatus(@Param("status") String status, Pageable pageRequest);

    List<Ticket> findByDriverID(String driverID);

    List<Ticket> findByDriverID(@Param("driverID") String driverID, Pageable pageRequest);

    List<Ticket> findByStationIDAndCreatedTimeAfterAndCreatedTimeBefore(int stationID,Timestamp begin, Timestamp end);

}
