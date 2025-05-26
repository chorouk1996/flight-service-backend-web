package com.service.backend.web.scheduled;


import com.service.backend.web.services.implementation.BookingService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class CheckPendingPayments {

    private final BookingService bookingService;

    private final static Logger LOGGER = LoggerFactory.getLogger(CheckPendingPayments.class);
    @Scheduled(cron = "0 * * * * *")
    public  void checkPendingPayments(){

        LOGGER.info("Cancelling pending booking.... ");
        bookingService.cancelAllPendingPaymentBooking();
    }
}
