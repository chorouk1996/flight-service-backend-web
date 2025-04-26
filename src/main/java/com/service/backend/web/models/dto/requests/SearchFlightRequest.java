package com.service.backend.web.models.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.backend.web.models.enumerators.FlightStatusEnum;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class SearchFlightRequest {


    @NotBlank
    @JsonProperty("departureCity")
    private String departureCity;


    @NotBlank
    @JsonProperty("destinationCity")
    private String destinationCity;

    @JsonProperty("departureDate")
    private LocalDate date;

    @JsonProperty("airline")
    private String airline;

    @JsonProperty("aircraft")
    private String aircraft;

    @JsonProperty("status")
    private FlightStatusEnum status;


    @JsonProperty("sort")
    private SortRequest sort;



    public String getDeparture_city() {
        return departureCity;
    }

    public void setDeparture_city(String departure_city) {
        this.departureCity = departure_city;
    }

    public String getDestination_city() {
        return destinationCity;
    }

    public void setDestination_city(String destination_city) {
        this.destinationCity = destination_city;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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


    public SortRequest getSort() {
        return sort;
    }

    public void setSort(SortRequest sort) {
        this.sort = sort;
    }

    public String getDepartureCity() {
        return departureCity;
    }

    public void setDepartureCity(String departureCity) {
        this.departureCity = departureCity;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public void setDestinationCity(String destinationCity) {
        this.destinationCity = destinationCity;
    }

    public FlightStatusEnum getStatus() {
        return status;
    }

    public void setStatus(FlightStatusEnum status) {
        this.status = status;
    }
}
