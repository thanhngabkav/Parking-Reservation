package com.atp.webservice.parking_reservation_10.services.stationService;

import com.atp.webservice.parking_reservation_10.entities.Station;
import com.atp.webservice.parking_reservation_10.entities.StationVehicleType;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationVehicleTypeCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.models.StationModel;
import com.atp.webservice.parking_reservation_10.services.models.StationVehicleTypeModel;
import com.atp.webservice.parking_reservation_10.services.stationVehicleTypeService.StationVehicleTypeConverter;
import com.atp.webservice.parking_reservation_10.services.models.StationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class StationConverter {


    @Autowired
    private StationVehicleTypeCRUDRepository stationVehicleTypeCRUDRepository;

    @Autowired
    private StationVehicleTypeConverter stationVehicleTypeConverter;
    
    /**
     * Convert from {@link Station} to {@link StationModel}
     * @param station
     * @return
     */
    public  StationModel convertFromEntities(Station station){
        if(station == null)
            return null;
        StationModel mStationModel = new StationModel();

        List<StationVehicleTypeModel> m_stationVehicleTypes = new ArrayList<StationVehicleTypeModel>();
        mStationModel.setAddress(station.getAddress())
                .setCloseTime(station.getCloseTime())
                .setCoordinate(station.getCoordinate())
                .setCreatedDate(station.getCreatedDate())
                .setID(station.getID())
                .setApplicationID(station.getApplicationID())
                .setImageLink(station.getImageLink())
                .setStar(station.getStar())
                .setName(station.getName())
                .setOpenTime(station.getOpenTime())
                .setCloseTime(station.getCloseTime())
                .setOwnerID(UUID.fromString(station.getOwnerID()))
                .setParkingMapLink(station.getParkingMapLink())
                .setStatus(station.getStatus())
                .setTotalSlots(station.getTotalSlots())
                .setUsedSlots(station.getUsedSlots())
                .setHoldingSlots(station.getHoldingSlots())
                .setServices(station.getServices());
        List<StationVehicleType> m_stationVehicleTypeList = stationVehicleTypeCRUDRepository.findByStationID(station.getID());
        for(StationVehicleType m_stationVehicleType : m_stationVehicleTypeList){
            m_stationVehicleTypes.add(stationVehicleTypeConverter.convertFromEntity(m_stationVehicleType));
        }
        mStationModel.setStationVehicleTypes(m_stationVehicleTypes);

        return mStationModel;
    }


    /**
     * Convert from {@link StationModel} to {@link Station}
     * @param stationModel
     * @return Station
     */
    public Station convertToEntity(StationModel stationModel){
        Station en_station = new Station();
        en_station.setHoldingSlots(stationModel.getHoldingSlots())
                .setUsedSlots(stationModel.getUsedSlots())
                .setTotalSlots(stationModel.getTotalSlots())
                .setCoordinate(stationModel.getCoordinate());
        en_station.setApplicationID(stationModel.getApplicationID())
                .setName(stationModel.getName())
                .setAddress(stationModel.getAddress())
                .setCloseTime(stationModel.getCloseTime())
                .setID(stationModel.getID())
                .setOpenTime(stationModel.getOpenTime())
                .setOwnerID(stationModel.getOwnerID().toString())
                .setStar(stationModel.getStar())
                .setStatus(stationModel.getStatus())
                .setCreatedDate(stationModel.getCreatedDate())
                .setServices(stationModel.getServices());
        en_station.setImageLink(stationModel.getImageLink())
                .setParkingMapLink(stationModel.getParkingMapLink());
        List<StationVehicleType> m_stationVehicleTypes = new ArrayList<StationVehicleType>();
        for(StationVehicleTypeModel stationVehicleTypeModel : stationModel.getStationVehicleTypes()){
            m_stationVehicleTypes.add(stationVehicleTypeConverter.convertFromModel(stationVehicleTypeModel));
        }
        en_station.setStationVehicleTypes(m_stationVehicleTypes);

        return en_station;
    }

}
