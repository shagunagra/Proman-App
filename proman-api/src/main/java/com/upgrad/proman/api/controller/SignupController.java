package com.upgrad.proman.api.controller;

import com.upgrad.proman.api.model.SignupUserRequest;
import com.upgrad.proman.api.model.SignupUserResponse;
import com.upgrad.proman.service.business.SignupBusinessService;
import com.upgrad.proman.service.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class SignupController {

    @Autowired
    private SignupBusinessService signupBusinessService;

    // To create Response :
    // Response Body
    // Response Code
    @RequestMapping(method= RequestMethod.POST, path="/signup", consumes= MediaType.APPLICATION_JSON_UTF8_VALUE, produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SignupUserResponse> signup(final SignupUserRequest signupUserRequest){

        final UserEntity userEntity = new UserEntity();
        userEntity.setUuid(UUID.randomUUID().toString());
        userEntity.setFirstName(signupUserRequest.getFirstName());
        userEntity.setLastName(signupUserRequest.getLastName());
        userEntity.setEmail(signupUserRequest.getEmailAddress());
        userEntity.setPassword(signupUserRequest.getPassword());
        userEntity.setMobilePhone(signupUserRequest.getMobileNumber());
        userEntity.setSalt("1234xyz");
        // 1 - Created 2 - Active 3- Inactive 4 - Registered
        userEntity.setStatus(4);
        userEntity.setCreatedAt(ZonedDateTime.now());
        userEntity.setCreatedBy("proman-backend-api");


        final UserEntity createdUserEntity= signupBusinessService.signup(userEntity);

        SignupUserResponse userResponse = new SignupUserResponse();
        userResponse.id(createdUserEntity.getUuid());
        userResponse.status("REGISTERED");

        return new ResponseEntity<SignupUserResponse>(userResponse, HttpStatus.CREATED);
    }

}
