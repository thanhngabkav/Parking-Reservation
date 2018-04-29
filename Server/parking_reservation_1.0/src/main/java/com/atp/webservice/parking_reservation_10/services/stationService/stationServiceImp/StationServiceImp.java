package com.atp.webservice.parking_reservation_10.services.stationService.stationServiceImp;

import com.atp.webservice.parking_reservation_10.entities.Station;
import com.atp.webservice.parking_reservation_10.entities.sparkPresenter.StationPresenter;
import com.atp.webservice.parking_reservation_10.entities.uitls.StationStatus;
import com.atp.webservice.parking_reservation_10.repository.sparkRepository.StationRepository;
import com.atp.webservice.parking_reservation_10.repository.springCRUDRepository.StationCRUDRepository;
import com.atp.webservice.parking_reservation_10.services.messageService.MessageService;
import com.atp.webservice.parking_reservation_10.services.messageService.models.MessageStatus;
import com.atp.webservice.parking_reservation_10.services.messageService.models.MessageTopic;
import com.atp.webservice.parking_reservation_10.services.messageService.models.ServerMessage;
import com.atp.webservice.parking_reservation_10.services.models.StationModel;
import com.atp.webservice.parking_reservation_10.services.stationService.StationConverter;
import com.atp.webservice.parking_reservation_10.services.stationService.StationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.transaction.Transactional;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
@PropertySource("classpath:application.properties")
public class StationServiceImp implements StationService {

    @Autowired
    private StationCRUDRepository stationCRUDRepository;

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private MessageService messageService;

    @Autowired
    private StationConverter stationConverter;

    @Value("${imagesPath}")
    private String imagePath;

    private static final int IMG_WIDTH = 240;

    private static final int IMG_HEIGHT = 320;

    private static final int MAX_FILES = 5;

    private static Logger logger = Logger.getLogger(StationServiceImp.class);

    @Override
    public List<StationModel> getStationByName(String name) {
        List<com.atp.webservice.parking_reservation_10.entities.Station> stations = stationCRUDRepository.findStationsByName(name);
        List<StationModel> listStationModelPresenter = new ArrayList<StationModel>();
        for (com.atp.webservice.parking_reservation_10.entities.Station station : stations) {
            StationModel stationModelPresenter = stationConverter.convertFromEntities(station);
            listStationModelPresenter.add(stationModelPresenter);
        }

        return listStationModelPresenter;
    }

    @Override
    public StationModel getStationByID(int stationID) {
        StationModel stationModel = stationConverter.convertFromEntities(stationCRUDRepository.findOne(stationID));
        return stationModel;
    }

    @Override
    public StationModel addNewStation(StationModel station) {

        StationPresenter stationPresenter = new StationPresenter();
        station.setID(-1);
        station.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        station.setUsedSlots(0);
        station.setHoldingSlots(0);
        station.setStar(3);
        station.setStatus(StationStatus.WAITING_FOR_ACTIVE);
        //save to database
        Station m_station = stationCRUDRepository.save(stationConverter.convertToEntity(station));
        stationPresenter.convertFromEntities(m_station);
        //save to spark data set
        stationRepository.save(stationPresenter);
        StationModel newStation = stationConverter.convertFromEntities(m_station);

        //send message to admin
        ServerMessage<StationModel> message = new ServerMessage<StationModel>();
        message.setTitle("New Station");
        message.setStatus(MessageStatus.NEW_STATION);
        message.setData(newStation);
        messageService.sendMessageToTopic(message, MessageTopic.ADMIN_TOPIC);

        return newStation;
    }

    @Override
    public StationModel updateHoldingSlots(int stationID, int num) {
        Station station = stationCRUDRepository.findOne(stationID);
        if (station == null || !station.getStatus().equals(StationStatus.ACTIVE)) // station not found or UnActive
            return null;
        station.setHoldingSlots(station.getHoldingSlots() + num > 0 ? station.getHoldingSlots() + num : 0);
        StationModel updatedStation = stationConverter.convertFromEntities(stationCRUDRepository.save(station));
        //send message to station
        ServerMessage<StationModel> message = new ServerMessage<StationModel>();
        message.setStatus(MessageStatus.UPDATE_HOLDING_SLOTS);
        message.setData(updatedStation);
        StringBuilder topicNameBuilder = new StringBuilder();
        topicNameBuilder.append(MessageTopic.STATION_TOPIC);
        topicNameBuilder.append("_").append(stationID);
        messageService.sendMessageToTopic(message, topicNameBuilder.toString());

        return updatedStation;
    }

    @Override
    public StationModel updateUsedSlots(int stationID, int num) {
        Station station = stationCRUDRepository.findOne(stationID);
        if (station == null) // station not found
            return null;
        station.setUsedSlots(station.getUsedSlots() + num > 0 ? station.getHoldingSlots() + num : 0);
        //update station
        Station updatedStation = stationCRUDRepository.save(station);
        //update spark data set
        StationPresenter stationPresenter = new StationPresenter();
        stationPresenter.convertFromEntities(updatedStation);
        stationRepository.save(stationPresenter);
        StationModel updatedStationModel = stationConverter.convertFromEntities(updatedStation);
        //send message to station
        ServerMessage<StationModel> message = new ServerMessage<StationModel>();
        message.setTitle("Used slots changed");
        message.setStatus(MessageStatus.UPDATE_USED_SLOTS);
        message.setData(updatedStationModel);
        StringBuilder topicNameBuilder = new StringBuilder();
        topicNameBuilder.append(MessageTopic.STATION_TOPIC);
        topicNameBuilder.append("_").append(stationID);
        messageService.sendMessageToTopic(message, topicNameBuilder.toString());
        return updatedStationModel;
    }

    @Override
    public StationModel updateStation(StationModel stationModel) {
        Station m_station = stationCRUDRepository.findOne(stationModel.getID());
        if (m_station == null) { // station not found
            return null;
        }
        //update station
        Station updatedStation = stationCRUDRepository.save(stationConverter.convertToEntity(stationModel));
        //update spark data set
        StationPresenter stationPresenter = new StationPresenter();
        stationPresenter.convertFromEntities(updatedStation);
        stationRepository.save(stationPresenter);
        StationModel updatedStationModel = stationConverter.convertFromEntities(updatedStation);
        //send message to station
        ServerMessage<StationModel> message = new ServerMessage<StationModel>();
        message.setTitle("Update Station");
        message.setStatus(MessageStatus.UPDATE_STATION);
        message.setData(updatedStationModel);
        StringBuilder topicNameBuilder = new StringBuilder();
        topicNameBuilder.append(MessageTopic.STATION_TOPIC);
        topicNameBuilder.append("_").append(stationModel.getID());
        messageService.sendMessageToTopic(message, topicNameBuilder.toString());
        return updatedStationModel;
    }

    @Override
    public byte[] upLoadNewImage(byte[] image, int stationID) throws IOException {
        StringBuilder path = new StringBuilder();
        path.append(imagePath).append("/stations/").append(stationID);
        Path dirPath = Paths.get(path.toString());
        if (Files.notExists(dirPath)) {
            try {
                Files.createDirectories(dirPath);
            } catch (IOException e) {
                logger.error("Can not create new path");
                e.printStackTrace();
            }
        }

        if(Files.list(dirPath).count() >= MAX_FILES){
            logger.warn("Number files in "+ dirPath.toString() +" is out of range!");
            return new byte[0];
        }

        String fileName = UUID.randomUUID().toString();
        Path filePath = Paths.get(path.append("/").append(fileName).append(".png").toString());
        return saveImage(image, fileName, filePath);
    }

    @Override
    public byte[] updateImage(byte[] image, String fileName, int stationID) throws IOException {
        StringBuilder path = new StringBuilder();
        path.append(imagePath).append("/stations/").append(stationID).append("/").append(fileName);
        Path filePath = Paths.get(path.toString());
        if (!Files.exists(filePath)) {
            return new byte[0];
        }
        return saveImage(image, fileName, filePath);
    }

    @Override
    public void deleteImage(String fileName, int stationID) throws IOException {
        StringBuilder path = new StringBuilder();
        path.append(imagePath).append("/stations/").append(stationID).append("/").append(fileName);
        Path filePath = Paths.get(path.toString());
        if (!Files.exists(filePath)) {
            return;
        }
        Files.delete(filePath);
    }

    @Override
    public List<byte[]> getAllStationImage(int stationID) throws IOException {
        StringBuilder path = new StringBuilder();
        List<byte[]> result = new ArrayList<byte[]>();
        path.append(imagePath).append("/stations/").append(stationID);
        Path dirPath = Paths.get(path.toString());
        if (Files.notExists(dirPath)) {
           return  result;
        }
        List<File> files = Files.list(dirPath)
                            .filter(Files::isRegularFile)
                            .map(Path::toFile)
                            .collect(Collectors.toList());
        FileInputStream fileInputStream = new FileInputStream(files.get(0));
        for(File file: files)
            result.add(Files.readAllBytes(file.toPath()));

        return result;
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();
        return resizedImage;
    }

    private byte[] saveImage(byte[] image, String fileName, Path path) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(image));
        BufferedImage resizedBufferedImage = resizeImage(bufferedImage, BufferedImage.TYPE_INT_ARGB);
        ByteArrayOutputStream resizedByteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(resizedBufferedImage, "png", resizedByteArrayOutputStream);
        resizedByteArrayOutputStream.flush();
        Files.write(path, resizedByteArrayOutputStream.toByteArray());
        logger.info("New Image: " + path.toString());
        return resizedByteArrayOutputStream.toByteArray();
    }
}
