package com.jbdl.Dto;

import com.jbdl.Model.User;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
@Builder
public class UserResponseDto {
    private String message;
    private User user;
}
