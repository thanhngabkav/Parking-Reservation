package com.atp.webservice.parking_reservation_10.controller;

import com.atp.webservice.parking_reservation_10.services.mobileServices.MapService;
import com.atp.webservice.parking_reservation_10.services.mobileServices.presenter.StationLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/api/maps")
public class MapController {

    @Autowired
    private MapService mapService;

    /**
     * Get list location near a place(lat, lng) in a radius
     * @param lat lat coordinate
     * @param lng long coordinate
     * @param rad radius
     * @param response {@link HttpServletResponse}
     * @param result binding result
     * @return
     */
    @RequestMapping(value = "/place", method = RequestMethod.GET)
    public ResponseEntity<List<StationLocation>> getNearByLocation(@RequestParam("lat") double lat, @RequestParam("lng") double lng,
                                                                   @RequestParam("rad") double rad, HttpServletResponse response,
                                                                   BindingResult result){
        if(result.hasErrors()){
            return  new ResponseEntity<List<StationLocation>>(HttpStatus.BAD_REQUEST);
        }else{
            StationLocation place  = new StationLocation(-1,lat, lng);
            List<StationLocation> stationLocations = mapService.getNearByParking(place, rad);
            return new ResponseEntity<List<StationLocation>>(stationLocations,HttpStatus.OK);
        }
    }
}
