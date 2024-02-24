package com.jbdl.wallet_service.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbdl.jbdl_wallet_library.Constants.TopicConstants;
import com.jbdl.notification_service.Dto.SendMailNotification;
import com.jbdl.notification_service.Enums.ServiceType;
import com.jbdl.wallet_service.Model.Wallet;
import com.jbdl.wallet_service.Repository.WalletOperationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.Future;

@Service
@Slf4j
@RequiredArgsConstructor
public class WalletOperationService {
    private final WalletOperationRepository WR;

    private final KafkaTemplate<String ,String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    public void createWalletForNewUser(Map<String, Object> receivedData) throws JsonProcessingException {
        Long UserId= Long.parseLong((String) receivedData.get("userId"));
        Wallet newWallet = new Wallet(UserId);
        log.info("New Wallet details for user id : {} are Wallet : {}", UserId, newWallet);
        Wallet newCreatedWallet = WR.save(newWallet);
        log.info("New Wallet details for user id : {} are Wallet : {}", UserId, newCreatedWallet);

        SendMailNotification sendMailNotification = SendMailNotification.builder()
                .receiverMailId("Ayush1910094@akgec.ac.in.com")
                .message("Hi Your Paytm Wallet Account Has been created successfully." +
                        "Your daily Transaction limit is 10 daily money transfer limit Rs. 10000." +
                        "You can update this limit anytime whenever you want.Thank You! "
                        +receivedData.get("userName"))
                .serviceType(ServiceType.WALLET_SERVICE)
                .subject("New Paytm account Creation Confirm Message.")
                .build();

        Future<SendResult<String,String>> send = kafkaTemplate
                .send(TopicConstants
                        .SEND_NOTIFICATION_TOPIC, newCreatedWallet.getUserId().toString(),objectMapper
                        .writeValueAsString(sendMailNotification));



    }


    public Double fetchBalance(Long userId) {
       Wallet wallet = WR.findByUserId(userId);
       return wallet.getBalance();
    }
}



