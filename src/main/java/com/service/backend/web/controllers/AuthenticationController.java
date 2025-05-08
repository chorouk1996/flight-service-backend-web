package com.service.backend.web.controllers;

import com.service.backend.web.models.requests.AuthentUserRequest;
import com.service.backend.web.models.requests.ResetTokenRequest;
import com.service.backend.web.models.responses.AuthenticationResponse;
import com.service.backend.web.services.interfaces.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public AuthenticationResponse loginUser(@RequestBody @Valid AuthentUserRequest user) throws NoSuchAlgorithmException {
        return new AuthenticationResponse(userService.authenticate(user));
    }

    @PostMapping("/refresh")
    public AuthenticationResponse refreshToken(@RequestBody AuthenticationResponse token) {
        return new AuthenticationResponse(userService.refreshToken(token));
    }

    @PostMapping("/request-reset")
    public void requestResetToken(@RequestBody ResetTokenRequest tokenRequest, HttpServletRequest request) {
        userService.resetToken(tokenRequest,request);
    }
}
