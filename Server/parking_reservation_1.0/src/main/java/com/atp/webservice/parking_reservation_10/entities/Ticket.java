package com.atp.webservice.parking_reservation_10.entities;

import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;
import com.atp.webservice.parking_reservation_10.entities.uitls.TableName;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Table(name = TableName.TICKET)
@Entity
public class Ticket implements Serializable{

    @Id
    @Column(name = "id")
    private String ID;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "checkin_time")
    private Timestamp checkinTime;

    @Column(name = "checkout_time")
    private Timestamp checkoutTime;

    @Column(name = "qr_code")
    @Type(type = "text")
    private String qRCode;

    @Column(name = "driver_id")
    private String driverID;

    @Column(name = "station_id")
    private int stationID;

    @Column(name = "vehicle_id", nullable = false)
    private String vehicleID;

    @Column(name = "is_paid")
    private boolean isPaid;

    @Column(name = "total_price")
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "driver_id", insertable = false, updatable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "station_id", insertable = false, updatable = false)
    private Station station;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", insertable = false, updatable = false)
    private Vehicle vehicle;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "ticket_detail",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_type_id")
    )
    private List<TicketType> ticketTypes;


    public Ticket(String ID, Timestamp createdTime, String status, Timestamp checkinTime, Timestamp checkoutTime,String qRCode,
                  String driverID, int stationID, String vehicleID, boolean isPaid, double totalPrice ) {
        this.ID = ID;
        this.createdTime = createdTime;
        this.status = status;
        this.checkinTime = checkinTime;
        this.checkoutTime = checkoutTime;
        this.qRCode = qRCode;
        this.driverID = driverID;
        this.stationID = stationID;
        this.vehicleID = vehicleID;
        this.isPaid = isPaid;
        this.totalPrice = totalPrice;
    }

    public Ticket() {
        this(DefaultValue.UUID.toString(), DefaultValue.TIMESTAMP, DefaultValue.STRING, DefaultValue.TIMESTAMP, DefaultValue.TIMESTAMP,
               DefaultValue.STRING, DefaultValue.UUID.toString(), DefaultValue.INT, DefaultValue.STRING,
                DefaultValue.BOOLEAN, DefaultValue.DOUBLE);
    }

    public String getID() {
        return ID;
    }

    public Ticket setID(String ID) {
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

    public String getDriverID() {
        return driverID;
    }

    public Ticket setDriverID(String driverID) {
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

    public Station getStation() {
        return station;
    }

    public Ticket setStation(Station station) {
        this.station = station;
        return this;
    }

    public Driver getDriver() {
        return driver;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<TicketType> getTicketTypes() {
        return ticketTypes;
    }

    public void setTicketTypes(List<TicketType> ticketTypes) {
        this.ticketTypes = ticketTypes;
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
