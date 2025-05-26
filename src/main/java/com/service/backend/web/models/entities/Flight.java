package com.service.backend.web.models.entities;

import com.service.backend.web.models.enumerators.FlightStatusEnum;
import jakarta.persistence.*;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String flightNumber;

    @Column
    private String origin;

    @Column
    private String destination;

    @Column
    private LocalDateTime departureTime;

    @Column
    private LocalDateTime arrivalTime;

    @Column
    private Double price;

    @Column
    private Integer seats;

    @Column
    private String airlineName ;

    @Column
    private String aircraftType ;

    @Column
    private String baggagePolicy;


    @Column
    @Enumerated(EnumType.STRING)
    private FlightStatusEnum flightStatus ;

    @Column
    private String delayReason;

    @OneToMany(mappedBy = "flight")
    private List<Booking> bookings;


}
