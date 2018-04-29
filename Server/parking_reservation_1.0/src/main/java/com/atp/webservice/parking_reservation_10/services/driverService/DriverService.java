package com.atp.webservice.parking_reservation_10.services.driverService;

import com.atp.webservice.parking_reservation_10.services.models.DriverModel;

import java.util.List;

public interface DriverService {


    /**
     * Add new driverModel into database
     * @param driverModel {@link DriverModel}
     * @return added DriverModel
     */
    DriverModel addNewDriver(DriverModel driverModel);

    /**
     * Update driverModel
     * @param driverModel {@link DriverModel}
     * @return true if update successful and false if not
     */
    DriverModel updateDriver(DriverModel driverModel);

    /***
     * Get driver by id
     * @param id driverID
     * @return DriverModel if found or null if not found
     */
    DriverModel getDriverById(String id);

    /**
     * Get driver by user name (email or phone number)
     * @param userName email or phone number
     * @return DriverModel if found or null if not found
     */
    DriverModel getDriverByEmailOrPhoneNumber(String userName);

    /**
     * Get All Drivers
     * @return List {@link DriverModel}
     */
    List<DriverModel> getAll();

    /**
     * Get page list Drivers
     * @param pageNumber page number
     * @return List {@link DriverModel}
     */
    List<DriverModel> getPageList(int pageNumber);

}
