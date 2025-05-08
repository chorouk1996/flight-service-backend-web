package com.service.backend.web.models.dto;


import com.service.backend.web.models.enumerators.NotificationTypeEnum;

import java.time.LocalDateTime;

public class NotificationDto {

    private Long id;

    private UserDto user;

    private String title;
    private String message;
    private LocalDateTime createdAt;
    private Boolean isRead;

    private NotificationTypeEnum type;

    private FlightDto flight;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public FlightDto getFlight() {
        return flight;
    }

    public void setFlight(FlightDto flight) {
        this.flight = flight;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public NotificationTypeEnum getType() {
        return type;
    }

    public void setType(NotificationTypeEnum type) {
        this.type = type;
    }
}
