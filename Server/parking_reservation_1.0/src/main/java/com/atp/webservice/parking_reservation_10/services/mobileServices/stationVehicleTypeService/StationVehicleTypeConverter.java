package com.atp.webservice.parking_reservation_10.services.mobileServices.stationVehicleTypeService;

import com.atp.webservice.parking_reservation_10.entities.StationVehicleType;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.StationModel;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.StationVehicleTypeModel;

public class StationVehicleTypeConverter {

    /**
     * Convert from {@link StationVehicleTypeModel} to {@link StationVehicleType}
     * @param stationVehicleType
     * @return StationVehicleType
     */
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

    /**
     *Convert from {@link StationVehicleType}  to {@link StationVehicleTypeModel}
     * @param stationVehicleTypeModel
     * @return StationVehicleTypeModel
     */
    public StationVehicleType convertfromModel(StationVehicleTypeModel stationVehicleTypeModel){
        StationVehicleType m_stationVehicleType = new StationVehicleType();
        m_stationVehicleType.setVehicleTypeId(stationVehicleTypeModel.getVehicleTypeId());
        m_stationVehicleType.setUsedSlots(stationVehicleTypeModel.getUsedSlots());
        m_stationVehicleType.setTotalSlots(stationVehicleTypeModel.getTotalSlots());
        m_stationVehicleType.setStationID(stationVehicleTypeModel.getStationID());
        m_stationVehicleType.setHoldingSlots(stationVehicleTypeModel.getHoldingSlots());
        m_stationVehicleType.setId(stationVehicleTypeModel.getId());

        return m_stationVehicleType;

    }
}
