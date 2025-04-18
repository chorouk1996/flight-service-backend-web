package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.FlightDto;
import com.service.backend.web.services.implementation.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    FlightService flightService;
    FlightDto flight = new FlightDto();

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlight(@PathVariable Long id) {
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FlightDto>> getAllFlight() {
        return new ResponseEntity<>(flightService.getAllFlight(), HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<FlightDto> addFlight(@RequestBody FlightDto flight) {
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<FlightDto> updateFlight(@RequestBody FlightDto flight) {
        return new ResponseEntity<>(flight, HttpStatus.OK);
    }
}
