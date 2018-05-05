package com.atp.webservice.parking_reservation_10.services.userService;

import com.atp.webservice.parking_reservation_10.entities.User;
import com.atp.webservice.parking_reservation_10.services.models.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserModel convertFromEntity(User user){

        UserModel userModel = new UserModel();
        userModel.setEmail(user.getEmail());
        userModel.setId(user.getUserID());
        userModel.setPassword("");
        userModel.setStatus(user.getStatus());
        userModel.setUserType(user.getUserType());

        return userModel;
    }
}
