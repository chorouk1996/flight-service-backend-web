package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.services.implementation.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<BookingDto>> getAllBooking() {
        return new ResponseEntity<>(bookingService.getAllBooking(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<BookingDto> addBooking(@RequestBody BookingDto booking) {
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<BookingDto> updateBooking(@RequestBody BookingDto booking) {
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }
}
