package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.FlightDto;
import com.service.backend.web.models.requests.SearchFlightRequest;
import com.service.backend.web.services.interfaces.IFlightService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flight")
@AllArgsConstructor
public class FlightController {

    private final IFlightService flightService;


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<FlightDto> getFlight(@PathVariable Long id) {
        return new ResponseEntity<>(flightService.getFlight(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<FlightDto>> getAllFlight() {
        return new ResponseEntity<>(flightService.getAllFlight(), HttpStatus.OK);
    }



    @PostMapping("/search")
    @PreAuthorize("hasAuthority('USER')")
    public List<FlightDto> searchFlight(@RequestBody @Valid SearchFlightRequest searchCriteria) {
        return flightService.userSearchFlight(searchCriteria);
    }
}
