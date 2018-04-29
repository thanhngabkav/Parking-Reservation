package com.atp.webservice.parking_reservation_10.services.models;

import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;
import scala.Serializable;

public class VehicleTypeModel implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public VehicleTypeModel() {
        this(DefaultValue.INT, DefaultValue.STRING);
    }

    public VehicleTypeModel(int typeID, String typeName) {
        this.typeID = typeID;
        this.typeName = typeName;
    }

    private int typeID;

    private String typeName;

    public int getTypeID() {
        return typeID;
    }

    public VehicleTypeModel setTypeID(int typeID) {
        this.typeID = typeID;
        return this;
    }

    public String getTypeName() {
        return typeName;
    }

    public VehicleTypeModel setTypeName(String typeName) {
        this.typeName = typeName;
        return this;
    }
}
