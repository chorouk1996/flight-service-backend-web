package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.models.dto.requests.CreateBookingRequest;
import com.service.backend.web.models.dto.responses.CreateBookingResponse;
import com.service.backend.web.models.dto.responses.MyBookingResponse;
import com.service.backend.web.models.entities.User;

import java.util.List;
import java.util.Set;

public interface IBookingService {

    CreateBookingResponse addBooking(CreateBookingRequest booking, String username);

    List<BookingDto> getAllBooking();

    void cancelAllPendingPaymentBooking();

    void cancelBooking(Long booking);

    List<MyBookingResponse> getAllBooking(String username, int page, int size);

    List<MyBookingResponse> getUpcomingBooking(String username, int page, int size);

    List<MyBookingResponse> getPastBooking(String username, int page, int size);

    void cancelMyBooking(Long booking, String username);

    void confirmBooking(Long booking);

    List<User> getMailsWithThisDelayedFlightAndConfirmedBooking(Long flightId);

}
