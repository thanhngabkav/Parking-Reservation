package com.atp.webservice.parking_reservation_10.services.messageService.models;

import java.io.Serializable;

public enum MessageStatus implements Serializable {

    NOTIFY(100),
    INFO(200),
    UPDATE_MAP(201),
    UPDATE_STATION(202),
    UPDATE_HOLDING_SLOTS(203),
    UPDATE_USED_SLOTS(204),
    NEW_OWNER(205),
    NEW_STATION(206),
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
