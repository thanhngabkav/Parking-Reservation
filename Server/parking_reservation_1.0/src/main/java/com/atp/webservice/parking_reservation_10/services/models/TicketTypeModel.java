package com.atp.webservice.parking_reservation_10.services.models;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.sql.Time;
import java.util.Objects;

public class TicketTypeModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int ticketTypeID;

    private int stationVehicleTypeID;

    private double price;

    private Time holdingTime;

    private String name;

    private int serviceID;

    private String vehicleTypeName;

    public int getTicketTypeID() {
        return ticketTypeID;
    }

    public void setTicketTypeID(int ticketTypeID) {
        this.ticketTypeID = ticketTypeID;
    }

    public int getStationVehicleTypeID() {
        return stationVehicleTypeID;
    }

    public void setStationVehicleTypeID(int stationVehicleTypeID) {
        this.stationVehicleTypeID = stationVehicleTypeID;
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketTypeModel that = (TicketTypeModel) o;
        return ticketTypeID == that.ticketTypeID;
    }

    @Override
    public int hashCode() {

        return Objects.hash(ticketTypeID);
    }

    @Override
    public String toString() {
        return "TicketTypeModel{" +
                "ticketTypeID=" + ticketTypeID +
                ", stationVehicleTypeID=" + stationVehicleTypeID +
                ", price=" + price +
                ", holdingTime=" + holdingTime +
                ", name='" + name + '\'' +
                ", serviceID=" + serviceID +
                ", vehicleTypeName='" + vehicleTypeName + '\'' +
                '}';
    }
}
