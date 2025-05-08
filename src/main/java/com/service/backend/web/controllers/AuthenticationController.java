package com.service.backend.web.controllers;

import com.service.backend.web.models.requests.AuthentUserRequest;
import com.service.backend.web.models.requests.ResetPasswordRequest;
import com.service.backend.web.models.requests.ResetTokenRequest;
import com.service.backend.web.models.responses.AuthenticationResponse;
import com.service.backend.web.models.responses.ResetTokenResponse;
import com.service.backend.web.services.interfaces.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public AuthenticationResponse loginUser(@RequestBody @Valid AuthentUserRequest user) {
        return new AuthenticationResponse(userService.authenticate(user));
    }

    @PostMapping("/refresh")
    public AuthenticationResponse refreshToken(@RequestBody AuthenticationResponse token) {
        return new AuthenticationResponse(userService.refreshToken(token));
    }

    @PostMapping("/request-reset")
    public ResetTokenResponse requestResetToken(@RequestBody ResetTokenRequest tokenRequest, HttpServletRequest request) {
        userService.resetToken(tokenRequest, request);
        return new ResetTokenResponse("If an active account with this email exists, a reset link has been sent.");
    }

    @PostMapping("/reset-password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        userService.resetPassword(resetPasswordRequest);
    }
}
