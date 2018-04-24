package com.atp.webservice.parking_reservation_10.services.mobileServices.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * This is a models for {@link com.atp.webservice.parking_reservation_10.entities.Vehicle}
 * Using for transfer data with Mobile Applications
 */
public class VehicleModel implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String licensePlate;

    private VehicleTypeModel vehicleTypeModel;

    private String driverID;

    public VehicleModel(String id, String name, VehicleTypeModel vehicleTypeModel, String licensePlate, String driverID) {
        this.id = id;
        this.name = name;
        this.vehicleTypeModel = vehicleTypeModel;
        this.licensePlate = licensePlate;
        this.driverID = driverID;
    }

    public VehicleModel() {
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

    public VehicleTypeModel getVehicleTypeModel() {
        return vehicleTypeModel;
    }

    public VehicleModel setVehicleTypeModel(VehicleTypeModel vehicleTypeModel) {
        this.vehicleTypeModel = vehicleTypeModel;
        return this;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public VehicleModel setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
        return this;
    }

    public String getDriverID() {
        return driverID;
    }

    public VehicleModel setDriverID(String driverID) {
        this.driverID = driverID;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VehicleModel vehicleModel = (VehicleModel) o;
        return Objects.equals(id, vehicleModel.id) &&
                Objects.equals(name, vehicleModel.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "VehicleModel{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", vehicleTypeModel=" + vehicleTypeModel +
                '}';
    }

}
