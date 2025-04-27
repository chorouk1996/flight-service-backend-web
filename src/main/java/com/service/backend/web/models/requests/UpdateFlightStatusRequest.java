package com.service.backend.web.models.requests;

import com.service.backend.web.models.enumerators.FlightStatusEnum;

public class UpdateFlightStatusRequest {

    private FlightStatusEnum status;

    private String delayReason;


    public FlightStatusEnum getStatus() {
        return status;
    }

    public void setStatus(FlightStatusEnum status) {
        this.status = status;
    }


    public String getDelayReason() {
        return delayReason;
    }

    public void setDelayReason(String delayReason) {
        this.delayReason = delayReason;
    }
}
