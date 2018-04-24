package com.atp.webservice.parking_reservation_10.services.mobileServices.models;


import java.util.Objects;

public class StationVehicleTypeModel {

    private int id;

    private int vehicleTypeId;

    private int stationID;

    private int totalSlots;

    private int usedSlots;

    private int holdingSlots;

    public StationVehicleTypeModel(int id, int vehicleTypeId, int stationID, int totalSlots, int usedSlots, int holdingSlots) {
        this.id = id;
        this.vehicleTypeId = vehicleTypeId;
        this.stationID = stationID;
        this.totalSlots = totalSlots;
        this.usedSlots = usedSlots;
        this.holdingSlots = holdingSlots;
    }

    public StationVehicleTypeModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public int getUsedSlots() {
        return usedSlots;
    }

    public void setUsedSlots(int usedSlots) {
        this.usedSlots = usedSlots;
    }

    public int getHoldingSlots() {
        return holdingSlots;
    }

    public void setHoldingSlots(int holdingSlots) {
        this.holdingSlots = holdingSlots;
    }

    @Override
    public String toString() {
        return "StationVehicleTypeModel{" +
                "id=" + id +
                ", vehicleTypeId=" + vehicleTypeId +
                ", stationID=" + stationID +
                ", totalSlots=" + totalSlots +
                ", usedSlots=" + usedSlots +
                ", holdingSlots=" + holdingSlots +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationVehicleTypeModel that = (StationVehicleTypeModel) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
