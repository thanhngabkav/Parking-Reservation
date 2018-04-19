package com.atp.webservice.parking_reservation_10.services.mobileServices.driverService.driverServiceImp;

import com.atp.webservice.parking_reservation_10.entities.User;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.DriverCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.RoleCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.UserCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.VehicleCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.mobileServices.driverService.DriverConverter;
import com.atp.webservice.parking_reservation_10.services.mobileServices.driverService.DriverService;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.DriverModel;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private VehicleCRUDRepository vehicleCRUDRepository;

    @Autowired
    private DriverConverter driverConverter;

    @Override
    public DriverModel addNewDriver(DriverModel driverModel) {
        if(userCRUDRepository.findFirstByPhoneNumber(driverModel.getPhoneNumber())!= null ||
                userCRUDRepository.findFirstByEmail(driverModel.getEmail())!= null)//user is existed
            return null;
        else{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            com.atp.webservice.parking_reservation_10.entities.Driver driverEntity = driverConverter.convertToEntity(driverModel);
            driverEntity.setUserID(UUID.randomUUID().toString());
            driverEntity.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
            driverEntity.setPassword(encoder.encode(driverModel.getPassword()));
            return driverConverter.convertFromEntity(driverCRUDRepository.save(driverEntity));
        }
    }

    @Override
    public DriverModel updateDriver(DriverModel driverModel) {
        if(!userCRUDRepository.exists(driverModel.getUserID()))//user not found
            return null;
        else{
            //update drive info
            com.atp.webservice.parking_reservation_10.entities.Driver driverEntity = driverCRUDRepository.findOne(driverModel.getUserID());
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            driverEntity.setBalance(driverModel.getBalance());
            driverEntity.setFullName(driverModel.getDriverName());
            driverEntity.setApplicationID(driverModel.getApplicationID());
            driverEntity.setPassword(encoder.encode(driverModel.getPassword()));

            return driverConverter.convertFromEntity(driverCRUDRepository.save(driverEntity));
        }
    }

    @Override
    public DriverModel getDriverById(String id) {
       return driverConverter.convertFromEntity(driverCRUDRepository.findOne(id));
    }

    @Override
    public DriverModel getDriverByEmailOrPhoneNumber(String userName) {
        User user = userCRUDRepository.findFirstByEmail(userName);//find by email
        if(user == null)
            user = userCRUDRepository.findFirstByPhoneNumber(userName);//find by user name
        if(user == null)
            return null;
        return driverConverter.convertFromEntity(driverCRUDRepository.findOne(user.getUserID()));
    }

    @Override
    public List<DriverModel> getAll() {
        List<DriverModel> driverModelList = new ArrayList<DriverModel>();
        for(com.atp.webservice.parking_reservation_10.entities.Driver driver : driverCRUDRepository.findAll()){
            driverModelList.add(driverConverter.convertFromEntity(driver));
        }

        return driverModelList;
    }

    @Override
    public List<DriverModel> getPageList(int pageNumber) {
        List<DriverModel> driverModelList = new ArrayList<DriverModel>();
        PageRequest pageRequest = new PageRequest(pageNumber-1, PAGE_SIZE);
        for(com.atp.webservice.parking_reservation_10.entities.Driver driver : driverCRUDRepository.findAll(pageRequest)){
            driverModelList.add(driverConverter.convertFromEntity(driver));
        }
        return driverModelList;
    }

}
