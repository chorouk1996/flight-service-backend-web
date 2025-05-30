package com.service.backend.web.models.responses;

import com.service.backend.web.models.dto.PassengerDto;
import com.service.backend.web.models.enumerators.BookingStatusEnum;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MyBookingResponse {

    private Long bookingId;
    private String flightOrigin;
    private String flightDestination;
    private LocalDateTime departureTime;
    private LocalDateTime bookingDate;
    private BookingStatusEnum status;

    private List<PassengerDto> passengers;



}
