package com.atp.webservice.parking_reservation_10.services.mobileServices.ticketTypeService;

import com.atp.webservice.parking_reservation_10.entities.TicketType;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.TicketTypeModel;
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

}
