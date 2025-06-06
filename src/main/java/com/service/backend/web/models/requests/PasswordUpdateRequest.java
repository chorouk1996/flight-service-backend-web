package com.service.backend.web.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "PasswordUpdateRequest", description = "Request body for updating a user's password.")
public class PasswordUpdateRequest {

    @Email
    @NotBlank(message = "email should not be empty")
    @Schema(description = "User's email address", example = "user@example.com", required = true)
    private String email;

    @NotBlank(message = "oldPassword should not be empty")
    @Schema(description = "Current password of the user", example = "CurrentPassword123!", required = true)
    private String oldPassword;

    @NotBlank(message = "newPassword should not be empty")
    @Pattern(
            regexp = "^(?=.*[@#$%!])(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9@#$%!]{8,}$",
            message = "Password must be at least 8 characters long and contain upper/lowercase letters, a digit, and a special character."
    )
    @Schema(
            description = "New password that meets security complexity requirements",
            example = "NewPassword@2024",
            required = true
    )
    private String newPassword;
}