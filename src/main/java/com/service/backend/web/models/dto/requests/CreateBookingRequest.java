package com.service.backend.web.models.dto.requests;

import com.service.backend.web.models.dto.PassengerDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CreateBookingRequest {

    @NotNull
    private long flightId;

    @NotEmpty
    private List<CreatePassengerRequest> passengers;


    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public List<CreatePassengerRequest> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<CreatePassengerRequest> passengers) {
        this.passengers = passengers;
    }
}
