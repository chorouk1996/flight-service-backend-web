package com.service.backend.web.models.requests;

import com.service.backend.web.models.enumerators.FlightStatusEnum;

import lombok.Data;

@Data
public class UpdateFlightStatusRequest {

    private FlightStatusEnum status;

    private String delayReason;



}
