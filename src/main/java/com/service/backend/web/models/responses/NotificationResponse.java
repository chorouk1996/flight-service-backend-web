package com.service.backend.web.models.responses;


import com.service.backend.web.models.enumerators.NotificationTypeEnum;

import lombok.Data;

import java.time.LocalDateTime;

import com.service.backend.web.models.enumerators.NotificationTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(name = "NotificationResponse", description = "Represents a single user notification.")
public class NotificationResponse {

    @Schema(description = "Unique identifier of the notification", example = "501")
    private Long id;

    @Schema(description = "Title of the notification", example = "Booking Confirmed")
    private String title;

    @Schema(description = "Detailed notification message", example = "Your booking #1234 has been confirmed.")
    private String message;

    @Schema(description = "Timestamp when the notification was created", example = "2025-07-01T14:20:00")
    private LocalDateTime createdAt;

    @Schema(description = "Indicates whether the user has read the notification", example = "false")
    private Boolean isRead;

    @Schema(description = "Type of the notification", example = "BOOKING")
    private NotificationTypeEnum type;
}
