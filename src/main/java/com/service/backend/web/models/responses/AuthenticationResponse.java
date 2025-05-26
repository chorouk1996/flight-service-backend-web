package com.service.backend.web.models.responses;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {

    @NotBlank
    private String token;

    @NotBlank
    private String refreshToken;
}
