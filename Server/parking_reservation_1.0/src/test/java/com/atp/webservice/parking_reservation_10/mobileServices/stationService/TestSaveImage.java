package com.atp.webservice.parking_reservation_10.mobileServices.stationService;

import com.atp.webservice.parking_reservation_10.services.stationService.StationService;
import com.atp.webservice.parking_reservation_10.services.stationService.stationServiceImp.StationServiceImp;

import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestSaveImage {
    public static void main(String[] args) throws IOException {
        StationServiceImp stationServiceImp = new StationServiceImp();
//        String filePath = "F:/Images/My Picture/Avata.jpg";
//        Path path = Paths.get(filePath);
//        byte[] image = Files.readAllBytes(path);
        List<byte[]> list = stationServiceImp.getAllStationImage(2);
        for(byte[] bytes : list){
            System.out.println(bytes.length);
            stationServiceImp.upLoadNewImage(bytes,2);
        }

    }
}
