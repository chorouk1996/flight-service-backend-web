package com.service.backend.web.models.requests;


import jakarta.validation.constraints.NotNull;

import lombok.Data;

import java.util.List;

@Data
public class CreateBookingRequest {

    @NotNull
    private long flightId;

    private List<CreateSavedPassengerRequest> passengers;

    private List<Long> passengerIds;

}
