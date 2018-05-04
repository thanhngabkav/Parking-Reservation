package com.atp.webservice.parking_reservation_10.services.models;

import java.io.Serializable;

public class ChartDataModel implements Serializable {

    long numCheckedTickets;

    long numExpiredTickets;

    public long getNumCheckedTickets() {
        return numCheckedTickets;
    }

    public void setNumCheckedTickets(long numCheckedTickets) {
        this.numCheckedTickets = numCheckedTickets;
    }

    public long getNumExpiredTickets() {
        return numExpiredTickets;
    }

    public void setNumExpiredTickets(long numExpiredTickets) {
        this.numExpiredTickets = numExpiredTickets;
    }

    public ChartDataModel() {
       this(0,0);
    }

    public ChartDataModel(long numCheckedTickets, long numExpiredTickets) {
        this.numCheckedTickets = numCheckedTickets;
        this.numExpiredTickets = numExpiredTickets;
    }

    @Override
    public String toString() {
        return "ChartDataModel{" +
                "numCheckedTickets=" + numCheckedTickets +
                ", numExpiredTickets=" + numExpiredTickets +
                '}';
    }
}
