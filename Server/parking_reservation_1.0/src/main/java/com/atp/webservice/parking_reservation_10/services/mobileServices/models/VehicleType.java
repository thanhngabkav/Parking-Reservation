package com.atp.webservice.parking_reservation_10.services.mobileServices.models;

import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;
import scala.Serializable;

public class VehicleType implements Serializable{

    public VehicleType() {
        this(DefaultValue.INT, DefaultValue.STRING);
    }

    public VehicleType(int typeID, String typeName) {
        this.typeID = typeID;
        this.typeName = typeName;
    }

    private int typeID;

    private String typeName;

    public int getTypeID() {
        return typeID;
    }

    public VehicleType setTypeID(int typeID) {
        this.typeID = typeID;
        return this;
    }

    public String getTypeName() {
        return typeName;
    }

    public VehicleType setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }
}
