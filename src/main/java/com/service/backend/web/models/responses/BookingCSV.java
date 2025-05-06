package com.service.backend.web.models.responses;

import com.service.backend.web.models.enumerators.BookingStatusEnum;

import java.time.LocalDateTime;

public record BookingCSV(
        Long bookingId,
        String flightNumber,
        String origin,
        String destination,
        LocalDateTime departureTime,
        LocalDateTime bookingDate,
        String userEmail,
        int passengerCount,
        BookingStatusEnum status
) {}

