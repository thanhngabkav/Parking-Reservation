package com.atp.webservice.parking_reservation_10.services.mobileServices.messageService.models;

import java.io.Serializable;

public enum MessageStatus implements Serializable {

    INFO(200),
    NOTIFY(100),
    WARNING(500),
    UPDATE(300),
    DO_BACKGROUND(400),
    ERROR(505);

    private int value;

    MessageStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
