package com.service.backend.web.models.responses;

import com.service.backend.web.models.dto.PassengerDto;
import com.service.backend.web.models.enumerators.BookingStatusEnum;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import com.service.backend.web.models.dto.PassengerDto;
import com.service.backend.web.models.enumerators.BookingStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(name = "MyBookingResponse", description = "Detailed booking information for the currently logged-in user.")
public class MyBookingResponse {

    @Schema(description = "Unique identifier for the booking", example = "2001")
    private Long bookingId;

    @Schema(description = "Origin of the flight", example = "Casablanca")
    private String flightOrigin;

    @Schema(description = "Destination of the flight", example = "Istanbul")
    private String flightDestination;

    @Schema(description = "Scheduled departure time of the flight", example = "2025-08-01T10:00:00")
    private LocalDateTime departureTime;

    @Schema(description = "Timestamp when the booking was made", example = "2025-07-15T09:30:00")
    private LocalDateTime bookingDate;

    @Schema(description = "Current status of the booking", example = "CONFIRMED")
    private BookingStatusEnum status;

    @Schema(description = "List of passengers included in this booking")
    private List<PassengerDto> passengers;
}