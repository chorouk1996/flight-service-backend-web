package com.service.backend.web.scheduled;


import com.service.backend.web.services.implementation.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class DeleteExpiredNotificationTask {

    @Autowired
    NotificationService notificationService;
    @Scheduled(cron = "0 0 * * * ?")
    public  void deleteExpiredNotification(){
        System.out.println("Deleting expired notifications.... ");
        notificationService.deleteExpiredNotifications();
    }
}
