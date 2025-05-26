package com.service.backend.web.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.backend.web.models.enumerators.FlightStatusEnum;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

import java.time.LocalDate;

@Data
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

    private Double minPrice;
    private Double maxPrice;
    private Long minDurationMinutes;
    private Long maxDurationMinutes;

    private Boolean flexibleDates;



}
