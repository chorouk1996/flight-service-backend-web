package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.models.requests.CreateBookingRequest;
import com.service.backend.web.models.responses.CreateBookingResponse;
import com.service.backend.web.models.responses.MyBookingResponse;
import com.service.backend.web.services.helper.SecurityHelper;
import com.service.backend.web.services.interfaces.IBookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/booking")
@AllArgsConstructor
@Validated
@Tag(name = "Booking (User)", description = "Endpoints for regular users to manage their own bookings.")
public class BookingController {

    private final IBookingService bookingService;

    @Operation(summary = "Get booking by ID", description = "Retrieves a specific booking by its ID. Only accessible to users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Booking not found")
    })
    @GetMapping("/{bookingId}")
    @PreAuthorize("hasAuthority('USER')")
    public BookingDto getBooking(@PathVariable Long bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    @Operation(summary = "List all my bookings", description = "Returns a paginated list of bookings made by the currently authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bookings retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/my-bookings")
    @PreAuthorize("hasAuthority('USER')")
    public List<MyBookingResponse> getMyBookings(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") @Max(100) int size) {
        return bookingService.getAllBooking(SecurityHelper.getUserConnected().getUsername(), page, size);
    }

    @Operation(summary = "Create a new booking", description = "Creates a new booking for the currently authenticated user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Booking created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PostMapping()
    @PreAuthorize("hasAuthority('USER')")
    public CreateBookingResponse addBooking(@RequestBody @Valid CreateBookingRequest booking) {
        return bookingService.addBooking(booking, SecurityHelper.getUserConnected().getUsername());
    }

    @Operation(summary = "Update a booking", description = "Updates an existing booking. Only editable by the user who made the booking.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PutMapping()
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<BookingDto> updateBooking(@RequestBody @Valid BookingDto booking) {
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @Operation(summary = "Cancel my booking", description = "Cancels a booking made by the current user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking cancelled successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied or booking does not belong to user")
    })
    @PutMapping("/cancel-my-booking/{bookingId}")
    @PreAuthorize("hasAuthority('USER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelMyBooking(bookingId, SecurityHelper.getUserConnected().getUsername());
    }

    @Operation(summary = "Get my upcoming bookings", description = "Returns a paginated list of upcoming bookings for the current user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Upcoming bookings retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/my-bookings/upcoming")
    @PreAuthorize("hasAuthority('USER')")
    public List<MyBookingResponse> getMyUpcomingBookings(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") @Max(100) int size) {
        return bookingService.getUpcomingBooking(SecurityHelper.getUserConnected().getUsername(), page, size);
    }

    @Operation(summary = "Get my past bookings", description = "Returns a paginated list of past bookings for the current user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Past bookings retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/my-bookings/past")
    @PreAuthorize("hasAuthority('USER')")
    public List<MyBookingResponse> getMyPastBookings(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") @Max(100) int size) {
        return bookingService.getPastBooking(SecurityHelper.getUserConnected().getUsername(), page, size);
    }

}
