package com.atp.webservice.parking_reservation_10.controller;


import com.atp.webservice.parking_reservation_10.entities.Owner;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.OwnerCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/owners")
public class OwnerController {

    @Autowired
    private OwnerCRUDRepository parkingOwnerRepository;


    /**
     * Get All Station Owner
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Owner>> GetAll() {
        List<Owner> ownerList = new ArrayList<Owner>();
        this.parkingOwnerRepository.findAll().forEach(ownerList::add);
        return new ResponseEntity<List<Owner>>(ownerList, HttpStatus.OK);
    }

    /**
     * Get Owner {@link Owner} by ID
     *
     * @param ownerID owner ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Owner> GetOwnerByID(@PathVariable("id") int ownerID) {
        Owner owner = this.parkingOwnerRepository.findOne(ownerID);
        return new ResponseEntity<Owner>(owner, HttpStatus.OK);

    }
}
