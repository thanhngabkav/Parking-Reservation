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

    private int vehicleTypeID;

    private String licensePlate;

    private String driverID;


    public Vehicle(String id, String name, int vehicleTypeID, String licensePlate) {
        this.id = id;
        this.name = name;
        this.vehicleTypeID = vehicleTypeID;
        this.licensePlate = licensePlate;
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

    public int getVehicleTypeID() {
        return vehicleTypeID;
    }

    public Vehicle setVehicleTypeID(int vehicleTypeID) {
        this.vehicleTypeID = vehicleTypeID;
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
                ", vehicleTypeID=" + vehicleTypeID +
                '}';
    }

    /**
     * Convert from {@link com.atp.webservice.parking_reservation_10.entities.Vehicle} to {@link Vehicle}
     * @param vehicleEntity {@link com.atp.webservice.parking_reservation_10.entities.Vehicle}
     * @return Vehicle
     */
    public Vehicle convertFromEntity(com.atp.webservice.parking_reservation_10.entities.Vehicle vehicleEntity){
        this.setId(vehicleEntity.getID());
        this.setName(vehicleEntity.getName());
        this.setVehicleTypeID(vehicleEntity.getVehicleTypeID());
        this.setLicensePlate(vehicleEntity.getLicensePlate());
        return this;
    }
}
