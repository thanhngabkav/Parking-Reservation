package com.atp.webservice.parking_reservation_10.services.mobileServices.ticketService.ticketSerivceImp;

import com.atp.webservice.parking_reservation_10.entities.sparkPresenter.StationPresenter;
import com.atp.webservice.parking_reservation_10.entities.uitls.StationStatus;
import com.atp.webservice.parking_reservation_10.entities.uitls.TicketStatus;
import com.atp.webservice.parking_reservation_10.repository.sparkRepository.StationRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.TicketCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.Vehicle;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.VehicleType;
import com.atp.webservice.parking_reservation_10.services.mobileServices.ticketService.TicketService;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImp implements TicketService{

    @Autowired
    private TicketCRUDRepository ticketCRUDRepository;

    @Autowired
    private StationRepository stationRepository;

    @Override
    public List<Ticket> getUsedTickets(String userID) {
        return getListTicketByDriverIDAndStatus(userID, TicketStatus.USED);
    }

    @Override
    public List<Ticket> getUsingTickets(String userID) {
        //get list Holding tickets
        List<Ticket> result = getListTicketByDriverIDAndStatus(userID, TicketStatus.HOLDIND);
        //add checked tickets
        result.addAll(getListTicketByDriverIDAndStatus(userID, TicketStatus.CHECKED));
        return result;
    }

    @Override
    public List<Ticket> getListTicketByDriverIDAndStatus(String driverID, String status){
        List<com.atp.webservice.parking_reservation_10.entities.Ticket> tickets =
                ticketCRUDRepository.findTicketsByDriverIDAndStatus(driverID, status);
        List<Ticket> result = new ArrayList<Ticket>();
        for(com.atp.webservice.parking_reservation_10.entities.Ticket ticket : tickets){
            Ticket m_ticket = convertFromEntity(ticket);
            result.add(m_ticket);
        }
        return result;
    }


    @Override
    public Ticket sendRequestForReservation(String userID, String stationID) {

        StationPresenter station = stationRepository.findOne(Integer.parseInt(stationID));
        if(station == null || !(station.getStatus().equals(StationStatus.ACTIVE))){
            return  null;
        }

        return null;
    }

    private Ticket convertFromEntity(com.atp.webservice.parking_reservation_10.entities.Ticket enTicket){
        Ticket mTicket = new Ticket();
        mTicket.setCheckInTime(enTicket.getCheckinTime().toString());
        if(enTicket.getCheckoutTime()!=null)
            mTicket.setCheckOutTime(enTicket.getCheckoutTime().toString());
        mTicket.setCreatedDate(enTicket.getCreatedTime().toString());
        mTicket.setId(enTicket.getID().toString());
        mTicket.setStationID(enTicket.getStationID()+"");
        mTicket.setStatus(enTicket.getStatus());
        mTicket.setTypeID(enTicket.getTicketTypeID()+"");
        mTicket.setqRCode(enTicket.getqRCode());
        mTicket.setStationName(enTicket.getStation().getName());
        mTicket.setTypeName(enTicket.getTicketType().getName());

        VehicleType mVehicleType = new VehicleType(enTicket.getVehicle().getVehicleTypeID(), enTicket.getVehicle().getName());
        Vehicle mVehicle = new Vehicle();
        mVehicle.setId(enTicket.getVehicleID());
        mVehicle.setLicensePlate(enTicket.getVehicle().getLicensePlate());
        mVehicle.setName(enTicket.getVehicle().getName());
        mVehicle.setVehicleType(mVehicleType);
        mVehicle.setDriverID(enTicket.getDriverID());

        mTicket.setVehicle(mVehicle);


        return mTicket;
    }
}
