package com.atp.webservice.parking_reservation_10.controller;

import com.atp.webservice.parking_reservation_10.services.mobileServices.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api//drivers")
public class DriverController {

    @Autowired
    private DriverService driverService;



}
