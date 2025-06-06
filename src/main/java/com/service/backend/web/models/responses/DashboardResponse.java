package com.service.backend.web.models.responses;


import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Schema(name = "DashboardResponse", description = "Aggregated dashboard metrics related to bookings and flights.")
public class DashboardResponse {

    @Schema(description = "Total number of bookings", example = "1200")
    private long totalBookings;

    @Schema(description = "Number of confirmed bookings", example = "950")
    private long confirmedBookings;

    @Schema(description = "Number of cancelled bookings", example = "150")
    private long cancelledBookings;

    @Schema(description = "Total number of flights in the system", example = "85")
    private long totalFlights;

    @Schema(description = "Total revenue generated", example = "123456.78")
    private Double totalRevenue;
}