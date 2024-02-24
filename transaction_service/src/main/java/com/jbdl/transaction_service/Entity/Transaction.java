package com.jbdl.transaction_service.Entity;

import com.jbdl.transaction_service.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Slf4j
@Table(name="wallet_transaction")
public class Transaction {
//    - transactionId
//    - walletId
//    - senderId
//    - receiverId
//    - amount - > 0
//            - paymentStatus - SUCCESS, PENDING, FAILED
//    - remark
//    - paymentDate

    @Id
    @UuidGenerator
    private String transactionId;

    @Column(nullable=false)
    private Long walletId;

    @Column(nullable=false)
    private Long senderId;

    @Column(nullable=false)
    private Long receiverId;

    @Column(nullable=false)
    private Double amount;

    @Column(nullable=false)
    private PaymentStatus paymentStatus;


    private String remarks;

    @CreationTimestamp
    private LocalDateTime paymentInitiationDate;

    @UpdateTimestamp
    private LocalDateTime paymentSuccessfulDate;






}
