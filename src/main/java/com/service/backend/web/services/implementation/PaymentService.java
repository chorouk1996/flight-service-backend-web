package com.service.backend.web.services.implementation;

import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.services.interfaces.IBookingService;
import com.service.backend.web.services.interfaces.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService implements IPaymentService {

    @Autowired
    IBookingService bookingService;

    @Override
    public void pay(Long id,String username) {
       BookingDto booking =  bookingService.getBookingByIdandUser(id,username);

        bookingService.confirmBooking(booking.getId());

    }
}
