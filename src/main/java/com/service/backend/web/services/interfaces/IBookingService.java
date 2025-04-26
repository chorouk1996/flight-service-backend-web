package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.models.dto.requests.CreateBookingRequest;
import com.service.backend.web.models.dto.responses.CreateBookingResponse;

import java.util.List;

public interface IBookingService {

     CreateBookingResponse addBooking(CreateBookingRequest booking, String username);
     List<BookingDto> getAllBooking();

     void cancelBooking(Long booking);


}
