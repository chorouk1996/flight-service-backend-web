package com.service.backend.web.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "ResetPasswordRequest", description = "Request object for resetting a user's password using a valid reset token.")
public class ResetPasswordRequest {

    @NotBlank(message = "the token should not be empty")
    @Schema(description = "Password reset token sent to the user's email", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...", required = true)
    private String token;

    @NotBlank(message = "newPassword should not be empty")
    @Pattern(
            regexp = "^(?=.*[@#$%!])(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z0-9@#$%!]{8,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, one special character (@#$%!), and be at least 8 characters long"

    )
    @Schema(
            description = "New password meeting complexity requirements",
            example = "ResetPassword@2024",
            required = true
    )
    private String newPassword;
}