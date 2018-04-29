package com.atp.webservice.parking_reservation_10.services.models;

import java.io.Serializable;
import java.util.List;

public class DriverModel implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String userID;

    private String phoneNumber;

    private String email;

    private String password;

    private List<VehicleModel> vehicleModels;

    private String driverName;

    private String applicationID;

    private double balance;


    public DriverModel(String userID, String phoneNumber, String email, String password, List<VehicleModel> vehicleModels, String driverName, String applicationID, double balance) {
        this.userID = userID;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.vehicleModels = vehicleModels;
        this.driverName = driverName;
        this.applicationID = applicationID;
        this.balance = balance;

    }

    public DriverModel() {
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

    public List<VehicleModel> getVehicleModels() {
        return vehicleModels;
    }

    public String getPassword() {
        return password;
    }

    public DriverModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getDriverName() {
        return driverName;
    }

    public DriverModel setDriverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public DriverModel setApplicationID(String applicationID) {
        this.applicationID = applicationID;
        return this;
    }

    public double getBalance() {
        return balance;
    }

    public DriverModel setBalance(double balance) {
        this.balance = balance;
        return this;
    }

    public void setVehicleModels(List<VehicleModel> vehicleModels) {
        this.vehicleModels = vehicleModels;
    }

    @Override
    public String toString() {
        return "DriverModel{" +
                "userID='" + userID + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", driverName='" + driverName + '\'' +
                '}';
    }
}
