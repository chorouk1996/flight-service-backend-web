package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    UserDto user = new UserDto();
    @GetMapping("/:id")
    public ResponseEntity<UserDto> getUser(@RequestParam Long id){
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUser(){
        return new ResponseEntity<>(List.of(user), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto user){
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user){
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
