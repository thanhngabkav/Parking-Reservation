package com.atp.webservice.parking_reservation_10.controller;

import com.atp.webservice.parking_reservation_10.entities.Driver;
import com.atp.webservice.parking_reservation_10.entities.Owner;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.DriverCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.mobileServices.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;

    @Autowired
    private DriverCRUDRepository driverCRUDRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Driver>> GetAll() {
        List<Driver> drivers = new ArrayList<Driver>();
        this.driverCRUDRepository.findAll().forEach(drivers::add);
        return new ResponseEntity<List<Driver>>(drivers, HttpStatus.OK);
    }
}
