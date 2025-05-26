package com.service.backend.web.models.requests;


import com.service.backend.web.annotation.ActiveFlight;
import com.service.backend.web.annotation.AvailableSeat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import lombok.Data;

import java.util.List;

@Data
@AvailableSeat
public class CreateBookingRequest {

    @NotNull
    @ActiveFlight
    private long flightId;

    @Valid
    private List<CreateSavedPassengerRequest> passengers;

    private List<Long> passengerIds;

}
