package com.atp.webservice.parking_reservation_10.services.mobileServices.driverService;

import com.atp.webservice.parking_reservation_10.services.mobileServices.models.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    /**
     * Get all {@link Driver}
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Driver>> getAll() {
        List<Driver> drivers = driverService.getAll();
        return new ResponseEntity<List<Driver>>(drivers, HttpStatus.OK);
    }

    /**
     * Get Page list {@link Driver}
     * @param pageNumber
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Driver>> getPageList(@RequestParam("page") int pageNumber) {
        List<Driver> drivers = driverService.getPageList(pageNumber);
        return new ResponseEntity<List<Driver>>(drivers, HttpStatus.OK);
    }


    /**
     * Update {@link Driver}
     * @param driver {@link Driver}
     * @param result {@link BindingResult}
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Driver> addNewDriver(@RequestBody Driver driver, BindingResult result){
        if(result.hasErrors())
            return new ResponseEntity<Driver>(HttpStatus.NOT_ACCEPTABLE);
        Driver updatedDriver = driverService.addNewDriver(driver);
        if(updatedDriver == null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<Driver>(driver,HttpStatus.CREATED);
    }

    /**
     * Update {@link Driver}
     * @param driver {@link Driver}
     * @param result {@link BindingResult}
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Driver> updateDriver(@PathVariable("id") String userID, @RequestBody Driver driver, BindingResult result){
        if(result.hasErrors())
            return new ResponseEntity<Driver>(HttpStatus.NOT_ACCEPTABLE);
        Driver updatedDriver = driverService.updateDriver(driver);
        if(updatedDriver == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Driver>(driver,HttpStatus.OK);
    }

    /**
     * Get driver by id
     * @param driverID
     * @return Driver
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Driver> getById(@PathVariable(name = "id") String driverID){
        Driver driver = driverService.getDriverById(driverID);
        return new ResponseEntity<Driver>(driver,HttpStatus.OK);
    }

    /**
     * Find driver by phone number or email
     * @param emailOrPhone
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Driver> getByUserName(@RequestParam("userName") String emailOrPhone){
        Driver driver = driverService.getDriverByEmailOrPhoneNumber(emailOrPhone);
        return new ResponseEntity<Driver>(driver,HttpStatus.OK);
    }


}
