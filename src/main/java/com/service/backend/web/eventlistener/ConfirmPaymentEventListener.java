package com.service.backend.web.eventlistener;

import com.service.backend.web.events.ConfirmPaymentEvent;
import com.service.backend.web.services.interfaces.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class ConfirmPaymentEventListener {

    @Autowired
    INotificationService notificationService;

    @EventListener
    public void confirmPayment(ConfirmPaymentEvent event) {
        System.out.println("Confirm Payment with  : " + event.getBooking());
        notificationService.sendPaymentConfirmationNotification(event.getBooking());

    }
}
