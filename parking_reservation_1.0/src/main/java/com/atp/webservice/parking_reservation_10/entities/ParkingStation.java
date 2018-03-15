package com.atp.webservice.parking_reservation_10.entities;

import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;
import com.atp.webservice.parking_reservation_10.entities.uitls.TableName;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.Objects;

@Table(name = TableName.PARKING_STATION)
@Entity
public class ParkingStation implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "status")
    private String status;

    @Column(name = "open_time")
    private Time openTime;

    @Column(name = "close_time")
    private Time closeTime;

    @Column(name = "active_code", length = 50)
    private String activeCode;

    @Column(name = "coordinate", length = 200)
    private String coordinate;

    @Column(name = "parking_id", insertable = false, updatable = false)
    private int parkingID;

    @ManyToOne
    @JoinColumn(name = "parking_id")
    private Parking parking;

    public ParkingStation(String name, String status, Time openTime, Time closeTime, String activeCode, String coordinate, int parkingID) {
        this.name = name;
        this.status = status;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.activeCode = activeCode;
        this.coordinate = coordinate;
        this.parkingID = parkingID;
    }

    public ParkingStation() {
        this(DefaultValue.STRING, DefaultValue.STRING, DefaultValue.TIME, DefaultValue.TIME, DefaultValue.STRING,
                DefaultValue.STRING, DefaultValue.INT);
    }

    public int getID() {
        return ID;
    }

    public ParkingStation setID(int ID) {
        this.ID = ID;
        return this;
    }

    public String getName() {
        return name;
    }

    public ParkingStation setName(String name) {
        this.name = name;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public ParkingStation setStatus(String status) {
        this.status = status;
        return this;
    }

    public Time getOpenTime() {
        return openTime;
    }

    public ParkingStation setOpenTime(Time openTime) {
        this.openTime = openTime;
        return this;
    }

    public Time getCloseTime() {
        return closeTime;
    }

    public ParkingStation setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
        return this;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public ParkingStation setActiveCode(String activeCode) {
        this.activeCode = activeCode;
        return this;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public ParkingStation setCoordinate(String coordinate) {
        this.coordinate = coordinate;
        return this;
    }

    public int getParkingID() {
        return parkingID;
    }

    public ParkingStation setParkingID(int parkingID) {
        this.parkingID = parkingID;
        return this;
    }

    public Parking getParking() {
        return parking;
    }

    public ParkingStation setParking(Parking parking) {
        this.parking = parking;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingStation that = (ParkingStation) o;
        return ID == that.ID;
    }

    @Override
    public int hashCode() {

        return Objects.hash(ID);
    }
}
