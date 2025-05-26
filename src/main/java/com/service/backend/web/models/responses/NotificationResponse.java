package com.service.backend.web.models.responses;


import com.service.backend.web.models.enumerators.NotificationTypeEnum;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationResponse {

    private Long id;

    private String title;
    private String message;
    private LocalDateTime createdAt;
    private Boolean isRead;

    private NotificationTypeEnum type;



}
