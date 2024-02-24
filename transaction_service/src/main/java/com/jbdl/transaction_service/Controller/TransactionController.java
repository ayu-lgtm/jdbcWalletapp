package com.jbdl.transaction_service.Controller;

import com.jbdl.transaction_service.Dto.UserTransactionRequest;
import com.jbdl.transaction_service.Dto.UserTransactionResponse;
import com.jbdl.transaction_service.Service.TransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final TransactionService TS;
    public ResponseEntity<UserTransactionResponse> createTransactionRequest(@RequestBody UserTransactionRequest request){


            return new ResponseEntity<>(TS.createTransactionRequest(request), HttpStatus.ACCEPTED);

    }
}
