package com.service.backend.web.models.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
public class AuthentUserRequest {


    @NotBlank(message = "Email must not be blank or empty")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "Please insert a valid email address"
    )    private String email;

    @NotBlank(message = "Password must not be blank or empty")
    private String password;




}
