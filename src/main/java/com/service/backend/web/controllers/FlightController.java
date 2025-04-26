package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.FlightDto;
import com.service.backend.web.models.dto.requests.CreateFlightRequest;
import com.service.backend.web.models.dto.requests.SearchFlightRequest;
import com.service.backend.web.models.dto.requests.UpdateFlightRequest;
import com.service.backend.web.models.dto.responses.CreateFlightResponse;
import com.service.backend.web.services.implementation.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    FlightService flightService;


    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlight(@PathVariable Long id) {
        return new ResponseEntity<>(flightService.getFlight(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<FlightDto>> getAllFlight() {
        return new ResponseEntity<>(flightService.getAllFlight(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public CreateFlightResponse addFlight(@RequestBody CreateFlightRequest flight) {
        return flightService.addFlight(flight);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public CreateFlightResponse updateFlight(@RequestBody UpdateFlightRequest flight) {
        return flightService.updateFlight(flight);
    }

    @DeleteMapping("/{flightId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteFlight(@PathVariable Long flightId) {
         flightService.cancelFlight(flightId);
    }

    @PostMapping("/search")
    public List<FlightDto> searchFlight(@RequestBody @Valid SearchFlightRequest searchCriteria) {
        return flightService.searchFlight(searchCriteria);
    }
}
