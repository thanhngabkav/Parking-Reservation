package com.atp.webservice.parking_reservation_10.genUserID;

import com.atp.webservice.parking_reservation_10.services.algorithms.KeyHelper;
import com.atp.webservice.parking_reservation_10.services.algorithms.KeypairHelper;
import com.atp.webservice.parking_reservation_10.services.algorithms.MapHelper;
import com.atp.webservice.parking_reservation_10.services.models.TicketModel;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

public class TestGenUserID {
    public static void main(String[] args) throws Exception {
//       String text = "123";
//
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String enPass = encoder.encode(text);
//        System.out.println(enPass);
//        BCryptPasswordEncoder encoder1 = new BCryptPasswordEncoder();
//        String enPass1 = encoder1.encode(text);
//        System.out.println(enPass1);
//        System.out.println(encoder.matches(text,enPass1));
//        System.out.println(encoder1.matches(text, enPass));
//        ObjectMapper objectMapper = new ObjectMapper();
//        String mess = objectMapper.writeValueAsString(new TicketModel());
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(mess).append(mess).append(mess).append(mess).append(mess).append(mess).append(mess).append(mess).append(mess).append(mess);
//        String scretKey = KeyHelper.genSecretKey();
//        String scretKey2 = KeyHelper.genSecretKey();
//        System.out.println(scretKey);
//        System.out.println(scretKey2);
//        String encryptedMessage = KeyHelper.encrypt(stringBuilder.toString(), scretKey);
//        String decryptedMessage = KeyHelper.decrypt(encryptedMessage, scretKey);
//
//        System.out.println(encryptedMessage);
//        System.out.println(decryptedMessage);
        MapHelper mapHelper = new MapHelper();
        System.out.println(mapHelper.distance(10.7974333,106.67,10.76739,106.696872,'K') <=5);
    }

}
