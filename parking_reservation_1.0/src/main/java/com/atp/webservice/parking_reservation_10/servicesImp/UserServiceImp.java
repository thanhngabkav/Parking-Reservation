package com.atp.webservice.parking_reservation_10.servicesImp;

import com.atp.webservice.parking_reservation_10.entities.User;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.UserCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserCRUDRepository userRepository;

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }
}
