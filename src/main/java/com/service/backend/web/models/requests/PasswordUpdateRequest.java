package com.service.backend.web.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class PasswordUpdateRequest {

    @Email
    @NotBlank(message = "email should not be empty")
    private String email;
    @NotBlank(message = "oldPassword should not be empty")
    private String oldPassword;
    @NotBlank(message = "newPassword should not be empty")
    @Pattern(regexp = "^(?=.*[@#$%!])(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9@#$%!]{8,}$")
    private String newPassword;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
