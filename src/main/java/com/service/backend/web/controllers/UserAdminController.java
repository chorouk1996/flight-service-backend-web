package com.service.backend.web.controllers;


import com.service.backend.web.models.responses.CreateUserResponse;
import com.service.backend.web.models.responses.UserPaginationResponse;
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
    public UserPaginationResponse getAllUser(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "10") int size) {
        return userService.getAllUsers( page,  size);
    }

    @PutMapping("/{userId}/block")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void blockUser(@PathVariable long userId){
         userService.blockUser(userId);
    }

    @PutMapping("/{userId}/unblock")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void unBlockUser(@PathVariable long userId) {
         userService.unBlockUser(userId);
    }
}
