package com.atp.webservice.parking_reservation_10.entities;

import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;
import com.atp.webservice.parking_reservation_10.entities.uitls.TableName;
import org.codehaus.jackson.annotate.JsonIgnore;

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

    @Column(name = "price")
    private double price;

    @Column(name = "holding_time")
    private Time holdingTime;

    @Column(name = "name")
    private String name;

    @Column(name = "service_id")
    private int serviceID;

    @Column(name = "station_vehicle_type_id")
    private int stationVehicleTypeID;


    @ManyToOne
    @JoinColumn(name = "service_id", insertable = false , updatable = false)
    private Service service;

    @ManyToOne
    @JoinColumn(name = "station_vehicle_type_id", insertable = false, updatable = false)
    private StationVehicleType stationVehicleType;

    @ManyToMany(mappedBy = "ticketTypes")
    @JsonIgnore
    private List<Ticket> tickets;

    public TicketType(int stationVehicleTypeID ,double price, Time holdingTime) {
        this.stationVehicleTypeID = stationVehicleTypeID;
        this.price = price;
        this.holdingTime = holdingTime;
    }


    public TicketType() {
        this(DefaultValue.INT,DefaultValue.DOUBLE, DefaultValue.TIME);
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

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public int getStationVehicleTypeID() {
        return stationVehicleTypeID;
    }

    public void setStationVehicleTypeID(int stationVehicleTypeID) {
        this.stationVehicleTypeID = stationVehicleTypeID;
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


//
//    public void setStation(Station station) {
//        this.station = station;
//    }
//
//    public List<Ticket> getTickets() {
//        return tickets;
//    }
//
//    public void setTickets(List<Ticket> tickets) {
//        this.tickets = tickets;
//    }

    public String getName() {
        return name;
    }

    public TicketType setName(String name) {
        this.name = name;
        return this;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }
}
