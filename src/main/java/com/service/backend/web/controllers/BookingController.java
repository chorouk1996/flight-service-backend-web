package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.models.requests.CreateBookingRequest;
import com.service.backend.web.models.responses.CreateBookingResponse;
import com.service.backend.web.models.responses.MyBookingResponse;
import com.service.backend.web.services.helper.SecurityHelper;
import com.service.backend.web.services.interfaces.IBookingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@AllArgsConstructor
public class BookingController {

    private final IBookingService bookingService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public BookingDto getBooking(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    @GetMapping("/my-bookings")
    @PreAuthorize("hasAuthority('USER')")
    public List<MyBookingResponse> getMyBookings(@RequestParam(required = false, defaultValue = "0") int page,@RequestParam(required = false, defaultValue = "10") int size) {
        return bookingService.getAllBooking(SecurityHelper.getUserConnected().getUsername(),page,size);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('USER')")
    public CreateBookingResponse addBooking(@RequestBody @Valid CreateBookingRequest booking) {
         return bookingService.addBooking(booking,SecurityHelper.getUserConnected().getUsername());
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<BookingDto> updateBooking(@RequestBody @Valid BookingDto booking) {
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }


    @PutMapping("/cancel-my-booking/{bookingId}")
    @PreAuthorize("hasAuthority('USER')")
    public void cancelBooking(@PathVariable Long bookingId) {
        bookingService.cancelMyBooking(bookingId,SecurityHelper.getUserConnected().getUsername());
    }

    @GetMapping("/my-bookings/upcoming")
    @PreAuthorize("hasAuthority('USER')")
    public List<MyBookingResponse> getMyUpcomingBookings(@RequestParam(required = false, defaultValue = "0") int page,@RequestParam(required = false, defaultValue = "10") int size) {
        return bookingService.getUpcomingBooking(SecurityHelper.getUserConnected().getUsername(),page,size);
    }

    @GetMapping("/my-bookings/past")
    @PreAuthorize("hasAuthority('USER')")
    public List<MyBookingResponse> getMyPastBookings(@RequestParam(required = false, defaultValue = "0") int page,@RequestParam(required = false, defaultValue = "10") int size) {
        return bookingService.getPastBooking(SecurityHelper.getUserConnected().getUsername(),page,size);
    }

}
