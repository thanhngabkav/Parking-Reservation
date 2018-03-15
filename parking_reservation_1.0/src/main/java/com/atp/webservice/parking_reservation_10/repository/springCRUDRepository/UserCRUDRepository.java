package com.atp.webservice.parking_reservation_10.repository.springCRUDRepository;

import com.atp.webservice.parking_reservation_10.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserCRUDRepository extends CrudRepository<User,Integer>{
    User findUserByUserName(String userName);
}
