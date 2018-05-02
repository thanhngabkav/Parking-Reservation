package com.atp.webservice.parking_reservation_10.services.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class TicketQRModel implements Serializable {

    private String id;

    private String status;

    private String checkinTime;

    private String checkoutTime;

    private boolean ispPaid;

    private double totalPrice;

    private String vehicleTypeName;

    private String licensePlate;

    private String createdTime;

    private String driverName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(String checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(String checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public boolean isIspPaid() {
        return ispPaid;
    }

    public void setIspPaid(boolean ispPaid) {
        this.ispPaid = ispPaid;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getVehicleTypeName() {
        return vehicleTypeName;
    }

    public void setVehicleTypeName(String vehicleTypeName) {
        this.vehicleTypeName = vehicleTypeName;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketQRModel that = (TicketQRModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TicketQRModel{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", checkinTime=" + checkinTime +
                ", checkoutTime=" + checkoutTime +
                ", ispPaid=" + ispPaid +
                ", totalPrice=" + totalPrice +
                ", vehicleTypeName='" + vehicleTypeName + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", createdTime=" + createdTime +
                ", driverName='" + driverName + '\'' +
                '}';
    }
}
