package com.jbdl.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
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
@Table(name="wallet_user")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message="User FullNAme should not be blank")
    @Column(nullable = false)
    private String userFullName;

    @Column(unique = true,nullable = false)
    @NotBlank(message="Mobile Number should not be blank")
    private String userMobileNo;

    @Column(unique = true,nullable = false)
    @NotBlank(message="Email Id should not be blank")
    private String userMailId;

    private String userAddress;

    @Column(unique = true)
    private String userPan;

    @CreationTimestamp
    private LocalDateTime createdDate;
    @UpdateTimestamp
    private LocalDateTime updatedDate;
}
