package com.atp.webservice.parking_reservation_10.services.webServices.accountService.serviceImp;

import com.atp.webservice.parking_reservation_10.services.webServices.accountService.AccountService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@PropertySource("classpath:application.properties")
public class AccountServiceImp implements AccountService {

    @Value("${imagesPath}")
    private String imagePath;

    @Override
    public void SaveUserImage(byte[] image, Integer userID, String fileName) throws IOException {
        StringBuilder path = new StringBuilder();
        path.append(imagePath).append("/").append(userID.toString());
        Path dirPath = Paths.get(path.toString());
        if(Files.notExists(dirPath))
            Files.createDirectories(dirPath);
        Path filePath = Paths.get(path.append("/").append(fileName).toString());
        Files.write(filePath,image);
    }

    @Override
    public byte[] GetUserImage(Integer userID, String fileName) throws IOException {
        StringBuilder path = new StringBuilder();
        path.append(imagePath).append("/").append(userID.toString()).append("/").append(fileName);
        return Files.readAllBytes(Paths.get(path.toString()));
    }
}
