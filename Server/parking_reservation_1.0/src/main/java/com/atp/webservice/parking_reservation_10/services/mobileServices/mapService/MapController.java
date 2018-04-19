package com.atp.webservice.parking_reservation_10.services.mobileServices.mapService;

import com.atp.webservice.parking_reservation_10.services.mobileServices.models.StationLocationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     *
     * @param lat      lat coordinate
     * @param lng      long coordinate
     * @param rad      radius
     * @param response {@link HttpServletResponse}
     * @return
     */
    @RequestMapping(value = "/place", method = RequestMethod.GET)
    public ResponseEntity<List<StationLocationModel>> getNearByLocation(@RequestParam("lat") double lat, @RequestParam("lng") double lng,
                                                                        @RequestParam("rad") double rad, HttpServletResponse response) {

        StationLocationModel place = new StationLocationModel(-1, lat, lng);
        List<StationLocationModel> stationLocationModels = mapService.getNearByParking(place, rad);
        return new ResponseEntity<List<StationLocationModel>>(stationLocationModels, HttpStatus.OK);

    }
}
