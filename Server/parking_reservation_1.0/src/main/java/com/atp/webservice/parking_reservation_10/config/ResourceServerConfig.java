package com.atp.webservice.parking_reservation_10.config;

import com.atp.webservice.parking_reservation_10.entities.uitls.UserRole;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/maps/**").permitAll()
                .antMatchers("/api/stations/**").hasAnyRole(UserRole.DRIVER_ROLE,UserRole.ADMIN_ROLE, UserRole.OWNER_ROLE )
                .antMatchers("/api/vehicles/**").hasAnyRole(UserRole.DRIVER_ROLE,UserRole.ADMIN_ROLE)
                .antMatchers("/api/drivers/**").hasAnyRole(UserRole.DRIVER_ROLE,UserRole.ADMIN_ROLE)
                .antMatchers("/api/tickets/**").hasAnyRole(UserRole.DRIVER_ROLE,UserRole.ADMIN_ROLE, UserRole.OWNER_ROLE );
    }
}
