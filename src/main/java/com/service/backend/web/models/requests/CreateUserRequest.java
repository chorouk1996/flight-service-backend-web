package com.service.backend.web.models.requests;

import com.service.backend.web.models.enumerators.RoleEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class CreateUserRequest {


    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Email @NotBlank
    private String email;

    @Size(min = 8) @NotBlank
    private String password;

    private RoleEnum role;


}
