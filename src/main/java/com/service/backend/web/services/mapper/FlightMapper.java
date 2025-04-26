package com.service.backend.web.services.mapper;

import com.service.backend.web.models.dto.FlightDto;
import com.service.backend.web.models.entities.Flight;
import com.service.backend.web.models.enumerators.FlightStatusEnum;
import com.service.backend.web.services.helper.FlightHelper;


public class FlightMapper {

    private FlightMapper() {
        throw new UnsupportedOperationException("Don't instantiate this  Utility class");
    }
    public static FlightDto mapFlightEntityToDto(Flight flight) {
        FlightDto dto = new FlightDto();
        dto.setId(flight.getId());
        dto.setOrigin(flight.getOrigin());
        dto.setDestination(flight.getDestination());
        dto.setDepartureTime(flight.getDepartureTime());
        dto.setArrivalTime(flight.getArrivalTime());
        dto.setPrice(flight.getPrice());
        dto.setSeats(flight.getSeats());
        dto.setFlightStatus(flight.getFlightStatus());
        dto.setAircraftType(flight.getAircraftType());
        dto.setAirlineName(flight.getAirlineName());
        dto.setBaggagePolicy(flight.getBaggagePolicy());
        dto.setDurationMinutes(FlightHelper.calculateDuration(flight.getDepartureTime(),flight.getArrivalTime()));
        return dto;
    }

    public static Flight mapFlightDtoToEntity(FlightDto dto) {
        Flight flight = new Flight();
        flight.setId(dto.getId());
        flight.setOrigin(dto.getOrigin());
        flight.setDestination(dto.getDestination());
        flight.setDepartureTime(dto.getDepartureTime());
        flight.setArrivalTime(dto.getArrivalTime());
        flight.setPrice(dto.getPrice());
        flight.setSeats(dto.getSeats());
        flight.setFlightStatus(dto.getFlightStatus());
        flight.setAircraftType(dto.getAircraftType());
        flight.setAirlineName(dto.getAirlineName());
        flight.setBaggagePolicy(dto.getBaggagePolicy());
        return flight;
    }

}
