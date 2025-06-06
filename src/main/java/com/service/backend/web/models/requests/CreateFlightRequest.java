package com.service.backend.web.models.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "CreateFlightRequest", description = "DTO for creating a new flight in the system.")
public class CreateFlightRequest {

    @NotBlank
    @Schema(description = "Unique flight number identifier", example = "AA1234", required = true)
    private String flightNumber;

    @NotBlank
    @Schema(description = "Origin city or airport code", example = "JFK", required = true)
    private String origin;

    @NotBlank
    @Schema(description = "Destination city or airport code", example = "LAX", required = true)
    private String destination;

    @NotNull
    @Schema(description = "Flight departure timestamp in ISO format", example = "2025-06-01T14:00:00", required = true)
    private LocalDateTime departureTime;

    @NotNull
    @Schema(description = "Flight arrival timestamp in ISO format", example = "2025-06-01T17:30:00", required = true)
    private LocalDateTime arrivalTime;

    @Min(1)
    @Schema(description = "Ticket price in the system's currency", example = "299.99", required = true)
    private Double price;

    @Min(1)
    @Schema(description = "Total number of available seats", example = "180", required = true)
    private Integer seats;

    @NotBlank
    @Schema(description = "Airline name operating the flight", example = "Delta Airlines", required = true)
    private String airlineName;

    @NotBlank
    @Schema(description = "Aircraft model or type", example = "Boeing 737", required = true)
    private String aircraftType;

    @NotBlank
    @Schema(description = "Policy describing baggage allowance", example = "2 checked bags, 1 carry-on", required = true)
    private String baggagePolicy;
}