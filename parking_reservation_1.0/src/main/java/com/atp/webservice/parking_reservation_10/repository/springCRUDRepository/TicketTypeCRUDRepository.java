package com.atp.webservice.parking_reservation_10.repository.springCRUDRepository;

import com.atp.webservice.parking_reservation_10.entities.TicketType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketTypeCRUDRepository extends CrudRepository<TicketType, Integer>{
}
