package com.service.backend.web.models.responses;

import lombok.AllArgsConstructor;

import lombok.Data;

@Data
@AllArgsConstructor
public class FlightByAirlineResponse {

    private String airline;

    private long flightCount;


    public FlightByAirlineResponse(IFlightByAirlineResponse res){
        this.airline = res.getAirline();
        this.flightCount =res.getFlightCount();
    }
}
