package com.service.backend.web.models.responses;


public class ResetTokenResponse {

    private String message;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResetTokenResponse(String message) {
        this.message = message;
    }
}
