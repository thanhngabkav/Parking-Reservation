package com.atp.webservice.parking_reservation_10.services.deamonServices.deamonServicesImp;

import com.atp.webservice.parking_reservation_10.entities.*;
import com.atp.webservice.parking_reservation_10.entities.uitls.TicketStatus;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationVehicleTypeCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.TicketCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.VehicleCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.deamonServices.StationTask;
import com.atp.webservice.parking_reservation_10.services.stationVehicleTypeService.StationVehicleTypeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class StationTaskImp implements StationTask {

    @Autowired
    private TicketCRUDRepository ticketCRUDRepository;

    @Autowired
    private StationVehicleTypeCRUDRepository  stationVehicleTypeCRUDRepository;

    @Autowired
    private VehicleCRUDRepository vehicleCRUDRepository;

    @Autowired
    private StationVehicleTypeService stationVehicleTypeService;

    private static final int SLEEP_TIME_IN_MIL = 60000;// 1 minute

    private static Logger logger = Logger.getLogger(StationTask.class);

    @Override
    public void checkStations() {
        //check tickets have status  is Holding, if holding time is Expired, update status and update holding slots of
        //station and station vehicle type
        int numStatusChanged =0;
        List<Ticket> holdingTicketList = ticketCRUDRepository.findByStatus(TicketStatus.HOLDING);
        for(Ticket holdingTicket : holdingTicketList){
            List<TicketType> ticketTypeList = holdingTicket.getTicketTypes();
            boolean isExpired = false;
            for(TicketType ticketType : ticketTypeList){
                long holingTimeInSeconds = ticketType.getHoldingTime().toLocalTime().toSecondOfDay();
                LocalDateTime timeExpired = holdingTicket.getCreatedTime().toLocalDateTime().plusSeconds(holingTimeInSeconds);
                if(timeExpired.isBefore(LocalDateTime.now())){
                    isExpired = true;
                    break;
                }
            }
            if(isExpired){
                Vehicle vehicle = vehicleCRUDRepository.findOne(holdingTicket.getVehicleID());
                StationVehicleType stationVehicleType = stationVehicleTypeCRUDRepository.findFirstByStationIDAndAndVehicleTypeId(
                        holdingTicket.getStationID(), vehicle.getVehicleTypeID());
                //update station vehicle type holding slots and station holding slots too
                stationVehicleTypeService.updateHoldingSlots(stationVehicleType.getId(),-1);
                //update ticket
                holdingTicket.setStatus(TicketStatus.EXPRIRRED);
                ticketCRUDRepository.save(holdingTicket);
                numStatusChanged++;
            }
        }
        logger.info("Checking station thread is working. Number ticket' status changed: " + numStatusChanged);
    }

    @Override
    public void run() {
        while (true){
            try {
                checkStations();
                Thread.sleep(SLEEP_TIME_IN_MIL);
            } catch (Exception e) {
                logger.error("Station Checking Thread ERROR!");
                e.printStackTrace();
            }
        }
    }
}
