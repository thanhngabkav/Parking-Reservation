package com.atp.webservice.parking_reservation_10.services.userService.userServiceImp;

import com.atp.webservice.parking_reservation_10.entities.User;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.UserCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.models.UserModel;
import com.atp.webservice.parking_reservation_10.services.userService.UserConverter;
import com.atp.webservice.parking_reservation_10.services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserCRUDRepository userCRUDRepository;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserModel getUserByEmailOrPhoneNumber(String userName) {
        User userEntity = userCRUDRepository.findFirstByEmail(userName);
        if(userEntity == null)
            userEntity = userCRUDRepository.findFirstByPhoneNumber(userName);
        if(userEntity!= null)
            return userConverter.convertFromEntity(userEntity);
        return null;
    }
}
