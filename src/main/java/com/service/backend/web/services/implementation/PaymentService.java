package com.service.backend.web.services.implementation;

import com.service.backend.web.events.ConfirmPaymentEvent;
import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.models.responses.PaymentResponse;
import com.service.backend.web.services.interfaces.IBookingService;
import com.service.backend.web.services.interfaces.IPaymentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PaymentService implements IPaymentService {

    private final IBookingService bookingService;

    private final ApplicationEventPublisher applicationEventPublisher;
    @Override
    public PaymentResponse pay(Long id, String username) {

        if (Math.random() < 0.8) {
            BookingDto booking = bookingService.getBookingByIdandUser(id, username);
            bookingService.confirmBooking(booking.getId());
            applicationEventPublisher.publishEvent(new ConfirmPaymentEvent(id));
            return new PaymentResponse("Payment Successful");
        } else {
            return new PaymentResponse("Payment Failed");
        }


    }
}
