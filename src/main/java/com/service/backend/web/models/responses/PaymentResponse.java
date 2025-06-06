package com.service.backend.web.models.responses;

import lombok.AllArgsConstructor;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
@AllArgsConstructor
@Schema(name = "PaymentResponse", description = "Response message after attempting a payment.")
public class PaymentResponse {

    @Schema(description = "Descriptive message regarding the payment result", example = "Payment processed successfully.")
    private String message;
}