package com.jbdl.wallet_service.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Slf4j
@Table(name="wallet")
public class Wallet {
//    - walletId
//    - userId
//    - Balance
//    - dailyLimit
//    - dailyTransactionLimit
//    - CreatedDate
//    - UpdatedDate
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long walletId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Double balance;

    @Column(nullable = false)
    private Integer dailyLimit;

    @Column(nullable = false)
    private Long dailyTransactionLimit;

    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;


    public Wallet(Long userId) {
        this.userId= userId;
        this.balance=0.0;
        this.dailyLimit=10000;
        this.dailyTransactionLimit= 10L;
    }
}
