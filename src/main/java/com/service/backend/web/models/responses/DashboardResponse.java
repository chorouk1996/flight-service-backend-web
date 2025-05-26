package com.service.backend.web.models.responses;


import lombok.Data;

@Data
public class DashboardResponse {

    long totalBookings;
    long confirmedBookings;
    long cancelledBookings;
    long totalFlights;
    Double totalRevenue;



}
