package com.atp.webservice.parking_reservation_10.config.generateData;


import com.atp.webservice.parking_reservation_10.entities.*;
import com.atp.webservice.parking_reservation_10.entities.uitls.*;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.*;
import com.atp.webservice.parking_reservation_10.services.algorithms.KeypairHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * This class is use for generate sample data
 */
@Component
public class DatabaseInitial implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private OwnerCRUDRepository ownerCRUDRepository;

    @Autowired
    private DriverCRUDRepository driverCRUDRepository;

    @Autowired
    private StationCRUDRepository stationCRUDRepository;

    @Autowired
    private VehicleTypeCRUDRepository vehicleTypeCRUDRepository;

    @Autowired
    private  VehicleCRUDRepository vehicleCRUDRepository;

    @Autowired
    private TicketTypeCRUDRepository ticketTypeCRUDRepository;

    @Autowired
    private  TicketCRUDRepository ticketCRUDRepository;

    @Autowired
    private RoleCRUDRepository roleCRUDRepository;

    @Autowired
    private ServiceCRUDRepository serviceCRUDRepository;

    private static Logger logger = Logger.getLogger(DatabaseInitial.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        logger.info("Importing sample data");
        initRoles();

        initServices();

        try {
            initUser();
        } catch (NoSuchAlgorithmException e) {
            logger.warn("Init Owners fail");
            e.printStackTrace();
        }
        try {
            initStation();
        } catch (IOException e) {
            logger.warn("Init Stations fail");
            e.printStackTrace();
        }
        initVehicleType();

        initVehicle();

        initTicketType();

        initTicket();

    }

    private void initServices() {
        logger.info("Init Services");
        String[] services = {StationServices.DO_XE, StationServices.SUA_XE, StationServices.DO_XANG, StationServices.RUA_XE};
        for(String service : services){
            serviceCRUDRepository.save(new Service(service));
        }
    }

    private void initRoles() {
        logger.info("Init Roles");
        String[] roles = {UserRole.ADMIN_ROLE, UserRole.DRIVER_ROLE, UserRole.OWNER_ROLE, UserRole.THIRD_PARTY};
        for(String role : roles){
            roleCRUDRepository.save(new Role(role));
        }
    }

    private void  initVehicleType(){
        VehicleType  vehicle = new VehicleType();
        vehicle.setName("Xe Đạp");
        vehicleTypeCRUDRepository.save(vehicle);
        //
        VehicleType  vehicle1 = new VehicleType();
        vehicle1.setName("Xe Máy");
        vehicleTypeCRUDRepository.save(vehicle1);
        //
        VehicleType  vehicle2 = new VehicleType();
        vehicle2.setName("Ô Tô");
        vehicleTypeCRUDRepository.save(vehicle2);
        //
        VehicleType  vehicle3 = new VehicleType();
        vehicle3.setName("Xe Tải");
        vehicleTypeCRUDRepository.save(vehicle3);
        //
        VehicleType  vehicle4 = new VehicleType();
        vehicle4.setName("Xe Khách");
        vehicleTypeCRUDRepository.save(vehicle4);
    }

    private void  initVehicle(){
        logger.info("Init Vehicles");
        List<Driver> drivers = driverCRUDRepository.findAll();
        List<VehicleType> vehicleTypes = vehicleTypeCRUDRepository.findAll();
        for(Driver driver : drivers){
            for(VehicleType vehicleType : vehicleTypes){
                byte[] array = new byte[7]; // length is bounded by 7
                new Random().nextBytes(array);
                String generatedString = new String(array, Charset.forName("EUC-KR"));
                Vehicle  vehicle = new Vehicle();
                vehicle.setID(UUID.randomUUID().toString());
                vehicle.setDriverID(driver.getUserID());
                vehicle.setLicensePlate(generatedString);
                vehicle.setVehicleTypeID(vehicleType.getID());
                vehicle.setName("Driver Vehicle");
                vehicleCRUDRepository.save(vehicle);
            }
        }
    }

    private void initTicketType(){
        logger.info("Init Ticket Types");
        List<VehicleType> allVehicleTypes = vehicleTypeCRUDRepository.findAll();
        List<Station> allStations = stationCRUDRepository.findAll();
        for (Station station : allStations){
            for(VehicleType vehicleType: allVehicleTypes){
                TicketType ticketType = new TicketType();
                ticketType.setHoldingTime(Time.valueOf(LocalTime.of(1,0,0)));
                ticketType.setPrice(vehicleType.getID()*3 + station.getID()*2 + 2000);
                ticketType.setStation(station);
                ticketType.setName("Type Name or description");
                ticketType.setStationID(station.getID());
                ticketType.setVehicleType(vehicleType);
                ticketType.setVehicleTypeID(vehicleType.getID());

                ticketTypeCRUDRepository.save(ticketType);
            }
        }
    }

    private void initTicket(){

        List<Vehicle> allVehicles = vehicleCRUDRepository.findAll();
        List<Station> allStations = stationCRUDRepository.findAll();
        String[] ticketStatus = {TicketStatus.CHECKED, TicketStatus.HOLDIND, TicketStatus.IN_USE, TicketStatus.USED};
        int i=0;

        for(Vehicle vehicle : allVehicles){
            for(Station station : allStations){
                for(TicketType ticketType : ticketTypeCRUDRepository.findByStationIDAndVehicleTypeID(station.getID(), vehicle.getVehicleTypeID())){
                    i++;
                    Ticket ticket = new Ticket();
                    ticket.setID(UUID.randomUUID().toString())
                            .setCheckinTime(Timestamp.valueOf(LocalDateTime.now()));
                    if(i%5 ==0)
                        ticket.setCheckoutTime(Timestamp.valueOf(LocalDateTime.now()));
                    else
                        ticket.setCheckoutTime(null);
                    ticket.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()))
                            .setDriverID(vehicle.getDriverID());
                    ticket.setqRCode("");
                    ticket.setStationID(station.getID())
                            .setStatus(ticketStatus[i%4]);
                    ticket.setTicketTypeID(ticketType.getID());
                    ticket.setVehicleID(vehicle.getID());
                    logger.info("Init Tickets " + i);
                    ticketCRUDRepository.save(ticket);
                }
            }
        }

    }

    private void initOwner(User user, int i) throws NoSuchAlgorithmException {
        //Init owner
       // logger.info("Init Owners");
        Owner owner = new Owner();
        owner.setUserID(user.getUserID());
        owner.setAddress("Owner Address");
        owner.setName("owner " + user.getUserID().toString());
        owner.setUserName("Owner_"+i);
        KeyPair keyPair = KeypairHelper.buildKeyPair();
        owner.setPublicKey(KeypairHelper.EncodePublicKeyToString(keyPair.getPublic()));
        owner.setPrivateKey(KeypairHelper.EncodePrivateKeyToString(keyPair.getPrivate()));
        owner.setEmail(user.getEmail());
        owner.setPhoneNumber(user.getPhoneNumber());
        owner.setStatus(user.getStatus());
        owner.setPassword(user.getPassword());
        owner.setRoles(roleCRUDRepository.findRoleByRoleName(UserRole.OWNER_ROLE));
        owner.setUserType(UserType.OWNER);
        ownerCRUDRepository.save(owner);
    }

    private void initUser() throws NoSuchAlgorithmException {
        logger.info("Init user");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pass = "123";
        for (int i = 1; i <= 100; i++) {
            StringBuilder email = new StringBuilder();
            email.append("user_").append(i).append("@gmail.com");
            User user = new User();
            user.setUserID(UUID.randomUUID().toString());
            user.setPassword(encoder.encode(pass));
            user.setPhoneNumber("0962810884"+i);
            user.setEmail(email.toString());

            switch (i % 3) {
                case 1:
                    user.setUserType(UserType.OWNER);
                    break;
                case 2:
                    user.setUserType(UserType.DRIVER);
                    break;
                case 0:
                    user.setUserType(UserType.THIRD_PARTY);
                    break;
            }
            switch (i % 5) {
                case 1:
                    user.setStatus(UserStatus.WAITING);
                    break;
                case 3:
                    user.setStatus(UserStatus.SUSPENDED);
                    break;
                default:
                    user.setStatus(UserStatus.ACTIVE);
                    break;
            }
            //userRepository.save(user);

            switch (i % 3) {
                case 1:
                    initOwner(user,i);
                    break;
                case 2:
                    initMobileAppUser(user);
                    break;
            }
        }
    }

    private void initMobileAppUser(User user) {
        //logger.info("Init Drivers");
        Driver driver = new Driver();
        driver.setUserID(user.getUserID());
        driver.setStatus(user.getStatus());
        driver.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        driver.setFullName("Driver " + user.getUserID().toString() );
        driver.setPhoneNumber(user.getPhoneNumber());
        driver.setEmail(user.getEmail());
        driver.setStatus(user.getStatus());
        driver.setPassword(user.getPassword());
        driver.setRoles(roleCRUDRepository.findRoleByRoleName(UserRole.DRIVER_ROLE));
        driver.setUserType(UserType.DRIVER);
        driverCRUDRepository.save(driver);
    }


    private void initStation() throws IOException {
        logger.info("Init Stations");
        List<Owner> ownerList = ownerCRUDRepository.findAll();
        List<Owner> activeOwners = new ArrayList<Owner>();
        for (Owner owner : ownerList){
            if(owner.getStatus().equals(UserStatus.ACTIVE))
                activeOwners.add(owner);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<GenerateClass.RootObject>> typeReference =  new TypeReference<List<GenerateClass.RootObject>>(){};
        String json = "[\n" +
                "      {\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 10.76739,\n" +
                "               \"lng\" : 106.696872\n" +
                "            },\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 10.7687689302915,\n" +
                "                  \"lng\" : 106.6981211302915\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 10.7660709697085,\n" +
                "                  \"lng\" : 106.6954231697085\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/doctor-71.png\",\n" +
                "         \"id\" : \"40d46399d9ff8f27caca3a8d51841f6d1c7a3a36\",\n" +
                "         \"name\" : \"Bệnh viện Tai Mũi Họng Sài Gòn\",\n" +
                "         \"opening_hours\" : {\n" +
                "            \"open_now\" : false,\n" +
                "            \"weekday_text\" : []\n" +
                "         },\n" +
                "         \"photos\" : [\n" +
                "            {\n" +
                "               \"height\" : 2048,\n" +
                "               \"html_attributions\" : [\n" +
                "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/103042126595100596459/photos\\\"\\u003eHoai Anh Hoang\\u003c/a\\u003e\"\n" +
                "               ],\n" +
                "               \"photo_reference\" : \"CmRaAAAA5_5B-oONvM9BbffVBlc6BjugfoGFBpzmNHlLqdtn2wDOslylIKaVeEVdOOpwAd4_swHKmkiUODdK1VaIDN6OIPYYYDNI0qtI0D_0R2WYnbI3cP3EqMEcbbV9q8fNDWOsEhC72BAX43dotccdL7K-S1UqGhSFGwsTh7T1E4mvDFkLAli_tgZJIg\",\n" +
                "               \"width\" : 1536\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJifuj1T8vdTERBohoHDXzPOI\",\n" +
                "         \"rating\" : 3.9,\n" +
                "         \"reference\" : \"CmRSAAAA26MZQmZFiyagLVA_XrZmHZ-ZYM_SK9QcThyaPIZHRamxeQtwxjOoOYWoc7ugkVAgL63gEG-JbLYaB8hlGhKk3afB_nzMzhZAtfdTjEHCuw1fk0epYT8qFqxXC98O_kxUEhCCuxxRhxgLUf5sfpUDeEj3GhR2nWtttz5hdt9B4ZnI8mh041cuHQ\",\n" +
                "         \"scope\" : \"GOOGLE\",\n" +
                "         \"types\" : [ \"hospital\", \"health\", \"point_of_interest\", \"establishment\" ],\n" +
                "         \"vicinity\" : \"1 Trịnh Văn Cấn, Phường Cầu Ông Lãnh\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 10.7974333,\n" +
                "               \"lng\" : 106.6712873\n" +
                "            },\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 10.7989661802915,\n" +
                "                  \"lng\" : 106.6728266802915\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 10.7962682197085,\n" +
                "                  \"lng\" : 106.6701287197085\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/doctor-71.png\",\n" +
                "         \"id\" : \"8d073016e84ac097a5d39ada8c482f1a2a2e24fb\",\n" +
                "         \"name\" : \"Viện Y dược học dân tộc Thành phố Hồ Chí Minh\",\n" +
                "         \"opening_hours\" : {\n" +
                "            \"open_now\" : false,\n" +
                "            \"weekday_text\" : []\n" +
                "         },\n" +
                "         \"photos\" : [\n" +
                "            {\n" +
                "               \"height\" : 540,\n" +
                "               \"html_attributions\" : [\n" +
                "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/110334963104471852803/photos\\\"\\u003eViện Y dược học dân tộc Thành phố Hồ Chí Minh\\u003c/a\\u003e\"\n" +
                "               ],\n" +
                "               \"photo_reference\" : \"CmRaAAAAxBkST3gNmgum2mxzfler7Y2vzvJ-_THUwsT9DVWumzTDmQzphLkkP4BNhG8ZaodyZjeYxR-NTUK4qszEGrAZoiuT6f40nL52JyQSyTz1MHKTIfiyzeq3SFlcA4o2ds9HEhCGJ8hC1upWU5g_QfkseZoCGhSlCuJhUyR6PJfYJ_q07Y_4zafqsA\",\n" +
                "               \"width\" : 960\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJDagOPCkpdTERU_OVnL-RCdY\",\n" +
                "         \"rating\" : 4.3,\n" +
                "         \"reference\" : \"CmRSAAAA8WxBRtM77mYu7_uBmQlBhV5TCCHxY1lL4iTlwOAUo-pKet3EL9TVaZ7U5WadFTcmComX5mK56L-cX9txrZwh0xuGJI_iXpyoaazk9wEfjQlpNBT6svxirr2Hz2SrkIW4EhCnhDtq9IJ7iKq2oyVApdWpGhQthIpExDvw5Q78reGFhmUktB_7Zg\",\n" +
                "         \"scope\" : \"GOOGLE\",\n" +
                "         \"types\" : [ \"hospital\", \"point_of_interest\", \"establishment\" ],\n" +
                "         \"vicinity\" : \"273-275 Nguyễn Văn Trỗi, Phường 10\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 10.7578734,\n" +
                "               \"lng\" : 106.660513\n" +
                "            },\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 10.7594054302915,\n" +
                "                  \"lng\" : 106.6619154302915\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 10.75670746970849,\n" +
                "                  \"lng\" : 106.6592174697085\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/doctor-71.png\",\n" +
                "         \"id\" : \"c84cbc992799e12d1e88fd1c236a78f57ae617ad\",\n" +
                "         \"name\" : \"Bệnh viện Răng Hàm Mặt Trung ương (Thành phố Hồ Chí Minh)\",\n" +
                "         \"opening_hours\" : {\n" +
                "            \"open_now\" : true,\n" +
                "            \"weekday_text\" : []\n" +
                "         },\n" +
                "         \"photos\" : [\n" +
                "            {\n" +
                "               \"height\" : 458,\n" +
                "               \"html_attributions\" : [\n" +
                "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/102371987185140943686/photos\\\"\\u003eTHIEN NAM\\u003c/a\\u003e\"\n" +
                "               ],\n" +
                "               \"photo_reference\" : \"CmRaAAAAhZOXrv5PMcPfNbKi2LVxSpkNQAjSHHv6_LWrpwP0meSp7f0MxH3YL-yKY-JqsHsgVhOKU3kXPomxw97YoKKhOAs-lZrX21uAYZXFPTBuejCZtoZNzbqke1x59w7gZSwvEhD1OucJq8OqYSHCbEfnbMZFGhRBvH2R93xhkiwJz_MdT8E1D_3oIg\",\n" +
                "               \"width\" : 690\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJM8U52vEudTERWPVz1MZDV90\",\n" +
                "         \"rating\" : 3.7,\n" +
                "         \"reference\" : \"CmRSAAAAvCkVmRiW-xPXthR7q6sgyEE7YUhyAy5OUYBDubI5EpGhfvKFh-u7glVQIzq-nmJV0uAxrKT0-XquHCbB9u8OmF4suAPXUzpZqti1qcb3wrURWxVsTvWSodr8qLnW5QWREhC2zr7jnbwrmQLYctozs4JrGhRNOhtn9YzOog0VTdT4EQUiMozugg\",\n" +
                "         \"scope\" : \"GOOGLE\",\n" +
                "         \"types\" : [\n" +
                "            \"beauty_salon\",\n" +
                "            \"dentist\",\n" +
                "            \"hospital\",\n" +
                "            \"doctor\",\n" +
                "            \"health\",\n" +
                "            \"point_of_interest\",\n" +
                "            \"establishment\"\n" +
                "         ],\n" +
                "         \"vicinity\" : \"201A Nguyễn Chí Thanh, Phường 12\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 10.7919967,\n" +
                "               \"lng\" : 106.6771842\n" +
                "            },\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 10.7933141302915,\n" +
                "                  \"lng\" : 106.6785514802915\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 10.7906161697085,\n" +
                "                  \"lng\" : 106.6758535197085\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/doctor-71.png\",\n" +
                "         \"id\" : \"f5b050bfa4f19f20a735c962bd5121ac45b5b864\",\n" +
                "         \"name\" : \"Trạm Y Tế Phường 12 Quận Phú Nhuận\",\n" +
                "         \"place_id\" : \"ChIJHZTCaNUodTER8STAsAr5_x8\",\n" +
                "         \"reference\" : \"CmRRAAAArzXlp8aIf-8BQjd-vjhcJazkSgcx8eKeiX7sOu9oR9l5Yj_Djv-RkbtMqnk5RGKNlrwdB7Qutkg1tEj-BMAgyDzkwZdcXZUq7W-Urgij-pE0Znb-yV5SizTi9ETjyHObEhBqQS0YSy7kpe4rLphNkjhXGhSCKsKW5Ek7BaoSP2ZMn5FJHk3keg\",\n" +
                "         \"scope\" : \"GOOGLE\",\n" +
                "         \"types\" : [ \"hospital\", \"point_of_interest\", \"establishment\" ],\n" +
                "         \"vicinity\" : \"225 Huỳnh Văn Bánh\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 10.7570385,\n" +
                "               \"lng\" : 106.6597305\n" +
                "            },\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 10.7588751802915,\n" +
                "                  \"lng\" : 106.6609910802915\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 10.7561772197085,\n" +
                "                  \"lng\" : 106.6582931197085\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/doctor-71.png\",\n" +
                "         \"id\" : \"59b51d27a7cc4711255a3dbb1201854ff85a2341\",\n" +
                "         \"name\" : \"Bịnh viện Chợ Rẫy\",\n" +
                "         \"opening_hours\" : {\n" +
                "            \"open_now\" : true,\n" +
                "            \"weekday_text\" : []\n" +
                "         },\n" +
                "         \"photos\" : [\n" +
                "            {\n" +
                "               \"height\" : 371,\n" +
                "               \"html_attributions\" : [\n" +
                "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/101229873700523481250/photos\\\"\\u003eMèo Ngốc\\u003c/a\\u003e\"\n" +
                "               ],\n" +
                "               \"photo_reference\" : \"CmRaAAAA7r9AI2qkgLzI6vQnI7BLhffxG_d_uw9utXkio2YGNvdvPzz02enhxtm5_Oo96LcPIvF39CtD6zILGO2ifPEL9xZ3Yl-MuZC-wDwKTl8NHSG3wnO6VrLDqjyJBAaPAeZXEhDynzjUkx4z7OYA18cg2_YNGhRrsNWEJaEHfSud3Gvfx5Mlzai3xw\",\n" +
                "               \"width\" : 660\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJ1_fr7_EudTERWAqRuFPOFJA\",\n" +
                "         \"rating\" : 4,\n" +
                "         \"reference\" : \"CmRSAAAA1stSY8UOf9AhVDWSPH9C8PSMol9t0X9s91j3QqPj8cMAg9itZBDgMyE8ot51ojdr4Ec7cBgVFE5Qu4KrHCvR58nSRsWre2luPVtvpJmAnw_NW81SONif_PZqJZ6mDF4yEhAyOPU0szldrEwCsc0pVwbdGhR_M5POh_9F4vE5FXodnfHGL_Fc1A\",\n" +
                "         \"scope\" : \"GOOGLE\",\n" +
                "         \"types\" : [ \"hospital\", \"point_of_interest\", \"establishment\" ],\n" +
                "         \"vicinity\" : \"BV Chợ Rẫy\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 10.7764079,\n" +
                "               \"lng\" : 106.6866124\n" +
                "            },\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 10.7777678802915,\n" +
                "                  \"lng\" : 106.6883292802915\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 10.7750699197085,\n" +
                "                  \"lng\" : 106.6856313197085\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/doctor-71.png\",\n" +
                "         \"id\" : \"38b096356d673981b67f60bb6e161ed459775d71\",\n" +
                "         \"name\" : \"Bệnh viện Da liễu Tp Hồ Chí Minh\",\n" +
                "         \"opening_hours\" : {\n" +
                "            \"open_now\" : false,\n" +
                "            \"weekday_text\" : []\n" +
                "         },\n" +
                "         \"photos\" : [\n" +
                "            {\n" +
                "               \"height\" : 2592,\n" +
                "               \"html_attributions\" : [\n" +
                "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/100634436699464983999/photos\\\"\\u003ePham Minhthien\\u003c/a\\u003e\"\n" +
                "               ],\n" +
                "               \"photo_reference\" : \"CmRaAAAA4GobAqqBMKTCIaBr6rc-OVRpyVvRXwFKIorUpcR8NcYc2iiYWWVuB5c-ZffS8KVovkIAnnLjg3kn9I0WvMBCnTEB4czA2A_wu05AlrHkTqLevUtNrZ9hsYgnRuE7XmhiEhCp70X4kfYQkV3PJaktyh7hGhRU0Isv7R-nvmpuXRH1ccSGYMOK8A\",\n" +
                "               \"width\" : 4608\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJQzfzHyUvdTERuyCQaRYr4xM\",\n" +
                "         \"rating\" : 3.7,\n" +
                "         \"reference\" : \"CmRRAAAAav0SqwlvJgRvKueWf5HmJq1Qnpa2w5IydzwVE6bnC9A-eSr0wC4_cE9h0C6uBURZ8TGEZzv3mWts44TrebwzSKUceSW9-0HJvCl4omStW-9Ta3jSgDTpXyREuXTf5TMOEhB2XjEDQMmGjd9ZrhxRWPXxGhSwnXEHr1qS8s3jd9eJfA1jv-t-gw\",\n" +
                "         \"scope\" : \"GOOGLE\",\n" +
                "         \"types\" : [ \"hospital\", \"point_of_interest\", \"establishment\" ],\n" +
                "         \"vicinity\" : \"2 Nguyễn Thông, Phường 6\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 10.7815242,\n" +
                "               \"lng\" : 106.7026387\n" +
                "            },\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 10.78498935,\n" +
                "                  \"lng\" : 106.7042142\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 10.77809635,\n" +
                "                  \"lng\" : 106.7004922\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/doctor-71.png\",\n" +
                "         \"id\" : \"fcb17ff4024aa6568a8b2e49e90335ef7604340f\",\n" +
                "         \"name\" : \"Bệnh viện Nhi Đồng 2\",\n" +
                "         \"opening_hours\" : {\n" +
                "            \"open_now\" : true,\n" +
                "            \"weekday_text\" : []\n" +
                "         },\n" +
                "         \"photos\" : [\n" +
                "            {\n" +
                "               \"height\" : 2448,\n" +
                "               \"html_attributions\" : [\n" +
                "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/114508535546419017601/photos\\\"\\u003eTong Do Tien\\u003c/a\\u003e\"\n" +
                "               ],\n" +
                "               \"photo_reference\" : \"CmRaAAAAf65aH1Orb6AefXdKdWCEcNz7JF9ELNubau9j7emK1Ffh9zWoMaj4B-g6px63M5A7HYuQROKboKGa6_ZwHSe3csrXQ-QANkmkruOulCDzKWtQPKp3n7j40ZMXHRVLecmKEhDNu92EwjenqWQ2PYtuIHgXGhSULuQmW-RDr5Dg_0zjm96yFJ0q6A\",\n" +
                "               \"width\" : 3264\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJB0QTqEkvdTERpAKg4ETIOos\",\n" +
                "         \"rating\" : 4,\n" +
                "         \"reference\" : \"CmRSAAAAAAeHapnOTUO4RN5aptmcj8U0OsB8yri7qEGBT3jPAX8Zq4mMFoObLykzJIURrF5f_qJo297nBwDUA1QYVESlJg9AB5KaKSVghK60z0t9Zg7LqVzNOPXDQ84NthzSsZKUEhAIHgwlCaPzy1PWNIL0gZdZGhRYmLHI2V2jjDSfCrXorzdr8rIZ9g\",\n" +
                "         \"scope\" : \"GOOGLE\",\n" +
                "         \"types\" : [ \"hospital\", \"point_of_interest\", \"establishment\" ],\n" +
                "         \"vicinity\" : \"14 Lý Tự Trọng, Bến Nghé\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 10.7843705,\n" +
                "               \"lng\" : 106.6840455\n" +
                "            },\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 10.7857931302915,\n" +
                "                  \"lng\" : 106.6854367802915\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 10.7830951697085,\n" +
                "                  \"lng\" : 106.6827388197085\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/doctor-71.png\",\n" +
                "         \"id\" : \"2a14cc4c173da489a470b9108229d531039aa98b\",\n" +
                "         \"name\" : \"Bệnh viện Tai Mũi Họng Thành Phố Hồ Chí Minh\",\n" +
                "         \"opening_hours\" : {\n" +
                "            \"open_now\" : false,\n" +
                "            \"weekday_text\" : []\n" +
                "         },\n" +
                "         \"photos\" : [\n" +
                "            {\n" +
                "               \"height\" : 2988,\n" +
                "               \"html_attributions\" : [\n" +
                "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/116672363966761297232/photos\\\"\\u003eSơn Nguyễn Minh\\u003c/a\\u003e\"\n" +
                "               ],\n" +
                "               \"photo_reference\" : \"CmRaAAAASC72JhuToPUQTtPMIHMdVTltmFfHtBHYNRTYJa8qt1CFfekbMnf8X2wX6JLCk_EKRUH6NjrdjpN3tKXovkcT_wWbQ5uusoTCL6P7iGIotkZbn9blN5mjNtG3I6kLDSrvEhDO-caL2YWoYaPtqqvzt7wgGhTaruaLQYzobdHEW9wXPQFH7Cyfqg\",\n" +
                "               \"width\" : 5312\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJazoXICwvdTER0OBg-I63dYU\",\n" +
                "         \"rating\" : 3.5,\n" +
                "         \"reference\" : \"CmRSAAAAq51POL-mlj6OKyNL2LlvFjEMkQdqNw1TRMDvGaOwpaTnoi66LP6egMwDmcSXF1j2-cgNO6sJxQs7FL4hSv-28C1mPAzfhqyYKB_qdUSad5CwDQLRWyUxHS3eWHuSm8PREhCIMG3oy6JGVekppKacEaduGhT0vsxEpB-zQ1e0N23gTyhL4XuGfw\",\n" +
                "         \"scope\" : \"GOOGLE\",\n" +
                "         \"types\" : [ \"hospital\", \"doctor\", \"health\", \"point_of_interest\", \"establishment\" ],\n" +
                "         \"vicinity\" : \"155B Trần Quốc Thảo\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 10.7744283,\n" +
                "               \"lng\" : 106.6662819\n" +
                "            },\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 10.7756742,\n" +
                "                  \"lng\" : 106.66934975\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 10.7729278,\n" +
                "                  \"lng\" : 106.66313795\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/doctor-71.png\",\n" +
                "         \"id\" : \"0d73bbeccca9f92a1ad14d76e0bdb47fd2fa4c0c\",\n" +
                "         \"name\" : \"Bệnh viện Nhân Dân 115\",\n" +
                "         \"opening_hours\" : {\n" +
                "            \"open_now\" : true,\n" +
                "            \"weekday_text\" : []\n" +
                "         },\n" +
                "         \"photos\" : [\n" +
                "            {\n" +
                "               \"height\" : 3024,\n" +
                "               \"html_attributions\" : [\n" +
                "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/116981176499772562232/photos\\\"\\u003eVinh Tran quoc\\u003c/a\\u003e\"\n" +
                "               ],\n" +
                "               \"photo_reference\" : \"CmRaAAAAlL9hqhGVQ4T-XPSGr2njRxd35HrXq42JprLq0L_xIu1czyqiC1837KeJDXH5ruxhGWdqwd3UUWj_sPY2dN-Ki6SkG46_mJfGc0LFoiGesGNwzlQZA3Ww3unt7qVzOA3kEhDnoOPH7ZsGdpFtgeYT3ItfGhTN5uYOsj7vjnzHBcu6x9fwDjPg7g\",\n" +
                "               \"width\" : 4032\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJ9wf5nNwudTERGKS75vgAyoc\",\n" +
                "         \"rating\" : 3.8,\n" +
                "         \"reference\" : \"CmRSAAAAI8Tid76kb0Kpbnml52FRjW4Ct6mc12tfu87g_jB-qTIqx9I0Z1Gd2tqpqHNlApU_ml7VLP8DMU-dauLmph1xezLoNdgkbCXdigJ8TUMAQqgYWG0J6p9gTF74UCS5SFVNEhBDE1V7j0YrGoYmqwCASB1cGhQC1PGxT_2qbXVQcrh7HCmSpqsSVw\",\n" +
                "         \"scope\" : \"GOOGLE\",\n" +
                "         \"types\" : [ \"hospital\", \"point_of_interest\", \"establishment\" ],\n" +
                "         \"vicinity\" : \"527 Sư Vạn Hạnh, Phường 12\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 10.8540899,\n" +
                "               \"lng\" : 106.7542368\n" +
                "            },\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 10.8554066302915,\n" +
                "                  \"lng\" : 106.7555916302915\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 10.8527086697085,\n" +
                "                  \"lng\" : 106.7528936697085\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/doctor-71.png\",\n" +
                "         \"id\" : \"b89e1a15534856a8c0333f4778b4e4bddfc51b5e\",\n" +
                "         \"name\" : \"Trạm Y tế phường Linh Tây\",\n" +
                "         \"photos\" : [\n" +
                "            {\n" +
                "               \"height\" : 3096,\n" +
                "               \"html_attributions\" : [\n" +
                "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/116340722397159323815/photos\\\"\\u003eNash Lo\\u003c/a\\u003e\"\n" +
                "               ],\n" +
                "               \"photo_reference\" : \"CmRaAAAAZ7mhiKRj4q6YnZBQuPyuScQEQrHYvBlISqv5xHps5nhHkBF4DubGqFfKTdYJ9MFW0mfI0SwvFX2TOLv3Ssy99E6sTtaXfTa35L4yFJG9_pTjSbSVrJbZsphHWUtkTySpEhBLJzaA-pR4bXcsnctDDBWyGhRbbfX5Yfp9gXJHw0B26f1bs9g9GA\",\n" +
                "               \"width\" : 4128\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJ62HhYJYndTER5bCra_PgFHI\",\n" +
                "         \"rating\" : 4.2,\n" +
                "         \"reference\" : \"CmRRAAAAjgkX89Wpt92cExt5rJdpawbcKLvxqcFZQO7RGb1S2397SBpo8GY9XXYL-fhW7HQwS2jZbtyjaUu34DwyTdBec2HUAHr8_6OCzkhFgMCPBLYtZAF0XvGqXa_jdGBrTSnDEhCgZDQdd48mHiK9sBokc5OuGhQfpL6ojyeJypSSKSs_5GKm2QmxBg\",\n" +
                "         \"scope\" : \"GOOGLE\",\n" +
                "         \"types\" : [ \"hospital\", \"point_of_interest\", \"establishment\" ],\n" +
                "         \"vicinity\" : \"23 Đường Nguyễn Văn Lịch\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 10.7658858,\n" +
                "               \"lng\" : 106.693319\n" +
                "            },\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 10.7672063802915,\n" +
                "                  \"lng\" : 106.6946995802915\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 10.7645084197085,\n" +
                "                  \"lng\" : 106.6920016197085\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png\",\n" +
                "         \"id\" : \"b7475a35c28c36c1fdef4e51f010cb7dbaa7f40c\",\n" +
                "         \"name\" : \"Thẩm mỹ viện AVIDA\",\n" +
                "         \"opening_hours\" : {\n" +
                "            \"open_now\" : false,\n" +
                "            \"weekday_text\" : []\n" +
                "         },\n" +
                "         \"photos\" : [\n" +
                "            {\n" +
                "               \"height\" : 675,\n" +
                "               \"html_attributions\" : [\n" +
                "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/105844531627092910810/photos\\\"\\u003eThẩm mỹ viện AVIDA\\u003c/a\\u003e\"\n" +
                "               ],\n" +
                "               \"photo_reference\" : \"CmRaAAAAphEWsmXM192mWISSVW0TbzTXXBrEJ5kfUm2b_x0Bdag-RkZSXJFSgJPqo1EscX53R1NecNV8mAlKUG8h4X4cjys3J8oeaD2yd_KyVx68OASi3-YDJUcv_D6tnkEpT0k9EhARDpaQdh8krtLlRQjBpZkIGhQxqzLHj1Cqoqe7pO0m1DzCGIpmXw\",\n" +
                "               \"width\" : 1193\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJRxbTcRYvdTEREbQvymK5i9A\",\n" +
                "         \"rating\" : 4.4,\n" +
                "         \"reference\" : \"CmRSAAAAc8MUqUR1Sut8LeL_d8Y3iM75IpMU8FxTN2vyjC3KQRqZL2whBwCXvdD1urXAT5sk-HBLuOg7P9RS6kRxAl-RuVd8UNUC7kY99ZCGXHru77BlJOeBwj-3bTYa9Ea1IL5iEhByqdf-BnnufBRV1uSrpYn9GhRR8qHQVbn14WzU6hXLjYAJZiZx9g\",\n" +
                "         \"scope\" : \"GOOGLE\",\n" +
                "         \"types\" : [\n" +
                "            \"beauty_salon\",\n" +
                "            \"hospital\",\n" +
                "            \"doctor\",\n" +
                "            \"store\",\n" +
                "            \"health\",\n" +
                "            \"point_of_interest\",\n" +
                "            \"establishment\"\n" +
                "         ],\n" +
                "         \"vicinity\" : \"106 Đường Trần Hưng Đạo, Phường Phạm Ngũ Lão\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 10.7680338,\n" +
                "               \"lng\" : 106.669407\n" +
                "            },\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 10.76997855,\n" +
                "                  \"lng\" : 106.6721025\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 10.76726435,\n" +
                "                  \"lng\" : 106.6685085\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/doctor-71.png\",\n" +
                "         \"id\" : \"9e403a154879185151a101dd7f36d72c16f01adb\",\n" +
                "         \"name\" : \"Bệnh viện Nhi Đồng 1\",\n" +
                "         \"opening_hours\" : {\n" +
                "            \"open_now\" : false,\n" +
                "            \"weekday_text\" : []\n" +
                "         },\n" +
                "         \"photos\" : [\n" +
                "            {\n" +
                "               \"height\" : 2988,\n" +
                "               \"html_attributions\" : [\n" +
                "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/101894365731879551986/photos\\\"\\u003eBao Nguyen Nguyen Tran\\u003c/a\\u003e\"\n" +
                "               ],\n" +
                "               \"photo_reference\" : \"CmRaAAAA0gdVM9afwVv0m-_GwpB1cCHun2_QfilT8iaf5yWFM-MEpBfHSwvcSE8at2LNAcPFPIG0J19GdAUgsadXORfgDBzG0uoFiQSIo4Ap-LaKOxmluA-S0gTwdSwB-uxpTYs_EhCpGz66DsqDIg4MHbSUKAfUGhT_p5_HVn_GN2Y9hystbiyaff0WaQ\",\n" +
                "               \"width\" : 5312\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJ8RrVGt4udTER6IGFV0rDIAU\",\n" +
                "         \"rating\" : 4,\n" +
                "         \"reference\" : \"CmRRAAAAMFzy-AYTiA9ecwyFvrEor9j1rz_hiDYJIkksuT3bSrgT0xPP-0oIU0wb8yh1rWW3Vs7U1ofzzRvNsuYkmWreCy9o0G6O6UoWpO6QoJB1_K-jIAXeHOr3pumq0IMYu-G1EhAgKSaWx2LpoG4rzywTa7URGhSdo9tq7PwNZ0iS4Vh9DuyKK70RPA\",\n" +
                "         \"scope\" : \"GOOGLE\",\n" +
                "         \"types\" : [ \"hospital\", \"point_of_interest\", \"establishment\" ],\n" +
                "         \"vicinity\" : \"341 Sư Vạn Hạnh, Phường 10\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 10.7635921,\n" +
                "               \"lng\" : 106.6918415\n" +
                "            },\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 10.7651708302915,\n" +
                "                  \"lng\" : 106.6931039302915\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 10.7624728697085,\n" +
                "                  \"lng\" : 106.6904059697085\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/doctor-71.png\",\n" +
                "         \"id\" : \"cafec064c2fc05f29218c2bca26e7d0a046ca62d\",\n" +
                "         \"name\" : \"Bệnh viện Răng Hàm Mặt Tp. HCM\",\n" +
                "         \"opening_hours\" : {\n" +
                "            \"open_now\" : true,\n" +
                "            \"weekday_text\" : []\n" +
                "         },\n" +
                "         \"photos\" : [\n" +
                "            {\n" +
                "               \"height\" : 1150,\n" +
                "               \"html_attributions\" : [\n" +
                "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/101493865766707585554/photos\\\"\\u003eBệnh viện Răng Hàm Mặt Tp. HCM\\u003c/a\\u003e\"\n" +
                "               ],\n" +
                "               \"photo_reference\" : \"CmRaAAAAlMUOzW1TD9d5tsO8INZap2xvxaAGQdZh-Yn0RgMxkIF0OOLhISxE3YmV0Q1_wETeb-XUg0Mr5KKcFUSRaH7iSfB_juM6m-IWkNC_aD0OLxaxyxaJ8JoawKibEgj2kt9dEhCYOIHBLslQQsitS-cME8kjGhTN2M_iHojhWAflep1Q74noPxcKpA\",\n" +
                "               \"width\" : 2048\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJe1zY5xYvdTERCu7JEruNQTw\",\n" +
                "         \"rating\" : 3.8,\n" +
                "         \"reference\" : \"CmRRAAAAN5X4PPuC3k5YIOaIdOSA3imDSv9lZBrqxdlZg2QB7h8XTIWEkwOHrHhT9ZvGuJPs5Ggp2aYC0D-Bw8jWp6XzgVGaxRHPCsJ1lSMwV2WMS_us3p82zVVOPZVshSDG4J3PEhDydXuDVDbOGEpx0eT8na5DGhSoZ8dzFEgSKDZt5mf3FyNRqSnjsQ\",\n" +
                "         \"scope\" : \"GOOGLE\",\n" +
                "         \"types\" : [ \"hospital\", \"point_of_interest\", \"establishment\" ],\n" +
                "         \"vicinity\" : \"263-265 Đường Trần Hưng Đạo, Phường Cô Giang\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 10.7915602,\n" +
                "               \"lng\" : 106.653414\n" +
                "            },\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 10.7929091802915,\n" +
                "                  \"lng\" : 106.6547629802915\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 10.7902112197085,\n" +
                "                  \"lng\" : 106.6520650197085\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/doctor-71.png\",\n" +
                "         \"id\" : \"b2005ba2c3ef6976586f921c38876e3f755ef3ad\",\n" +
                "         \"name\" : \"Bệnh viện Thống Nhất\",\n" +
                "         \"opening_hours\" : {\n" +
                "            \"open_now\" : true,\n" +
                "            \"weekday_text\" : []\n" +
                "         },\n" +
                "         \"photos\" : [\n" +
                "            {\n" +
                "               \"height\" : 426,\n" +
                "               \"html_attributions\" : [\n" +
                "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/117907485819381298008/photos\\\"\\u003eBệnh viện Thống Nhất\\u003c/a\\u003e\"\n" +
                "               ],\n" +
                "               \"photo_reference\" : \"CmRaAAAAUNHHcnsJrKZOxvnSdMSK-rz0zp0-gw_EjWFKWh_C88hpe6AqgoFLopcI3_8fMGeEJTLbHZcuSu9zw1F_6ZvysaYFjcr3G5K-2WHcX0dQPwOEMu7kpAbEaII3bFrCFGWoEhClTR68LJwKpOkuFYc0sbr2GhT77pI_BorxRDOw3t_LY1grrDjqJA\",\n" +
                "               \"width\" : 640\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJKbEmq8oudTERHKoGJK1mPmo\",\n" +
                "         \"rating\" : 3.7,\n" +
                "         \"reference\" : \"CmRRAAAAnx7g4WmpFKrRw4PB3x7OGoORndeJSc02bcABafbP_WO7GFuEE2otg_6CFMFhyMESqxUvVro3vxBhoETN0MX7AVriVETaJ1BBbeJR7_GdJAhVhGDzFLPpvkq8pt4-V7qTEhAC0TWCF9dnFbwvkDnG3xAPGhS86M0KLTVmGiqOR_78NcZHn1KoSQ\",\n" +
                "         \"scope\" : \"GOOGLE\",\n" +
                "         \"types\" : [ \"hospital\", \"point_of_interest\", \"establishment\" ],\n" +
                "         \"vicinity\" : \"1 Lý Thường Kiệt, Phường 7\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 10.761512,\n" +
                "               \"lng\" : 106.6607\n" +
                "            },\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 10.7628550302915,\n" +
                "                  \"lng\" : 106.6620260802915\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 10.7601570697085,\n" +
                "                  \"lng\" : 106.6593281197085\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png\",\n" +
                "         \"id\" : \"2aea7a302a85188aa3f109d28cd33a2bd46dc2a3\",\n" +
                "         \"name\" : \"Nha Khoa Thẩm Mỹ Châu Á\",\n" +
                "         \"opening_hours\" : {\n" +
                "            \"open_now\" : false,\n" +
                "            \"weekday_text\" : []\n" +
                "         },\n" +
                "         \"photos\" : [\n" +
                "            {\n" +
                "               \"height\" : 3000,\n" +
                "               \"html_attributions\" : [\n" +
                "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/117919717628466169319/photos\\\"\\u003eCuc Bui\\u003c/a\\u003e\"\n" +
                "               ],\n" +
                "               \"photo_reference\" : \"CmRaAAAAURDupLqKn87NJesuuVt1n27kqn8PpeTrCAXYieJwyro80XxkOASfSYt9siTaUHhWBzyMxqJT7FfHWDy7sYM5qZuD6O7Qx0kNLl-s23ZH_Ua8vGxlH1xLEn9r1ohDUK6pEhDtuy3SmITRI6GSrxfMzaoVGhRC89OPHkyGXttKi1h-TtvfwT4zlg\",\n" +
                "               \"width\" : 4000\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJV8bzV-4udTERYIvTe8C-aBg\",\n" +
                "         \"rating\" : 4.9,\n" +
                "         \"reference\" : \"CmRRAAAAIJ12JIFi6SaTHw2rff9q0AYrD4VZa0foaTNxpqn8QxS0c2fLpPGTI0WP-lKo5qXf_sKBxxKOADXkeWlucgludSOCAwixtXek5ePwPDEEN0eFjDS4BJvGd9kittVEKQ7xEhAS7VVgaorwC4lwJwB8gqlRGhQydMaXuPYetawd9REtpLAmp_ogvw\",\n" +
                "         \"scope\" : \"GOOGLE\",\n" +
                "         \"types\" : [ \"dentist\", \"hospital\", \"health\", \"point_of_interest\", \"establishment\" ],\n" +
                "         \"vicinity\" : \"116 Lý Thường Kiệt, Phường 7\"\n" +
                "      },\n" +
                "      {\n" +
                "         \"geometry\" : {\n" +
                "            \"location\" : {\n" +
                "               \"lat\" : 10.757373,\n" +
                "               \"lng\" : 106.67815\n" +
                "            },\n" +
                "            \"viewport\" : {\n" +
                "               \"northeast\" : {\n" +
                "                  \"lat\" : 10.7587219802915,\n" +
                "                  \"lng\" : 106.6794989802915\n" +
                "               },\n" +
                "               \"southwest\" : {\n" +
                "                  \"lat\" : 10.7560240197085,\n" +
                "                  \"lng\" : 106.6768010197085\n" +
                "               }\n" +
                "            }\n" +
                "         },\n" +
                "         \"icon\" : \"https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png\",\n" +
                "         \"id\" : \"bdd6e9815ad4e7d00ca29477ae94bbca613f1f13\",\n" +
                "         \"name\" : \"PHÒNG KHÁM ĐA KHOA MỸ TÙNG\",\n" +
                "         \"opening_hours\" : {\n" +
                "            \"open_now\" : false,\n" +
                "            \"weekday_text\" : []\n" +
                "         },\n" +
                "         \"photos\" : [\n" +
                "            {\n" +
                "               \"height\" : 2048,\n" +
                "               \"html_attributions\" : [\n" +
                "                  \"\\u003ca href=\\\"https://maps.google.com/maps/contrib/115008925487294541423/photos\\\"\\u003ePHÒNG KHÁM ĐA KHOA MỸ TÙNG\\u003c/a\\u003e\"\n" +
                "               ],\n" +
                "               \"photo_reference\" : \"CmRaAAAAhx4QKpT3Zthe0J2OHKnIhTfHOY4Cwvcl3lHzrSYZNPPAmYvAPZmSJz0TGm_8S7uIddKbutR03j5ksGYguetvOqheHNRkVj03fQEJikIPgh_ZzkLzHF8GiVJl3a3RhBpQEhDjft-MWoPimudwt_a4ITyDGhQ1yXAWHmAB0-urWW3_gNKhrNuzNw\",\n" +
                "               \"width\" : 1467\n" +
                "            }\n" +
                "         ],\n" +
                "         \"place_id\" : \"ChIJ3aPRNB0vdTERpEGDBVVEBAY\",\n" +
                "         \"rating\" : 5,\n" +
                "         \"reference\" : \"CmRRAAAACK5kJ65iuLuDFBkaXxTcMc6GSMiuQZn1Qy7dsbL3ivUXY8dEGbbTiKg_rkqMQ_tHJt27VlTeaXe09Sa9dA-x91jASCRt5gv5und_6ya4Pr7Pq-_-soryGtq0l7nAvsZ5EhBEZ1bPj4Nl68MnYUCJeS-IGhRhu9gH_fwZi7NftLuYtMj9BdhwXA\",\n" +
                "         \"scope\" : \"GOOGLE\",\n" +
                "         \"types\" : [ \"hospital\", \"doctor\", \"health\", \"point_of_interest\", \"establishment\" ],\n" +
                "         \"vicinity\" : \"142, LÊ HỒNG PHONG, P3, 5\"\n" +
                "      }\n" +
                "]\n";

        List<GenerateClass.RootObject> rootObjects = objectMapper.readValue(json, typeReference);
        Time openTime = Time.valueOf(LocalTime.of(8,30));
        Time closingTime  = Time.valueOf(LocalTime.of(22,30));
        for (int i =0;i<rootObjects.size();i++){

            int ownerIndex =  Math.min(i,activeOwners.size()-1);
            String coordinate = rootObjects.get(i).getGeometry().getLocation().getLat()+","
                                + rootObjects.get(i).getGeometry().getLocation().getLng();
            Station station = new Station();
            station.setAddress("Address");
            station.setCoordinate(coordinate);
            station.setName(rootObjects.get(i).getName());
            station.setOwnerID(ownerList.get(ownerIndex).getUserID());
            station.setOpenTime(openTime);
            station.setCloseTime(closingTime);
            station.setTotalSlots(100);
            station.setUsedSlots(23);
            station.setStar(3);
            station.setKeyPair(ownerList.get(ownerIndex).getPrivateKey()+"," + ownerList.get(ownerIndex).getPublicKey());
            station.setOwner(ownerList.get(ownerIndex));
            station.setServices(serviceCRUDRepository.findAll());
            stationCRUDRepository.save(station);
        }
    }
}
