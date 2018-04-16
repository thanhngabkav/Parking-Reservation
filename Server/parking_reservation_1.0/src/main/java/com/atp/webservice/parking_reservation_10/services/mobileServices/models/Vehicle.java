package com.atp.webservice.parking_reservation_10.services.mobileServices.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * This is a models for {@link com.atp.webservice.parking_reservation_10.entities.Vehicle}
 * Using for transfer data with Mobile Applications
 */
public class Vehicle implements Serializable{

    private String id;

    private String name;

    private String licensePlate;

    private VehicleType vehicleType;

    private String driverID;

    public Vehicle(String id, String name, VehicleType vehicleType, String licensePlate, String driverID) {
        this.id = id;
        this.name = name;
        this.vehicleType = vehicleType;
        this.licensePlate = licensePlate;
        this.driverID = driverID;
    }

    public Vehicle() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Vehicle setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Vehicle setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public String getDriverID() {
        return driverID;
    }

    public Vehicle setDriverID(String driverID) {
        this.driverID = driverID;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(id, vehicle.id) &&
                Objects.equals(name, vehicle.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", vehicleType=" + vehicleType +
                '}';
    }

}
