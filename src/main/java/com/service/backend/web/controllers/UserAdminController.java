package com.service.backend.web.controllers;


import com.service.backend.web.models.requests.CreateUserRequest;
import com.service.backend.web.models.responses.CreateUserResponse;
import com.service.backend.web.models.responses.UserPaginationResponse;
import com.service.backend.web.services.interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/users")
@AllArgsConstructor
@Validated
@Tag(name = "User Management (Admin)", description = "Admin-only endpoints to manage platform users including creation, blocking, and listing.")
public class UserAdminController {

    private final IUserService userService;

    @Operation(summary = "Get all users (non-paginated)", description = "Returns a complete list of all users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied — admin only")
    })
    @GetMapping("/all")
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).ADMIN")
    public List<CreateUserResponse> getAllUser() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Create a new user", description = "Adds a new user to the system with the specified details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user data"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PostMapping()
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).ADMIN")
    public CreateUserResponse addUser(@RequestBody @Valid CreateUserRequest user) {
        return userService.addUser(user);
    }

    @Operation(summary = "Get paginated users", description = "Returns a paginated list of users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paginated users retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).ADMIN")
    public UserPaginationResponse getAllUser(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") @Max(100) int size) {
        return userService.getAllUsers(page, size);
    }

    @Operation(summary = "Block user", description = "Disables a user's account. The user will not be able to log in.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User blocked successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{userId}/block")
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void blockUser(@PathVariable long userId) {
        userService.blockUser(userId);
    }

    @Operation(summary = "Unblock user", description = "Re-enables a previously blocked user account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User unblocked successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{userId}/unblock")
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unBlockUser(@PathVariable long userId) {
        userService.unBlockUser(userId);
    }
}