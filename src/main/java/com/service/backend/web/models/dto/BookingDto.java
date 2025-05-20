package com.service.backend.web.models.dto;


import com.service.backend.web.models.enumerators.BookingStatusEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class BookingDto {
    private Long id;

    private UserDto user;

    private FlightDto flight;

    private LocalDateTime bookingDate;

    private BookingStatusEnum status;

    private List<PassengerDto> passengers;



}
