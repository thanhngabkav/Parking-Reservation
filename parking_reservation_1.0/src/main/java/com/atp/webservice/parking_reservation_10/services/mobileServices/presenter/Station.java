package com.atp.webservice.parking_reservation_10.services.mobileServices.presenter;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

public class Station implements Serializable{

    private int ID;

    private String applicationID;

    private String keyPair;

    private String name;

    private String address;

    private Timestamp createdDate;

    private String status;

    private int level;

    private Time openTime;

    private Time closeTime;

    private String imageLink;

    private int totalSlots;

    private int usedSlots;

    private String parkingMapLink;

    private String coordinate;

    private UUID ownerID;


    public Station() {
    }

    public Station(int ID,String applicationID, String keyPair, String name, String address, Timestamp createdDate, String status, int level, Time openTime, Time closeTime, String imageLink, int totalSlots, int usedSlots, String parkingMapLink, String coordinate, UUID ownerID) {
        this.ID = ID;
        this.applicationID = applicationID;
        this.keyPair = keyPair;
        this.name = name;
        this.address = address;
        this.createdDate = createdDate;
        this.status = status;
        this.level = level;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.imageLink = imageLink;
        this.totalSlots = totalSlots;
        this.usedSlots = usedSlots;
        this.parkingMapLink = parkingMapLink;
        this.coordinate = coordinate;
        this.ownerID = ownerID;
    }

    public int getID() {
        return ID;
    }

    public Station setID(int ID) {
        this.ID = ID;
        return this;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public Station setApplicationID(String applicationID) {
        this.applicationID = applicationID;
        return this;
    }

    public String getKeyPair() {
        return keyPair;
    }

    public Station setKeyPair(String keyPair) {
        this.keyPair = keyPair;
        return this;
    }

    public String getName() {
        return name;
    }

    public Station setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Station setAddress(String address) {
        this.address = address;
        return this;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public Station setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Station setStatus(String status) {
        this.status = status;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public Station setLevel(int level) {
        this.level = level;
        return this;
    }

    public Time getOpenTime() {
        return openTime;
    }

    public Station setOpenTime(Time openTime) {
        this.openTime = openTime;
        return this;
    }

    public Time getCloseTime() {
        return closeTime;
    }

    public Station setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
        return this;
    }

    public String getImageLink() {
        return imageLink;
    }

    public Station setImageLink(String imageLink) {
        this.imageLink = imageLink;
        return this;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public Station setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
        return this;
    }

    public int getUsedSlots() {
        return usedSlots;
    }

    public Station setUsedSlots(int usedSlots) {
        this.usedSlots = usedSlots;
        return this;
    }

    public String getParkingMapLink() {
        return parkingMapLink;
    }

    public Station setParkingMapLink(String parkingMapLink) {
        this.parkingMapLink = parkingMapLink;
        return this;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public Station setCoordinate(String coordinate) {
        this.coordinate = coordinate;
        return this;
    }

    public UUID getOwnerID() {
        return ownerID;
    }

    public Station setOwnerID(UUID ownerID) {
        this.ownerID = ownerID;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return ID == station.ID;
    }

    @Override
    public int hashCode() {

        return Objects.hash(ID);
    }

    public void convertFromEntities(com.atp.webservice.parking_reservation_10.entities.Station station){
        if(station == null)
            return ;
        this.setAddress(station.getAddress())
                        .setCloseTime(station.getCloseTime())
                        .setCoordinate(station.getCoordinate())
                        .setCreatedDate(station.getCreatedDate())
                        .setID(station.getID())
                        .setApplicationID(station.getApplicationID())
                        .setImageLink(station.getImageLink())
                        .setKeyPair(station.getKeyPair())
                        .setLevel(station.getLevel())
                        .setName(station.getName())
                        .setOpenTime(station.getOpenTime())
                        .setCloseTime(station.getCloseTime())
                        .setOwnerID(station.getOwnerID())
                        .setParkingMapLink(station.getParkingMapLink())
                        .setStatus(station.getStatus())
                        .setTotalSlots(station.getTotalSlots())
                        .setUsedSlots(station.getUsedSlots());
    }
}
