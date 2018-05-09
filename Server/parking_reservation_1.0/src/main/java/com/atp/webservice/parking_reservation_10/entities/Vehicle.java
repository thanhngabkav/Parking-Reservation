package com.atp.webservice.parking_reservation_10.entities;

import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;
import com.atp.webservice.parking_reservation_10.entities.uitls.TableName;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Table(name = TableName.VEHICLE)
@Entity
public class Vehicle implements Serializable{

    @Id
    @Column(name = "id")
    private String ID;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "license_plate", length = 50)
    private String licensePlate;

    @Column(name = "vehicle_type_id")
    private int vehicleTypeID;

    @Column(name = "driver_id", nullable = false)
    private String driverID;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id", insertable = false, updatable = false)
    private VehicleType vehicleType;

    @ManyToOne
    @JoinColumn(name = "driver_id", insertable = false, updatable = false)
    private Driver driver;

    @OneToMany(mappedBy = "vehicle", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    @JsonIgnore
    private List<Ticket> tickets;


    public Vehicle(String name, int vehicleTypeID, String licensePlate) {
        this.name = name;
        this.vehicleTypeID = vehicleTypeID;
        this.licensePlate = licensePlate;
    }

    public Vehicle() {
        this(DefaultValue.STRING,DefaultValue.INT, DefaultValue.STRING);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public int getVehicleTypeID() {
        return vehicleTypeID;
    }

    public void setVehicleTypeID(int vehicleTypeID) {
        this.vehicleTypeID = vehicleTypeID;
    }

    public String getDriverID() {
        return driverID;
    }


    public void setDriverID(String driveID) {
        this.driverID = driveID;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public Vehicle setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public Vehicle setDriver(Driver driver) {
        this.driver = driver;
        return this;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return ID == vehicle.ID;
    }

    @Override
    public int hashCode() {

        return Objects.hash(ID);
    }

    @Override
    public String toString() {
        return "VehicleModel{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", vehicleTypeID=" + vehicleTypeID +
                ", driverID='" + driverID + '\'' +
                ", vehicleType=" + vehicleType +
                ", driver=" + driver +
                ", tickets=" + tickets +
                '}';
    }
}