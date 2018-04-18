package com.atp.webservice.parking_reservation_10.config;

import com.atp.webservice.parking_reservation_10.entities.uitls.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan
public class WebSecSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImp)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/maps/**").permitAll()
                .antMatchers("/api/stations/**").hasAnyRole(UserRole.DRIVER_ROLE,UserRole.ADMIN_ROLE, UserRole.OWNER_ROLE )
                .antMatchers("/api/vehicles/**").hasAnyRole(UserRole.DRIVER_ROLE,UserRole.ADMIN_ROLE)
                .antMatchers("/api/drivers/**").hasAnyAuthority(UserRole.DRIVER_ROLE,UserRole.ADMIN_ROLE)
                .antMatchers("/api/tickets/**").hasAnyRole(UserRole.DRIVER_ROLE,UserRole.ADMIN_ROLE, UserRole.OWNER_ROLE );
    }
}
