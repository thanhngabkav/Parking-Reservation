package com.atp.webservice.parking_reservation_10.services.mobileServices.models;

import com.atp.webservice.parking_reservation_10.entities.uitls.UserType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Driver implements Serializable{

    private String userID;

    private String phoneNumber;

    private String email;

    private String address;

    private String password;

    private List<Vehicle> vehicles;

    private String driverName;

    private String applicationID;

    private double balance;


    public Driver(String userID, String phoneNumber, String email, String address, String password, List<Vehicle> vehicles, String driverName, String applicationID, double balance) {
        this.userID = userID;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.password = password;
        this.vehicles = vehicles;
        this.driverName = driverName;
        this.applicationID = applicationID;
        this.balance = balance;

    }

    public Driver() {
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public String getPassword() {
        return password;
    }

    public Driver setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getDriverName() {
        return driverName;
    }

    public Driver setDriverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public Driver setApplicationID(String applicationID) {
        this.applicationID = applicationID;
        return this;
    }

    public double getBalance() {
        return balance;
    }

    public Driver setBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "userID='" + userID + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", driverName='" + driverName + '\'' +
                '}';
    }
}
