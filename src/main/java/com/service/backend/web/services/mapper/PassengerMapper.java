package com.service.backend.web.services.mapper;

import com.service.backend.web.models.dto.PassengerDto;
import com.service.backend.web.models.dto.requests.CreatePassengerRequest;
import com.service.backend.web.models.entities.Passenger;

public class PassengerMapper {

    private PassengerMapper() {
        throw new UnsupportedOperationException("Don't instantiate this  Utility class");
    }

    public static PassengerDto mapPassengerEntityToDto(Passenger passenger) {
        PassengerDto dto = new PassengerDto();
        dto.setId(passenger.getId());
        dto.setAge(passenger.getAge());
        dto.setFirstName(passenger.getFirstName());
        dto.setLastName(passenger.getLastName());
        dto.setEmail(passenger.getEmail());
        return dto;
    }

    public static Passenger mapPassengerDtoToEntity(PassengerDto dto) {
        Passenger passenger = new Passenger();
        passenger.setId(dto.getId());
        passenger.setAge(dto.getAge());
        passenger.setFirstName(dto.getFirstName());
        passenger.setLastName(dto.getLastName());
        passenger.setEmail(dto.getEmail());
        return passenger;
    }

    public static Passenger mapCreatePassengerRequestToEntity(CreatePassengerRequest dto) {
        Passenger passenger = new Passenger();
        passenger.setAge(dto.getAge());
        passenger.setFirstName(dto.getFirstName());
        passenger.setLastName(dto.getLastName());
        passenger.setEmail(dto.getEmail());
        return passenger;
    }


}
