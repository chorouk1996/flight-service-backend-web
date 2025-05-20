package com.service.backend.web.models.requests;


import com.service.backend.web.models.enumerators.FlightStatusEnum;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UpdateFlightRequest {

    private String flightNumber;

    private String origin;

    private String destination;


    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    @Min(1)
    private Double price;

    @Min(1)
    private Integer seats;

    private String airlineName;

    private String aircraftType;

    private String baggagePolicy;


    private FlightStatusEnum status;

    private String delayReason;


}