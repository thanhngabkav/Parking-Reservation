package com.atp.webservice.parking_reservation_10.entities;

import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Table(name = "vehicle")
@Entity
public class Vehicle implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID ID;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "license_plate", length = 50)
    private String licensePlate;

    @Column(name = "vehicle_type_id", insertable = false, updatable = false)
    private int vehicleTypeID;

    @Column(name = "driver_id", insertable = false, updatable = false)
    private int driveID;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id")
    private VehicleType vehicleType;

    @ManyToOne
    @JoinColumn(name = "driverID", nullable = false)
    private Driver driver;

    public Vehicle(String name, int vehicleTypeID, String licensePlate) {
        this.name = name;
        this.vehicleTypeID = vehicleTypeID;
        this.licensePlate = licensePlate;
    }

    public Vehicle() {
        this(DefaultValue.STRING,DefaultValue.INT, DefaultValue.STRING);
    }

    public UUID getID() {
        return ID;
    }

    public void setID(UUID ID) {
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

    public int getDriveID() {
        return driveID;
    }

    public void setDriveID(int driveID) {
        this.driveID = driveID;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
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
}
