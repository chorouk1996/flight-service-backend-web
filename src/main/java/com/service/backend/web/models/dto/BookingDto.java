package com.service.backend.web.models.dto;


import java.time.LocalDate;
import java.util.List;

public class BookingDto {
    private Long id;

    private UserDto user;

    private FlightDto flight;

    private LocalDate bookingDate;

    private String status;

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

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PassengerDto> getPassengers() {
        return passengerDtos;
    }

    public void setPassengers(List<PassengerDto> passengerDtos) {
        this.passengerDtos = passengerDtos;
    }
}
