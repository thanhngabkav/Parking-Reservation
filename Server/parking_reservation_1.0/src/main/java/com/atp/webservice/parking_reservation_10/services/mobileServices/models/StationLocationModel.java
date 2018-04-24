package com.atp.webservice.parking_reservation_10.services.mobileServices.models;

import java.io.Serializable;
import java.util.Objects;

public class StationLocationModel implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int stationID;

    private int totalSlot;

    private  int usedSlot;

    private int freeSlot;

    private double lat;

    private double lng;

    public StationLocationModel(int stationID, double lat, double lng) {
        this.stationID = stationID;
        this.lat = lat;
        this.lng = lng;
        this.totalSlot =0;
        this.freeSlot =0;
        this.usedSlot =0;
    }

    public StationLocationModel() {
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getTotalSlot() {
        return totalSlot;
    }

    public void setTotalSlot(int totalSlot) {
        this.totalSlot = totalSlot;
    }

    public int getUsedSlot() {
        return usedSlot;
    }

    public void setUsedSlot(int usedSlot) {
        this.usedSlot = usedSlot;
    }

    public int getFreeSlot() {
        return freeSlot;
    }

    public void setFreeSlot(int freeSlot) {
        this.freeSlot = freeSlot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationLocationModel that = (StationLocationModel) o;
        return stationID == that.stationID;
    }

    @Override
    public int hashCode() {

        return Objects.hash(stationID);
    }

    @Override
    public String toString() {
        return "StationLocationModel{" +
                "stationID=" + stationID +
                ", totalSlot=" + totalSlot +
                ", usedSlot=" + usedSlot +
                ", freeSlot=" + freeSlot +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
