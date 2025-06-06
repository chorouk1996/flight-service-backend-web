package com.service.backend.web.models.requests;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "UpdateSavedPassengerRequest", description = "DTO to update an existing saved passenger.")
public class UpdateSavedPassengerRequest {

    @Schema(description = "Passenger's new first name", example = "Alice")
    private String firstName;

    @Schema(description = "Passenger's new last name", example = "Johnson")
    private String lastName;

    @Schema(description = "Passenger's updated age", example = "35")
    private Integer age;

    @Schema(description = "Passenger's new email address", example = "alice.johnson@example.com")
    private String email;
}