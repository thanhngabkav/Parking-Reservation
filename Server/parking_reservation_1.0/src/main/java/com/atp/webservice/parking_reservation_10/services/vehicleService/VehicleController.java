package com.atp.webservice.parking_reservation_10.services.vehicleService;



import com.atp.webservice.parking_reservation_10.services.models.VehicleModel;
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
    public ResponseEntity<List<VehicleModel>> getAllDriverVehicles(@PathVariable("id") String driverId){

        return new ResponseEntity<List<VehicleModel>>(vehicleService.getAllDriverVehicleByDriver(driverId), HttpStatus.OK);
    }

    /**
     * Get page list vehicles by type id
     * @param typeID
     * @param pageNumber
     * @return
     */
    @RequestMapping(value = "/type/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<VehicleModel>> getAllVehiclesByType(@PathVariable("id") int typeID,
                                                                   @RequestParam(name = "page") int pageNumber){

        return new ResponseEntity<List<VehicleModel>>(vehicleService.getPageListVehicleByTypeId(typeID, pageNumber), HttpStatus.OK);
    }


    /**
     * Add new {@link com.atp.webservice.parking_reservation_10.entities.Vehicle}
     * @param vehicleModel
     * @param result
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleModel> addNewVehicle(@RequestBody VehicleModel vehicleModel, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<VehicleModel>(HttpStatus.FAILED_DEPENDENCY);
        }
        VehicleModel newVehicleModel = vehicleService.addNewVehicle(vehicleModel);
        if(newVehicleModel == null)
            return new ResponseEntity<VehicleModel>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<VehicleModel>(newVehicleModel, HttpStatus.OK);
    }


    /**
     * Edit new {@link com.atp.webservice.parking_reservation_10.entities.Vehicle}
     * @param vehicleModel
     * @param result
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VehicleModel> updateVehicle(@PathVariable("id") String id, @RequestBody VehicleModel vehicleModel, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<VehicleModel>(HttpStatus.FAILED_DEPENDENCY);
        }
        VehicleModel editVehicleModel = vehicleService.editVehicle(vehicleModel);
        if(editVehicleModel == null)
            return new ResponseEntity<VehicleModel>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<VehicleModel>(editVehicleModel, HttpStatus.OK);
    }

    /**
     * Delete new {@link com.atp.webservice.parking_reservation_10.entities.Vehicle}
     * @param id vehicleId
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteVehicle(@PathVariable("id") String id){
      vehicleService.deleteVehicle(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
