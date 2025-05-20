package com.service.backend.web.eventlistener;

import com.service.backend.web.events.ConfirmPaymentEvent;
import com.service.backend.web.services.interfaces.INotificationService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class ConfirmPaymentEventListener {

    private final INotificationService notificationService;

    @EventListener
    public void confirmPayment(ConfirmPaymentEvent event) {
        System.out.println("Confirm Payment with  : " + event.getBooking());
        notificationService.sendPaymentConfirmationNotification(event.getBooking());

    }
}
