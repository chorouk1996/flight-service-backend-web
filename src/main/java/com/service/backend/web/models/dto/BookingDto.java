package com.service.backend.web.models.dto;


import com.service.backend.web.models.enumerators.BookingStatusEnum;

import java.time.LocalDateTime;
import java.util.List;

public class BookingDto {
    private Long id;

    private UserDto user;

    private FlightDto flight;

    private LocalDateTime bookingDate;

    private BookingStatusEnum status;

    private List<PassengerDto> passengerDtos;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public FlightDto getFlight() {
        return flight;
    }

    public void setFlight(FlightDto flight) {
        this.flight = flight;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public BookingStatusEnum getStatus() {
        return status;
    }

    public void setStatus(BookingStatusEnum status) {
        this.status = status;
    }

    public List<PassengerDto> getPassengers() {
        return passengerDtos;
    }

    public void setPassengers(List<PassengerDto> passengerDtos) {
        this.passengerDtos = passengerDtos;
    }
}
