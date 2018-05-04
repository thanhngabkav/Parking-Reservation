package com.atp.webservice.parking_reservation_10.services.stationService;

import com.atp.webservice.parking_reservation_10.services.models.ChartDataModel;
import com.atp.webservice.parking_reservation_10.services.models.StationModel;

import java.io.IOException;
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

    /**
     * Upload new image and save in server
     * @param image
     * @param stationID
     * @return
     * @throws IOException
     */
    byte[] upLoadNewImage(byte[] image, int stationID) throws IOException;

    /**
     * Update server image
     * @param image
     * @param fileName
     * @param stationID
     * @return
     * @throws IOException
     */
    byte[] updateImage(byte[] image, String fileName, int stationID) throws IOException;

    /**
     * delete image in server
     * @param fileName
     * @param stationID
     * @throws IOException
     */
    void deleteImage(String fileName, int stationID) throws IOException;


    /**
     * Get station's image in server
     * @param stationID
     * @return
     * @throws IOException
     */
    List<byte[]> getAllStationImage(int stationID) throws IOException;

    /**
     * Get report data in range of years
     * @param stationID
     * @param year
     * @return
     */
    List<ChartDataModel> getStationReportDataByYear(int stationID,int year);


}
