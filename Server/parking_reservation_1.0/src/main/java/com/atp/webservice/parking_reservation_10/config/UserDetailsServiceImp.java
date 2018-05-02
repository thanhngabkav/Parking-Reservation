package com.atp.webservice.parking_reservation_10.config;

import com.atp.webservice.parking_reservation_10.entities.User;
import com.atp.webservice.parking_reservation_10.entities.Role;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private UserService userService;

    private Logger logger = Logger.getLogger(UserDetailsServiceImp.class);


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = this.userService.getUserByUserName((userName));
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        List<Role> roleList = user.getRoles();
        for (Role role : roleList) {
            if(!role.getRoleName().startsWith("ROLE_")){
                grantedAuthorityList.add( new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            }else {
                grantedAuthorityList.add( new SimpleGrantedAuthority(role.getRoleName()));
            }
        }

        logger.info("Authenticated DriverModel: " + userName);

        return new org.springframework.security.core.userdetails.User(userName,user.getPassword(), grantedAuthorityList);
    }

    
}
