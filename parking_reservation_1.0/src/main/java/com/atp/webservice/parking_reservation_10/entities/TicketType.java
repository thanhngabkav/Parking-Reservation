package com.atp.webservice.parking_reservation_10.entities;

import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

@Table(name = "ticket_type")
@Entity
public class TicketType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "vehicle_type_id", updatable = false, insertable = false)
    private int vehicleTypeID;

    @Column(name = "parking_id", insertable = false, updatable = false)
    private int parkingID;

    @Column(name = "price")
    private double price;

    @Column(name = "holding_time")
    private Time holdingTime;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id")
    private VehicleType vehicleType;

    @ManyToOne
    @JoinColumn(name = "parking_id")
    private Parking parking;

    @OneToMany(mappedBy = "ticketType", cascade = CascadeType.MERGE)
    private List<Ticket> tickets;

    public TicketType(int vehicleTypeID, int parkingID, double price, Time holdingTime) {
        this.vehicleTypeID = vehicleTypeID;
        this.parkingID = parkingID;
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
}
