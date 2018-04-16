package com.atp.webservice.parking_reservation_10.services.mobileServices.models;

import java.io.Serializable;

public class Ticket implements Serializable{

    private String id;

    private String createdDate;

    private String checkInTime;

    private String checkOutTime;


    private String typeID;

    private String typeName;

    private String stationID;

    private String stationName;

    private String status;

    private String qRCode;

    private Vehicle vehicle;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(String checkInTime) {
        this.checkInTime = checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(String checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Ticket setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        return this;
    }

    public String getTypeID() {
        return typeID;
    }

    public void setTypeID(String typeID) {
        this.typeID = typeID;
    }

    public String getStationID() {
        return stationID;
    }

    public void setStationID(String stationID) {
        this.stationID = stationID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getqRCode() {
        return qRCode;
    }

    public void setqRCode(String qRCode) {
        this.qRCode = qRCode;
    }

    public String getStationName() {
        return stationName;
    }
    public Ticket setStationName(String stationName) {
        this.stationName = stationName;
        return this;
    }

    public String getTypeName() {
        return typeName;
    }

    public Ticket setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }
}
