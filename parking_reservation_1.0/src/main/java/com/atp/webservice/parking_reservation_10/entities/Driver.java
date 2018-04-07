package com.atp.webservice.parking_reservation_10.entities;

import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;
import com.atp.webservice.parking_reservation_10.entities.uitls.TableName;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Table(name = TableName.DRIVER)
@Entity
@AttributeOverrides({
        @AttributeOverride(name="id", column=@Column(name="id")),
        @AttributeOverride(name="user_name", column=@Column(name="user_name"))
})
public class Driver extends User implements Serializable{

    @Column(name = "application_id")
    private String applicationID;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "balance")
    private double balance;

    @Column(name = "token", columnDefinition = "TEXT")
    private String token;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.MERGE)
    private List<Vehicle> vehicleList;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.MERGE)
    private List<Ticket> tickets;

    public Driver(String applicationID, String fullName, Timestamp createdDate, double balance, String token) {
        this.applicationID = applicationID;
        this.fullName = fullName;
        this.createdDate = createdDate;
        this.balance = balance;
        this.token = token;
        this.vehicleList = new ArrayList<Vehicle>();
    }

    public Driver() {
        this(DefaultValue.STRING, DefaultValue.STRING, DefaultValue.TIMESTAMP, DefaultValue.DOUBLE, DefaultValue.STRING);
    }


//    public UUID getID() {
//        return ID;
//    }
//
//    public void setID(UUID ID) {
//        this.ID = ID;
//    }


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public Driver setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return this.getUserID() == driver.getUserID();
    }

    @Override
    public int hashCode() {

        return Objects.hash(this.getUserID());
    }
}
