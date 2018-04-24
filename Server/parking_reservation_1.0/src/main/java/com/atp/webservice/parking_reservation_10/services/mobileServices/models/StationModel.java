package com.atp.webservice.parking_reservation_10.services.mobileServices.models;

import com.atp.webservice.parking_reservation_10.entities.Service;
import com.atp.webservice.parking_reservation_10.entities.StationVehicleType;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class StationModel implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int ID;

    private String applicationID;

    private String name;

    private String address;

    private Timestamp createdDate;

    private String status;

    private double star;

    private Time openTime;

    private Time closeTime;

    private String imageLink;

    private int totalSlots;

    private int usedSlots;

    private int holdingSlots;

    private String parkingMapLink;

    private String coordinate;

    private UUID ownerID;

    private List<Service> services;

    List<StationVehicleTypeModel> stationVehicleTypes;

    public StationModel() {
    }

    public StationModel(int ID, String applicationID, String name, String address, Timestamp createdDate, String status, double star, Time openTime, Time closeTime, String imageLink, int totalSlots, int usedSlots, int holdingSlots, String parkingMapLink, String coordinate, UUID ownerID) {
        this.ID = ID;
        this.applicationID = applicationID;
        this.name = name;
        this.address = address;
        this.createdDate = createdDate;
        this.status = status;
        this.star = star;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.imageLink = imageLink;
        this.totalSlots = totalSlots;
        this.usedSlots = usedSlots;
        this.holdingSlots = holdingSlots;
        this.parkingMapLink = parkingMapLink;
        this.coordinate = coordinate;
        this.ownerID = ownerID;
    }

    public int getID() {
        return ID;
    }

    public StationModel setID(int ID) {
        this.ID = ID;
        return this;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public StationModel setApplicationID(String applicationID) {
        this.applicationID = applicationID;
        return this;
    }

    public String getName() {
        return name;
    }

    public StationModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public StationModel setAddress(String address) {
        this.address = address;
        return this;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public StationModel setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public StationModel setStatus(String status) {
        this.status = status;
        return this;
    }

    public double getStar() {
        return star;
    }

    public StationModel setStar(double star) {
        this.star = star;
        return  this;
    }

    public Time getOpenTime() {
        return openTime;
    }

    public StationModel setOpenTime(Time openTime) {
        this.openTime = openTime;
        return this;
    }

    public Time getCloseTime() {
        return closeTime;
    }

    public StationModel setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
        return this;
    }

    public String getImageLink() {
        return imageLink;
    }

    public StationModel setImageLink(String imageLink) {
        this.imageLink = imageLink;
        return this;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public StationModel setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
        return this;
    }

    public int getUsedSlots() {
        return usedSlots;
    }

    public StationModel setUsedSlots(int usedSlots) {
        this.usedSlots = usedSlots;
        return this;
    }

    public String getParkingMapLink() {
        return parkingMapLink;
    }

    public StationModel setParkingMapLink(String parkingMapLink) {
        this.parkingMapLink = parkingMapLink;
        return this;
    }

    public int getHoldingSlots() {
        return holdingSlots;
    }

    public StationModel setHoldingSlots(int holdingSlots) {
        this.holdingSlots = holdingSlots;
        return this;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public StationModel setCoordinate(String coordinate) {
        this.coordinate = coordinate;
        return this;
    }

    public UUID getOwnerID() {
        return ownerID;
    }

    public StationModel setOwnerID(UUID ownerID) {
        this.ownerID = ownerID;
        return this;
    }

    public List<Service> getServices() {
        return services;
    }

    public StationModel setServices(List<Service> services) {
        this.services = services;
        return this;
    }


    public List<StationVehicleTypeModel> getStationVehicleTypes() {
        return stationVehicleTypes;
    }

    public void setStationVehicleTypes(List<StationVehicleTypeModel> stationVehicleTypes) {
        this.stationVehicleTypes = stationVehicleTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationModel stationModel = (StationModel) o;
        return ID == stationModel.ID;
    }

    @Override
    public int hashCode() {

        return Objects.hash(ID);
    }

    @Override
    public String toString() {
        return "StationModel{" +
                "ID=" + ID +
                ", applicationID='" + applicationID + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", createdDate=" + createdDate +
                ", status='" + status + '\'' +
                ", star=" + star +
                ", openTime=" + openTime +
                ", closeTime=" + closeTime +
                ", imageLink='" + imageLink + '\'' +
                ", totalSlots=" + totalSlots +
                ", usedSlots=" + usedSlots +
                ", holdingSlots=" + holdingSlots +
                ", parkingMapLink='" + parkingMapLink + '\'' +
                ", coordinate='" + coordinate + '\'' +
                ", ownerID=" + ownerID +
                ", services=" + services +
                ", stationVehicleTypes=" + stationVehicleTypes +
                '}';
    }
}
