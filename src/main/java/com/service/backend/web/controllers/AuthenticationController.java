package com.service.backend.web.controllers;

import com.service.backend.web.models.requests.AuthentUserRequest;
import com.service.backend.web.models.requests.ResetPasswordRequest;
import com.service.backend.web.models.requests.ResetTokenRequest;
import com.service.backend.web.models.responses.AuthenticationResponse;
import com.service.backend.web.models.responses.ResetTokenResponse;
import com.service.backend.web.services.interfaces.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final IUserService userService;

    @PostMapping("/login")
    public AuthenticationResponse loginUser(@RequestBody @Valid AuthentUserRequest user) {
        return new AuthenticationResponse(userService.authenticate(user));
    }

    @DeleteMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logoutUser() {
        userService.logout();
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
    public ResetTokenResponse resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        userService.resetPassword(resetPasswordRequest);
        return new ResetTokenResponse("If an active account with this email exists, the password will be changed.");

    }
}
