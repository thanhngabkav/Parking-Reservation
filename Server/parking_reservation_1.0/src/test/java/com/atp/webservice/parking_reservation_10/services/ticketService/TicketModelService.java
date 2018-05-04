package com.atp.webservice.parking_reservation_10.services.ticketService;


import com.atp.webservice.parking_reservation_10.entities.Driver;
import com.atp.webservice.parking_reservation_10.entities.Station;
import com.atp.webservice.parking_reservation_10.entities.Vehicle;
import com.atp.webservice.parking_reservation_10.entities.VehicleType;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketModelService {


    @MockBean
    private TicketCRUDRepository ticketCRUDRepository;

    @MockBean
    private StationCRUDRepository stationCRUDRepository;

    @MockBean
    private DriverCRUDRepository driverCRUDRepository;

    @MockBean
    private TicketTypeCRUDRepository ticketTypeCRUDRepository;

    @MockBean
    private VehicleCRUDRepository vehicleCRUDRepository;

    @MockBean
    private OwnerCRUDRepository ownerCRUDRepository;


    public void sendRequestForReservationTest_1(){

        Station mStation = new Station();
        mStation.setID(11);
        mStation.setName("My StationModel");

        Driver mDriver = new Driver();
        mDriver.setUserID("fake id");

        VehicleType vehicleType = new VehicleType();
        vehicleType.setID(1);
        vehicleType.setName("Xe May");

        Vehicle mVehicle = new Vehicle();
        mVehicle.setName("driver vehicle");
        mVehicle.setLicensePlate("02068");
        mVehicle.setDriverID(mDriver.getUserID());

//        when()
    }
}
