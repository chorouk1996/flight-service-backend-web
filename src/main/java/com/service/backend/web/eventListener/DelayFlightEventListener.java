package com.service.backend.web.eventListener;

import com.service.backend.web.events.DelayFlightEvent;
import com.service.backend.web.models.entities.User;
import com.service.backend.web.services.implementation.BookingService;
import com.service.backend.web.services.implementation.NotificationService;
import com.service.backend.web.services.implementation.PassengerService;
import com.service.backend.web.services.interfaces.IBookingService;
import com.service.backend.web.services.interfaces.INotificationService;
import com.service.backend.web.services.interfaces.IPassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DelayFlightEventListener {

    @Autowired
    INotificationService notificationService;
    @Autowired
    IBookingService bookingService;

    @Autowired
    IPassengerService passengerService;


    @EventListener
    public void handleUserRegistered(DelayFlightEvent event) {
        System.out.println("Flight was delayed with id : " + event.getFlightId());

        List<User> users = bookingService.getMailsWithThisDelayedFlightAndConfirmedBooking(event.getFlightId());
        notificationService.sendDelayNotificationToAllUsers(event,users);

    }
}
