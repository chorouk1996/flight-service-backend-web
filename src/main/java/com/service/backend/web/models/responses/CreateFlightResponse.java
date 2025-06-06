package com.service.backend.web.models.responses;


import com.service.backend.web.models.enumerators.FlightStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "CreateFlightResponse", description = "Details of the flight after it has been created or updated.")
public class CreateFlightResponse {

    @Schema(description = "Unique flight number", example = "AF302")
    private String flightNumber;

    @Schema(description = "Origin city or airport", example = "Casablanca")
    private String origin;

    @Schema(description = "Destination city or airport", example = "Madrid")
    private String destination;

    @Schema(description = "Departure time of the flight", example = "2025-08-01T09:00:00")
    private LocalDateTime departureTime;

    @Schema(description = "Arrival time of the flight", example = "2025-08-01T11:30:00")
    private LocalDateTime arrivalTime;

    @Schema(description = "Ticket price", example = "199.99")
    private Double price;

    @Schema(description = "Total available seats", example = "150")
    private Integer seats;

    @Schema(description = "Airline operating the flight", example = "Royal Air Maroc")
    private String airlineName;

    @Schema(description = "Type of aircraft used", example = "Boeing 737")
    private String aircraftType;

    @Schema(description = "Policy regarding baggage", example = "1 cabin + 1 checked bag")
    private String baggagePolicy;

    @Schema(description = "Current status of the flight", example = "SCHEDULED")
    private FlightStatusEnum flightStatus;

    @Schema(description = "Reason for delay or cancellation, if applicable", example = "Maintenance issue")
    private String delayReason;
}