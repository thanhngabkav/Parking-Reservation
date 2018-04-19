package com.atp.webservice.parking_reservation_10.services.mobileServices.ticketService;

import com.atp.webservice.parking_reservation_10.entities.uitls.TicketStatus;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.TicketModel;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.TicketReservationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
     * @param userID DriverModel id
     * @return
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<TicketModel>> getAllTicketByUserID(@PathVariable("id") String userID){
        List<TicketModel> ticketModels = ticketService.getUsedTickets(userID);
        return new ResponseEntity<List<TicketModel>>(ticketModels, HttpStatus.OK);
    }

    /**
     * Get list tickets by status by user ID
     * @param userID
     * @param status
     * @return List {@link TicketModel} if found or null with status NOT_FOUND
     */
    @RequestMapping(value = "/user/{id}")
    public ResponseEntity<List<TicketModel>> getTicketsByStatus(@PathVariable("id") String userID,
                                                                @RequestParam("status") String status){
        if(status.equals(TicketStatus.IN_USE)){
            List<TicketModel> ticketModelList = ticketService.getUsingTickets(userID);
            return  new ResponseEntity<List<TicketModel>>(ticketModelList, HttpStatus.OK);
        }else {
            List<TicketModel> ticketModelList = ticketService.getListTicketByDriverIDAndStatus(userID, status);
            if(ticketModelList !=null)
                return  new ResponseEntity<List<TicketModel>>(ticketModelList, HttpStatus.OK);
            else
                return new ResponseEntity<List<TicketModel>>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Make reservation
     * @param ticketReservationModel {@link TicketReservationModel}
     * @return
     */
    @RequestMapping(value = "/reservation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketModel> makeReservation(@RequestBody TicketReservationModel ticketReservationModel){
        TicketModel mTicketModel = ticketService.sendRequestForReservation(ticketReservationModel);
        if(mTicketModel == null)
            return new ResponseEntity<TicketModel>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<TicketModel>(mTicketModel,HttpStatus.OK);
    }
}
