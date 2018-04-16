package com.atp.webservice.parking_reservation_10.entities;

import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;
import com.atp.webservice.parking_reservation_10.entities.uitls.TableName;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

@Table(name = TableName.TICKET_TYPE)
@Entity
public class TicketType implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "vehicle_type_id")
    private int vehicleTypeID;

    @Column(name = "station_id")
    private int stationID;

    @Column(name = "price")
    private double price;

    @Column(name = "holding_time")
    private Time holdingTime;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id", updatable = false, insertable = false)
    private VehicleType vehicleType;

    @ManyToOne
    @JoinColumn(name = "station_id", insertable = false, updatable = false)
    private Station station;

    @OneToMany(mappedBy = "ticketType", cascade = CascadeType.PERSIST)
    private List<Ticket> tickets;

    public TicketType(int vehicleTypeID, int stationID, double price, Time holdingTime) {
        this.vehicleTypeID = vehicleTypeID;
        this.stationID = stationID;
        this.price = price;
        this.holdingTime = holdingTime;
    }

    public TicketType() {
        this(DefaultValue.INT,DefaultValue.INT,DefaultValue.DOUBLE, DefaultValue.TIME);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketType that = (TicketType) o;
        return ID == that.ID;
    }

    @Override
    public int hashCode() {

        return Objects.hash(ID);
    }

    public int getID() {
        return ID;
    }

    public int getVehicleTypeID() {
        return vehicleTypeID;
    }

    public void setVehicleTypeID(int vehicleTypeID) {
        this.vehicleTypeID = vehicleTypeID;
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Time getHoldingTime() {
        return holdingTime;
    }

    public void setHoldingTime(Time holdingTime) {
        this.holdingTime = holdingTime;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public String getName() {
        return name;
    }

    public TicketType setName(String name) {
        this.name = name;
        return this;
    }
}
