package com.service.backend.web.models.responses;

public class PaymentResponse {

    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PaymentResponse(String message) {
        this.message = message;
    }
}
