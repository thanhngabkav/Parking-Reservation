package com.atp.webservice.parking_reservation_10.controller;

import com.atp.webservice.parking_reservation_10.entities.uitls.TicketStatus;
import com.atp.webservice.parking_reservation_10.services.mobileServices.TicketService;
import com.atp.webservice.parking_reservation_10.services.mobileServices.presenter.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    /**
     * Get all user's tickets by user ID
     * @param userID User id
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Ticket>> getAllTicketByUserID(@PathVariable("id") String userID){
        List<Ticket> tickets = ticketService.getUsedTickets(userID);
        return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);
    }

    /**
     * Get list tickets by status by user ID
     * @param userID
     * @param status
     * @return List {@link Ticket} if found or null with status NOT_FOUND
     */
    @RequestMapping(value = "/ticket/user/{id}")
    public ResponseEntity<List<Ticket>> getTicketsByStatus(@PathVariable("id") String userID,
                                                       @RequestParam("status") String status){
        if(status.equals(TicketStatus.IN_USE)){
            List<Ticket> ticketList = ticketService.getUsingTickets(userID);
            return  new ResponseEntity<List<Ticket>>(ticketList, HttpStatus.OK);
        }else {
            List<Ticket> ticketList = ticketService.getListTicketByDriverIDAndStatus(userID, status);
            if(ticketList!=null)
                return  new ResponseEntity<List<Ticket>>(ticketList, HttpStatus.OK);
            else
                return new ResponseEntity<List<Ticket>>(HttpStatus.NOT_FOUND);
        }
    }


}
