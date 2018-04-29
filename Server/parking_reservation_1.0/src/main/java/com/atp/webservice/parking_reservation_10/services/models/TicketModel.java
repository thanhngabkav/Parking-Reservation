package com.atp.webservice.parking_reservation_10.services.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class TicketModel implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private String createdDate;

    private String checkInTime;

    private String checkOutTime;

    private List<TicketTypeModel> ticketTypeModels;

    private String stationID;

    private String stationName;

    private String status;

    private String qRCode;

    private VehicleModel vehicleModel;

    private double totalPrice;

    private boolean isPaid;

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

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    public TicketModel setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
        return this;
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
    public TicketModel setStationName(String stationName) {
        this.stationName = stationName;
        return this;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public TicketModel setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public TicketModel setPaid(boolean paid) {
        isPaid = paid;
        return this;
    }

    public List<TicketTypeModel> getTicketTypeModels() {
        return ticketTypeModels;
    }

    public TicketModel setTicketTypeModels(List<TicketTypeModel> ticketTypeModels) {
        this.ticketTypeModels = ticketTypeModels;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketModel ticketModel = (TicketModel) o;
        return Objects.equals(id, ticketModel.id) &&
                Objects.equals(createdDate, ticketModel.createdDate) &&
                Objects.equals(checkInTime, ticketModel.checkInTime) &&
                Objects.equals(checkOutTime, ticketModel.checkOutTime) &&
                Objects.equals(ticketModel, ticketModel.ticketTypeModels) &&
                Objects.equals(stationID, ticketModel.stationID) &&
                Objects.equals(stationName, ticketModel.stationName) &&
                Objects.equals(status, ticketModel.status) &&
                Objects.equals(qRCode, ticketModel.qRCode) &&
                Objects.equals(vehicleModel, ticketModel.vehicleModel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, createdDate, checkInTime, checkOutTime, ticketTypeModels, stationID, stationName, status, qRCode, vehicleModel);
    }
}
