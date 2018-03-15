package com.atp.webservice.parking_reservation_10.entities;

import com.atp.webservice.parking_reservation_10.entities.uitls.TableName;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Table(name = TableName.USER)
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public class User implements Serializable{

    @Id
    @Column(name = "user_id")
    private UUID userID;

    @Column(name = "user_name")
    public String userName;

    @Column(name = "password")
    public String password;

    @Column(name = "user_type")
    public String userType;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserRole> userRoles;


    public User(String userName, String password, String userType, List<UserRole> userRoles) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
        this.userRoles = userRoles;
    }

    public User() {
    }

    public UUID getUserID() {
        return userID;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userID == user.userID;
    }

    @Override
    public int hashCode() {

        return Objects.hash(userID);
    }
}
