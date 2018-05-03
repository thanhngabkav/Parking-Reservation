package com.atp.webservice.parking_reservation_10.services.stationService;


import com.atp.webservice.parking_reservation_10.services.messageService.MessageService;
import com.atp.webservice.parking_reservation_10.services.messageService.models.MessageStatus;
import com.atp.webservice.parking_reservation_10.services.messageService.models.MessageTopic;
import com.atp.webservice.parking_reservation_10.services.messageService.models.ServerMessage;
import com.atp.webservice.parking_reservation_10.services.models.StationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/stations")
public class StationController {

    @Autowired
    private StationService stationService;

    @Autowired
    private MessageService messageService;

    /**
     * Find stations by name
     *
     * @param name station name
     * @return
     */
    @RequestMapping(value = "/")
    public ResponseEntity<List<StationModel>> findByName(@RequestParam(value = "name") String name) {

        return new ResponseEntity<List<StationModel>>(stationService.getStationByName(name), HttpStatus.OK);
    }

    /**
     * Find StationOverview by ID
     *
     * @param stationID
     * @return
     */
    @RequestMapping(value = "/{id}")
    public ResponseEntity<StationModel> findOne(@PathVariable(value = "id") Integer stationID) {

        return new ResponseEntity<StationModel>(stationService.getStationByID(stationID), HttpStatus.OK);
    }

    /**
     * Add new Station
     * @param stationModel
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StationModel> addNewStation(@RequestBody StationModel stationModel) {
        StationModel m_stationModel = stationService.addNewStation(stationModel);
        if (m_stationModel == null)
            return new ResponseEntity<StationModel>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<StationModel>(m_stationModel, HttpStatus.OK);
    }

    /**
     * Add new Station
     * @param stationModel
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StationModel> updateStation(@RequestBody StationModel stationModel) {
        StationModel m_stationModel = stationService.updateStation(stationModel);
        if (m_stationModel == null)
            return new ResponseEntity<StationModel>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<StationModel>(m_stationModel, HttpStatus.OK);
    }


    /**
     * Add new images for station
     * @param stationID
     * @param files
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/{id}/images",headers = "Content-Type= multipart/form-data", method = RequestMethod.POST)
    public ResponseEntity<List<byte[]>> saveImages(@PathVariable(name = "id") int stationID,
                                            @RequestParam(name = "file")MultipartFile[] files) throws IOException {

        List<byte[]> result = new ArrayList<byte[]>();
        for(MultipartFile file : files){
            result.add(stationService.upLoadNewImage(file.getBytes(), stationID));
        }

        return new ResponseEntity<List<byte[]>>(result, HttpStatus.OK);
    }


    /**
     * Update station images by file name
     * @param stationID
     * @param files
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/{id}/images",headers = "Content-Type= multipart/form-data", method = RequestMethod.PUT)
    public ResponseEntity<List<byte[]>> updateImages(@PathVariable(name = "id") int stationID,
                                                  @RequestParam(name = "file")MultipartFile[] files) throws IOException {

        List<byte[]> result = new ArrayList<byte[]>();
        for(MultipartFile file : files){
            result.add(stationService.updateImage(file.getBytes(), file.getOriginalFilename(), stationID));
        }
        return new ResponseEntity<List<byte[]>>(result, HttpStatus.OK);
    }

    /**
     * Get all station images
     * @param stationID
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/{id}/images", method = RequestMethod.GET)
    public ResponseEntity<List<byte[]>> getImages(@PathVariable(name = "id") int stationID) throws IOException {
        List<byte[]> result = stationService.getAllStationImage(stationID);
        return new ResponseEntity<List<byte[]>>(result, HttpStatus.OK);
    }

}
