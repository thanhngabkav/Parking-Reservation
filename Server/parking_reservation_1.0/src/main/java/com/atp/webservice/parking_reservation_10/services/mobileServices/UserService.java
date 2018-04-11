package com.atp.webservice.parking_reservation_10.services.mobileServices;

import com.atp.webservice.parking_reservation_10.services.mobileServices.presenter.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    /**
     * Find single user by user name
     * @param userName : User Name
     * @return {@link User} if found or null if not
     */
    User getUserByUserName(String userName);

    /**
     * Get User by User ID
     * @param userID : User ID
     * @return {@link User}
     */
    User getUserByUserID(String userID);

    /**
     * Create new {@link com.atp.webservice.parking_reservation_10.entities.Driver} from {@link User}
     * @param user {@link User}
     * @return true if success, and false if fail
     */
    boolean createNewDriver(User user);

}
