package com.atp.webservice.parking_reservation_10.services.ticketTypeService;

import com.atp.webservice.parking_reservation_10.entities.TicketType;
import com.atp.webservice.parking_reservation_10.services.models.TicketTypeModel;
import org.springframework.stereotype.Component;

@Component
public class TicketTypeConverter {

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
