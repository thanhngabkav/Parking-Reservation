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
