package com.service.backend.web.models.dto;


import com.service.backend.web.models.enumerators.BookingStatusEnum;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingDto {
    private Long id;

    private UserDto user;

    private FlightDto flight;

    private LocalDateTime bookingDate;

    private BookingStatusEnum status;

    private List<PassengerDto> passengers;



}
