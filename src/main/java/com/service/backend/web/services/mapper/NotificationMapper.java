package com.service.backend.web.services.mapper;

import com.service.backend.web.models.dto.NotificationDto;
import com.service.backend.web.models.entities.Notification;
import com.service.backend.web.models.responses.NotificationResponse;


public  class NotificationMapper {

    private NotificationMapper() {
        throw new UnsupportedOperationException("Don't instantiate this  Utility class");
    };


    public static Notification mapNotificationDtoToEntity(NotificationDto dto) {
        Notification notification = new Notification();
        notification.setFlight(FlightMapper.mapFlightDtoToEntity(dto.getFlight()));
        notification.setCreatedAt(dto.getCreatedAt());
        notification.setUser(UserMapper.mapUserDtoToEntity(dto.getUser()));
        notification.setRead(dto.getRead());
        notification.setType(dto.getType());
        notification.setMessage(dto.getMessage());
        notification.setTitle(dto.getTitle());
        return notification;
    }

    public static NotificationDto mapNotificationEntityToDto(Notification entity) {
        NotificationDto dto = new NotificationDto();
        dto.setId(entity.getId());
        dto.setFlight(FlightMapper.mapFlightEntityToDto(entity.getFlight()));
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUser(UserMapper.mapUserEntityToDto(entity.getUser()));
        dto.setRead(entity.getRead());
        dto.setType(entity.getType());
        dto.setMessage(entity.getMessage());
        dto.setTitle(entity.getTitle());
        return dto;
    }

    public static NotificationResponse mapEntityToNotificationResponse(Notification entity) {
        NotificationResponse dto = new NotificationResponse();
        dto.setId(entity.getId());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setRead(entity.getRead());
        dto.setType(entity.getType());
        dto.setMessage(entity.getMessage());
        dto.setTitle(entity.getTitle());
        return dto;
    }

}
