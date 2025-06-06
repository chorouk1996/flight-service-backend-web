package com.service.backend.web.models.responses;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(name = "BookingByMonthResponse", description = "Represents the number of bookings and total revenue for a given month.")
public class BookingByMonthResponse {

    @Schema(description = "Month in format MMM-yyyy", example = "May-2025")
    private String month;

    @Schema(description = "Total number of bookings in the month", example = "123")
    private long bookingCount;

    @Schema(description = "Total revenue generated in the month", example = "25430.75")
    private double revenue;

    public BookingByMonthResponse(IBookingByMonthResponse res) {
        this.month = res.getMonth();
        this.bookingCount = res.getBookingCount();
        this.revenue = res.getRevenue();
    }
}