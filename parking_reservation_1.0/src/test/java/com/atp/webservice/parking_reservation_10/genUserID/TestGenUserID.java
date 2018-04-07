//package com.atp.webservice.parking_reservation_10.genUserID;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
//public class TestGenUserID {
//    public static void main(String[] args) throws IOException {
////        System.out.println(UUID.randomUUID());
////        Timestamp date = Timestamp.valueOf(LocalDateTime.now());
////        String time = date.toString();
////        System.out.println("First: " + time);
////
////        Timestamp date2 = Timestamp.valueOf(time);
////        System.out.println("Second: " + date2);
//        int count = 0;
//        for(int i=1;i<=100;i++){
//            if(i%3 == 1 && i%4 != 3 && i%4 != 1){
//                count++;
//            }
//        }
//
//        System.out.println("Count: " +count);
//        ObjectMapper objectMapper = new ObjectMapper();
//        TypeReference<List<GenerateClass.RootObject>> typeReference =  new TypeReference<List<GenerateClass.RootObject>>(){};
//        List<GenerateClass.RootObject> rootObjects = objectMapper.readValue(new File("F:\\jsonData.txt"), typeReference);
//        System.out.println("Number station: " + rootObjects.size());
//        for (GenerateClass.RootObject rootObject : rootObjects) {
//            System.out.println(rootObject.getName());
//            System.out.println("lat: " + rootObject.getGeometry().getLocation().getLat());
//            System.out.println("lng: " + rootObject.getGeometry().getLocation().getLng());
//        }
//    }
//}
