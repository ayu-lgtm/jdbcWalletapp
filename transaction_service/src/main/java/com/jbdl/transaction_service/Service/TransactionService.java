package com.jbdl.transaction_service.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbdl.transaction_service.Dto.UserTransactionRequest;
import com.jbdl.transaction_service.Dto.UserTransactionResponse;
import com.jbdl.transaction_service.Entity.Transaction;
import com.jbdl.transaction_service.Repositiory.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository TR;
    private final RestTemplate RT;
    private final ObjectMapper objectMapper;
    public UserTransactionResponse createTransactionRequest(UserTransactionRequest request) {
       Boolean senderExistCheck = RT.getForObject("http://localhost:8081/wallet_users/userExistence/"+request.getSenderId(),Boolean.class);
       if(Boolean.FALSE.equals(senderExistCheck)){
           log.info("Sender with id: "+request.getSenderId()+" Not Exist");
           throw new RuntimeException("Sender with id: "+request.getSenderId()+" Not Exist");
       }

        Boolean receiverExistCheck = RT.getForObject("http://localhost:8081/wallet_users/userExistence/"+request.getReceiverId(),Boolean.class);
        if(Boolean.FALSE.equals(receiverExistCheck)){
            log.info("Receiver with id: "+request.getReceiverId()+" Not Exist");
            throw new RuntimeException("Receiver with id: "+request.getReceiverId()+" Not Exist");
        }

        Double availableBalance = RT.getForObject("http://localhost:8091/wallet-ops/fetchBalance/"+request.getSenderId(),Double.class);
        if(availableBalance < request.getAmount()){
            log.info("Insufficient Amount : "+availableBalance);
            throw new RuntimeException("Insufficient Amount : "+availableBalance);
        }

        Transaction currentTransaction = TR.save();
    }
}
