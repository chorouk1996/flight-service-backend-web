package com.service.backend.web.models.responses;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

import lombok.Data;

@Data
@AllArgsConstructor
public class RefreshTokenResponse {

    @NotBlank
    private String token;

}
