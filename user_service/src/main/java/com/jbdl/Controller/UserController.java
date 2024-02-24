package com.jbdl.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jbdl.Dto.UserResponseDto;
import com.jbdl.Model.User;
import com.jbdl.Service.UserOperationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet_users")
@Slf4j
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserOperationService US;

    @PostMapping
    public ResponseEntity<UserResponseDto> createNewUser(@RequestBody @Valid User user){
        try {
            return new ResponseEntity<>(US.createUserAccount(user), HttpStatus.CREATED);
        }catch (RuntimeException e){
            log.error(String.format("Runtime Exception occurred for userId : %d Exception is : %s",user.getUserId(),e.getMessage()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    @GetMapping("/userExistence/{userId}")
    public ResponseEntity<Boolean> checkUserExistence(@PathVariable Long userId){
        return new ResponseEntity<>(US.checkUserExistence(userId),HttpStatus.OK);
    }

}
