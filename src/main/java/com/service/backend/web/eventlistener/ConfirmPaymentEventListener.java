package com.service.backend.web.eventlistener;

import com.service.backend.web.events.ConfirmPaymentEvent;
import com.service.backend.web.services.implementation.BookingService;
import com.service.backend.web.services.interfaces.INotificationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class ConfirmPaymentEventListener {

    private final INotificationService notificationService;

    private final static Logger LOGGER = LoggerFactory.getLogger(ConfirmPaymentEventListener.class);

    @EventListener
    public void confirmPayment(ConfirmPaymentEvent event) {
        LOGGER.info("Confirm Payment with  : " + event.getBooking());
        notificationService.sendPaymentConfirmationNotification(event.getBooking());

    }
}
