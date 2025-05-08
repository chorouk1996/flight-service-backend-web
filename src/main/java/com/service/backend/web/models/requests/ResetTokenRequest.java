package com.service.backend.web.models.requests;

import jakarta.validation.constraints.NotBlank;

public class ResetTokenRequest {

    @NotBlank(message = "the email should not be empty")
    private String email;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
