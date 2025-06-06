package com.service.backend.web.models.requests;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "SearchBookingRequest", description = "Search filter to query bookings by email, status, or flight number.")
public class SearchBookingRequest {

    @Schema(description = "User email to filter bookings by", example = "user@example.com")
    private String email;

    @Schema(description = "Booking status to filter by (e.g., CONFIRMED, CANCELLED, PENDING_PAYMENT)", example = "CONFIRMED")
    private String status;

    @Schema(description = "Flight number to filter by", example = "AA1234")
    private String flightNumber;

    @Schema(description = "Page number for pagination", example = "0", defaultValue = "0")
    private int page = 0;

    @Schema(description = "Number of records per page (max 100)", example = "10", defaultValue = "10")
    private int size = 10;
}