package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.models.dto.UserDto;
import com.service.backend.web.models.requests.CreateBookingRequest;
import com.service.backend.web.models.responses.CreateBookingResponse;
import com.service.backend.web.models.responses.MyBookingResponse;

import java.util.List;

public interface IBookingService {

    CreateBookingResponse addBooking(CreateBookingRequest booking, String username);

    List<BookingDto> getAllBooking();

    void cancelAllPendingPaymentBooking();

    void cancelBooking(Long booking);

    List<MyBookingResponse> getAllBooking(String username, int page, int size);

    List<MyBookingResponse> getUpcomingBooking(String username, int page, int size);

    List<MyBookingResponse> getPastBooking(String username, int page, int size);

    List<BookingDto> getConfirmedAndDepartedBooking();

    void cancelMyBooking(Long booking, String username);

    void confirmBooking(Long booking);

    List<UserDto> getMailsWithThisDelayedFlightAndConfirmedBooking(Long flightId);

    BookingDto getBookingByIdandUser(Long id,String username);


    Double calculateBookingRevenue();
    long countAll();

    long countCancelledBookings();

    long countConfirmedBookings();
}
