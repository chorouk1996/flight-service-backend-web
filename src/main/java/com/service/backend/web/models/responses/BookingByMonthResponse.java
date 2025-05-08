package com.service.backend.web.models.responses;

public class BookingByMonthResponse {

    private String month;

    private long bookingCount;

    private double revenue;

    public BookingByMonthResponse(IBookingByMonthResponse res) {
        this.month = res.getMonth();
        this.bookingCount = res.getBookingCount();
        this.revenue = res.getRevenue();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public long getBookingCount() {
        return bookingCount;
    }

    public void setBookingCount(long bookingCount) {
        this.bookingCount = bookingCount;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }
}
