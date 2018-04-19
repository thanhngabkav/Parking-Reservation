package com.atp.webservice.parking_reservation_10.services.mobileServices.vehicleTypeService.vehicleTypeServiceImp;


import com.atp.webservice.parking_reservation_10.services.mobileServices.models.VehicleTypeModel;
import com.atp.webservice.parking_reservation_10.services.mobileServices.vehicleTypeService.VehicleTypeService;

public class VehicleTypeServiceImp implements VehicleTypeService {


    /**
     * Convert from Entoty to model
     * @param enVehicleType
     * @return
     */
    public static VehicleTypeModel convertFromEntity(
            com.atp.webservice.parking_reservation_10.entities.VehicleType enVehicleType){
        if(enVehicleType ==  null)
            return null;
        VehicleTypeModel mVehicleTypeModel = new VehicleTypeModel();
        mVehicleTypeModel.setTypeID(enVehicleType.getID());
        mVehicleTypeModel.setTypeName(enVehicleType.getName());

        return mVehicleTypeModel;
    }

    /**
     * Convert from model to Entity
     * @param mVehicleTypeModel
     * @return
     */
    public static com.atp.webservice.parking_reservation_10.entities.VehicleType convertFromModel(VehicleTypeModel mVehicleTypeModel){
        if(mVehicleTypeModel ==  null)
            return  null;
        com.atp.webservice.parking_reservation_10.entities.VehicleType enVehicleType = new com.atp.webservice.parking_reservation_10.entities.VehicleType();
        enVehicleType.setName(mVehicleTypeModel.getTypeName());
        enVehicleType.setID(mVehicleTypeModel.getTypeID());

        return enVehicleType;
    }
}
