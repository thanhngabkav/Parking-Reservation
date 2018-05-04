package com.atp.webservice.parking_reservation_10.services.ticketTypeService;

import com.atp.webservice.parking_reservation_10.entities.StationVehicleType;
import com.atp.webservice.parking_reservation_10.entities.TicketType;
import com.atp.webservice.parking_reservation_10.entities.VehicleType;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationVehicleTypeCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.VehicleTypeCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.models.TicketTypeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class TicketTypeConverter {

    @Autowired
    private StationVehicleTypeCRUDRepository stationVehicleTypeCRUDRepository;

    @Autowired
    private VehicleTypeCRUDRepository vehicleTypeCRUDRepository;

    public TicketTypeModel convertFromEntity(TicketType enTicketType){

        if(enTicketType == null)
            return null;

        TicketTypeModel ticketTypeModel = new TicketTypeModel();
        ticketTypeModel.setHoldingTime(enTicketType.getHoldingTime());
        ticketTypeModel.setName(enTicketType.getName());
        ticketTypeModel.setPrice(enTicketType.getPrice());
        ticketTypeModel.setServiceID(enTicketType.getServiceID());
        ticketTypeModel.setStationVehicleTypeID(enTicketType.getStationVehicleTypeID());
        ticketTypeModel.setTicketTypeID(enTicketType.getID());
        StationVehicleType stationVehicleType = stationVehicleTypeCRUDRepository.findOne(enTicketType.getStationVehicleTypeID());
        VehicleType vehicleType = vehicleTypeCRUDRepository.findOne(stationVehicleType.getVehicleTypeId());
        ticketTypeModel.setVehicleTypeName(vehicleType.getName());

        return  ticketTypeModel;

    }

    public TicketType convertFromModel(TicketTypeModel ticketTypeModel){
        if(ticketTypeModel == null)
            return null;

        TicketType ticketType = new TicketType();
        ticketType.setStationVehicleTypeID(ticketTypeModel.getStationVehicleTypeID());
        ticketType.setHoldingTime(ticketTypeModel.getHoldingTime());
        ticketType.setName(ticketTypeModel.getName());
        ticketType.setPrice(ticketTypeModel.getPrice());
        ticketType.setServiceID(ticketTypeModel.getServiceID());

        return ticketType;
    }

}
