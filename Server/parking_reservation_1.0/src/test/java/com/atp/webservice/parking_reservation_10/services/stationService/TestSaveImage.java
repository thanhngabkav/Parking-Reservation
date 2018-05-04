package com.atp.webservice.parking_reservation_10.services.stationService;

import com.atp.webservice.parking_reservation_10.services.stationService.stationServiceImp.StationServiceImp;

import java.util.*;
import java.io.IOException;

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
