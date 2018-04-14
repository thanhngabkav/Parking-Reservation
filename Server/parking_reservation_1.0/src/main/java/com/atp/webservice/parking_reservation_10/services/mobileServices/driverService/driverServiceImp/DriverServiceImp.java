package com.atp.webservice.parking_reservation_10.services.mobileServices.driverService.driverServiceImp;

import com.atp.webservice.parking_reservation_10.entities.User;
import com.atp.webservice.parking_reservation_10.entities.sparkPresenter.StationPresenter;
import com.atp.webservice.parking_reservation_10.entities.uitls.StationStatus;
import com.atp.webservice.parking_reservation_10.entities.uitls.UserRole;
import com.atp.webservice.parking_reservation_10.entities.uitls.UserStatus;
import com.atp.webservice.parking_reservation_10.entities.uitls.UserType;
import com.atp.webservice.parking_reservation_10.repository.sparkRepository.StationRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.DriverCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.RoleCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.UserCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.mobileServices.driverService.DriverService;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.Driver;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.Ticket;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DriverServiceImp implements DriverService {

    @Autowired
    private DriverCRUDRepository  driverCRUDRepository;

    @Autowired
    private UserCRUDRepository userCRUDRepository;

    @Autowired
    private RoleCRUDRepository roleCRUDRepository;

    @Override
    public Driver addNewDriver(Driver driver) {
        if(userCRUDRepository.findFirstByPhoneNumber(driver.getPhoneNumber())!=null ||
                userCRUDRepository.findFirstByEmail(driver.getEmail())!=null)//user is existed
            return null;
        else{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            com.atp.webservice.parking_reservation_10.entities.Driver driverEntity = convertToEntity(driver);
            driverEntity.setUserID(UUID.randomUUID().toString());
            driverEntity.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
            driverEntity.setPassword(encoder.encode(driver.getPassword()));
            return convertFromEntity(driverCRUDRepository.save(driverEntity));
        }
    }

    @Override
    public Driver updateDriver(Driver driver) {
        if(!userCRUDRepository.exists(driver.getUserID()))//user not found
            return null;
        else{
            //update drive info
            com.atp.webservice.parking_reservation_10.entities.Driver driverEntity = driverCRUDRepository.findOne(driver.getUserID());
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            driverEntity.setBalance(driver.getBalance());
            driverEntity.setFullName(driver.getDriverName());
            driverEntity.setApplicationID(driver.getApplicationID());
            driverEntity.setPassword(encoder.encode(driver.getPassword()));

            return convertFromEntity(driverCRUDRepository.save(driverEntity));
        }
    }

    @Override
    public Driver getDriverById(String id) {
       return convertFromEntity(driverCRUDRepository.findOne(id));
    }

    @Override
    public Driver getDriverByEmailOrPhoneNumber(String userName) {
        User user = userCRUDRepository.findFirstByEmail(userName);//find by email
        if(user == null)
            user = userCRUDRepository.findFirstByPhoneNumber(userName);//find by user name
        if(user == null)
            return null;
        return convertFromEntity(driverCRUDRepository.findOne(user.getUserID()));
    }

    @Override
    public List<Driver> getAll() {
        List<Driver> driverList = new ArrayList<Driver>();
        for(com.atp.webservice.parking_reservation_10.entities.Driver driver : driverCRUDRepository.findAll()){
            driverList.add(convertFromEntity(driver));
        }

        return driverList;
    }

    @Override
    public List<Driver> getPageList(int pageNumber) {
        List<Driver> driverList = new ArrayList<Driver>();
        PageRequest pageRequest = new PageRequest(pageNumber-1, PAGE_SIZE);
        for(com.atp.webservice.parking_reservation_10.entities.Driver driver : driverCRUDRepository.findAll(pageRequest)){
            driverList.add(convertFromEntity(driver));
        }
        return driverList;
    }


    /**
     * Convert from {@link com.atp.webservice.parking_reservation_10.entities.Driver} to {@link com.atp.webservice.parking_reservation_10.entities.Driver}
     * @param driverEntity {@link com.atp.webservice.parking_reservation_10.entities.Driver}
     * @return Driver
     */
    private Driver convertFromEntity(com.atp.webservice.parking_reservation_10.entities.Driver driverEntity){
        if(driverEntity == null)
            return null;
        Driver driver = new Driver();
        driver.setAddress("No Address");
        driver.setEmail(driverEntity.getEmail());
        driver.setPhoneNumber(driverEntity.getPhoneNumber());
        driver.setUserID(driverEntity.getUserID());
        driver.setDriverName(driverEntity.getFullName());
        driver.setBalance(driverEntity.getBalance());
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        for (com.atp.webservice.parking_reservation_10.entities.Vehicle vehicle : driverEntity.getVehicleList()){
            vehicles.add(new Vehicle().convertFromEntity(vehicle));
        }
        driver.setVehicles(vehicles);
        return  driver;
    }

    /**
     * Convert from {@link Driver } to {@link com.atp.webservice.parking_reservation_10.entities.Driver}
     * @param driverModel
     * @return Driver
     */
    private com.atp.webservice.parking_reservation_10.entities.Driver convertToEntity(Driver driverModel){
        if(driverModel == null)
            return null;
        com.atp.webservice.parking_reservation_10.entities.Driver driverEntity = new com.atp.webservice.parking_reservation_10.entities.Driver();
        driverEntity.setUserType(UserType.DRIVER);
        driverEntity.setRoles(roleCRUDRepository.findRoleByRoleName(UserRole.DRIVER_ROLE));
        driverEntity.setPassword(driverModel.getPassword());
        driverEntity.setStatus(UserStatus.ACTIVE);
        driverEntity.setEmail(driverModel.getEmail());
        driverEntity.setPhoneNumber(driverModel .getPhoneNumber());
        driverEntity.setFullName(driverModel.getDriverName());
        driverEntity.setBalance(driverModel.getBalance());
        driverEntity.setUserID(driverModel.getUserID());
        driverEntity.setBalance(driverModel.getBalance());
        driverEntity.setApplicationID(driverModel.getApplicationID());
        driverEntity.setUserID(driverModel.getUserID());

        return driverEntity;
    }
}
