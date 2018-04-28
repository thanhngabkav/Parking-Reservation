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


    /**
     * Add new station
     * @param stationModel
     * @return
     */
    StationModel addNewStation(StationModel stationModel);

    /**
     * Update holding slots
     * @param stationID
     * @param num
     * @return StationModel
     */
    StationModel updateHoldingSlots(int stationID, int num);

    /**
     * Update used slot and free slots too
     * @param stationID
     * @param num
     * @return
     */
    StationModel updateUsedSlots(int stationID, int num);

    /**
     * Update station
     * @param stationModel
     * @return
     */
    StationModel updateStation(StationModel stationModel);
}
