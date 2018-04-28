package com.atp.webservice.parking_reservation_10.services.deamonServices.deamonServicesImp;

import com.atp.webservice.parking_reservation_10.entities.Station;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.TicketCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.deamonServices.StationTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationTaskImp implements StationTask, Runnable {

    @Autowired
    private  StationCRUDRepository stationCRUDRepository;

    @Autowired
    private TicketCRUDRepository ticketCRUDRepository;



    @Override
    public void checkStations() {

        List<Station> stationList = stationCRUDRepository.findAll();

    }

    @Override
    public void run() {

    }
}
