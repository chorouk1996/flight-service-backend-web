package com.service.backend.web.models.requests;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CreateBookingRequest {

    @NotNull
    private long flightId;

    private List<CreateSavedPassengerRequest> passengers;

    private List<Long> passengerIds;

}
