package com.atp.webservice.parking_reservation_10.services.mobileServices.ServicesImp;

import com.atp.webservice.parking_reservation_10.entities.uitls.TicketStatus;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.TicketCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.mobileServices.TicketService;
import com.atp.webservice.parking_reservation_10.services.mobileServices.presenter.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TicketServiceImp implements TicketService{

    @Autowired
    private TicketCRUDRepository ticketCRUDRepository;

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
            Ticket m_ticket = new Ticket();
            m_ticket.setCheckInTime(ticket.getCheckinTime().toString());
            m_ticket.setCheckOutTime(ticket.getCheckoutTime().toString());
            m_ticket.setCreatedDate(ticket.getCreatedTime().toString());
            m_ticket.setId(ticket.getID().toString());
            m_ticket.setDriverID(ticket.getDriverID().toString());
            m_ticket.setStationID(ticket.getStationID()+"");
            m_ticket.setStatus(ticket.getStatus());
            m_ticket.setTypeID(ticket.getTicketTypeID()+"");
            m_ticket.setqRCode(ticket.getqRCode());
            result.add(m_ticket);
        }
        return result;
    }
}
