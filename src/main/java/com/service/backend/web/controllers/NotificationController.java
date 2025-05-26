package com.service.backend.web.controllers;


import com.service.backend.web.models.responses.NotificationResponse;
import com.service.backend.web.services.helper.SecurityHelper;
import com.service.backend.web.services.interfaces.INotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/notification")
@AllArgsConstructor
@Tag(name = "Notification (User)", description = "Endpoints for users to view and manage their flight-related notifications.")
public class NotificationController {

    private final INotificationService notificationService;

    @Operation(
            summary = "Get all user notifications",
            description = "Returns a list of all notifications for the currently authenticated user."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Notifications retrieved successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/all")
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).USER")
    public List<NotificationResponse> getAllNotifications() {
        return notificationService.getAlNotifications(SecurityHelper.getUserConnected().getUsername());
    }

    @Operation(
            summary = "Mark notification as read",
            description = "Marks a specific notification as read for the currently authenticated user."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Notification marked as read"),
            @ApiResponse(responseCode = "403", description = "Access denied"),
            @ApiResponse(responseCode = "404", description = "Notification not found")
    })
    @PostMapping("/{notificationId}/mark-as-read")
    @PreAuthorize("hasAuthority(T(com.service.backend.web.constantes.Role).USER")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> markAsRead(@PathVariable Long notificationId) {
        notificationService.markAsRead(notificationId, SecurityHelper.getUserConnected().getUsername());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}