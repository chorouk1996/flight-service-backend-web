package com.service.backend.web.controllers;

import com.service.backend.web.exceptions.FunctionalException;
import com.service.backend.web.exceptions.FunctionalExceptionDto;
import com.service.backend.web.models.dto.requests.AuthentUserRequest;
import com.service.backend.web.models.dto.responses.AuthenticationResponse;
import com.service.backend.web.services.interfaces.IUserService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private IUserService userService;

    @PostMapping("")
    public AuthenticationResponse loginUser(@RequestBody AuthentUserRequest user) throws NoSuchAlgorithmException {
        return new AuthenticationResponse(userService.authenticate(user));
    }

    @PostMapping("/refresh")
    public AuthenticationResponse refreshToken(@RequestBody AuthenticationResponse token) {
        return new AuthenticationResponse(userService.refreshToken(token));
    }
}
