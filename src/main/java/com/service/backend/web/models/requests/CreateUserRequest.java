package com.service.backend.web.models.requests;

import com.service.backend.web.models.enumerators.RoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
@Schema(name = "CreateUserRequest", description = "DTO used to register a new user in the system")
public class CreateUserRequest {

    @NotBlank
    @Schema(description = "User's first name", example = "Alice", required = true)
    private String firstName;

    @NotBlank
    @Schema(description = "User's last name", example = "Johnson", required = true)
    private String lastName;

    @Email
    @NotBlank
    @Schema(description = "User's email address", example = "alice@example.com", required = true)
    private String email;

    @Size(min = 8)
    @NotBlank
    @Schema(description = "User's password (minimum 8 characters)", example = "StrongPass123", required = true)
    private String password;

    @Schema(description = "User role (e.g., ADMIN or USER)", example = "USER")
    private RoleEnum role;
}
