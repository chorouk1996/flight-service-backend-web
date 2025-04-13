package com.service.backend.web.services.helper;

import com.service.backend.web.models.dto.PassengerDto;
import com.service.backend.web.models.entities.Passenger;
import org.springframework.stereotype.Service;

import static com.service.backend.web.services.helper.BookingServiceHelper.mapBookingEntityToDto;

@Service
public class PassengerServiceHelper  {

    public static PassengerDto mapPassengerEntityToDto(Passenger passenger) {
        PassengerDto dto = new PassengerDto();
        dto.setId(passenger.getId());
        dto.setName(passenger.getName());
        dto.setAge(passenger.getAge());
        dto.setBooking(mapBookingEntityToDto(passenger.getBooking()));


        return dto;
    }

    public static Passenger mapPassengerDtoToEntity(PassengerDto dto) {
        Passenger passenger = new Passenger();
        passenger.setId(dto.getId());
        passenger.setName(dto.getName());
        passenger.setAge(dto.getAge());

        // Booking can be set later if needed
        return passenger;
    }

}
