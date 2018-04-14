package com.atp.webservice.parking_reservation_10.services.mobileServices.mapService.mapServiceImp;

import com.atp.webservice.parking_reservation_10.entities.Station;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.algorithms.MapHelper;
import com.atp.webservice.parking_reservation_10.services.mobileServices.mapService.MapService;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.StationLocation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapServiceImp implements MapService {

    public static final char DISTANCE_UNIT = 'K';

    private static Logger logger = Logger.getLogger(MapServiceImp.class);

    @Autowired
    StationCRUDRepository stationCRUDRepository;

    @Override
    public List<StationLocation> getNearByParking(StationLocation stationLocation, double radius) {
        MapHelper mapHelper = new MapHelper();
        // this is the bad solution, improvement it if have time
        List<Station> stationList = (List<Station>) stationCRUDRepository.findAll();
        double lng1, lat1, lng2, lat2;
        lng1 = stationLocation.getLng();
        lat1= stationLocation.getLat();
        List<StationLocation> result = new ArrayList<StationLocation>();
        for(Station station : stationList){
            try{
                //split station coordinate to get lat and lng
                lng2 = Double.parseDouble(station.getCoordinate().split(",")[1]);
                lat2 = Double.parseDouble(station.getCoordinate().split(",")[0]);
                if(mapHelper.distance(lat1,lng1,lat2,lng2,DISTANCE_UNIT) <= radius){
                    StationLocation location = new StationLocation(station.getID(),lat2,lng2);
                    location.setTotalSlot(station.getTotalSlots());
                    location.setUsedSlot(station.getUsedSlots());
                    result.add(location);
                }
            }catch(Exception ex){
                logger.warn("Can not parse StationOverview coordinate to double. Error:  " +
                        ex.getMessage());
            }
        }
        return result;
    }
}
