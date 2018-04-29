package com.atp.webservice.parking_reservation_10.services.ticketService;


import com.atp.webservice.parking_reservation_10.entities.Ticket;
import com.atp.webservice.parking_reservation_10.entities.TicketType;
import com.atp.webservice.parking_reservation_10.entities.Vehicle;
import com.atp.webservice.parking_reservation_10.entities.VehicleType;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.VehicleTypeCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.models.TicketModel;
import com.atp.webservice.parking_reservation_10.services.models.TicketTypeModel;
import com.atp.webservice.parking_reservation_10.services.models.VehicleModel;
import com.atp.webservice.parking_reservation_10.services.models.VehicleTypeModel;
import com.atp.webservice.parking_reservation_10.services.ticketTypeService.TicketTypeConverter;
import com.atp.webservice.parking_reservation_10.services.ticketTypeService.TicketTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TicketConverter {


    @Autowired
    private TicketTypeConverter ticketTypeConverter;

    @Autowired
    private VehicleTypeCRUDRepository vehicleTypeCRUDRepository;

    public TicketModel convertFromEntity(Ticket enTicket){
        TicketModel mTicketModel = new TicketModel();
        mTicketModel.setCheckInTime(enTicket.getCheckinTime().toString());
        if(enTicket.getCheckoutTime()!=null)
            mTicketModel.setCheckOutTime(enTicket.getCheckoutTime().toString());
        mTicketModel.setCreatedDate(enTicket.getCreatedTime().toString());
        mTicketModel.setId(enTicket.getID().toString());
        mTicketModel.setStationID(enTicket.getStationID()+"");
        mTicketModel.setStatus(enTicket.getStatus());
        mTicketModel.setqRCode(enTicket.getqRCode());
        mTicketModel.setStationName(enTicket.getStation().getName());

        List<TicketType> enTicketTypes = enTicket.getTicketTypes();
        List<TicketTypeModel> mTicketTypeModels =
                new ArrayList<TicketTypeModel>();
        double totalPrice =0;
        for (TicketType enTicketType: enTicketTypes){
            TicketTypeModel ticketTypeModel = ticketTypeConverter.convertFromEntity(enTicketType);
            mTicketTypeModels.add(ticketTypeModel);
            totalPrice+= ticketTypeModel.getPrice();
        }

        mTicketModel.setTicketTypeModels(mTicketTypeModels);
        mTicketModel.setTotalPrice(totalPrice);
        VehicleType enVehicleType = vehicleTypeCRUDRepository.findOne(enTicket.getVehicle().getVehicleTypeID());
        VehicleTypeModel mVehicleTypeModel = new VehicleTypeModel(enVehicleType.getID(), enVehicleType.getName());
        VehicleModel mVehicleModel =
                new VehicleModel();
        mVehicleModel.setId(enTicket.getVehicleID());
        mVehicleModel.setLicensePlate(enTicket.getVehicle().getLicensePlate());
        mVehicleModel.setName(enTicket.getVehicle().getName());
        mVehicleModel.setVehicleTypeModel(mVehicleTypeModel);
        mVehicleModel.setDriverID(enTicket.getDriverID());
        mTicketModel.setPaid(enTicket.isPaid());
        mTicketModel.setVehicleModel(mVehicleModel);


        return mTicketModel;
    }
}
