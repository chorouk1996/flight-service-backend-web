package com.service.backend.web.models.responses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DashboardResponse {

    long totalBookings;
    long confirmedBookings;
    long cancelledBookings;
    long totalFlights;
    Double totalRevenue;



}
