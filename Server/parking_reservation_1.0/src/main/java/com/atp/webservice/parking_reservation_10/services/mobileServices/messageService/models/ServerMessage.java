package com.atp.webservice.parking_reservation_10.services.mobileServices.messageService.models;

import java.io.Serializable;

public class ServerMessage<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    MessageStatus status;

    String title;

    T data;

    public ServerMessage(MessageStatus status, String title, T data) {
        this.status = status;
        this.title = title;
        this.data = data;
    }

    public ServerMessage() {
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
