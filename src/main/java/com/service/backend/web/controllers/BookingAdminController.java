package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.models.requests.CreateBookingRequest;
import com.service.backend.web.models.requests.SearchBookingRequest;
import com.service.backend.web.models.responses.CreateBookingResponse;
import com.service.backend.web.models.responses.SearchBookingResponse;
import com.service.backend.web.services.helper.SecurityHelper;
import com.service.backend.web.services.interfaces.IBookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/booking")
@AllArgsConstructor
@Tag(name = "Booking Management (Admin)", description = "Administrative operations for managing bookings.")
public class BookingAdminController {

    private final IBookingService bookingService;

    @Operation(summary = "Get booking by ID", description = "Retrieves detailed information of a booking by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking found and returned"),
            @ApiResponse(responseCode = "404", description = "Booking not found"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/{bookingId}")
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).ADMIN")
    public BookingDto getBooking(@PathVariable Long bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    @Operation(summary = "Get all bookings", description = "Returns a paginated list of all bookings in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of bookings returned"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/all")
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).ADMIN")
    public ResponseEntity<List<BookingDto>> getAllBooking(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size) {
        return new ResponseEntity<>(bookingService.getAllBooking(page, size), HttpStatus.OK);
    }

    @Operation(summary = "Create a new booking", description = "Creates a new booking with the provided passenger and flight details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Booking created successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PostMapping()
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).ADMIN")
    public CreateBookingResponse addBooking(@RequestBody @Valid CreateBookingRequest booking) {
        return bookingService.addBooking(booking, SecurityHelper.getUserConnected().getUsername());
    }

    @Operation(summary = "Update a booking", description = "Updates the provided booking with new details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking updated successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PutMapping()
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).ADMIN")
    public ResponseEntity<BookingDto> updateBooking(@RequestBody @Valid BookingDto booking) {
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @Operation(summary = "Cancel a booking", description = "Cancels the booking with the given ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking cancelled successfully"),
            @ApiResponse(responseCode = "404", description = "Booking not found"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PutMapping("/cancel/{bookingId}")
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelBooking(bookingId);
    }

    @Operation(summary = "Confirm a booking", description = "Confirms the booking with the given ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking confirmed successfully"),
            @ApiResponse(responseCode = "404", description = "Booking not found"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PutMapping("/confirm/{bookingId}")
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void confirmBooking(@PathVariable Long bookingId) {
        bookingService.confirmBooking(bookingId);
    }

    @Operation(summary = "Search bookings", description = "Searches for bookings that match the provided criteria.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Search results returned"),
            @ApiResponse(responseCode = "400", description = "Invalid search criteria"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PostMapping("/search")
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).ADMIN")
    public List<SearchBookingResponse> searchBooking(@RequestBody @Valid SearchBookingRequest request){
        return bookingService.searchBooking(request);
    }

    @Operation(summary = "Export bookings", description = "Exports all booking data as a downloadable file.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bookings exported successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @GetMapping("/export")
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).ADMIN")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void exportBooking(HttpServletResponse response){
        bookingService.exportAllBookingto(response);
    }
}
