package com.atp.webservice.parking_reservation_10.services.mobileServices.ServicesImp;

import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.mobileServices.StationService;
import com.atp.webservice.parking_reservation_10.services.mobileServices.presenter.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationServiceImp implements StationService {

    @Autowired
    private StationCRUDRepository stationCRUDRepository;

    @Override
    public List<Station> getStationByName(String name) {
       List<com.atp.webservice.parking_reservation_10.entities.Station> stations = stationCRUDRepository.findStationsByName(name);
       List<Station> listStationPresenter = new ArrayList<Station>();
       for(com.atp.webservice.parking_reservation_10.entities.Station station : stations){
           Station stationPresenter = new Station();
           stationPresenter.convertFromEntities(station);
            listStationPresenter.add(stationPresenter);
       }

       return  listStationPresenter;
    }

    @Override
    public Station getStationByID(int stationID) {
        Station station =  new Station();
        station.convertFromEntities(stationCRUDRepository.findOne(stationID));
        return station;
    }
}
