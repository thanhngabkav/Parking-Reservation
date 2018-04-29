package com.atp.webservice.parking_reservation_10.services.driverService;

import com.atp.webservice.parking_reservation_10.entities.uitls.UserRole;
import com.atp.webservice.parking_reservation_10.entities.uitls.UserStatus;
import com.atp.webservice.parking_reservation_10.entities.uitls.UserType;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.RoleCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.VehicleCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.models.DriverModel;
import com.atp.webservice.parking_reservation_10.services.models.VehicleModel;
import com.atp.webservice.parking_reservation_10.services.vehicleService.vehicleServiceImp.VehicleServiceImp;
import com.atp.webservice.parking_reservation_10.services.vehicleService.vehicleServiceImp.VehicleServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DriverConverter {


    @Autowired
    private VehicleCRUDRepository vehicleCRUDRepository;

    @Autowired
    private RoleCRUDRepository roleCRUDRepository;

    /**
     * Convert from {@link com.atp.webservice.parking_reservation_10.entities.Driver} to {@link com.atp.webservice.parking_reservation_10.entities.Driver}
     * @param driverEntity {@link com.atp.webservice.parking_reservation_10.entities.Driver}
     * @return DriverModel
     */
    public DriverModel convertFromEntity(com.atp.webservice.parking_reservation_10.entities.Driver driverEntity){
        if(driverEntity == null)
            return null;
        DriverModel driverModel = new DriverModel();
        driverModel.setEmail(driverEntity.getEmail());
        driverModel.setPhoneNumber(driverEntity.getPhoneNumber());
        driverModel.setUserID(driverEntity.getUserID());
        driverModel.setDriverName(driverEntity.getFullName());
        driverModel.setBalance(driverEntity.getBalance());
        List<VehicleModel> vehicleModels = new ArrayList<VehicleModel>();
        for (com.atp.webservice.parking_reservation_10.entities.Vehicle vehicle : vehicleCRUDRepository.findByDriverID(driverEntity.getUserID())){
            vehicleModels.add(VehicleServiceImp.convertFromEntity(vehicle));
        }
        driverModel.setVehicleModels(vehicleModels);
        return driverModel;
    }

    /**
     * Convert from {@link DriverModel } to {@link com.atp.webservice.parking_reservation_10.entities.Driver}
     * @param driverModelModel
     * @return DriverModel
     */
    public com.atp.webservice.parking_reservation_10.entities.Driver convertToEntity(DriverModel driverModelModel){
        if(driverModelModel == null)
            return null;
        com.atp.webservice.parking_reservation_10.entities.Driver driverEntity = new com.atp.webservice.parking_reservation_10.entities.Driver();
        driverEntity.setUserType(UserType.DRIVER);
        driverEntity.setRoles(roleCRUDRepository.findRoleByRoleName(UserRole.DRIVER_ROLE));
        driverEntity.setPassword(driverModelModel.getPassword());
        driverEntity.setStatus(UserStatus.ACTIVE);
        driverEntity.setEmail(driverModelModel.getEmail());
        driverEntity.setPhoneNumber(driverModelModel.getPhoneNumber());
        driverEntity.setFullName(driverModelModel.getDriverName());
        driverEntity.setBalance(driverModelModel.getBalance());
        driverEntity.setUserID(driverModelModel.getUserID());
        driverEntity.setBalance(driverModelModel.getBalance());
        driverEntity.setApplicationID(driverModelModel.getApplicationID());
        driverEntity.setUserID(driverModelModel.getUserID());

        return driverEntity;
    }
}
