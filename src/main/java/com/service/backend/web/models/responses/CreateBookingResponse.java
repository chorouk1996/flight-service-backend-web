package com.service.backend.web.models.responses;

import com.service.backend.web.models.dto.PassengerDto;
import com.service.backend.web.models.enumerators.BookingStatusEnum;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import io.swagger.v3.oas.annotations.media.Schema;


@Data
@Schema(name = "CreateBookingResponse", description = "Response returned after a successful flight booking.")
public class CreateBookingResponse {

    @Schema(description = "Unique identifier of the booking", example = "123")
    private Long id;

    @Schema(description = "ID of the user who made the booking", example = "45")
    private Long user;

    @Schema(description = "ID of the booked flight", example = "78")
    private Long flight;

    @Schema(description = "Timestamp when the booking was created", example = "2025-05-30T12:45:00")
    private LocalDateTime bookingDate;

    @Schema(description = "Status of the booking", example = "PENDING_PAYMENT")
    private BookingStatusEnum status;

    @Schema(description = "List of passengers associated with the booking")
    private List<PassengerDto> passengers;
}
