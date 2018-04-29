package com.atp.webservice.parking_reservation_10.services.messageService.messageServiceImp;


import com.atp.webservice.parking_reservation_10.services.messageService.MessageService;
import com.atp.webservice.parking_reservation_10.services.messageService.models.MessageStatus;
import com.atp.webservice.parking_reservation_10.services.messageService.models.ServerMessage;
import com.atp.webservice.parking_reservation_10.services.messageService.MessageService;
import com.atp.webservice.parking_reservation_10.services.messageService.models.MessageStatus;
import com.atp.webservice.parking_reservation_10.services.messageService.models.ServerMessage;
import de.bytefish.fcmjava.responses.TopicMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/message")
public class TestMessageController {

    @Autowired
    private MessageService messageService;

    @RequestMapping("")
    public ResponseEntity<TopicMessageResponse> sendMessage(){
        ServerMessage<String> message = new ServerMessage<>();
        message.setData("Hello master");
        message.setTitle("Test message");
        message.setStatus(MessageStatus.DO_BACKGROUND);

        return new ResponseEntity<TopicMessageResponse>(messageService.sendMessageToTopic(message, "station_2"),
                HttpStatus.OK);
    }
}
