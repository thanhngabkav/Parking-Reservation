package com.atp.webservice.parking_reservation_10.config;

import com.atp.webservice.parking_reservation_10.entities.User;
import com.atp.webservice.parking_reservation_10.entities.UserRole;
import com.atp.webservice.parking_reservation_10.services.UserService;
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
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userService.getUserByUserName((s));
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        //Add User roles here
        //I will add DF roles is ROLE_USER
        //logger.info(user.toString());

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<GrantedAuthority>();
        List<UserRole> userRoleList = user.getUserRoles();
        for (UserRole role : userRoleList) {
            if(!role.getRole().startsWith("ROLE_")){
                grantedAuthorityList.add( new SimpleGrantedAuthority("ROLE_" + role.getRole()));
            }else {
                grantedAuthorityList.add( new SimpleGrantedAuthority(role.getRole()));
            }
        }

        return new org.springframework.security.core.userdetails.User(user.getUserName(),user.getPassword(), grantedAuthorityList);
    }
}
