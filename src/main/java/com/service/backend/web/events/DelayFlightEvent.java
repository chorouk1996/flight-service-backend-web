package com.service.backend.web.events;

public class DelayFlightEvent {

    private Long flightId;


    private String reason;

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    public DelayFlightEvent(Long flightId,String reason) {
        this.flightId = flightId;
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
