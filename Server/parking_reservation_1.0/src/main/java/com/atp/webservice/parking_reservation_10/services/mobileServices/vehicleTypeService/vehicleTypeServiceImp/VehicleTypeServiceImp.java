package com.atp.webservice.parking_reservation_10.services.mobileServices.vehicleTypeService.vehicleTypeServiceImp;


import com.atp.webservice.parking_reservation_10.services.mobileServices.models.VehicleType;
import com.atp.webservice.parking_reservation_10.services.mobileServices.vehicleTypeService.VehicleTypeService;

public class VehicleTypeServiceImp implements VehicleTypeService {


    /**
     * Convert from Entoty to model
     * @param enVehicleType
     * @return
     */
    public static VehicleType convertFromEntity(
            com.atp.webservice.parking_reservation_10.entities.VehicleType enVehicleType){
        if(enVehicleType ==  null)
            return null;
        VehicleType mVehicleType = new VehicleType();
        mVehicleType.setTypeID(enVehicleType.getID());
        mVehicleType.setTypeName(enVehicleType.getName());

        return mVehicleType;
    }

    /**
     * Convert from model to Entity
     * @param mVehicleType
     * @return
     */
    public static com.atp.webservice.parking_reservation_10.entities.VehicleType convertFromModel(VehicleType mVehicleType){
        if(mVehicleType ==  null)
            return  null;
        com.atp.webservice.parking_reservation_10.entities.VehicleType enVehicleType = new com.atp.webservice.parking_reservation_10.entities.VehicleType();
        enVehicleType.setName(mVehicleType.getTypeName());
        enVehicleType.setID(mVehicleType.getTypeID());

        return enVehicleType;
    }
}
