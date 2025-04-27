package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.models.dto.requests.CreateBookingRequest;
import com.service.backend.web.models.dto.responses.CreateBookingResponse;
import com.service.backend.web.models.dto.responses.MyBookingResponse;
import com.service.backend.web.security.UserDetailsImpl;
import com.service.backend.web.services.implementation.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/booking")
public class BookingAdminController {

    @Autowired
    BookingService bookingService;
    BookingDto booking = new BookingDto();

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public BookingDto getBooking(@PathVariable Long id) {
        return booking;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<BookingDto>> getAllBooking() {
        return new ResponseEntity<>(bookingService.getAllBooking(), HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public CreateBookingResponse addBooking(@RequestBody @Valid CreateBookingRequest booking) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         return bookingService.addBooking(booking,user.getUsername());
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<BookingDto> updateBooking(@RequestBody @Valid BookingDto booking) {
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @PutMapping("/cancel/{bookingId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void cancelBooking(@PathVariable Long bookingId) {
         bookingService.cancelBooking(bookingId);
    }

    @PutMapping("/confirm/{bookingId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void confirmBooking(@PathVariable Long bookingId) {
        bookingService.confirmBooking(bookingId);
    }
}
