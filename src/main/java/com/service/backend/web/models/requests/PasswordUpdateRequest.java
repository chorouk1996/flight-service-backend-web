package com.service.backend.web.models.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PasswordUpdateRequest {

    @Email
    @NotBlank(message = "email should not be empty")
    private String email;
    @NotBlank(message = "oldPassword should not be empty")
    private String oldPassword;
    @NotBlank(message = "newPassword should not be empty")
    @Pattern(regexp = "^(?=.*[@#$%!])(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9@#$%!]{8,}$")
    private String newPassword;


}
