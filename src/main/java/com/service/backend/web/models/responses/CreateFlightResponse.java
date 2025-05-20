package com.service.backend.web.models.responses;



import com.service.backend.web.models.enumerators.FlightStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CreateFlightResponse {


    private String flightNumber;

    private String origin;

    private String destination;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private Double price;

    private Integer seats;

    private String airlineName;

    private String aircraftType;

    private String baggagePolicy;

    private FlightStatusEnum flightStatus ;

    private String delayReason;



}