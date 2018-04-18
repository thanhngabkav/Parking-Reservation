package com.atp.webservice.parking_reservation_10.services.mobileServices.vehicleService.vehicleServiceImp;

import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.VehicleCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.Vehicle;
import com.atp.webservice.parking_reservation_10.services.mobileServices.models.VehicleType;
import com.atp.webservice.parking_reservation_10.services.mobileServices.vehicleService.VehicleService;
import com.atp.webservice.parking_reservation_10.services.mobileServices.vehicleTypeService.vehicleTypeServiceImp.VehicleTypeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Vehicle addNewVehicle(Vehicle vehicle) {
        com.atp.webservice.parking_reservation_10.entities.Vehicle vehicleEntity = convertFromModel(vehicle);
        if(vehicleEntity == null )//convert  fail
            return null;

        vehicleEntity.setID(UUID.randomUUID().toString());
        return convertFromEntity(vehicleCRUDRepository.save(vehicleEntity));
    }

    @Override
    public Vehicle editVehicle(Vehicle vehicle) {
        com.atp.webservice.parking_reservation_10.entities.Vehicle vehicleEntity = convertFromModel(vehicle);
        if(vehicleEntity == null || !vehicleCRUDRepository.exists(vehicle.getId()))//convert fail or not existed
            return null;
        return convertFromEntity(vehicleCRUDRepository.save(vehicleEntity));
    }

    @Override
    public List<Vehicle> getAllDriverVehicleByDriver(String driverID) {
        List<com.atp.webservice.parking_reservation_10.entities.Vehicle> driverVehicles =
                vehicleCRUDRepository.findByDriverID(driverID);
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        for(com.atp.webservice.parking_reservation_10.entities.Vehicle vehicle : driverVehicles)
            vehicles.add(convertFromEntity(vehicle));
        return vehicles;
    }

    @Override
    public List<Vehicle> getPageListVehicleByDriver(String driverID, int pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber-1, PAGE_SIZE);
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        for(com.atp.webservice.parking_reservation_10.entities.Vehicle vehicle :
                vehicleCRUDRepository.findByDriverID(driverID,pageRequest))
            vehicles.add(convertFromEntity(vehicle));
        return vehicles;
    }

    @Override
    public List<Vehicle> getPageListVehicle(int pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber-1, PAGE_SIZE);
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        for(com.atp.webservice.parking_reservation_10.entities.Vehicle vehicle :
                vehicleCRUDRepository.findAll(pageRequest))
            vehicles.add(convertFromEntity(vehicle));
        return vehicles;
    }

    @Override
    public Vehicle getVehicleById(String vehicleID) {
        return convertFromEntity(vehicleCRUDRepository.findOne(vehicleID));
    }

    @Override
    public List<Vehicle> getPageListVehicleByTypeId(int typeID, int pageNumber) {
        PageRequest pageRequest = new PageRequest(pageNumber-1, PAGE_SIZE);
        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        for(com.atp.webservice.parking_reservation_10.entities.Vehicle vehicle :
                vehicleCRUDRepository.findByVehicleTypeID(typeID,pageRequest))
            vehicles.add(convertFromEntity(vehicle));
        return vehicles;
    }

    /**
     * Convert from {@link com.atp.webservice.parking_reservation_10.entities.Vehicle} to {@link Vehicle}
     * @param vehicleEntity {@link com.atp.webservice.parking_reservation_10.entities.Vehicle}
     * @return Vehicle
     */
    public static Vehicle convertFromEntity(com.atp.webservice.parking_reservation_10.entities.Vehicle vehicleEntity){
        if(vehicleEntity == null)
            return null;
        Vehicle vehicle = new Vehicle();
        vehicle.setName(vehicleEntity.getName());
        vehicle.setLicensePlate(vehicleEntity.getLicensePlate());
        vehicle.setId(vehicleEntity.getID());
        vehicle.setVehicleType(VehicleTypeServiceImp.convertFromEntity(vehicleEntity.getVehicleType()));
        vehicle.setDriverID(vehicleEntity.getDriverID());

        return vehicle;
    }

    /**
     * Convert from {@link Vehicle} to {@link com.atp.webservice.parking_reservation_10.entities.Vehicle}
     * @param vehicleModel {@link Vehicle}
     * @return Vehicle
     */
    public static com.atp.webservice.parking_reservation_10.entities.Vehicle convertFromModel(Vehicle vehicleModel){
        if(vehicleModel == null)
            return  null;
        com.atp.webservice.parking_reservation_10.entities.Vehicle vehicle = new com.atp.webservice.parking_reservation_10.entities.Vehicle();
        vehicle.setName(vehicleModel.getName());
        vehicle.setVehicleTypeID(vehicleModel.getVehicleType().getTypeID());
        vehicle.setVehicleType(VehicleTypeServiceImp.convertFromModel(vehicleModel.getVehicleType()));
        vehicle.setID(vehicleModel.getId());
        vehicle.setDriverID(vehicleModel.getDriverID());
        vehicle.setLicensePlate(vehicleModel.getLicensePlate());

        return vehicle;
    }
}