package com.service.backend.web.controllers;


import com.service.backend.web.models.requests.PasswordUpdateRequest;
import com.service.backend.web.services.helper.SecurityHelper;
import com.service.backend.web.services.interfaces.IUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final IUserService userService;


    @PutMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('USER')")
    public void updatePassword(@RequestBody @Valid PasswordUpdateRequest user) {
         userService.updatePassword(user, SecurityHelper.getUserConnected());
    }


}
