package com.service.backend.web.models.dto;


import com.service.backend.web.models.enumerators.NotificationTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class NotificationDto {

    private Long id;

    private UserDto user;

    private String title;
    private String message;
    private LocalDateTime createdAt;
    private Boolean isRead;

    private NotificationTypeEnum type;

    private FlightDto flight;



}
