package com.atp.webservice.parking_reservation_10.services.mobileServices.ticketService.ticketSerivceImp;

import com.atp.webservice.parking_reservation_10.entities.*;
import com.atp.webservice.parking_reservation_10.entities.uitls.StationStatus;
import com.atp.webservice.parking_reservation_10.entities.uitls.TicketStatus;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.*;
import com.atp.webservice.parking_reservation_10.services.algorithms.KeyHelper;
import com.atp.webservice.parking_reservation_10.services.algorithms.KeypairHelper;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.TicketModel;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.TicketTypeModel;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.TicketReservationModel;
import com.atp.webservice.parking_reservation_10.services.mobileServices.ticketService.TicketConverter;
import com.atp.webservice.parking_reservation_10.services.mobileServices.ticketService.TicketService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@org.springframework.stereotype.Service
public class TicketServiceImp implements TicketService{

    private Logger logger = Logger.getLogger(TicketServiceImp.class);

    @Autowired
    private TicketCRUDRepository ticketCRUDRepository;

    @Autowired
    private StationCRUDRepository stationCRUDRepository;

    @Autowired
    private DriverCRUDRepository driverCRUDRepository;

    @Autowired
    private TicketTypeCRUDRepository ticketTypeCRUDRepository;

    @Autowired
    private VehicleCRUDRepository vehicleCRUDRepository;

    @Autowired
    private OwnerCRUDRepository ownerCRUDRepository;

    @Autowired
    private TicketConverter ticketConverter;

    @Autowired
    private ServiceCRUDRepository serviceCRUDRepository;

    @Override
    public List<TicketModel> getUsedTickets(String userID) {
        return getListTicketByDriverIDAndStatus(userID, TicketStatus.USED);
    }

    @Override
    public List<TicketModel> getUsingTickets(String userID) {
        //get list Holding tickets
        List<TicketModel> result = getListTicketByDriverIDAndStatus(userID, TicketStatus.HOLDIND);
        //add checked tickets
        result.addAll(getListTicketByDriverIDAndStatus(userID, TicketStatus.CHECKED));
        return result;
    }

    @Override
    public List<TicketModel> getListTicketByDriverIDAndStatus(String driverID, String status){
        List<com.atp.webservice.parking_reservation_10.entities.Ticket> tickets =
                ticketCRUDRepository.findTicketsByDriverIDAndStatus(driverID, status);
        List<TicketModel> result = new ArrayList<TicketModel>();
        for(com.atp.webservice.parking_reservation_10.entities.Ticket ticket : tickets){
            TicketModel m_ticketModel = ticketConverter.convertFromEntity(ticket);
            result.add(m_ticketModel);
        }
        return result;
    }

    @Override
    public TicketModel sendRequestForReservation(TicketReservationModel ticketReservationModel) {
        /**
         * Steps:
         * 1. Check station
         * 2. If driver pay for ticket, check account balance
         * 3. Create ticket
         */

        Station mStation = stationCRUDRepository.findOne(ticketReservationModel.getStationID());
        Driver mDriver = driverCRUDRepository.findOne(ticketReservationModel.getUserID());
        Vehicle mVehicle = vehicleCRUDRepository.findOne(ticketReservationModel.getVehicleID());
        if(mStation == null || mDriver == null || mVehicle == null){
            return null;
        }
        List<com.atp.webservice.parking_reservation_10.entities.TicketType>  mTicketTypes = new ArrayList<com.atp.webservice.parking_reservation_10.entities.TicketType>();
        double totalPrice =0;
        for (TicketTypeModel ticketDetail : ticketReservationModel.getTicketTypeModels()){
            TicketType mTicketType = ticketTypeCRUDRepository.findOne(ticketDetail.getTicketTypeID());
            Service msService = serviceCRUDRepository.findOne(ticketDetail.getServiceID());
            //invalid foreign keys
            if(mTicketType == null || mTicketType.getStationID()!= mStation.getID() || mTicketType.getVehicleTypeID()!= mVehicle.getVehicleTypeID()
                    || mTicketType.getServiceID() != msService.getServiceID())
                return null;
            mTicketTypes.add(mTicketType);
            totalPrice+= mTicketType.getPrice();
        }
        if(mTicketTypes.size() == 0)
            return null;


        //station is not active
        if(!mStation.getStatus().equals(StationStatus.ACTIVE)){
            return null;
        }

        //don't have enough money
        if(ticketReservationModel.isPaid()){
            System.out.println("Pay");
            if(mDriver.getBalance() < totalPrice){
                return null;
            }
        }

        //valid
        /**
         * 1. Create new ticket
         * 2. Encrypt QR code data bt station private key
         */
        Owner mOwner = ownerCRUDRepository.findOne(mStation.getOwnerID());
        com.atp.webservice.parking_reservation_10.entities.Ticket mTicket = new com.atp.webservice.parking_reservation_10.entities.Ticket();
        mTicket.setPaid(ticketReservationModel.isPaid());
        mTicket.setVehicle(mVehicle);
        mTicket.setDriver(mDriver);
        mTicket.setTicketTypes(mTicketTypes);
        mTicket.setVehicleID(mVehicle.getID());
        mTicket.setStatus(TicketStatus.HOLDIND);
        mTicket.setStationID(mStation.getID());
        mTicket.setDriverID(mDriver.getUserID());
        mTicket.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
        mTicket.setID(UUID.randomUUID().toString());
        mTicket.setStation(mStation);
        mTicket.setVehicle(mVehicle);
        StringBuilder QRCode = new StringBuilder();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String content =  objectMapper.writeValueAsString(ticketConverter.convertFromEntity(mTicket));
            QRCode.append(KeyHelper.encrypt(content, mOwner.getSecretKey()));
        } catch (Exception e) {
            logger.error("Can not gen QR code!");
            e.printStackTrace();
        }
        mTicket.setqRCode(QRCode.toString());
        //save new ticket
        ticketCRUDRepository.save(mTicket);

        return ticketConverter.convertFromEntity(mTicket);
    }


}
