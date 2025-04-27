package com.service.backend.web.services.interfaces;


import com.service.backend.web.events.DelayFlightEvent;
import com.service.backend.web.models.dto.UserDto;
import com.service.backend.web.models.responses.NotificationResponse;

import java.util.List;

public interface INotificationService {

    void sendDelayNotificationToAllUsers(DelayFlightEvent event, List<UserDto>  users) ;

    List<NotificationResponse> getAlNotifications(String username);

    void markAsRead(Long id, String user);

    void deleteExpiredNotifications();

    void sendPaymentConfirmationNotification(Long booking);
}
