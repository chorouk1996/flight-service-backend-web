package com.service.backend.web.models.dto;


import com.service.backend.web.models.enumerators.FlightStatusEnum;

import lombok.Data;


import java.time.LocalDateTime;
import java.util.List;

@Data
public class FlightDto {

    private Long id;

    private String origin;

    private String destination;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private Double price;

    private Integer seats;

    private List<BookingDto> booking;

    private String airlineName ;

    private String aircraftType ;

    private String baggagePolicy;

    private Long durationMinutes;
    private FlightStatusEnum flightStatus ;

    private String flightNumber;

    private String delayReason;


}
