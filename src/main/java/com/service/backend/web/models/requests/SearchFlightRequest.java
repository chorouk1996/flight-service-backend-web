package com.service.backend.web.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.service.backend.web.models.enumerators.FlightStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(name = "SearchFlightRequest", description = "Request payload to search for flights using various filters and optional sorting.")
public class SearchFlightRequest {

    @NotBlank
    @JsonProperty("departureCity")
    @Schema(description = "Departure city or airport code", example = "CASABLANCA", required = true)
    private String departureCity;

    @NotBlank
    @JsonProperty("destinationCity")
    @Schema(description = "Destination city or airport code", example = "PARIS", required = true)
    private String destinationCity;

    @JsonProperty("departureDate")
    @Schema(description = "Date of departure (ISO format)", example = "2025-07-10")
    private LocalDate date;

    @JsonProperty("airline")
    @Schema(description = "Filter by airline name", example = "Air France")
    private String airline;

    @JsonProperty("aircraft")
    @Schema(description = "Filter by aircraft model or type", example = "Boeing 737")
    private String aircraft;

    @JsonProperty("status")
    @Schema(description = "Filter by flight status", example = "SCHEDULED")
    private FlightStatusEnum status;

    @JsonProperty("sort")
    @Schema(description = "Sorting preferences for the flight search results")
    private SortRequest sort;

    @Schema(description = "Minimum flight price to filter by", example = "100.0")
    private Double minPrice;

    @Schema(description = "Maximum flight price to filter by", example = "500.0")
    private Double maxPrice;

    @Schema(description = "Minimum duration in minutes", example = "60")
    private Long minDurationMinutes;

    @Schema(description = "Maximum duration in minutes", example = "240")
    private Long maxDurationMinutes;

    @Schema(description = "Enable flexible dates search (±1 day)", example = "true")
    private Boolean flexibleDates;
}