package com.atp.webservice.parking_reservation_10.services.mobileServices.vehicleService;

import com.atp.webservice.parking_reservation_10.services.mobileServices.models.VehicleModel;

import java.util.List;

public interface VehicleService {

    /**
     * Add new {@link VehicleModel}
     * @param vehicleModel {@link VehicleModel}
     * @return saved VehicleModel
     */
    VehicleModel addNewVehicle(VehicleModel vehicleModel);

    /**
     * edit vehicleModel
     * @param vehicleModel {@link VehicleModel}
     * @return true if success all false if not
     */
    VehicleModel editVehicle(VehicleModel vehicleModel);

    /**
     * Get all driver's vehicles
     * @param driverID driverID
     * @return List {@link VehicleModel}
     */
    List<VehicleModel> getAllDriverVehicleByDriver(String driverID);

    /**
     * Get list driver vehicles by page number
     * @param driverID
     * @return
     */
    List<VehicleModel> getPageListVehicleByDriver(String driverID, int pageNumber);

    /**
     * Get page list vehicles
     * @param pageNumber
     * @return
     */
    List<VehicleModel> getPageListVehicle(int pageNumber);

    /**
     * Get vehicle detail by id
     * @param vehicleID
     * @return
     */
    VehicleModel getVehicleById(String vehicleID);

    /**
     * Get page list vehicles by type
     * @param typeID
     * @param pageNumber
     * @return
     */
    List<VehicleModel> getPageListVehicleByTypeId(int typeID, int pageNumber);
}
