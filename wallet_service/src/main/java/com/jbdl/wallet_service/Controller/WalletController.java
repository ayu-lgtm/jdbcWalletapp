package com.jbdl.wallet_service.Controller;

import com.jbdl.wallet_service.Service.WalletOperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet-ops")
@RequiredArgsConstructor
public class WalletController {
    private final WalletOperationService walletOperationService;


    @GetMapping("/fetchBalance/{userId}")
    public ResponseEntity<Double> getBalance(@PathVariable Long userId){
        return new ResponseEntity<>(walletOperationService.fetchBalance(userId), HttpStatus.OK);
    }
}
