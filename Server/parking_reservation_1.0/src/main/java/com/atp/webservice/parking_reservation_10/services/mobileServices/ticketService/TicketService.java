package com.atp.webservice.parking_reservation_10.services.mobileServices.ticketService;

import com.atp.webservice.parking_reservation_10.entities.Ticket;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.TicketModel;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.TicketReservationModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {

    /**
     * Get list user's Tickets {@link TicketModel} by driver id {@link com.atp.webservice.parking_reservation_10.entities.Driver}
     * @param userID : user id
     * @param page page number
     * @return List {@link TicketModel}
     */
    List<TicketModel> getUserTickets(String userID, int page);

    /**
     * Get list ticket by driver ID and ticket status
     * @param driverID
     * @param status
     * @return
     */
    List<TicketModel> getListTicketByDriverIDAndStatus(String driverID, String status, int page);

    /**
     * Send Reservation request to StationOverview
     * @param ticketReservationModel {@link TicketReservationModel}
     * @return TicketModel {@link TicketModel} if success or null if not
     */
    TicketModel sendRequestForReservation(TicketReservationModel ticketReservationModel);


    /***
     * Update ticket (only necessary fields)
     * @param ticketModel
     * @return
     */
    TicketModel updateTicket(TicketModel ticketModel);

}
