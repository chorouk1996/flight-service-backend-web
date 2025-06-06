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
@Schema(name = "SearchBookingResponse", description = "Represents detailed information about a booking returned from an admin search.")
public class SearchBookingResponse {

    @Schema(description = "Unique identifier for the booking", example = "1005")
    private Long bookingId;

    @Schema(description = "Origin city or airport of the flight", example = "Casablanca")
    private String flightOrigin;

    @Schema(description = "Destination city or airport of the flight", example = "Paris")
    private String flightDestination;

    @Schema(description = "Flight number", example = "AF302")
    private String flightNumber;

    @Schema(description = "Email of the user who made the booking", example = "user@example.com")
    private String userEmail;

    @Schema(description = "Scheduled departure time", example = "2025-07-10T09:00:00")
    private LocalDateTime departureTime;

    @Schema(description = "Date and time the booking was created", example = "2025-06-15T14:20:00")
    private LocalDateTime bookingDate;

    @Schema(description = "Status of the booking", example = "CONFIRMED")
    private BookingStatusEnum status;

    @Schema(description = "List of passengers included in this booking")
    private List<PassengerDto> passengers;
}
