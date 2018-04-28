package com.atp.webservice.parking_reservation_10.services.mobileServices.stationVehicleTypeService.stationVehicleTypeServiceImp;

import com.atp.webservice.parking_reservation_10.entities.Station;
import com.atp.webservice.parking_reservation_10.entities.StationVehicleType;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationVehicleTypeCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.StationVehicleTypeModel;
import com.atp.webservice.parking_reservation_10.services.mobileServices.stationService.StationConverter;
import com.atp.webservice.parking_reservation_10.services.mobileServices.stationService.StationService;
import com.atp.webservice.parking_reservation_10.services.mobileServices.stationVehicleTypeService.StationVehicleTypeConverter;
import com.atp.webservice.parking_reservation_10.services.mobileServices.stationVehicleTypeService.StationVehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationVehicleTypeServiceImp implements StationVehicleTypeService {

    @Autowired
    private StationService stationService;

    @Autowired
    private StationVehicleTypeCRUDRepository stationVehicleTypeCRUDRepository;

    @Autowired
    private StationCRUDRepository stationCRUDRepository;

    @Autowired
    private StationConverter stationConverter;

    @Autowired
    private StationVehicleTypeConverter stationVehicleTypeConverter;

    @Override
    public StationVehicleTypeModel updateUsedSlots(int stationVehicleTypeID, int num) {
        StationVehicleType stationVehicleType = stationVehicleTypeCRUDRepository.findOne(stationVehicleTypeID);
        //not found
        if (stationVehicleType == null) {
            return null;
        }
        stationVehicleType.setUsedSlots(stationVehicleType.getUsedSlots() + num);
        stationService.updateUsedSlots(stationVehicleType.getStationID(), num);
        return stationVehicleTypeConverter.convertFromEntity(stationVehicleTypeCRUDRepository.save(stationVehicleType));
    }

    @Override
    public StationVehicleTypeModel updateHoldingSlots(int stationVehicleTypeID, int num) {
        StationVehicleType stationVehicleType = stationVehicleTypeCRUDRepository.findOne(stationVehicleTypeID);
        //not found
        if (stationVehicleType == null) {
            return null;
        }
        int newSlots = stationVehicleType.getHoldingSlots() + num;
        stationVehicleType.setHoldingSlots(newSlots > 0 ? newSlots : 0);
        stationService.updateHoldingSlots(stationVehicleType.getStationID(), num);
        return stationVehicleTypeConverter.convertFromEntity(stationVehicleTypeCRUDRepository.save(stationVehicleType));
    }

    @Override
    public StationVehicleTypeModel updateStationVehicleType(StationVehicleTypeModel stationVehicleTypeModel) {
        StationVehicleType stationVehicleType = stationVehicleTypeCRUDRepository.findOne(stationVehicleTypeModel.getId());
        //not found
        if (stationVehicleType == null) {
            return null;
        }
        StationVehicleType updatedStationVehicleType = stationVehicleTypeConverter.convertFromModel(stationVehicleTypeModel);
        //update station
        int numTotalSlotsChanged = updatedStationVehicleType.getTotalSlots() - stationVehicleType.getTotalSlots();
        int numUsedSlotsChanged = updatedStationVehicleType.getUsedSlots() - stationVehicleType.getUsedSlots();
        int numHoldingSlotsChanged = updatedStationVehicleType.getHoldingSlots() - stationVehicleType.getHoldingSlots();
        Station station = stationCRUDRepository.findOne(updatedStationVehicleType.getStationID());
        station.setTotalSlots(station.getTotalSlots() + numTotalSlotsChanged > 0 ? station.getTotalSlots() + numTotalSlotsChanged : 0);
        station.setUsedSlots(station.getUsedSlots() + numUsedSlotsChanged > 0 ? station.getUsedSlots() + numUsedSlotsChanged : 0);
        station.setHoldingSlots(station.getHoldingSlots() + numHoldingSlotsChanged > 0 ? station.getHoldingSlots() + numHoldingSlotsChanged : 0);
        stationService.updateStation(stationConverter.convertFromEntities(station));
        //update station vehicle type
        return stationVehicleTypeConverter.convertFromEntity(stationVehicleTypeCRUDRepository.save(updatedStationVehicleType));
    }
}
