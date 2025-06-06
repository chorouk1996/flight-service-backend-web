package com.service.backend.web.models.requests;

import com.service.backend.web.models.enumerators.FlightStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "UpdateFlightRequest", description = "DTO for updating an existing flight's details.")
public class UpdateFlightRequest {

    @Schema(description = "Flight number to update", example = "AF202")
    private String flightNumber;

    @Schema(description = "Updated origin city or airport", example = "CASABLANCA")
    private String origin;

    @Schema(description = "Updated destination city or airport", example = "NEW YORK")
    private String destination;

    @Schema(description = "New departure time", example = "2025-07-15T08:30:00")
    private LocalDateTime departureTime;

    @Schema(description = "New arrival time", example = "2025-07-15T13:45:00")
    private LocalDateTime arrivalTime;

    @Min(1)
    @Schema(description = "Updated flight price", example = "350.00")
    private Double price;

    @Min(1)
    @Schema(description = "Updated available seats", example = "200")
    private Integer seats;

    @Schema(description = "Updated airline name", example = "Air France")
    private String airlineName;

    @Schema(description = "Updated aircraft type", example = "Airbus A330")
    private String aircraftType;

    @Schema(description = "Updated baggage policy", example = "1 checked + 1 cabin bag")
    private String baggagePolicy;

    @Schema(description = "Updated flight status", example = "DELAYED")
    private FlightStatusEnum status;

    @Schema(description = "Reason for delay or cancellation", example = "Technical issue")
    private String delayReason;
}