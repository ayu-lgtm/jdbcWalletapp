package com.jbdl.wallet_service.Config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbdl.jbdl_wallet_library.Constants.TopicConstants;
import com.jbdl.wallet_service.Service.WalletOperationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class KafkaConfig {

    private final ObjectMapper objectMapper;
    private final WalletOperationService WS;

    @KafkaListener(topics = TopicConstants.USER_CREATION_TOPIC, groupId = "user_service.walletCreation")
    public void pollMessagesForUserCreation(ConsumerRecord receivedMessage) throws JsonProcessingException {

        Map<String,Object> receivedData =  objectMapper.readValue(receivedMessage.value().toString(), Map.class);

        String userId= String.valueOf(receivedData.get("userId"));
        log.info("ReceivedData: {}", userId.getClass());
        //log.info("ReceivedData: {}", receivedData.get("user_id"));
        WS.createWalletForNewUser(receivedData);

    }
}
