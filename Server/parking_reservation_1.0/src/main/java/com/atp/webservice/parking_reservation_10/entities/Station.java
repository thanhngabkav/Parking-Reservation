package com.atp.webservice.parking_reservation_10.entities;

import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;
import com.atp.webservice.parking_reservation_10.entities.uitls.TableName;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Table(name = TableName.STATION)
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class Station implements Serializable{

    @Id
    @JsonProperty("station_id")
    @Column(name = "station_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @JsonProperty("application_id")
    @Column(name = "application_id")
    private String applicationID;

    @JsonProperty("name")
    @Column(name = "name", length = 500)
    private String name;

    @JsonProperty("address")
    @Column(name = "address", length = 500)
    private String address;

    @JsonProperty("created_date")
    @Column(name = "created_date")
    private Timestamp createdDate;

    @JsonProperty("status")
    @Column(name = "status", length = 50)
    private String status;

    @JsonProperty("star")
    @Column(name = "star")
    private double star;

    @JsonProperty("open_time")
    @Column(name = "open_time")
    private Time openTime;

    @JsonProperty("Close_time")
    @Column(name = "close_time")
    private Time closeTime;

    @JsonProperty("image_link")
    @Column(name = "image_link", length = 500)
    private String imageLink;

    @JsonProperty("total_slots")
    @Column(name = "total_slots")
    private int totalSlots;

    @JsonProperty("used_slots")
    @Column(name = "used_slots")
    private int usedSlots;

    @JsonProperty("holding_slot")
    @Column(name = "holding_slots")
    private int holdingSlots;

    @JsonProperty("parking_map_link")
    @Column(name = "parking_map_link")
    private String parkingMapLink;

    @JsonProperty("coordinate")
    @Column(name = "coordinate", length = 200)
    private String coordinate;

    @JsonProperty("owner_id")
    @Column(name = "owner_id")
    private String ownerID;

    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    @ManyToOne
    private Owner owner;

    @OneToMany(mappedBy = "station", cascade = CascadeType.PERSIST)
    @JsonIgnore
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    @JsonIgnore
    private  List<Comment> comments;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(
            name = "station_service",
            joinColumns = @JoinColumn(name = "station_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Service> services;

    @OneToMany(mappedBy = "station")
    private List<StationVehicleType> stationVehicleTypes;

//    @OneToMany(mappedBy = "parking", cascade = CascadeType.MERGE)
//    private List<ParkingStation> parkingStations;

    public Station(String applicationID,String name, String address, Timestamp createdDate, String status, int level, Time openTime, Time closeTime, String imageLink, int totalSlots, int holdingSlots, int usedSlots, String parkingMapLink, String coordinate, String ownerID) {
        this.applicationID = applicationID;
        this.name = name;
        this.address = address;
        this.createdDate = createdDate;
        this.status = status;
        this.star = level;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.imageLink = imageLink;
        this.totalSlots = totalSlots;
        this.holdingSlots = holdingSlots;
        this.usedSlots = usedSlots;
        this.parkingMapLink = parkingMapLink;
        this.coordinate = coordinate;
        this.ownerID = ownerID;
    }

    public Station() {
        this(DefaultValue.STRING, DefaultValue.STRING, DefaultValue.STRING,
                DefaultValue.TIMESTAMP,DefaultValue.STRING, DefaultValue.INT, DefaultValue.TIME, DefaultValue.TIME,
                DefaultValue.STRING, DefaultValue.INT, DefaultValue.INT, DefaultValue.INT, DefaultValue.STRING, DefaultValue.STRING, DefaultValue.UUID.toString());
    }

    public int getID() {
        return ID;
    }

    public Station setID(int ID) {
        this.ID = ID;
        return this;
    }

    public String getApplicationID() {
        return applicationID;
    }

    public Station setApplicationID(String applicationID) {
        this.applicationID = applicationID;
        return this;
    }

    public String getName() {
        return name;
    }

    public Station setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Station setAddress(String address) {
        this.address = address;
        return this;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public Station setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Station setStatus(String status) {
        this.status = status;
        return this;
    }

    public double getStar() {
        return star;
    }

    public Station setStar(double star) {
        this.star = star;
        return  this;
    }

    public Time getOpenTime() {
        return openTime;
    }

    public Station setOpenTime(Time openTime) {
        this.openTime = openTime;
        return this;
    }

    public Time getCloseTime() {
        return closeTime;
    }

    public Station setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
        return this;
    }

    public String getImageLink() {
        return imageLink;
    }

    public Station setImageLink(String imageLink) {
        this.imageLink = imageLink;
        return this;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public Station setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
        return this;
    }

    public int getHoldingSlots() {
        return holdingSlots;
    }

    public Station setHoldingSlots(int holdingSlots) {
        this.holdingSlots = holdingSlots;
        return this;
    }

    public int getUsedSlots() {
        return usedSlots;
    }

    public Station setUsedSlots(int usedSlots) {
        this.usedSlots = usedSlots;
        return this;
    }

    public String getParkingMapLink() {
        return parkingMapLink;
    }

    public Station setParkingMapLink(String parkingMapLink) {
        this.parkingMapLink = parkingMapLink;
        return this;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public Station setOwnerID(String ownerID) {
        this.ownerID = ownerID;
        return this;
    }

    public Station setOwner(Owner owner) {
        this.owner = owner;
        return this;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }


    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    //    public List<ParkingStation> getParkingStations() {
//        return parkingStations;
//    }
//
//    public StationOverview setParkingStations(List<ParkingStation> parkingStations) {
//        this.parkingStations = parkingStations;
//        return this;
//    }


    public List<StationVehicleType> getStationVehicleTypes() {
        return stationVehicleTypes;
    }

    public void setStationVehicleTypes(List<StationVehicleType> stationVehicleTypes) {
        this.stationVehicleTypes = stationVehicleTypes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return ID == station.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
