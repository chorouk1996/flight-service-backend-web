package com.service.backend.web.models.responses;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingByMonthResponse {

    private String month;

    private long bookingCount;

    private double revenue;

    public BookingByMonthResponse(IBookingByMonthResponse res) {
        this.month = res.getMonth();
        this.bookingCount = res.getBookingCount();
        this.revenue = res.getRevenue();
    }
}
