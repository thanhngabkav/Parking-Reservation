package com.atp.webservice.parking_reservation_10.services.mobileServices.stationService;

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


    public static StationModel convertFromEntities(com.atp.webservice.parking_reservation_10.entities.Station station){
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

}
