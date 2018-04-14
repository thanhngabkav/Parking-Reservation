package com.atp.webservice.parking_reservation_10.services.webServices.accountService.models;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class OwnerChartModel implements Serializable {


    private Timestamp startTime;

    private Timestamp endTime;

    List<OwnerChartDataSet> chartDataSets;

    public OwnerChartModel() {
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public OwnerChartModel setStartTime(Timestamp startTime) {
        this.startTime = startTime;
        return this;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public OwnerChartModel setEndTime(Timestamp endTime) {
        this.endTime = endTime;
        return this;
    }

    public List<OwnerChartDataSet> getChartDataSets() {
        return chartDataSets;
    }

    public OwnerChartModel setChartDataSets(List<OwnerChartDataSet> chartDataSets) {
        this.chartDataSets = chartDataSets;
        return this;
    }
}
