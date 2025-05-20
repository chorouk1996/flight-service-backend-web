package com.service.backend.web.models.responses;


import com.service.backend.web.models.enumerators.NotificationTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class NotificationResponse {

    private Long id;

    private String title;
    private String message;
    private LocalDateTime createdAt;
    private Boolean isRead;

    private NotificationTypeEnum type;



}
