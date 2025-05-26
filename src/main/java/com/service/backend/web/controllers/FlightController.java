package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.FlightDto;
import com.service.backend.web.models.requests.SearchFlightRequest;
import com.service.backend.web.services.interfaces.IFlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/flight")
@AllArgsConstructor
@Tag(name = "Flight (User)", description = "Endpoints for users to browse and search available flights.")
public class FlightController {

    private final IFlightService flightService;

    @Operation(summary = "Get flight by ID", description = "Retrieves details of a specific flight by its ID. Requires user authentication.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight details retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Flight not found")
    })
    @GetMapping("/{flightId}")
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).USER")
    public ResponseEntity<FlightDto> getFlight(@PathVariable Long flightId) {
        return new ResponseEntity<>(flightService.getFlight(flightId), HttpStatus.OK);
    }

    @Operation(summary = "List all flights", description = "Returns all available flights. Requires user authentication.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of flights retrieved"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/all")
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).USER")
    public ResponseEntity<List<FlightDto>> getAllFlight() {
        return new ResponseEntity<>(flightService.getAllFlight(), HttpStatus.OK);
    }

    @Operation(summary = "Search flights", description = "Search for flights based on departure, destination, date, and other filters. Requires user authentication.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Matching flights retrieved"),
            @ApiResponse(responseCode = "400", description = "Invalid search parameters"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PostMapping("/search")
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).USER")
    public List<FlightDto> searchFlight(@RequestBody @Valid SearchFlightRequest searchCriteria) {
        return flightService.userSearchFlight(searchCriteria);
    }
}