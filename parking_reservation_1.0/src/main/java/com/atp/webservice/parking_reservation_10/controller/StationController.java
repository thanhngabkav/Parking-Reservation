package com.atp.webservice.parking_reservation_10.controller;


import com.atp.webservice.parking_reservation_10.services.mobileServices.StationService;
import com.atp.webservice.parking_reservation_10.services.mobileServices.presenter.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
     * Find Station by ID
     * @param stationID
     * @param result
     * @return
     */
    @RequestMapping(value = "/{id}")
    public ResponseEntity<Station> findOne(@PathVariable(value = "id") int stationID, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<Station>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<Station>(stationService.getStationByID(stationID), HttpStatus.OK);

    }

}
