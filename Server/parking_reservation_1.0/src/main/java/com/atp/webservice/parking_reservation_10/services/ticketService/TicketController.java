package com.atp.webservice.parking_reservation_10.services.ticketService;

import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;
import com.atp.webservice.parking_reservation_10.entities.uitls.TicketStatus;
import com.atp.webservice.parking_reservation_10.services.models.TicketModel;
import com.atp.webservice.parking_reservation_10.services.models.TicketReservationModel;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
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
     * Get list tickets by status by user ID
     *
     * @param userID
     * @param status
     * @return List {@link TicketModel} if found or null with status NOT_FOUND
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<TicketModel>> getTicketsByStatus(@PathVariable("id") String userID,
                                                                @RequestParam(value = "page", required = false, defaultValue = DefaultValue.INT +"") int page,
                                                                @RequestParam(value = "status", required = false, defaultValue = DefaultValue.STRING) String status,
                                                                HttpServletRequest request) {

        //get all
        if (request.getParameter("status") == null || status.equals(DefaultValue.STRING)) {
            List<TicketModel> ticketModels = ticketService.getUserTickets(userID, page);
            return new ResponseEntity<List<TicketModel>>(ticketModels, HttpStatus.OK);
        }

        if(request.getParameter("page") == null || page == DefaultValue.INT){
            List<TicketModel> ticketModels = ticketService.getAllTicketsByDriverIDAndStatus(userID, status);
            return new ResponseEntity<List<TicketModel>>(ticketModels, HttpStatus.OK);
        }

        List<TicketModel> ticketModelList = ticketService.getListTicketByDriverIDAndStatus(userID, status, page);
        if (ticketModelList != null) {
            return new ResponseEntity<List<TicketModel>>(ticketModelList, HttpStatus.OK);
        } else {

            return new ResponseEntity<List<TicketModel>>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Make reservation
     *
     * @param ticketReservationModel {@link TicketReservationModel}
     * @return
     */
    @RequestMapping(value = "/reservation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketModel> makeReservation(@RequestBody TicketReservationModel ticketReservationModel) {
        TicketModel mTicketModel = ticketService.sendRequestForReservation(ticketReservationModel);
        if (mTicketModel == null)
            return new ResponseEntity<TicketModel>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<TicketModel>(mTicketModel, HttpStatus.OK);
    }


    /**
     * Update ticket
     *
     * @param ticketModel {@link TicketModel}
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketModel> makeReservation(@RequestBody TicketModel ticketModel) {
        TicketModel mTicketModel = ticketService.updateTicket(ticketModel);
        if (mTicketModel == null)
            return new ResponseEntity<TicketModel>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<TicketModel>(mTicketModel, HttpStatus.OK);
    }

    /**
     * Get ticket by ID
     *
     * @param ticketID
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketModel> makeReservation(@PathVariable("id") String ticketID) {
        TicketModel mTicketModel = ticketService.getTicketById(ticketID);
        if (mTicketModel == null)
            return new ResponseEntity<TicketModel>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<TicketModel>(mTicketModel, HttpStatus.OK);
    }

}
