package com.atp.webservice.parking_reservation_10.genUserID;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestGenUserID {
    public static void main(String[] args) throws IOException {
       String text = "123";

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String enPass = encoder.encode(text);
        System.out.println(enPass);
        BCryptPasswordEncoder encoder1 = new BCryptPasswordEncoder();
        String enPass1 = encoder1.encode(text);
        System.out.println(enPass1);
        System.out.println(encoder.matches(text,enPass1));
        System.out.println(encoder1.matches(text, enPass));
    }

}
