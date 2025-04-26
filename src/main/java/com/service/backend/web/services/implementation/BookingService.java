package com.service.backend.web.services.implementation;

import com.service.backend.web.exceptions.FunctionalException;
import com.service.backend.web.exceptions.FunctionalExceptionDto;
import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.models.dto.requests.CreateBookingRequest;
import com.service.backend.web.models.dto.responses.CreateBookingResponse;
import com.service.backend.web.models.dto.responses.MyBookingResponse;
import com.service.backend.web.models.entities.Booking;
import com.service.backend.web.models.entities.Passenger;
import com.service.backend.web.models.entities.User;
import com.service.backend.web.models.enumerators.BookingStatusEnum;
import com.service.backend.web.repositories.BookingRepository;
import com.service.backend.web.services.interfaces.IBookingService;
import com.service.backend.web.services.interfaces.ISavedPassengerService;
import com.service.backend.web.services.mapper.BookingMapper;
import com.service.backend.web.services.mapper.FlightMapper;
import com.service.backend.web.services.mapper.PassengerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.service.backend.web.services.mapper.PassengerMapper.mapSavedPassengerToPassenger;

@Service
public class BookingService implements IBookingService {

    BookingRepository bookingRepository;

    @Autowired
    ISavedPassengerService savedPassengerService;

    @Autowired
    FlightService flightService;

    @Autowired
    UserService userService;

    @Override
    public CreateBookingResponse addBooking(CreateBookingRequest booking, String username) {
        if (booking.getPassengerIds().isEmpty() && booking.getPassengers().isEmpty())
            throw new FunctionalException(new FunctionalExceptionDto("A booking must contain at least one passenger.", HttpStatus.BAD_REQUEST));
        Booking bookingToAdd = new Booking();
        User user = userService.getUserById(username);
        List<Passenger> passengers = new ArrayList<>();
        bookingToAdd.setFlight(FlightMapper.mapFlightDtoToEntity(flightService.getAvailableFlight(booking.getFlightId())));
        if (!booking.getPassengers().isEmpty()) {
           List<Passenger> passenger=  booking.getPassengers().stream().map(PassengerMapper::mapCreatePassengerRequestToEntity).toList();
            passenger.forEach(pass -> pass.setUser(user));
            passengers.addAll(passenger);
        }
        if (!booking.getPassengerIds().isEmpty()) {
            booking.getPassengerIds().stream().forEach(id -> {
                        Passenger passenger = mapSavedPassengerToPassenger(savedPassengerService.getSavedPassengerById(username, id));
                        passenger.setUser(user);
                        passengers.add(passenger);
                    }
            );

        }
        passengers.forEach(pass -> pass.setBooking(bookingToAdd));
        bookingToAdd.setPassengers(passengers);
        flightService.decreaseSeat(booking.getFlightId(), booking.getPassengers().size());
        bookingToAdd.setBookingDate(LocalDateTime.now());
        bookingToAdd.setUser(user);
        bookingToAdd.setStatus(BookingStatusEnum.CONFIRMED);
        return BookingMapper.mapBookingEntityToResponse(bookingRepository.save(bookingToAdd));
    }

    @Override
    public List<BookingDto> getAllBooking() {
        return bookingRepository.findAll().stream().map(BookingMapper::mapBookingEntityToDto).toList();
    }

    @Override
    public List<MyBookingResponse> getAllBooking(String username, int page, int size) {
        final Pageable pageableRequest = PageRequest.of(page, size);
        return bookingRepository.findByUser(userService.getUserById(username), pageableRequest).stream().map(BookingMapper::mapBookingEntityToMyBookingResponse).toList();
    }

    @Override
    public void cancelBooking(Long booking) {

        bookingRepository.findByIdAndStatusNot(booking, BookingStatusEnum.CANCELLED).ifPresentOrElse(
                myBooking -> {
                    flightService.increaseSeat(myBooking.getFlight().getId(), myBooking.getPassengers().size());
                    myBooking.setStatus(BookingStatusEnum.CANCELLED);
                    bookingRepository.save(myBooking);
                },

                () -> {
                    throw new FunctionalException(new FunctionalExceptionDto("This booking does not exist or has already been cancelled", HttpStatus.NOT_FOUND));
                }
        );


    }

    @Override
    public void cancelMyBooking(Long booking, String username) {

        bookingRepository.findByIdAndUserAndStatusNot(booking, userService.getUserById(username), BookingStatusEnum.CANCELLED).ifPresentOrElse(
                myBooking -> {
                    flightService.increaseSeat(myBooking.getFlight().getId(), myBooking.getPassengers().size());
                    myBooking.setStatus(BookingStatusEnum.CANCELLED);
                    bookingRepository.save(myBooking);
                },

                () -> {
                    throw new FunctionalException(new FunctionalExceptionDto("You are not authorized to cancel this booking.", HttpStatus.FORBIDDEN));
                }
        );


    }

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


}
