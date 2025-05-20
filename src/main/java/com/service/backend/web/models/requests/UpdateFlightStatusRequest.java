package com.service.backend.web.models.requests;

import com.service.backend.web.models.enumerators.FlightStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateFlightStatusRequest {

    private FlightStatusEnum status;

    private String delayReason;



}
