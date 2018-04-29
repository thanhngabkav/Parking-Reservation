package com.atp.webservice.parking_reservation_10.services.messageService.messageServiceImp;

import com.atp.webservice.parking_reservation_10.services.messageService.MessageService;
import com.atp.webservice.parking_reservation_10.services.messageService.models.ServerMessage;
import de.bytefish.fcmjava.client.FcmClient;
import de.bytefish.fcmjava.client.settings.PropertiesBasedSettings;
import de.bytefish.fcmjava.model.options.FcmMessageOptions;
import de.bytefish.fcmjava.model.topics.Topic;
import de.bytefish.fcmjava.requests.data.DataMulticastMessage;
import de.bytefish.fcmjava.requests.data.DataUnicastMessage;
import de.bytefish.fcmjava.requests.topic.TopicUnicastMessage;
import de.bytefish.fcmjava.responses.FcmMessageResponse;
import de.bytefish.fcmjava.responses.TopicMessageResponse;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

@Service
public class MessageServiceImp implements MessageService {

    private static final String FCM_API_URL = "https://gcm-http.googleapis.com/gcm/send";

    private static final String FCM_SERVER_KEY = "AIzaSyDRR_NJbwTOfmnqjrle0kFT1-DufYONl7o";

    private static  final String CONTENT_TYPE = MediaType.APPLICATION_JSON_VALUE;

    private static final long MESSAGE_TIME_TO_LIVE_IN_HOUR =1;

    private static Logger logger = Logger.getLogger(MessageServiceImp.class);


    private Properties getFcmConfigProperties(){
        Properties properties = new Properties();
        properties.put("fcm.api.url", FCM_API_URL);
        properties.put("fcm.api.key",FCM_SERVER_KEY);

        return properties;
    }

    @Override
    public FcmMessageResponse sendMessageToDevice(ServerMessage message, String clientID) {
        FcmClient fcmClient = new FcmClient(PropertiesBasedSettings.createFromProperties(getFcmConfigProperties()));

        FcmMessageOptions options = FcmMessageOptions.builder()
                .setTimeToLive(Duration.ofHours(MESSAGE_TIME_TO_LIVE_IN_HOUR))
                .build();

        DataUnicastMessage personalMessage = new DataUnicastMessage(options, clientID, message);
        FcmMessageResponse response = fcmClient.send(personalMessage);
        logger.debug("Sent message to " +  clientID + ".Response: " + response.toString());
        return response;
    }

    @Override
    public TopicMessageResponse sendMessageToTopic(ServerMessage message, String topicName) {
                FcmClient fcmClient = new FcmClient(PropertiesBasedSettings.createFromProperties(getFcmConfigProperties()));

        FcmMessageOptions options = FcmMessageOptions.builder()
                .setTimeToLive(Duration.ofHours(MESSAGE_TIME_TO_LIVE_IN_HOUR))
                .build();

        Topic topic = new Topic(topicName);
        TopicUnicastMessage unicastMessage = new TopicUnicastMessage(options, topic,message);
        TopicMessageResponse response = fcmClient.send(unicastMessage);
        logger.debug("Sent message to " +  topicName + ".Response: " + response.toString());
        return response;
    }

    @Override
    public FcmMessageResponse sendMessageToDeviceGroup(ServerMessage message, List<String> clientIDs) {
        FcmClient fcmClient = new FcmClient(PropertiesBasedSettings.createFromProperties(getFcmConfigProperties()));

        FcmMessageOptions options = FcmMessageOptions.builder()
                .setTimeToLive(Duration.ofHours(MESSAGE_TIME_TO_LIVE_IN_HOUR))
                .build();

        DataMulticastMessage multicastMessage = new DataMulticastMessage(options, clientIDs, message);
        FcmMessageResponse response = fcmClient.send(multicastMessage);
        logger.debug("Sent message to clients.Response: " + response.toString());
        return response;
    }
}
