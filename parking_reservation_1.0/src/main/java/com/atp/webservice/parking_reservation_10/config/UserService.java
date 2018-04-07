package com.atp.webservice.parking_reservation_10.config;

import com.atp.webservice.parking_reservation_10.entities.User;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.UserCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserCRUDRepository userCRUDRepository;

    public User getUserByUserName(String userName) {
        return  userCRUDRepository.findUserByEmailOrPhoneNumber(userName);
    }
}
