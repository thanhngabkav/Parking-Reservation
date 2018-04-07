package com.atp.webservice.parking_reservation_10.genUserID;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class TestGenUserID {
    public static void main(String[] args) throws IOException {
//        System.out.println(UUID.randomUUID());
//        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
//        String time = date.toString();
//        System.out.println("First: " + time);
//
//        Timestamp date2 = Timestamp.valueOf(time);
//        System.out.println("Second: " + date2);

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<GenerateClass.RootObject>> typeReference =  new TypeReference<List<GenerateClass.RootObject>>(){};
        List<GenerateClass.RootObject> rootObjects = objectMapper.readValue(new File("F:\\jsonData.txt"), typeReference);
        for (GenerateClass.RootObject rootObject : rootObjects) {
            System.out.println(rootObject.getName());
            System.out.println("lat: " + rootObject.getGeometry().getLocation().getLat());
            System.out.println("lng: " + rootObject.getGeometry().getLocation().getLng());
        }
    }
}
