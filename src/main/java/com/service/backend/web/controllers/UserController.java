package com.service.backend.web.controllers;


import com.service.backend.web.models.requests.PasswordUpdateRequest;
import com.service.backend.web.services.helper.SecurityHelper;
import com.service.backend.web.services.interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
@Tag(name = "User Profile", description = "Endpoints for authenticated users to manage their own profile settings.")
public class UserController {

    private final IUserService userService;

    @Operation(
            summary = "Update user password",
            description = "Allows the authenticated user to update their password. Requires current password and new password."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Password updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid password data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized or invalid credentials"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PutMapping("/password")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).USER")
    public void updatePassword(@RequestBody @Valid PasswordUpdateRequest user) {
        userService.updatePassword(user, SecurityHelper.getUserConnected());
    }
}
