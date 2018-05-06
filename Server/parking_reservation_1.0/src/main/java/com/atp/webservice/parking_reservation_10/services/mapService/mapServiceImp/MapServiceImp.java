package com.atp.webservice.parking_reservation_10.services.mapService.mapServiceImp;

import com.atp.webservice.parking_reservation_10.entities.Station;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.algorithms.MapHelper;
import com.atp.webservice.parking_reservation_10.services.mapService.MapService;
import com.atp.webservice.parking_reservation_10.services.models.StationLocationModel;
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
    public List<StationLocationModel> getNearByParking(StationLocationModel stationLocationModel, double radius) {
        MapHelper mapHelper = new MapHelper();
        // this is the bad solution, improvement it if have time
        List<Station> stationList = (List<Station>) stationCRUDRepository.findAll();
        double lng1, lat1, lng2, lat2;
        lng1 = stationLocationModel.getLng();
        lat1= stationLocationModel.getLat();
        List<StationLocationModel> result = new ArrayList<StationLocationModel>();
        for(Station station : stationList){
            try{
                //split station coordinate to get lat and lng
                lng2 = Double.parseDouble(station.getCoordinate().split(",")[1]);
                lat2 = Double.parseDouble(station.getCoordinate().split(",")[0]);
                if(mapHelper.distance(lat1,lng1,lat2,lng2,DISTANCE_UNIT) <= radius){
                    StationLocationModel location = new StationLocationModel(station.getID(),lat2,lng2);
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

    @Override
    public List<StationLocationModel> getNearByStationsService(StationLocationModel stationLocationModel, double radius, int serviceID) {
        MapHelper mapHelper = new MapHelper();
        // this is the bad solution, improvement it if have time
        List<Station> stationList = (List<Station>) stationCRUDRepository.findAll();
        double lng1, lat1, lng2, lat2;
        lng1 = stationLocationModel.getLng();
        lat1= stationLocationModel.getLat();
        List<StationLocationModel> result = new ArrayList<StationLocationModel>();
        for(Station station : stationList){
            try{
                //split station coordinate to get lat and lng
                lng2 = Double.parseDouble(station.getCoordinate().split(",")[1]);
                lat2 = Double.parseDouble(station.getCoordinate().split(",")[0]);
                if(mapHelper.distance(lat1,lng1,lat2,lng2,DISTANCE_UNIT) <= radius){
                    List<com.atp.webservice.parking_reservation_10.entities.Service> stationServices = station.getServices();
                    for(com.atp.webservice.parking_reservation_10.entities.Service service : stationServices){
                        if(service.getServiceID() == serviceID){
                            StationLocationModel location = new StationLocationModel(station.getID(),lat2,lng2);
                            location.setTotalSlot(station.getTotalSlots());
                            location.setUsedSlot(station.getUsedSlots());
                            result.add(location);
                            break;
                        }
                    }
                }
            }catch(Exception ex){
                logger.warn("Can not parse StationOverview coordinate to double. Error:  " +
                        ex.getMessage());
            }
        }
        return result;
    }
}
