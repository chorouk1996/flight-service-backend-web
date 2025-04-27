package com.service.backend.web.services.mapper;

import com.service.backend.web.models.dto.BookingDto;
import com.service.backend.web.models.dto.NotificationDto;
import com.service.backend.web.models.dto.responses.CreateBookingResponse;
import com.service.backend.web.models.dto.responses.MyBookingResponse;
import com.service.backend.web.models.entities.Booking;
import com.service.backend.web.models.entities.Notification;

import static com.service.backend.web.services.mapper.FlightMapper.mapFlightDtoToEntity;
import static com.service.backend.web.services.mapper.FlightMapper.mapFlightEntityToDto;
import static com.service.backend.web.services.mapper.UserMapper.mapUserDtoToEntity;


public  class NotificationMapper {

    private NotificationMapper() {
        throw new UnsupportedOperationException("Don't instantiate this  Utility class");
    };


    public static Notification mapNotificationDtoToEntity(NotificationDto dto) {
        Notification notification = new Notification();
        notification.setFlight(dto.getFlight());
        notification.setCreatedAt(dto.getCreatedAt());
        notification.setUser(dto.getUser());
        notification.setRead(dto.getRead());
        notification.setType(dto.getType());
        notification.setMessage(dto.getMessage());
        notification.setTitle(dto.getTitle());
        return notification;
    }

}
