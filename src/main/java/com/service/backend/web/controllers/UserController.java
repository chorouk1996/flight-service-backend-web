package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.requests.CreateUserRequest;
import com.service.backend.web.models.dto.responses.CreateUserResponse;
import com.service.backend.web.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CreateUserResponse> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public CreateUserResponse addUser(@RequestBody CreateUserRequest user) {
        return userService.addUser(user);
    }

}
