package com.atp.webservice.parking_reservation_10.services.mobileServices.stationService;

import com.atp.webservice.parking_reservation_10.entities.sparkPresenter.StationPresenter;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.StationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class StationConverter {


    public static StationModel convertFromEntities(com.atp.webservice.parking_reservation_10.entities.Station station){
        if(station == null)
            return null;
        StationModel mStationModel = new StationModel();
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
                .setServices(station.getServices())
                .setTicketTypes(station.getTicketTypes());

        return mStationModel;
    }

}
