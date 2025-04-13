package com.service.backend.web;

import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.models.dto.FlightDto;
import com.service.backend.web.models.dto.PassengerDto;
import com.service.backend.web.models.dto.UserDto;
import com.service.backend.web.services.inetrface.IBookingService;
import com.service.backend.web.services.inetrface.IFlightService;
import com.service.backend.web.services.inetrface.IPassengerService;
import com.service.backend.web.services.inetrface.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static com.service.backend.web.services.helper.BookingServiceHelper.mapBookingDtoToEntity;

@Component
public class TestCommandLineRunner implements CommandLineRunner {

    @Autowired
    private  IUserService userService ;

    @Autowired
    private  IFlightService flightService;

    @Autowired
    private  IPassengerService passService;

    @Autowired
    private  IBookingService bookingService;

    @Override
    public void run(String... args) throws Exception {
        UserDto user = new UserDto();
        user.setEmail("amahri.chorouk@gmail.com");
        user.setName("chorouk");
        user.setRole("admin");


        BookingDto booking = new BookingDto();
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus("ongoing");

        user.setBooking(List.of(booking));

        FlightDto flight = new FlightDto();
        flight.setBooking(List.of(booking));
        flight.setArrivalTime(LocalDateTime.now().plusMonths(12).plusHours(2));
        flight.setDepartureTime(LocalDateTime.now().plusMonths(12));
        flight.setDestination("Bangok");
        flight.setPrice(100.00);
        flight.setSeats(12);
        flight.setOrigin("casablanca");


        booking.setFlight(flight);

        PassengerDto passenger = new PassengerDto();
        passenger.setAge(19);
        passenger.setBooking(booking);
        passenger.setName("chorouk");

        booking.setPassengers(List.of(passenger));
        booking.setUser(user);
        bookingService.addBooking(booking);

    }
}
