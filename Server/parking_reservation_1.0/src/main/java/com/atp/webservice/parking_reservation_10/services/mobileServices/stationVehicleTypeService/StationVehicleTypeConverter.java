package com.atp.webservice.parking_reservation_10.services.mobileServices.stationVehicleTypeService;

import com.atp.webservice.parking_reservation_10.entities.StationVehicleType;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.StationVehicleTypeModel;

public class StationVehicleTypeConverter {

    public StationVehicleTypeModel convertFromEntity(StationVehicleType stationVehicleType){

        StationVehicleTypeModel model = new StationVehicleTypeModel();
        model.setId(stationVehicleType.getId());
        model.setHoldingSlots(stationVehicleType.getHoldingSlots());
        model.setStationID(stationVehicleType.getStationID());
        model.setTotalSlots(stationVehicleType.getTotalSlots());
        model.setUsedSlots(stationVehicleType.getUsedSlots());
        model.setVehicleTypeId(stationVehicleType.getVehicleTypeId());

        return model;
    }
}
