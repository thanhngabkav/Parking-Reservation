package com.atp.webservice.parking_reservation_10.services.driverService;

import com.atp.webservice.parking_reservation_10.services.models.DriverModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    /**
     * Get all {@link DriverModel}
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DriverModel>> getAll() {
        List<DriverModel> driverModels = driverService.getAll();
        return new ResponseEntity<List<DriverModel>>(driverModels, HttpStatus.OK);
    }

    /**
     * Get Page list {@link DriverModel}
     * @param pageNumber
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DriverModel>> getPageList(@RequestParam("page") int pageNumber) {
        List<DriverModel> driverModels = driverService.getPageList(pageNumber);
        return new ResponseEntity<List<DriverModel>>(driverModels, HttpStatus.OK);
    }


    /**
     * Update {@link DriverModel}
     * @param driverModel {@link DriverModel}
     * @param result {@link BindingResult}
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DriverModel> addNewDriver(@RequestBody DriverModel driverModel, BindingResult result){
        if(result.hasErrors())
            return new ResponseEntity<DriverModel>(HttpStatus.NOT_ACCEPTABLE);
        DriverModel newDriverModel = driverService.addNewDriver(driverModel);
        if(newDriverModel == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<DriverModel>(newDriverModel,HttpStatus.CREATED);
    }

    /**
     * Update {@link DriverModel}
     * @param driverModel {@link DriverModel}
     * @param result {@link BindingResult}
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DriverModel> updateDriver(@PathVariable("id") String userID, @RequestBody DriverModel driverModel, BindingResult result){
        if(result.hasErrors())
            return new ResponseEntity<DriverModel>(HttpStatus.NOT_ACCEPTABLE);
        DriverModel updatedDriverModel = driverService.updateDriver(driverModel);
        if(updatedDriverModel == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<DriverModel>(updatedDriverModel,HttpStatus.OK);
    }

    /**
     * Get driver by id
     * @param driverID
     * @return DriverModel
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DriverModel> getById(@PathVariable(name = "id") String driverID){
        DriverModel driverModel = driverService.getDriverById(driverID);
        return new ResponseEntity<DriverModel>(driverModel,HttpStatus.OK);
    }

    /**
     * Find driver by phone number or email
     * @param emailOrPhone
     * @return
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DriverModel> getByUserName(@RequestParam("userName") String emailOrPhone){
        DriverModel driverModel = driverService.getDriverByEmailOrPhoneNumber(emailOrPhone);
        return new ResponseEntity<DriverModel>(driverModel,HttpStatus.OK);
    }


}
