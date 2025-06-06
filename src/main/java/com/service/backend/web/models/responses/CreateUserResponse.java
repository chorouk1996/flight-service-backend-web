package com.service.backend.web.models.responses;



import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "CreateUserResponse", description = "Response DTO returned after creating a new user.")
public class CreateUserResponse {

    @Schema(description = "Unique identifier of the created user", example = "101")
    private Long id;

    @Schema(description = "First name of the user", example = "Chorouk")
    private String firstName;

    @Schema(description = "Last name of the user", example = "Amahri")
    private String lastName;

    @Schema(description = "Email address of the user", example = "chorouk.amahri@example.com")
    private String email;

    @Schema(description = "Role assigned to the user", example = "ADMIN")
    private String role;
}
