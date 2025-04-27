package com.service.backend.web.services.implementation;

import com.service.backend.web.exceptions.FunctionalException;
import com.service.backend.web.exceptions.FunctionalExceptionDto;
import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.models.dto.UserDto;
import com.service.backend.web.models.entities.Booking;
import com.service.backend.web.models.entities.Passenger;
import com.service.backend.web.models.entities.User;
import com.service.backend.web.models.enumerators.BookingStatusEnum;
import com.service.backend.web.models.requests.CreateBookingRequest;
import com.service.backend.web.models.responses.CreateBookingResponse;
import com.service.backend.web.models.responses.MyBookingResponse;
import com.service.backend.web.repositories.BookingRepository;
import com.service.backend.web.services.interfaces.IBookingService;
import com.service.backend.web.services.interfaces.ISavedPassengerService;
import com.service.backend.web.services.mapper.BookingMapper;
import com.service.backend.web.services.mapper.FlightMapper;
import com.service.backend.web.services.mapper.PassengerMapper;
import com.service.backend.web.services.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    PassengerService passengerService;

    @Autowired
    FlightService flightService;

    @Autowired
    UserService userService;

    @Override
    public CreateBookingResponse addBooking(CreateBookingRequest booking, String username) {
        if (booking.getPassengerIds().isEmpty() && booking.getPassengers().isEmpty())
            throw new FunctionalException(new FunctionalExceptionDto("A booking must contain at least one passenger.", HttpStatus.BAD_REQUEST));
        flightService.checkAvailableSeat(booking.getFlightId(), booking.getPassengerIds().size() + booking.getPassengers().size());
        Booking bookingToAdd = new Booking();
        User user = userService.getUserById(username);
        List<Passenger> passengers = new ArrayList<>();
        bookingToAdd.setFlight(FlightMapper.mapFlightDtoToEntity(flightService.getAvailableFlight(booking.getFlightId())));
        if (!booking.getPassengers().isEmpty()) {
            List<Passenger> passenger = booking.getPassengers().stream().map(PassengerMapper::mapCreatePassengerRequestToEntity).toList();
            passenger.forEach(pass -> pass.setBookedByUserEmail(user.getEmail()));
            passengers.addAll(passenger);
        }
        if (!booking.getPassengerIds().isEmpty()) {
            booking.getPassengerIds().stream().forEach(id -> {
                        Passenger passenger = mapSavedPassengerToPassenger(savedPassengerService.getSavedPassengerById(username, id));
                        passenger.setBookedByUserEmail(user.getEmail());
                        passengers.add(passenger);
                    }
            );

        }
        passengers.forEach(pass -> pass.setBooking(bookingToAdd));
        bookingToAdd.setPassengers(passengers);
        bookingToAdd.setBookingDate(LocalDateTime.now());
        bookingToAdd.setUser(user);
        bookingToAdd.setStatus(BookingStatusEnum.PENDING_PAYMENT);
        flightService.decreaseSeat(booking.getFlightId(), bookingToAdd.getPassengers().size());
        Booking book = bookingRepository.save(bookingToAdd);
        return BookingMapper.mapBookingEntityToResponse(book);
    }

    @Override
    public List<BookingDto> getAllBooking() {
        return bookingRepository.findAll().stream().map(BookingMapper::mapBookingEntityToDto).toList();
    }

    @Override
    @Transactional
    public void cancelAllPendingPaymentBooking() {
        List<Booking> bookings = bookingRepository.findByStatusAndBookingDateBefore(BookingStatusEnum.PENDING_PAYMENT, LocalDateTime.now().minusMinutes(10));
        bookings.stream().forEach(
                booking -> {
                    booking.setStatus(BookingStatusEnum.CANCELLED);
                    flightService.increaseSeat(booking.getFlight().getId(), booking.getPassengers().size());
                }
        );
        bookingRepository.saveAll(bookings);
    }

    @Override
    public List<MyBookingResponse> getAllBooking(String username, int page, int size) {
        final Pageable pageableRequest = PageRequest.of(page, size);
        return bookingRepository.findByUser(userService.getUserById(username), pageableRequest).stream().map(BookingMapper::mapBookingEntityToMyBookingResponse).toList();
    }

    @Override
    public List<MyBookingResponse> getUpcomingBooking(String username, int page, int size) {
        final Pageable pageableRequest = PageRequest.of(page, size);
        return bookingRepository.findByUserAndStatusAndFlight_DepartureTimeAfter(userService.getUserById(username), BookingStatusEnum.CONFIRMED, LocalDateTime.now(), pageableRequest).stream().map(BookingMapper::mapBookingEntityToMyBookingResponse).toList();

    }

    @Override
    public List<MyBookingResponse> getPastBooking(String username, int page, int size) {
        final Pageable pageableRequest = PageRequest.of(page, size);
        return bookingRepository.findByUserAndStatusAndFlight_DepartureTimeBefore(userService.getUserById(username), BookingStatusEnum.CONFIRMED, LocalDateTime.now(), pageableRequest).stream().map(BookingMapper::mapBookingEntityToMyBookingResponse).toList();

    }

    @Override
    public List<BookingDto> getConfirmedAndDepartedBooking() {
        return null;
    }

    @Override
    public void cancelBooking(Long booking) {
        bookingRepository.findByIdAndStatusNot(booking, BookingStatusEnum.CANCELLED).ifPresentOrElse(
                myBooking -> {
                    if (!myBooking.getStatus().equals(BookingStatusEnum.PENDING_PAYMENT))
                        throw new FunctionalException(new FunctionalExceptionDto("Only pending payment bookings can be cancelled manually", HttpStatus.CONFLICT));
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

    @Override
    public void confirmBooking(Long booking) {
        bookingRepository.findByIdAndStatus(booking, BookingStatusEnum.PENDING_PAYMENT).ifPresentOrElse(
                myBooking -> {
                    myBooking.setStatus(BookingStatusEnum.CONFIRMED);
                    bookingRepository.save(myBooking);
                },

                () -> {
                    throw new FunctionalException(new FunctionalExceptionDto("Only pending payment bookings can be confirmed manually", HttpStatus.CONFLICT));
                }
        );

    }

    public List<UserDto> getMailsWithThisDelayedFlightAndConfirmedBooking(Long flightId) {

        return bookingRepository.findByFlightIdAndStatus(flightId, BookingStatusEnum.CONFIRMED).stream().map(booking -> UserMapper.mapUserEntityToDto(booking.getUser())).toList();
    }

    @Override
    public BookingDto getBookingByIdandUser(Long id, String username) {
        return BookingMapper.mapBookingEntityToDto(bookingRepository.findByIdAndStatusAndUser(id, BookingStatusEnum.PENDING_PAYMENT, userService.getUserById(username)).orElseThrow(
                () -> {
                    throw new FunctionalException(new FunctionalExceptionDto("This booking does not exist or has already been cancelled", HttpStatus.NOT_FOUND));
                }
        ));
    }

    @Override
    public long countAll() {
        return bookingRepository.count();
    }

    @Override
    public long countCancelledBookings() {
        return bookingRepository.countByStatus(BookingStatusEnum.CANCELLED);
    }

    @Override
    public long countConfirmedBookings() {
        return bookingRepository.countByStatus(BookingStatusEnum.CONFIRMED);
    }

    @Override
    public Double calculateBookingRevenue() {
        return bookingRepository.findByStatus(BookingStatusEnum.CONFIRMED).stream().map(booking -> booking.getFlight().getPrice() * booking.getPassengers().size()).reduce((priceA, priceB) -> priceA + priceB).get();
    }

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


}
