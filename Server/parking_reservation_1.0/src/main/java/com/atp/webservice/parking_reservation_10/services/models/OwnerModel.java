package com.atp.webservice.parking_reservation_10.services.models;

import java.io.Serializable;
import java.util.Objects;

public class OwnerModel implements Serializable {

    private String id;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String address;

    private String password;

    private String bank;

    private String bankAccountNumber;

    private int numStations;

    private String status;

    private String secretKey;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public int getNumStations() {
        return numStations;
    }

    public void setNumStations(int numStations) {
        this.numStations = numStations;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public OwnerModel(String id, String fullName, String email, String phoneNumber, String address, String password, String bank, String bankAccountNumber, int numStations, String status, String secretKey) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.password = password;
        this.bank = bank;
        this.bankAccountNumber = bankAccountNumber;
        this.numStations = numStations;
        this.status = status;
        this.secretKey = secretKey;
    }

    public OwnerModel() {
    }

    @Override
    public String toString() {
        return "OwnerModel{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", bank='" + bank + '\'' +
                ", bankAccountNumber='" + bankAccountNumber + '\'' +
                ", numStations=" + numStations +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerModel that = (OwnerModel) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
