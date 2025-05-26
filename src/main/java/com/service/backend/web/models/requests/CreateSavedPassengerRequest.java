package com.service.backend.web.models.requests;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateSavedPassengerRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Min(0)
    @Max(120)
    private Integer age;

    @Email
    @NotBlank
    private String email;


}
