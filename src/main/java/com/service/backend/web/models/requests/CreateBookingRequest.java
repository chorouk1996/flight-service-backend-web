package com.service.backend.web.models.requests;


import com.service.backend.web.annotation.ActiveFlight;
import com.service.backend.web.annotation.AvailableSeat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
@AvailableSeat
@Schema(name = "CreateBookingRequest", description = "Payload to create a new flight booking for one or more passengers.")
public class CreateBookingRequest {

    @NotNull
    @ActiveFlight
    @Schema(description = "ID of the flight to be booked. Must reference an active flight.", example = "101", required = true)
    private long flightId;

    @Valid
    @Schema(description = "List of new passengers to be created and included in this booking")
    private List<CreateSavedPassengerRequest> passengers;

    @Schema(description = "List of IDs of already saved passengers to be included in this booking", example = "[5, 8, 12]")
    private List<Long> passengerIds;
}