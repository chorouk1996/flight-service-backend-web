package com.service.backend.web.scheduled;


import com.service.backend.web.services.interfaces.INotificationService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class DeleteExpiredNotificationTask {

    private final INotificationService notificationService;
    @Scheduled(cron = "0 0 * * * ?")
    public  void deleteExpiredNotification(){
        System.out.println("Deleting expired notifications.... ");
        notificationService.deleteExpiredNotifications();
    }
}
