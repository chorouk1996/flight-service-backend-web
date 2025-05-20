package com.service.backend.web.scheduled;


import com.service.backend.web.services.implementation.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class CheckPendingPayments {

    private final BookingService bookingService;
    @Scheduled(cron = "0 * * * * *")
    public  void checkPendingPayments(){

        System.out.println("Cancelling pending booking.... ");
        bookingService.cancelAllPendingPaymentBooking();
    }
}
