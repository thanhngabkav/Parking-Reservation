package com.atp.webservice.parking_reservation_10.services.mobileServices.models;

import java.io.Serializable;
import java.sql.Time;
import java.util.Objects;

public class TicketTypeModel implements Serializable {

    private int ticketTypeID;

    private int vehicleTypeID;

    private int stationID;

    private double price;

    private Time holdingTime;

    private String name;

    private int serviceID;

    public int getTicketTypeID() {
        return ticketTypeID;
    }

    public void setTicketTypeID(int ticketTypeID) {
        this.ticketTypeID = ticketTypeID;
    }

    public int getVehicleTypeID() {
        return vehicleTypeID;
    }

    public void setVehicleTypeID(int vehicleTypeID) {
        this.vehicleTypeID = vehicleTypeID;
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Time getHoldingTime() {
        return holdingTime;
    }

    public void setHoldingTime(Time holdingTime) {
        this.holdingTime = holdingTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketTypeModel that = (TicketTypeModel) o;
        return ticketTypeID == that.ticketTypeID &&
                vehicleTypeID == that.vehicleTypeID &&
                stationID == that.stationID &&
                Double.compare(that.price, price) == 0 &&
                serviceID == that.serviceID &&
                Objects.equals(holdingTime, that.holdingTime) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ticketTypeID, vehicleTypeID, stationID, price, holdingTime, name, serviceID);
    }
}
