package com.service.backend.web.models.responses;

import com.service.backend.web.models.enumerators.BookingStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(name = "BookingCSV", description = "Represents a booking record suitable for CSV export.")
public record BookingCSV(
        @Schema(description = "Unique ID of the booking", example = "101")
        Long bookingId,

        @Schema(description = "Flight number associated with the booking", example = "AF203")
        String flightNumber,

        @Schema(description = "Flight origin", example = "Casablanca")
        String origin,

        @Schema(description = "Flight destination", example = "Paris")
        String destination,

        @Schema(description = "Scheduled departure time", example = "2025-06-10T08:00:00")
        LocalDateTime departureTime,

        @Schema(description = "Date and time when the booking was made", example = "2025-05-15T14:30:00")
        LocalDateTime bookingDate,

        @Schema(description = "Email of the user who made the booking", example = "user@example.com")
        String userEmail,

        @Schema(description = "Number of passengers associated with the booking", example = "3")
        int passengerCount,

        @Schema(description = "Status of the booking", example = "CONFIRMED")
        BookingStatusEnum status
) {}