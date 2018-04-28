package com.atp.webservice.parking_reservation_10.repository.springCRUDRepository;

import com.atp.webservice.parking_reservation_10.entities.Ticket;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketCRUDRepository extends JpaRepository<Ticket, String> {

    List<Ticket> findTicketsByDriverIDAndStatus(String driverID, String status);

    List<Ticket> findTicketsByDriverIDAndStatus(@Param("driverID") String driverID,@Param("status") String status, Pageable pageRequest);

    List<Ticket> findByStatus(String status);

    List<Ticket> findByStatus(@Param("status") String status, Pageable pageRequest);

    List<Ticket>findByDriverID(String driverID);

    List<Ticket>findByDriverID(@Param("driverID") String driverID, Pageable pageRequest);

}
