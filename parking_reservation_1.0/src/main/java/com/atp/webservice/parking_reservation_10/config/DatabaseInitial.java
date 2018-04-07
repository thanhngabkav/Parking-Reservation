package com.atp.webservice.parking_reservation_10.config;


import com.atp.webservice.parking_reservation_10.entities.Driver;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.UserCRUDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public class DatabaseInitial implements ApplicationListener<ContextRefreshedEvent>{

    @Autowired
    private UserCRUDRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //Init Mobile app users
        initMobileAppUsers();
    }

    private void initOwner(){

    }

    private void initMobileAppUsers() {
        for(int i=1;i<=100;i++){
            Driver driver = new Driver();

        }
    }
}
