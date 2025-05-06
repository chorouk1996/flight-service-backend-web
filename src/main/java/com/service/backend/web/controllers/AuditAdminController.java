package com.service.backend.web.controllers;


import com.service.backend.web.models.responses.BookingByMonthResponse;
import com.service.backend.web.models.responses.FlightByAirlineResponse;
import com.service.backend.web.services.interfaces.IBookingService;
import com.service.backend.web.services.interfaces.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/audit")
public class AuditAdminController {

    @Autowired
    IFlightService flightService;


    @GetMapping("/logs")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<FlightByAirlineResponse> getFlightsByAirline() {
        return flightService.getFlightsByAirline();
    }


}
