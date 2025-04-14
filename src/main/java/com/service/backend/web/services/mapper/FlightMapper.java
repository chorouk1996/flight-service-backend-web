package com.service.backend.web.services.mapper;

import com.service.backend.web.models.dto.FlightDto;
import com.service.backend.web.models.entities.Flight;
import org.springframework.stereotype.Service;


@Service
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
        return flight;
    }
}
