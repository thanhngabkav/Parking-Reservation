package com.atp.webservice.parking_reservation_10.services.mobileServices.stationService;


import com.atp.webservice.parking_reservation_10.services.mobileServices.stationService.StationService;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/stations")
public class StationController {

    @Autowired
    private StationService stationService;

    /**
     * Find stations by name
     * @param name station name
     * @return
     */
    @RequestMapping(value = "/")
    public ResponseEntity<List<Station>> findByName(@RequestParam(value = "name") String name){

        return  new ResponseEntity<List<Station>>(stationService.getStationByName(name), HttpStatus.OK);
    }

    /**
     * Find StationOverview by ID
     * @param stationID
     * @return
     */
    @RequestMapping(value = "/{id}")
    public ResponseEntity<Station> findOne(@PathVariable(value = "id") Integer stationID){

        return new ResponseEntity<Station>(stationService.getStationByID(stationID), HttpStatus.OK);

    }

}
