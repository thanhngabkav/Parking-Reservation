package com.atp.webservice.parking_reservation_10.webservice;


import com.atp.webservice.parking_reservation_10.entities.ParkingOwner;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.ParkingCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.ParkingOwnerCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/ParkingOwner")
public class ParkingOwnerService {

    @Autowired
    private ParkingOwnerCRUDRepository parkingOwnerRepository;


    /**
     * Get All Parking Owner
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ParkingOwner>> GetAll() {
        List<ParkingOwner> parkingOwnerList = new ArrayList<ParkingOwner>();
        this.parkingOwnerRepository.findAll().forEach(parkingOwnerList::add);
        return new ResponseEntity<List<ParkingOwner>>(parkingOwnerList, HttpStatus.OK);
    }

    /**
     * Get ParkingOwner {@link ParkingOwner} by ID
     *
     * @param ownerID owner ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParkingOwner> GetOwnerByID(@PathVariable("id") int ownerID) {
        ParkingOwner owner = this.parkingOwnerRepository.findOne(ownerID);
        return new ResponseEntity<ParkingOwner>(owner, HttpStatus.OK);

    }
}
