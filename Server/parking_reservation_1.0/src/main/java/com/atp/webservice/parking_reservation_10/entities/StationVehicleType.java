package com.atp.webservice.parking_reservation_10.entities;

import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;
import com.atp.webservice.parking_reservation_10.entities.uitls.TableName;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = TableName.STATION_VEHICLE_TYPE)
public class StationVehicleType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "vehicle_type_id")
    private int vehicleTypeId;

    @Column(name = "station_id")
    private int stationID;

    @Column(name = "total_slot")
    private int totalSlots;

    @Column(name = "used_slots")
    private int usedSlots;

    @Column(name = "hlolding_slots")
    private int holdingSlots;

    @ManyToOne
    @JoinColumn(name = "station_id", insertable = false, updatable = false)
    private Station station;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id", insertable = false, updatable = false)
    private VehicleType vehicleType;

    @OneToMany(mappedBy = "stationVehicleType")
    @JsonIgnore
    private List<TicketType> ticketTypes;


    public StationVehicleType(int vehicleTypeId, int stationID, int totalSlots, int usedSlots, int holdingSlots) {
        this.vehicleTypeId = vehicleTypeId;
        this.stationID = stationID;
        this.totalSlots = totalSlots;
        this.usedSlots = usedSlots;
        this.holdingSlots = holdingSlots;
    }

    public StationVehicleType() {
        this(DefaultValue.INT, DefaultValue.INT, DefaultValue.INT, DefaultValue.INT, DefaultValue.INT);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public void setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
    }

    public int getUsedSlots() {
        return usedSlots;
    }

    public void setUsedSlots(int usedSlots) {
        this.usedSlots = usedSlots;
    }

    public int getHoldingSlots() {
        return holdingSlots;
    }

    public void setHoldingSlots(int holdingSlots) {
        this.holdingSlots = holdingSlots;
    }

    @Override
    public String toString() {
        return "StationVehicleType{" +
                "id=" + id +
                ", vehicleTypeId=" + vehicleTypeId +
                ", stationID=" + stationID +
                ", totalSlots=" + totalSlots +
                ", usedSlots=" + usedSlots +
                ", holdingSlots=" + holdingSlots +
                '}';
    }
}
