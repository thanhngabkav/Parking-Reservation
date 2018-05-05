package com.atp.webservice.parking_reservation_10.services.models;

import java.io.Serializable;

public class UserModel implements Serializable {

    private String id;

    private String email;

    private String password;

    private String phoneNumber;

    private String status;

    private String userType;

    public String getId() {
        return id;
    }

    public UserModel setId(String id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public UserModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public UserModel setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getUserType() {
        return userType;
    }

    public UserModel setUserType(String userType) {
        this.userType = userType;
        return this;
    }
}
