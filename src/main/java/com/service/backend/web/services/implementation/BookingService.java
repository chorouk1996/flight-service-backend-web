package com.service.backend.web.services.implementation;

import com.opencsv.ICSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.service.backend.web.exceptions.FunctionalException;
import com.service.backend.web.exceptions.FunctionalExceptionDto;
import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.models.dto.UserDto;
import com.service.backend.web.models.entities.Booking;
import com.service.backend.web.models.entities.Passenger;
import com.service.backend.web.models.entities.User;
import com.service.backend.web.models.enumerators.BookingStatusEnum;
import com.service.backend.web.models.requests.CreateBookingRequest;
import com.service.backend.web.models.requests.SearchBookingRequest;
import com.service.backend.web.models.responses.*;
import com.service.backend.web.repositories.BookingCustomRepository;
import com.service.backend.web.repositories.BookingRepository;
import com.service.backend.web.services.interfaces.IAuditLogService;
import com.service.backend.web.services.interfaces.IBookingService;
import com.service.backend.web.services.interfaces.ISavedPassengerService;
import com.service.backend.web.services.mapper.BookingMapper;
import com.service.backend.web.services.mapper.FlightMapper;
import com.service.backend.web.services.mapper.PassengerMapper;
import com.service.backend.web.services.mapper.UserMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.service.backend.web.services.mapper.PassengerMapper.mapSavedPassengerToPassenger;

@Service
public class BookingService implements IBookingService {

    private static final String ALREADY_CANCELED_BOOKING = "This booking does not exist or has already been cancelled";
    BookingRepository bookingRepository;

    @Autowired
    ISavedPassengerService savedPassengerService;


    @Autowired
    FlightService flightService;

    @Autowired
    UserService userService;

    @Autowired
    BookingCustomRepository bookingCustomRepository;

    @Autowired
    IAuditLogService auditLogService;
    @Override
    public CreateBookingResponse addBooking(CreateBookingRequest booking, String username) {
        if (booking.getPassengerIds().isEmpty() && booking.getPassengers().isEmpty())
            throw new FunctionalException(new FunctionalExceptionDto("A booking must contain at least one passenger.", HttpStatus.BAD_REQUEST));
        flightService.checkAvailableSeat(booking.getFlightId(), booking.getPassengerIds().size() + booking.getPassengers().size());
        Booking bookingToAdd = new Booking();
        User user = userService.getUserByEmail(username);
        List<Passenger> passengers = new ArrayList<>();
        bookingToAdd.setFlight(FlightMapper.mapFlightDtoToEntity(flightService.getAvailableFlight(booking.getFlightId())));
        if (!booking.getPassengers().isEmpty()) {
            List<Passenger> passenger = booking.getPassengers().stream().map(PassengerMapper::mapCreatePassengerRequestToEntity).toList();
            passenger.forEach(pass -> pass.setBookedByUserEmail(user.getEmail()));
            passengers.addAll(passenger);
        }
        if (!booking.getPassengerIds().isEmpty()) {
            booking.getPassengerIds().forEach(id -> {
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
    @Transactional
    public void cancelAllPendingPaymentBooking() {
        List<Booking> bookings = bookingRepository.findByStatusAndBookingDateBefore(BookingStatusEnum.PENDING_PAYMENT, LocalDateTime.now().minusMinutes(10));
        bookings.forEach(
                booking -> {
                    booking.setStatus(BookingStatusEnum.CANCELLED);
                    flightService.increaseSeat(booking.getFlight().getId(), booking.getPassengers().size());
                    auditLogService.auditBookingCancel(BookingStatusEnum.PENDING_PAYMENT,booking.getId());
                }
        );
        bookingRepository.saveAll(bookings);
    }

    @Override
    public List<MyBookingResponse> getAllBooking(String username, int page, int size) {
        final Pageable pageableRequest = PageRequest.of(page, size);
        return bookingRepository.findByUser(userService.getUserByEmail(username), pageableRequest).stream().map(BookingMapper::mapBookingEntityToMyBookingResponse).toList();
    }

    @Override
    public List<MyBookingResponse> getUpcomingBooking(String username, int page, int size) {
        final Pageable pageableRequest = PageRequest.of(page, size);
        return bookingRepository.findByUserAndStatusAndFlight_DepartureTimeAfter(userService.getUserByEmail(username), BookingStatusEnum.CONFIRMED, LocalDateTime.now(), pageableRequest).stream().map(BookingMapper::mapBookingEntityToMyBookingResponse).toList();

    }

    @Override
    public List<MyBookingResponse> getPastBooking(String username, int page, int size) {
        final Pageable pageableRequest = PageRequest.of(page, size);
        return bookingRepository.findByUserAndStatusAndFlight_DepartureTimeBefore(userService.getUserByEmail(username), BookingStatusEnum.CONFIRMED, LocalDateTime.now(), pageableRequest).stream().map(BookingMapper::mapBookingEntityToMyBookingResponse).toList();

    }

    @Override
    public List<BookingDto> getConfirmedAndDepartedBooking() {
        return Collections.emptyList();
    }

    @Override
    public void cancelBooking(Long booking) {
        bookingRepository.findByIdAndStatusNot(booking, BookingStatusEnum.CANCELLED).ifPresentOrElse(
                myBooking -> {
                    if (!(myBooking.getStatus().equals(BookingStatusEnum.PENDING_PAYMENT) || myBooking.getStatus().equals(BookingStatusEnum.CONFIRMED)))
                        throw new FunctionalException(new FunctionalExceptionDto("Only pending payment bookings can be cancelled manually", HttpStatus.CONFLICT));
                    flightService.increaseSeat(myBooking.getFlight().getId(), myBooking.getPassengers().size());
                    BookingStatusEnum oldStatus  = myBooking.getStatus();
                    myBooking.setStatus(BookingStatusEnum.CANCELLED);
                    bookingRepository.save(myBooking);
                    auditLogService.auditBookingCancel(oldStatus,myBooking.getId());

                },

                () -> {
                    throw new FunctionalException(new FunctionalExceptionDto(ALREADY_CANCELED_BOOKING, HttpStatus.NOT_FOUND));
                }
        );


    }

    @Override
    public void cancelMyBooking(Long booking, String username) {

        bookingRepository.findByIdAndUserAndStatusNot(booking, userService.getUserByEmail(username), BookingStatusEnum.CANCELLED).ifPresentOrElse(
                myBooking -> {
                    flightService.increaseSeat(myBooking.getFlight().getId(), myBooking.getPassengers().size());
                    BookingStatusEnum oldStatus  = myBooking.getStatus();
                    myBooking.setStatus(BookingStatusEnum.CANCELLED);
                    bookingRepository.save(myBooking);
                    auditLogService.auditBookingCancel(oldStatus,myBooking.getId());

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
                    System.out.println("Admin confirmed booking " + myBooking.getId());
                    bookingRepository.save(myBooking);
                    auditLogService.auditBookingConfirmation(myBooking.getId());
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
        return BookingMapper.mapBookingEntityToDto(bookingRepository.findByIdAndStatusAndUser(id, BookingStatusEnum.PENDING_PAYMENT, userService.getUserByEmail(username)).orElseThrow(
                () -> {
                    throw new FunctionalException(new FunctionalExceptionDto(ALREADY_CANCELED_BOOKING, HttpStatus.NOT_FOUND));
                }
        ));
    }

    @Override
    public BookingDto getBookingById(Long id) {
        return BookingMapper.mapBookingEntityToDto(bookingRepository.findById(id).orElseThrow(
                () -> {
                    throw new FunctionalException(new FunctionalExceptionDto(ALREADY_CANCELED_BOOKING, HttpStatus.NOT_FOUND));
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
    public List<BookingDto> getAllBooking(int page, int size) {
        final Pageable pageableRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "bookingDate"));
        return bookingRepository.findAll(pageableRequest).stream().map(BookingMapper::mapBookingEntityToDto).toList();
    }

    @Override
    public List<SearchBookingResponse> searchBooking(SearchBookingRequest request) {
        return bookingCustomRepository.findByCriteria(request).stream().map(BookingMapper::mapBookingEntityToSearchBookingResponse).toList();
    }

    @Override
    public Double calculateBookingRevenue() {
        return bookingRepository.findByStatus(BookingStatusEnum.CONFIRMED).stream().map(booking -> booking.getFlight().getPrice() * booking.getPassengers().size()).reduce(Double::sum).orElse(0.0);
    }

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void exportAllBookingto(HttpServletResponse response){
        List<BookingCSV> bookings = getBookingDtoExport();
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition","attachment; filename=bookings");
        try {
            StatefulBeanToCsv<BookingCSV> writer = new StatefulBeanToCsvBuilder<BookingCSV>(response.getWriter()).withSeparator(ICSVWriter.DEFAULT_SEPARATOR).build();
            writer.write(bookings);
        }
        catch(Exception ex){
            throw new FunctionalException(new FunctionalExceptionDto("can't export cvs, try later",HttpStatus.SERVICE_UNAVAILABLE));
        }

    }

    @Override
    public List<BookingByMonthResponse> getBookingsByMonth() {
        return bookingRepository.getBookingsByMonth().stream().map(BookingByMonthResponse::new).toList();
    }

    private List<BookingCSV> getBookingDtoExport() {
        return bookingRepository.findAll().stream().map(BookingMapper::mapBookingEntityToBookingCSV).toList();
    }

}
