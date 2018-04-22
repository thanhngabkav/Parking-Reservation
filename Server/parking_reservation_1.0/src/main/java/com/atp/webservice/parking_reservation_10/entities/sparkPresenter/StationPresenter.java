package com.atp.webservice.parking_reservation_10.entities.sparkPresenter;

import com.atp.webservice.parking_reservation_10.entities.Station;
import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.stereotype.Component;

import java.io.Serializable;

import java.util.Objects;

@Component
@JsonPropertyOrder({ "station_id", "application_id", "close_time", "coordinate", "created_date", "holding_slots", "image_link", "name",
"open_time", "owner_id", "parking_map_link", "station_id", "status", "total_slots", "used_slots"})
public class StationPresenter implements Serializable {

    @JsonProperty("station_id")
    private Integer station_id;

    @JsonProperty("application_id")
    private String application_id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("address")
    private String address;

    @JsonProperty("created_date")
    private String created_date;

    @JsonProperty("status")
    private String status;

    @JsonProperty("star")
    private Double star;

    @JsonProperty("open_time")
    private String open_time;

    @JsonProperty("close_time")
    private String close_time;

    @JsonProperty("image_link")
    private String image_link;

    @JsonProperty("total_slots")
    private String total_slots;

    @JsonProperty("holding_slots")
    private String holding_slots;

    @JsonProperty("used_slots")
    private String used_slots;

    @JsonProperty("parking_map_link")
    private String parking_map_link;

    @JsonProperty("owner_id")
    private String owner_id;

    @JsonProperty("coordinate")
    private String coordinate;


    public StationPresenter(Integer station_id, String application_id, String name, String address, String created_date, String status, Double star, String open_time, String close_time, String image_link, String total_slots, String holding_slots, String used_slots, String parking_map_link, String owner_id, String coordinate) {
        this.station_id = station_id;
        this.application_id = application_id;
        this.name = name;
        this.address = address;
        this.created_date = created_date;
        this.status = status;
        this.star = star;
        this.open_time = open_time;
        this.close_time = close_time;
        this.image_link = image_link;
        this.total_slots = total_slots;
        this.holding_slots = holding_slots;
        this.used_slots = used_slots;
        this.parking_map_link = parking_map_link;
        this.owner_id = owner_id;
        this.coordinate = coordinate;
    }

    public StationPresenter() {
        this(DefaultValue.INT, DefaultValue.STRING, DefaultValue.STRING, DefaultValue.STRING,
                DefaultValue.TIMESTAMP.toString(),DefaultValue.STRING, DefaultValue.DOUBLE, DefaultValue.TIMESTAMP.toString(), DefaultValue.TIMESTAMP.toString(),
                DefaultValue.STRING, DefaultValue.STRING, DefaultValue.STRING, DefaultValue.STRING, DefaultValue.STRING, DefaultValue.STRING, new String("0.0;0.0"));

    }

    public Integer getStation_id() {
        return station_id;
    }

    public void setStation_id(Integer station_id) {
        this.station_id = station_id;
    }

    public String getApplication_id() {
        return application_id;
    }

    public void setApplication_id(String application_id) {
        this.application_id = application_id;
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

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
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

    public String getTotal_slots() {
        return total_slots;
    }

    public void setTotal_slots(String total_slots) {
        this.total_slots = total_slots;
    }

    public String getHolding_slots() {
        return holding_slots;
    }

    public void setHolding_slots(String holding_slots) {
        this.holding_slots = holding_slots;
    }

    public String getUsed_slots() {
        return used_slots;
    }

    public void setUsed_slots(String used_slots) {
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

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StationPresenter that = (StationPresenter) o;
        return station_id == that.station_id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(station_id);
    }


    @Override
    public String toString() {
        return "StationPresenter{" +
                ", station_id=" + station_id +
                ", application_id='" + application_id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", created_date=" + created_date +
                ", status='" + status + '\'' +
                ", star=" + star +
                ", open_time='" + open_time + '\'' +
                ", close_time='" + close_time + '\'' +
                ", image_link='" + image_link + '\'' +
                ", total_slots=" + total_slots +
                ", holding_slots=" + holding_slots +
                ", used_slots=" + used_slots +
                ", parking_map_link='" + parking_map_link + '\'' +
                ", owner_id='" + owner_id + '\'' +
                ", coordinate='" + coordinate + '\'' +
                '}';
    }

    /**
     * Convert from {@link StationPresenter} to {@link Station} without One-Many Relationship
     * @return
     */
    public Station convertToEntities(){
        //Not Include One - Many relationship
//        Station station = new Station();
//        station.setID(this.station_id)
//                .setAddress(this.getAddress())
//                .setCloseTime(this.getClose_time())
//                .setImageLink(this.getImage_link())
//                .setCreatedDate(this.getCreated_date())
//                .setStar(this.getStar())
//                .setName(this.getName())
//                .setOpenTime(String.valueOf(this.getOpen_time()))
//                .setOwnerID(this.getOwner_id())
//                .setParkingMapLink(this.parking_map_link)
//                .setStatus(this.getStatus())
//                .setTotalSlots(this.getTotal_slots())
//                .setUsedSlots(this.getUsed_slots())
//                .setHoldingSlots(this.getHolding_slots())
//                .setApplicationID(this.getApplication_id())
//                .setCoordinate(this.getCoordinate())
//                return station;
        return null;
    }

    /**
     * Convert from @{@link Station} to {@link StationPresenter} without One-Many Relationship
     * @param station @{@link Station}
     */
    public void convertFromEntities(Station station){
        this.setAddress(station.getAddress());
        this.setClose_time(station.getCloseTime().toLocalTime().toString());
        this.setCreated_date(station.getCreatedDate().toString());
        this.setStation_id(station.getID());
        this.setImage_link(station.getImageLink());
        this.setStar(station.getStar());
        this.setName(station.getName());
        this.setOpen_time(station.getOpenTime().toLocalTime().toString());
        this.setOwner_id(station.getOwnerID());
        this.setParking_map_link(station.getParkingMapLink());
        this.setStatus(station.getStatus());
        this.setTotal_slots(station.getTotalSlots()+"");
        this.setUsed_slots(station.getUsedSlots()+"");
        this.setApplication_id(station.getApplicationID());
        this.setCoordinate(station.getCoordinate());
    }
}
