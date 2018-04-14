package com.atp.webservice.parking_reservation_10.services.mobileServices.driverService;

import com.atp.webservice.parking_reservation_10.services.mobileServices.models.Driver;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DriverService {

    static final int PAGE_SIZE = 20;

    /**
     * Add new driver into database
     * @param driver {@link Driver}
     * @return added Driver
     */
    Driver addNewDriver(Driver driver);

    /**
     * Update driver
     * @param driver {@link Driver}
     * @return true if update successful and false if not
     */
    Driver updateDriver(Driver driver);

    /***
     * Get driver by id
     * @param id driverID
     * @return Driver if found or null if not found
     */
    Driver getDriverById(String id);

    /**
     * Get driver by user name (email or phone number)
     * @param userName email or phone number
     * @return Driver if found or null if not found
     */
    Driver getDriverByEmailOrPhoneNumber(String userName);

    /**
     * Get All Drivers
     * @return List {@link Driver}
     */
    List<Driver> getAll();

    /**
     * Get page list Drivers
     * @param pageNumber page number
     * @return List {@link Driver}
     */
    List<Driver> getPageList(int pageNumber);

}
