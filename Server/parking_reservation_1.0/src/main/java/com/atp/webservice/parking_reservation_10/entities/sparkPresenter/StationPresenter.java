package com.atp.webservice.parking_reservation_10.entities.sparkPresenter;

import com.atp.webservice.parking_reservation_10.entities.Station;
import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;

public class StationPresenter implements Serializable{

    @JsonProperty("id")
    private int id;

    @JsonProperty("key_pair")
    private String key_pair;

    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String address;

    @JsonProperty("created_date")
    private Timestamp created_date;

    @JsonProperty("status")
    private String status;

    @JsonProperty("level")
    @Column(name = "level")
    private int level;

    @JsonProperty("open_time")
    private String open_time;

    @JsonProperty("Close_time")
    private String close_time;

    @JsonProperty("image_link")
    private String image_link;

    @JsonProperty("total_slots")
    private int total_slots;

    @JsonProperty("used_slots")
    private int used_slots;

    @JsonProperty("parking_map_link")
    private String parking_map_link;

    @JsonProperty("owner_id")
    private String owner_id;

    public StationPresenter(int id, String key_pair, String name, String address, Timestamp created_date, String status, int level, String open_time, String close_time, String image_link, int total_slots, int used_slots, String parking_map_link, String owner_id) {
        this.id = id;
        this.key_pair = key_pair;
        this.name = name;
        this.address = address;
        this.created_date = created_date;
        this.status = status;
        this.level = level;
        this.open_time = open_time;
        this.close_time = close_time;
        this.image_link = image_link;
        this.total_slots = total_slots;
        this.used_slots = used_slots;
        this.parking_map_link = parking_map_link;
        this.owner_id = owner_id;
    }

    public StationPresenter() {
        this(DefaultValue.INT, DefaultValue.STRING, DefaultValue.STRING, DefaultValue.STRING,
                DefaultValue.TIMESTAMP,DefaultValue.STRING, DefaultValue.INT, DefaultValue.STRING, DefaultValue.STRING,
                DefaultValue.STRING, DefaultValue.INT, DefaultValue.INT, DefaultValue.STRING, DefaultValue.STRING);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey_pair() {
        return key_pair;
    }

    public void setKey_pair(String key_pair) {
        this.key_pair = key_pair;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Timestamp created_date) {
        this.created_date = created_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getOpen_time() {
        return open_time;
    }

    public void setOpen_time(String open_time) {
        this.open_time = open_time;
    }

    public String getClose_time() {
        return close_time;
    }

    public void setClose_time(String close_time) {
        this.close_time = close_time;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public int getTotal_slots() {
        return total_slots;
    }

    public void setTotal_slots(int total_slots) {
        this.total_slots = total_slots;
    }

    public int getUsed_slots() {
        return used_slots;
    }

    public void setUsed_slots(int used_slots) {
        this.used_slots = used_slots;
    }

    public String getParking_map_link() {
        return parking_map_link;
    }

    public void setParking_map_link(String parking_map_link) {
        this.parking_map_link = parking_map_link;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationPresenter that = (StationPresenter) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }


    /**
     * Convert from {@link StationPresenter} to {@link Station} without One-Many Relationship
     * @return
     */
    public Station convertToParking(){
        //Not Include One - Many relationship
        Station station = new Station();
        station.setID(this.id)
                .setAddress(this.getAddress())
                .setCloseTime(Time.valueOf(this.getClose_time()))
                .setImageLink(this.getImage_link())
                .setKeyPair(this.getKey_pair())
                .setCreatedDate(this.getCreated_date())
                .setLevel(this.getLevel())
                .setName(this.getName())
                .setOpenTime(Time.valueOf(this.getOpen_time()))
                .setOwner(null)//can be find Owner here if necessary
                .setOwnerID(this.getOwner_id())
                .setParkingMapLink(this.parking_map_link)
                .setStatus(this.getStatus())
                //.setParkingStations(null)//can be find List<Station Station> here if necessary
                //.setTickets(null)
                .setTotalSlots(this.getTotal_slots())
                .setUsedSlots(this.getUsed_slots())
                .setCloseTime(Time.valueOf(this.getClose_time()));
        return station;
    }

    /**
     * Convert from @{@link Station} to {@link StationPresenter} without One-Many Relationship
     * @param station @{@link Station}
     */
    public void convertFromParking(Station station){
        this.setAddress(station.getAddress());
        this.setClose_time(station.getCloseTime().toString());
        this.setCreated_date(station.getCreatedDate());
        this.setId(station.getID());
        this.setImage_link(station.getImageLink());
        this.setKey_pair(station.getKeyPair());
        this.setLevel(station.getLevel());
        this.setName(station.getName());
        this.setOpen_time(station.getOpenTime().toString());
        this.setOwner_id(station.getOwnerID().toString());
        this.setParking_map_link(station.getParkingMapLink());
        this.setStatus(station.getStatus());
        this.setTotal_slots(station.getTotalSlots());
        this.setUsed_slots(station.getUsedSlots());
    }
}
