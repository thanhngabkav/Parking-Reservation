package com.atp.webservice.parking_reservation_10.services.mobileServices.stationService;


import com.atp.webservice.parking_reservation_10.services.mobileServices.messageService.MessageService;
import com.atp.webservice.parking_reservation_10.services.mobileServices.messageService.models.MessageStatus;
import com.atp.webservice.parking_reservation_10.services.mobileServices.messageService.models.MessageTopic;
import com.atp.webservice.parking_reservation_10.services.mobileServices.messageService.models.ServerMessage;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.StationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/stations")
public class StationController {

    @Autowired
    private StationService stationService;

    @Autowired
    private MessageService messageService;

    /**
     * Find stations by name
     *
     * @param name station name
     * @return
     */
    @RequestMapping(value = "/")
    public ResponseEntity<List<StationModel>> findByName(@RequestParam(value = "name") String name) {

        return new ResponseEntity<List<StationModel>>(stationService.getStationByName(name), HttpStatus.OK);
    }

    /**
     * Find StationOverview by ID
     *
     * @param stationID
     * @return
     */
    @RequestMapping(value = "/{id}")
    public ResponseEntity<StationModel> findOne(@PathVariable(value = "id") Integer stationID) {

        return new ResponseEntity<StationModel>(stationService.getStationByID(stationID), HttpStatus.OK);
    }

    /**
     * Add new Station
     * @param stationModel
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StationModel> addNewStation(@RequestBody StationModel stationModel) {
        StationModel m_stationModel = stationService.addNewStation(stationModel);
        if (m_stationModel == null)
            return new ResponseEntity<StationModel>(HttpStatus.BAD_REQUEST);
        //send message to client
        ServerMessage<StationModel> message = new ServerMessage<StationModel>();
        message.setTitle("New Station");
        message.setStatus(MessageStatus.NEW_STATION);
        message.setData(m_stationModel);
        messageService.sendMessageToTopic(message, MessageTopic.ADMIN_TOPIC);

        return new ResponseEntity<StationModel>(m_stationModel, HttpStatus.OK);
    }



}
