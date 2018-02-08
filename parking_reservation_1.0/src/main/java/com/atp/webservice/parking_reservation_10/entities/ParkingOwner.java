package com.atp.webservice.parking_reservation_10.entities;

import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Table(name = "parking_owner")
@Entity
public class ParkingOwner implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "name", length = 250, nullable = false)
    private String name;

    @Column(name = "phone_number", length = 50, nullable = false)
    private String phoneNumber;

    @Column(name = "email", length = 150, nullable = false)
    private String email;

    @Column(name = "address", length = 250, nullable = false)
    private String address;

    @Column(name = "user_name", length = 150, nullable = false)
    private String userName;

    @Column(name = "password", length = 500)
    private String password;

    @Column(name = "bank_account_number", length = 200)
    private String bankAccountNumber;

    @Column(name = "bank", length = 150)
    private String bankName;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.MERGE)
    private List<Parking> parkings;

    public ParkingOwner(String name, String phoneNumber, String email, String address, String userName, String password, String bankAccountNumber, String bankName) {
        this.ID = ID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.userName = userName;
        this.password = password;
        this.bankAccountNumber = bankAccountNumber;
        this.bankName = bankName;
    }

    public ParkingOwner() {
        this(DefaultValue.STRING, DefaultValue.STRING, DefaultValue.STRING, DefaultValue.STRING,
                DefaultValue.STRING, DefaultValue.STRING, DefaultValue.STRING, DefaultValue.STRING);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public List<Parking> getParkings() {
        return parkings;
    }

    public void setParkings(List<Parking> parkings) {
        this.parkings = parkings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingOwner that = (ParkingOwner) o;
        return ID == that.ID;
    }

    @Override
    public int hashCode() {

        return Objects.hash(ID);
    }
}

