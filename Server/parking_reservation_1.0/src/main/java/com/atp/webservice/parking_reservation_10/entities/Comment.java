package com.atp.webservice.parking_reservation_10.entities;

import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "user_id")
    private String userID;

    @Column(name = "station_id")
    private int stationID;

    @Column(name = "content")
    @Type(type = "text")
    private String content;

    @Column(name = "star")
    private double star;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private Driver driver;

    @ManyToOne
    @JoinColumn(name = "station_id", insertable = false, updatable = false)
    private Station station;


    public Comment(String userID, int stationID, String content, double star, Timestamp createdTime) {
        this.id = DefaultValue.UUID.toString();
        this.userID = userID;
        this.stationID = stationID;
        this.content = content;
        this.star = star;
        this.createdTime = createdTime;
    }

    public Comment() {
        this(DefaultValue.STRING, DefaultValue.INT, DefaultValue.STRING, DefaultValue.DOUBLE, DefaultValue.TIMESTAMP);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }
}
