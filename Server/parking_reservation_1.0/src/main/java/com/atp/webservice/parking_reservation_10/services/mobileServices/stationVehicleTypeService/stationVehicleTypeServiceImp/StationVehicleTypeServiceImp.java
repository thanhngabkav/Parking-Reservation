package com.atp.webservice.parking_reservation_10.services.mobileServices.stationVehicleTypeService.stationVehicleTypeServiceImp;

import com.atp.webservice.parking_reservation_10.entities.StationVehicleType;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationVehicleTypeCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.StationVehicleTypeModel;
import com.atp.webservice.parking_reservation_10.services.mobileServices.stationService.StationService;
import com.atp.webservice.parking_reservation_10.services.mobileServices.stationVehicleTypeService.StationVehicleTypeConverter;
import com.atp.webservice.parking_reservation_10.services.mobileServices.stationVehicleTypeService.StationVehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;

public class StationVehicleTypeServiceImp implements StationVehicleTypeService {

    @Autowired
    private StationService stationService;

    @Autowired
    private StationVehicleTypeCRUDRepository stationVehicleTypeCRUDRepository;

    @Override
    public StationVehicleTypeModel updateUsedSlots(int stationVehicleTypeID, int num) {
        StationVehicleType stationVehicleType = stationVehicleTypeCRUDRepository.findOne(stationVehicleTypeID);
        //not found
        if(stationVehicleType == null){
            return null;
        }
        stationVehicleType.setUsedSlots(stationVehicleType.getUsedSlots() + num);
        stationService.updateUsedSlots(stationVehicleType.getStationID(), num);
        StationVehicleTypeConverter converter = new StationVehicleTypeConverter();
        return converter.convertFromEntity(stationVehicleTypeCRUDRepository.save(stationVehicleType));
    }

    @Override
    public StationVehicleTypeModel updateHoldingSlots(int stationVehicleTypeID, int num) {
        StationVehicleType stationVehicleType = stationVehicleTypeCRUDRepository.findOne(stationVehicleTypeID);
        //not found
        if(stationVehicleType == null){
            return null;
        }
        stationVehicleType.setHoldingSlots(stationVehicleType.getHoldingSlots() + num);
        stationService.updateHoldingSlots(stationVehicleType.getStationID(), num);
        StationVehicleTypeConverter converter = new StationVehicleTypeConverter();
        return converter.convertFromEntity(stationVehicleTypeCRUDRepository.save(stationVehicleType));
    }

    @Override
    public StationVehicleTypeModel updateStationVehicleType(StationVehicleTypeModel stationVehicleTypeModel) {
        StationVehicleType stationVehicleType = stationVehicleTypeCRUDRepository.findOne(stationVehicleTypeModel.getId());
        //not found
        if(stationVehicleType == null){
            return null;
        }

        StationVehicleTypeConverter converter = new StationVehicleTypeConverter();
        StationVehicleType updatedStationVehicleType = converter.convertfromModel(stationVehicleTypeModel);


        return null;
    }
}
