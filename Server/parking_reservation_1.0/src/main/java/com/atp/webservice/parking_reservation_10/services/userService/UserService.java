package com.atp.webservice.parking_reservation_10.services.userService;

import com.atp.webservice.parking_reservation_10.services.models.UserModel;

public interface UserService {

    UserModel getUserByEmailOrPhoneNumber(String userName);
}
