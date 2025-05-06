package com.service.backend.web.models.responses;

public class FlightByAirlineResponse {

    private String airline;

    private long flightCount;


    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }

    public long getFlightCount() {
        return flightCount;
    }

    public void setFlightCount(Integer flightCount) {
        this.flightCount = flightCount;
    }

    public FlightByAirlineResponse(IFlightByAirlineResponse res){
        this.airline = res.getAirline();
        this.flightCount =res.getFlightCount();
    }
}
