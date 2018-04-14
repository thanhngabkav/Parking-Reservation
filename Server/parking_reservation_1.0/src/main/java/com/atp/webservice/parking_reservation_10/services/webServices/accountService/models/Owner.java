package com.atp.webservice.parking_reservation_10.services.webServices.accountService.models;

import java.io.Serializable;

public class Owner implements Serializable{

    private String userID;

    private String name;

    private String userName;

    private String address;

    private String bankAccountNumber;

    private String bank;

    public Owner() {
    }

    public String getUserID() {
        return userID;
    }

    public Owner setUserID(String userID) {
        this.userID = userID;
        return this;
    }

    public String getName() {
        return name;
    }

    public Owner setName(String name) {
        this.name = name;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public Owner setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Owner setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public Owner setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
        return this;
    }

    public String getBank() {
        return bank;
    }

    public Owner setBank(String bank) {
        this.bank = bank;
        return this;
    }
}
