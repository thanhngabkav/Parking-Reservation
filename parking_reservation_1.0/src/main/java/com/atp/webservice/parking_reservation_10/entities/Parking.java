package com.atp.webservice.parking_reservation_10.entities;

import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Table(name = "parking")
@Entity
public class Parking  implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "key_pair", columnDefinition = "TEXT")
    private String keyPair;

    @Column(name = "name", length = 500)
    private String name;

    @Column(name = "address", length = 500)
    private String address;

    @Column(name = "created_date")
    private Timestamp createdDate;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "level")
    private int level;

    @Column(name = "open_time")
    private Time openTime;

    @Column(name = "close_time")
    private Time closeTime;

    @Column(name = "image_link", length = 500)
    private String imageLink;

    @Column(name = "total_slots")
    private int totalSlots;

    @Column(name = "used_slots")
    private int usedSlots;

    @Column(name = "parking_map_link")
    private String parkingMapLink;

    @Column(name = "owner_id", nullable = false, insertable = false, updatable = false)
    private int ownerID;

    @JoinColumn(name = "owner_id")
    @ManyToOne
    private ParkingOwner owner;

    @OneToMany(mappedBy = "parking", cascade = CascadeType.MERGE)
    private List<Ticket> tickets;

    @OneToMany(mappedBy = "parking", cascade = CascadeType.MERGE)
    private List<ParkingStation> parkingStations;

    public Parking(String keyPair, String name, String address, Timestamp createdDate, String status, int level, Time openTime, Time closeTime, String imageLink, int totalSlots, int usedSlots, String parkingMapLink, int ownerID) {
        this.keyPair = keyPair;
        this.name = name;
        this.address = address;
        this.createdDate = createdDate;
        this.status = status;
        this.level = level;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.imageLink = imageLink;
        this.totalSlots = totalSlots;
        this.usedSlots = usedSlots;
        this.parkingMapLink = parkingMapLink;
        this.ownerID = ownerID;
    }

    public Parking() {
        this(DefaultValue.STRING, DefaultValue.STRING, DefaultValue.STRING,
                DefaultValue.TIMESTAMP,DefaultValue.STRING, DefaultValue.INT, DefaultValue.TIME, DefaultValue.TIME,
                DefaultValue.STRING, DefaultValue.INT, DefaultValue.INT, DefaultValue.STRING, DefaultValue.INT);
    }

    public int getID() {
        return ID;
    }

    public Parking setID(int ID) {
        this.ID = ID;
        return this;
    }

    public String getKeyPair() {
        return keyPair;
    }

    public Parking setKeyPair(String keyPair) {
        this.keyPair = keyPair;
        return this;
    }

    public String getName() {
        return name;
    }

    public Parking setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Parking setAddress(String address) {
        this.address = address;
        return this;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public Parking setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Parking setStatus(String status) {
        this.status = status;
        return this;
    }

    public int getLevel() {
        return level;
    }

    public Parking setLevel(int level) {
        this.level = level;
        return this;
    }

    public Time getOpenTime() {
        return openTime;
    }

    public Parking setOpenTime(Time openTime) {
        this.openTime = openTime;
        return this;
    }

    public Time getCloseTime() {
        return closeTime;
    }

    public Parking setCloseTime(Time closeTime) {
        this.closeTime = closeTime;
        return this;
    }

    public String getImageLink() {
        return imageLink;
    }

    public Parking setImageLink(String imageLink) {
        this.imageLink = imageLink;
        return this;
    }

    public int getTotalSlots() {
        return totalSlots;
    }

    public Parking setTotalSlots(int totalSlots) {
        this.totalSlots = totalSlots;
        return this;
    }

    public int getUsedSlots() {
        return usedSlots;
    }

    public Parking setUsedSlots(int usedSlots) {
        this.usedSlots = usedSlots;
        return this;
    }

    public String getParkingMapLink() {
        return parkingMapLink;
    }

    public Parking setParkingMapLink(String parkingMapLink) {
        this.parkingMapLink = parkingMapLink;
        return this;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public Parking setOwnerID(int ownerID) {
        this.ownerID = ownerID;
        return this;
    }

    public ParkingOwner getOwner() {
        return owner;
    }

    public Parking setOwner(ParkingOwner owner) {
        this.owner = owner;
        return this;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public Parking setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
        return this;
    }

    public List<ParkingStation> getParkingStations() {
        return parkingStations;
    }

    public Parking setParkingStations(List<ParkingStation> parkingStations) {
        this.parkingStations = parkingStations;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parking parking = (Parking) o;
        return ID == parking.ID;
    }

    @Override
    public int hashCode() {

        return Objects.hash(ID);
    }
}
