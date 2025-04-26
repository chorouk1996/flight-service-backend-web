package com.service.backend.web.services.implementation;

import com.service.backend.web.exceptions.FunctionalException;
import com.service.backend.web.exceptions.FunctionalExceptionDto;
import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.models.dto.requests.CreateBookingRequest;
import com.service.backend.web.models.dto.responses.CreateBookingResponse;
import com.service.backend.web.models.entities.Booking;
import com.service.backend.web.models.enumerators.BookingStatusEnum;
import com.service.backend.web.repositories.BookingRepository;
import com.service.backend.web.services.interfaces.IBookingService;
import com.service.backend.web.services.mapper.BookingMapper;
import com.service.backend.web.services.mapper.FlightMapper;
import com.service.backend.web.services.mapper.PassengerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService implements IBookingService {

    BookingRepository bookingRepository;

    @Autowired
    PassengerService PassengerService;

    @Autowired
    FlightService flightService;

    @Autowired
    UserService userService;

    @Override
    public CreateBookingResponse addBooking(CreateBookingRequest booking, String username) {
        Booking bookingToAdd = new Booking();
        bookingToAdd.setPassengers(booking.getPassengers().stream().map(PassengerMapper::mapPassengerDtoToEntity).toList());
        flightService.decreaseSeat(booking.getFlightId(), booking.getPassengers().size());
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

    @Override
    public void cancelBooking(Long booking) {

        bookingRepository.findByIdAndStatusNot(booking,BookingStatusEnum.CANCELLED).ifPresentOrElse(
                (myBooking) -> {
                    flightService.increaseSeat(myBooking.getFlight().getId(), myBooking.getPassengers().size());
                    myBooking.setStatus(BookingStatusEnum.CANCELLED);
                    bookingRepository.save(myBooking);
                },

                () -> {
                    throw new FunctionalException(new FunctionalExceptionDto("This booking does not exist or has already been cancelled", HttpStatus.NOT_FOUND));
                }
        );


    }

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


}
