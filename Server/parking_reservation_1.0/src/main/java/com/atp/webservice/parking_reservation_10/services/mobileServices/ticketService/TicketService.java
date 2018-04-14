package com.atp.webservice.parking_reservation_10.services.mobileServices.ticketService;

import com.atp.webservice.parking_reservation_10.services.mobileServices.models.Ticket;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

    /**
     * Get list used Tickets {@link Ticket} by driver id {@link com.atp.webservice.parking_reservation_10.entities.Driver}
     * @param userID : user id
     * @return List {@link Ticket}
     */
    List<Ticket> getUsedTickets(String userID);

    /**
     * Get list current using Tickets {@link Ticket} by driver id {@link com.atp.webservice.parking_reservation_10.entities.Driver}
     * @param userID
     * @return
     */
    List<Ticket> getUsingTickets(String userID);

    /**
     * Get list ticket by driver ID and ticket status
     * @param driverID
     * @param status
     * @return
     */
    List<Ticket> getListTicketByDriverIDAndStatus(String driverID, String status);

    /**
     * Send Reservation request to StationOverview
     * @param userID {@link com.atp.webservice.parking_reservation_10.entities.Driver} Driver ID
     * @param stationID {@link com.atp.webservice.parking_reservation_10.entities.Station} StationOverview ID
     * @return Ticket {@link Ticket} if success or null if not
     */
    Ticket sendRequestForReservation(String userID, String stationID);

}
