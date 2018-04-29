package com.atp.webservice.parking_reservation_10.services.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * This model is use for transfer data to make a reservation
 */
public class TicketReservationModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String userID;

    private int stationID;

    private String vehicleID;

    private boolean isPaid;

    private double totalPrice;

    private List<TicketTypeModel> ticketTypeModels;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<TicketTypeModel> getTicketTypeModels() {
        return ticketTypeModels;
    }

    public void setTicketTypeModels(List<TicketTypeModel> ticketTypeModels) {
        this.ticketTypeModels = ticketTypeModels;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public TicketReservationModel setPaid(boolean paid) {
        isPaid = paid;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketReservationModel that = (TicketReservationModel) o;
        return stationID == that.stationID &&
                isPaid == that.isPaid &&
                Double.compare(that.totalPrice, totalPrice) == 0 &&
                Objects.equals(userID, that.userID) &&
                Objects.equals(vehicleID, that.vehicleID) &&
                Objects.equals(ticketTypeModels, that.ticketTypeModels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, stationID, vehicleID, isPaid, totalPrice, ticketTypeModels);
    }
}
