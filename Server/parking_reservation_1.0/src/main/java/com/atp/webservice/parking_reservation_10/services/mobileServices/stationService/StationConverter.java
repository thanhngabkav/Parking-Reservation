package com.atp.webservice.parking_reservation_10.services.mobileServices.stationService;

import com.atp.webservice.parking_reservation_10.entities.Station;
import com.atp.webservice.parking_reservation_10.entities.StationVehicleType;
import com.atp.webservice.parking_reservation_10.entities.TicketType;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.StationModel;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.StationVehicleTypeModel;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.TicketTypeModel;
import com.atp.webservice.parking_reservation_10.services.mobileServices.stationVehicleTypeService.StationVehicleTypeConverter;
import com.atp.webservice.parking_reservation_10.services.mobileServices.ticketTypeService.TicketTypeConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class StationConverter {


    /**
     * Convert from {@link Station} to {@link StationModel}
     * @param station
     * @return
     */
    public static StationModel convertFromEntities(Station station){
        if(station == null)
            return null;
        StationModel mStationModel = new StationModel();

        List<StationVehicleTypeModel> m_stationVehicleTypes = new ArrayList<StationVehicleTypeModel>();
        StationVehicleTypeConverter m_conConverter = new StationVehicleTypeConverter();
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

        for(StationVehicleType m_stationVehicleType : station.getStationVehicleTypes()){
            m_stationVehicleTypes.add(m_conConverter.convertFromEntity(m_stationVehicleType));
        }
        mStationModel.setStationVehicleTypes(m_stationVehicleTypes);

        return mStationModel;
    }


    /**
     * Convert from {@link StationModel} to {@link Station}
     * @param stationModel
     * @return Station
     */
    public static Station convertToEntity(StationModel stationModel){
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
        StationVehicleTypeConverter m_converter = new StationVehicleTypeConverter();
        for(StationVehicleTypeModel stationVehicleTypeModel : stationModel.getStationVehicleTypes()){
            m_stationVehicleTypes.add(m_converter.convertfromModel(stationVehicleTypeModel));
        }
        en_station.setStationVehicleTypes(m_stationVehicleTypes);

        return en_station;
    }

}
