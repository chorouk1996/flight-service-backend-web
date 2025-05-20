package com.service.backend.web.controllers;



import com.service.backend.web.models.responses.BookingByMonthResponse;
import com.service.backend.web.models.responses.FlightByAirlineResponse;
import com.service.backend.web.services.interfaces.IBookingService;
import com.service.backend.web.services.interfaces.IFlightService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/stats")
@AllArgsConstructor
public class StatsAdminController {

    private final IFlightService flightService;

    private final IBookingService bookingService;
    @GetMapping("/flights-by-airline")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<FlightByAirlineResponse> getFlightsByAirline() {
        return flightService.getFlightsByAirline();
    }

    @GetMapping("/bookings-by-month")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<BookingByMonthResponse> getBookingsByMonth() {
        return bookingService.getBookingsByMonth();
    }


}
