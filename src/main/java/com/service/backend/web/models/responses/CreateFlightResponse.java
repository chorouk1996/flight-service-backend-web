package com.service.backend.web.models.responses;



import com.service.backend.web.models.enumerators.FlightStatusEnum;

import java.time.LocalDateTime;

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


    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public String getBaggagePolicy() {
        return baggagePolicy;
    }

    public void setBaggagePolicy(String baggagePolicy) {
        this.baggagePolicy = baggagePolicy;
    }

    public FlightStatusEnum getFlightStatus() {
        return flightStatus;
    }

    public void setFlightStatus(FlightStatusEnum flightStatus) {
        this.flightStatus = flightStatus;
    }

    public String getDelayReason() {
        return delayReason;
    }

    public void setDelayReason(String delayReason) {
        this.delayReason = delayReason;
    }
}