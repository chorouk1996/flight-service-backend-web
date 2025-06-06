package com.service.backend.web.models.requests;

import com.service.backend.web.models.enumerators.FlightStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "UpdateFlightStatusRequest", description = "DTO for updating the status of a flight, including delay reason if applicable.")
public class UpdateFlightStatusRequest {

    @Schema(description = "New flight status to apply", example = "DELAYED", required = true)
    private FlightStatusEnum status;

    @Schema(description = "Optional reason if flight is delayed or cancelled", example = "Weather conditions")
    private String delayReason;
}