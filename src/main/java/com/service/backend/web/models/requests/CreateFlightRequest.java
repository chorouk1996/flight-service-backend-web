package com.service.backend.web.models.requests;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateFlightRequest {


    @NotBlank
    private String flightNumber;

    @NotBlank
    private String origin;

    @NotBlank
    private String destination;

    @NotNull
    private LocalDateTime departureTime;

    @NotNull
    private LocalDateTime arrivalTime;

    @Min(1)
    private Double price;

    @Min(1)
    private Integer seats;

    @NotBlank
    private String airlineName;

    @NotBlank
    private String aircraftType;

    @NotBlank
    private String baggagePolicy;



}