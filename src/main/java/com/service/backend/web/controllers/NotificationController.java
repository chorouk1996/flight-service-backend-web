package com.service.backend.web.controllers;


import com.service.backend.web.models.dto.NotificationDto;
import com.service.backend.web.models.responses.NotificationResponse;
import com.service.backend.web.security.UserDetailsImpl;
import com.service.backend.web.services.interfaces.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/notification")
public class NotificationController {

    @Autowired
    private INotificationService notificationService;


    @GetMapping("/all")
    public List<NotificationResponse> getAllNotifications() {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return notificationService.getAlNotifications(user.getUsername());
    }

    @PostMapping("/{id}/mark-as-read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
         notificationService.markAsRead(id,user.getUsername());
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
