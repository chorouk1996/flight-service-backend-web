package com.service.backend.web.controllers;

import com.service.backend.web.exceptions.FunctionalException;
import com.service.backend.web.exceptions.FunctionalExceptionDto;
import com.service.backend.web.models.requests.AuthentUserRequest;
import com.service.backend.web.models.requests.ResetPasswordRequest;
import com.service.backend.web.models.requests.ResetTokenRequest;
import com.service.backend.web.models.responses.AuthenticationResponse;
import com.service.backend.web.models.responses.RefreshTokenResponse;
import com.service.backend.web.models.responses.ResetTokenResponse;
import com.service.backend.web.services.interfaces.IUserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;


@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final IUserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping("/login")
    public ResponseEntity<RefreshTokenResponse> loginUser(@RequestBody @Valid AuthentUserRequest user) {
        LOGGER.info("Login attempt for user: {}", user.getEmail());
        AuthenticationResponse authenticationResponse = userService.authenticate(user);
        LOGGER.info("Login successful for user: {}", user.getEmail());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, setCookies(authenticationResponse.getRefreshToken(), Duration.ofDays(30)))
                .body(new RefreshTokenResponse(authenticationResponse.getToken()));
    }

    @DeleteMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> logoutUser(HttpServletRequest request) {
        String token = getCookie(request);
        LOGGER.info("Logout requested for token: {}", token);
        userService.logout(token);
        LOGGER.info("Logout successful for token: {}", token);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, setCookies("", Duration.ofSeconds(0)))
                .build();
    }

    @PostMapping("/refresh")
    public RefreshTokenResponse refreshToken(HttpServletRequest request) {
        String token = getCookie(request);
        LOGGER.info("Token refresh requested for token: {}", token);
        RefreshTokenResponse response = userService.refreshToken(token);
        LOGGER.info("Access token successfully refreshed");
        return response;
    }

    @PostMapping("/request-reset")
    public ResetTokenResponse requestResetToken(@RequestBody ResetTokenRequest tokenRequest, HttpServletRequest request) {
        LOGGER.info("Password reset requested for email: {}", tokenRequest.getEmail());
        userService.resetToken(tokenRequest, request);
        return new ResetTokenResponse("If an active account with this email exists, a reset link has been sent.");
    }

    @PostMapping("/reset-password")
    public ResetTokenResponse resetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {
        userService.resetPassword(resetPasswordRequest);
        return new ResetTokenResponse("If an active account with this email exists, the password will be changed.");

    }

    private String setCookies(String value, Duration duration) {
        return ResponseCookie.from("refresh_token", value)
                .httpOnly(true)
                .secure(true)
                .maxAge(duration)
                .sameSite("Strict")
                .build().toString();
    }

    private String getCookie(HttpServletRequest request) {
        Optional<Cookie> cookie = Arrays.stream(request.getCookies())
                .filter(c -> "refresh_token".equals(c.getName()))
                .findFirst();

        return cookie.orElseThrow(() -> {
            LOGGER.warn("Missing refresh token in cookie");
            throw new FunctionalException(
                    new FunctionalExceptionDto("the refresh token is mandatory", HttpStatus.BAD_REQUEST)
            );
        }).getValue();
    }
}
