package com.service.backend.web.models.responses;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;


@Data
@AllArgsConstructor
@Schema(name = "RefreshTokenResponse", description = "Response containing a new access token after a successful token refresh.")
public class RefreshTokenResponse {

    @NotBlank
    @Schema(description = "Newly generated JWT access token", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;
}