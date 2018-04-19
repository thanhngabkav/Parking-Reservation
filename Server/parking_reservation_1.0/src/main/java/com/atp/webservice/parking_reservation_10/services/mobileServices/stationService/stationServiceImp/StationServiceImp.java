package com.atp.webservice.parking_reservation_10.services.mobileServices.stationService.stationServiceImp;

import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.StationModel;
import com.atp.webservice.parking_reservation_10.services.mobileServices.stationService.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationServiceImp implements StationService {

    @Autowired
    private StationCRUDRepository stationCRUDRepository;

    @Override
    public List<StationModel> getStationByName(String name) {
       List<com.atp.webservice.parking_reservation_10.entities.Station> stations = stationCRUDRepository.findStationsByName(name);
       List<StationModel> listStationModelPresenter = new ArrayList<StationModel>();
       for(com.atp.webservice.parking_reservation_10.entities.Station station : stations){
           StationModel stationModelPresenter = new StationModel();
           stationModelPresenter.convertFromEntities(station);
            listStationModelPresenter.add(stationModelPresenter);
       }

       return listStationModelPresenter;
    }

    @Override
    public StationModel getStationByID(int stationID) {
        StationModel stationModel =  new StationModel();
        stationModel.convertFromEntities(stationCRUDRepository.findOne(stationID));
        return stationModel;
    }
}
