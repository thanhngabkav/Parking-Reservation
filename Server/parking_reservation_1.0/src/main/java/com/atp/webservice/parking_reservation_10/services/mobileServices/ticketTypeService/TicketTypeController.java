package com.atp.webservice.parking_reservation_10.services.mobileServices.ticketTypeService;

import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.TicketTypeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/api/tickettypes")
public class TicketTypeController {

    @Autowired
    private TicketTypeService ticketTypeService;

//
//    /**
//     * Find ticket types by service and station's vehicle type id
//     * @param serviceID
//     * @param stationID
//     * @return
//     */
//    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<TicketTypeModel>> getTicketTypesByServiceIDAndStationID(
//            @RequestParam("serviceID") int serviceID,
//            @RequestParam("stationID") int stationID){
//
//        List<TicketTypeModel> m_ticTicketTypeModels = ticketTypeService.getAllTicketTypesByServiceIDAndStationID(serviceID, stationID);
//        return  new ResponseEntity<List<TicketTypeModel>>(m_ticTicketTypeModels, HttpStatus.OK);
//    }


    /**
     * Find ticket types by service, station and vehicle type
     * @param serviceID
     * @param stationID
     * @param vehicleTypeID
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TicketTypeModel>> getTicketTypesByServiceIDAndStationIDAndVehicleTypeID(
            @RequestParam("serviceID") int serviceID,
            @RequestParam("stationID") int stationID,
            @RequestParam(value = "vehicleTypeID", required = false, defaultValue = DefaultValue.INT+"") int vehicleTypeID, HttpServletRequest request){

        System.out.println(vehicleTypeID);
        if(request.getParameter("vehicleTypeID") == null || vehicleTypeID == DefaultValue.INT){
            List<TicketTypeModel> m_ticTicketTypeModels = ticketTypeService.getAllTicketTypesByServiceIDAndStationID(serviceID, stationID);
            return  new ResponseEntity<List<TicketTypeModel>>(m_ticTicketTypeModels, HttpStatus.OK);
        }

        List<TicketTypeModel> m_ticTicketTypeModels = ticketTypeService.getTicketTypesByServiceIDAndStationIDAndVehicleTypeID(serviceID, stationID, vehicleTypeID);
        return  new ResponseEntity<List<TicketTypeModel>>(m_ticTicketTypeModels, HttpStatus.OK);
    }

    /**
     * Add new Ticket Type
     * @param ticketTypeModel
     * @return TicketTypeModel
     */
    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketTypeModel> addNewTicketType(@RequestBody TicketTypeModel ticketTypeModel){
        TicketTypeModel m_ticketTypeModel = ticketTypeService.addNewTicketType(ticketTypeModel);
        if(m_ticketTypeModel == null)
            return new ResponseEntity<TicketTypeModel>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<TicketTypeModel>(m_ticketTypeModel, HttpStatus.OK);
    }

    /**
     * Update Ticket Type
     * @param ticketTypeModel
     * @return TicketTypeModel
     */
    @RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TicketTypeModel> updateTicketType(@RequestBody TicketTypeModel ticketTypeModel){
        TicketTypeModel m_ticTypeModel = ticketTypeService.updateTicketType(ticketTypeModel);
        if(m_ticTypeModel == null){
            return new ResponseEntity<TicketTypeModel>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<TicketTypeModel>(m_ticTypeModel, HttpStatus.OK);
    }
}
