package com.atp.webservice.parking_reservation_10.services.mobileServices.vehicleService.vehicleServiceImp;

import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.VehicleCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.VehicleModel;
import com.atp.webservice.parking_reservation_10.services.mobileServices.vehicleService.VehicleService;
import com.atp.webservice.parking_reservation_10.services.mobileServices.vehicleTypeService.vehicleTypeServiceImp.VehicleTypeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class VehicleServiceImp implements VehicleService {

    public static final int PAGE_SIZE = 20;

    @Autowired
    private VehicleCRUDRepository vehicleCRUDRepository;

    @Override
    public VehicleModel addNewVehicle(VehicleModel vehicleModel) {
        com.atp.webservice.parking_reservation_10.entities.Vehicle vehicleEntity = convertFromModel(vehicleModel);
        if(vehicleEntity == null )//convert  fail
            return null;

        vehicleEntity.setID(UUID.randomUUID().toString());
        return convertFromEntity(vehicleCRUDRepository.save(vehicleEntity));
    }

    @Override
    public VehicleModel editVehicle(VehicleModel vehicleModel) {
        com.atp.webservice.parking_reservation_10.entities.Vehicle vehicleEntity = convertFromModel(vehicleModel);
        if(vehicleEntity == null || !vehicleCRUDRepository.exists(vehicleModel.getId()))//convert fail or not existed
            return null;
        return convertFromEntity(vehicleCRUDRepository.save(vehicleEntity));
    }

    @Override
    public List<VehicleModel> getAllDriverVehicleByDriver(String driverID) {
        List<com.atp.webservice.parking_reservation_10.entities.Vehicle> driverVehicles =
                vehicleCRUDRepository.findByDriverID(driverID);
        List<VehicleModel> vehicleModels = new ArrayList<VehicleModel>();
        for(com.atp.webservice.parking_reservation_10.entities.Vehicle vehicle : driverVehicles)
            vehicleModels.add(convertFromEntity(vehicle));
        return vehicleModels;
    }

    @Override
    public List<VehicleModel> getPageListVehicleByDriver(String driverID, int pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber-1, PAGE_SIZE);
        List<VehicleModel> vehicleModels = new ArrayList<VehicleModel>();
        for(com.atp.webservice.parking_reservation_10.entities.Vehicle vehicle :
                vehicleCRUDRepository.findByDriverID(driverID,pageRequest))
            vehicleModels.add(convertFromEntity(vehicle));
        return vehicleModels;
    }

    @Override
    public List<VehicleModel> getPageListVehicle(int pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber-1, PAGE_SIZE);
        List<VehicleModel> vehicleModels = new ArrayList<VehicleModel>();
        for(com.atp.webservice.parking_reservation_10.entities.Vehicle vehicle :
                vehicleCRUDRepository.findAll(pageRequest))
            vehicleModels.add(convertFromEntity(vehicle));
        return vehicleModels;
    }

    @Override
    public VehicleModel getVehicleById(String vehicleID) {
        return convertFromEntity(vehicleCRUDRepository.findOne(vehicleID));
    }

    @Override
    public List<VehicleModel> getPageListVehicleByTypeId(int typeID, int pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber-1, PAGE_SIZE);
        List<VehicleModel> vehicleModels = new ArrayList<VehicleModel>();
        for(com.atp.webservice.parking_reservation_10.entities.Vehicle vehicle :
                vehicleCRUDRepository.findByVehicleTypeID(typeID,pageRequest))
            vehicleModels.add(convertFromEntity(vehicle));
        return vehicleModels;
    }

    /**
     * Convert from {@link com.atp.webservice.parking_reservation_10.entities.Vehicle} to {@link VehicleModel}
     * @param vehicleEntity {@link com.atp.webservice.parking_reservation_10.entities.Vehicle}
     * @return VehicleModel
     */
    public static VehicleModel convertFromEntity(com.atp.webservice.parking_reservation_10.entities.Vehicle vehicleEntity){
        if(vehicleEntity == null)
            return null;
        VehicleModel vehicleModel = new VehicleModel();
        vehicleModel.setName(vehicleEntity.getName());
        vehicleModel.setLicensePlate(vehicleEntity.getLicensePlate());
        vehicleModel.setId(vehicleEntity.getID());
        vehicleModel.setVehicleTypeModel(VehicleTypeServiceImp.convertFromEntity(vehicleEntity.getVehicleType()));
        vehicleModel.setDriverID(vehicleEntity.getDriverID());

        return vehicleModel;
    }

    /**
     * Convert from {@link VehicleModel} to {@link com.atp.webservice.parking_reservation_10.entities.Vehicle}
     * @param vehicleModelModel {@link VehicleModel}
     * @return VehicleModel
     */
    public static com.atp.webservice.parking_reservation_10.entities.Vehicle convertFromModel(VehicleModel vehicleModelModel){
        if(vehicleModelModel == null)
            return  null;
        com.atp.webservice.parking_reservation_10.entities.Vehicle vehicle = new com.atp.webservice.parking_reservation_10.entities.Vehicle();
        vehicle.setName(vehicleModelModel.getName());
        vehicle.setVehicleTypeID(vehicleModelModel.getVehicleTypeModel().getTypeID());
        vehicle.setVehicleType(VehicleTypeServiceImp.convertFromModel(vehicleModelModel.getVehicleTypeModel()));
        vehicle.setID(vehicleModelModel.getId());
        vehicle.setDriverID(vehicleModelModel.getDriverID());
        vehicle.setLicensePlate(vehicleModelModel.getLicensePlate());

        return vehicle;
    }
}
