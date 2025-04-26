package com.service.backend.web.models.dto.requests;


import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CreateBookingRequest {

    @NotNull
    private long flightId;

    private List<CreateSavedPassengerRequest> passengers;

    private List<Long> passengerIds;

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public List<CreateSavedPassengerRequest> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<CreateSavedPassengerRequest> passengers) {
        this.passengers = passengers;
    }

    public List<Long> getPassengerIds() {
        return passengerIds;
    }

    public void setPassengerIds(List<Long> passengerIds) {
        this.passengerIds = passengerIds;
    }
}
