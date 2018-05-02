package com.atp.webservice.parking_reservation_10.config.initial;


import com.atp.webservice.parking_reservation_10.entities.*;
import com.atp.webservice.parking_reservation_10.entities.sparkPresenter.StationPresenter;
import com.atp.webservice.parking_reservation_10.entities.uitls.*;
import com.atp.webservice.parking_reservation_10.repository.sparkRepository.SparkHelper;
import com.atp.webservice.parking_reservation_10.repository.sparkRepository.StationRepositoryImp;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.*;
import com.atp.webservice.parking_reservation_10.services.algorithms.KeyHelper;
import com.atp.webservice.parking_reservation_10.services.algorithms.KeypairHelper;
import com.atp.webservice.parking_reservation_10.services.stationService.StationService;
import com.atp.webservice.parking_reservation_10.services.ticketService.TicketConverter;
import com.atp.webservice.parking_reservation_10.services.ticketService.TicketService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import org.apache.log4j.Logger;
import org.apache.spark.storage.StorageLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

/**
 * This class is use for generate sample data
 */

@Component
public class DatabaseInitial {

    @Autowired
    private OwnerCRUDRepository ownerCRUDRepository;

    @Autowired
    private DriverCRUDRepository driverCRUDRepository;

    @Autowired
    private StationCRUDRepository stationCRUDRepository;

    @Autowired
    private VehicleTypeCRUDRepository vehicleTypeCRUDRepository;

    @Autowired
    private VehicleCRUDRepository vehicleCRUDRepository;

    @Autowired
    private TicketTypeCRUDRepository ticketTypeCRUDRepository;

    @Autowired
    private TicketCRUDRepository ticketCRUDRepository;

    @Autowired
    private RoleCRUDRepository roleCRUDRepository;

    @Autowired
    private ServiceCRUDRepository serviceCRUDRepository;

    @Autowired
    private StationVehicleTypeCRUDRepository stationVehicleTypeCRUDRepository;


    @Autowired
    private TicketConverter ticketConverter;


    @Autowired
    private StationService stationService;

    @Autowired
    private TicketService ticketService;


    private static Logger logger = Logger.getLogger(DatabaseInitial.class);

    public void doImport() {


//        logger.info("Importing sample data");
//
//        initRoles();
//
//        initServices();
//
//        try {
//            initUser();
//        } catch (NoSuchAlgorithmException e) {
//            logger.warn("Init Owners fail");
//            e.printStackTrace();
//        }
//        try {
//            initStation();
//        } catch (IOException e) {
//            logger.warn("Init Stations fail");
//            e.printStackTrace();
//        }
//        initVehicleType();
//
//        initStationVehicleType();
//
//        initVehicle();
//
//        initTicketType();
//
//        initTicket();
//
//
//        /**
//         * This code block used to generate more station
//
//        for(int i =1;i<=10;i++){
//            try {
//                initStation();
//            } catch (IOException e) {
//                logger.warn("Init Stations fail");
//                e.printStackTrace();
//            }
//        }*/

        initParkingDataSet();

        logger.info("\n===========================================================\n" +
                "||                    GENERATED DATABASE                  ||\n" +
                "===========================================================");
//        StationLocationModel stationLocationModel = new StationLocationModel();
//        stationLocationModel.setLat(10.76739);
//        stationLocationModel.setLng(106.696872);
//        mapService.getNearByParking(stationLocationModel,5);
    }

    private void initStationVehicleType() {
        logger.info("Init Station's vehicle type");
        List<VehicleType> allVehicleTypes = vehicleTypeCRUDRepository.findAll();
        List<Station> allStations = stationCRUDRepository.findAll();
        int totalSlots = 0;
        int usedSlots = 0;
        int holdingSlots = 0;
        for (Station station : allStations) {
            totalSlots = 0;
            usedSlots = 0;
            holdingSlots = 0;
            for (VehicleType vehicleType : allVehicleTypes) {
                StationVehicleType stationVehicleType = new StationVehicleType();
                stationVehicleType.setVehicleTypeId(vehicleType.getID());
                stationVehicleType.setStationID(station.getID());
                stationVehicleType.setTotalSlots(30);
                stationVehicleType.setHoldingSlots(0);
                stationVehicleType.setUsedSlots(10);
                totalSlots += 30;
                usedSlots += 10;
                holdingSlots += 0;
                stationVehicleTypeCRUDRepository.save(stationVehicleType);
            }
            station.setTotalSlots(totalSlots);
            station.setUsedSlots(usedSlots);
            station.setHoldingSlots(holdingSlots);
            stationCRUDRepository.save(station);
        }
    }

    public void initParkingDataSet() {
        StationRepositoryImp.parkingDataSet = SparkHelper.GetRRDFromTable(TableName.STATION, StationPresenter.class)
                .select("station_id", "application_id", "close_time", "coordinate", "created_date", "holding_slots", "image_link", "name",
                        "open_time", "owner_id", "parking_map_link", "station_id", "status", "total_slots", "used_slots");
        StationRepositoryImp.parkingDataSet.persist(StorageLevel.MEMORY_ONLY());
        StationRepositoryImp.parkingDataSet.show();
    }


    private void initServices() {
        logger.info("Init Services");
        String[] services = {StationServices.DO_XE, StationServices.SUA_XE, StationServices.DO_XANG, StationServices.RUA_XE};
        for (String service : services) {
            serviceCRUDRepository.save(new Service(service));
        }
    }

    private void initRoles() {
        logger.info("Init Roles");
        String[] roles = {UserRole.ADMIN_ROLE, UserRole.DRIVER_ROLE, UserRole.OWNER_ROLE, UserRole.THIRD_PARTY};
        for (String role : roles) {
            roleCRUDRepository.save(new Role(role));
        }
    }

    private void initVehicleType() {
        VehicleType vehicle = new VehicleType();
        vehicle.setName("Xe Đạp");
        vehicleTypeCRUDRepository.save(vehicle);
        //
        VehicleType vehicle1 = new VehicleType();
        vehicle1.setName("Xe Máy");
        vehicleTypeCRUDRepository.save(vehicle1);
        //
        VehicleType vehicle2 = new VehicleType();
        vehicle2.setName("Ô Tô");
        vehicleTypeCRUDRepository.save(vehicle2);
        //
        VehicleType vehicle3 = new VehicleType();
        vehicle3.setName("Xe Tải");
        vehicleTypeCRUDRepository.save(vehicle3);
        //
        VehicleType vehicle4 = new VehicleType();
        vehicle4.setName("Xe Khách");
        vehicleTypeCRUDRepository.save(vehicle4);
    }

    private void initVehicle() {
        logger.info("Init Vehicles");
        List<Driver> drivers = driverCRUDRepository.findAll();
        List<VehicleType> vehicleTypes = vehicleTypeCRUDRepository.findAll();
        for (Driver driver : drivers) {
            for (VehicleType vehicleType : vehicleTypes) {
                byte[] array = new byte[7]; // length is bounded by 7
                new Random().nextBytes(array);
                Faker faker = new Faker();
                //String generatedString = new String(array, Charset.forName("EUC-KR"));
                Vehicle vehicle = new Vehicle();
                vehicle.setID(UUID.randomUUID().toString());
                vehicle.setDriverID(driver.getUserID());
                vehicle.setLicensePlate(faker.number().digits(6));
                vehicle.setVehicleTypeID(vehicleType.getID());
                vehicle.setName("Driver's Vehicle");
                vehicleCRUDRepository.save(vehicle);
            }
        }
    }

    /***
     * Note: Oly generate data for parking service (id =1)
     */
    private void initTicketType() {

        logger.info("Init TicketModel Types");
        List<StationVehicleType> stationVehicleTypes = stationVehicleTypeCRUDRepository.findAll();
        for (StationVehicleType stationVehicleType : stationVehicleTypes) {
            Random random = new Random();
            TicketType ticketType = new TicketType();
            ticketType.setHoldingTime(Time.valueOf(LocalTime.of(0, Math.abs(random.nextInt()) % 5, Math.abs(random.nextInt()) % 60)));
            ticketType.setPrice(Math.abs(random.nextInt()) % 5000 + 2000);
            ticketType.setName("Vé giữ trong ngày");
            ticketType.setStationVehicleTypeID(stationVehicleType.getId());
            ticketType.setServiceID(1);//dich vu gui xe
            ticketTypeCRUDRepository.save(ticketType);
        }
    }

    private void initTicket() {
        logger.info("Init Tickets");
        List<Vehicle> vehicles = vehicleCRUDRepository.findAllByVehicleTypeID(2);//xe may
        List<Station> allStations = stationCRUDRepository.findAll();
        String[] ticketStatus = {TicketStatus.CHECKED, TicketStatus.HOLDING, TicketStatus.EXPRIRRED, TicketStatus.USED};
        int i = 0;
        for (Station station : allStations) {
            for (Vehicle vehicle : vehicles) {
                Owner owner = ownerCRUDRepository.findOne(station.getOwnerID());
                logger.debug("Init Tickets " + i);
                i++;
                List<StationVehicleType> stationVehicleTypes = new ArrayList<StationVehicleType>();
                stationVehicleTypes.add(stationVehicleTypeCRUDRepository.findFirstByStationIDAndAndVehicleTypeId(station.getID(), vehicle.getVehicleTypeID()));
                List<TicketType> ticketTypes = new ArrayList<TicketType>();
                for (StationVehicleType stationVehicleType : stationVehicleTypes) {
                    ticketTypes.addAll(ticketTypeCRUDRepository.findByStationVehicleTypeID(stationVehicleType.getId()));
                }
                Ticket ticket = new Ticket();
                ticket.setID(UUID.randomUUID().toString())
                        .setCheckinTime(Timestamp.valueOf(LocalDateTime.now()));
                if (i % 5 == 0)
                    ticket.setCheckoutTime(Timestamp.valueOf(LocalDateTime.now()));
                else
                    ticket.setCheckoutTime(null);
                ticket.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()))
                        .setDriverID(vehicle.getDriverID());
                ticket.setqRCode("");
                ticket.setStationID(station.getID())
                        .setStatus(ticketStatus[i % 4]);
                StationVehicleType stationVehicleType = stationVehicleTypes.get(0);
                if (ticketStatus[i % 4].equals(TicketStatus.HOLDING)) {
                    station.setHoldingSlots(station.getHoldingSlots() + 1);
                    stationVehicleType.setHoldingSlots(stationVehicleType.getHoldingSlots() + 1);
                    stationCRUDRepository.save(station);
                    stationVehicleTypeCRUDRepository.save(stationVehicleType);
                }
                ticket.setStation(station);
                ticket.setTicketTypes(ticketTypes);
                ticket.setVehicleID(vehicle.getID());
                ticket.setVehicle(vehicle);
                ticket.setPaid(false);
                double totalPrice = 0;
                for (TicketType ticketType : ticketTypes)
                    totalPrice += ticketType.getPrice();
                ticket.setTotalPrice(totalPrice);
                StringBuilder QRCode = new StringBuilder();
               // ObjectMapper objectMapper = new ObjectMapper();
                try {
                    String content = ticketService.createQRCodeContent(ticketConverter.convertFromEntity(ticket));
                    //System.out.println(content);
                    QRCode.append(KeyHelper.encrypt(content, owner.getSecretKey()));
                    //System.out.println(KeyHelper.decrypt(QRCode.toString(),owner.getSecretKey()));
                } catch (Exception e) {
                    logger.error("Gen QR code fail!");
                    e.printStackTrace();
                }
                ticket.setqRCode(QRCode.toString());
                ticketCRUDRepository.save(ticket);
            }
        }

    }

    private void initOwner(User user, int i) throws NoSuchAlgorithmException {
        //Init owner
        // logger.info("Init Owners");
        Owner owner = new Owner();
        owner.setUserID(user.getUserID());
        owner.setAddress("Owner Address");
        owner.setName("Owner " + user.getUserID().toString());
        owner.setUserName("Owner_" + i);
        KeyPair keyPair = KeypairHelper.buildKeyPair();
        owner.setSecretKey(KeyHelper.genSecretKey());
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
            user.setPhoneNumber("0962810884" + i);
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
//            switch (i % 5) {
//                case 1:
//                    user.setStatus(UserStatus.WAITING);
//                    break;
//                case 3:
//                    user.setStatus(UserStatus.SUSPENDED);
//                    break;
//                default:
//                    user.setStatus(UserStatus.ACTIVE);
//                    break;
//            }
            user.setStatus(UserStatus.ACTIVE);
            //userRepository.save(user);

            switch (i % 3) {
                case 1:
                    initOwner(user, i);
                    break;
                case 2:
                    initMobileAppUser(user);
                    break;
            }
        }
    }

    private void initMobileAppUser(User user) {
        Faker faker = new Faker();
        //logger.info("Init Drivers");
        Driver driver = new Driver();
        driver.setUserID(user.getUserID());
        driver.setStatus(user.getStatus());
        driver.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        driver.setFullName(faker.name().fullName());
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
        for (Owner owner : ownerList) {
            if (owner.getStatus().equals(UserStatus.ACTIVE))
                activeOwners.add(owner);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<GenerateClass.RootObject>> typeReference = new TypeReference<List<GenerateClass.RootObject>>() {
        };
        Path dataFilePath = Paths.get(new File("src\\main\\resources\\static\\data").getAbsolutePath() + "\\Stations");
        String json =new String(Files.readAllBytes(dataFilePath), StandardCharsets.UTF_8);
        //H:\Hoc Ky 2 - 2017\Parking Reservation\Source\SourceServer\Server\parking_reservation_1.0\src\main\resources\static\data\Stations
        List<GenerateClass.RootObject> rootObjects = objectMapper.readValue(json, typeReference);
        Time openTime = Time.valueOf(LocalTime.of(8, 30));
        Time closingTime = Time.valueOf(LocalTime.of(22, 30));
        for (int i = 0; i < rootObjects.size(); i++) {

            int ownerIndex = Math.min(i, activeOwners.size() - 1);
            String coordinate = rootObjects.get(i).getGeometry().getLocation().getLat() + ","
                    + rootObjects.get(i).getGeometry().getLocation().getLng();
            Station station = new Station();
            station.setAddress(rootObjects.get(i).getVicinity());
            station.setCoordinate(coordinate);
            station.setName(rootObjects.get(i).getName());
            station.setOwnerID(ownerList.get(ownerIndex).getUserID());
            station.setOpenTime(openTime);
            station.setCloseTime(closingTime);
            station.setTotalSlots(100);
            station.setHoldingSlots(0);
            station.setUsedSlots(23);
            station.setStar(rootObjects.get(i).getRating());
            station.setOwner(ownerList.get(ownerIndex));
            station.setServices(serviceCRUDRepository.findAll());
            station.setStatus(StationStatus.ACTIVE);
            station.setImageLink("https://drive.google.com/uc?id=1hXviep1R7qRjwQYdaTvaO4zMeu-iWqGI;https://drive.google.com/uc?id=1bSEWIVGcFtYJdQLcSWZvagFkQ0EhuFo5;https://drive.google.com/uc?id=1kcbBWQ90ZKI-Utot2hezhIrHVSJcqsem");
            stationCRUDRepository.save(station);
        }
    }
}
