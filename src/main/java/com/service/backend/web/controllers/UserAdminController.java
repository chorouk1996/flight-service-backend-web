package com.service.backend.web.controllers;


import com.service.backend.web.models.responses.CreateUserResponse;
import com.service.backend.web.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/users")
public class UserAdminController {

    @Autowired
    private IUserService userService;


    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CreateUserResponse> getAllUser(@RequestParam(required = false, defaultValue = "0") int page,@RequestParam(required = false, defaultValue = "10") int size) {
        return userService.getAllUser( page,  size);
    }

    @GetMapping("/{userId}/block")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void blockUser(@PathVariable long userId){
         userService.blockUser(userId);
    }

    @GetMapping("/{userId}/unblock")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void unBlockUser(@PathVariable long userId) {
         userService.unBlockUser(userId);
    }
}
