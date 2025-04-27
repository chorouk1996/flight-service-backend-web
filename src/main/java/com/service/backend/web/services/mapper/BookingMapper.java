package com.service.backend.web.services.mapper;

import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.models.responses.CreateBookingResponse;
import com.service.backend.web.models.responses.MyBookingResponse;
import com.service.backend.web.models.entities.Booking;

import static com.service.backend.web.services.mapper.FlightMapper.mapFlightDtoToEntity;
import static com.service.backend.web.services.mapper.FlightMapper.mapFlightEntityToDto;
import static com.service.backend.web.services.mapper.UserMapper.mapUserDtoToEntity;


public  class BookingMapper {

    private BookingMapper() {
        throw new UnsupportedOperationException("Don't instantiate this  Utility class");
    };

    public static BookingDto  mapBookingEntityToDto(Booking booking){
        BookingDto dto = new BookingDto();
        dto.setId(booking.getId());
        dto.setStatus(booking.getStatus());
        dto.setBookingDate(booking.getBookingDate());
        dto.setFlight(mapFlightEntityToDto(booking.getFlight()));
        dto.setUser(UserMapper.mapUserEntityToDto(booking.getUser()));
        dto.setPassengers(booking.getPassengers().stream().map(PassengerMapper::mapPassengerEntityToDto).toList());
        return dto;
    }
    public static CreateBookingResponse  mapBookingEntityToResponse(Booking booking){
        CreateBookingResponse dto = new CreateBookingResponse();
        dto.setId(booking.getId());
        dto.setStatus(booking.getStatus());
        dto.setBookingDate(booking.getBookingDate());
        dto.setFlight(booking.getFlight().getId());
        dto.setUser(booking.getUser().getId());
        dto.setPassengers(booking.getPassengers().stream().map(PassengerMapper::mapPassengerEntityToDto).toList());
        return dto;
    }
    public static MyBookingResponse  mapBookingEntityToMyBookingResponse(Booking booking){
        MyBookingResponse dto = new MyBookingResponse();
        dto.setBookingId(booking.getId());
        dto.setStatus(booking.getStatus());
        dto.setBookingDate(booking.getBookingDate());
        dto.setFlightDestination(booking.getFlight().getDestination());
        dto.setFlightOrigin(booking.getFlight().getOrigin());
        dto.setPassengers(booking.getPassengers().stream().map(PassengerMapper::mapPassengerEntityToDto).toList());
        dto.setDepartureTime(booking.getFlight().getDepartureTime());
        return dto;
    }

    public static Booking mapBookingDtoToEntity(BookingDto dto) {
        Booking booking = new Booking();
        booking.setId(dto.getId());
        booking.setStatus(dto.getStatus());
        booking.setBookingDate(dto.getBookingDate());
        booking.setFlight(mapFlightDtoToEntity(dto.getFlight()));
        booking.setUser(mapUserDtoToEntity(dto.getUser()));
        booking.setPassengers(dto.getPassengers().stream().map(PassengerMapper::mapPassengerDtoToEntity).toList());

        return booking;
    }

}
