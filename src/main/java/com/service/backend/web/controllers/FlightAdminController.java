package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.FlightDto;
import com.service.backend.web.models.requests.CreateFlightRequest;
import com.service.backend.web.models.requests.SearchFlightRequest;
import com.service.backend.web.models.requests.UpdateFlightRequest;
import com.service.backend.web.models.requests.UpdateFlightStatusRequest;
import com.service.backend.web.models.responses.CreateFlightResponse;
import com.service.backend.web.services.implementation.FlightService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/flight")
public class FlightAdminController {

    @Autowired
    FlightService flightService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public CreateFlightResponse addFlight(@RequestBody @Valid CreateFlightRequest flight) {
        return flightService.addFlight(flight);
    }

    @PutMapping("/{flightId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CreateFlightResponse updateFlight(@PathVariable (required = true) Long flightId,@RequestBody UpdateFlightRequest flight) {
        return flightService.updateFlight(flightId,flight);
    }

    @PutMapping("/{flightId}/status")
    @PreAuthorize("hasAuthority('ADMIN')")
    public CreateFlightResponse updateFlightStatus(@PathVariable (required = true) Long flightId,@RequestBody UpdateFlightStatusRequest request) {
        return flightService.updateFlightStatus(flightId,request);
    }

    @DeleteMapping("/{flightId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteFlight(@PathVariable Long flightId) {
         flightService.cancelFlight(flightId);
    }


    @PostMapping("/search")
    public List<FlightDto> searchFlight(@RequestBody @Valid SearchFlightRequest searchCriteria) {
        return flightService.adminSearchFlight(searchCriteria);
    }
}
