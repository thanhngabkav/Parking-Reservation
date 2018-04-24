package com.atp.webservice.parking_reservation_10.services.mobileServices.messageService.models;

import java.io.Serializable;

public class MessageTopic implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public static final String DRIVER_TOPIC = "driver";

    public static final String STATION_TOPIC = "station_";

    public static final String OWNER_TOPIC = "owner";

    public static final String  ADMIN_TOPIC = "admin";
}
