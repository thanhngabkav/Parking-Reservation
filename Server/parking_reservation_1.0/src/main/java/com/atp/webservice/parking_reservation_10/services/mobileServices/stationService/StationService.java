package com.atp.webservice.parking_reservation_10.services.mobileServices.stationService;

import com.atp.webservice.parking_reservation_10.services.mobileServices.models.StationModel;

import java.util.List;

public interface StationService {

    /**
     * List list {@link StationModel} by station name
     * @param name
     * @return List {@link StationModel}
     */
    List<StationModel> getStationByName(String name);


    /**
     * Get {@link StationModel} by ID
     * @param stationID
     * @return StationOverview
     */
    StationModel getStationByID(int stationID);



}
