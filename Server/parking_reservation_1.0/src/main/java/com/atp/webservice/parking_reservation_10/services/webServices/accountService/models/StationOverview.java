package com.atp.webservice.parking_reservation_10.services.webServices.accountService.models;

import java.io.Serializable;

public class StationOverview implements Serializable {

    private String name;

    private String address;

    private String status;

    private int totalSlots;

    private int vehicleTypes;

    private int services;

    private int ticketTypes;

    public StationOverview() {
    }

    public String getName() {
        return name;
    }

    public StationOverview setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public StationOverview setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public StationOverview setStatus(String status) {
        this.status = status;
        return this;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public StationOverview setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
        return this;
    }

    public int getVehicleTypes() {
        return vehicleTypes;
    }

    public StationOverview setVehicleTypes(int vehicleTypes) {
        this.vehicleTypes = vehicleTypes;
        return this;
    }

    public int getServices() {
        return services;
    }

    public StationOverview setServices(int services) {
        this.services = services;
        return this;
    }

    public int getTicketTypes() {
        return ticketTypes;
    }

    public StationOverview setTicketTypes(int ticketTypes) {
        this.ticketTypes = ticketTypes;
        return this;
    }
}

