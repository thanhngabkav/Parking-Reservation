package com.atp.webservice.parking_reservation_10.services.webServices.accountService.models;

import java.io.Serializable;

public class OwnerChartDataSet implements Serializable{

    private String key;

    private double value;

    public OwnerChartDataSet() {
    }

    public String getKey() {
        return key;
    }

    public OwnerChartDataSet setKey(String key) {
        this.key = key;
        return this;
    }

    public double getValue() {
        return value;
    }

    public OwnerChartDataSet setValue(double value) {
        this.value = value;
        return this;
    }
}
