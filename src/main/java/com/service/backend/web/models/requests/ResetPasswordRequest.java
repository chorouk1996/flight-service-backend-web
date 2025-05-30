package com.service.backend.web.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
public class ResetPasswordRequest {

    @NotBlank(message = "the token should not be empty")
    private String token;

    @NotBlank(message = "newPassword should not be empty")
    @Pattern(regexp = "^(?=.*[@#$%!])(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z0-9@#$%!]{8,}$")
    private String newPassword;



}
