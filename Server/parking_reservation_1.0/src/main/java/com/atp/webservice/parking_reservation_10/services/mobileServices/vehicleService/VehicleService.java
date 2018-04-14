package com.atp.webservice.parking_reservation_10.services.mobileServices.vehicleService;

import com.atp.webservice.parking_reservation_10.services.mobileServices.models.Vehicle;

import java.util.List;

public interface VehicleService {

    /**
     * Add new {@link Vehicle}
     * @param vehicle {@link Vehicle}
     * @return saved Vehicle
     */
    Vehicle addNewVehicle(Vehicle vehicle);

    /**
     * edit vehicle
     * @param vehicle {@link Vehicle}
     * @return true if success all false if not
     */
    Vehicle editVehicle(Vehicle vehicle);

    /**
     * Get all driver's vehicles
     * @param driverID driverID
     * @return List {@link Vehicle}
     */
    List<Vehicle> getAllDriverVehicleByDriver(String driverID);

    /**
     * Get list driver vehicles by page number
     * @param driverID
     * @return
     */
    List<Vehicle> getPageListVehicleByDriver(String driverID, int pageNumber);

    /**
     * Get page list vehicles
     * @param pageNumber
     * @return
     */
    List<Vehicle> getPageListVehicle(int pageNumber);

    /**
     * Get vehicle detail by id
     * @param vehicleID
     * @return
     */
    Vehicle  getVehicleById(String vehicleID);

    /**
     * Get page list vehicles by type
     * @param typeID
     * @param pageNumber
     * @return
     */
    List<Vehicle> getPageListVehicleByTypeId(int typeID, int pageNumber);
}
