package com.service.backend.web.services.interfaces;


import com.service.backend.web.events.DelayFlightEvent;
import com.service.backend.web.models.entities.User;

import java.util.List;
import java.util.Set;

public interface INotificationService {

    void sendDelayNotificationToAllUsers(DelayFlightEvent event, List<User> users) ;
}
