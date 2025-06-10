package com.service.backend.web.scheduled;


import com.service.backend.web.services.interfaces.INotificationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class DeleteExpiredNotificationTask {

    private final INotificationService notificationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteExpiredNotificationTask.class);

    @Scheduled(cron = "0 0 * * * ?")
    public  void deleteExpiredNotification(){
        LOGGER.info("Deleting expired notifications.... ");
        notificationService.deleteExpiredNotifications();
    }
}
