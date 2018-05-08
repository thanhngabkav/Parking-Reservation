package com.atp.webservice.parking_reservation_10.services.ownerService.ownerServiceImp;

import com.atp.webservice.parking_reservation_10.entities.Owner;
import com.atp.webservice.parking_reservation_10.entities.User;
import com.atp.webservice.parking_reservation_10.entities.uitls.UserStatus;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.OwnerCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.UserCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.algorithms.KeyHelper;
import com.atp.webservice.parking_reservation_10.services.models.OwnerModel;
import com.atp.webservice.parking_reservation_10.services.ownerService.OwnerConverter;
import com.atp.webservice.parking_reservation_10.services.ownerService.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class OwnerSerivceImp implements OwnerService {

    @Autowired
    private OwnerCRUDRepository ownerCRUDRepository;

    @Autowired
    private UserCRUDRepository userCRUDRepository;

    @Autowired
    private OwnerConverter ownerConverter;

    private static int PAGE_SIZE = 20;

    @Override
    public Owner findByPhoneNumberOrEmail(String phoneOrEmail) {
        User user = userCRUDRepository.findFirstByEmail(phoneOrEmail);//find by email
        if(user == null)
            user = userCRUDRepository.findFirstByPhoneNumber(phoneOrEmail);//find by user name
        if(user == null)
            return null;
        return ownerCRUDRepository.findOne(user.getUserID());
    }

    @Override
    public OwnerModel createOwner(OwnerModel ownerModel) throws NoSuchAlgorithmException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Owner owner = ownerConverter.convertFromModel(ownerModel);
        owner.setUserID(UUID.randomUUID().toString());
        owner.setStatus(UserStatus.WAITING);
        owner.setPassword(encoder.encode(ownerModel.getPassword()));
        owner.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
        owner.setSecretKey(KeyHelper.genSecretKey());

        return ownerConverter.convertFromEntity(ownerCRUDRepository.save(owner));
    }

    @Override
    public Page<OwnerModel> getPageListOwners(int page) {
        PageRequest pageRequest = new PageRequest(page-1, PAGE_SIZE);
        Page<Owner> ownerList = ownerCRUDRepository.findAllByOrderByCreatedTimeDesc(pageRequest);

        List<OwnerModel> ownerModelList = new ArrayList<>();
        for(Owner owner : ownerList.getContent()){
            ownerModelList.add(ownerConverter.convertFromEntity(owner));
        }
        Page<OwnerModel> ownerModelPage = new PageImpl<OwnerModel>(ownerModelList);
        return ownerModelPage;
    }

    @Override
    public List<OwnerModel> getAllOwnerModel() {
        List<Owner> ownerList = ownerCRUDRepository.findAllByOrderByCreatedTimeDesc();
        List<OwnerModel> ownerModelList = new ArrayList<OwnerModel>();
        for(Owner ownerEntity :  ownerList){
            ownerModelList.add(ownerConverter.convertFromEntity(ownerEntity));
        }
        return ownerModelList;
    }

    @Override
    public OwnerModel updateOwner(OwnerModel ownerModel) throws NoSuchAlgorithmException {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Owner owner = ownerConverter.convertFromModel(ownerModel);
        owner.setPassword(encoder.encode(ownerModel.getPassword()));

        return ownerConverter.convertFromEntity(ownerCRUDRepository.save(owner));
    }


}