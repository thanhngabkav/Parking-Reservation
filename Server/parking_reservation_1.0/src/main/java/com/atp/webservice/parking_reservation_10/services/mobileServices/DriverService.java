package com.atp.webservice.parking_reservation_10.services.mobileServices;

import com.atp.webservice.parking_reservation_10.services.mobileServices.presenter.Ticket;

public interface DriverService {

    /**
     * Send Reservation request to Station
     * @param userID {@link com.atp.webservice.parking_reservation_10.entities.Driver} Driver ID
     * @param stationID {@link com.atp.webservice.parking_reservation_10.entities.Station} Station ID
     * @return Ticket {@link Ticket} if success or null if not
     */
    Ticket sendRequestForReservation(String userID, String stationID);

}
