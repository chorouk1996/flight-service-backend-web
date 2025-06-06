package com.service.backend.web.models.responses;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@AllArgsConstructor
@Schema(name = "AuthenticationResponse", description = "Response containing access and refresh tokens after successful login.")
public class AuthenticationResponse {

    @NotBlank
    @Schema(description = "JWT access token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    @NotBlank
    @Schema(description = "Refresh token to obtain new access tokens", example = "dGhpcy1pcy1hLXJlZnJlc2gtdG9rZW4tZXhhbXBsZQ==")
    private String refreshToken;
}