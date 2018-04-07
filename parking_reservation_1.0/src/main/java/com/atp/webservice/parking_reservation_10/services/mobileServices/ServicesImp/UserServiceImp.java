package com.atp.webservice.parking_reservation_10.services.mobileServices.ServicesImp;

import com.atp.webservice.parking_reservation_10.entities.Driver;
import com.atp.webservice.parking_reservation_10.entities.Role;
import com.atp.webservice.parking_reservation_10.entities.Vehicle;
import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;
import com.atp.webservice.parking_reservation_10.entities.uitls.UserRole;
import com.atp.webservice.parking_reservation_10.entities.uitls.UserStatus;
import com.atp.webservice.parking_reservation_10.entities.uitls.UserType;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.DriverCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.RoleCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.UserCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.VehicleCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.mobileServices.UserService;
import com.atp.webservice.parking_reservation_10.services.mobileServices.presenter.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Transactional
public class UserServiceImp implements UserService{

    @Autowired
    private DriverCRUDRepository driverCRUDRepository;

    @Autowired
    private VehicleCRUDRepository vehicleCRUDRepository;

    @Autowired
    private UserCRUDRepository userCRUDRepository;

    @Autowired
    private RoleCRUDRepository roleCRUDRepository;

    @Override
    public User getUserByUserName(String userName) {
        Driver driver = driverCRUDRepository.findDriverByEmailOrPhoneNumber(userName);
        List<Vehicle> vehicleList = vehicleCRUDRepository.findByDriveID(driver.getUserID());
        List<com.atp.webservice.parking_reservation_10.services.mobileServices.presenter.Vehicle>
                listVehiclePresenter = new ArrayList<>();
        for(Vehicle vehicle : vehicleList){
            listVehiclePresenter.add(
                    new com.atp.webservice.parking_reservation_10.services.mobileServices.presenter.Vehicle(vehicle.getID().toString(), vehicle.getName()));
        }

        User user = new User(driver.getUserID().toString(), driver.getPhoneNumber(), driver.getEmail(), "NO Address","", listVehiclePresenter);
        return user;
    }

    @Override
    public User getUserByUserID(String userID) {
        Driver driver = driverCRUDRepository.findOne(UUID.fromString(userID));
        List<Vehicle> vehicleList = vehicleCRUDRepository.findByDriveID(driver.getUserID());
        List<com.atp.webservice.parking_reservation_10.services.mobileServices.presenter.Vehicle>
                listVehiclePresenter = new ArrayList<>();
        for(Vehicle vehicle : vehicleList){
            listVehiclePresenter.add(
                    new com.atp.webservice.parking_reservation_10.services.mobileServices.presenter.Vehicle(vehicle.getID().toString(), vehicle.getName()));
        }
        // Driver does not have address
        User user = new User(driver.getUserID().toString(), driver.getPhoneNumber(), driver.getEmail(), "NO Address","", listVehiclePresenter);
        return user;

    }

    @Override
    public boolean createNewDriver(User user) {

        com.atp.webservice.parking_reservation_10.entities.User userByEmail = userCRUDRepository.findUserByEmailOrPhoneNumber(user.getEmail());
        com.atp.webservice.parking_reservation_10.entities.User userByPhone = userCRUDRepository.findUserByEmailOrPhoneNumber(user.getPhoneNumber());

        if(userByEmail!= null || userByPhone!= null){
            //user is exited!
            return  false;
        }
        //create user
        com.atp.webservice.parking_reservation_10.entities.User m_user = new com.atp.webservice.parking_reservation_10.entities.User();
        m_user.setUserID(DefaultValue.UUID);
        m_user.setPassword(user.getPassword());
        m_user.setPhoneNumber(user.getPhoneNumber());
        m_user.setEmail(user.getEmail());
        m_user.setUserType(UserType.DRIVER);
        m_user.setStatus(UserStatus.ACTIVE);
        List<Role> roles = new ArrayList<Role>();
        roles.add(roleCRUDRepository.findRoleByRoleName(UserRole.DRIVER_ROLE));
        m_user.setRoles(roles);
        userCRUDRepository.save(m_user);

        //create driver account
        Driver driver = new Driver();
        driver.setUserID(m_user.getUserID());
        driver.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        driverCRUDRepository.save(driver);

        return true;
    }
}
