package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.BookingDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/booking")
public class BookingController {

    BookingDto booking = new BookingDto();
    @GetMapping("/:id")
    public ResponseEntity<BookingDto> getBooking(@RequestParam Long id){
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookingDto>> getAllBooking(){
        return new ResponseEntity<>(List.of(booking), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<BookingDto> addBooking(@RequestBody BookingDto booking){
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<BookingDto> updateBooking(@RequestBody BookingDto booking){
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }
}
