package com.atp.webservice.parking_reservation_10.services.mobileServices.ticketTypeService;

import com.atp.webservice.parking_reservation_10.services.mobileServices.models.TicketTypeModel;

import java.util.List;

public interface TicketTypeService {

    /**
     * Get all station ticket type a vehicle ID
     * @param stationID
     * @param serviceID
     * @return
     */
    List<TicketTypeModel> getAllTicketTypesByServiceIDAndStationID(int serviceID, int stationID);

    /**
     * Get list ticket type by service, station and vehicle type
     * @param serviceID
     * @param stationID
     * @param vehicleTypeID
     * @return List {@link TicketTypeModel}
     */
    List<TicketTypeModel> getTicketTypesByServiceIDAndStationIDAndVehicleTypeID(int serviceID, int stationID, int vehicleTypeID);

    /**
     * Add new {@link com.atp.webservice.parking_reservation_10.entities.TicketType}
     * @param ticketTypeModel
     * @return
     */
    TicketTypeModel addNewTicketType(TicketTypeModel ticketTypeModel);

    /**
     * Update {@link com.atp.webservice.parking_reservation_10.entities.TicketType}
     * @param ticketTypeModel
     * @return
     */
    TicketTypeModel updateTicketType(TicketTypeModel ticketTypeModel);




}
