package com.service.backend.web.controllers;


import com.service.backend.web.models.requests.CreateUserRequest;
import com.service.backend.web.models.requests.PasswordUpdateRequest;
import com.service.backend.web.models.responses.CreateUserResponse;
import com.service.backend.web.services.interfaces.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;


    @PutMapping("/updatePassword")
    public void updatePassword(@RequestBody @Valid PasswordUpdateRequest user) {
         userService.updatePassword(user);
    }

}
