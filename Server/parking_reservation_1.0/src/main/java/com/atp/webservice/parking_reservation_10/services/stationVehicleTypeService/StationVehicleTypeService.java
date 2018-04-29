package com.atp.webservice.parking_reservation_10.services.stationVehicleTypeService;

import com.atp.webservice.parking_reservation_10.services.models.StationVehicleTypeModel;

public interface StationVehicleTypeService {

    /**
     * Update used slots for Station vehicle type and the Station
     * @param stationVehicleTypeID
     * @param num
     * @return
     */
    StationVehicleTypeModel updateUsedSlots(int stationVehicleTypeID, int num);

    /**
     * Update holding slots for Station vehicle type and Station
     * @param stationVehicleTypeID
     * @param num
     * @return
     */
    StationVehicleTypeModel updateHoldingSlots(int stationVehicleTypeID, int num);

    /**
     * Update Station vehicle type and Station
     * @param stationVehicleTypeModel
     * @return
     */
    StationVehicleTypeModel updateStationVehicleType(StationVehicleTypeModel stationVehicleTypeModel);

}
