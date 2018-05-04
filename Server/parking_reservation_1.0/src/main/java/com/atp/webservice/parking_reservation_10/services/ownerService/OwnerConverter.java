package com.atp.webservice.parking_reservation_10.services.ownerService;

import com.atp.webservice.parking_reservation_10.entities.Owner;
import com.atp.webservice.parking_reservation_10.entities.Role;
import com.atp.webservice.parking_reservation_10.entities.uitls.DefaultValue;
import com.atp.webservice.parking_reservation_10.entities.uitls.UserRole;
import com.atp.webservice.parking_reservation_10.entities.uitls.UserType;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.OwnerCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.RoleCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.models.OwnerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class OwnerConverter {

    @Autowired
    private StationCRUDRepository stationCRUDRepository;

    @Autowired
    private RoleCRUDRepository roleCRUDRepository;

    /**
     * Convert from Entity to Model
     * @param owner
     * @return
     */
    public OwnerModel convertFromEntity(Owner owner){

        OwnerModel ownerModel = new OwnerModel();
        ownerModel.setAddress(owner.getAddress());
        ownerModel.setBank(owner.getBankName());
        ownerModel.setEmail(owner.getEmail());
        ownerModel.setBankAccountNumber(owner.getBankAccountNumber());
        ownerModel.setFullName(owner.getName());
        ownerModel.setId(owner.getUserID());
        ownerModel.setNumStations((int) stationCRUDRepository.countByOwnerID(owner.getUserID()));
        ownerModel.setPassword(owner.getPassword());
        ownerModel.setPhoneNumber(owner.getPhoneNumber());
        ownerModel.setStatus(owner.getStatus());
        ownerModel.setSecretKey(owner.getSecretKey());

        return ownerModel;

    }


    /**
     * Convert from model to Entity
     * @param ownerModel
     * @return
     */
    public Owner convertFromModel(OwnerModel ownerModel){
        Owner owner = new Owner();
        List<Role> roleList = roleCRUDRepository.findRoleByRoleName(UserRole.OWNER_ROLE);

        owner.setAddress(ownerModel.getAddress());
        owner.setBankAccountNumber(ownerModel.getBankAccountNumber());
        owner.setBankName(ownerModel.getBank());
        owner.setName(ownerModel.getFullName());
        owner.setSecretKey(ownerModel.getSecretKey());
        owner.setUserName(DefaultValue.STRING);
        owner.setEmail(ownerModel.getEmail());
        owner.setPassword(ownerModel.getPassword());
        owner.setPhoneNumber(ownerModel.getPhoneNumber());
        owner.setRoles(roleList);
        owner.setStatus(ownerModel.getStatus());
        owner.setUserID(ownerModel.getId());
        owner.setUserType(UserType.OWNER);


        return owner;
    }
}
