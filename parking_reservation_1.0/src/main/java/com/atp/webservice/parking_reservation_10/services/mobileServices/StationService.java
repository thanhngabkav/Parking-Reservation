package com.atp.webservice.parking_reservation_10.services.mobileServices;

import com.atp.webservice.parking_reservation_10.services.mobileServices.presenter.Station;

import java.util.List;

public interface StationService {

    /**
     * List list {@link Station} by station name
     * @param name
     * @return List {@link Station}
     */
    List<Station> getStationByName(String name);


    /**
     * Get {@link Station} by ID
     * @param stationID
     * @return Station
     */
    Station getStationByID(int stationID);
}
