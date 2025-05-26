package com.service.backend.web.eventlistener;

import com.service.backend.web.events.DelayFlightEvent;
import com.service.backend.web.models.dto.UserDto;
import com.service.backend.web.services.interfaces.IBookingService;
import com.service.backend.web.services.interfaces.INotificationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DelayFlightEventListener {

    private final INotificationService notificationService;
    private final IBookingService bookingService;

    private static final  Logger LOGGER = LoggerFactory.getLogger(DelayFlightEventListener.class);

    @EventListener
    public void handleUserRegistered(DelayFlightEvent event) {
        LOGGER.info("Flight was delayed with id : {} " , event.getFlightId());

        List<UserDto> users = bookingService.getMailsWithThisDelayedFlightAndConfirmedBooking(event.getFlightId());
        notificationService.sendDelayNotificationToAllUsers(event,users);

    }
}
