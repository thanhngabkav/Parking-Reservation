package com.atp.webservice.parking_reservation_10.services.ownerService.ownerServiceImp;

import com.atp.webservice.parking_reservation_10.entities.Owner;
import com.atp.webservice.parking_reservation_10.entities.User;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.OwnerCRUDRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.UserCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.ownerService.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerSerivceImp implements OwnerService {

    @Autowired
    private OwnerCRUDRepository ownerCRUDRepository;

    @Autowired
    private UserCRUDRepository userCRUDRepository;

    @Override
    public Owner findByPhoneNumberOrEmail(String phoneOrEmail) {
        User user = userCRUDRepository.findFirstByEmail(phoneOrEmail);//find by email
        if(user == null)
            user = userCRUDRepository.findFirstByPhoneNumber(phoneOrEmail);//find by user name
        if(user == null)
            return null;
        return ownerCRUDRepository.findOne(user.getUserID());
    }
}
