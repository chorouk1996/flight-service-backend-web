package com.service.backend.web.controllers;


import com.service.backend.web.models.responses.NotificationResponse;
import com.service.backend.web.services.helper.SecurityHelper;
import com.service.backend.web.services.interfaces.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/notification")
public class NotificationController {

    @Autowired
    private INotificationService notificationService;


    @GetMapping("/all")
    public List<NotificationResponse> getAllNotifications() {
        return notificationService.getAlNotifications(SecurityHelper.getUserConnected().getUsername());
    }

    @PostMapping("/{id}/mark-as-read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
         notificationService.markAsRead(id, SecurityHelper.getUserConnected().getUsername());
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
