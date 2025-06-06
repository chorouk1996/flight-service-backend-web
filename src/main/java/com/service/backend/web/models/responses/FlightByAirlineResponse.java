package com.service.backend.web.models.responses;

import lombok.AllArgsConstructor;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(name = "FlightByAirlineResponse", description = "Aggregated count of flights per airline.")
public class FlightByAirlineResponse {

    @Schema(description = "Name of the airline", example = "Royal Air Maroc")
    private String airline;

    @Schema(description = "Total number of flights operated by the airline", example = "42")
    private long flightCount;

    public FlightByAirlineResponse(IFlightByAirlineResponse res) {
        this.airline = res.getAirline();
        this.flightCount = res.getFlightCount();
    }
}