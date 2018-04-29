package com.atp.webservice.parking_reservation_10.services.messageService;


import com.atp.webservice.parking_reservation_10.services.messageService.models.ServerMessage;
import de.bytefish.fcmjava.responses.FcmMessageResponse;
import de.bytefish.fcmjava.responses.TopicMessageResponse;

import java.util.List;

public interface MessageService {

    /**
     * Send downstream message to single client
     * @param clientID : fcm token
     * @param message {@link ServerMessage}
     * @return FcmMessageResponse
     */
    FcmMessageResponse sendMessageToDevice(ServerMessage message,String clientID);

    /**
     * Send message to a topic
     * @param topicName topic name
     * @param message {@link ServerMessage}
     * @return TopicMessageResponse
     */
    TopicMessageResponse sendMessageToTopic(ServerMessage message, String topicName);

    /**
     * Send message to device group
     * @param clientIDs List client token
     * @param message {@link ServerMessage}
     * @return FcmMessageResponse
     */
    FcmMessageResponse sendMessageToDeviceGroup(ServerMessage message, List<String> clientIDs );

}
