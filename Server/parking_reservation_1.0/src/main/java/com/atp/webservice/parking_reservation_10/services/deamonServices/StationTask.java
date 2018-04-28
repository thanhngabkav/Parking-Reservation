package com.atp.webservice.parking_reservation_10.services.deamonServices;

public interface StationTask extends Runnable{

    void checkStations();
}
