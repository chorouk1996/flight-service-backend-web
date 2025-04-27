package com.service.backend.web.services.implementation;


import com.service.backend.web.events.DelayFlightEvent;
import com.service.backend.web.exceptions.FunctionalException;
import com.service.backend.web.exceptions.FunctionalExceptionDto;
import com.service.backend.web.models.dto.FlightDto;
import com.service.backend.web.models.dto.NotificationDto;
import com.service.backend.web.models.dto.UserDto;
import com.service.backend.web.models.entities.Notification;
import com.service.backend.web.models.enumerators.NotificationTypeEnum;
import com.service.backend.web.models.responses.NotificationResponse;
import com.service.backend.web.repositories.NotificationRepository;
import com.service.backend.web.services.interfaces.IFlightService;
import com.service.backend.web.services.interfaces.INotificationService;
import com.service.backend.web.services.interfaces.IUserService;
import com.service.backend.web.services.mapper.NotificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    IFlightService flightService;

    @Autowired
    IUserService userService;

    @Override
    public void sendDelayNotificationToAllUsers(DelayFlightEvent event, List<UserDto> users) {

        FlightDto flight = flightService.getFlight(event.getFlightId());
        users.stream().forEach(
                user -> {
                    NotificationDto notification = new NotificationDto();
                    notification.setCreatedAt(LocalDateTime.now());
                    notification.setFlight(flight);
                    notification.setMessage(String.format("""
                            Dear Passenger,
                                            
                                            
                            We would like to inform you that your flight (%s) has been delayed.
                                            
                            Reason: %s
                                            
                                            
                            We sincerely apologize for the inconvenience caused.
                            Our teams are doing their best to minimize the delay and keep you updated.
                                            
                                            
                            Thank you for your understanding.""", flight.getFlightNumber(), event.getReason())
                    );
                    notification.setTitle("Flight " + flight.getFlightNumber() + " - Schedule Update");
                    notification.setUser(user);
                    notification.setRead(false);
                    notification.setType(NotificationTypeEnum.INFO);
                    notificationRepository.save(NotificationMapper.mapNotificationDtoToEntity(notification));
                }
        );

    }

    @Override
    public List<NotificationResponse> getAlNotifications(String username) {
        return notificationRepository.findByUser(userService.getUserById(username)).stream().map(NotificationMapper::mapEntityToNotificationResponse).toList();
    }

    @Override
    public void markAsRead(Long id, String username) {

        Notification notif  = notificationRepository.findByIdAndUser(id, userService.getUserById(username)).orElseThrow(
                () -> {
                    throw new FunctionalException(new FunctionalExceptionDto("This notification doesn't exist", HttpStatus.NOT_FOUND));
                }

        );
        notif.setRead(true);
        notificationRepository.save(notif);
    }

    @Override
    public void deleteExpiredNotifications() {
        notificationRepository.deleteByCreatedAtBefore(LocalDateTime.now().minusMonths(3));
    }
}
