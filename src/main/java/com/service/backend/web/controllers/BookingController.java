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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingService;
    BookingDto booking = new BookingDto();

    @GetMapping("/{id}")
    public BookingDto getBooking(@PathVariable Long id) {
        return booking;
    }

    @GetMapping("/my-bookings")
    public List<MyBookingResponse> getMyBookings(@RequestParam(required = false, defaultValue = "0") int page,@RequestParam(required = false, defaultValue = "10") int size) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return bookingService.getAllBooking(user.getUsername(),page,size);
    }

    @PostMapping()
    public CreateBookingResponse addBooking(@RequestBody @Valid CreateBookingRequest booking) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         return bookingService.addBooking(booking,user.getUsername());
    }

    @PutMapping()
    public ResponseEntity<BookingDto> updateBooking(@RequestBody @Valid BookingDto booking) {
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }


    @PutMapping("/cancel-my-booking/{bookingId}")
    public void cancelBooking(@PathVariable Long bookingId) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        bookingService.cancelMyBooking(bookingId,user.getUsername());
    }

    @GetMapping("/my-bookings/upcoming")
    public List<MyBookingResponse> getMyUpcomingBookings(@RequestParam(required = false, defaultValue = "0") int page,@RequestParam(required = false, defaultValue = "10") int size) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return bookingService.getUpcomingBooking(user.getUsername(),page,size);
    }

    @GetMapping("/my-bookings/past")
    public List<MyBookingResponse> getMyPastBookings(@RequestParam(required = false, defaultValue = "0") int page,@RequestParam(required = false, defaultValue = "10") int size) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return bookingService.getPastBooking(user.getUsername(),page,size);
    }

}
