package com.atp.webservice.parking_reservation_10.services.messageService;


import com.atp.webservice.parking_reservation_10.entities.Service;
import com.atp.webservice.parking_reservation_10.services.messageService.models.MessageStatus;
import com.atp.webservice.parking_reservation_10.services.messageService.models.MessageTopic;
import com.atp.webservice.parking_reservation_10.services.messageService.models.ServerMessage;
import de.bytefish.fcmjava.responses.TopicMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/testtopic", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TopicMessageResponse> sendTopic(){

        ServerMessage<Service> message = new ServerMessage<>();
        Service data = new Service();
        data.setServiceID(1);
        data.setServiceName("Service Name");
        message.setData(data);
        message.setStatus(MessageStatus.DO_BACKGROUND);
        message.setTitle("Message Title");
        TopicMessageResponse response = messageService.sendMessageToTopic(message,MessageTopic.DRIVER_TOPIC);

        return new ResponseEntity<TopicMessageResponse>(response, HttpStatus.OK);
    }
}
