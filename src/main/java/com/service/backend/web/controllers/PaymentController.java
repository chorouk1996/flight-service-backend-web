package com.service.backend.web.controllers;


import com.service.backend.web.models.responses.PaymentResponse;
import com.service.backend.web.services.helper.SecurityHelper;
import com.service.backend.web.services.interfaces.IPaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/bookings")
@AllArgsConstructor
@Tag(name = "Payment (User)", description = "Handles payments for flight bookings by authenticated users.")
public class PaymentController {

    private final IPaymentService paymentService;

    @Operation(
            summary = "Pay for a booking",
            description = "Processes the payment for a given booking. The booking must belong to the authenticated user and be in a payable state."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment processed successfully"),
            @ApiResponse(responseCode = "403", description = "Access denied or booking does not belong to user"),
            @ApiResponse(responseCode = "404", description = "Booking not found or already paid"),
            @ApiResponse(responseCode = "500", description = "Payment processing failed")
    })
    @PutMapping("{bookingId}/pay")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<PaymentResponse> pay(@PathVariable Long bookingId) {
        return new ResponseEntity<>(
                paymentService.pay(bookingId, SecurityHelper.getUserConnected().getUsername()),
                HttpStatus.OK
        );
    }
}