package com.service.backend.web.models.responses;

import com.service.backend.web.models.dto.PassengerDto;
import com.service.backend.web.models.enumerators.BookingStatusEnum;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateBookingResponse {

    private Long id;

    private Long user;

    private Long flight;

    private LocalDateTime bookingDate;

    private BookingStatusEnum status;

    private List<PassengerDto> passengers;



}
