package com.service.backend.web.services.interfaces;

import com.service.backend.web.models.dto.BookingDto;

import java.util.List;

public interface IBookingService {

    public void addBooking(BookingDto booking);
    public List<BookingDto> getAllBooking();

}
