package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.FlightDto;
import com.service.backend.web.models.requests.CreateFlightRequest;
import com.service.backend.web.models.requests.SearchFlightRequest;
import com.service.backend.web.models.requests.UpdateFlightRequest;
import com.service.backend.web.models.requests.UpdateFlightStatusRequest;
import com.service.backend.web.models.responses.CreateFlightResponse;
import com.service.backend.web.services.helper.SecurityHelper;
import com.service.backend.web.services.interfaces.IFlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/flight")
@AllArgsConstructor
@Tag(name = "Flight Management (Admin)", description = "Administrative actions for creating, updating, cancelling, and searching flights.")
public class FlightAdminController {

    private final IFlightService flightService;

    private final static Logger LOGGER = LoggerFactory.getLogger(FlightAdminController.class);
    @Operation(summary = "Create a new flight", description = "Adds a new flight to the system. Requires ADMIN authority.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Flight created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid flight data"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PostMapping
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).ADMIN")
    public CreateFlightResponse addFlight(@RequestBody @Valid CreateFlightRequest flight) {
        return flightService.addFlight(flight);
    }

    @Operation(summary = "Update a flight", description = "Updates flight details by ID. Requires ADMIN authority.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid update data"),
            @ApiResponse(responseCode = "404", description = "Flight not found"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PutMapping("/{flightId}")
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).ADMIN")
    public CreateFlightResponse updateFlight(
            @PathVariable Long flightId,
            @RequestBody @Valid UpdateFlightRequest flight) {
        return flightService.updateFlight(flightId, flight);
    }

    @Operation(summary = "Update flight status", description = "Updates the status (e.g., CANCELLED, DEPARTED) of a flight. Requires ADMIN authority.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight status updated"),
            @ApiResponse(responseCode = "400", description = "Invalid status value"),
            @ApiResponse(responseCode = "404", description = "Flight not found"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PutMapping("/{flightId}/status")
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).ADMIN")
    public CreateFlightResponse updateFlightStatus(
            @PathVariable Long flightId,
            @RequestBody @Valid UpdateFlightStatusRequest request) {

        LOGGER.info("Admin {} updated flight {}  status to {}", SecurityHelper.getUserConnected().getUsername(), flightId,request.getStatus());
        return flightService.updateFlightStatus(flightId, request);
    }

    @Operation(summary = "Delete (cancel) a flight", description = "Cancels an existing flight. Requires ADMIN authority.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flight cancelled successfully"),
            @ApiResponse(responseCode = "404", description = "Flight not found"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @DeleteMapping("/{flightId}")
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFlight(@PathVariable Long flightId)
    {
        LOGGER.info("Admin {} cancelled flight {}", SecurityHelper.getUserConnected().getUsername(), flightId);
        flightService.cancelFlight(flightId);
    }

    @Operation(summary = "Search flights (admin)", description = "Searches for flights using flexible admin-side filters.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Flights found"),
            @ApiResponse(responseCode = "400", description = "Invalid search parameters")
    })
    @PostMapping("/search")
    public List<FlightDto> searchFlight(@RequestBody @Valid SearchFlightRequest searchCriteria) {
        return flightService.adminSearchFlight(searchCriteria);
    }
}