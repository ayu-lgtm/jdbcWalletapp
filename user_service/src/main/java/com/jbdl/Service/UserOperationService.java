package com.jbdl.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbdl.Dto.UserResponseDto;
import com.jbdl.Model.User;
import com.jbdl.Repository.UserOperationRepository;
import com.jbdl.jbdl_wallet_library.Constants.TopicConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.Future;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserOperationService{

    private final UserOperationRepository UR;

    private final KafkaTemplate<String ,String> kafkaTemplate;

    private final ObjectMapper objectMapper;
    public UserResponseDto createUserAccount(User user) throws JsonProcessingException {
        User savedUser = UR.save(user);

        String msg = String.format("User Account is created for UserId : %d and UserName : %s",savedUser.getUserId(),savedUser.getUserFullName());

        log.info(msg);

        Map<String,String> walletCreationRequest = Map.of("userId",savedUser.getUserId().toString(),"userName",savedUser.getUserFullName());

        Future<SendResult<String,String>> send = kafkaTemplate
                .send(TopicConstants
                        .USER_CREATION_TOPIC, savedUser.getUserId().toString(),objectMapper
                        .writeValueAsString(walletCreationRequest));


        return UserResponseDto
                .builder()
                .message("User Account is Created ,Wallet Creation is in Progress,we will notify yo over mail once done...")
                .user(savedUser)
                .build();
    }

    public Boolean checkUserExistence(Long userId) {
        return UR.existsById(userId);
    }
}
