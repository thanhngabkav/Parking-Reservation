package com.atp.webservice.parking_reservation_10.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "user_role")
public class UserRole implements Serializable{

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleID;

    @Column(name = "role")
    private String role;

    @Column(name = "user_id")
    private UUID UserID;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id",insertable = false, updatable = false)
    private User user;

    public UserRole(String role, UUID userID) {
        this.role = role;
        UserID = userID;
    }

    public UserRole() {
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UUID getUserID() {
        return UserID;
    }

    public void setUserID(UUID userID) {
        UserID = userID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRole userRole = (UserRole) o;
        return roleID == userRole.roleID;
    }

    @Override
    public int hashCode() {

        return Objects.hash(roleID);
    }
}
