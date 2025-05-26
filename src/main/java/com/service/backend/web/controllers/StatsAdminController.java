package com.service.backend.web.controllers;



import com.service.backend.web.models.responses.BookingByMonthResponse;
import com.service.backend.web.models.responses.FlightByAirlineResponse;
import com.service.backend.web.services.interfaces.IBookingService;
import com.service.backend.web.services.interfaces.IFlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/stats")
@AllArgsConstructor
@Tag(name = "Statistics (Admin)", description = "Provides analytical insights for administrators such as flight distribution and booking trends.")
public class StatsAdminController {

    private final IFlightService flightService;
    private final IBookingService bookingService;

    @Operation(
            summary = "Get number of flights per airline",
            description = "Returns a list showing how many flights are operated by each airline."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Statistics retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied — admin only")
    })
    @GetMapping("/flights-by-airline")
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).ADMIN")
    public List<FlightByAirlineResponse> getFlightsByAirline() {
        return flightService.getFlightsByAirline();
    }

    @Operation(
            summary = "Get number of bookings per month",
            description = "Returns the total number of bookings grouped by month for analytics."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Monthly booking statistics retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied — admin only")
    })
    @GetMapping("/bookings-by-month")
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).ADMIN")
    public List<BookingByMonthResponse> getBookingsByMonth() {
        return bookingService.getBookingsByMonth();
    }
}
