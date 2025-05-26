package com.service.backend.web.models.requests;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ResetTokenRequest {

    @NotBlank(message = "the email should not be empty")
    private String email;

}
