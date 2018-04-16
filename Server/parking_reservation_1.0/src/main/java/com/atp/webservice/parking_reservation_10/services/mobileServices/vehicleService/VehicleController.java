package com.atp.webservice.parking_reservation_10.services.mobileServices.vehicleService;



import com.atp.webservice.parking_reservation_10.services.mobileServices.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    /**
     * Get all driver vehicles
     * @param driverId
     * @return
     */
    @RequestMapping(value = "/driver/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vehicle>> getAllDriverVehicles(@PathVariable("id") String driverId){

        return new ResponseEntity<List<Vehicle>>(vehicleService.getAllDriverVehicleByDriver(driverId), HttpStatus.OK);
    }

    /**
     * Get page list vehicles by type id
     * @param typeID
     * @param pageNumber
     * @return
     */
    @RequestMapping(value = "/type/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Vehicle>> getAllVehiclesByType(@PathVariable("id") int typeID,
                                                              @RequestParam(name = "page") int pageNumber){

        return new ResponseEntity<List<Vehicle>>(vehicleService.getPageListVehicleByTypeId(typeID, pageNumber), HttpStatus.OK);
    }


    /**
     * Add new {@link com.atp.webservice.parking_reservation_10.entities.Vehicle}
     * @param vehicle
     * @param result
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vehicle> addNewVehicle(@RequestBody Vehicle vehicle, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<Vehicle>(HttpStatus.FAILED_DEPENDENCY);
        }
        Vehicle newVehicle = vehicleService.addNewVehicle(vehicle);
        if(newVehicle == null)
            return new ResponseEntity<Vehicle>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Vehicle>(newVehicle, HttpStatus.OK);
    }


    /**
     * Add new {@link com.atp.webservice.parking_reservation_10.entities.Vehicle}
     * @param vehicle
     * @param result
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable("id") String id, @RequestBody Vehicle vehicle, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<Vehicle>(HttpStatus.FAILED_DEPENDENCY);
        }
        Vehicle editVehicle = vehicleService.editVehicle(vehicle);
        if(editVehicle == null)
            return new ResponseEntity<Vehicle>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<Vehicle>(editVehicle, HttpStatus.OK);
    }
}
