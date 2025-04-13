package com.service.backend.web.services.helper;

import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.models.entities.Booking;
import org.springframework.stereotype.Service;

import static com.service.backend.web.services.helper.FlightServiceHelper.mapFlightDtoToEntity;
import static com.service.backend.web.services.helper.FlightServiceHelper.mapFlightEntityToDto;
import static com.service.backend.web.services.helper.PassengerServiceHelper.mapPassengerDtoToEntity;
import static com.service.backend.web.services.helper.UserServiceHelper.mapUserDtoToEntity;

@Service
public  class BookingServiceHelper  {


    public static BookingDto  mapBookingEntityToDto(Booking booking){
        BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        dto.setStatus(booking.getStatus());
        dto.setBookingDate(booking.getBookingDate());
        dto.setFlight(mapFlightEntityToDto(booking.getFlight()));
        dto.setUser(UserServiceHelper.mapUserEntityToDto(booking.getUser()));
        dto.setPassengers(booking.getPassengers().stream().map(pass ->PassengerServiceHelper.mapPassengerEntityToDto(pass)).toList());
        return dto;
    }

    public static Booking mapBookingDtoToEntity(BookingDto dto) {
        Booking booking = new Booking();
        booking.setId(dto.getId());
        booking.setStatus(dto.getStatus());
        booking.setBookingDate(dto.getBookingDate());
        booking.setFlight(mapFlightDtoToEntity(dto.getFlight()));
        booking.setUser(mapUserDtoToEntity(dto.getUser()));
        booking.setPassengers(dto.getPassengers().stream().map(pass->mapPassengerDtoToEntity(pass)).toList());

        return booking;
    }

}
