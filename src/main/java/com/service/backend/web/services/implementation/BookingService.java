package com.service.backend.web.services.implementation;

import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.models.dto.requests.CreateBookingRequest;
import com.service.backend.web.models.dto.responses.CreateBookingResponse;
import com.service.backend.web.models.entities.Booking;
import com.service.backend.web.models.entities.Passenger;
import com.service.backend.web.models.enumerators.BookingStatusEnum;
import com.service.backend.web.repositories.BookingRepository;
import com.service.backend.web.services.interfaces.IBookingService;
import com.service.backend.web.services.mapper.BookingMapper;
import com.service.backend.web.services.mapper.FlightMapper;
import com.service.backend.web.services.mapper.PassengerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.service.backend.web.services.mapper.BookingMapper.mapBookingDtoToEntity;

@Service
public class BookingService implements IBookingService {

    BookingRepository bookingRepository;

    @Autowired  PassengerService PassengerService;

    @Autowired FlightService flightService;

    @Autowired UserService userService;
    @Override
    public CreateBookingResponse addBooking(CreateBookingRequest booking, String username) {
        Booking bookingToAdd = new Booking();
        bookingToAdd.setPassengers(booking.getPassengers().stream().map(PassengerMapper::mapPassengerDtoToEntity).toList());
        bookingToAdd.setFlight(FlightMapper.mapFlightDtoToEntity(flightService.getFlight(booking.getFlightId())));
        bookingToAdd.setBookingDate(LocalDateTime.now());
        bookingToAdd.setUser(userService.getUser(username).get());
        bookingToAdd.setStatus(BookingStatusEnum.CONFIRMED);
        return BookingMapper.mapBookingEntityToResponse(bookingRepository.save(bookingToAdd));
    }

    @Override
    public List<BookingDto> getAllBooking() {
        return bookingRepository.findAll().stream().map(BookingMapper::mapBookingEntityToDto).toList();
    }

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


}
