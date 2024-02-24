package com.jbdl.notification_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbdl.jbdl_wallet_library.Constants.TopicConstants;
import com.jbdl.notification_service.Dto.SendMailNotification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {

    private final ObjectMapper objectMapper;
    private final SendMail sendMail;

    @KafkaListener(topics = TopicConstants.SEND_NOTIFICATION_TOPIC, groupId = "send_notification")
    public void sendNotification(ConsumerRecord receivedRecord) throws JsonProcessingException {
        SendMailNotification d= objectMapper.readValue(receivedRecord.value().toString(),SendMailNotification.class);
        log.info(String.format("Received Notification from Service: %s with Message: %s", d.getServiceType().name(), d.toString()));
        sendMail.sendMail(d);
    }
}
