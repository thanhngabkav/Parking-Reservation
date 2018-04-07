package com.atp.webservice.parking_reservation_10.entities;

import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;
import com.atp.webservice.parking_reservation_10.entities.uitls.TableName;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Table(name = TableName.TICKET)
@Entity
public class Ticket implements Serializable{

    @Id
    @Column(name = "id")
    private UUID ID;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "checkin_time")
    private Timestamp checkinTime;

    @Column(name = "checkout_time")
    private Timestamp checkoutTime;

    @Column(name = "qr_code")
    private String qRCode;

    @Column(name = "driver_id")
    private UUID driverID;

    @Column(name = "station_id")
    private int stationID;

    @Column(name = "ticket_type_id", insertable = false, updatable = false)
    private int ticketTypeID;

    @ManyToOne
    @JoinColumn(name = "driver_id", insertable = false, updatable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "station_id", insertable = false, updatable = false)
    private Station station;

    @ManyToOne
    @JoinColumn(name = "ticket_type_id", insertable = false, updatable = false)
    private TicketType ticketType;


    public Ticket(UUID ID, Timestamp createdTime, String status, Timestamp checkinTime, Timestamp checkoutTime,String qRCode, UUID driverID, int stationID, int ticketTypeID) {
        this.ID = ID;
        this.createdTime = createdTime;
        this.status = status;
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
        this.qRCode = qRCode;
        this.driverID = driverID;
        this.stationID = stationID;
        this.ticketTypeID = ticketTypeID;
    }

    public Ticket() {
        this(DefaultValue.UUID, DefaultValue.TIMESTAMP, DefaultValue.STRING, DefaultValue.TIMESTAMP, DefaultValue.TIMESTAMP,
               DefaultValue.STRING, DefaultValue.UUID, DefaultValue.INT, DefaultValue.INT);
    }

    public UUID getID() {
        return ID;
    }

    public Ticket setID(UUID ID) {
        this.ID = ID;
        return this;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public Ticket setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Ticket setStatus(String status) {
        this.status = status;
        return this;
    }

    public Timestamp getCheckoutTime() {
        return checkoutTime;
    }

    public Ticket setCheckoutTime(Timestamp checkoutTime) {
        this.checkoutTime = checkoutTime;
        return this;
    }

    public Timestamp getCheckinTime() {
        return checkinTime;
    }

    public void setCheckinTime(Timestamp checkinTime) {
        this.checkinTime = checkinTime;
    }

    public String getqRCode() {
        return qRCode;
    }

    public void setqRCode(String qRCode) {
        this.qRCode = qRCode;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public UUID getDriverID() {
        return driverID;
    }

    public Ticket setDriverID(UUID driverID) {
        this.driverID = driverID;
        return this;
    }

    public int getStationID() {
        return stationID;
    }

    public Ticket setStationID(int stationID) {
        this.stationID = stationID;
        return this;
    }

    public int getTicketTypeID() {
        return ticketTypeID;
    }

    public Ticket setTicketTypeID(int ticketTypeID) {
        this.ticketTypeID = ticketTypeID;
        return this;
    }

    public Driver getDriver() {
        return driver;
    }

    public Ticket setDirver(Driver driver) {
        this.driver = driver;
        return this;
    }

    public Station getStation() {
        return station;
    }

    public Ticket setStation(Station station) {
        this.station = station;
        return this;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public Ticket setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(ID, ticket.ID);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ID);
    }
}
