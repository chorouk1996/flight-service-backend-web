package com.service.backend.web.scheduled;


import com.service.backend.web.services.implementation.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class CheckPendingPayments {

    @Autowired
    BookingService bookingService;
    @Scheduled(cron = "0 * * * * *")
    public  void checkPendingPayments(){

        System.out.println("Cancelling pending booking.... ");
        bookingService.cancelAllPendingPaymentBooking();
    }
}
