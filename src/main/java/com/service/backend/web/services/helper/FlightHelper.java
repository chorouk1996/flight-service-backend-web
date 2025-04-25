package com.service.backend.web.services.helper;

import com.service.backend.web.models.dto.FlightDto;
import com.service.backend.web.models.entities.Flight;
import com.service.backend.web.models.enumerators.FlightStatusEnum;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.TemporalUnit;


public class FlightHelper {

    private FlightHelper() {
        throw new UnsupportedOperationException("Don't instantiate this  Utility class");
    }



    public static Long calculateDuration(LocalDateTime departureTime, LocalDateTime arrivalTime) {
        return Duration.between(departureTime,arrivalTime).toMinutes();
    }
}
