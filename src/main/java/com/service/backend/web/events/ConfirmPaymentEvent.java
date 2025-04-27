package com.service.backend.web.events;

public class ConfirmPaymentEvent {

    private Long booking;


    public Long getBooking() {
        return booking;
    }

    public void setBooking(Long booking) {
        this.booking = booking;
    }

    public ConfirmPaymentEvent(Long booking) {
        this.booking = booking;
    }
}
