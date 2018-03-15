package com.atp.webservice.parking_reservation_10.services;

import com.atp.webservice.parking_reservation_10.entities.User;

public interface UserService {

    /**
     * Find single user by user name
     * @param userName : User Name
     * @return @{@link User} if found or null if not
     */
    User getUserByUserName(String userName);
}
