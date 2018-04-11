package com.atp.webservice.parking_reservation_10.services.mobileServices.ServicesImp;

import com.atp.webservice.parking_reservation_10.entities.sparkPresenter.StationPresenter;
import com.atp.webservice.parking_reservation_10.entities.uitls.StationStatus;
import com.atp.webservice.parking_reservation_10.repository.sparkRepository.StationRepository;
import com.atp.webservice.parking_reservation_10.services.mobileServices.DriverService;
import com.atp.webservice.parking_reservation_10.services.mobileServices.presenter.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DriverServiceImp implements DriverService {

    @Autowired
    private StationRepository stationRepository;

    @Override
    public Ticket sendRequestForReservation(String userID, String stationID) {

        StationPresenter station = stationRepository.findOne(Integer.parseInt(stationID));
        if(station == null || !(station.getStatus().equals(StationStatus.ACTIVE))){
            return  null;
        }

        return null;
    }
}
