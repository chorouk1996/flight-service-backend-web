package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.UserDto;
import com.service.backend.web.models.dto.requests.AuthentUserRequest;
import com.service.backend.web.models.dto.requests.CreateUserRequest;
import com.service.backend.web.models.dto.responses.CreateUserResponse;
import com.service.backend.web.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    UserDto user = new UserDto();
    @Autowired
    private IUserService userService ;


    @PostMapping("/login")
    public String loginUser(@RequestBody AuthentUserRequest user) throws NoSuchAlgorithmException {
        return userService.authenticate(user);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<CreateUserResponse> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping()
    public CreateUserResponse addUser(@RequestBody CreateUserRequest user){
        return userService.addUser(user);
    }

    @PutMapping()
    public UserDto updateUser(@RequestBody UserDto user){
        return user;
    }
}
