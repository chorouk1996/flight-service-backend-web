package com.service.backend.web.services.implementation;

import com.service.backend.web.models.responses.DashboardResponse;
import com.service.backend.web.services.interfaces.IBookingService;
import com.service.backend.web.services.interfaces.IDashboardService;
import com.service.backend.web.services.interfaces.IFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService implements IDashboardService {

    @Autowired
    IBookingService bookingService;

    @Autowired
    IFlightService flightService;
    @Override
    public DashboardResponse getOverview() {
        DashboardResponse response = new DashboardResponse();
        response.setTotalBookings(bookingService.countAll());
        response.setCancelledBookings(bookingService.countCancelledBookings());
        response.setConfirmedBookings(bookingService.countConfirmedBookings());
        response.setTotalFlights(flightService.countAll());
        response.setTotalRevenue(bookingService.calculateBookingRevenue());
        return response;
    }
}
