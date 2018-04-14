package com.atp.webservice.parking_reservation_10.services.webServices.accountService.models;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 */
public class RegisterModel implements Serializable{

    private String name;

    private String email;

    private String phoneNumber;

    private String address;

    private String password;

    private String stationName;

    private Timestamp openTime;

    private Timestamp closeTime;

    private String imageLink;

    private int totalSlots;

    private double lat;

    private double lng;

    public RegisterModel() {
    }

    public String getName() {
        return name;
    }

    public RegisterModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public RegisterModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public RegisterModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public RegisterModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getStationName() {
        return stationName;
    }

    public RegisterModel setStationName(String stationName) {
        this.stationName = stationName;
        return this;
    }

    public Timestamp getOpenTime() {
        return openTime;
    }

    public RegisterModel setOpenTime(Timestamp openTime) {
        this.openTime = openTime;
        return this;
    }

    public Timestamp getCloseTime() {
        return closeTime;
    }

    public RegisterModel setCloseTime(Timestamp closeTime) {
        this.closeTime = closeTime;
        return this;
    }

    public String getImageLink() {
        return imageLink;
    }

    public RegisterModel setImageLink(String imageLink) {
        this.imageLink = imageLink;
        return this;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public RegisterModel setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
        return this;
    }

    public double getLat() {
        return lat;
    }

    public RegisterModel setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLng() {
        return lng;
    }

    public RegisterModel setLng(double lng) {
        this.lng = lng;
        return this;
    }

    @Override
    public String toString() {
        return "RegisterModel{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", stationName='" + stationName + '\'' +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                ", imageLink='" + imageLink + '\'' +
                ", totalSlots=" + totalSlots +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
