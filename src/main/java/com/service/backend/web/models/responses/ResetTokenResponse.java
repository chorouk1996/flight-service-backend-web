package com.service.backend.web.models.responses;


import lombok.AllArgsConstructor;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "ResetTokenResponse", description = "Response returned after requesting or completing a password reset.")
public class ResetTokenResponse {

    @Schema(description = "Informational message regarding the reset token action", example = "If an active account with this email exists, a reset link has been sent.")
    private String message;
}