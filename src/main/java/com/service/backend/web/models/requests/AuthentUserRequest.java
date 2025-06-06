package com.service.backend.web.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;


@Data
@Schema(name = "AuthentUserRequest", description = "Request object for user authentication (login).")
public class AuthentUserRequest {

    @NotBlank(message = "Email must not be blank or empty")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Please insert a valid email address"
    )
    @Schema(description = "User email address", example = "user@example.com", required = true)
    private String email;

    @NotBlank(message = "Password must not be blank or empty")
    @Schema(description = "User password", example = "MySecurePassword123", required = true)
    private String password;
}