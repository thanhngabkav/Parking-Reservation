package com.atp.webservice.parking_reservation_10.config;


import com.atp.webservice.parking_reservation_10.entities.Driver;
import com.atp.webservice.parking_reservation_10.entities.Owner;
import com.atp.webservice.parking_reservation_10.entities.Station;
import com.atp.webservice.parking_reservation_10.entities.User;
import com.atp.webservice.parking_reservation_10.entities.uitls.UserStatus;
import com.atp.webservice.parking_reservation_10.entities.uitls.UserType;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.DriverCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.OwnerCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.UserCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.algorithms.KeypairHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 */
@Component
public class DatabaseInitial implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserCRUDRepository userRepository;

    @Autowired
    private OwnerCRUDRepository ownerCRUDRepository;

    @Autowired
    private DriverCRUDRepository driverCRUDRepository;

    @Autowired
    private StationCRUDRepository stationCRUDRepository;

    private static Logger logger = Logger.getLogger(DatabaseInitial.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        logger.info("Importing sample data");
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

    }

    private void initOwner(User user, int i) throws NoSuchAlgorithmException {
        //Init owner
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
        ownerCRUDRepository.save(owner);
    }

    private void initUser() throws NoSuchAlgorithmException {
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
        Driver driver = new Driver();
        driver.setUserID(user.getUserID());
        driver.setStatus(user.getStatus());
        driver.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        driver.setFullName("Driver " + user.getUserID().toString() );
        driver.setPhoneNumber(user.getPhoneNumber());
        driver.setEmail(user.getEmail());
        driver.setStatus(user.getStatus());
        driver.setPassword(user.getPassword());
        driverCRUDRepository.save(driver);
    }


    private void initStation() throws IOException {
        List<Owner> ownerList = ownerCRUDRepository.findAll();
        List<Owner> activeOwners = new ArrayList<Owner>();
        for (Owner owner : ownerList){
            if(owner.getStatus().equals(UserStatus.ACTIVE))
                activeOwners.add(owner);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<GenerateClass.RootObject>> typeReference =  new TypeReference<List<GenerateClass.RootObject>>(){};
        List<GenerateClass.RootObject> rootObjects = objectMapper.readValue(new File("./jsonData.txt"), typeReference);
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
            station.setLevel(3);
            station.setKeyPair(ownerList.get(ownerIndex).getPrivateKey()+"," + ownerList.get(ownerIndex).getPublicKey());
            station.setOwner(ownerList.get(ownerIndex));
            stationCRUDRepository.save(station);
        }

    }
}
