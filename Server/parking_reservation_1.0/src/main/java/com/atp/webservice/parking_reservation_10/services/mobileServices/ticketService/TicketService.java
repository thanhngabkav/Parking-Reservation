package com.atp.webservice.parking_reservation_10.services.mobileServices.ticketService;

import com.atp.webservice.parking_reservation_10.services.mobileServices.models.TicketModel;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.TicketReservationModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

    /**
     * Get list used Tickets {@link TicketModel} by driver id {@link com.atp.webservice.parking_reservation_10.entities.Driver}
     * @param userID : user id
     * @return List {@link TicketModel}
     */
    List<TicketModel> getUsedTickets(String userID);

    /**
     * Get list current using Tickets {@link TicketModel} by driver id {@link com.atp.webservice.parking_reservation_10.entities.Driver}
     * @param userID
     * @return
     */
    List<TicketModel> getUsingTickets(String userID);

    /**
     * Get list ticket by driver ID and ticket status
     * @param driverID
     * @param status
     * @return
     */
    List<TicketModel> getListTicketByDriverIDAndStatus(String driverID, String status);

    /**
     * Send Reservation request to StationOverview
     * @param ticketReservationModel {@link TicketReservationModel}
     * @return TicketModel {@link TicketModel} if success or null if not
     */
    TicketModel sendRequestForReservation(TicketReservationModel ticketReservationModel);

}
