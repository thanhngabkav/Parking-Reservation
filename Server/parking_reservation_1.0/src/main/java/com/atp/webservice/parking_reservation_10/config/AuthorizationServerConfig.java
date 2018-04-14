package com.atp.webservice.parking_reservation_10.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    public static final int TOKEN_VALIDITY_SECOND_APP = 2592000; //30 days

    public static final int TOKEN_VALIDITY_SECOND_WEB = 1296000; //15 days

    public static final int TOKEN_VALIDITY_SECOND_3PARTY = 86400; //1 day


    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.checkTokenAccess(("isAuthenticated()"));
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                    .withClient("UserApp")
                    .authorizedGrantTypes("password")
                    .authorities("ROLE_USER_APP")
                    .scopes("read","write")
                    .resourceIds("oauth2-resource")
                    .accessTokenValiditySeconds(TOKEN_VALIDITY_SECOND_APP)
                    .secret("UserApp_ParkingReservation")
                .and()
                    .withClient("WebApp")
                    .authorizedGrantTypes("password")
                    .authorities("ROLE_WEB_APP")
                    .scopes("read","write")
                    .resourceIds("oauth2-resource")
                    .accessTokenValiditySeconds(TOKEN_VALIDITY_SECOND_WEB)
                    .secret("WebApp_ParkingReservation")
                .and()
                    .withClient("3Party")
                    .authorizedGrantTypes("password")
                    .authorities("ROLE_3PARTY_APP")
                    .scopes("read","write")
                    .resourceIds("oauth2-resource")
                    .accessTokenValiditySeconds(TOKEN_VALIDITY_SECOND_3PARTY)
                    .secret("3Party_ParkingReservation");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager);
    }


}