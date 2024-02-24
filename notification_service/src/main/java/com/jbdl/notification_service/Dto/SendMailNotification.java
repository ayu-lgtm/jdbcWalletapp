package com.jbdl.notification_service.Dto;

import com.jbdl.notification_service.Enums.ServiceType;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SendMailNotification {
    private String receiverMailId;
    private String message;
    private String subject;
    private ServiceType serviceType;
}
