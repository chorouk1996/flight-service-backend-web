package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.SavedPassengerDto;
import com.service.backend.web.models.requests.CreateSavedPassengerRequest;
import com.service.backend.web.models.requests.UpdateSavedPassengerRequest;
import com.service.backend.web.services.helper.SecurityHelper;
import com.service.backend.web.services.interfaces.ISavedPassengerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/passengers")
@AllArgsConstructor
@Tag(name = "Saved Passengers (User)", description = "Endpoints for users to manage their saved passengers for quicker bookings.")
public class PassengerController {

    private final ISavedPassengerService passengerService;

    @Operation(summary = "Add saved passenger", description = "Creates and saves a new passenger under the authenticated user's account.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Passenger created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid passenger data"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PostMapping()
    @PreAuthorize("hasAuthority('USER')")
    public SavedPassengerDto addSavedPassenger(@RequestBody @Valid CreateSavedPassengerRequest passenger) {
        return passengerService.addSavedPassenger(passenger, SecurityHelper.getUserConnected().getUsername());
    }

    @Operation(summary = "List all saved passengers", description = "Retrieves all passengers saved by the authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of saved passengers retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('USER')")
    public List<SavedPassengerDto> getAllSavedPassenger() {
        return passengerService.getAllSavedPassenger(SecurityHelper.getUserConnected().getUsername());
    }

    @Operation(summary = "Get a saved passenger by ID", description = "Retrieves a specific saved passenger belonging to the authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Passenger retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Passenger not found")
    })
    @GetMapping("/{passengerId}")
    @PreAuthorize("hasAuthority('USER')")
    public SavedPassengerDto getSavedPassenger(@PathVariable Long passengerId) {
        return passengerService.getSavedPassengerById(SecurityHelper.getUserConnected().getUsername(), passengerId);
    }

    @Operation(summary = "Update saved passenger", description = "Updates an existing saved passenger for the authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Passenger updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid update data"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PutMapping("/{passengerId}")
    @PreAuthorize("hasAuthority('USER')")
    public SavedPassengerDto updateSavedPassenger(
            @PathVariable Long passengerId,
            @RequestBody @Valid UpdateSavedPassengerRequest passenger) {
        return passengerService.updateSavedPassengerByUser(SecurityHelper.getUserConnected().getUsername(), passenger, passengerId);
    }

    @Operation(summary = "Delete saved passenger", description = "Deletes a saved passenger belonging to the authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Passenger deleted successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Passenger not found")
    })
    @DeleteMapping("/{passengerId}")
    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSavedPassenger(@PathVariable Long passengerId) {
        passengerService.deleteSavedPassengerByUser(SecurityHelper.getUserConnected().getUsername(), passengerId);
    }
}