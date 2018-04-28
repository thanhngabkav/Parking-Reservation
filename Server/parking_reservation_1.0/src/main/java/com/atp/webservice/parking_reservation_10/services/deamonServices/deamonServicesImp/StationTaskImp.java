package com.atp.webservice.parking_reservation_10.services.deamonServices.deamonServicesImp;

import com.atp.webservice.parking_reservation_10.entities.*;
import com.atp.webservice.parking_reservation_10.entities.uitls.TicketStatus;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationVehicleTypeCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.TicketCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.VehicleCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.deamonServices.StationTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StationTaskImp implements StationTask, Runnable {

    @Autowired
    private  StationCRUDRepository stationCRUDRepository;

    @Autowired
    private TicketCRUDRepository ticketCRUDRepository;

    @Autowired
    private StationVehicleTypeCRUDRepository  stationVehicleTypeCRUDRepository;

    @Autowired
    private VehicleCRUDRepository vehicleCRUDRepository;



    @Override
    public void checkStations() {
        //check cac ticket dang co trang thai la holding, neu thoi gian qua han, chuyen trang thai va cap nhat
        //so luong cho holding cua station va  station vehicle type
        List<Station> stationList = stationCRUDRepository.findAll();
        List<Ticket> holdingTicketList = ticketCRUDRepository.findByStatus(TicketStatus.HOLDING);
        for(Ticket holdingTicket : holdingTicketList){
            List<TicketType> ticketTypeList = holdingTicket.getTicketTypes();
            boolean isExpired = false;
            for(TicketType ticketType : ticketTypeList){
                long holingTimeInSeconds = ticketType.getHoldingTime().toLocalTime().toSecondOfDay();
                LocalDateTime timeExpired = holdingTicket.getCreatedTime().toLocalDateTime().plusSeconds(holingTimeInSeconds);
                if(timeExpired.isAfter(LocalDateTime.now())){
                    isExpired = true;
                    break;
                }
            }
            if(isExpired){
                Vehicle vehicle = vehicleCRUDRepository.findOne(holdingTicket.getVehicleID());
                StationVehicleType stationVehicleType = stationVehicleTypeCRUDRepository.findFirstByStationIDAndAndVehicleTypeId(
                        holdingTicket.getStationID(), vehicle.getVehicleTypeID());
               // stationVehicleType.setHoldingSlots();
            }
        }
    }

    @Override
    public void run() {

    }
}
