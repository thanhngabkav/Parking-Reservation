package com.atp.webservice.parking_reservation_10.config.initial;


import com.atp.webservice.parking_reservation_10.services.deamonServices.StationTask;
import com.atp.webservice.parking_reservation_10.services.deamonServices.deamonServicesImp.StationTaskImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Component
public class WorkerInitial {

    @Autowired
    private StationTask stationTask;

    public void startDaemondThreads(){
        Thread stationChecker = new Thread(stationTask);
        stationChecker.setDaemon(false);
        stationChecker.start();

    }
}
