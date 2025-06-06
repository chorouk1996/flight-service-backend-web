package com.service.backend.web.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(name = "ResetTokenRequest", description = "Request object to initiate the password reset process by providing an email.")
public class ResetTokenRequest {

    @NotBlank(message = "the email should not be empty")
    @Schema(description = "Email address associated with the user's account", example = "user@example.com", required = true)
    private String email;
}