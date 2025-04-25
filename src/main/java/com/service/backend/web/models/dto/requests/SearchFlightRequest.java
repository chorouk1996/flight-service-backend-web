package com.service.backend.web.models.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class SearchFlightRequest {


    @NotBlank
    @JsonProperty("departureCity")
    private String departure_city;


    @NotBlank
    @JsonProperty("destinationCity")
    private String destination_city;

    @JsonProperty("departureDate")
    private LocalDateTime date;

    @JsonProperty("airline")
    private String airline;

    @JsonProperty("aircraft")
    private String aircraft;

    @JsonProperty("status")
    private String status;



    public String getDeparture_city() {
        return departure_city;
    }

    public void setDeparture_city(String departure_city) {
        this.departure_city = departure_city;
    }

    public String getDestination_city() {
        return destination_city;
    }

    public void setDestination_city(String destination_city) {
        this.destination_city = destination_city;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public String getAircraft() {
        return aircraft;
    }

    public void setAircraft(String aircraft) {
        this.aircraft = aircraft;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
