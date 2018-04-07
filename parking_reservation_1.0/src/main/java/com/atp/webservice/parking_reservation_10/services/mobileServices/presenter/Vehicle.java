package com.atp.webservice.parking_reservation_10.services.mobileServices.presenter;

import java.io.Serializable;
import java.util.Objects;

/**
 * This is a presenter for {@link com.atp.webservice.parking_reservation_10.entities.Vehicle}
 * Using for transfer data with Mobile Applications
 */
public class Vehicle implements Serializable{

    public String id;

    public String name;

    public Vehicle(String id, String name) {
        this.id = id;
        this.name = name;
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
                '}';
    }
}
