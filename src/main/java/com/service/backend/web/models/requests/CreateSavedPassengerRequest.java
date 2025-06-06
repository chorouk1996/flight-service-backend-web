package com.service.backend.web.models.requests;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;
@Data
@Schema(name = "CreateSavedPassengerRequest", description = "DTO to save a passenger to the user's profile for future bookings.")
public class CreateSavedPassengerRequest {

    @NotBlank
    @Schema(description = "Passenger's first name", example = "John", required = true)
    private String firstName;

    @NotBlank
    @Schema(description = "Passenger's last name", example = "Doe", required = true)
    private String lastName;

    @Min(0)
    @Max(120)
    @Schema(description = "Passenger's age in years", example = "28", required = true)
    private Integer age;

    @Email
    @NotBlank
    @Schema(description = "Passenger's email address", example = "john.doe@example.com", required = true)
    private String email;
}
