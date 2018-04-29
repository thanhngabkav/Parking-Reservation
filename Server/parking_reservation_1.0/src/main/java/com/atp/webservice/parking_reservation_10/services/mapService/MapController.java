package com.atp.webservice.parking_reservation_10.services.mapService;

import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;
import com.atp.webservice.parking_reservation_10.services.models.StationLocationModel;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/maps")
public class MapController {

    private static Logger logger = Logger.getLogger(MapController.class);

    @Autowired
    private MapService mapService;

    /**
     * Get list location near a place(lat, lng) in a radius
     *
     * @param lat      lat coordinate
     * @param lng      long coordinate
     * @param rad      radius
     * @param response {@link HttpServletResponse}
     * @return
     */
    @RequestMapping(value = "/place", method = RequestMethod.GET)
    public ResponseEntity<List<StationLocationModel>> getNearByLocation(@RequestParam("lat") double lat, @RequestParam("lng") double lng,
                                                                        @RequestParam("rad") double rad,
                                                                        @RequestParam(value = "serviceID", required = false, defaultValue = DefaultValue.INT + "") int serviceID,
                                                                        HttpServletResponse response,
                                                                        HttpServletRequest request) {

        // start time
        long startTime = System.currentTimeMillis();

        StationLocationModel place = new StationLocationModel(-1, lat, lng);
        List<StationLocationModel> stationLocationModels = new ArrayList<StationLocationModel>();
        if (serviceID == DefaultValue.INT || request.getParameter("serviceID") == null) {
            stationLocationModels = mapService.getNearByParking(place, rad);
        } else {
            stationLocationModels = mapService.getNearByStationsService(place, rad, serviceID);
        }
        //end time
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        logger.info("Time execute: " + elapsedTime + " millisecond. With " + stationLocationModels.size() + " results");
        return new ResponseEntity<List<StationLocationModel>>(stationLocationModels, HttpStatus.OK);

    }

}
