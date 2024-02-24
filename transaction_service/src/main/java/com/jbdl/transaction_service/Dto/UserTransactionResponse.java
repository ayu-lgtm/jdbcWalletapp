package com.jbdl.transaction_service.Dto;

import com.jbdl.transaction_service.PaymentStatus;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserTransactionResponse {
    private String TransactionId;
    private String message;
    private LocalDateTime paymentInitiationTime;
    private PaymentStatus status;


}
